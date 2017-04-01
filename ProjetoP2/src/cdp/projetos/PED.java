package cdp.projetos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;
import cdp.utils.Validacao;

/**
 * Representa um projeto do tipo PED no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private Set<Produtividade> produtividades;
	
	private boolean temProfessorCoordenador;
	private boolean temProfessor;
	private boolean temAluno;
	
	private static double PERCENTUAL_BASE = 10.0;
	private static final double PERCENTUAL_POR_PRODUCAO_ACADEMICA = 0.2;
	private static final double PECENTUAL_POR_PRODUCAO_TECNICA = 0.3;
	private static final double PERCENTUAL_POR_100K_DE_CAPITAL = 1;
	
	
	public PED(String nomeDoProjeto, String categoria, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException, DataException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setCategoria(categoria);
		this.produtividades = new HashSet<>();
		
	}
	
	public String getProdutividades() {
		return produtividades.toString();
	}
	
	/**
	 * O metodo vai pegar a quantidade de produtividade de um determinado tipo.
	 * @param produtividade A produtividade que sera pesquisada
	 * @return Retorna, na forma de string, a quantidade de certa produtividade
	 */
	public String getProdutividade(String prod) {
		for(Produtividade produtividade: this.produtividades) {
			if (produtividade.getProdutividade().equalsIgnoreCase(prod)) {
				return String.valueOf(produtividade.getQuantidade());
			}
		}
		return null;
	}
	
	/**
	 * Adiciona uma quantidade de produtividade ao projeto, que no caso, pode ser prodAcademica, prodTecnica e patentes.
	 * @param produtividade A produtividade que sera adicionada
	 * @param quantidade A quantidade que sera adicionada
	 */
	public void adicionaProdutividade(String produtividade, int quantidade) {
		
		Iterator<Produtividade> it = this.produtividades.iterator();
		while(it.hasNext()) {
			Produtividade prod = it.next();
			if(prod.getProdutividade().equalsIgnoreCase(produtividade)) {
				it.remove();
				break;
			}
		}
		Produtividade p = new Produtividade(produtividade, quantidade);
		produtividades.add(p);
	}
	
	/**
	 * O metodo vai definir a categoria do projeto PED.
	 * @param categoria
	 * @return
	 * @throws ValidacaoException
	 */
	public boolean setCategoria(String categoria) throws ValidacaoException {

		for(CategoriaPED categ: CategoriaPED.values()) {

			if(categoria.equalsIgnoreCase(categ.getValor())) {
				this.categoria = categ;
				return true;
			}
		}
		
		throw new ValidacaoException("Categoria invalida");
	}
	
	public CategoriaPED getCategoria() {
		return categoria;
	}
	
	public void atualizaDespesasProjeto(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException, CadastroException {
		if(this.getCategoria() == CategoriaPED.COOPERACAO_EMPRESAS) {
			
			if(montanteBolsas == 0 || montanteCusteio == 0 || montanteCapital == 0) {
				throw new ValidacaoException("projeto do tipo Coop devem possuir todas as despesas");
			}
			
			Validacao.validaMontanteDespesa(montanteBolsas, montanteCusteio, montanteCapital, "montante nulo ou vazio");
		
			
			this.setDespesa("bolsa", montanteBolsas);
			this.setDespesa("custeio", montanteCusteio);
			this.setDespesa("capital", montanteCapital);
			
		} else {
			
			
			if(this.getCategoria() == CategoriaPED.PIBIC || this.getCategoria() == CategoriaPED.PIBITI) {
				if(montanteBolsas == 0) {
					throw new ValidacaoException("projeto do tipo P&D - PIBIC ou PIBIT deve permitir despesas de bolsas");
				}
				
				if(montanteCusteio > 0 || montanteCapital > 0) {
					throw new ValidacaoException("projeto do tipo P&D - PIBIC ou PIBIT nao permite despesas de custeio ou capital");
				}
			}
			
			Validacao.validaDouble(montanteBolsas, "montante nulo ou vazio");
			
			this.setDespesa("bolsa", montanteBolsas);

		}
	}

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException, ValidacaoException {
		
			
		if(this.getCategoria() == CategoriaPED.COOPERACAO_EMPRESAS) { // TIPO COOPERACAO COM EMPRESAS
			
			this.cadastraCOOP(participacaoASerAdicionada);
			
			
		} else { // TIPO PIBIC, PIBITI e PIVIC
			
			this.cadastraOutros(participacaoASerAdicionada);	
			
		}
		
		super.participacoes.add(participacaoASerAdicionada);
					
	}

	private void cadastraCOOP(Participacao participacaoASerAdicionada) throws CadastroException, ValidacaoException {
		
		if(super.ehProfessor(participacaoASerAdicionada)) {  // inicio de adicao de professores COOPERACAO

			Professor prof = (Professor) participacaoASerAdicionada;
			if(prof.getCoordenador()) { // verifica se professor eh coordenador
				if(this.temProfessorCoordenador == false) { // verifica se o projeto ja contem um coordenador
					
					this.temProfessorCoordenador = true;
				} else {
					throw new CadastroException("Projetos P&D nao podem ter mais de um coordenador");
				}
			
			} else {
				if(this.temProfessorCoordenador) { // verifica se ja tem um coordenador cadastrado, para cadastrar o professor
					if(super.participacoes.contains(participacaoASerAdicionada)) {
						throw new CadastroException("Professor ja esta cadastrado nesse projeto");
					}
					
					
				} else {
					throw new CadastroException("Projetos P&D precisam de um coordenador");

				} // fim de adicao de professores cooperacao
		
			} 					
		} else {
			
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
			}
			
		}
		
		super.verificaParticipacao(participacaoASerAdicionada.getCpf(), participacaoASerAdicionada.getCodigoProjeto());
		
	}
	
	private void cadastraOutros(Participacao participacaoASerAdicionada) throws CadastroException, ValidacaoException {

		
		if(super.ehProfessor(participacaoASerAdicionada)) {
			
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Professor ja esta cadastrado nesse projeto");
			}
			
			if(temProfessor) {
				
				throw new CadastroException("Projetos P&D nao podem ter mais de um professor");

			} else {

				this.temProfessor = true;
			}
			
		} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
			if(temAluno) {
				
				throw new CadastroException("Projetos P&D nao podem ter mais de um graduando");
			
		
			} else {

				this.temAluno = true;
			}
			
		} else if (participacaoASerAdicionada instanceof AlunoPosGraduando) {
			throw new CadastroException("Tipo de projeto invalido para pos graduando");
		}
		
		super.verificaParticipacao(participacaoASerAdicionada.getCpf(), participacaoASerAdicionada.getCodigoProjeto());
		
	}
	
	@Override
	public void removeParticipacao(Participacao participacaoASerRemovida) throws CadastroException, ValidacaoException {
		
		if(this.getCategoria() == CategoriaPED.COOPERACAO_EMPRESAS) {
			if(participacaoASerRemovida instanceof Professor && ((Professor) participacaoASerRemovida).getCoordenador()) {
				this.setTemProfessorCoordenador(false);
			}
			
			
		} else {
			if(participacaoASerRemovida instanceof Professor) {
				this.setTemProfessor(false);
			} else {
				this.setTemAluno(false);
			}
		}
		
		boolean removeu = super.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
	}
	
	public void setTemAluno(boolean bool) {
		this.temAluno = bool;
	}
	
	public void setTemProfessor(boolean bool) {
		this.temProfessor = bool;
	}
	
	/**
	 * O metodo vai retornar todas as produtividades do projeto.
	 * @param descricao
	 * @return
	 * @throws ValidacaoException
	 */
	public String getRepresentacaoProdutividade(String descricao) throws ValidacaoException {
		for(Produtividade produtividade: this.produtividades) {
			if(produtividade.getProdutividade().equalsIgnoreCase(descricao)) {
				return Integer.toString(produtividade.getQuantidade());
			}
		}
		
		throw new ValidacaoException("Projeto nao tem essa produtividade");
	}

	public void setTemProfessorCoordenador(boolean b) {
		this.temProfessorCoordenador = b;
		
	}
	
	
	/**
	 * Calcula o total da contribuicao de um projeto para a UASC.
	 * @throws ValidacaoException 
	 */
	@Override
	public double geraContribuicao() throws ValidacaoException {
		double baseDaContribuicao = 0;
		
		if(getCategoria().equals(CategoriaPED.PIBIC) || getCategoria().equals(CategoriaPED.PIBITI) || getCategoria().equals(CategoriaPED.PIVIC)) {
			
			return 0.0;
			
		} else {
			baseDaContribuicao = super.getDespesa("capital") + super.getDespesa("custeio") + super.getDespesa("bolsa");
			
		}

		if(baseDaContribuicao <= 10000) {
			return 0.0;
		}
		
		calculaPercentualDeContribuicao();
		
		return (baseDaContribuicao * PERCENTUAL_BASE)/100.0;
		
	
	}
	/**
	 * Calcula o percentual de um projeto, com base no percentual base (10%). Aumentando e/ou diminuindo esse percentual.
	 * @throws ValidacaoException 
	 */
	private void calculaPercentualDeContribuicao() throws ValidacaoException {
		PERCENTUAL_BASE = 10;
		
		int qtdPatentes = Integer.parseInt(getProdutividade("patentes"));
		int qtdProdTecnica = Integer.parseInt(getProdutividade("producao tecnica"));
		int qtdProdAcademica = Integer.parseInt(getProdutividade("producao academica"));
		int percentualDoCapital = (int) (super.getDespesa("capital")/100000);
		
		if(qtdPatentes > 0) {
			PERCENTUAL_BASE += 3.0;
		}
		
		
		PERCENTUAL_BASE += qtdProdTecnica * PECENTUAL_POR_PRODUCAO_TECNICA;
		PERCENTUAL_BASE += percentualDoCapital * PERCENTUAL_POR_100K_DE_CAPITAL;
		PERCENTUAL_BASE -= qtdProdAcademica * PERCENTUAL_POR_PRODUCAO_ACADEMICA;
		
	}

	
	
	

}

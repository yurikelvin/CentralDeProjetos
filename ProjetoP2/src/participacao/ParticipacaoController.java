package participacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import exception.CadastroException;
import exception.ValidacaoException;
import pessoas.PessoaController;
import projetos.ProjetoController;
import validacao.ValidaPessoa;
import validacao.Validacao;

public class ParticipacaoController implements Serializable{
	
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	
	private ArrayList<Participacao> participacoes; 
	
	private static final String FIM_DE_LINHA = System.lineSeparator();

	public ParticipacaoController(PessoaController pessoaController, ProjetoController projetoController) {
		
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
		this.participacoes = new ArrayList<>();
	}
	
	/**
	 * Faz a associacao entre uma pessoa cadastrada no sistema e um projeto cadastrado no sistema, criando uma participacao.
	 * Faz associacao de Professor.
	 * @param cpf Cpf da pessoa a ter a participacao criada (precisa ser cadastrada no sistema).
	 * @param codigoProjeto Codigo do projeto cadastrado no sistema.
	 * @param coordenador Se o professor eh coordenador ou nao.
	 * @param valorHora Valor da hora de trabalho do professor.
	 * @param qtdHoras Quantidade de horas trabalhadas.
	 * @throws CadastroException Caso a pessoa/projeto nao esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso os parametros sejam nulo/vazio ou invalido.
	 */
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		
		
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		this.pessoaController.pesquisaPessoa(cpf);
		this.projetoController.pesquisaProjeto(codigoProjeto);
		
		
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {

			Professor participacao = new Professor(valorHora, qtdHoras, coordenador);
			
			this.projetoController.validaHoraProfessor(valorHora, codigoProjeto);
			
			this.pessoaController.associaPessoa(participacao, cpf);
			this.projetoController.associaProjeto(participacao, codigoProjeto);
			

			this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
			this.pessoaController.adicionaParticipacao(participacao, cpf);
			
			this.participacoes.add(participacao);
		}
		
	}
	
	/**
	 * Faz a associacao entre uma pessoa cadastrada no sistema e um projeto cadastrado no sistema, criando uma participacao.
	 * Faz associacao de Aluno Graduando.
	 * @param cpf Cpf da pessoa a ter a participacao criada (precisa ser cadastrada no sistema).
	 * @param codigoProjeto Codigo do projeto cadastrado no sistema.
	 * @param qtdHoras Quantidade de horas trabalhadas.
	 * @param valorHora Valor da hora de trabalho do aluno graudando.
	 * @throws CadastroException Caso a pessoa/projeto nao esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso os parametros sejam nulo/vazio ou invalido.
	 */
	
	public void associaGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			
			AlunoGraduando participacao = new AlunoGraduando(valorHora, qtdHoras);
			
			this.pessoaController.associaPessoa(participacao, cpf);
			this.projetoController.associaProjeto(participacao, codigoProjeto);
			

			this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
			this.pessoaController.adicionaParticipacao(participacao, cpf);
			
			this.participacoes.add(participacao);
			
		}
		
	}
	
	/**
	 * Faz a associacao entre uma pessoa cadastrada no sistema e um projeto cadastrado no sistema, criando uma participacao.
	 * Faz associacao de um Profissional.
	 * {@link Cargo}
	 * @param cpf Cpf da pessoa a ter a participacao criada (precisa ser cadastrada no sistema).
	 * @param codigoProjeto Codigo do projeto cadastrado no sistema.
	 * @param qtdHoras Quantidade de horas trabalhadas.
	 * @param valorHora Valor da hora de trabalho do profissional.
	 * @param cargo Cargo a qual o profissional vai ocupar.
	 * @throws CadastroException Caso a pessoa/projeto nao esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso os parametros sejam nulo/vazio ou invalido.
	 */
	
	public void associaProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(cargo, "Cargo invalido");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			
			Profissional participacao = new Profissional(cargo.toLowerCase(), valorHora, qtdHoras);
			
			this.pessoaController.associaPessoa(participacao, cpf);
			this.projetoController.associaProjeto(participacao, codigoProjeto);
			

			this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
			this.pessoaController.adicionaParticipacao(participacao, cpf);
			
			this.participacoes.add(participacao);
		}
		
	}
	
	/**
	 * Faz a associacao entre uma pessoa cadastrada no sistema e um projeto cadastrado no sistema, criando uma participacao.
	 * Faz associacao de um Aluno Pos Graduando.
	 * {@link Vinculo}
	 * @param cpf Cpf da pessoa a ter a participacao criada (precisa ser cadastrada no sistema).
	 * @param codigoProjeto Codigo do projeto cadastrado no sistema.
	 * @param qtdHoras Quantidade de horas trabalhadas.
	 * @param valorHora Valor da hora de trabalho do aluno pos graduando.
	 * @param associacao Vinculo ao qual o aluno esta ocupando (mestrado ou doutorado).
	 * @throws CadastroException Caso a pessoa/projeto nao esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso os parametros sejam nulo/vazio ou invalido.
	 */
	
	public void associaPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(associacao, "Pos graduacao invalida.");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			
			AlunoPosGraduando participacao = new AlunoPosGraduando( valorHora, qtdHoras, associacao);

			this.pessoaController.associaPessoa(participacao, cpf);
			this.projetoController.associaProjeto(participacao, codigoProjeto);
			

			this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
			this.pessoaController.adicionaParticipacao(participacao, cpf);
			
			this.participacoes.add(participacao);
		}
		
	
	}
	
	/**
	 * Pesquisa uma participacao cadastrada no sistema entre uma pessoa e um projeto.
	 * 
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto.
	 * @return True se a participacao existir. Falso caso contrario.
	 * @throws ValidacaoException Caso os parametros sejam nulo/vazio ou invalido.
	 */
	
	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws ValidacaoException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		for(Participacao participacao: this.participacoes) {
			if(participacao.getPessoa().getClass().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
				return true;
		
			} 
			
		}
		
		return false;
	}
	
	/**
	 * Remove uma participacao cadastrada no sistema (entre pessoa e projeto).
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto.
	 * @return True se bem sucedida com a remocao.
	 * @throws CadastroException Caso a pessoa ou o projeto nao seja encontrado
	 * @throws ValidacaoException Caso os parametros fornecidos sejam nulo/vazio.
	 */
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException, ValidacaoException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		this.pessoaController.pesquisaPessoa(cpf);
		this.projetoController.pesquisaProjeto(codigoProjeto);

		
		Iterator<Participacao> it = this.participacoes.iterator();
		while(it.hasNext()) {
			Participacao participacao = it.next();
			if(participacao.getPessoa().getCpf().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
				
				this.projetoController.setParametros(participacao);
				this.projetoController.removeParticipacao(participacao, codigoProjeto);
				this.pessoaController.removeParticipacao(participacao, cpf);

				it.remove();
				return true;
			}
		}
		

		throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
	}

	/**
	 * Retorna as participacoes existentes no sistema.
	 * @return As participacoes existentes no sistema.
	 */

	public String mostraParticipacoes() {
		String toString = "";
		for(Participacao participacao: participacoes) {
			toString += participacao.getPessoa().getCpf() + " " + participacao.getProjeto().getCodigo() + " " + participacao.getProjeto().getNome() + FIM_DE_LINHA;
		}
		
		return toString;
	}
	

}

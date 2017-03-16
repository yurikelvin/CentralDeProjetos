package centraldeprojetos;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import exception.CadastroException;
import exception.ValidacaoException;

import participacao.AlunoGraduando;
import participacao.AlunoPosGraduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;

import pessoas.PessoaController;
import projetos.ProjetoController;

import validacao.ValidaPessoa;
import validacao.Validacao;

/**
 * A classe realiza todas as operacoes possiveis com projeto, atraves da classe projetoController,
 * Salva todas as participacoes ja feitas nos projetos,
 * e pode cadastrar/editar/remover uma pessoa do sistema.
 * @author Tiberio Gadelha
 *
 */
public class CentralDeProjetos implements Serializable{

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ArrayList<Participacao> participacoes; 
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	public CentralDeProjetos() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacoes = new ArrayList<>();
	}
	
	/**
	 * Cadastra uma nova pessoa no sistema, atraves do cpf, nome e email.
	 * {@link PessoaController#cadastraPessoa(String, String, String)}
	 * @param cpf CPF da pessoa que sera cadastrada
	 * @param nome O nome da pessoa que sera cadastrada
	 * @param email O email da pessoa que sera cadastrada
	 * @return	Retorna o cpf como uma string.
	 * @throws ValidacaoException Caso Nome ou Cpf ou Email sejam invalidos.
	 * @throws CadastroException Caso a cpf a ser cadastrada ja esteja no sistema.
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException {
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}
	
	/**
	 * Pega uma determinada informacao acerca de uma pessoa cadastrada no sistema.
	 * {@link PessoaController#getInfoPessoa(String, String)}
	 * @param cpf CPF da pessoa que deseja buscar
	 * @param atributo A informacao desejada.
	 * @return Retorna o valor do atributo na forma de string
	 * @throws ValidacaoException
	 * @throws IllegalArgumentException Caso o item pesquisado nao seja "Nome" ou "Email".
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada.
	 */
	public String getInfoPessoa(String cpf, String atributo)
			throws ValidacaoException, IllegalArgumentException, CadastroException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}
	
	/**
	 * Modifica uma determinada infomacao acerca de uma pessoa cadastrada no sistema.
	 * {@link PessoaController#editaPessoa(String, String, String)}
	 * @param cpf CPF da pessoa que sera modificada
	 * @param atributo O atributo que sera modificado
	 * @param novoValor O novo valor do atributo
	 * @throws CadastroException Caso a pessoa ligada ao cpf nao esteja cadastrada.
	 * @throws ValidacaoException Caso o cpf nao seja valido ou novoValor nulo/vazio.
	 * @throws IllegalArgumentException Caso o atributo seja cpf ou atributo invalido.
	 */
	public void editaPessoa(String cpf, String atributo, String novoValor)
			throws CadastroException, ValidacaoException, IllegalArgumentException {
		pessoaController.editaPessoa(cpf, atributo, novoValor);
	}
	
	
	/**
	 * Remove determinada pessoa do sistema pelo seu cpf.
	 * {@link PessoaController#removePessoa(String)}
	 * @param cpf CPF da pessoa que sera removida
	 * @throws CadastroException
	 * @throws ValidacaoException
	 */
	public void removePessoa(String cpf) throws CadastroException, ValidacaoException {
		pessoaController.removePessoa(cpf);
	}

	public String mostraPessoas() {
		return pessoaController.toString();
	}

	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException {
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
				duracao);
	}

	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException, ParseException {
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws ValidacaoException, ParseException {
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws ValidacaoException, ParseException {
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
				dataInicio, duracao);
	}

	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException {
		return projetoController.getInfoProjeto(codigoProjeto, atributo);
	}

	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor)
			throws CadastroException, ValidacaoException, ParseException {
		projetoController.editaProjeto(codigoDoProjeto, atributo, novoValor);
	}

	public void removeProjeto(int codigoDoProjeto) throws CadastroException {
		projetoController.removeProjeto(codigoDoProjeto);
	}

	public int getCodigoProjeto(String nome) throws CadastroException {
		return projetoController.getCodigoProjeto(nome);
	}

	public String mostraProjetos() {
		return projetoController.toString();
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

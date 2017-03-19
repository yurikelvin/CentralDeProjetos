package pessoas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

//import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import participacao.AlunoGraduando;
import participacao.Participacao;
import projetos.Projeto;
import validacao.ValidaPessoa;
import validacao.Validacao;

/**
 * Classe desenvolvida para controlar as pessoas do sistema.
 * 
 * @author Yuri Silva
 * @author 
 * @author Tiberio Gadelha
 * @author 
 *
 */

public class PessoaController implements Serializable{

	private HashSet<Pessoa> pessoas;
	
	private static final String NOME = "nome";
	private static final String EMAIL = "email";
	private static final String CPF = "cpf";
	private static final String PARTICIPACOES = "participacoes";
	
	private static final String FIM_DE_LINHA = System.lineSeparator();


	
	public PessoaController() {
		pessoas = new HashSet<>();
	}
	
	/**
	 * Cadastra uma pessoa no sistema.
	 * @param cpf Cpf da pessoa a ser cadastrada.
	 * @param nome Nome da pessoa a ser cadastrada.
	 * @param email Email da pessoa a ser cadastrada.
	 * @return Retorna o CPF desta pessoa.
	 * @throws ValidacaoException Caso Nome ou Cpf ou Email sejam invalidos.
	 * @throws CadastroException Caso a cpf a ser cadastrada ja esteja no sistema.
	 */
	
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException{
		
		ValidaPessoa.validaNome(nome);
		ValidaPessoa.validaCPF(cpf);
		ValidaPessoa.validaEmail(email);

		
		if(!temPessoa(cpf)) {
			pessoas.add(new Pessoa(nome, cpf, email));
		} else {
			throw new CadastroException("Pessoa com mesmo CPF ja cadastrada");
		}
		
		
		return cpf;
	}
	
	/**
	 * Retorna uma informacao da pessoa, seja NOME ou EMAIL desta.
	 * @param cpf CPF da pessoa a requerer a informacao.
	 * @param atributo Item pesquisado. "Nome" ou "Email".
	 * @return Uma informacao da pessoa, NOME ou EMAIL.
	 * @throws ValidacaoException Caso o cpf passado nao seja valido.
	 * @throws IllegalArgumentException Caso o item pesquisado nao seja "Nome" ou "Email".
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada.
	 */
	
	public String getInfoPessoa(String cpf, String atributo) throws ValidacaoException, IllegalArgumentException, CadastroException {
		
		ValidaPessoa.validaCPF(cpf);
		
		Validacao.validaString(atributo, "atributo nulo ou vazio");

		Pessoa pessoaProcurada = this.getPessoa(cpf);
		
		switch(atributo.toLowerCase()) {
				
			case NOME:
				return pessoaProcurada.getNome();
			case EMAIL:
				return pessoaProcurada.getEmail();
			case PARTICIPACOES:
				return pessoaProcurada.mostraParticipacoes();
			default:
				throw new IllegalArgumentException("Atributo nao valido");
			}
			
		
		

	}
	
	/**
	 * Edita uma pessoa com base no CPF e campo especificado.
	 * @param cpf CPF da pessoa a editar.
	 * @param atributo Item a ser editado. Se "Nome" ou "Email".
	 * @param novoValor Novo valor a ser substituido com base no atributo.
	 * @throws CadastroException Caso a pessoa ligada ao cpf nao esteja cadastrada.
	 * @throws ValidacaoException Caso o cpf nao seja valido ou novoValor nulo/vazio.
	 * @throws IllegalArgumentException Caso o atributo seja cpf ou atributo invalido.
	 */

	public void editaPessoa(String cpf, String atributo, String novoValor) throws CadastroException, ValidacaoException, IllegalArgumentException{

		ValidaPessoa.validaCPF(cpf);
		Validacao.validaString(atributo, "atributo nulo ou vazio");
		
		Pessoa pessoaProcurada = this.getPessoa(cpf);
		
		switch(atributo.toLowerCase()) {
		
		case NOME:
			ValidaPessoa.validaNome(novoValor);
			pessoaProcurada.setNome(novoValor);
			break;
		case EMAIL:
			ValidaPessoa.validaEmail(novoValor);
			pessoaProcurada.setEmail(novoValor);
			break;
		case CPF:
			throw new IllegalArgumentException("CPF nao pode ser alterado");

		default:
			throw new IllegalArgumentException("Atributo nao valido");
			
		
		}
		
		
		
	}
	
	/**
	 * Remove uma pessoa com base no cpf dela cadastrado ao sistema.
	 * @param cpf CPF da pessoa a ser removida.
	 * @throws CadastroException Caso o cpf nao esteja ligado a nenhuma pessoa do sistema.
	 * @throws ValidacaoException Caso o cpf nao seja valido.
	 */
	
	public void removePessoa(String cpf) throws CadastroException, ValidacaoException {
		ValidaPessoa.validaCPF(cpf);
		Pessoa pessoaARemover = this.getPessoa(cpf);
		pessoas.remove(pessoaARemover);
	}
	
	
	/**
	 * Prodcura uma pessoa cadastrada no sistema atraves do seu cpf
	 * @param cpf CPF da pessoa que sera procurada
	 * @return Retorna o objeto da Pessoa procurada.
	 * @throws CadastroException Lanca uma excecao caso o cpf ainda nao tenha sido cadastrado no sistema.
	 */
	
	private Pessoa getPessoa(String cpf) throws CadastroException {
		
		ValidaPessoa.validaCPF(cpf);
		
		for(Pessoa pessoaProcurada: pessoas) {
			if(pessoaProcurada.getCpf().equals(cpf)) {
				return pessoaProcurada;
			}
		}
		
		throw new CadastroException("Pessoa nao encontrada");
	}
	
	/**
	 * Verifica se determinado cpf estah cadastrado no sistema
	 * @param cpf CPF que vai ser procurado
	 * @return Retorna true, se o cpf estiver cadastrado no sistema, e false, se nao estiver.
	 */
	private boolean temPessoa(String cpf) {
		for(Pessoa pessoaProcurada: pessoas) {
			if(pessoaProcurada.getCpf().equals(cpf)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Associa uma participacao a uma pessoa.
	 * Dada uma participacao, adiciona uma pessoa nesta participacao.
	 * @param participacao Participacao a ter a pessoa associada.
	 * @param cpf Cpf da pessoa a ser procurada.
	 * @return True se bem sucedido.
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada.
	 */
	
	public boolean associaPessoa(Participacao participacao, String cpf) throws CadastroException {
		Pessoa pessoa = this.getPessoa(cpf);
		
		participacao.setPessoa(pessoa);
		return true;
	}
	

	@Override
	public String toString() {
		String to = "";
		for(Pessoa pessoa: pessoas) {
			to += pessoa + FIM_DE_LINHA;
		}
		return to;
	}
	
	/**
	 * Dada uma participacao com Pessoa e Projeto ja incluso, adiciona em pessoa a participacao dada.
	 * 
	 * @param participacao Participacao a ser armazenada.
	 * @param cpf Cpf da pessoa.
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada.
	 */

	public void adicionaParticipacao(Participacao participacao, String cpf) throws CadastroException {
		Pessoa pessoa = this.getPessoa(cpf);
		
		pessoa.setParticipacao(participacao);
	}
	
	/**
	 * Pesquisa uma pessoa no sistema.
	 * @param cpf Cpf da pessoa.
	 * @return True se bem sucedido.
	 * @throws CadastroException Caso a pessoa nao seja encontrada.
	 */
	
	public boolean pesquisaPessoa(String cpf) throws CadastroException {
		for(Pessoa pessoa: this.pessoas) {
			if(pessoa.getCpf().equals(cpf)) {
				return true;
			}
		}
		
		throw new CadastroException("Pessoa nao encontrada");
	}
	
	/**
	 * Remove uma participacao que a pessoa tem no sistema.
	 * Como pessoa tem suas participacoes, ela pode quebrar o contrato e com isso tendo sua participacao removida do projeto.
	 * @param participacao Participacao a ser removida.
	 * @param cpf Cpf da pessoa ser removida.
	 * @throws CadastroException Caso a pessoa nao seja localizada.
	 */

	public void removeParticipacao(Participacao participacao, String cpf) throws CadastroException {
		Pessoa pessoa = this.getPessoa(cpf);
		pessoa.removeParticipacao(participacao);
		
	}
	
	/**
	 * Retorna a quantidade de pontos obtidos nas participacoes.
	 * {@link Pessoa#calculaPontuacaoPorParticipacao()}
	 * @param cpf Cpf da pessoa.
	 * @return A pontuacao por participacoes da pessoa.
	 * @throws CadastroException Caso a pessoa nao seja encontrada.
	 */
	
	public double calculaPontuacaoPorParticipacao(String cpf) throws CadastroException {
		Pessoa pessoa = this.getPessoa(cpf);
		
		return pessoa.calculaPontuacaoPorParticipacao();
	}
	
	/**
	 * Retorna o valor total da bolsa de uma pessoa, atraves do seu cpf.
	 * @param cpf O cpf da pessoa.
	 * @return Retorna um double que representa a bolsa da pessoa.
	 * @throws CadastroException Caso o cpf nao esteja cadastrado, uma excecao eh lancada.
	 */
	public double getValorBolsa(String cpf) throws CadastroException {
		Pessoa pessoa = this.getPessoa(cpf);
		return pessoa.getValorBolsa();
	}
}

package pessoas;

import java.io.Serializable;
import java.util.HashSet;

//import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import participacao.Participacao;
import validacao.ValidaPessoa;

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
		

		Pessoa pessoaProcurada = this.getPessoa(cpf);
		
		switch(atributo.toLowerCase()) {
				
			case NOME:
				return pessoaProcurada.getNome();
			case EMAIL:
				return pessoaProcurada.getEmail();
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
	
	public void adicionaParticipacao(String cpf, Participacao participacao) throws CadastroException {
		
	}
	
	public Pessoa getPessoa(String cpf) throws CadastroException {
		for(Pessoa pessoaProcurada: pessoas) {
			if(pessoaProcurada.getCpf().equals(cpf)) {
				return pessoaProcurada;
			}
		}
		
		throw new CadastroException("Pessoa nao encontrada");
	}
	
	private boolean temPessoa(String cpf) {
		for(Pessoa pessoaProcurada: pessoas) {
			if(pessoaProcurada.getCpf().equals(cpf)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void iniciaSistema() throws Exception {
		// posteriormente ser implementado
	}
	
	public void fechaSistema() throws Exception {
		// posteriormente ser implementado
	}
	
	
}

package centraldeprojetos;

import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import pessoas.Pessoa;
import pessoas.PessoaController;
import validacao.ValidaPessoa;

public class Facade {

	private PessoaController meuPessoa;
	//private ProjetoController meuProjeto;
	
	public Facade(){
		meuPessoa = new PessoaController();
		//meuProjeto = new ProjetoController();
	}
	
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException{
		
		try{
			return meuPessoa.cadastraPessoa(cpf, nome, email);
		}catch(CadastroException e){
			throw new CadastroException("Erro no cadastro de pessoa: " + e.getMessage());
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro no cadastro de pessoa: " + e.getMessage());
		}
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws ValidacaoException, IllegalArgumentException, CadastroException {
		
		try{
			return meuPessoa.getInfoPessoa(cpf, atributo);
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro na consulta de pessoa: " + e.getMessage());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException(e.getMessage());
		}catch(CadastroException e){
			throw new CadastroException("Erro na consulta de pessoa: " + e.getMessage());
		}
	}

	public void editaPessoa(String cpf, String atributo, String novoValor) throws CadastroException, ValidacaoException, IllegalArgumentException{
		
		try{
			meuPessoa.editaPessoa(cpf, atributo, novoValor);
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro na atualizacao de pessoa: " + e.getMessage());
		}catch(CadastroException e){
			throw new CadastroException(e.getMessage());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Erro na atualizacao de pessoa: " + e.getMessage());
		}
	}
	
	public void removePessoa(String cpf) throws CadastroException, ValidacaoException {
		
		try{
			meuPessoa.removePessoa(cpf);
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro na remocao de pessoa: " + e.getMessage());
		}catch(CadastroException e){
			throw new CadastroException("Erro na remocao de pessoa: " + e.getMessage());
		}
	}
	
	public void iniciaSistema() throws Exception {
		// posteriormente ser implementado
	}
	
	public void fechaSistema() throws Exception {
		// posteriormente ser implementado
	}
	
	public static void main(String[] args) {
		args = new String[] {"centraldeprojetos.Facade", "acceptance_tests/us1_test.txt", "acceptance_tests/us1_test_exception.txt"};
		EasyAccept.main(args);
}
}
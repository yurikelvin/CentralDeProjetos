package centraldeprojetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import participacao.ParticipacaoController;
import pessoas.Pessoa;
import pessoas.PessoaController;
import projetos.ProjetoController;
import validacao.ValidaPessoa;

public class Facade {

	private PessoaController pessoaController;
	private ParticipacaoController participacaoController;
	private ProjetoController projetosController;
	
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException{
		
		try{
			return pessoaController.cadastraPessoa(cpf, nome, email);
		}catch(CadastroException e){
			throw new CadastroException("Erro no cadastro de pessoa: " + e.getMessage());
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro no cadastro de pessoa: " + e.getMessage());
		}
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws ValidacaoException, IllegalArgumentException, CadastroException {
		
		try{
			return pessoaController.getInfoPessoa(cpf, atributo);
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
			pessoaController.editaPessoa(cpf, atributo, novoValor);
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
			pessoaController.removePessoa(cpf);
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro na remocao de pessoa: " + e.getMessage());
		}catch(CadastroException e){
			throw new CadastroException("Erro na remocao de pessoa: " + e.getMessage());
		}
	}
	
	public void iniciaSistema() throws Exception {
		
		this.pessoaController = (PessoaController) restauraPessoaController("pessoas.ser");
		this.projetosController = (ProjetoController) restauraProjetoController("projetos.ser");
		this.participacaoController = (ParticipacaoController) restauraParticipacaoController("participacoes.ser");
		
	}
	
	private PessoaController restauraPessoaController(String caminho) {
		PessoaController pessoaController = null;
		
		try{
			try(ObjectInputStream arqObjectos = new ObjectInputStream(new FileInputStream(caminho))){
				pessoaController = (PessoaController) arqObjectos.readObject();
			}
		}catch(ClassNotFoundException | IOException e){
			pessoaController = new PessoaController();
		}
		return pessoaController;

	}
	
	private ProjetoController restauraProjetoController(String caminho) {
		ProjetoController projetoController = null;
		try{
			try(ObjectInputStream arqObjectos = new ObjectInputStream(new FileInputStream(caminho))){
				projetoController = (ProjetoController) arqObjectos.readObject();
			}
		}catch(ClassNotFoundException | IOException e){
			projetoController = new ProjetoController();
		}
		return projetoController;

	}
	
	private ParticipacaoController restauraParticipacaoController(String caminho) {
		ParticipacaoController participacaoController = null;

		try{
			try(ObjectInputStream arqObjectos = new ObjectInputStream(new FileInputStream(caminho))){
				participacaoController = (ParticipacaoController) arqObjectos.readObject();
			}
		}catch(ClassNotFoundException | IOException e){
			participacaoController = new ParticipacaoController();
		}
		return participacaoController;

	}
	

	public void fechaSistema() throws IOException {
		
		try{
			try(ObjectOutputStream arqObjectos = new ObjectOutputStream(new FileOutputStream("pessoas.ser"))){
				arqObjectos.writeObject(this.pessoaController);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
		try{
			try(ObjectOutputStream arqObjectos = new ObjectOutputStream(new FileOutputStream("projetos.ser"))){
				arqObjectos.writeObject(this.projetosController);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
		try{
			try(ObjectOutputStream arqObjectos = new ObjectOutputStream(new FileOutputStream("participacoes.ser"))){
				arqObjectos.writeObject(this.participacaoController);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
		
		
		
		
		
	}
	

	
	public static void main(String[] args) {
		args = new String[] {"centraldeprojetos.Facade", "acceptance_tests/us1_test.txt", "acceptance_tests/us1_test_exception.txt"};
		//EasyAccept.main(args);
}
}
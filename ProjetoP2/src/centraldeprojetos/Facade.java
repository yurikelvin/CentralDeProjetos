package centraldeprojetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import easyaccept.EasyAccept;
//import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import participacao.ParticipacaoController;
import pessoas.PessoaController;
import projetos.ProjetoController;

public class Facade {

	private PessoaController pessoaController;
	private ParticipacaoController participacaoController;
	private ProjetoController projetosController;
	
	// metodos de PessoaController
	
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
	
	// metodos de ParticipacaoController
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			participacaoController.associaProfessor(cpf, codigoProjeto, coordenador, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
	}

	public void associaGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			participacaoController.associaGraduando(cpf, codigoProjeto, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
		
	}

	public void associaProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			participacaoController.associaProfissional(cpf, codigoProjeto, cargo, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
	
	}

	public void associaPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			participacaoController.associaPosGraduando(cpf, codigoProjeto, associacao, valorHora, qtdHoras);
			
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		

	}

	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		try {
			return participacaoController.pesquisaParticipacao(cpf, codigoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na pesquisa de participacao: " + e.getMessage());
		}
	}
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		try {
			return participacaoController.removeParticipacao(cpf, codigoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	// metodos de ProjetoController
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException {
		int codigo = 0;
		try {
			codigo = projetosController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,duracao);
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}catch(ParseException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}catch(CadastroException e) {
			throw new CadastroException("Erro no cadastro de projeto: " + e.getMessage());
		}
		
		return codigo;
			
	}

	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {

			int codigo = 0;
			try {
				codigo = projetosController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao);
			}catch(ValidacaoException e) {
				throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
			}catch(ParseException e) {
				throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
			}
			
			return codigo;
	}

	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws ValidacaoException {
			
		int codigo = 0;
		try {
			codigo = projetosController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}catch(ParseException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}
		
		return codigo;
	}

	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		int codigo = 0;
		try {
			codigo = projetosController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
					dataInicio, duracao);
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}catch(ParseException e) {
			throw new ValidacaoException("Erro no cadastro de projeto: " + e.getMessage());
		}
		
		return codigo;
	}

	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException {
		try {
			return projetosController.getInfoProjeto(codigoProjeto, atributo);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na consulta de projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na consulta de projeto: " + e.getMessage());
		}

		
	}

	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor)
			throws CadastroException, ValidacaoException {
		try {
			projetosController.editaProjeto(codigoDoProjeto, atributo, novoValor);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na atualizacao de projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na atualizacao de projeto: " + e.getMessage());
		}catch(ParseException e) {
			throw new ValidacaoException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	}

	public void removeProjeto(int codigoDoProjeto) throws CadastroException {
		try {
			projetosController.removeProjeto(codigoDoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	
	}

	public int getCodigoProjeto(String nome) throws CadastroException {
		try {
			return projetosController.getCodigoProjeto(nome);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na obtencao de codigo de projeto: " + e.getMessage());
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
			participacaoController = new ParticipacaoController(this.pessoaController, this.projetosController);
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
			try(ObjectOutputStream arqObjectos1 = new ObjectOutputStream(new FileOutputStream("projetos.ser"))){
				arqObjectos1.writeObject(this.projetosController);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
		try{
			try(ObjectOutputStream arqObjectos2 = new ObjectOutputStream(new FileOutputStream("participacoes.ser"))){
				arqObjectos2.writeObject(this.participacaoController);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
		
		
		
		
		
	}
	
	public void showPessoas() {
		System.out.println(pessoaController.toString());
	}
	
	public void showProjetos() {
		System.out.println(projetosController.toString());
	}

	
	public static void main(String[] args) {
		args = new String[] {"centraldeprojetos.Facade","acceptance_tests/us1_test.txt", "acceptance_tests/us1_test_exception.txt", "acceptance_tests/us2_test.txt", "acceptance_tests/us2_test_exception.txt", "acceptance_tests/copia3.txt"};
		EasyAccept.main(args);
	}
}
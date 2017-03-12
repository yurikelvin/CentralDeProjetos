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
import projetos.ProjetoController;

public class Facade {

	private CentralDeProjetos centralDeProjetos;
	
	// metodos de gerencia de Pessoa
	
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException{
		
		try{
			return centralDeProjetos.cadastraPessoa(cpf, nome, email);
		}catch(CadastroException e){
			throw new CadastroException("Erro no cadastro de pessoa: " + e.getMessage());
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro no cadastro de pessoa: " + e.getMessage());
		}
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws ValidacaoException, IllegalArgumentException, CadastroException {
		
		try{
			return centralDeProjetos.getInfoPessoa(cpf, atributo);
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
			centralDeProjetos.editaPessoa(cpf, atributo, novoValor);
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
			centralDeProjetos.removePessoa(cpf);
		}catch(ValidacaoException e){
			throw new ValidacaoException("Erro na remocao de pessoa: " + e.getMessage());
		}catch(CadastroException e){
			throw new CadastroException("Erro na remocao de pessoa: " + e.getMessage());
		}
	}
	
	// metodos de gerencia de Associacao entre Pessoa e Projeto
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			centralDeProjetos.associaProfessor(cpf, codigoProjeto, coordenador, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
	}

	public void associaGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			centralDeProjetos.associaGraduando(cpf, codigoProjeto, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
		
	}

	public void associaProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			centralDeProjetos.associaProfissional(cpf, codigoProjeto, cargo, valorHora, qtdHoras);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		
	
	}

	public void associaPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras)
			throws CadastroException, ValidacaoException {
		try {
			
			centralDeProjetos.associaPosGraduando(cpf, codigoProjeto, associacao, valorHora, qtdHoras);
			
		}catch(CadastroException e) {
			throw new CadastroException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: " + e.getMessage());
		}
		

	}

	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		try {
			return centralDeProjetos.pesquisaParticipacao(cpf, codigoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na pesquisa de participacao: " + e.getMessage());
		}
	}
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		try {
			return centralDeProjetos.removeParticipacao(cpf, codigoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	// metodos de gerencia de projetos
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException {
		int codigo = 0;
		try {
			codigo = centralDeProjetos.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,duracao);
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
				codigo = centralDeProjetos.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao);
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
			codigo = centralDeProjetos.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
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
			codigo = centralDeProjetos.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
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
			return centralDeProjetos.getInfoProjeto(codigoProjeto, atributo);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na consulta de projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na consulta de projeto: " + e.getMessage());
		}

		
	}

	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor)
			throws CadastroException, ValidacaoException {
		try {
			centralDeProjetos.editaProjeto(codigoDoProjeto, atributo, novoValor);
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
			centralDeProjetos.removeProjeto(codigoDoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	
	}

	public int getCodigoProjeto(String nome) throws CadastroException {
		try {
			return centralDeProjetos.getCodigoProjeto(nome);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na obtencao de codigo de projeto: " + e.getMessage());
		}
		
	}

	public void iniciaSistema() throws Exception {
		
		this.centralDeProjetos = (CentralDeProjetos) restauraCentralProjetos("cdpComputacao.ser");

		
	}
	
	private CentralDeProjetos restauraCentralProjetos(String caminho) {
		
		CentralDeProjetos centralDeProjetos = null;
		
		try{
			try(ObjectInputStream arqObjectos = new ObjectInputStream(new FileInputStream(caminho))){
				centralDeProjetos = (CentralDeProjetos) arqObjectos.readObject();
			}
		}catch(ClassNotFoundException | IOException e){
			centralDeProjetos = new CentralDeProjetos();
		}
		return centralDeProjetos;

	}
	
	
	public void fechaSistema() throws IOException {
		
		try{
			try(ObjectOutputStream arqObjectos = new ObjectOutputStream(new FileOutputStream("cdpComputacao.ser"))){
				arqObjectos.writeObject(this.centralDeProjetos);
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		
	}
	
	public static void main(String[] args) {
		args = new String[] {"centraldeprojetos.Facade","acceptance_tests/us1_test.txt", "acceptance_tests/us1_test_exception.txt", "acceptance_tests/us2_test.txt", "acceptance_tests/us2_test_exception.txt", "acceptance_tests/us3_test.txt", "acceptance_tests/us3_test_exception.txt"};
		EasyAccept.main(args);
	}
}
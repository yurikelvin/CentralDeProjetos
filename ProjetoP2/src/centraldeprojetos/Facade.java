package centraldeprojetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

//import easyaccept.EasyAccept;

import exception.CadastroException;
import exception.ValidacaoException;


public class Facade {

	private CentralDeProjetos centralDeProjetos;
	

	
	/**
	 * Cadastra uma pessoa com base no cpf, nome e email.
	 * {@link CentralDeProjetos#cadastraPessoa(String, String, String)}
	 * @param cpf Cpf da pessoa.
	 * @param nome Nome da pessoa.
	 * @param email Email da Pessoa.
	 * @return Retorna o cpf desta pessoa.
	 * @throws ValidacaoException Caso cpf, nome ou email seja nulo/invalido.
	 * @throws CadastroException Caso a pessoa ja esteja cadastrada no sistema.
	 */

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
	
	/**
	 * Retorna uma informacao especifica da pessoa, passando o cpf e o que se deseja saber (NOME, EMAIL, PARTICIPACOES).
	 * {@link CentralDeProjetos#getInfoPessoa(String, String)}
	 * @param cpf Cpf da Pessoa.
	 * @param atributo Informacao que procura saber.
	 * @return Retorna a informacao requesitada.
	 * @throws ValidacaoException Caso o cpf ou atributo seja nulo/vazio.
	 * @throws IllegalArgumentException Caso o atributo seja invalido.
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada no sistema.
	 */
	
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
	
	/**
	 * Atualiza as informacoes de uma pessoa com base em seu cpf, o valor que quer mudar, e o novo valor a ser considerado.
	 * {@link CentralDeProjetos#editaPessoa(String, String, String)}
	 * @param cpf Cpf da pessoa.
	 * @param atributo Informacao que quer mudar.
	 * @param novoValor Novo valor da informacao.
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada no sistema.
	 * @throws ValidacaoException Caso o cpf ou atributo ou novoValor seja nulo/invalido.
	 * @throws IllegalArgumentException Caso a operacao que se deseja atualizar seja invalido.
	 */

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
	
	/**
	 * Remove uma pessoa cadastrada no sistema com base em seu cpf.
	 * {@link CentralDeProjetos#removePessoa(String)}
	 * @param cpf Cpf da pessoa a ser removida.
	 * @throws CadastroException Caso a pessoa nao esteja cadastrada no sistema.
	 * @throws ValidacaoException Caso o cpf seja nulo/vazio.
	 */
	
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
	
	/**
	 * Associa um professor (que eh uma pessoa cadastrada no sistema)a um projeto existente no sistema.
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto existente.
	 * @param coordenador Se o professor eh coordenador.
	 * @param valorHora Valor da hora do professor.
	 * @param qtdHoras Quantidade de horas trabalhadas por semana.
	 * @throws CadastroException Caso o cpf nao esteja cadastrado no sistema ou o codigo do projeto nao leve a nenhum projeto existente. Ou professor ja esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso o cpf, codigoProjeto, valorHora e qtdHoras sejam nulo/vazios ou invalido.
	 */
	
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
	
	/**
	 * Associa um aluno de graduacao (que se encontra como uma pessoa cadastrada no sistema) a um projeto existente.
	 * @param cpf Cpf da pessoa a ser vinculada.
	 * @param codigoProjeto Codigo do projeto a ser vinculado.
	 * @param valorHora Valor da hora do aluno de graduacao.
	 * @param qtdHoras Quantidade de horas trabalhadas por semana.
	 * @throws CadastroException Caso o cpf ou codigo do projeto nao estejam cadastrados no sistema. Ou Aluno ja esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso o cpf ou codigo do projeto ou valorHora ou qtdHoras sejam nulo/vazios ou invalidos.
	 */

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
	
	/**
	 * Associa um profissional (que se encontra como uma pessoa cadastrada no sistema) a um projeto existente.
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto.
	 * @param cargo Cargo em que ele vai ocupar no projeto.
	 * @param valorHora Valor da hora.
	 * @param qtdHoras Quantidade de horas por semana.
	 * @throws CadastroException Caso o cpf ou codigoProjeto nao estejam cadastrados no sistema. Ou profissional ja esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso o cpf, ou codigoProjeto ou cargo ou valorHora ou qtdHoras sejam nulo/vazios/invalidos
	 */

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
	
	/**
	 * Associa um aluno pos-graduando (que se encontra como uma pessoa cadastrada no sistema) a um projeto existente.
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto.
	 * @param associacao Tipo de pos-graduacao stricto sensu.
	 * @param valorHora Valor da hora.
	 * @param qtdHoras Quantidade de horas por semana.
	 * @throws CadastroException Caso o cpf ou codigoProjeto nao estejam cadastrados no sistema. Ou aluno ja esteja cadastrado no projeto.
	 * @throws ValidacaoException Caso o cpf, ou codigoProjeto ou associacao ou valorHora ou qtdHoras sejam nulo/vazios/invalidos
	 */

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
	
	/**
	 * Pesquisa uma participacao (associacao) entre uma pessoa e um projeto ( ou vice - versa). 
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do Projeto.
	 * @return Retorna true se a pessoa participar do projeto em especifico.
	 * @throws ValidacaoException Caso o cpf ou codigoProjeto passados sejam nulo/vazios.
	 */

	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws CadastroException, ValidacaoException {
		try {
			return centralDeProjetos.pesquisaParticipacao(cpf, codigoProjeto);
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na pesquisa de participacao: " + e.getMessage());
		}
	}
	
	/**
	 * Remove a associacao que uma pessoa tem com o projeto e a que o projeto tem com pessoa.
	 * @param cpf Cpf da pessoa.
	 * @param codigoProjeto Codigo do projeto.
	 * @return Retorna true caso consiga fazer a remocao da associacao.
	 * @throws CadastroException Caso o cpf nao esteja cadastrado no projeto indicado.
	 */
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		try {
			return centralDeProjetos.removeParticipacao(cpf, codigoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na remocao de participacao: " + e.getMessage());
		}
	}

	// metodos de gerencia de projetos
	
	/**
	 * Adiciona um projeto de Monitoria ao sistema.
	 * Onde eh permitido somente o cadastro de 1 Professor (Coordenador ou nao) e um aluno de graduacao.
	 * 
	 * @param nome Nome do Projeto de monitoria.
	 * @param disciplina Disciplina a qual ele esta associada.
	 * @param rendimento Valor esperado de aprovacao da turma.
	 * @param objetivo Objetivo que o projeto tem de conseguir com seu feito.
	 * @param periodo Periodo letivo em que se inicia.
	 * @param dataInicio Data de inicio do projeto.
	 * @param duracao Duracao em meses do projeto de monitoria.
	 * @return Retorna o codigo do projeto criado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazios ou invalidos.
	 * @throws ParseException Caso ocorra erro quando for fazer a transformacao da data passada.
	 */
	
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
	
	/**
	 * Adiciona projeto do PET ao sistema.
	 * Onde eh permitido somente o cadastro de 1 Professor (Coordenador) e alunos de graduacao.
	 * 
	 * @param nome Nome do Projeto.
	 * @param objetivo Objetivo que o projeto tem de conseguir com seu feito.
	 * @param rendimento Valor esperado de conseguir exito.
	 * @param prodTecnica Quantidade de producoes tecnicas que se deseja atingir com o projeto.
	 * @param prodAcademica Quantidade de producoes academicas que se deseja atingir com o projeto.
	 * @param patentes Quantidade de patentes que se deseja atingir com o projeto.
	 * @param dataInicio Data em que o projeto se inicia ou iniciou.
	 * @param duracao Duracao em meses do projeto de monitoria.
	 * @return Retorna o codigo do projeto criado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazios ou invalidos.
	 * @throws ParseException Caso ocorra erro quando for fazer a transformacao da data passada.
	 */

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
	
	/**
	 * Adiciona um projeto de Extensao ao sistema.
	 * @param nome Nome do projeto.
	 * @param objetivo Objetivo que se pretende com o projeto.
	 * @param impacto Area de impacto social que o projeto vai ter.
	 * @param dataInicio Data em que o projeto teve inicio ou vai iniciar.
	 * @param duracao Duracao em meses do projeto
	 * @return Retorna o codigo do projeto criado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazio ou invalido.
	 */

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
	
	/**
	 * Adiciona um projeto de P & D ao sistema.
	 * 
	 * {@link CategoriaPED}
	 * @param nome Nome do projeto.
	 * @param categoria Categoria em que se encontra.
	 * @param prodTecnica Quantidade de producoes tecnicas que se deseja atingir com o projeto.
	 * @param prodAcademica Quantidade de producoes academicas que se deseja atingir com o projeto.
	 * @param patentes Quantidade de patentes que se deseja atingir com o projeto.
	 * @param objetivo Objetivo do projeto.
	 * @param dataInicio Data em que o projeto entra/entrou em vigor.
	 * @param duracao Duracao em meses do projeto.
	 * @return Retorna o codigo do projeto criado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazios ou invalidos.
	 */

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
	
	/**
	 * Recebe uma informacao a cerca do projeto, passando como parametro o codigo do projeto e o atributo desejado.
	 * {@link CentralDeProjetos#getInfoProjeto(int, String)}
	 * @param codigoProjeto Codigo do projeto.
	 * @param atributo Informacao que se deseja obter.
	 * @return Retorna a informacao a cerca do projeto.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado no sistema.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazio.
	 */

	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException {
		try {
			return centralDeProjetos.getInfoProjeto(codigoProjeto, atributo);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na consulta de projeto: " + e.getMessage());
		}catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na consulta de projeto: " + e.getMessage());
		}

		
	}
	
	/**
	 * Atualiza as informacoes do projeto.
	 * 
	 * @param codigoDoProjeto Codigo do projeto.
	 * @param atributo Valor que se deseja atualizar.
	 * @param novoValor Novo valor a ser adicionado.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazio ou invalido.
	 */

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
	
	/**
	 * Remove um projeto cadastrado do sistema passando seu codigo.
	 * 
	 * @param codigoDoProjeto Codigo do projeto.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado no sistema.
	 */

	public void removeProjeto(int codigoDoProjeto) throws CadastroException {
		try {
			centralDeProjetos.removeProjeto(codigoDoProjeto);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na atualizacao de projeto: " + e.getMessage());
		}
	
	}
	
	/**
	 * Pesquisa o codigo de um projeto cadastrado no sistema passando o nome do projeto.
	 * @param nome Nome do projeto.
	 * @return Retorna o codigo do projeto.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 */

	public int getCodigoProjeto(String nome) throws CadastroException {
		try {
			return centralDeProjetos.getCodigoProjeto(nome);
		}catch(CadastroException e) {
			throw new CadastroException("Erro na obtencao de codigo de projeto: " + e.getMessage());
		}
		
	}
	
	/**
	 * Inicializa os dados contidos no arquivo cpdComputacao.ser
	 * @throws Exception
	 */

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
	
	/**
	 * Mantem a persistencia dos dados do sistema na saida do mesmo.
	 * @throws IOException
	 */
	
	
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
		//EasyAccept.main(args); 


	}
}
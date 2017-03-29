package cdp.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.factorys.FactoryDeProjeto;
import cdp.participacao.Participacao;
import cdp.projetos.CategoriaPED;
import cdp.projetos.Extensao;
import cdp.projetos.Monitoria;
import cdp.projetos.PED;
import cdp.projetos.PET;
import cdp.projetos.Projeto;
import cdp.utils.Validacao;

/**
 * Classe responsavel por fazer a gerencia de projetos no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class ProjetoController implements Serializable{

	private Set<Projeto> projetosCadastrados;
	private FactoryDeProjeto factoryProjeto;
	private int codigosProjeto;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private static final String NOME = "nome";
	private static final String OBJETIVO = "objetivo";
	private static final String DISCIPLINA = "disciplina";
	private static final String RENDIMENTO = "rendimento";
	private static final String PERIODO = "periodo";
	private static final String DATAINICIO = "data de inicio";
	private static final String DURACAO = "duracao";
	private static final String IMPACTO = "impacto";
	private static final String PRODTECNICA = "producao tecnica";
	private static final String PRODACADEMICA = "producao academica";
	private static final String PATENTES = "patentes";
	private static final String CATEGORIA = "categoria";
	private static final String PARTICIPACOES = "participacoes";
	
	
	public ProjetoController(){
		this.projetosCadastrados = new TreeSet<>();
		factoryProjeto = new FactoryDeProjeto();

		
		
	}
	
	/**
	 * O metodo vai primeiro validar os parametros para criar um projeto do tipo Monitoria,
	 * depois, atraves da factoryProjeto, criar uma monitoria.
	 * @param nome Nome da monitoria
	 * @param disciplina A disciplina da monitoria
	 * @param rendimento Taxa de rendimento esperada dos alunos
	 * @param objetivo Objetivo da monitoria
	 * @param periodo Periodo em que a monitoria se iniciou.
	 * @param dataInicio Data de inicio da monitoria
	 * @param duracao Tempo de duracao
	 * @return Retorna o codigo do projeto como int.
	 * @throws ValidacaoException Lanca uma excecao se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao se ocorrer um erro na validacao da data.
	 * @throws DataException Caso a data nao seja uma data valida no calendario.
	 */
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException, DataException{
		Validacao.validaString(nome, "Nome nulo ou vazio");
		Validacao.validaString(disciplina,"Disciplina nulo ou vazio");
		Validacao.validaInt(rendimento,"Rendimento invalido");
		Validacao.validaString(objetivo, "Objetivo nulo ou vazio");
		Validacao.validaString(periodo,"Periodo nulo ou vazio");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao Invalida");
		
		this.codigosProjeto++;
		projetosCadastrados.add(factoryProjeto.criaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao, codigosProjeto));
		
		
		return codigosProjeto;
	
		
		
	}
	
	/**
	 * O metodo vai validar os parametros para criar um projeto do tipo PET,
	 * depois, atraves da factoryProjeto, vai crirar um Pet.
	 * @param nome O nome do Pet
	 * @param objetivo Objetivo do Pet
	 * @param impacto O impacto que o projeto ira trazer a sociedade
	 * @param rendimento Taxa de rendimento esperada dos alunos
	 * @param prodTecnica O numero de producoes tecnicas produzidas
	 * @param prodAcademica O numero de producoes academicas produzidas
	 * @param patentes O numero de patentes do projeto
	 * @param dataInicio A data que o projeto iniciou
	 * @param duracao A duracao total do projeto
	 * @return Retorna um inteiro que representa o codigo do projeto.
	 * @throws ValidacaoException Lanca uma excecao se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao se ocorrer um erro na validacao da data.
	 * @throws DataException Caso a data nao seja uma data valida no calendario.
	 */
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException, ParseException, DataException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(impacto, "Impacto invalido");
		Validacao.validaInt(rendimento,"Rendimento invalido");
		Validacao.validaInt(prodTecnica, "Numero de producoes tecnicas invalido");
		Validacao.validaInt(prodAcademica, "Numero de producoes academicas invalido");
		Validacao.validaInt(patentes, "Numero de patentes invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetosCadastrados.add(factoryProjeto.criaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao, codigosProjeto));
		
		return codigosProjeto;
		
	}
	
	/**
	 * O metodo vai validar os parametros para a criacao de um projeto de tipo Extensao,
	 * depois, atraves da factoryProjeto, criar uma Extensao.
	 * @param nome O nome do projeto
	 * @param objetivo O objetivo do projeto
	 * @param impacto O impacto social que o projeto ira trazer.
	 * @param dataInicio A data que o projeto iniciou
	 * @param duracao A duracao total do projeto.
	 * @return Retorna um inteiro que representa seu codigo.
	 * @throws ValidacaoException Lanca uma excecao se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao se ocorrer um erro na validacao da data.
	 * @throws DataException Caso a data nao seja uma data valida no calendario.
	 */
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws ValidacaoException, ParseException, DataException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(impacto, "Impacto invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao invalida");
		Validacao.validaImpactoSocial(impacto, "Impacto invalido");
		
		this.codigosProjeto++;
		projetosCadastrados.add(factoryProjeto.criaExtensao(nome, objetivo, impacto, dataInicio, duracao, codigosProjeto));
		
		return codigosProjeto;
	}
	
	/**
	 * O metodo vai validar os parametros para criar um projeto do tipo PED,
	 * depois, atraves da factoryProjeto, ira criar um PED.
	 * @param nome Nome do projeto
	 * @param categoria A categoria do PED.
	 * @param prodTecnica O numero de producoes tecnicas do projeto.
	 * @param prodAcademica O numero de producoes academicas do projeto.
	 * @param patentes O numero de patentes do projeto.
	 * @param objetivo O objetivo do projeto
	 * @param dataInicio Data de inicio do projeto
	 * @param duracao A duracao total do projeto.
	 * @return Retorna um inteiro que representa eu codigo.
	 * @throws ValidacaoException Lanca uma excecao se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao se ocorrer um erro na validacao da data.
	 * @throws DataException Caso a data nao seja uma data valida no calendario.
	 */
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo, String dataInicio, int duracao) throws ValidacaoException, ParseException, DataException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(categoria, "Categoria invalida");
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(prodTecnica, "Numero de producoes tecnicas invalido");
		Validacao.validaInt(prodAcademica, "Numero de producoes academicas invalido");
		Validacao.validaInt(patentes, "Numero de patentes invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetosCadastrados.add(factoryProjeto.criaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigosProjeto));		
		
		return codigosProjeto;
		
		}
	
	/**
	 * O metodo busca determinada infomacao sobre um projeto, atraves do seu codigo e do atributo desejado.
	 * @param codigoProjeto O codigo do projeto
	 * @param atributo A informacao desejada.
	 * @return Retorna a informacao em forma de string.
	 * @throws ValidacaoException Lanca uma excecao se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao se ocorrer um erro na validacao da data.
	 */
	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException{
		Validacao.validaInt(codigoProjeto,"Codigo invalido");
		Validacao.validaString(atributo, "Atributo nulo ou invalido");
		
		Projeto projetoASerConsultado = getProjetos(codigoProjeto);

		
		switch (atributo.toLowerCase()) {
		case NOME:
			return projetoASerConsultado.getNome();
			
		case OBJETIVO:
			return projetoASerConsultado.getObjetivo();
			
		case DATAINICIO:
			return projetoASerConsultado.getDataInicio();
			
		case DISCIPLINA:
			if(ehMonitoria(projetoASerConsultado, atributo)){
				return ((Monitoria) projetoASerConsultado).getDisciplina();
			}
		case RENDIMENTO:
			if(ehMonitoria(projetoASerConsultado, atributo)){
				return ((Monitoria) projetoASerConsultado).getRepresentacaoRendimento();
			}
			
			else if(ehPET(projetoASerConsultado, atributo)){
				return ((PET) projetoASerConsultado).getRepresentacaoRendimento();
			}
			
		case PERIODO:
			if(ehMonitoria(projetoASerConsultado, atributo)){
				return ((Monitoria) projetoASerConsultado).getPeriodo();
			}
		case DURACAO:
			return projetoASerConsultado.getRepresentacaoDuracao();
			
		case IMPACTO:
			
			if(ehPET(projetoASerConsultado, atributo)){
				return ((PET) projetoASerConsultado).getImpactoSocial().getImpactoSocial();
			}
			
			else if(ehExtensao(projetoASerConsultado, atributo)){
				return ((Extensao) projetoASerConsultado).getImpacto().getImpactoSocial();
			}
			
		case PRODTECNICA:
			if(ehPET(projetoASerConsultado, atributo)){
				return ((PET) projetoASerConsultado).getProdutividade(PRODTECNICA);
			}
			
			else if(ehPED(projetoASerConsultado, atributo)){
				return ((PED) projetoASerConsultado).getProdutividade(PRODTECNICA);
				
			}
			
		case PRODACADEMICA:
			if(ehPET(projetoASerConsultado, atributo)){
				return ((PET) projetoASerConsultado).getProdutividade(PRODACADEMICA);
			}
			
			else if(ehPED(projetoASerConsultado, atributo)){
				return ((PED) projetoASerConsultado).getProdutividade(PRODACADEMICA);
				
			}
		case PATENTES:
			if(ehPET(projetoASerConsultado, atributo)){
				return ((PET) projetoASerConsultado).getProdutividade(PATENTES);
			}
			
			else if(ehPED(projetoASerConsultado, atributo)){
				return ((PED) projetoASerConsultado).getProdutividade(PATENTES);
				
			}
		case CATEGORIA:
			if(ehPED(projetoASerConsultado, atributo)) {
				return ((PED) projetoASerConsultado).getCategoria().getValor();
			}
		case PARTICIPACOES:
			return projetoASerConsultado.mostraPessoasAssociadas();
			
			
		default:
			
			throw new ValidacaoException("Atributo nulo ou invalido");
			
		}
		
		
	}
	
	/**
	 * Verifica se o projeto eh do tipo Monitoria.
	 * @param projeto O projeto que sera verificado
	 * @param atributo O atributo que sera verificado.
	 * @return Retorna true, se for do tipo monitoria, e false, se nao for.
	 * @throws ValidacaoException 
	 */
	
	private boolean ehMonitoria(Projeto projeto, String atributo) throws ValidacaoException{
		
		if(projeto instanceof Monitoria){
			return true;
		}
		
		else if(projeto instanceof PED){
		
			throw new ValidacaoException("PED nao possui " + atributo);
		}
		
		else if (projeto instanceof Extensao){
			
			throw new ValidacaoException("Extensao nao possui " + atributo);
		}
		
		else if (projeto instanceof PET){
			if(atributo.equalsIgnoreCase(RENDIMENTO)){
				return false;
			}
			throw new ValidacaoException("PET nao possui " + atributo);
		}
		
		return false;
	}
	
	/**
	 * Verifica se o projeto eh do tipo Extensao
		 * @param projeto O projeto que sera verificado
	 * @param atributo O atributo que sera verificado.
	 * @return Retorna true, se for do tipo Extensao, e false, se nao for.
	 * @throws ValidacaoException 
	 */
	
	private boolean ehExtensao(Projeto projeto,String atributo) throws ValidacaoException{
		
		if(projeto instanceof Extensao){
			return true;
		}
		
		else if(projeto instanceof PED){
		
			throw new ValidacaoException("PED nao possui " + atributo);
		}
		
		else if (projeto instanceof PET){
			if(atributo.equalsIgnoreCase(IMPACTO)){
				return false;
			}
			throw new ValidacaoException("PET nao possui " + atributo);
		}
		
		else if (projeto instanceof Monitoria){
			
			throw new ValidacaoException("Monitoria nao possui " + atributo);
		}
		
		return false;
	}
	
	/**
	 * Verifica se o projeto eh do tipo PED.
	* @param projeto O projeto que sera verificado
	 * @param atributo O atributo que sera verificado.
	 * @return Retorna true, se for do tipo PED, e false, se nao for.
	 * @throws ValidacaoException 
	 */
	private boolean ehPED(Projeto projeto,String atributo) throws ValidacaoException{
		
		if(projeto instanceof PED){
			return true;
		}
		
		else if(projeto instanceof PET){
			if(atributo.equals(PRODTECNICA)){
				return false;
			}
			else if(atributo.equalsIgnoreCase(PRODACADEMICA)){
				return false;
			}
			else if(atributo.equalsIgnoreCase(PATENTES)){
				return false;
			}
			throw new ValidacaoException("PET nao possui " + atributo);
		}
		
		else if (projeto instanceof Extensao){
			if(atributo.equalsIgnoreCase(IMPACTO)){
				return false;
			}
			throw new ValidacaoException("Extensao nao possui " + atributo);
		}
		
		else if (projeto instanceof Monitoria){
			if(atributo.equalsIgnoreCase(RENDIMENTO)){
				return false;
			}
			throw new ValidacaoException("Monitoria nao possui " + atributo);
		}
		
		return false;
	}
	
	/**
	 * Verifica se o projeto eh do tipo PET.
	 * @param projeto O projeto que sera verificado
	 * @param atributo O atributo que sera verificado.
	 * @return Retorna true, se for do tipo PET, e false, se nao for.
	 * @throws ValidacaoException 
	 */
	private boolean ehPET(Projeto projeto,String atributo) throws ValidacaoException{
		
		if(projeto instanceof PET){
			return true;
		}
		
		else if(projeto instanceof PED){
			if(atributo.equalsIgnoreCase(PRODTECNICA)){
				return false;
			}
			else if(atributo.equalsIgnoreCase(PRODACADEMICA)){
				return false;
				}
			else if(atributo.equalsIgnoreCase(PATENTES)){
				return false;
			}
			throw new ValidacaoException("PED nao possui " + atributo);
		}
		
		else if (projeto instanceof Extensao){
			if(atributo.equalsIgnoreCase(IMPACTO)){
				return false;
			}
			throw new ValidacaoException("Extensao nao possui " + atributo);
		}
		
		else if (projeto instanceof Monitoria){
			if(atributo.equalsIgnoreCase(RENDIMENTO)){
				return false;
			}
			throw new ValidacaoException("Monitoria nao possui " + atributo);
		}
		
		return false;
	}
	
	public Projeto getProjetos(int codigoProjeto) throws CadastroException {

		for(Projeto projetoAserEncontrado: projetosCadastrados){
			if(projetoAserEncontrado.getCodigo() == codigoProjeto){
				return projetoAserEncontrado;
			}
		}
		throw new CadastroException("Projeto nao encontrado");
	}
	
	/**
	 * O metodo ira modificar algum atributo de um projeto.
	 * @param codigoDoProjeto O codigo do projeto que sera modificado.
	 * @param atributo O atributo que sera modificado.
	 * @param novoValor O novo valor dado ao atributo.
	 * @throws ValidacaoException Lanca uma excecao, se ocorrer um erro na validacao das strings.
	 * @throws ParseException Lanca uma excecao, se ocorrer um erro na validacao da data 
	 * @throws DataException Caso a data nao seja uma data valida no calendario.
	 */
	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor) throws CadastroException, ValidacaoException, ParseException, DataException{
		Validacao.validaString(atributo, "Atributo nao pode ser vazio ou invalido");
		Validacao.validaInt(codigoDoProjeto, "Codigo invalido");
		Validacao.validaString(novoValor, atributo + " nulo ou vazio");
		
		
		Projeto projetoAserAlterado = getProjetos(codigoDoProjeto);
		switch (atributo.toLowerCase()) {
		case NOME:
			projetoAserAlterado.setNome(novoValor);
			break;
		case OBJETIVO:	
			projetoAserAlterado.setObjetivo(novoValor);
			break;
		case DISCIPLINA:
			if(ehMonitoria(projetoAserAlterado, DISCIPLINA)){
				((Monitoria) projetoAserAlterado).setDisciplina(novoValor);
			}
			break;
		case PERIODO:
			if(ehMonitoria(projetoAserAlterado, PERIODO)){
				((Monitoria) projetoAserAlterado).setPeriodo(novoValor);
			}
			break;
		case DATAINICIO:
			Validacao.validaData(novoValor, "Formato de data invalido");
			projetoAserAlterado.setDataInicio(novoValor);
			break;
		case CATEGORIA:
			if(ehPED(projetoAserAlterado, CATEGORIA)){
				((PED) projetoAserAlterado).setCategoria(novoValor);
			}
			break;
		case DURACAO:
			int novaDuracao = Integer.parseInt(novoValor);
			projetoAserAlterado.setDuracao(novaDuracao);
			break;
		case IMPACTO:
			int novoImpacto = Integer.parseInt(novoValor);
			if(ehPET(projetoAserAlterado, IMPACTO)){
				((PET) projetoAserAlterado).setImpacto(novoImpacto);
			}
			else if(ehExtensao(projetoAserAlterado, IMPACTO)){
				((Extensao) projetoAserAlterado).setImpacto(novoImpacto);
			}
			break;
			
		case PRODTECNICA:
			int novaProd = Integer.parseInt(novoValor);
			
			if(ehPET(projetoAserAlterado, PRODTECNICA)){
				((PET) projetoAserAlterado).adicionaProdutividade(PRODTECNICA, novaProd);
			}
			
			else if(ehPED(projetoAserAlterado, PRODTECNICA)){
				((PED) projetoAserAlterado).adicionaProdutividade(PRODTECNICA, novaProd);
			}
			break;

		case PRODACADEMICA:
			int novaProda = Integer.parseInt(novoValor);
			if(ehPET(projetoAserAlterado, PRODACADEMICA)){
				((PET) projetoAserAlterado).adicionaProdutividade(PRODACADEMICA, novaProda);
			}
			
			else if(ehPED(projetoAserAlterado, PRODACADEMICA)){
				((PED) projetoAserAlterado).adicionaProdutividade(PRODACADEMICA, novaProda);
			}
			break;
			
		case PATENTES:
			int novaPatente = Integer.parseInt(novoValor);
			if(ehPET(projetoAserAlterado, PATENTES)){
				((PET) projetoAserAlterado).adicionaProdutividade(PATENTES, novaPatente);
			}
			
			else if(ehPED(projetoAserAlterado, PATENTES)){
				((PED) projetoAserAlterado).adicionaProdutividade(PATENTES, novaPatente);
			}
			break;
		default:
			
			throw new ValidacaoException("Atributo invalido");
		}
		
		
	}
	
	/**
	 * Remove um projeto do  banco de projetos, atraves do seu codigo.
	 * @param codigoDoProjeto O codigo do projeto que sera removido
	 * @throws CadastroException Retorna uma excecao, se o projeto nao for cadastrado no sistema.
	 * @throws ValidacaoException Caso o codigo do projeto seja invalido.
	 */
	public void removeProjeto(int codigoDoProjeto) throws CadastroException, ValidacaoException{
		Validacao.validaInt(codigoDoProjeto, "Codigo invalido");
		
		
		
		Iterator<Projeto> it = projetosCadastrados.iterator();
		while(it.hasNext()) {
			Projeto projetoProcurado = it.next();
			if(projetoProcurado.getCodigo() == codigoDoProjeto) {
				it.remove();
			}
		}
	}
	
	/**
	 * O metodo vai retornar o codigo de um projeto, atraves do seu nome.
	 * @param nome O nome do projeto que sera procurado.
	 * @return Retorna o inteiro que representa o codigo
	 * @throws CadastroException Retorna uma excecao, se o projeto nao for cadastrado no sistema.
	 * @throws ValidacaoException Caso o nome seja nulo/vazio
	 */
	public int getCodigoProjeto(String nome) throws CadastroException, ValidacaoException {
		Validacao.validaString(nome, "Nome nulo ou vazio");
		
		Iterator<Projeto> it = this.projetosCadastrados.iterator();
		while(it.hasNext()) {
			Projeto projetoProcurado = it.next();
			if(projetoProcurado.getNome().equals(nome)) {
				return projetoProcurado.getCodigo();
			}
			
		}
		
		
		throw new CadastroException("Projeto nao encontrado");
	}
	
	/**
	 * Associa uma participacao a um projeto.
	 * Dada uma participacao, adiciona um projeto nesta participacao.
	 * @param participacao Participacao a ser adicionada o projeto.
	 * @param codigoProjeto Codigo do projeto.
	 * @return True se bem sucedido.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 */
	
	public boolean associaProjeto(Participacao participacao, int codigoProjeto)  throws CadastroException {
		
		Projeto projeto = this.getProjetos(codigoProjeto);
		
		participacao.setProjeto(projeto);
		
		return true;
		
	}
	
	/**
	 * Dada uma participacao com Pessoa e Projeto ja incluso, adiciona em projeto a participacao dada.
	 * 
	 * @param participacao Participacao a ser armazenada.
	 * @param codigoProjeto Codigo do projeto.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazio
	 */
	
	public void adicionaParticipacao(Participacao participacao, int codigoProjeto) throws CadastroException, ValidacaoException {
		Projeto projeto = this.getProjetos(codigoProjeto);
		
		projeto.adicionaParticipacao(participacao);
	}
	
	/**
	 * Remove uma participacao que o projeto tem no sistema.
	 * Como projeto tem suas participacoes, ele pode remover a pessoa do projeto explicitado, causando assim a quebra da participacao.
	 * @param participacao Participacao a ser removida.
	 * @param codigoProjeto Codigo do projeto a ser removido.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 * @throws ValidacaoException Caso os parametros passados sejam nulo/vazios
	 */
	
	
	public void removeParticipacao(Participacao participacao, int codigoProjeto) throws CadastroException, ValidacaoException {
		Projeto projeto = this.getProjetos(codigoProjeto);
		projeto.removeParticipacao(participacao);
	}
	
	/**
	 * Pesquisa um projeto no sistema.
	 * @param codigoProjeto Codigo do projeto a ser procurado.
	 * @return True se bem sucedido.
	 * @throws CadastroException Caso o projeto nao seja encontrado.
	 */
	
	public boolean pesquisaProjeto(int codigoProjeto) throws CadastroException {
		Iterator<Projeto> it = this.projetosCadastrados.iterator();
		while(it.hasNext()) {
			Projeto projetoProcurado = it.next();
			if(projetoProcurado.getCodigo() == codigoProjeto) {
				return true;
			}
			
		}
		
		throw new CadastroException("Projeto nao encontrado");
	}
	
	@Override
	public String toString() {
		String toString = "";
		for(Projeto projeto: this.projetosCadastrados) {
			toString += projeto + FIM_DE_LINHA;
		}
		return toString;
	}
	
	/**
	 * Verifica a logica por tras, para um dado professor verificar seu valorHora.
	 * @param valorHora Valor da hora do professor.
	 * @param codigoProjeto Codigo do projeto a ser procurado.
	 * @return True se bem sucedido. False caso contrario.
	 * @throws CadastroException Caso o projeto nao esteja cadastrado.
	 * @throws ValidacaoException Caso o valorHora do professor seja invalido para o projeto em questao.
	 */

	public boolean validaHoraProfessor(double valorHora, int codigoProjeto) throws CadastroException, ValidacaoException {
		Projeto projeto = this.getProjetos(codigoProjeto);
		
		if(projeto instanceof Monitoria) {
			
			if(valorHora > 0) {
				throw new ValidacaoException("Valor da hora de um professor da monitoria deve ser zero");
			}
			return true;
		}
		Validacao.validaDoubleSemZero(valorHora, "Valor da hora invalido");
		return false;
		
	}
	
	public String geraRelatorioCadProjetos() throws IOException {
		String relatorio = "Cadastro de Projetos: " + this.projetosCadastrados.size() + " projetos registrados" + FIM_DE_LINHA;
		
		int contProjetos = 1;
		for(Projeto projeto: this.projetosCadastrados) {
			relatorio += "==> Projeto " + contProjetos + ":" + FIM_DE_LINHA;
			relatorio += projeto.toString() + FIM_DE_LINHA; // depois resolver
			
		}
		int totalProjetosConcluidos = 0; // rever
		
		relatorio += "Total de projetos concluidos: " + totalProjetosConcluidos + FIM_DE_LINHA;
		// esperar testes da us7
		relatorio += "% Participacao da graduacao: ";
		
		this.salvaRelatorio(relatorio);
		
		return relatorio;
	}
	
	private void salvaRelatorio(String relatorio) throws IOException {
		PrintWriter arquivo = null;
		
		try {
			arquivo = new PrintWriter(new BufferedWriter(new FileWriter("arquivos_sistema/relatorios/cad_projetos.txt")));
			arquivo.println(relatorio);
		} finally {
			if(arquivo != null) {
				arquivo.close();
			}
			
		}
		
	}

	public void atualizaDespesasProjeto(int codigoProjeto, double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException, CadastroException {

		Validacao.validaRepresentacaoCodigoProjeto(codigoProjeto, "codigo nulo ou vazio");
		
		Validacao.validaDouble(montanteBolsas, "valor negativo");
		Validacao.validaDouble(montanteCusteio, "valor negativo");
		Validacao.validaDouble(montanteCapital, "valor negativo");
		
		Projeto projeto = this.getProjetos(codigoProjeto);
		
		projeto.atualizaDespesasProjeto(montanteBolsas, montanteCusteio, montanteCapital);
	}
	
	
	public double calculaColaboracaoUASC(int codigoProjeto) throws CadastroException, ValidacaoException {
		Projeto projeto = this.getProjetos(codigoProjeto);
		
		return  projeto.geraContribuicao();
	}
	

}

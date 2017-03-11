package projetos;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Iterator;
import java.util.TreeSet;

import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import validacao.Validacao;

public class ProjetoController implements Serializable{

	private TreeSet<Projeto> projetos;
	private FactoryDeProjeto factoryProjeto;
	private int codigosProjeto;
	
	
	private static final String NOME = "nome";
	private static final String OBJETIVO = "objetivo";
	private static final String DISCIPLINA = "disciplina";
	private static final String RENDIMENTO = "rendimento";
	private static final String PERIODO = "perido";
	private static final String DATAINICIO = "data de inicio";
	private static final String DURACAO = "duracao";
	private static final String IMPACTO = "impacto";
	private static final String PRODTECNICA = "producao tecnica";
	private static final String PRODACADEMICA = "producao academica";
	private static final String PATENTES = "patentes";
	private static final String CATEGORIA = "categoria";
	
	
	public ProjetoController(){
		this.projetos = new TreeSet<>();
		factoryProjeto = new FactoryDeProjeto();
		
		
	}
	
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException{
		Validacao.validaString(nome, "Nome nulo ou vazio");
		Validacao.validaString(disciplina,"Disciplina nulo ou vazio");
		Validacao.validaInt(rendimento,"Rendimento invalido");
		Validacao.validaString(objetivo, "Objetivo nulo ou vazio");
		Validacao.validaString(periodo,"Periodo nulo ou vazio");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao Invalida");
		
		this.codigosProjeto++;
		projetos.add(factoryProjeto.criaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao, codigosProjeto));
		
		
		return codigosProjeto;
	
		
		
	}
	
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException, ParseException{
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
		projetos.add(factoryProjeto.criaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao, codigosProjeto));
		
		return codigosProjeto;
		
	}
	
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws ValidacaoException, ParseException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(impacto, "Impacto invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetos.add(factoryProjeto.criaExtensao(nome, objetivo, impacto, dataInicio, duracao, codigosProjeto));
		
		return codigosProjeto;
	}
	
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo, String dataInicio, int duracao) throws ValidacaoException, ParseException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(categoria, "Categoria invalida");
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(prodTecnica, "Numero de producoes tecnicas invalido");
		Validacao.validaInt(prodAcademica, "Numero de producoes academicas invalido");
		Validacao.validaInt(patentes, "Numero de patentes invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaIntSemZero(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetos.add(factoryProjeto.criaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigosProjeto));		
		
		return codigosProjeto;
		
		}
	
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
				return ((PET) projetoASerConsultado).getImpactoSocial();
			}
			
			else if(ehExtensao(projetoASerConsultado, atributo)){
				return ((Extensao) projetoASerConsultado).getImpacto();
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
				return ((PED) projetoASerConsultado).getCategoria();
			}
			
			
		default:
			
			throw new ValidacaoException("Atributo nulo ou invalido");
			
		}
		
		
	}
	
	private boolean ehMonitoria(Projeto projeto,String atributo){
		
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
	
	private boolean ehExtensao(Projeto projeto,String atributo){
		
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
	
	private boolean ehPED(Projeto projeto,String atributo){
		
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
	
	private boolean ehPET(Projeto projeto,String atributo){
		
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
		for(Projeto projetoAserEncontrado: projetos){
			if(projetoAserEncontrado.getCodigo() == codigoProjeto){
				return projetoAserEncontrado;
			}
		}
		throw new CadastroException("Projeto nao encontrado");
	}
	
	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor) throws CadastroException, ValidacaoException, ParseException{
		Validacao.validaString(atributo, atributo + " nao pode ser vazio ou invalido");
		Validacao.validaInt(codigoDoProjeto, "Codigo invalido");
		Validacao.validaString(novoValor, atributo + " nao pode ser vazio ou nulo");
		
		
		Projeto projetoAserAlterado = getProjetos(codigoDoProjeto);
		switch (atributo.toLowerCase()) {
		case NOME:
			projetoAserAlterado.setNome(novoValor);
			break;
		case OBJETIVO:	
			projetoAserAlterado.SetObjetivo(novoValor);
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

	public void removeProjeto(int codigoDoProjeto) throws CadastroException{
		Validacao.validaInt(codigoDoProjeto, "Codigo invalido");
		
		
		
		Iterator<Projeto> it = projetos.iterator();
		while(it.hasNext()) {
			Projeto projetoProcurado = it.next();
			if(projetoProcurado.getCodigo() == codigoDoProjeto) {
				it.remove();
			}
		}
		

		
	}
	
	public int getCodigoProjeto(String nome) throws CadastroException {
		for(Projeto projeto: this.projetos) {
			if(projeto.getNome().equalsIgnoreCase(nome)) {
				return projeto.getCodigo();
			}
			
		}
		
		throw new CadastroException("Projeto nao encontrado");
	}
	

}

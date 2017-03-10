package projetos;

import java.text.ParseException;
import java.util.TreeSet;

import exception.CadastroException;
import exception.ValidacaoException;
import validacao.Validacao;

public class ProjetoController {

	private TreeSet<Projeto> projetos;
	private FactoryDeProjeto factoryProjeto;
	private int codigosProjeto;
	
	
	public ProjetoController(){
		this.projetos = new TreeSet<>();
		factoryProjeto = new FactoryDeProjeto();
		
		
	}
	
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao) throws ValidacaoException, ParseException{
		Validacao.validaString(nome, "Nome nulo ou vazio");
		Validacao.validaString(disciplina,"Disciplina nulo ou vazio");
		Validacao.validaInt(rendimento,"Rendimento invalido");
		Validacao.validaString(objetivo, "Objetivo nulo ou vazio");
		Validacao.validaString(periodo,"Periodo nulo ou vazio");
		Validacao.validaData(dataInicio);
		Validacao.validaInt(duracao,"Duracao Invalida");
		
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
		Validacao.validaInt(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetos.add(factoryProjeto.criaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao, codigosProjeto));
		
		return codigosProjeto;
		
	}
	
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws ValidacaoException, ParseException{
		Validacao.validaString(nome,"Nome nulo ou vazio" );
		Validacao.validaString(objetivo,"Objetivo nulo ou vazio");
		Validacao.validaInt(impacto, "Impacto invalido");
		Validacao.validaData(dataInicio);
		Validacao.validaInt(duracao,"Duracao invalida");
		
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
		Validacao.validaInt(duracao,"Duracao invalida");
		
		this.codigosProjeto++;
		projetos.add(factoryProjeto.criaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigosProjeto));		
		
		return codigosProjeto;
		
		}
	
//	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException{
//		Projeto projetoASerConsultado = getProjetos(codigoProjeto);
//		
//		
//	}
	
	public Projeto getProjetos(int codigoProjeto) throws CadastroException {
		for(Projeto projetoAserEncontrado: projetos){
			if(projetoAserEncontrado.getCodigo() == codigoProjeto){
				return projetoAserEncontrado;
			}
		}
		throw new CadastroException("Projeto nao encontrado");
	}
}

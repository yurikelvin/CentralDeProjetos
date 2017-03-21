package cdp.factorys;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.projetos.Extensao;
import cdp.projetos.Monitoria;
import cdp.projetos.PED;
import cdp.projetos.PET;

/**
 * Classe responsavel pelo encapsulamento de criacao de um projeto.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class FactoryDeProjeto implements Serializable {
	
	private static final String PROD_ACADEMICA = "producao academica";
	private static final String PROD_TECNICA = "producao tecnica";
	private static final String PATENTES = "patentes";

	public Monitoria criaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao,int codigoProjeto) throws DataException {
		
		return new Monitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao, codigoProjeto);
	}

	public PET criaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, int codigoProjeto) throws DataException{
		
		if(impacto < 1 || impacto > 6 ){
			throw new ValidacaoException("Impacto invalido");
		}
		
		PET pet = new PET(nome, objetivo, impacto , rendimento, dataInicio, duracao , codigoProjeto);
		pet.adicionaProdutividade(PROD_ACADEMICA, prodAcademica);
		pet.adicionaProdutividade(PROD_TECNICA, prodTecnica);
		pet.adicionaProdutividade(PATENTES, patentes);
		
		return pet;
			
	}

	public Extensao criaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao,
			int codigoProjeto) throws DataException{
		
		return new Extensao(nome, objetivo, impacto, dataInicio, duracao, codigoProjeto);
	}

	public PED criaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao, int codigoProjeto) throws DataException{
		
		PED ped = new PED(nome, categoria, objetivo, dataInicio, duracao, codigoProjeto);
		ped.adicionaProdutividade(PROD_ACADEMICA, prodAcademica);
		ped.adicionaProdutividade(PROD_TECNICA, prodTecnica);
		ped.adicionaProdutividade(PATENTES, patentes);
		
		 
		return ped;
		
		
	}
	

}

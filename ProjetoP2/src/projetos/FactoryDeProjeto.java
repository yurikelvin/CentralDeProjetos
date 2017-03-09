package projetos;

import exception.ValidacaoException;

public class FactoryDeProjeto {

	public Monitoria criaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao,int codigoProjeto) {
		
		return new Monitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao, codigoProjeto);
	}

	public PET criaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademia,
			int patentes, String dataInicio, int duracao, int codigoProjeto) {
		
		if(impacto<1 ||impacto>6 ){
			throw new ValidacaoException("Impacto invalido");
		}
		
		return new PET(nome, objetivo, impacto , rendimento, prodTecnica, prodAcademia, patentes, dataInicio, duracao , codigoProjeto);
			
	}

	public Extensao criaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao,
			int codigoProjeto) {
		
		if(impacto<1 || impacto>6){
			throw new ValidacaoException("Impacto invalido");
		}
		
		return new Extensao(nome, objetivo, impacto, dataInicio, duracao, codigoProjeto);
	}

	public Projeto criaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao, int codigoProjeto) {
		 
		
		return new PED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao, codigoProjeto);
		
	}

}

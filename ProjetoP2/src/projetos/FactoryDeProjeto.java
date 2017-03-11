package projetos;

import exception.CadastroException;
import exception.ValidacaoException;

public class FactoryDeProjeto {

	public Monitoria criaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao,int codigoProjeto) throws ValidacaoException, CadastroException {
		
		return new Monitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao, codigoProjeto);
	}

	public PET criaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica,
			int patentes, String dataInicio, int duracao, int codigoProjeto) {
		
		if(impacto<1 ||impacto>6 ){
			throw new ValidacaoException("Impacto invalido");
		}
		
		PET pet = new PET(nome, objetivo, impacto , rendimento, dataInicio, duracao , codigoProjeto);
		pet.adicionaProdutividade("producao academica", prodAcademica);
		pet.adicionaProdutividade("producao tecnica", prodTecnica);
		pet.adicionaProdutividade("patentes", patentes);
		
		return pet;
			
	}

	public Extensao criaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao,
			int codigoProjeto) {
		
		if(impacto<1 || impacto>6){
			throw new ValidacaoException("Impacto invalido");
		}
		
		return new Extensao(nome, objetivo, impacto, dataInicio, duracao, codigoProjeto);
	}

	public PED criaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao, int codigoProjeto) {
		
		PED ped = new PED(nome, categoria, objetivo, dataInicio, duracao, codigoProjeto);
		ped.adicionaProdutividade("producao academica", prodAcademica);
		ped.adicionaProdutividade("producao tecnica", prodTecnica);
		ped.adicionaProdutividade("patentes", patentes);
		 
		return ped;
		
		
	}

}

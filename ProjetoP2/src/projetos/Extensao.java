package projetos;

import exception.ValidacaoException;

public class Extensao extends Projeto {
	
	private ImpactoSocial impactoSocial;

	public Extensao(String nomeProjeto, String objetivoDoProjeto, int impacto, int duracao, String dataInicio) {
		super(nomeProjeto, objetivoDoProjeto, dataInicio, duracao);
		this.setImpacto(impacto);
		
	}

	public String getImpacto() {
		return impactoSocial.getImpactoSocial();
	}

	private boolean setImpacto(int impacto) throws ValidacaoException {
		for (ImpactoSocial impac : ImpactoSocial.values()) {
			if(impac.getValorImpactoSocial() == impacto) {
				this.impactoSocial = impac;
				return true;
			}
		}
		
		throw new ValidacaoException("Impacto social invalido");
	}

}

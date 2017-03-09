package projetos;

import exception.CadastroException;
import exception.ValidacaoException;

public class Extensao extends Projeto {
	
	private ImpactoSocial impactoSocial;

	public Extensao(String nomeProjeto, String objetivoDoProjeto, int impacto, int duracao, String dataInicio, int codigo) {
		super(nomeProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setImpacto(impacto);
		
	}

	public String getImpacto() {
		return impactoSocial.getImpactoSocial();
	}
	
	public void setCusteio(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", valor);
	}
	
	public void setBolsa(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("bolsa", valor);
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

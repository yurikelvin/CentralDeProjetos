package participacao;

import exception.ValidacaoException;

public class AlunoPosGraduando extends Participacao {
	
	private Vinculo vinculo;

	public AlunoPosGraduando(double valorHora, int qtdHoras, String vinculo) {
		super(valorHora, qtdHoras);
		this.setVinculo(vinculo);
	}
	
	
	private void setVinculo(String vinculo) throws ValidacaoException {
		for(Vinculo valorVinculo: Vinculo.values()) {
			if(vinculo.equalsIgnoreCase(valorVinculo.getVinculo())) {
				this.vinculo = valorVinculo;
			}
		}
		
		throw new ValidacaoException("Vinculo nao cadastrado");
	}
	
	public String getVinculo() {
		return vinculo.getVinculo();
	}

}

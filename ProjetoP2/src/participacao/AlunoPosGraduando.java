package participacao;

import exception.ValidacaoException;

/**
 * Representa um aluno de pos-graduacao no sistema.
 * @author Yuri Silva
 * @author Tiberio Gadelha
 */
public class AlunoPosGraduando extends Participacao {
	
	private Vinculo vinculo;
	
	/**
	 * 
	 * @param valorHora
	 * @param qtdHoras
	 * @param vinculo
	 */

	public AlunoPosGraduando(double valorHora, int qtdHoras, String vinculo) {
		super(valorHora, qtdHoras);
		this.setVinculo(vinculo);
	}
	
	/**
	 * O metodo vai definir o tipo de vinculo do aluno de pos-graduacao.
	 * @param vinculo A string do vinculo que sera definido.
	 * @throws ValidacaoException Caso nao seja um vinculo valido, uma excecao sera lancada
	 */
	
	
	private boolean setVinculo(String vinculo) throws ValidacaoException {
		for(Vinculo valorVinculo: Vinculo.values()) {
			if(vinculo.equalsIgnoreCase(valorVinculo.getVinculo())) {
				this.vinculo = valorVinculo;
				return true;
			}
		}
		
		throw new ValidacaoException("Vinculo nao cadastrado");
	}
	
	/**
	 * O metodo vai retornar o tipo de vinculo do aluno.
	 * @return Retorna o vinculo do aluno, como string.
	 */
	
	public String getVinculo() {
		return vinculo.getVinculo();
	}

}

package cdp.participacao;

import cdp.exception.ValidacaoException;

/**
 * Representa um aluno de pos-graduacao no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class AlunoPosGraduando extends Participacao {

	private Vinculo vinculo;
	private final static double PONTOS_PARTICIPACAO = 0;

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
	 * 
	 * @param vinculo
	 *            A string do vinculo que sera definido.
	 * @throws ValidacaoException
	 *             Caso nao seja um vinculo valido, uma excecao sera lancada
	 */

	private boolean setVinculo(String vinculo) throws ValidacaoException {
		for (Vinculo valorVinculo : Vinculo.values()) {
			if (vinculo.equalsIgnoreCase(valorVinculo.getVinculo())) {
				this.vinculo = valorVinculo;
				return true;
			}
		}

		throw new ValidacaoException("Vinculo nao cadastrado");
	}

	/**
	 * O metodo vai retornar o tipo de vinculo do aluno.
	 * 
	 * @return Retorna o vinculo do aluno.
	 */

	public Vinculo getVinculo() {
		return vinculo;
	}

	@Override

	public double geraGanhos() {
		if (this.getVinculo() == Vinculo.DOUTORADO) {
			return super.geraGanhos() + (super.geraGanhos() / 3.0);
		}

		return super.geraGanhos();
	}

	@Override
	public double geraPontuacaoParticipacao() {
		
		return PONTOS_PARTICIPACAO;
	}

}

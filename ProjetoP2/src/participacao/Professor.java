package participacao;

/**
 * Representa um Professor no sistema, que pode ser coordenador..
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 */

public class Professor extends Participacao {

	boolean coordenador;

	public Professor(double valorHora, int qtdHoras, boolean coordenador) {
		super(valorHora, qtdHoras);
		this.coordenador = coordenador;
	}

	public boolean getCoordenador() {
		return coordenador;
	}

	@Override
	
	public double geraGanhos() {

		return calculaAdicional() + super.geraGanhos();

	}

	private double calculaAdicional() {

		double adicional = super.getValorHora();

		if (coordenador) {
			adicional += super.getValorHora() * 0.4;
			return adicional;
		}
		return super.getValorHora();
	}

}
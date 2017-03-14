package participacao;

/**
 * Representa um Professor no sistema, que pode ser coordenador..
 * @author Yuri Silva
 * @author Tiberio Gadelha
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

}

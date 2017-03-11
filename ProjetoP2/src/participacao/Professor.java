package participacao;

/**
 * 
 * @author Yuri Silva
 *
 */

public class Professor extends Participacao {
	
	boolean coordenador;
	
	/**
	 * 
	 * @param valorHora
	 * @param qtdHoras
	 * @param coordenador
	 */

	public Professor(double valorHora, int qtdHoras, boolean coordenador) {
		super(valorHora, qtdHoras);
		this.coordenador = coordenador;
	}
	
	
	public boolean getCoordenador() {
		return coordenador;
	}

}

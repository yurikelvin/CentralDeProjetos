package cdp.participacao;

import cdp.projetos.Monitoria;

/**
 * Representa um Professor no sistema, que pode ser coordenador..
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class Professor extends Participacao {
	

	private boolean coordenador;
	
	private final static int PONTOS_POR_PARTICIPACAO_ANUAL = 4;
	private final static double BONUS_PARTICIPACAO = 4;
	private final static double BONUS_BOLSA_ADICIONAL = 0.4;


	public Professor(double valorHora, int qtdHoras, boolean coordenador) {
		super(valorHora, qtdHoras);
		this.coordenador = coordenador;
	}

	public boolean getCoordenador() {
		return coordenador;
	}

	@Override
	
	public double geraGanhos() {
		
		if(coordenador) {
			return (calculaAdicional() * super.getQtdHoras());
		}
		return super.geraGanhos();

	}

	private double calculaAdicional() {

		double adicional = super.getValorHora();
		if (coordenador) {
			adicional += super.getValorHora() * BONUS_BOLSA_ADICIONAL;
			return adicional;
		}
		return super.getValorHora();
	}

	/**
	 * Gera a pontuacao de participacao de um professor.
	 */
	@Override
	public double geraPontuacaoParticipacao() {
		double pontuacao = 0.0;
		
		pontuacao += PONTOS_POR_PARTICIPACAO_ANUAL * (super.getProjeto().getDuracao()/12);
		
		if(!(super.getProjeto() instanceof Monitoria)) {
			pontuacao += super.getProjeto().getQtdAlunosNoProjeto();
			
		}
		
		return pontuacao;
		
	}

}
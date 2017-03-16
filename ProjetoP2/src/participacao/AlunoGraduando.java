package participacao;

import projetos.Monitoria;

/**
 * Representa um aluno graduando no sistema.
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 *
 */

public class AlunoGraduando extends Participacao {
	
	private static final double MAXIMO_PONTOS_ATINGIDOS = 0;
	private static final double BONUS_PARTICIPACAO_MONITORIA = 1.5;
	private static final double BONUS_PARTICIPACAO = 2.0;
	private static final double LIMITE_PONTOS = 8;
	
	private double totalPontos;

	public AlunoGraduando(double valorHora, int qtdHoras) {
		super(valorHora, qtdHoras);

	}

	@Override
	public double geraPontuacaoParticipacao() {
		
		int pontosFeitos;
		int duracao = super.getProjeto().getDuracao();
		
		if(super.getProjeto() instanceof Monitoria) {
			if(duracao >= 6) {
				pontosFeitos = duracao / 6;
				return pontosFeitos * BONUS_PARTICIPACAO_MONITORIA;
				
			}else {
				return 0;
			}
		}else {
			pontosFeitos = duracao / 6;
			totalPontos += pontosFeitos;
			if(totalPontos <= LIMITE_PONTOS) {
				return pontosFeitos * BONUS_PARTICIPACAO;
			}
			return MAXIMO_PONTOS_ATINGIDOS;
		}

	}

}

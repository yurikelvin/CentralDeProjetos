package cdp.participacao;

import cdp.projetos.Monitoria;

/**
 * Representa um aluno graduando no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class AlunoGraduando extends Participacao {
	
	private static final double MAXIMO_PONTOS_ATINGIDOS = 0;
	private static final double BONUS_PARTICIPACAO_MONITORIA_SEMESTRAL = 1.5;
	private static final double BONUS_PARTICIPACAO_SEMESTRAL = 2.0;
	private static final double LIMITE_PONTOS = 8;
	private static final double DURACAO_INSUFICIENTE = 0.0;
	
	public static double controlePontos;

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
				return pontosFeitos * BONUS_PARTICIPACAO_MONITORIA_SEMESTRAL;
				
			}else {
				return DURACAO_INSUFICIENTE;
			}
		}else {
			pontosFeitos = duracao / 6;
			pontosFeitos *= BONUS_PARTICIPACAO_SEMESTRAL;
			
			if(AlunoGraduando.controlePontos + pontosFeitos > LIMITE_PONTOS) {
				double diferenca = LIMITE_PONTOS - controlePontos;
				if(pontosFeitos <= diferenca) {
					AlunoGraduando.controlePontos += diferenca;

					return pontosFeitos;
				}
			
				return MAXIMO_PONTOS_ATINGIDOS;
				
			}
			
			AlunoGraduando.controlePontos += pontosFeitos;

			return pontosFeitos;
			
		}

	}
	

}

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
	private static final int MESES_MINIMO = 6;
	
	
	private static final double BONUS_PARTICIPACAO_MONITORIA_SEMESTRAL = 1.5;
	private static final double BONUS_PARTICIPACAO_PED_SEMESTRAL = 2.0;
	
	private static final double LIMITE_PONTOS_PED = 8;
	private static final double LIMITE_PONTOS_MONITORIA = 6;
	
	private static final double DURACAO_INSUFICIENTE = 0.0;
	
	public static double controlePontosPED;
	public static double controlePontosMonitoria;

	public AlunoGraduando(double valorHora, int qtdHoras) {
		super(valorHora, qtdHoras);

	}
	
	@Override
	public double geraPontuacaoParticipacao() {
		
		double pontosFeitos;
		int duracao = super.getProjeto().getDuracao();
		
		if(super.getProjeto() instanceof Monitoria) {
			if(duracao >= MESES_MINIMO) {
				pontosFeitos = (duracao / MESES_MINIMO) * BONUS_PARTICIPACAO_MONITORIA_SEMESTRAL;
				
				if(AlunoGraduando.controlePontosMonitoria + pontosFeitos > LIMITE_PONTOS_MONITORIA) {
					double diferenca = LIMITE_PONTOS_MONITORIA - controlePontosMonitoria;
					if(pontosFeitos <= diferenca) {
						AlunoGraduando.controlePontosMonitoria += diferenca;

						return pontosFeitos;
					}
				
					return MAXIMO_PONTOS_ATINGIDOS;
					
				}
				
				AlunoGraduando.controlePontosMonitoria += pontosFeitos;

				return pontosFeitos;
				
			}else {
				return DURACAO_INSUFICIENTE;
			}
		}else {
			if(duracao >= MESES_MINIMO) {
				pontosFeitos = ( duracao / MESES_MINIMO ) * BONUS_PARTICIPACAO_PED_SEMESTRAL;
				
				
				if(AlunoGraduando.controlePontosPED + pontosFeitos > LIMITE_PONTOS_PED) {
					double diferenca = LIMITE_PONTOS_PED - controlePontosPED;
					if(pontosFeitos <= diferenca) {
						AlunoGraduando.controlePontosPED += diferenca;

						return pontosFeitos;
					}
				
					return MAXIMO_PONTOS_ATINGIDOS;
					
				}
				
				AlunoGraduando.controlePontosPED += pontosFeitos;

				return pontosFeitos;
			} else {
				return DURACAO_INSUFICIENTE;
			}

		}
	}
	

}

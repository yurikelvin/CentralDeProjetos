package participacao;

import exception.ValidacaoException;
import pessoas.Pessoa;
import projetos.PED;

/**
 * Representa um Profissional no sistema.
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 *
 */
public class Profissional extends Participacao {
	
	private Cargo cargo;
	
	private final static double BONUS_BOLSA_PESQUISADOR = 100.0;
	
	private final static String GERENTE = "gerente";
	private final static String DESENVOLVEDOR = "desenvolvedor";
	private final static String PESQUISADOR = "pesquisador";
	
	private final static int PONTUACAO_DESENVOLVEDOR = 5;
	private final static int PONTUACAO_GERENTE = 9;
	private final static int PONTUACAO_PESQUISADOR = 6;
	private final static int QTN_MESES_NO_ANO = 12;
	
	private final static int LIMITE_BONUS_GERENTE = 5;
	private final static int LIMITE_ATINGIDO = 0;
	private final static int BOLSA_GERENTE = 20;
	
	public static int controleGerenteParticipantes;
	

	public Profissional(String cargo, double valorHora, int qtdHoras) {
		super(valorHora, qtdHoras);
		this.setCargo(cargo);
	
	}
	
	/**
	 * O metodo vai definir o cargo do profissional.
	 * @param cargo A string do cargo que sera definido.
	 * @throws ValidacaoException Caso o cargo nao esteja definido no enum, uma excecao sera retornada.
	 */

	private boolean setCargo(String cargo) throws ValidacaoException {
		for(Cargo valorCargo: Cargo.values()) {
			if(cargo.equalsIgnoreCase(valorCargo.getCargo())) {
				this.cargo = valorCargo;
				return true;
			}
		}
		throw new ValidacaoException("Cargo nao cadastrado");
	}
	
	
	public String getCargo() {
		return cargo.getCargo();
	}
	
	
	@Override
	public double geraGanhos(){
		
		if (getCargo().equals(PESQUISADOR)){
			return super.geraGanhos() + BONUS_BOLSA_PESQUISADOR;
		} else if(getCargo().equals(GERENTE)) {
			double bolsa = 0;
			int participantesProjeto = super.getProjeto().getQtdParticipantes() - 1;
			
			if(controleGerenteParticipantes + participantesProjeto > LIMITE_BONUS_GERENTE) {
				double diferenca = LIMITE_BONUS_GERENTE - controleGerenteParticipantes;
				if(participantesProjeto <= diferenca) {
					Profissional.controleGerenteParticipantes += diferenca;

					return (participantesProjeto * BOLSA_GERENTE) + super.geraGanhos();
				}
			
				return LIMITE_ATINGIDO;
			}
			
			Profissional.controleGerenteParticipantes += participantesProjeto;
			
			return (participantesProjeto * BOLSA_GERENTE) + super.geraGanhos();
			
		} else {
			return super.geraGanhos();
		}
		
		
		
	}

	@Override
	public double geraPontuacaoParticipacao() {
		double pontuacao = 0;
		
		if(this.getCargo().equals(DESENVOLVEDOR)){
			pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_DESENVOLVEDOR;
			
		}
		
		else if(this.getCargo().equals(GERENTE)){
			 pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_GERENTE;
		}
		
		else if(this.getCargo().equals(PESQUISADOR)){
			 pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_PESQUISADOR;
		}
		
		return pontuacao;
	}
	

}

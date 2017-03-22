package cdp.participacao;

import cdp.exception.ValidacaoException;
import cdp.pessoa.Pessoa;
import cdp.projetos.PED;

/**
 * Representa um Profissional no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class Profissional extends Participacao {
	
	private Cargo cargo;
	
	
	private final static int PONTUACAO_DESENVOLVEDOR = 5;
	private final static int PONTUACAO_GERENTE = 9;
	private final static int PONTUACAO_PESQUISADOR = 6;
	private final static int QTN_MESES_NO_ANO = 12;
	
	private final static int LIMITE_BONUS_GERENTE = 5;
	private final static int BOLSA_GERENTE = 20;
	private final static double BONUS_BOLSA_PESQUISADOR = 100.0;
	

	

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
	
	
	public Cargo getCargo() {
		return cargo;
	}
	

	
	
	@Override
	public double geraGanhos(){
		
		if (this.getCargo() == Cargo.PESQUISADOR){
			
			return super.geraGanhos() + BONUS_BOLSA_PESQUISADOR;
		} else if(this.getCargo() == Cargo.GERENTE) {
			int participantesProjeto = super.getProjeto().getQtdParticipantesSemGerente();
			
			int controlaBonus = participantesProjeto - LIMITE_BONUS_GERENTE;
			
			// Faz a regulagem de adicional por pessoa, nao excedendo o limite bonus de pessoas que o gerente pode ter.
			if(controlaBonus >= 0) {
				return (LIMITE_BONUS_GERENTE * BOLSA_GERENTE) + super.geraGanhos();
			} else if(controlaBonus < 0 && controlaBonus >= - 4) {

				return (participantesProjeto * BOLSA_GERENTE) + super.geraGanhos();
			} else {
				return super.geraGanhos();
			}
			
		} else {
			return super.geraGanhos();
		}
		
		
		
	}

	@Override
	public double geraPontuacaoParticipacao() {
		double pontuacao = 0;
		
		if(this.getCargo() == Cargo.DESENVOLVEDOR){
			pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_DESENVOLVEDOR;
			
		}
		
		else if(this.getCargo() == Cargo.GERENTE){
			 pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_GERENTE;
		}
		
		else if(this.getCargo() == Cargo.PESQUISADOR){
			 pontuacao = (super.getProjeto().getDuracao()/QTN_MESES_NO_ANO) * PONTUACAO_PESQUISADOR;
		}
		
		return pontuacao;
	}
	

}

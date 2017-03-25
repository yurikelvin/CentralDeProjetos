package cdp.projetos;


import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;

/**
 * Representa um projeto do tipo Monitoria no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class Monitoria extends Projeto {
	
	private String disciplina;
	private int rendimento;
	private String periodo;
	
	
	private boolean temProfessor;


	public Monitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao, int codigo) throws DataException {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.setDisciplina(disciplina);
		this.setRendimento(rendimento);
		this.setPeriodo(periodo);
	}
	
	
	public void setBolsa(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("bolsa", valor);
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	

	public String getPeriodo() {
		return periodo;
	}
	

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getRepresentacaoRendimento() {
		return Integer.toString(this.getRendimento());
	}
	
	public void setProfessor(boolean b) {
		this.temProfessor = b;
	}
	

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		this.cadastraMonitoria(participacaoASerAdicionada);
		
		super.participacoes.add(participacaoASerAdicionada);
		
	}
	
	private void cadastraMonitoria(Participacao participacaoASerAdicionada) throws CadastroException {
		if(super.ehProfessor(participacaoASerAdicionada)) {
			
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Professor ja esta cadastrado nesse projeto");
			}
			
			if(this.temProfessor) {
				throw new CadastroException("Monitoria nao pode ter mais de um professor");
			} else {
				if(participacaoASerAdicionada.getValorHora() > 0) {
					throw new CadastroException("Valor da hora de um professor da monitoria deve ser zero");
				}
				
				this.temProfessor = true;
			}
		} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
			}
	
		}else if(participacaoASerAdicionada instanceof AlunoPosGraduando) {
			throw new CadastroException("Tipo de projeto invalido para pos graduando");
		} else {
			throw new CadastroException("Tipo de projeto invalido para profissional");
		}
		
		if(super.participacoes.contains(participacaoASerAdicionada)) {
			throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
		}
	}
	
	@Override
	public void removeParticipacao(Participacao participacaoASerRemovida) throws CadastroException {
		
		if(participacaoASerRemovida instanceof Professor) {
			this.setProfessor(false);
		}
		
		boolean removeu = super.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
	}


}

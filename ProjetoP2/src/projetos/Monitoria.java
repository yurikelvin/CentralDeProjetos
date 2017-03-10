package projetos;

import java.util.HashSet;

import exception.CadastroException;
import exception.ValidacaoException;
import participacao.AlunoGraduando;
import participacao.Participacao;
import participacao.Professor;

public class Monitoria extends Projeto {
	
	private String disciplina;
	private int rendimento;
	private String periodo;
	
	
	private boolean temProfessor;


	public Monitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo, String dataInicio, int duracao, int codigo) throws ValidacaoException, CadastroException {
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
	

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		if(super.ehProfessor(participacaoASerAdicionada)) {
			if(this.temProfessor) {
				throw new CadastroException("Monitoria nao pode ter mais de um professor");
			} else {
				if(participacaoASerAdicionada.getValorHora() > 0) {
					throw new CadastroException("Valor da hora de um professor da monitoria deve ser zero");
				}
				super.participacoes.add(participacaoASerAdicionada);
				this.temProfessor = true;
			}
		} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
			}
			super.participacoes.add(participacaoASerAdicionada);
		}
		
	}

}

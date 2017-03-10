package projetos;

import associacao.Professor;

import java.util.HashSet;

import associacao.AlunoGraduando;
import exception.CadastroException;
import exception.ValidacaoException;

public class Monitoria extends Projeto {
	
	private String disciplina;
	private int rendimento;
	private String periodo;
	
	private Professor professorAssociado;
	private HashSet<AlunoGraduando> alunosAssociados;

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
	
	public void setProfessor(Professor professor) throws ValidacaoException {
		if(this.professorAssociado == null) {
			this.professorAssociado = professor;
		} else {
			throw new ValidacaoException("Monitoria nao pode ter mais de um professor");
		}
	}
	
	public void adicionaAluno(AlunoGraduando aluno) throws ValidacaoException {
		if(alunosAssociados.contains(aluno)) {
			throw new ValidacaoException("Aluno ja esta cadastrado nesse projeto");
		}
		
		alunosAssociados.add(aluno);
	}

}

package projetos;

import exception.CadastroException;
import exception.ValidacaoException;

public class Monitoria extends Projeto {
	
	private String disciplina;
	private int rendimento;
	private String periodo;

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

}

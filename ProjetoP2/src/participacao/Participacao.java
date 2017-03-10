package participacao;

import pessoas.Pessoa;
import projetos.Projeto;

public abstract class Participacao {

	private double valorHora;
	private int qtdHoras;
	private String dataInicio;
	private int duracao;
	
	private Pessoa pessoa;
	private Projeto projeto;
	
	public Participacao(double valorHora, int qtdHoras) {
		
		this.valorHora = valorHora;
		this.qtdHoras = qtdHoras;
		
		
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	
	

	public int getQtdHoras() {
		return qtdHoras;
	}

	public void setQtdHoras(int qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public String mostraPessoa() {
		return pessoa.toString();
	}
	
	public String mostraProjeto() {
		return projeto.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getPessoa().getCpf() == null) ? 0 : this.getPessoa().getCpf().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacao other = (Participacao) obj;
		if (this.getPessoa().getCpf() == null) {
			if (other.getPessoa().getCpf() != null)
				return false;
		} else if (!this.getPessoa().getCpf().equals(other.getPessoa().getCpf()))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}

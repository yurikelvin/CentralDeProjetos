package participacao;

import java.io.Serializable;

import pessoas.Pessoa;
import projetos.Projeto;

/**
 * 
 * @author Yuri Silva
 *
 */

public abstract class Participacao implements Serializable {

	private double valorHora;
	private int qtdHoras;
	private String dataInicio;
	private int duracao;
	
	private Pessoa pessoa;
	private Projeto projeto;
	
	/**
	 * 
	 * @param valorHora
	 * @param qtdHoras
	 */
	
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
	
	public int getCodigoProjeto() {
		return projeto.getCodigo();
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String mostraPessoa() {
		return pessoa.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String mostraProjeto() {
		return projeto.representacao();
	}
	
	public String getCpf() {
		return pessoa.getCpf();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getCpf() == null) ? 0 : this.getCpf().hashCode());
		result = prime * result + this.getCodigoProjeto();
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
		if (this.getCpf() == null) {
			if (other.getCpf() != null)
				return false;
		} else if (!this.getCpf().equals(other.getCpf()))
			return false;
		if (this.getCodigoProjeto() != other.getCodigoProjeto())
			return false;
		return true;
	}
	

	
	
	
	
	
	
	
	

}

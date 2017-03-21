package cdp.participacao;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import cdp.pessoa.Pessoa;
import cdp.projetos.Projeto;

/**
 * Representa uma participacao no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public abstract class Participacao implements Serializable, Remuneracao {

	private double valorHora;
	private int qtdHoras;
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

	public String getData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return formatter.format(this.getProjeto().getDate());
	}

	public int getDuracao() {
		return this.getProjeto().getDuracao();
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
	 * O metodo ira mostrar a pessoa que esta associada a participacao.
	 * 
	 * @return Retorna todos os dados da pessoa associada.
	 */

	public String mostraPessoa() {
		return pessoa.toString();
	}

	/**
	 * O metodo ira mostrar o projeto que esta associado a participacao
	 * 
	 * @return Retorna o nome do projeto associado.
	 */

	public String mostraProjeto() {
		return projeto.representacao();
	}

	public String getCpf() {
		return pessoa.getCpf();

	}

	public double geraGanhos() {
		double bolsa = this.getValorHora() * this.getQtdHoras();

		return bolsa;
	}
	
	public abstract double geraPontuacaoParticipacao();

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

package cdp.pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Profissional;

/**
 * 
 * Classe que representa uma Pessoa no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 *
 */

public class Pessoa implements Serializable{
	
	
	private static final double BOLSA_MINIMA = 350.0;
	private String nome;
	private String cpf;
	private String email;
	
	private List<Participacao> projetosParticipados;
	
	public Pessoa(String nome, String cpf, String email) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		
		this.projetosParticipados = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @param participacao
	 */
	
	public void setParticipacao(Participacao participacao) {
		
		this.projetosParticipados.add(participacao);
	}
	
	/**
	 * O metodo ira mostrar todas as participacoes da pessoa.
	 * @return Retorna atraves de string as participacoes.
	 */
	
	public String mostraParticipacoes() {
		String participacoes = "";
		int contador = 0;
		for(Participacao participacao: projetosParticipados) {
		
			if(contador >= 1) {
				participacoes += ", " + participacao.mostraProjeto();
			}else {
				participacoes += participacao.mostraProjeto();
			}
			
			contador ++;
		}
		
		return participacoes;
	}
	
	/**
	 * Remove determinada participacao dos projetosParticipados da pessoa.
	 * @param participacaoASerRemovida A participacao que sera removida
	 * @throws ValidacaoException Lanca excecao se a partipacao nao existir nos projetosParticipados;
	 */
	public void removeParticipacao(Participacao participacaoASerRemovida) throws ValidacaoException {
		boolean removeu = this.projetosParticipados.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
	}
	
	/**
	 * Retorna a pontuacao obtida nas participacoes.
	 * Cada pessoa sabe calcular seus pontos de participacao no projeto.
	 * @return A pontuacao obtida nas participacoes efetuadas.
	 */
	
	public double calculaPontuacaoPorParticipacao() {
		double pontuacao = 0;
		for(Participacao participacao: this.projetosParticipados) {
			pontuacao += participacao.geraPontuacaoParticipacao();
		
		}
		
		AlunoGraduando.controlePontos = 0;
		
		return pontuacao;
	}
	
	/**
	 * Calcula o total da bolsa de uma pessoa atraves das participacoes.
	 * @return Retorna um double que representa a bolsa.
	 */
	public double getValorBolsa() {
		double bolsa = 0;
		
		
		for(Participacao participacao: this.projetosParticipados) {
			bolsa += participacao.geraGanhos();
	
			}
		if (bolsa < BOLSA_MINIMA) {
			return BOLSA_MINIMA;
		}
		
		return bolsa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getCpf() == null) ? 0 : this.getCpf().hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (this.getCpf() == null) {
			if (other.getCpf() != null)
				return false;
		} else if (!this.getCpf().equals(other.getCpf()))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", email=" + email + "]";
	}
	

}

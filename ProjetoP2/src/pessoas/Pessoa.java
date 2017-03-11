package pessoas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import exception.ValidacaoException;
import participacao.Participacao;

/**
 * 
 * Classe que representa uma Pessoa no sistema.
 * 
 * @author Yuri Silva
 * @author
 * @author Tiberio Gadelha
 * @author
 *
 */

public class Pessoa implements Serializable{
	
	
	private String nome;
	private String cpf;
	private String email;
	
	private ArrayList<Participacao> projetosParticipados;
	
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
	 * 
	 * @return
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
	
	public void removeParticipacao(Participacao participacaoASerRemovida) throws ValidacaoException {
		for(Participacao participacao: this.projetosParticipados) {
			if(participacao.equals(participacaoASerRemovida)) {
				this.projetosParticipados.remove(participacao);
				break;
			}
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
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

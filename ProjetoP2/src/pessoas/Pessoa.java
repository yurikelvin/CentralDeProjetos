package pessoas;

import java.io.Serializable;
import java.util.HashSet;

import junit.framework.Assert;
import participacao.AlunoGraduando;
import participacao.Participacao;
import projetos.Projeto;

/**
 * 
 * Classe que representa uma Pessoa no sistema.
 * 
 * @author Yuri Silva
 * @author
 * @author
 * @author
 *
 */

public class Pessoa implements Serializable{
	
	
	private String nome;
	private String cpf;
	private String email;
	
	private HashSet<Participacao> projetosParticipados;
	
	public Pessoa(String nome, String cpf, String email) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		
		this.projetosParticipados = new HashSet<>();
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
	
	public void setParticipacao(Participacao participacao) {
		
		this.projetosParticipados.add(participacao);
	}
	
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

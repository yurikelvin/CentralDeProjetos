package pessoas;

public class Pessoa {
	
	private String nome;
	private String cpf;
	private String email;
	
	public Pessoa(String nome, String cpf, String email) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEmail(email);
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		if (getCpf() == null) {
			if (other.getCpf() != null)
				return false;
		} else if (!getCpf().equals(other.cpf))
			return false;
		return true;
	}

}

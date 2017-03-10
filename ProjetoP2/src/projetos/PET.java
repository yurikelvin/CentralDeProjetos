package projetos;

import java.util.HashSet;

import associacao.AlunoGraduando;
import associacao.Professor;
import exception.CadastroException;
import exception.ValidacaoException;

public class PET extends Projeto {
	
	private int rendimento;
	private ImpactoSocial impactoSocial;
	private HashSet<Produtividade> produtividades;
	
	private Professor tutor;
	private HashSet<AlunoGraduando> alunosPetianos;
	
	public PET(String nomeDoProjeto, String objetivoDoProjeto,int impactoSocial, int rendimento, int prodTecnica, int prodAcademica, int patentes,  String dataInicio, int duracao, int codigo) {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setImpacto(impactoSocial);
		this.rendimento = rendimento;
		produtividades = new HashSet<>();
		
	}
	
	private boolean setImpacto(int impacto) throws ValidacaoException {
		for (ImpactoSocial impac : ImpactoSocial.values()) {
			if(impac.getValorImpactoSocial() == impacto) {
				this.impactoSocial = impac;
				return true;
			}
		}
		
		throw new ValidacaoException("Impacto social invalido");
	}
	
	public String getProdutividades() {
		return produtividades.toString();
	}
	
	public void adicionaProdutividade(String produtividade, int quantidade) {
		Produtividade p = new Produtividade(produtividade, quantidade);
		produtividades.add(p);
	}
	
	public int getRendimento(){
		return rendimento;
	}
	
	public String getImpactoSocial() {
		return impactoSocial.getImpactoSocial();
	}
	
	public void setBolsa(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("bolsa", valor);
	}
	
	public void setCusteio(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", valor);
	}
	
	public void adicionaTutor(Professor professor) throws ValidacaoException{
		if(this.tutor == null) {
			this.tutor = professor;
		} else {
			throw new ValidacaoException("Projetos PET nao podem ter mais de um coordenador");
		}
	}
	
	public void adicionaPetianos(AlunoGraduando aluno) throws ValidacaoException {
		if(this.alunosPetianos.contains(aluno)) {
			throw new ValidacaoException("Aluno ja esta cadastrado nesse projeto");
		}
		
		this.alunosPetianos.add(aluno);
	}

}

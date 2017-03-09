package projetos;

import java.util.HashSet;

import exception.CadastroException;
import exception.ValidacaoException;

public class PET extends Projeto {
	
	private int rendimento;
	private ImpactoSocial impactoSocial;
	private HashSet<Produtividade> produtividades;
	
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
	
	public void adicionaProdutividade(Produtividade produtividade) {
		produtividades.add(produtividade);
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
	
	

}

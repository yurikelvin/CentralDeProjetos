package projetos;

import java.util.HashSet;
import java.util.Iterator;

import exception.CadastroException;
import exception.ValidacaoException;

public abstract class Projeto {

	private String nomeDoProjeto;
	private String objetivoDoProjeto;
	private Despesa despesas;
	private String dataInicio;
	private int duracao;
	private int codigo;
	private HashSet<Participacao> participacoes;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataInicio, int duracao, int codigo) {
		this.nomeDoProjeto = nomeDoProjeto;
		this.objetivoDoProjeto = objetivoDoProjeto;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.despesas = new Despesa();
		this.participacoes = new HashSet<>();
		this.codigo = codigo;
		
	}
		
	
	public String getNome(){
		return this.nomeDoProjeto;
	}
	
	public void setNome(String nomeNovo){
		this.nomeDoProjeto = nomeNovo;
	}
	
	public String getObjetivo(){
		return this.objetivoDoProjeto;
	}
	
	public void SetObjetivo(String novoObjetivo){
		this.objetivoDoProjeto = novoObjetivo;
	}
	
	public String getDataInicio(){
		return this.dataInicio;
	}
	
	public int getDuracao(){
		return this.duracao;
	}
	
	public void setDuracao(int novaDuracao){
		this.duracao = novaDuracao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public double getDespesasTotal(){
		return this.despesas.getDespesaTotal();
		
	}
	
	public void setDespesa(String descricao, double valor) throws ValidacaoException, CadastroException {
		despesas.setDespesa(descricao, valor);
	}
	
	public double getDespesa(String descricao) {
		return despesas.getDespesa(descricao);
	}
	
	public boolean adicionaParticipacao(Participacao participacaoASerAdicionada){
		return participacoes.add(participacaoASerAdicionada);
	}
	
	public boolean removeParticipacao(Participacao participacaoASerRemovida){
		return this.participacoes.remove(participacaoASerRemovida);
	}
	
	public boolean verificaParticipacao(Participacao participacaoASerVerificada){
		Iterator<Participacao> it = participacoes.iterator();
		
		while(it.hasNext()){
			Participacao participacaoProcurada = it.next();
			if(participacaoProcurada.equals(participacaoASerVerificada)){
				return true;
			}
		}
		return false;
	}
	
	

}
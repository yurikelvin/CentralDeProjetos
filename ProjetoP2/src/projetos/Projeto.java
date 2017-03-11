package projetos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;

import exception.CadastroException;
import exception.ValidacaoException;
import participacao.Participacao;
import participacao.Professor;

public abstract class Projeto implements Serializable, Comparable<Projeto> {

	private String nomeDoProjeto;
	private String objetivoDoProjeto;
	private Despesa despesas;
	private String dataInicio;
	private int duracao;
	private int codigo;
	
	protected HashSet<Participacao> participacoes;
	
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
	
	public void setDataInicio(String novaData){
		this.dataInicio = novaData;
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
	
	public abstract void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException;
	
	public void removeParticipacao(Participacao participacaoASerRemovida) throws ValidacaoException {
		for(Participacao participacao: this.participacoes) {
			if(participacao.equals(participacaoASerRemovida)) {
				participacoes.remove(participacao);
				break;
			}
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
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
	
	protected boolean ehProfessor(Participacao participante) {
		if(participante instanceof Professor) { return true; }
		return false;
	}
	
	
	public String representacao() {

		return this.getNome();
	}
	
	
	public String getRepresentacaoDuracao() {
		return Integer.toString(this.getDuracao());
	}
	

	public int compareTo(Projeto outroProjeto) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(this.getDataInicio() ,formatter);
		
		LocalDate otherDate = LocalDate.parse(outroProjeto.getDataInicio(), formatter);
		
		return date.compareTo(otherDate);
	
	}
	



}
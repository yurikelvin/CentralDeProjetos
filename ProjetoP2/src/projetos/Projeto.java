package projetos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import comparator.ParticipacaoPeloNomeComparator;
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
	
	protected ArrayList<Participacao> participacoes;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataInicio, int duracao, int codigo) {
		this.nomeDoProjeto = nomeDoProjeto;
		this.objetivoDoProjeto = objetivoDoProjeto;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.despesas = new Despesa();
		this.participacoes = new ArrayList<>();
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
		
		boolean removeu = this.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}

		
	}
	
	public boolean verificaParticipacao(Participacao participacaoASerVerificada){
		return this.participacoes.contains(participacaoASerVerificada);
	}
	
	public boolean verificaParticipacao(String cpf, int codigoProjeto) {
		for(Participacao participacao: this.participacoes) {
			if(participacao.getPessoa().getCpf().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
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
	
	public String mostraPessoasAssociadas() {
		String pessoas = "";
		int contador = 0;
		ParticipacaoPeloNomeComparator nomeParticipacaoComparator = new ParticipacaoPeloNomeComparator();
		Collections.sort(participacoes, nomeParticipacaoComparator);
		for(Participacao participacao: participacoes) {
		
			if(contador >= 1) {
				pessoas += ", " + participacao.getPessoa().getNome();
			}else {
				pessoas += participacao.getPessoa().getNome();
			}
			
			contador ++;
		}
		
		return pessoas;
	}
	

	public int compareTo(Projeto outroProjeto) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(this.getDataInicio() ,formatter);
		
		LocalDate otherDate = LocalDate.parse(outroProjeto.getDataInicio(), formatter);
		
		int compare = date.compareTo(otherDate);
		if(compare > 0) {
			return 1;
		} else {
			return -1;
		}
	
	}


	@Override
	public String toString() {
		return "Projeto [nomeDoProjeto=" + nomeDoProjeto + ", objetivoDoProjeto=" + objetivoDoProjeto + ", dataInicio="
				+ dataInicio + ", duracao=" + duracao + ", codigo=" + codigo + ", representacao()=" + representacao()
				+ "]";
	}
	
	
	



}
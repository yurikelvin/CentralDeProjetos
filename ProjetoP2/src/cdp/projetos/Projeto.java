package cdp.projetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cdp.comparator.ParticipacaoPeloNomeComparator;
import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Cargo;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;
import cdp.participacao.Profissional;

/**
 * Classe que representa um Projeto no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public abstract class Projeto implements Serializable, Comparable<Projeto> {

	private String nomeDoProjeto;
	private String objetivoDoProjeto;
	private int duracao;
	private int codigo;
	
	private Despesa despesas;
	private LocalDate dataInicio;

	protected List<Participacao> participacoes;
	
	public Projeto(String nomeDoProjeto, String objetivoDoProjeto, String dataInicio, int duracao, int codigo) throws DataException {
		this.nomeDoProjeto = nomeDoProjeto;
		this.objetivoDoProjeto = objetivoDoProjeto;
		this.setDataInicio(dataInicio);
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
	
	public void setObjetivo(String novoObjetivo){
		this.objetivoDoProjeto = novoObjetivo;
	}
	
	public String getDataInicio(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(this.dataInicio);
	}
	
	public LocalDate getDate() {
		return this.dataInicio;
	}
	
	public void setDataInicio(String novaData) throws DataException{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataInicio = LocalDate.parse(novaData ,formatter);
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
	
	/**
	 * O metodo vai calcular as despesas totais de um projeto.
	 * @return Retorna um double com o total das despesas.
	 */
	
	public double getDespesasTotal(){
		return this.despesas.getDespesaTotal();
		
	}
	
	/**
	 * O metodo vai adicionar um valor a um tipo de despesa. Apos ter adicionado esse valor em um tipo de despesa,
	 * nao ha mais como mudar.
	 * @param descricao O tipo de despesa
	 * @param valor O valor da despesa
	 * @throws ValidacaoException
	 * @throws CadastroException
	 */
	
	public void setDespesa(String descricao, double valor) throws ValidacaoException, CadastroException {
		despesas.setDespesa(descricao, valor);
	}
	
	public double getDespesa(String descricao) {
		return despesas.getDespesa(descricao);
	}
	
	/**
	 * O metodo vai adicionar uma participacao ao projeto, ou seja, adicionar um novo contribuidor ao projeto.
	 * @param participacaoASerAdicionada
	 * @throws CadastroException
	 */
	
	public abstract void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException;
	
	/**
	 * O metodo vai remover um participante do projeto.
	 * @param participacaoASerRemovida A participacao que sera removida.
	 * @throws ValidacaoException
	 */
	public void removeParticipacao(Participacao participacaoASerRemovida) throws ValidacaoException {
		
		boolean removeu = this.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}

		
	}
	
	/**
	 * Verifica se determinada participacao ja esta no projeto, recebendo a propria participacao.
	 * @param participacaoASerVerificada 
	 * @return Retorna true, se a participacao estiver, e false, se nao estiver no projeto.
	 */
	
	public boolean verificaParticipacao(Participacao participacaoASerVerificada){
		return this.participacoes.contains(participacaoASerVerificada);
	}
	
	/**
	 * Verifica se determinada participacao ja esta no projeto atraves do cpf do participante e codigo do projeto.
	 * @param cpf
	 * @param codigoProjeto
	 * @return Retorna true, se a participacao estiver, e false, se nao estiver no projeto.
	 */
	public boolean verificaParticipacao(String cpf, int codigoProjeto) {
		for(Participacao participacao: this.participacoes) {
			if(participacao.getPessoa().getCpf().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Verifica se o participante eh um professor.
	 * @param participante
	 * @return Retorna true, se for um professor, e false, se nao for.
	 */
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
	
	public int getQtdAlunosNoProjeto() {
		int qtd = 0;
		for(Participacao participacao: this.participacoes) {
			if(participacao instanceof AlunoGraduando || participacao instanceof AlunoPosGraduando) {
				qtd += 1;
			}
		}
		return qtd;
	}
	
	public int getQtdParticipantesSemGerente() {
		int qtd = 0;
		for(Participacao participacao: this.participacoes) {
			if(participacao instanceof Profissional) {
				
				if(((Profissional) participacao).getCargo() == Cargo.GERENTE){

					qtd --;
				}
			}
			
			qtd ++;
				
		}
		return qtd;
	}
	/**
	 * Retorna todas as pessoas associadas ao projeto, atraves de uma string.
	 * @return
	 */
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
		
		LocalDate date = this.getDate();
		LocalDate otherDate = outroProjeto.getDate();
		
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
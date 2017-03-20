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
import participacao.AlunoGraduando;
import participacao.AlunoPosGraduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;

/**
 * Classe que representa um Projeto no sistema.
 * @author
 * @author Tiberio Gadelha
 * @author 
 * @author 
 */

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
	 * O m�todo vai remover um participante do projeto.
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
				if(((Profissional) participacao).getCargo().equals("gerente")){
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
package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;
import cdp.pessoa.Pessoa;
import cdp.projetos.Monitoria;
import cdp.projetos.Projeto;

public class MonitoriaTest {
	
	public Projeto monitoria;
	
	@Before
	public void setUp() throws ValidacaoException, CadastroException, DataException{
		monitoria = new Monitoria("Calculo - monitoria", "calculo", 20, "Auxiliar os alunos a aprender calculo.",
				"2017.1", "01/03/2017", 12, 2);
	}
	
	@Test
	public void testGetNome() {
		assertEquals("Calculo - monitoria", monitoria.getNome());
	}
	
	@Test
	public void testGetDisciplina() {
		assertEquals("calculo",  ((Monitoria) monitoria).getDisciplina());
	}
	
	@Test
	public void testGetRendimento() {
		assertEquals(20, ((Monitoria) monitoria).getRendimento());
	}
	
	@Test
	public void testGetObjetivo() {
		assertEquals("Auxiliar os alunos a aprender calculo.", monitoria.getObjetivo());
	}
	
	@Test
	public void testGetPeriodo() {
		assertEquals("2017.1", ((Monitoria) monitoria).getPeriodo());
	}
	
	@Test
	public void testGetDataInicio() {
		assertEquals("01/03/2017", monitoria.getDataInicio());
	}
	
	@Test
	public void testGetDuracao() {
		assertEquals(12, monitoria.getDuracao());
	}
	
	@Test
	public void testGetDespesasTotal() {
		assertEquals(0, monitoria.getDespesasTotal(), 0.0001);
	}
	
	@Test
	public void testSetDespesaMonitoria() throws ValidacaoException, CadastroException {
		((Monitoria) monitoria).setBolsa(255.50);
		assertEquals(255.50, monitoria.getDespesasTotal(), 0.0001);
	}
	
	@Test
	public void testAdicionaParticipacao() throws CadastroException, ValidacaoException {
		Professor p1 = new Professor(0, 10, false);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(monitoria);
		assertFalse(monitoria.verificaParticipacao(p1));
		monitoria.adicionaParticipacao(p1);
		assertTrue(monitoria.verificaParticipacao(p1));
	}
	
	@Test
	public void testRemoveParticipacao() throws CadastroException, ValidacaoException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(monitoria);
		monitoria.adicionaParticipacao(p1);
		assertTrue(monitoria.verificaParticipacao(p1));
		monitoria.removeParticipacao(p1);
		assertFalse(monitoria.verificaParticipacao(p1));
	}

}

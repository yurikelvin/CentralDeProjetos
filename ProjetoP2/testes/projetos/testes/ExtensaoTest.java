package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.pessoa.Pessoa;
import cdp.projetos.Extensao;
import cdp.projetos.Projeto;

public class ExtensaoTest {
	
	public Projeto extensao;

	@Before
	public void setUp() throws DataException {
		extensao = new Extensao("A teoria", "Estudar a teoria da criacao do universo", 6, "25/02/2017", 6, 2);
	}
	
	@Test
	public void testGetNome() {
		assertEquals("A teoria", extensao.getNome());
	}
	
	@Test
	public void testGetObjetivo() {
		assertEquals("Estudar a teoria da criacao do universo", extensao.getObjetivo());
	}
	
	@Test
	public void testGetDataInicio() {
		assertEquals("25/02/2017", extensao.getDataInicio());
	}
	
	@Test
	public void getImpacto() {
		assertEquals("Federacao (Brasil)", ((Extensao) extensao).getImpacto().getImpactoSocial());
	}
	
	@Test
	public void testSetDespesas() throws ValidacaoException, CadastroException {
		assertEquals(0, extensao.getDespesasTotal(), 0.0001);
		((Extensao) extensao).setCusteio(2000);
		assertEquals(2000, extensao.getDespesasTotal(), 0.0001);
		((Extensao) extensao).setBolsa(2500);
		assertEquals(4500, extensao.getDespesasTotal(), 0.0001);
		
		assertEquals(2000, extensao.getDespesa("Custeio"), 0.0001);
		assertEquals(2500, extensao.getDespesa("Bolsa"), 0.0001);
	}

	@Test
	public void testAdicionaParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(extensao);
		assertFalse(extensao.verificaParticipacao(p1));
		extensao.adicionaParticipacao(p1);
		assertTrue(extensao.verificaParticipacao(p1));
	}
	
	@Test
	public void testRemoveParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(extensao);
		extensao.adicionaParticipacao(p1);
		assertTrue(extensao.verificaParticipacao(p1));
		extensao.removeParticipacao(p1);
		assertFalse(extensao.verificaParticipacao(p1));
	}

}
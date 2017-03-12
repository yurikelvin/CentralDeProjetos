package projetos.testes;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.CadastroException;
import exception.ValidacaoException;
import participacao.AlunoGraduando;
import pessoas.Pessoa;
import projetos.Monitoria;
import projetos.PED;
import projetos.Projeto;

public class PEDTest {
	
	public Projeto ped;

	@Before
	public void setUp() {
		ped = new PED("Compt", "PIBIC", "Estudar computadores e redes.", "20/02/2017", 12, 2);
	}
	
	@Test
	public void testGetNome() {
		assertEquals("Compt", ped.getNome());
	}

	@Test
	public void testGetCategoria() {
		assertEquals("pibic", ((PED) ped).getCategoria());
	}
	
	@Test
	public void testGetProdutividades() {
		((PED) ped).adicionaProdutividade("producao academica", 5);
		((PED) ped).adicionaProdutividade("producao tecnica", 3);
		((PED) ped).adicionaProdutividade("patentes", 1);
		assertEquals("5", ((PED) ped).getProdutividade("producao academica"));
		assertEquals("3", ((PED) ped).getProdutividade("producao tecnica"));
		assertEquals("1", ((PED) ped).getProdutividade("patentes"));
	}
	
	@Test
	public void testSetDespesas() throws ValidacaoException, CadastroException {
		ped.setDespesa("bolsa", 2500);
		assertEquals(2500, ped.getDespesasTotal(), 0.0001);
	}	
	
	@Test
	public void testAdicionaParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(ped);
		assertFalse(ped.verificaParticipacao(p1));
		ped.adicionaParticipacao(p1);
		assertTrue(ped.verificaParticipacao(p1));
	}
	
	@Test
	public void testRemoveParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(ped);
		ped.adicionaParticipacao(p1);
		assertTrue(ped.verificaParticipacao(p1));
		ped.removeParticipacao(p1);
		assertFalse(ped.verificaParticipacao(p1));
	}

}

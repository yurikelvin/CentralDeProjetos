package projetos.testes;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.pessoa.Pessoa;
import cdp.projetos.PED;
import cdp.projetos.PET;
import cdp.projetos.Projeto;

public class PETTest {
	
	public PET pet;
	
	@Before
	public void setUP() throws DataException {
		pet = new PET("Pet Eletrica", "Auxiliar os alunos", 1, 70, "24/01/2017", 12, 2);
	}
	

	@Test
	public void testGetNome() {
		assertEquals("Pet Eletrica", pet.getNome());
	}
	
	@Test
	public void testGetObjetivo() {
		assertEquals("Auxiliar os alunos", pet.getObjetivo());
	}
	
	@Test
	public void testGetImpacto() {
		assertEquals("Comunidade Academica", pet.getImpactoSocial());
	}
	
	@Test
	public void testGetProdutividade() {
		((PET) pet).adicionaProdutividade("producao academica", 5);
		((PET) pet).adicionaProdutividade("producao tecnica", 3);
		((PET) pet).adicionaProdutividade("patentes", 1);
		assertEquals("5", ((PET) pet).getProdutividade("producao academica"));
		assertEquals("3", ((PET) pet).getProdutividade("producao tecnica"));
		assertEquals("1", ((PET) pet).getProdutividade("patentes"));
	
	}
	
	@Test
	public void testSetDespesas() throws ValidacaoException, CadastroException {
		pet.setDespesa("bolsa", 2550);
		assertEquals(2550, pet.getDespesasTotal(), 0.0001);
		pet.setDespesa("custeio", 5000.50);
		assertEquals(7550.50, pet.getDespesasTotal(), 0.0001);
	}
	
	@Test
	public void testAdicionaParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(pet);
		assertFalse(pet.verificaParticipacao(p1));
		pet.adicionaParticipacao(p1);
		assertTrue(pet.verificaParticipacao(p1));
	}
	
	@Test
	public void testRemoveParticipacao() throws CadastroException {
		AlunoGraduando p1 = new AlunoGraduando(10, 20);
		Pessoa pessoa = new Pessoa("Joao C", "111.111.111-11", "joao@gmail.com");
		p1.setPessoa(pessoa);
		p1.setProjeto(pet);
		pet.adicionaParticipacao(p1);
		assertTrue(pet.verificaParticipacao(p1));
		pet.removeParticipacao(p1);
		assertFalse(pet.verificaParticipacao(p1));
	}

	
}

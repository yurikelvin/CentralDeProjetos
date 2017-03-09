package pessoas.testes;
import static org.junit.Assert.*;

import org.junit.Test;

import pessoas.Pessoa;

public class PessoaTest {
	
	private Pessoa people;
	
	@Test
	public void testPessoa() {
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
	}

	@Test
	public void testGetNome() {
		Pessoa p1 = new Pessoa("Joao", "123.123.123-12", "joao@gmail.com");
		assertEquals("Joao", p1.getNome());
		assertNotEquals("Matheus", p1.getNome());
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertNotEquals("Joao", people.getNome());
		assertEquals("Matheus", people.getNome());
	}

	@Test
	public void testSetNome() {
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertEquals("Matheus", people.getNome());
		people.setNome("Joao");
		assertEquals("Joao", people.getNome());
		
	}

	@Test
	public void testEqualsObject() {
		Pessoa p1 = new Pessoa("Joao", "123.123.123-12", "joao@gmail.com");
		Pessoa p2 = new Pessoa("Tiberio", "123.123.123-12", "tenorio@gmail.com");
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(people));
		assertFalse(p2.equals(people));
	}
}

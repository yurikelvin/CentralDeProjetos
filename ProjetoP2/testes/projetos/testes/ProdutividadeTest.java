package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projetos.Produtividade;

public class ProdutividadeTest {

	private Produtividade minhaProd;
	
	@Before
	public void setUp() throws Exception {
		minhaProd = new Produtividade("patentes", 20);
	}

	@Test
	public void testGetProdutividade() {
		assertEquals("patentes", minhaProd.getProdutividade());
		assertNotEquals("PATENTES", minhaProd.getProdutividade());
	}

	@Test
	public void testGetQuantidade() {
		assertEquals(20, minhaProd.getQuantidade());
		assertNotEquals(15, minhaProd.getQuantidade());
	}

}

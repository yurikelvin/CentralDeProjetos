package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
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
		assertEquals(5, ((PED) ped).getProdutividade("producao academica"));
		assertEquals(3, ((PED) ped).getProdutividade("producao tecnica"));
		assertEquals(1, ((PED) ped).getProdutividade("patentes"));
	}

}

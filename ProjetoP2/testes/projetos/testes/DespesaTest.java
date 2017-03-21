package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.ValidacaoException;
import cdp.projetos.Despesa;

public class DespesaTest {

	private Despesa minhaDespesa;

	@Before
	public void setUp() throws Exception {
		minhaDespesa = new Despesa();
	}

	@Test
	public void testSetDespesa() throws ValidacaoException, CadastroException {
		minhaDespesa.setDespesa("bolsa", 20.0);
		assertEquals(20.0, minhaDespesa.getDespesa("bolsa"), 0.0001);
		minhaDespesa.setDespesa("custeio", 50.3);
		assertEquals(50.3, minhaDespesa.getDespesa("custeio"), 0.0001);
		minhaDespesa.setDespesa("capital", 30.5);
		assertEquals(30.5, minhaDespesa.getDespesa("capital"), 0.0001);
		try {
			minhaDespesa.setDespesa(" ", 15.5);
		} catch (Exception e) {
			assertEquals("Despesa invalida", e.getMessage());
		}
	}

	@Test
	public void testGetDespesaTotal() throws ValidacaoException, CadastroException {
		minhaDespesa.setDespesa("bolsa", 20.0);
		minhaDespesa.setDespesa("custeio", 50.3);
		minhaDespesa.setDespesa("capital", 30.5);
		assertEquals(100.8, minhaDespesa.getDespesaTotal(), 0.0001);

	}
	
	@Test
	public void testGetDespesa() throws ValidacaoException, CadastroException{
		minhaDespesa.setDespesa("bolsa", 20.0);
		assertEquals(20.0, minhaDespesa.getDespesa("bolsa"), 0.0001);
		try {
			minhaDespesa.setDespesa(" ", 15.5);
		} catch (Exception e) {
			assertEquals("Despesa invalida", e.getMessage());
		}
	}

}

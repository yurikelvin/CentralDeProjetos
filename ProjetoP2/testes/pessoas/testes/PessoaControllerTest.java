package pessoas.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.CadastroException;
import exception.ValidacaoException;
import pessoas.PessoaController;

public class PessoaControllerTest {

	private PessoaController meuController;
	
	@Before
	public void setUp(){
		meuController = new PessoaController();
	}
	
	@Test
	public void testCadastraPessoa() throws ValidacaoException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
	}

	@Test
	public void testGetInfoPessoa() throws ValidacaoException, IllegalArgumentException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
	}

	@Test
	public void testEditaPessoa() throws ValidacaoException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
		meuController.editaPessoa("123.123.123-12", "nome", "Tiberio");
		assertEquals("Tiberio", meuController.getInfoPessoa("123.123.123-12", "nome"));
		meuController.editaPessoa("123.123.123-12", "email", "tenorio@gmail.com");
		assertEquals("tenorio@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
	}

	@Test
	public void testRemovePessoa() throws ValidacaoException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
		
	}

	@Test
	public void testIniciaSistema() {
		fail("Not yet implemented");
	}

	@Test
	public void testFechaSistema() {
		fail("Not yet implemented");
	}

}

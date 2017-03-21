package pessoas.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.controllers.PessoaController;
import cdp.exception.CadastroException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.projetos.PET;

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
	public void testCadastraPessoaWithException() {
		try {
			meuController.cadastraPessoa("108..777.421.232-25", "Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("        ", "Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa(null, "Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("", "Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("ddd.ddd.ddd-dd", "Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "		", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", null, "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "1Yuri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Nome invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Y3uri", "Yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Nome invalido", e.getMessage());
		}
		
			
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri..silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri.silva@ccc..ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri.silva@@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri.silva@ccc.@ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri.silva.ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.cadastraPessoa("108.777.421-25", "Yuri", "Yuri;.silva.ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		
		
		
	}

	@Test
	public void testGetInfoPessoa() throws ValidacaoException, IllegalArgumentException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
		
		assertEquals("", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		AlunoGraduando p = new AlunoGraduando(150.0, 12);
		p.setProjeto(new PET("Change Trees", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		meuController.adicionaParticipacao(p, "123.123.123-12");
		
		assertEquals("Change Trees", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		AlunoGraduando j = new AlunoGraduando(50, 13);
		
		j.setProjeto(new PET("Aventuras", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		meuController.adicionaParticipacao(j, "123.123.123-12");
		
		assertEquals("Change Trees, Aventuras", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
	}
	
	@Test
	public void testGetInfoPessoaWithException() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		try {
			meuController.getInfoPessoa("", "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("		", "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("108.742.343-524", "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa(null, "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("", "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("		", "email");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("123.123.123-12", "xerox");
			fail();
		}catch(Exception e) {
			assertEquals("Atributo nao valido", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("108.742.343-59", "		");
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("108.742.343-59", "");
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.getInfoPessoa("108.742.343-59", null);
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
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
	public void testEditaPessoaWithException() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		try {
			meuController.editaPessoa("", "email", "yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("		", "email", "yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa(null, "email", "yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "xerox", "yuri.silva@ccc.ufcg.edu.br");
			fail();
		}catch(Exception e) {
			assertEquals("Atributo nao valido", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "cpf", "103.234.524-14");
			fail();
		}catch(Exception e) {
			assertEquals("CPF nao pode ser alterado", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "nome", "y1ri");
			fail();
		}catch(Exception e) {
			assertEquals("Nome invalido", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "email", "yuri.silva");
			fail();
		}catch(Exception e) {
			assertEquals("Email invalido", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", null, "yuri.silva");
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "", "yuri.silva");
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "		", "yuri.silva");
			fail();
		}catch(Exception e) {
			assertEquals("atributo nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "nome", null);
			fail();

		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "nome", "");
			fail();

		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "nome", "		");
			fail();

		}catch(Exception e) {
			assertEquals("Nome nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "Email", "");
			fail();

		}catch(Exception e) {
			assertEquals("Email nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "Email", "		");
			fail();

		}catch(Exception e) {
			assertEquals("Email nulo ou vazio", e.getMessage());
		}
		
		try {
			meuController.editaPessoa("123.123.123-12", "Email", null);
			fail();

		}catch(Exception e) {
			assertEquals("Email nulo ou vazio", e.getMessage());
		}
		
		
		
	}

	@Test
	public void testRemovePessoa() throws ValidacaoException, CadastroException {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("Matheus", meuController.getInfoPessoa("123.123.123-12", "nome"));
		assertEquals("matheus@gmail.com", meuController.getInfoPessoa("123.123.123-12", "email"));
		
	}
	
	@Test
	public void testAssociaPessoa() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		
		AlunoGraduando aluno = new AlunoGraduando(120.0, 15);
		meuController.associaPessoa(aluno, "123.123.123-12");
		
		assertEquals("123.123.123-12", aluno.getPessoa().getCpf());
		
		
	}
	
	@Test
	public void testAssociaPessoaWithException() throws Exception {
		AlunoGraduando aluno = new AlunoGraduando(120.0, 15);
		try {
			meuController.associaPessoa(aluno, "404.252.131-25");
		}catch(Exception e) {
			assertEquals("Pessoa nao encontrada", e.getMessage());
		}
	}
	
	@Test
	public void testAdicionaParticipacao() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		AlunoGraduando aluno = new AlunoGraduando(120.0, 15);
		aluno.setProjeto(new PET("Change Trees", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		AlunoGraduando aluno1 = new AlunoGraduando(130.0, 15);
		aluno1.setProjeto(new PET("Aventuras", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		meuController.adicionaParticipacao(aluno, "123.123.123-12");
		
		assertEquals("Change Trees", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		meuController.adicionaParticipacao(aluno1, "123.123.123-12");
		
		assertEquals("Change Trees, Aventuras", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		
	}
	
	@Test
	public void testPesquisaPessoa() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertTrue(meuController.pesquisaPessoa("123.123.123-12"));
		
		try {
			meuController.pesquisaPessoa("150.152.142-14");
			fail();
		}catch(Exception e) {
			assertEquals("Pessoa nao encontrada", e.getMessage());
		}
		
	}
	
	@Test
	public void testRemoveParticipacao() throws Exception {
		meuController.cadastraPessoa("123.123.123-12", "Matheus", "matheus@gmail.com");
		assertEquals("", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		AlunoGraduando aluno = new AlunoGraduando(120.0, 15);
		aluno.setProjeto(new PET("Change Trees", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		AlunoGraduando aluno1 = new AlunoGraduando(130.0, 15);
		aluno1.setProjeto(new PET("Aventuras", "Impulsionar o ensino", 3, 65,  "29/12/2016", 15, 2));
		
		meuController.adicionaParticipacao(aluno, "123.123.123-12");
		
		assertEquals("Change Trees", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		meuController.adicionaParticipacao(aluno1, "123.123.123-12");
		
		assertEquals("Change Trees, Aventuras", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		
		meuController.removeParticipacao(aluno, "123.123.123-12");
		assertEquals("Aventuras", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		meuController.removeParticipacao(aluno1, "123.123.123-12");
		assertEquals("", meuController.getInfoPessoa("123.123.123-12", "participacoes"));
		
		try {
			meuController.removeParticipacao(aluno1, "123.123.123-12");
			fail();
		}catch(Exception e) {
			assertEquals("Participacao nao encontrada", e.getMessage());
		}
		
	}

}

package participacao.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import cdp.controllers.ParticipacaoController;
import cdp.controllers.PessoaController;
import cdp.controllers.ProjetoController;
import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;

public class ParticipacaoControllerTest {
	
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;
	private int cod1, cod2, cod3, cod4, cod5, cod6;
	private String cpf1, cpf2, cpf3, cpf4;

	@Before
	public void setUp() throws ValidacaoException, CadastroException, ParseException, DataException {
		this.pessoaController = new PessoaController();
		this.projetoController = new ProjetoController();
		this.participacaoController = new ParticipacaoController(this.pessoaController, this.projetoController);
		
		// Cadastrando pessoas no controle de pessoas para posteriormente fazer a associacao.
		cpf1 = this.pessoaController.cadastraPessoa("102.421.529-51", "Yuri", "yuritest@gmail.com");
		cpf2 = this.pessoaController.cadastraPessoa("404.195.254-42", "Jose", "josepascal@gmail.com");
		cpf3 = this.pessoaController.cadastraPessoa("954.245.165-52", "Maria", "mariaj@gmail.com");
		cpf4 = this.pessoaController.cadastraPessoa("323.156.168-25", "Jessica", "jessica@gmail.com");
		
		// Cadastrando projetos no controle de projetos para posteriormente fazer a associacao.
		cod1 = this.projetoController.adicionaPED("ePol", "coop", 1, 0, 1, "Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", "01/01/2017", 36);
		cod2 = this.projetoController.adicionaMonitoria("Monitoria Linear", "Algebra Linear", 70, "Ensinar linear", "2016.2", "19/01/2017", 6);
		cod3 = this.projetoController.adicionaPET("Grupo Get", "Transcender os alunos", 2, 35, 3, 2, 2, "15/03/2017", 24);
		cod4 = this.projetoController.adicionaExtensao("Aulas de ingles", "Fornecer aulas de ingles gratuitas", 2, "03/03/2017", 12);
		cod5 = this.projetoController.adicionaPED("Inteligencia Artificial", "PIBITI", 1, 2, 2, "Tornar IA mais intuitivo", "05/09/2017", 6);
		cod6 = this.projetoController.adicionaPED("Amazon", "coop", 1, 0, 1, "Trazer novas tecnologias para o e-commerce", "03/01/2017", 36);
	}

	@Test
	public void testAssociaProfessor() throws ValidacaoException, CadastroException {
		// Teste cpf1 cod3 Professor Coordenador
		this.participacaoController.associaProfessor(cpf1, cod3, true, 150, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf1, cod3));
		
		// Teste cpf2 cod1 Professor Coordenador
		this.participacaoController.associaProfessor(cpf2, cod1, true, 320, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod1));
		
		// Teste cpf3 cod6 Professor Coordenador
		this.participacaoController.associaProfessor(cpf3, cod6, true, 320, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf3, cod6));
		
		// Teste cpf1 cod4 Professor Coordenador
		this.participacaoController.associaProfessor(cpf1, cod4, true, 160, 25);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf1, cod4));
	}

	@Test
	public void testAssociaGraduando() throws ValidacaoException, CadastroException {
		// Teste cpf2 cod5 AlunoGraduando
		this.participacaoController.associaGraduando(cpf2, cod5, 120, 20);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod5));
		
		// Teste cpf2 cod1 AlunoGraduando
		this.participacaoController.associaGraduando(cpf2, cod1, 120, 22);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod1));
		// Teste falso cpf2, cod4
		assertFalse(this.participacaoController.pesquisaParticipacao(cpf2, cod4));
	}

	@Test
	public void testAssociaProfissional() throws ValidacaoException, CadastroException {
		// Teste cpf4, cod1
		this.participacaoController.associaProfissional(cpf4, cod1, "Gerente", 120, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf4, cod1));
		
		// Teste cpf4, cod6
		this.participacaoController.associaProfissional(cpf4, cod6, "Desenvolvedor", 170, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf4, cod6));
		
		// Teste cpf2, cod6
		this.participacaoController.associaProfissional(cpf2, cod6, "Pesquisador", 220, 20);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod6));
	}

	@Test
	public void testAssociaPosGraduando() throws ValidacaoException, CadastroException {
		// Teste cpf4, cod4
		this.participacaoController.associaPosGraduando(cpf4, cod4, "Mestrado", 180, 20);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf4, cod4));
		
		// Teste cpf2, cod4
		this.participacaoController.associaPosGraduando(cpf2, cod4, "Doutorado", 450, 20);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod4));
		
	}

	@Test
	public void testPesquisaParticipacao() throws ValidacaoException, CadastroException {
		ProjetoController projetoC = new ProjetoController();
		PessoaController pessoaC = new PessoaController();
		ParticipacaoController partController = new ParticipacaoController(pessoaC, projetoC);
		
		// Testando participacoes inexistentes
		assertFalse(partController.pesquisaParticipacao(cpf1, cod1));
		assertFalse(partController.pesquisaParticipacao(cpf1, cod2));
		assertFalse(partController.pesquisaParticipacao(cpf1, cod3));
		assertFalse(partController.pesquisaParticipacao(cpf2, cod1));
		
		// Testando participacoes existentes
		
		// Teste cpf1 cod3 Professor Coordenador
		this.participacaoController.associaProfessor(cpf1, cod3, true, 150, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf1, cod3));
		
		// Teste cpf2 cod1 Professor Coordenador
		this.participacaoController.associaProfessor(cpf2, cod1, true, 320, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf2, cod1));
		
		// Teste cpf3 cod6 Professor Coordenador
		this.participacaoController.associaProfessor(cpf3, cod6, true, 320, 15);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf3, cod6));
		
		// Teste cpf1 cod4 Professor Coordenador
		this.participacaoController.associaProfessor(cpf1, cod4, true, 160, 25);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf1, cod4));
	}

	@Test
	public void testRemoveParticipacao() throws ValidacaoException, CadastroException {
		

		try {
			this.participacaoController.removeParticipacao(cpf1, cod1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.removeParticipacao(cpf2, cod2);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.removeParticipacao("102.423.351-25", 9);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.removeParticipacao(cpf1, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		this.participacaoController.associaProfessor(cpf1, cod4, true, 160, 25);
		assertTrue(this.participacaoController.pesquisaParticipacao(cpf1, cod4));
		assertTrue(this.participacaoController.removeParticipacao(cpf1, cod4));
		
		
	}

	@Test
	public void testMostraParticipacoes() {
		assertEquals("", this.participacaoController.mostraParticipacoes());
	}
	
	@Test
	public void testParticipacaoControllerException() {
		
		try {
			this.participacaoController.associaProfessor(cpf1, cod2, false, 50, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(cpf1, cod2, false, -50, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(cpf1, cod2, false, 50, - 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(cpf1, cod2, false, 50, 0);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando(cpf1, cod3, - 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando(cpf1, cod3, 160, 0);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "", 160, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "	", 160, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, null, 160, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "Mestrado", - 160, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "Mestrado", 160,  - 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "Mestrado", 160, 0);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf2, cod3, "Maestro", 160, 50);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "", 160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "		", 160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, null, 160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "Gerente",  - 160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "Gerente",  160, - 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "Gerente",  160, 0);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, cod3, "Poligno",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional("", cod3, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional("		", cod3, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional("1230912093", cod3, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, - 50, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(cpf2, 0, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfissional(null, 50, "Gerente",  160, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando("", cod1, 120, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando("		", cod1, 120, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando(null, cod1, 120, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando(cpf1, 0, 120, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaGraduando(cpf1, - 50, 120, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando("", cod1, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando("			", cod1, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(null, cod1, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf1, - 50, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf1,  0, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf1,  50, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaPosGraduando(cpf1,  0, "Mestrado", 40, 20);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor("", cod1, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor("		", cod1, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(null, cod1, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(cpf1, - 50, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		
		try {
			this.participacaoController.associaProfessor(cpf1, 0, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.associaProfessor(cpf1, 50, false, 60, 15);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.pesquisaParticipacao("", cod1);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.pesquisaParticipacao("	", cod1);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.pesquisaParticipacao(null, cod1);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.pesquisaParticipacao(cpf1, 0);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			this.participacaoController.pesquisaParticipacao(cpf1, -50);
			fail();

		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
	}

}

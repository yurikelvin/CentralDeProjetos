package participacao.testes;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import cdp.controllers.ParticipacaoController;
import cdp.controllers.PessoaController;
import cdp.controllers.ProjetoController;
import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.factorys.FactoryDeParticipacao;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Cargo;
import cdp.participacao.Professor;
import cdp.participacao.Profissional;
import cdp.participacao.Vinculo;
import cdp.projetos.CategoriaPED;
import cdp.projetos.PED;

public class FactoryDeParticipacaoTest {
	
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private FactoryDeParticipacao fParticipacao;
	private String cpf1, cpf2, cpf3, cpf4;
	private int cod1, cod2, cod3, cod4, cod5, cod6;
	
	@Before
	public void setUp() throws ValidacaoException, CadastroException, ParseException, DataException {
		this.pessoaController = new PessoaController();
		this.projetoController = new ProjetoController();
		this.fParticipacao = new FactoryDeParticipacao(this.pessoaController, this.projetoController);
		
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
	public void testCriaAssociacaoProfessor() throws ValidacaoException, CadastroException {
		// Teste cpf1, cod1 Professor Coordenador
		Professor professor = (Professor) this.fParticipacao.criaAssociacaoProfessor(cpf1, cod1, true, 120, 20);
		
		PED ePol = null;
		
		try {
			ePol = (PED) professor.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(professor.getProjeto().getCodigo() == cod1);
		assertEquals("0", ePol.getProdutividade("Producao Academica"));
		assertEquals("1", ePol.getProdutividade("Producao tecnica"));
		assertEquals("1", ePol.getProdutividade("Patentes"));
		assertEquals("Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", ePol.getObjetivo());
		assertEquals("01/01/2017", ePol.getDataInicio());
		assertEquals(36, ePol.getDuracao());
		assertEquals("ePol", professor.getProjeto().getNome());
		assertEquals("coop", ePol.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(20, professor.getQtdHoras());
		assertEquals(120.0, professor.getValorHora(), 0.001);
		assertTrue(professor.getCoordenador());
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf1, professor.getCpf());
		assertEquals("Yuri", professor.getPessoa().getNome());
		assertEquals("yuritest@gmail.com", professor.getPessoa().getEmail());
		
		
		// Teste cpf2, cod1 Professor
		
		Professor professor1 = (Professor) this.fParticipacao.criaAssociacaoProfessor(cpf2, cod1, false, 120, 20);
		
		PED ePol1 = null;
		
		try {
			ePol1 = (PED) professor1.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(professor1.getProjeto().getCodigo() == cod1);
		assertEquals("0", ePol1.getProdutividade("Producao Academica"));
		assertEquals("1", ePol1.getProdutividade("Producao tecnica"));
		assertEquals("1", ePol1.getProdutividade("Patentes"));
		assertEquals("Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", ePol1.getObjetivo());
		assertEquals("01/01/2017", ePol1.getDataInicio());
		assertEquals(36, ePol1.getDuracao());
		assertEquals("ePol", professor1.getProjeto().getNome());
		assertEquals("coop", ePol1.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(20, professor1.getQtdHoras());
		assertEquals(120.0, professor1.getValorHora(), 0.001);
		assertFalse(professor1.getCoordenador());
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf2, professor1.getCpf());
		assertEquals("Jose", professor1.getPessoa().getNome());
		assertEquals("josepascal@gmail.com", professor1.getPessoa().getEmail());
		
	}

	@Test
	public void testCriaAssociacaoGraduando() throws CadastroException, ValidacaoException {
		AlunoGraduando alunoG = (AlunoGraduando) this.fParticipacao.criaAssociacaoGraduando(this.cpf3, this.cod5, 150.0, 20);
		
		PED inteligenciaArtificial = null;
		try {
			inteligenciaArtificial = (PED) alunoG.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(alunoG.getProjeto().getCodigo() == cod5);
		assertEquals("2", inteligenciaArtificial.getProdutividade("Producao Academica"));
		assertEquals("1", inteligenciaArtificial.getProdutividade("Producao tecnica"));
		assertEquals("2", inteligenciaArtificial.getProdutividade("Patentes"));
		assertEquals("Tornar IA mais intuitivo", inteligenciaArtificial.getObjetivo());
		assertEquals("05/09/2017", inteligenciaArtificial.getDataInicio());
		assertEquals(6, inteligenciaArtificial.getDuracao());
		assertEquals("Inteligencia Artificial", alunoG.getProjeto().getNome());
		assertTrue(CategoriaPED.PIBITI == inteligenciaArtificial.getCategoria());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(20, alunoG.getQtdHoras());
		assertEquals(150.0, alunoG.getValorHora(), 0.001);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf3, alunoG.getCpf());
		assertEquals("Maria", alunoG.getPessoa().getNome());
		assertEquals("mariaj@gmail.com", alunoG.getPessoa().getEmail());
		
	}

	@Test
	public void testCriaAssociacaoProfissional() throws CadastroException, ValidacaoException {
		
		// Teste cpf2, cod1 Gerente
		Profissional profissional = (Profissional) this.fParticipacao.criaAssociacaoProfissional(cpf2, cod1, "Gerente", 300, 40);
		
		PED ePol = null;
		
		try {
			ePol = (PED) profissional.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(profissional.getProjeto().getCodigo() == cod1);
		assertEquals("0", ePol.getProdutividade("Producao Academica"));
		assertEquals("1", ePol.getProdutividade("Producao tecnica"));
		assertEquals("1", ePol.getProdutividade("Patentes"));
		assertEquals("Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", ePol.getObjetivo());
		assertEquals("01/01/2017", ePol.getDataInicio());
		assertEquals(36, ePol.getDuracao());
		assertEquals("ePol", profissional.getProjeto().getNome());
		assertEquals("coop", ePol.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(40, profissional.getQtdHoras());
		assertEquals(300.0, profissional.getValorHora(), 0.001);
		assertTrue(profissional.getCargo() == Cargo.GERENTE);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf2, profissional.getCpf());
		assertEquals("Jose", profissional.getPessoa().getNome());
		assertEquals("josepascal@gmail.com", profissional.getPessoa().getEmail());
		
		
		// Teste cpf3, cod1 Pesquisador
		
		Profissional profissional1 = (Profissional) this.fParticipacao.criaAssociacaoProfissional(cpf3, cod1, "Pesquisador", 300, 40);
		
		PED ePol1 = null;
		
		try {
			ePol1 = (PED) profissional1.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(profissional1.getProjeto().getCodigo() == cod1);
		assertEquals("0", ePol1.getProdutividade("Producao Academica"));
		assertEquals("1", ePol1.getProdutividade("Producao tecnica"));
		assertEquals("1", ePol1.getProdutividade("Patentes"));
		assertEquals("Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", ePol.getObjetivo());
		assertEquals("01/01/2017", ePol1.getDataInicio());
		assertEquals(36, ePol1.getDuracao());
		assertEquals("ePol", profissional1.getProjeto().getNome());
		assertEquals("coop", ePol1.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(40, profissional1.getQtdHoras());
		assertEquals(300.0, profissional1.getValorHora(), 0.001);
		assertTrue(profissional1.getCargo() == Cargo.PESQUISADOR);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf3, profissional1.getCpf());
		assertEquals("Maria", profissional1.getPessoa().getNome());
		assertEquals("mariaj@gmail.com", profissional1.getPessoa().getEmail());
		
		// Teste cpf4, cod1 Desenvolvedor
		Profissional profissional2 = (Profissional) this.fParticipacao.criaAssociacaoProfissional(cpf4, cod1, "Desenvolvedor", 300, 40);
		
		PED ePol2 = null;
		
		try {
			ePol2 = (PED) profissional2.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(profissional2.getProjeto().getCodigo() == cod1);
		assertEquals("0", ePol2.getProdutividade("Producao Academica"));
		assertEquals("1", ePol2.getProdutividade("Producao tecnica"));
		assertEquals("1", ePol2.getProdutividade("Patentes"));
		assertEquals("Fazer um sistema que interligue todas delegacias, ajudando no processo de inqueritos", ePol.getObjetivo());
		assertEquals("01/01/2017", ePol2.getDataInicio());
		assertEquals(36, ePol2.getDuracao());
		assertEquals("ePol", profissional2.getProjeto().getNome());
		assertEquals("coop", ePol2.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(40, profissional2.getQtdHoras());
		assertEquals(300.0, profissional2.getValorHora(), 0.001);
		assertTrue(profissional2.getCargo() == Cargo.DESENVOLVEDOR);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf4, profissional2.getCpf());
		assertEquals("Jessica", profissional2.getPessoa().getNome());
		assertEquals("jessica@gmail.com", profissional2.getPessoa().getEmail());
		
		
	}

	@Test
	public void testCriaAssociacaoPosGraduando() throws CadastroException, ValidacaoException {
		// Teste cpf2, cod6 Mestrado
		AlunoPosGraduando pos1 = (AlunoPosGraduando) this.fParticipacao.criaAssociacaoPosGraduando(cpf2, cod6, "Mestrado", 210, 15);
		
		PED amazon = null;
		try {
			amazon = (PED) pos1.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(pos1.getProjeto().getCodigo() == cod6);
		assertEquals("0", amazon.getProdutividade("Producao Academica"));
		assertEquals("1", amazon.getProdutividade("Producao tecnica"));
		assertEquals("1", amazon.getProdutividade("Patentes"));
		assertEquals("Trazer novas tecnologias para o e-commerce", amazon.getObjetivo());
		assertEquals("03/01/2017", amazon.getDataInicio());
		assertEquals(36, amazon.getDuracao());
		assertEquals("Amazon", pos1.getProjeto().getNome());
		assertEquals("coop", amazon.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(15, pos1.getQtdHoras());
		assertEquals(210.0, pos1.getValorHora(), 0.001);
		assertTrue(pos1.getVinculo() == Vinculo.MESTRADO);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf2, pos1.getCpf());
		assertEquals("Jose", pos1.getPessoa().getNome());
		assertEquals("josepascal@gmail.com", pos1.getPessoa().getEmail());
		
		// Teste cpf1, cod6 Doutorado
		AlunoPosGraduando pos2 = (AlunoPosGraduando) this.fParticipacao.criaAssociacaoPosGraduando(cpf1, cod6, "Doutorado", 360, 10);
	
		PED amazon1 = null;
		try {
			amazon1 = (PED) pos2.getProjeto();
		}catch(Exception e) {
			fail();
		}
		
		// Testando se todos dados de projetos foram associados com sucesso.
		assertTrue(pos2.getProjeto().getCodigo() == cod6);
		assertEquals("0", amazon1.getProdutividade("Producao Academica"));
		assertEquals("1", amazon1.getProdutividade("Producao tecnica"));
		assertEquals("1", amazon1.getProdutividade("Patentes"));
		assertEquals("Trazer novas tecnologias para o e-commerce", amazon1.getObjetivo());
		assertEquals("03/01/2017", amazon1.getDataInicio());
		assertEquals(36, amazon1.getDuracao());
		assertEquals("Amazon", pos2.getProjeto().getNome());
		assertEquals("coop", amazon1.getCategoria().getValor());
		// Testando se os dados da participacao foram concretizados.
		assertEquals(10, pos2.getQtdHoras());
		assertEquals(360.0, pos2.getValorHora(), 0.001);
		assertTrue(pos2.getVinculo() == Vinculo.DOUTORADO);
		// Testando se os dados de pessoa foram associados com sucesso.
		assertEquals(cpf1, pos2.getCpf());
		assertEquals("Yuri", pos2.getPessoa().getNome());
		assertEquals("yuritest@gmail.com", pos2.getPessoa().getEmail());
	
	}

	
	public void testCriaAssociacaoWithException() {
		
		String CPF_NAO_CADASTRADO = "104.224.145-52";
		int CODIGO_PROJETO_NAO_CADASTRADO = 15;
		
		try {
			fParticipacao.criaAssociacaoGraduando(CPF_NAO_CADASTRADO, 2, 160, 25);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoGraduando(this.cpf1, CODIGO_PROJETO_NAO_CADASTRADO, 160, 24);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoPosGraduando(this.cpf1, CODIGO_PROJETO_NAO_CADASTRADO, "Mestrado", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
		fParticipacao.criaAssociacaoPosGraduando(CPF_NAO_CADASTRADO, this.cod1, "Mestrado", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
		fParticipacao.criaAssociacaoPosGraduando(CPF_NAO_CADASTRADO, this.cod1, "Doutorado", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoPosGraduando(this.cpf1, CODIGO_PROJETO_NAO_CADASTRADO, "Doutorado", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoProfessor(CPF_NAO_CADASTRADO, this.cod1, false, 160.0, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoProfessor(this.cpf1, CODIGO_PROJETO_NAO_CADASTRADO, false, 160.0, 20);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoProfissional(this.cpf1, CODIGO_PROJETO_NAO_CADASTRADO, "Gerente", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			fParticipacao.criaAssociacaoProfissional(CPF_NAO_CADASTRADO, this.cod1, "Gerente", 160, 15);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		
	}
}

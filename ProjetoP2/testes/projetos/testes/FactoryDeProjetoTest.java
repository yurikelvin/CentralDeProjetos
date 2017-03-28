package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.factorys.FactoryDeProjeto;
import cdp.projetos.CategoriaPED;
import cdp.projetos.Extensao;
import cdp.projetos.ImpactoSocial;
import cdp.projetos.Monitoria;
import cdp.projetos.PED;
import cdp.projetos.PET;

public class FactoryDeProjetoTest {

	private FactoryDeProjeto fProjeto;
	private static final String PROD_ACADEMICA = "Producao Academica";
	private static final String PROD_TECNICA = "Producao Tecnica";
	private static final String PATENTES = "Patentes";
	
	@Before
	public void setUp() {
		this.fProjeto = new	FactoryDeProjeto();
	}
	@Test
	public void testCriaMonitoria() throws DataException {
		Monitoria projeto = this.fProjeto.criaMonitoria("Monitoria Linear", "Algebra Linear", 80, "Ensinar os alunos do ccc", "2017.1", "19/10/2016", 6, 1);
		
		assertEquals("Monitoria Linear", projeto.getNome());
		assertEquals("Algebra Linear", projeto.getDisciplina());
		assertEquals("Ensinar os alunos do ccc", projeto.getObjetivo());
		assertEquals(80, projeto.getRendimento());
		assertEquals("2017.1", projeto.getPeriodo());
		assertEquals(6, projeto.getDuracao());
		assertEquals("19/10/2016", projeto.getDataInicio());
		assertEquals(1, projeto.getCodigo());
	}

	@Test
	public void testCriaPET() throws DataException, ValidacaoException {
		PET projeto = this.fProjeto.criaPET("Grupo GET", "Transcender os alunos", 3, 70, 2, 2, 3, "03/02/2017", 24, 2);
		
		assertEquals("Grupo GET", projeto.getNome());
		assertEquals("Transcender os alunos", projeto.getObjetivo());
		assertEquals(ImpactoSocial.REGIAO_ESTADO, projeto.getImpactoSocial());
		assertEquals(70, projeto.getRendimento());
		assertEquals("2", projeto.getRepresentacaoProdutividade(PROD_ACADEMICA));
		assertEquals("2", projeto.getRepresentacaoProdutividade(PROD_TECNICA));
		assertEquals("3", projeto.getRepresentacaoProdutividade(PATENTES));
		assertEquals("03/02/2017", projeto.getDataInicio());
		assertEquals(24, projeto.getDuracao());
		assertEquals(2, projeto.getCodigo());
		
		projeto.setImpacto(2);
		assertEquals(ImpactoSocial.CIDADE, projeto.getImpactoSocial());
		projeto.setImpacto(1);
		assertEquals(ImpactoSocial.COMUNIDADE_ACADEMICA, projeto.getImpactoSocial());
		projeto.setImpacto(4);
		assertEquals(ImpactoSocial.ESTADO, projeto.getImpactoSocial());
		projeto.setImpacto(5);
		assertEquals(ImpactoSocial.REGIAO_FEDERACAO, projeto.getImpactoSocial());
		projeto.setImpacto(6);
		assertEquals(ImpactoSocial.FEDERACAO, projeto.getImpactoSocial());
	}

	@Test
	public void testCriaExtensao() throws DataException, ValidacaoException {
		Extensao projeto = this.fProjeto.criaExtensao("Ingles Basico I", "Ensinar ingles a comunidade", 1, "29/01/2017", 6, 3);
		
		
		assertEquals("Ingles Basico I", projeto.getNome());
		assertEquals("Ensinar ingles a comunidade", projeto.getObjetivo());
		assertEquals(ImpactoSocial.COMUNIDADE_ACADEMICA, projeto.getImpacto());
		assertEquals("29/01/2017", projeto.getDataInicio());
		assertEquals(6, projeto.getDuracao());
		assertEquals(3, projeto.getCodigo());
		
		projeto.setImpacto(2);
		assertEquals(ImpactoSocial.CIDADE, projeto.getImpacto());
		projeto.setImpacto(3);
		assertEquals(ImpactoSocial.REGIAO_ESTADO, projeto.getImpacto());
		projeto.setImpacto(4);
		assertEquals(ImpactoSocial.ESTADO, projeto.getImpacto());
		projeto.setImpacto(5);
		assertEquals(ImpactoSocial.REGIAO_FEDERACAO, projeto.getImpacto());
		projeto.setImpacto(6);
		assertEquals(ImpactoSocial.FEDERACAO, projeto.getImpacto());
		
		
	}

	@Test
	public void testCriaPED() throws DataException, ValidacaoException {
		PED projeto1 = this.fProjeto.criaPED("ePol", "coop", 3, 2, 1, "Integralizar a pf", "19/02/2017", 32, 4);
		
		assertEquals("ePol", projeto1.getNome());
		assertEquals("Integralizar a pf", projeto1.getObjetivo());
		assertEquals("2", projeto1.getRepresentacaoProdutividade(PROD_ACADEMICA));
		assertEquals("3", projeto1.getRepresentacaoProdutividade(PROD_TECNICA));
		assertEquals("1", projeto1.getRepresentacaoProdutividade(PATENTES));
		assertEquals("19/02/2017", projeto1.getDataInicio());
		assertEquals(32, projeto1.getDuracao());
		assertEquals(4, projeto1.getCodigo());
		assertEquals(CategoriaPED.COOPERACAO_EMPRESAS, projeto1.getCategoria());
		
		PED projeto2 = this.fProjeto.criaPED("ePol1", "PIBITI", 3, 2, 1, "auxiliar", "19/02/2017", 32, 5);
		assertEquals(CategoriaPED.PIBITI, projeto2.getCategoria());
		
		PED projeto3 = this.fProjeto.criaPED("ePol2", "PIBIC", 3, 2, 1, "auxiliar1", "19/02/2017", 32, 6);
		
		assertEquals(CategoriaPED.PIBIC, projeto3.getCategoria());
		
		PED projeto4 = this.fProjeto.criaPED("ePol2", "PIVIC", 3, 2, 1, "auxiliar1", "19/02/2017", 32, 7);
		
		assertEquals(CategoriaPED.PIVIC, projeto4.getCategoria());
	}
	
	@Test
	public void testCriaDataInvalida() {
		
		try {
			Monitoria monitoria = this.fProjeto.criaMonitoria("Calculo Numerico", "Calculo numerico", 60, "Ensinar", "2017.1", "35/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			Monitoria monitoria = this.fProjeto.criaMonitoria("Calculo Numerico", "Calculo numerico", 60, "Ensinar", "2017.1", "50/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			Monitoria monitoria = this.fProjeto.criaMonitoria("Calculo Numerico", "Calculo numerico", 60, "Ensinar", "2017.1", "32/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			Monitoria monitoria = this.fProjeto.criaMonitoria("Calculo Numerico", "Calculo numerico", 60, "Ensinar", "2017.1", "37/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			PED ped = this.fProjeto.criaPED("ePol", "coop", 2, 1, 5, "Integralizar as delegacias", "64/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			PET pet = this.fProjeto.criaPET("Grupo GET", "Transcender os alunos", 3, 50, 2, 3, 1, "33/02/2017", 6, 1);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			Extensao projeto = this.fProjeto.criaExtensao("Ingles Basico I", "Ensinar ingles a comunidade", 1, "32/01/2017", 6, 3);
			fail();
		}catch(Exception e) {
			assertNotNull(e.getMessage());
		}
		
		
	}

}

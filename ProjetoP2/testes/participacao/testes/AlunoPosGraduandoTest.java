package participacao.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Vinculo;
import cdp.pessoa.Pessoa;
import cdp.projetos.Extensao;
import cdp.projetos.Projeto;

public class AlunoPosGraduandoTest {
	
	public AlunoPosGraduando aluno;
	public Pessoa pessoa;

	@Before
	public void setUp() throws Exception {
		aluno = new AlunoPosGraduando(15, 30, "doutorado");
		pessoa = new Pessoa("Matheus", "123.123.123-12", "matheus@gmail.com");
		aluno.setPessoa(pessoa);
	}

	@Test
	public void testGeraGanhos() throws ValidacaoException {
		assertEquals(600, aluno.geraGanhos(), 0.0001);
		AlunoPosGraduando aluno2 = new AlunoPosGraduando(15, 30, "mestrado");
		assertEquals(450, aluno2.geraGanhos(), 0.0001);
	}

	@Test
	public void testGeraPontuacaoParticipacao() throws DataException, CadastroException, ValidacaoException {
		Projeto p1 = new Extensao("Extensao", "aumentar o conhecimento", 3, "25/04/2017", 28, 3);
		aluno.setProjeto(p1);
		p1.adicionaParticipacao(aluno);
		assertEquals(0, aluno.geraPontuacaoParticipacao(), 0.001);
	}

	@Test
	public void testGetVinculo() throws ValidacaoException {
		assertEquals(Vinculo.DOUTORADO, aluno.getVinculo());
		AlunoPosGraduando aluno2 = new AlunoPosGraduando(15, 30, "mestrado");
		assertEquals(Vinculo.MESTRADO, aluno2.getVinculo());
		assertNotEquals(Vinculo.DOUTORADO, aluno2.getVinculo());
	}

}

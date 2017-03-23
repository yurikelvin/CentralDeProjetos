package participacao.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.participacao.AlunoGraduando;
import cdp.pessoa.Pessoa;
import cdp.projetos.Extensao;
import cdp.projetos.Monitoria;
import cdp.projetos.Projeto;

public class AlunoGraduandoTest {

	public AlunoGraduando aluno;
	public Pessoa pessoa;
	
	@Before
	public void setUp() {
		aluno = new AlunoGraduando(15, 30);
		pessoa = new Pessoa("Joao", "111.111.111-11", "joao@gmail.com");
		aluno.setPessoa(pessoa);
	}
	
	@Test
	public void testGetValorHora() {
		assertEquals(15, aluno.getValorHora(), 0.0001);
	}
	
	@Test
	public void testGetQtdHora() {
		assertEquals(30, aluno.getQtdHoras(), 0.0001);
	}
	
	@Test
	public void testGetPessoa() {
		Pessoa pessoaTest = new Pessoa("Joao", "111.111.111-11", "joao@gmail.com");
		assertEquals(pessoaTest, aluno.getPessoa());
	}
  
	@Test
	public void testGeraPontuacaoParticipacaoMonitoria() throws DataException, CadastroException {
		//Adicionando um projeto a participacao.
		Projeto p1 = new Monitoria("Monitoria", "p2", 45, "Auxiliar os alunos", "2017.1", "12/04/2017", 14, 1);
		aluno.setProjeto(p1);
		p1.adicionaParticipacao(aluno);
		
		// O calculo da pontuacao para monitoria consiste em dividir a duracao do projeto por 6
		// O resultado do produto multiplicado por 1.5, resultando na pontuacao.
		assertEquals(3, aluno.geraPontuacaoParticipacao(), 0.1);
	}
	
	@Test
	public void testGeraPontuacaoParticipacao() throws DataException, CadastroException {
		//Adicionando um projeto a participacao.
		Projeto p1 = new Extensao("Extensao", "aumentar o conhecimento", 3, "25/04/2017", 28, 3);
		aluno.setProjeto(p1);
		p1.adicionaParticipacao(aluno);
		
		// O calculo da pontuacao para qualquer projeto, com excecao de Monitoria, eh dividir a duracao do projeto por 6
		// O resultado do produto multiplicar por 2, resultando na pontuacao. Essa pontuacao nunca pode ser maior que 8.
		assertEquals(8, aluno.geraPontuacaoParticipacao(), 0.001);
	}

	

}

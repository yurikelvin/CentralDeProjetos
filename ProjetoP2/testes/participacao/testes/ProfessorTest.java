package participacao.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.CadastroException;
import participacao.AlunoGraduando;
import participacao.AlunoPosGraduando;
import participacao.Professor;
import pessoas.Pessoa;
import projetos.Monitoria;
import projetos.PET;
import projetos.Projeto;

public class ProfessorTest {
	
	public Professor professor;
	public Pessoa pessoa;
	
	@Before
	public void setUp() {
		professor = new Professor(15.00, 40, true);
		pessoa = new Pessoa("Joao", "111.111.111-11", "joao@gmail.com");
		professor.setPessoa(pessoa);
	}
	
	@Test
	public void testGetValorHora() {
		assertEquals(15, professor.getValorHora(), 0.0001);
	}
	
	@Test
	public void testGetQtdHoras() {
		assertEquals(40, professor.getQtdHoras());
	}
	
	@Test
	public void testGetCoordenador() {
		assertTrue(professor.getCoordenador());
	}
	
	@Test
	public void testGetPessoa() {
		Pessoa pessoaTest = new Pessoa("Joao", "111.111.111-11", "joao@gmail.com");
		assertEquals(pessoaTest, professor.getPessoa());
	}

	@Test
	public void testGeraGanhos() {
		// O calculo sera: [15 (valor da hora) * 0.4 (valor da bonificacao por ser coordenador)] 
		// * 40 (Quantidade de horas trabalhadas)
		assertEquals(840.00, professor.geraGanhos(), 0.0001);
	}

	@Test
	public void testGeraPontuacaoParticipacao() throws CadastroException {
		Projeto p1 = new PET("PET", "Auxiliar os alunos", 3, 25, "25/10/2017", 12, 1);
		professor.setProjeto(p1);
		assertEquals(4, professor.geraPontuacaoParticipacao(), 0.001);
		
		//Adicionando um aluno ao projeto
		AlunoGraduando aluno = new AlunoGraduando(5, 20);
		Pessoa pessoa2 = new Pessoa("Caio", "222.222.222-22", "caio@hotmail.com");
		aluno.setPessoa(pessoa2);
		p1.adicionaParticipacao(aluno);
		assertEquals(5, professor.geraPontuacaoParticipacao(), 0.0001);
		
		//Adicionando mais um aluno ao projeto
		AlunoPosGraduando aluno2 = new AlunoPosGraduando(8, 20, "mestrado");
		Pessoa pessoa3 = new Pessoa("Walber", "333.333.333-33", "walber@hotmail.com");
		aluno2.setPessoa(pessoa3);
		p1.adicionaParticipacao(aluno2);
		assertEquals(6, professor.geraPontuacaoParticipacao(), 0.0001);
		
	}
	
	@Test
	public void testGeraPontuacaoPartipaoEmMonitoria() throws CadastroException {
		// Em um projeto de Monitoria, a quantidade de alunos nao conta pontos para o professor.
		Projeto p1 = new Monitoria("Monitoria", "Calculo", 25, "Auxiliar os alunos", "2017.1", "29/10/2017", 22, 2);
		professor.setProjeto(p1);
		assertEquals(4, professor.geraPontuacaoParticipacao(), 0.0001);
		
		//Adicionando um aluno ao projeto
		AlunoGraduando aluno = new AlunoGraduando(5, 20);
		Pessoa pessoa2 = new Pessoa("Caio", "222.222.222-22", "caio@hotmail.com");
		aluno.setPessoa(pessoa2);
		p1.adicionaParticipacao(aluno);
		assertEquals(4, professor.geraPontuacaoParticipacao(), 0.0001);
			
		//Adicionando mais um aluno ao projeto
		AlunoPosGraduando aluno2 = new AlunoPosGraduando(8, 20, "mestrado");
		Pessoa pessoa3 = new Pessoa("Walber", "333.333.333-33", "walber@hotmail.com");
		aluno2.setPessoa(pessoa3);
		p1.adicionaParticipacao(aluno2);
		assertEquals(4, professor.geraPontuacaoParticipacao(), 0.0001);
		
	}

}

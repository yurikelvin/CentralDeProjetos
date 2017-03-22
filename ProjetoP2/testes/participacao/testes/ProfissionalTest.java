package participacao.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.Cargo;
import cdp.participacao.Professor;
import cdp.participacao.Profissional;
import cdp.pessoa.Pessoa;
import cdp.projetos.Monitoria;
import cdp.projetos.PED;
import cdp.projetos.Projeto;

public class ProfissionalTest {

	public Profissional profissional;
	private Pessoa pessoa;
	
	@Before
	public void setUp() {
		profissional = new Profissional("gerente", 25, 40);
		pessoa = new Pessoa("Maria Joana", "222.222.222-22", "m.j@hotmail.com");
		profissional.setPessoa(pessoa);
	}
	
	@Test
	public void testGetCargo() {
		assertEquals(Cargo.GERENTE, profissional.getCargo());
	}
	
	@Test
	public void testGetValorHora() {
		assertEquals(25, profissional.getValorHora(), 0.0001);
	}
	
	@Test
	public void testGetQtdHoras() {
		assertEquals(40, profissional.getQtdHoras());
	}
	
	@Test
	public void testGeraGanhoGerente() throws CadastroException, ValidacaoException, DataException {
		//Criando um projeto test
		Projeto p1 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 24, 2);
		
		//Adicionando participantes ao projeto
		AlunoGraduando aluno = new AlunoGraduando(19, 20);
		Pessoa pessoa3 = new Pessoa("Pedro", "444.444.444-44", "pedro@hotmail.com");
		Professor professor = new Professor(15, 40, true);
		Pessoa pessoa2 = new Pessoa("Paulo", "555.555.555-55", "paulo@gmail.com");
		aluno.setPessoa(pessoa3);
		aluno.setProjeto(p1);
		professor.setPessoa(pessoa2);
		professor.setProjeto(p1);
		p1.adicionaParticipacao(aluno);
		p1.adicionaParticipacao(profissional);
		p1.adicionaParticipacao(professor);
		profissional.setProjeto(p1);
		
		// O calculo do ganho de um gerente consiste no bonus (20 reais por participante no projeto, exceto se for gerente, e no max 5) e
		// o produto entre o valor da hora trabalhada X quantidade de horas trabalhadas
		assertEquals(1040, profissional.geraGanhos(), 0.0001);
		
	}
	
	@Test
	public void testGeraGanhoDesenvolvedor() throws ValidacaoException, DataException {
		Profissional profissional3 = new Profissional("desenvolvedor", 30, 40);
		
		//Criando um projeto test
		Projeto p1 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 24, 2);
		
		profissional3.setPessoa(pessoa);
		profissional3.setProjeto(p1);
		
		// O salario do desenvolvedor nao tem bonus, apenas o produto entre as horas trabalhadas e o valor da hora.
		assertEquals(1200, profissional3.geraGanhos(), 0.00001);
		
	}
	
	@Test
	public void testGeraGanhoPesquisador() throws ValidacaoException, DataException{
		Profissional profissional2 = new Profissional("pesquisador", 18, 40);
		
		//Criando um projeto test
		Projeto p1 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 24, 2);
				
		// Um pesquisador ganha 100 de bonus no salario.
		assertEquals(820, profissional2.geraGanhos(), 0.0001);
		
		
	}

	@Test
	public void testGeraPontuacaoParticipacaoGerente() throws CadastroException, ValidacaoException, DataException{
		//Criando um projeto test
		Projeto p1 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 24, 2);
		//Adicionando participantes ao projeto.
		AlunoGraduando aluno = new AlunoGraduando(19, 20);
		Pessoa pessoa3 = new Pessoa("Pedro", "444.444.444-44", "pedro@hotmail.com");
		aluno.setPessoa(pessoa3);
		aluno.setProjeto(p1);
		p1.adicionaParticipacao(aluno);
		p1.adicionaParticipacao(profissional);
		// 
		profissional.setProjeto(p1);
		
		
		// O valor eh 18 pois o gerente ganha 9 pontos para cada 12 meses de duracao. Como temos 24 meses, ele ganha 9*2 = 18.
		assertEquals(18, profissional.geraPontuacaoParticipacao(), 0.001);
		assertEquals(1020, profissional.geraGanhos(), 0.0001);
	}
	
	@Test
	public void testGeraPontuacaoParticipacaoPesquisador() throws CadastroException, ValidacaoException, DataException {
		Profissional profissional2 = new Profissional("pesquisador", 18, 40);
		
		//Criando um projeto para ser associado ao profissional
		Projeto p2 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 36, 2);
		profissional2.setPessoa(pessoa);
		profissional2.setProjeto(p2);
		
		p2.adicionaParticipacao(profissional2);
		
		
		// O pesquisador ganha 6 pontos a cada 12 meses de projeto. Logo, 36 meses = 6*3 = 18.
		assertEquals(18, profissional2.geraPontuacaoParticipacao(), 0.001);
		assertEquals(820, profissional2.geraGanhos(), 0.0001);
	}
	
	@Test
	public void testGeraPontuacaoParticipacaoDesenvolvedor() throws CadastroException, ValidacaoException, DataException {
		Profissional profissional3 = new Profissional("desenvolvedor", 30, 40);
		
		//Criando um projeto para ser associado ao profissional.
		Projeto p2 = new PED("Pesquisas", "coop", "Fazer novas descobertas", "25/10/2017", 12, 2);
		profissional3.setPessoa(pessoa);
		profissional3.setProjeto(p2);
		
		p2.adicionaParticipacao(profissional3);
		
		// O desenvolvedor ganha 5 pontos a cada 12 meses de Projeto. Logo, 12 meses = 5*1 = 5.
		assertEquals(5, profissional3.geraPontuacaoParticipacao(), 0.001);
		assertEquals(1200, profissional3.geraGanhos(), 0.0001);
	}

}

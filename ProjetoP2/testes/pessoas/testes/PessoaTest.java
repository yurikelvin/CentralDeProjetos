package pessoas.testes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import participacao.AlunoGraduando;
import participacao.Participacao;
import pessoas.Pessoa;
import projetos.PED;
import projetos.PET;

public class PessoaTest {
	
	private Pessoa people;
	
	@Before
	public void setUp() {
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
	}
	
	@Test
	public void testPessoa() {
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
	}

	@Test
	public void testGetNome() {
		Pessoa p1 = new Pessoa("Joao", "123.123.123-12", "joao@gmail.com");
		assertEquals("Joao", p1.getNome());
		assertNotEquals("Matheus", p1.getNome());
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertNotEquals("Joao", people.getNome());
		assertEquals("Matheus", people.getNome());
	}

	@Test
	public void testSetNome() {
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertEquals("Matheus", people.getNome());
		people.setNome("Joao");
		assertEquals("Joao", people.getNome());
		
	}

	@Test
	public void testEqualsObject() {
		Pessoa p1 = new Pessoa("Joao", "123.123.123-12", "joao@gmail.com");
		Pessoa p2 = new Pessoa("Tiberio", "123.123.123-12", "tenorio@gmail.com");
		people = new Pessoa("Matheus", "987.654.321-00", "matheus.farias@ccc.ufcg.edu.br");
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(people));
		assertFalse(p2.equals(people));
	}
	
	@Test
	public void testMostraParticipacoes() {
		
		assertEquals("", people.mostraParticipacoes());
		Participacao participacao = new AlunoGraduando(150.0, 25);
		PED ped = new PED("Compt", "PIBIC", "Estudar computadores e redes.", "20/02/2017", 12, 2);
		participacao.setProjeto(ped);
		people.setParticipacao(participacao);
		assertEquals("Compt", people.mostraParticipacoes());
		PET pet = new PET("Pet Eletrica", "Auxiliar os alunos", 1, 70, "24/01/2017", 12, 2);
		Participacao participacao1 = new AlunoGraduando(120, 13);
		participacao1.setProjeto(pet);
		people.setParticipacao(participacao1);
		assertEquals("Compt, Pet Eletrica", people.mostraParticipacoes());
	}
	
	@Test
	public void testRemoveParticipacao() {
		
		assertEquals("", people.mostraParticipacoes());
		
		Participacao participacao = new AlunoGraduando(150.0, 25);
		PED ped = new PED("Compt", "PIBIC", "Estudar computadores e redes.", "20/02/2017", 12, 2);
		participacao.setProjeto(ped);
		participacao.setPessoa(people);
		people.setParticipacao(participacao);
		assertEquals("Compt", people.mostraParticipacoes());
		
		PET pet = new PET("Pet Eletrica", "Auxiliar os alunos", 1, 70, "24/01/2017", 12, 3);
		Participacao participacao1 = new AlunoGraduando(120, 13);
		participacao1.setProjeto(pet);
		participacao1.setPessoa(people);
		people.setParticipacao(participacao1);
		assertEquals("Compt, Pet Eletrica", people.mostraParticipacoes());
		
		people.removeParticipacao(participacao1);
		assertEquals("Compt", people.mostraParticipacoes());
		people.removeParticipacao(participacao);
		assertEquals("", people.mostraParticipacoes());
		
		
	}
}

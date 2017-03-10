package participacao;

import java.util.HashSet;

import exception.CadastroException;
import pessoas.Pessoa;
import pessoas.PessoaController;
import projetos.Projeto;
import projetos.ProjetoController;

public class ParticipacaoController {
	
	private HashSet<Participacao> participacoes;
	private PessoaController pessoaController;
	private ProjetoController projetoController;

	public ParticipacaoController() {
		participacoes = new HashSet<>();
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
	}
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras) throws CadastroException {
		
		Pessoa professor = pessoaController.getPessoa(cpf);
		Projeto projeto = projetoController.getProjetos(codigoProjeto);
		
		
		
		Professor participacao = new Professor(valorHora, qtdHoras, coordenador);
		
		professor.setParticipacao(participacao);
	}

}

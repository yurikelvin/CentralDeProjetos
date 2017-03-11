package centraldeprojetos;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;

import easyaccept.EasyAccept;
import exception.CadastroException;
import exception.ValidacaoException;
import participacao.AlunoGraduando;
import participacao.AlunoPosGraduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;
import pessoas.Pessoa;
import pessoas.PessoaController;
import projetos.Monitoria;
import projetos.Projeto;
import projetos.ProjetoController;
import validacao.ValidaPessoa;
import validacao.Validacao;

public class CentralDeProjetos implements Serializable{

	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private HashSet<Participacao> participacoes; 
	
	public CentralDeProjetos() {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacoes = new HashSet<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException, CadastroException {
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}

	public String getInfoPessoa(String cpf, String atributo)
			throws ValidacaoException, IllegalArgumentException, CadastroException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}

	public void editaPessoa(String cpf, String atributo, String novoValor)
			throws CadastroException, ValidacaoException, IllegalArgumentException {
		pessoaController.editaPessoa(cpf, atributo, novoValor);
	}

	public void removePessoa(String cpf) throws CadastroException, ValidacaoException {
		pessoaController.removePessoa(cpf);
	}

	public Pessoa getPessoa(String cpf) throws CadastroException {
		return pessoaController.getPessoa(cpf);
	}

	public String mostraPessoas() {
		return pessoaController.toString();
	}

	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws ValidacaoException, ParseException, CadastroException {
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio,
				duracao);
	}

	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException, ParseException {
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws ValidacaoException, ParseException {
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws ValidacaoException, ParseException {
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo,
				dataInicio, duracao);
	}

	public String getInfoProjeto(int codigoProjeto, String atributo) throws CadastroException, ValidacaoException {
		return projetoController.getInfoProjeto(codigoProjeto, atributo);
	}

	public Projeto getProjetos(int codigoProjeto) throws CadastroException {
		return projetoController.getProjetos(codigoProjeto);
	}

	public void editaProjeto(int codigoDoProjeto, String atributo, String novoValor)
			throws CadastroException, ValidacaoException, ParseException {
		projetoController.editaProjeto(codigoDoProjeto, atributo, novoValor);
	}

	public void removeProjeto(int codigoDoProjeto) throws CadastroException {
		projetoController.removeProjeto(codigoDoProjeto);
	}

	public int getCodigoProjeto(String nome) throws CadastroException {
		return projetoController.getCodigoProjeto(nome);
	}

	public String mostraProjetos() {
		return projetoController.toString();
	}
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		
		
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa professor = this.pessoaController.getPessoa(cpf);
		Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
		
		this.validaHoraProfessor(valorHora, projeto);
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {

			Professor participacao = new Professor(valorHora, qtdHoras, coordenador);
			participacao.setPessoa(professor);
			participacao.setProjeto(projeto);
			
			projeto.adicionaParticipacao(participacao);
			professor.setParticipacao(participacao);
			this.participacoes.add(participacao);
		}
		
	}
	
	private boolean validaHoraProfessor(double valorHora, Projeto projeto) {
		if(projeto instanceof Monitoria) {
			Validacao.validaDouble(valorHora, "Valor da hora invalido");
			if(valorHora > 0) {
				throw new ValidacaoException("Valor da hora de um professor da monitoria deve ser zero");
			}
			return true;
		}
		Validacao.validaDoubleSemZero(valorHora, "Valor da hora invalido");
		return false;
		
	}

	public void associaGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			Pessoa graduando = this.pessoaController.getPessoa(cpf);
			Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
			
			AlunoGraduando participacao = new AlunoGraduando(valorHora, qtdHoras);
			participacao.setPessoa(graduando);
			participacao.setProjeto(projeto);
			
			
			projeto.adicionaParticipacao(participacao);
			graduando.setParticipacao(participacao);
			
			this.participacoes.add(participacao);
		}
		
	}
	
	public void associaProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(cargo, "Cargo invalido");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			
			Pessoa profissional = this.pessoaController.getPessoa(cpf);
			Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
			
			Profissional participacao = new Profissional(cargo.toLowerCase(), valorHora, qtdHoras);
			participacao.setPessoa(profissional);
			participacao.setProjeto(projeto);
			
			profissional.setParticipacao(participacao);
			projeto.adicionaParticipacao(participacao);
			
			this.participacoes.add(participacao);
		}
		
	}
	
	
	public void associaPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(associacao, "Pos graduacao invalida.");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		if(!this.pesquisaParticipacao(cpf, codigoProjeto)) {
			Pessoa posGraduando = this.pessoaController.getPessoa(cpf);
			Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
			
			AlunoPosGraduando participacao = new AlunoPosGraduando( valorHora, qtdHoras, associacao);
			participacao.setPessoa(posGraduando);
			participacao.setProjeto(projeto);
			

			
			posGraduando.setParticipacao(participacao);
			projeto.adicionaParticipacao(participacao);
			
			this.participacoes.add(participacao);
		}
		
	
	}
	
	
	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		for(Participacao participacao: this.participacoes) {
			if(participacao.getPessoa().getClass().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
				return true;
		
			} 
			
		}
		
		return false;
	}
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa pessoa = pessoaController.getPessoa(cpf);
		Projeto projeto = projetoController.getProjetos(codigoProjeto);
		

		
		Iterator<Participacao> it = this.participacoes.iterator();
		while(it.hasNext()) {
			Participacao participacao = it.next();
			if(participacao.getPessoa().getCpf().equals(cpf) && participacao.getProjeto().getCodigo() == codigoProjeto) {
				this.participacoes.remove(participacao);
				projeto.removeParticipacao(participacao);
				pessoa.removeParticipacao(participacao);
			}
		}
		

		throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
	}
	
	
	

}

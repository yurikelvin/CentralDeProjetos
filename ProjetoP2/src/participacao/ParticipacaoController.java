package participacao;

import java.io.Serializable;
import java.util.HashSet;

import exception.CadastroException;
import exception.ValidacaoException;
import pessoas.Pessoa;
import pessoas.PessoaController;
import projetos.Projeto;
import projetos.ProjetoController;
import validacao.ValidaPessoa;
import validacao.Validacao;

/**
 * 
 * @author Yuri Silva
 *
 */
public class ParticipacaoController implements Serializable{
	
	private HashSet<Participacao> participacoes;
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	
	/**
	 * 
	 */

	public ParticipacaoController() {
		participacoes = new HashSet<>();
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @param coordenador
	 * @param valorHora
	 * @param qtdHoras
	 * @throws CadastroException
	 * @throws ValidacaoException
	 */
	
	public void associaProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		
		Validacao.validaDoubleSemZero(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa professor = this.pessoaController.getPessoa(cpf);
		Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
		
		
		
		Professor participacao = new Professor(valorHora, qtdHoras, coordenador);
		participacao.setPessoa(professor);
		participacao.setProjeto(projeto);
		
		projeto.adicionaParticipacao(participacao);
		professor.setParticipacao(participacao);
		this.participacoes.add(participacao);
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @param valorHora
	 * @param qtdHoras
	 * @throws CadastroException
	 * @throws ValidacaoException
	 */
	
	public void associaGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		
		Pessoa graduando = this.pessoaController.getPessoa(cpf);
		Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
		
		AlunoGraduando participacao = new AlunoGraduando(valorHora, qtdHoras);
		participacao.setPessoa(graduando);
		participacao.setProjeto(projeto);
		
		projeto.adicionaParticipacao(participacao);
		graduando.setParticipacao(participacao);
		
		this.participacoes.add(participacao);
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @param cargo
	 * @param valorHora
	 * @param qtdHoras
	 * @throws CadastroException
	 * @throws ValidacaoException
	 */
	
	public void associaProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(cargo, "Cargo invalido");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa profissional = this.pessoaController.getPessoa(cpf);
		Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
		
		Profissional participacao = new Profissional(cargo, valorHora, qtdHoras);
		participacao.setPessoa(profissional);
		participacao.setProjeto(projeto);
		
		profissional.setParticipacao(participacao);
		projeto.adicionaParticipacao(participacao);
		
		this.participacoes.add(participacao);
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @param associacao
	 * @param valorHora
	 * @param qtdHoras
	 * @throws CadastroException
	 * @throws ValidacaoException
	 */
	
	public void associaPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Validacao.validaDouble(valorHora, "Valor da hora invalido");
		Validacao.validaIntSemZero(qtdHoras, "Quantidade de horas invalida");
		Validacao.validaString(associacao, "Pos graduacao invalida.");
		
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa posGraduando = this.pessoaController.getPessoa(cpf);
		Projeto projeto = this.projetoController.getProjetos(codigoProjeto);
		
		AlunoPosGraduando participacao = new AlunoPosGraduando( valorHora, qtdHoras, associacao);
		participacao.setPessoa(posGraduando);
		participacao.setProjeto(projeto);
		

		
		posGraduando.setParticipacao(participacao);
		projeto.adicionaParticipacao(participacao);
		
		this.participacoes.add(participacao);
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @return
	 * @throws CadastroException 
	 */
	
	public boolean pesquisaParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		for(Participacao participacao: this.participacoes) {
			if(participacao.getCpf().equals(cpf) && participacao.getCodigoProjeto() == codigoProjeto) {
				return true;
			}
		}
		
		throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
	}
	
	/**
	 * 
	 * @param cpf
	 * @param codigoProjeto
	 * @return
	 * @throws CadastroException
	 * 
	 */
	
	public boolean removeParticipacao(String cpf, int codigoProjeto) throws CadastroException {
		ValidaPessoa.validaCPF(cpf);
		Validacao.validaIntSemZero(codigoProjeto, "Codigo do projeto invalido");
		
		Pessoa pessoa = pessoaController.getPessoa(cpf);
		Projeto projeto = projetoController.getProjetos(codigoProjeto);

		
		for(Participacao participacao: this.participacoes) {
			if(participacao.getCpf().equals(cpf) && participacao.getCodigoProjeto() == codigoProjeto) {
				this.participacoes.remove(participacao);
				projeto.removeParticipacao(participacao);
				return true;
			} else if(participacao.getCpf().equals(cpf) && participacao.getCodigoProjeto() != codigoProjeto) {
				throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
			} else if(!participacao.getCpf().equals(cpf) && participacao.getCodigoProjeto() == codigoProjeto) {
				throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
			}
		}
		

		throw new CadastroException("Pessoa nao possui participacao no projeto indicado");
	}
	

}

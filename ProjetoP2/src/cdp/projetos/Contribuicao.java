package cdp.projetos;

import cdp.exception.CadastroException;
import cdp.exception.ValidacaoException;

public interface Contribuicao {
	
	/**
	 * Calcula o valor da contribuicao para o UASC de um projeto.
	 * @return Retorna um double que representa o valor da contribuicao.
	 */
	double geraContribuicao() throws CadastroException, ValidacaoException;

}

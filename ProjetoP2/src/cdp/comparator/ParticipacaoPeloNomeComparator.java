package cdp.comparator;

import java.util.Comparator;

import cdp.participacao.Participacao;

/**
 * Classe responsavel por fornecer um comparator para que seja possivel ordenar as participacoes pelo nome.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class ParticipacaoPeloNomeComparator implements Comparator<Participacao>{

	@Override
	public int compare(Participacao p1, Participacao p2) {
		return p1.getPessoa().getNome().compareTo(p2.getPessoa().getNome());
	}

}

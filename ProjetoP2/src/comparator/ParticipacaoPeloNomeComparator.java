package comparator;

import java.util.Comparator;

import participacao.Participacao;

public class ParticipacaoPeloNomeComparator implements Comparator<Participacao>{

	@Override
	public int compare(Participacao p1, Participacao p2) {
		return p1.getPessoa().getNome().compareTo(p2.getPessoa().getNome());
	}

}

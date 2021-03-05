package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * Estratégia em que será jogado o primeiro carroção assim que possível
 * @author Mirlla Marques Santana Alves
 */
public class CarrocoesPrimeiro implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		for (Peca peca : mao) {
			if (peca.carroca()) {
				if (mesa.getNumPecas() == 0) {
					return new Jogada(peca, TipoJogada.NA_DIREITA);
				} else {
					if (peca.encaixa(mesa.getNumNaDireita())) {
						return new Jogada(peca, TipoJogada.NA_DIREITA);
					} else if (peca.encaixa(mesa.getNumNaEsquerda())) {
						return new Jogada(peca, TipoJogada.NA_ESQUERDA);
					}
				}
			}
		}
		for (Peca peca : mao) {
			if (mesa.getNumPecas() == 0) {
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			} else {
				if (peca.encaixa(mesa.getNumNaDireita())) {
					return new Jogada(peca, TipoJogada.NA_DIREITA);
				} else if (peca.encaixa(mesa.getNumNaEsquerda())) {
					return new Jogada(peca, TipoJogada.NA_ESQUERDA);
				}
			}
		}
		return new Jogada();
	}

}

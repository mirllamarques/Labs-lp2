package ufcg.ccc.domino.estrategia;

import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Peca;

/**
 * Estratégia que prioriza jogar as peças mais altas primeiro
 * @author Mirlla Marques Santana Alves
 */
public class MaisAltasPrimeiro implements EstrategiaDeJogo{

	@Override
	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		int indiceMaior = 0;
		int somaMaior = 0;
		boolean maoVazia = mesa.getNumPecas() == 0;
		for (int i = 0; i < mao.size(); i++) {
			boolean encaixaDireita =  mao.get(i).encaixa(mesa.getNumNaDireita());
			boolean encaixaEsquerda =  mao.get(i).encaixa(mesa.getNumNaEsquerda());
			if(maoVazia || encaixaDireita || encaixaEsquerda){
				int soma = mao.get(i).getNumDireito() + mao.get(i).getNumEsquerdo();
				if(soma > somaMaior) {
					somaMaior = soma;
					indiceMaior = i;
				}
			}
		}
		Peca maisAlta = mao.get(indiceMaior);
		if (maisAlta.encaixa(mesa.getNumNaDireita())){
			return new Jogada(maisAlta, TipoJogada.NA_DIREITA);
		} else if (maisAlta.encaixa(mesa.getNumNaEsquerda())){
			return new Jogada(maisAlta, TipoJogada.NA_ESQUERDA);
		}
		return new Jogada();
	}

}

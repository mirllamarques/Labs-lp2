package ufcg.ccc.domino;

import java.util.Random;

import ufcg.ccc.domino.estrategia.CarrocoesPrimeiro;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;
import ufcg.ccc.domino.estrategia.MaisAltasPrimeiro;

/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estratégias.
 * 
 */
public class DominoBrutalRepetido {

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		int vitoriasJ1 = 0, vitoriasJ2 = 0, empates = 0;
		
		TiposVitorias J1 = new TiposVitorias(), J2 = new TiposVitorias();

		EstrategiaDeJogo estrategia1 = new JogaPrimeiraPossivel(), estrategia2 = new CarrocoesPrimeiro(), 
				estrategia3 = new MaisAltasPrimeiro();
		EstrategiaDeJogo[] estrategias = new EstrategiaDeJogo[3];
		estrategias[0] = estrategia1;
		estrategias[1] = estrategia2;
		estrategias[2] = estrategia3;
		
		for (int i = 0; i < 10000; i++) {
			Random estrategiaAleatoria = new Random();
			int aleatorio1 = estrategiaAleatoria.nextInt(3);
			int aleatorio2 = estrategiaAleatoria.nextInt(3);
			Jogo j;
			if(i < 5000) {
				 j = new Jogo("J1", estrategias[aleatorio1], "J2",  estrategias[aleatorio2], 12);
			} else {
				 j = new Jogo("J2",  estrategias[aleatorio1], "J1",  estrategias[aleatorio2], 12);
			}
			
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor() == "J1") {
				vitoriasJ1++;
				J1.add(j.getPontuacao());
			} else if (historico.getVencedor() == "J2") {
				vitoriasJ2++;
				J2.add(j.getPontuacao());
			}
		}

		String vencedor = "";
		
		if (J1.getTotal() > J2.getTotal()) {
			vencedor = "J1";
		} else if (J2.getTotal() > J1.getTotal()) {
			vencedor = "J2";
		} else {
			vencedor = "Empate";
		}
		
		System.out.println("----Jogos----\nPartidas:\t" + (empates + vitoriasJ1 + vitoriasJ2) + "\nEmpates:\t"  + empates + "\nVencedor:\t" + vencedor + "\n\n----J1----\nPontuação:\t" +
				J1.getTotal() + "\nRodadas ganhas:\t" + vitoriasJ1 + "\nVitórias Normais:\t" + J1.getNormal()  + "\nVitórias por Carroção:\t" + 
				J1.getCarrocao() + "\nVitórias por Lá e Ló:\t" + J1.getLaELo() + "\nVitórias por Lá e Ló de Carroção: " + J1.getLaLoCruzado() +
				 "\n\n----J2----\nPontuação:\t" + J2.getTotal() + "\nRodadas ganhas:\t" + vitoriasJ2 + "\nVitórias Normais:\t" + J2.getNormal() + 
				 "\nVitórias por Carroção:\t" + J2.getCarrocao() + "\nVitórias por Lá e Ló:\t" + J2.getLaELo() + "\nVitórias por Lá e Ló de Carroção: "
				 + J2.getLaLoCruzado());
	}

}

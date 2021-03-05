package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.JogadaInvalidaException;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

class MaisAltasPrimeiroTest {
	
	private Mesa mesa;
	private MaisAltasPrimeiro estrategia;

	@BeforeEach
	void inicia() throws JogadaInvalidaException {
		mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1, 2));
		mesa.jogaNaEsquerda(new Peca(1, 1));
		this.estrategia = new MaisAltasPrimeiro();
	}

	@Test
	void testPassa() {
		Jogada j1 = this.estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(0, 3)));

		assertEquals(TipoJogada.PASSA, j1.getTipo());
	}
	
	@Test
	void MaisAltasPrimeiro() throws JogadaInvalidaException {
		Jogada j1 = this.estrategia.decideJogada(mesa, List.of(new Peca(2, 2), new Peca(4, 2)));
		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(4, j1.getPeca().getNumEsquerdo());
		assertEquals(2, j1.getPeca().getNumDireito());
		
		Jogada j2 = this.estrategia.decideJogada(mesa, List.of(new Peca(5, 2), new Peca(3, 2)));	
		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(5, j2.getPeca().getNumEsquerdo());
		assertEquals(2, j2.getPeca().getNumDireito());
		
		mesa.jogaNaDireita(new Peca(2, 5));
		mesa.jogaNaEsquerda(new Peca(1, 6));
		Jogada j3 = this.estrategia.decideJogada(mesa, List.of(new Peca(5, 5), new Peca(6, 6)));	
		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(6, j3.getPeca().getNumEsquerdo());
		assertEquals(6, j3.getPeca().getNumDireito());
		
	}


}

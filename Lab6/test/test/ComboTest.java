package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fornecedor.Fornecedor;
import produto.Combo;

class ComboTest {
	
	Combo combo1;
	Combo combo2;
	
	@BeforeEach
	void criaCombo() {
		Fornecedor olavo =  new Fornecedor("Seu Olavo", "olavo@email.com", "3310-6000");
		olavo.adicionaProduto("abc", "def", 1.0);
		olavo.adicionaProduto("xyz", "qwerty", 1.0);
		this.combo1 = new Combo(olavo, "qwerty", "wasd", 0.8, "abc - def, xyz - qwerty");
		Fornecedor salete =  new Fornecedor("Dona Salete", "salete@email.com", "4002-8922");
		salete.adicionaProduto("teste1", "testando", 1.0);
		salete.adicionaProduto("teste2", "outro teste", 1.0);
		this.combo2 = new Combo(salete, "teste", "JUnit5", 0.5, "teste1 - testando, teste2 - outro teste");
	}

	@Test
	void testSetFatorToString() {
		assertEquals("qwerty - wasd - R$0,40", this.combo1.toString());
		this.combo1.setFator(0.2);
		assertEquals("qwerty - wasd - R$1,60", this.combo1.toString());
		this.combo1.setFator(0.4);
		assertEquals("qwerty - wasd - R$1,20", this.combo1.toString());
		
		assertEquals("teste - JUnit5 - R$1,00", this.combo2.toString());
		this.combo2.setFator(0.7);
		assertEquals("teste - JUnit5 - R$0,60", this.combo2.toString());
		this.combo2.setFator(0.3);
		assertEquals("teste - JUnit5 - R$1,40", this.combo2.toString());
	}

	@Test
	void testGetNome() {
		assertEquals("qwerty", this.combo1.getNome());
		assertEquals("teste", this.combo2.getNome());
	}
}

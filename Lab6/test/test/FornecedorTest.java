package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fornecedor.Fornecedor;

class FornecedorTest {
	
	Fornecedor fornecedor;
	
	@BeforeEach
	void criaFornecedor() {
		this.fornecedor = new Fornecedor("Seu Olavo", "olavo@email.com", "3310-6000");
	}


	@Test
	void testAdicionaComboExibeProduto() {
		this.fornecedor.adicionaProduto("abc", "def", 1.0);
		this.fornecedor.adicionaProduto("xyz", "qwerty", 1.0);
		assertEquals("abc - def - R$1,00", this.fornecedor.exibeProduto("abc", "def"));
		assertEquals("xyz - qwerty - R$1,00", this.fornecedor.exibeProduto("xyz", "qwerty"));
		this.fornecedor.adicionaCombo("qwerty", "wasd", 0.8, "abc - def, xyz - qwerty");
		assertEquals("qwerty - wasd - R$0,40", this.fornecedor.exibeProduto("qwerty", "wasd"));
	}

	@Test
	void testListaProdutosRemoveProduto() {
		this.fornecedor.adicionaProduto("abc", "def", 1.0);
		this.fornecedor.adicionaProduto("xyz", "qwerty", 1.0);
		this.fornecedor.adicionaCombo("qwerty", "wasd", 0.8, "abc - def, xyz - qwerty");
		assertEquals("Seu Olavo - abc - def - R$1,00 | Seu Olavo - qwerty - wasd - R$0,40 | Seu Olavo - xyz - qwerty - R$1,00", this.fornecedor.listaProdutos());
		this.fornecedor.removeProduto("xyz", "qwerty");
		assertEquals("Seu Olavo - abc - def - R$1,00 | Seu Olavo - qwerty - wasd - R$0,40", this.fornecedor.listaProdutos());
		this.fornecedor.removeProduto("qwerty", "wasd");
		assertEquals("Seu Olavo - abc - def - R$1,00", this.fornecedor.listaProdutos());
	}

	@Test
	void testEditaCombo() {
		this.fornecedor.adicionaProduto("abc", "def", 1.0);
		this.fornecedor.adicionaProduto("xyz", "qwerty", 1.0);
		this.fornecedor.adicionaCombo("qwerty", "wasd", 0.8, "abc - def, xyz - qwerty");
		assertEquals("qwerty - wasd - R$0,40", this.fornecedor.exibeProduto("qwerty", "wasd"));
		this.fornecedor.editaCombo("qwerty", "wasd", 0.2);
		assertEquals("qwerty - wasd - R$1,60", this.fornecedor.exibeProduto("qwerty", "wasd"));
	}

}

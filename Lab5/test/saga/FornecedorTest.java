package saga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FornecedorTest {
	
	Fornecedor fornecedor1;
	Fornecedor fornecedor2;
	Fornecedor fornecedor3;

	@BeforeEach
	void criaFornecedores() {
		this.fornecedor1 = new Fornecedor("teste1", "teste1@junit.com", "123456789");
		this.fornecedor2 = new Fornecedor("teste2", "teste2@junit.com", "987654321");
		assertThrows(NullPointerException.class, () -> this.fornecedor3 = new Fornecedor(null, null, null));
		assertThrows(NullPointerException.class, () -> this.fornecedor3 = new Fornecedor(null, "teste3@junit.com", "987654321"));
		assertThrows(NullPointerException.class, () -> this.fornecedor3 = new Fornecedor("teste3", null, "987654321"));
		assertThrows(NullPointerException.class, () -> this.fornecedor3 = new Fornecedor("teste3", "teste3@junit.com", null));
		assertThrows(IllegalArgumentException.class, () -> this.fornecedor3 = new Fornecedor(" ", "testando3@junit.com", "987654321"));
		assertThrows(IllegalArgumentException.class, () -> this.fornecedor3 = new Fornecedor("teste3", "", "987654321"));
		assertThrows(IllegalArgumentException.class, () -> this.fornecedor3 = new Fornecedor("teste3", "testando3@junit.com", ""));
	}

	@Test
	void testAdicionaGetProduto() {
		this.fornecedor1.adicionaProduto("produto 1", "eu sou o produto 1", 1.0);
		this.fornecedor2.adicionaProduto("produto 2", "eu sou o produto 2", 2.0);
		assertEquals("produto 1 - eu sou o produto 1 - R$1,00", this.fornecedor1.getProduto("produto 1eu sou o produto 1").toString());
		assertEquals("produto 2 - eu sou o produto 2 - R$2,00", this.fornecedor2.getProduto("produto 2eu sou o produto 2").toString());
	}

	@Test
	void testAddContaGetContasGetNome() {
		assertEquals("teste1", this.fornecedor1.getNome());
		assertEquals("teste2", this.fornecedor2.getNome());
		Conta conta1 = new Conta(this.fornecedor1.getNome(), "Cliente1");
		Conta conta2 = new Conta(this.fornecedor2.getNome(), "Cliente2");
		this.fornecedor1.addConta(conta1);
		this.fornecedor2.addConta(conta2);
		assertTrue(this.fornecedor1.getContas().contains(conta1));
		assertTrue(this.fornecedor2.getContas().contains(conta2));
	}

	@Test
	void testSetEmailToString() {
		assertThrows(NullPointerException.class, () -> this.fornecedor1.setEmail(null));
		assertThrows(IllegalArgumentException.class, () -> this.fornecedor1.setEmail(""));
		this.fornecedor1.setEmail("testandoemail@junit.com");
		assertEquals("teste1 - testandoemail@junit.com - 123456789", this.fornecedor1.toString());
		this.fornecedor2.setEmail("testandoemail@junit.com");
		assertEquals("teste2 - testandoemail@junit.com - 987654321", this.fornecedor2.toString());
	}

	@Test
	void testSetTelefoneToString() {
		assertThrows(NullPointerException.class, () -> this.fornecedor1.setTelefone(null));
		assertThrows(IllegalArgumentException.class, () -> this.fornecedor1.setTelefone(""));
		this.fornecedor1.setTelefone("3333-3333");
		assertEquals("teste1 - teste1@junit.com - 3333-3333", this.fornecedor1.toString());
		this.fornecedor2.setTelefone("3333-3333");
		assertEquals("teste2 - teste2@junit.com - 3333-3333", this.fornecedor2.toString());
	}
	
	@Test
	void testRemoverProduto() {
		this.fornecedor1.adicionaProduto("produto 1", "eu sou o produto 1", 1.0);
		this.fornecedor2.adicionaProduto("produto 2", "eu sou o produto 2", 2.0);
	}
}

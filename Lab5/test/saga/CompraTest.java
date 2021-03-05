package saga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompraTest {
	Compra compra1;
	Compra compra2;
	
	@BeforeEach
	void criaCompra() throws Exception {
		this.compra1 = new Compra("Cliente Teste", "Fornecedor Teste", "Produto", "Descrição do produto", "01/01/2001");
		assertThrows(NullPointerException.class, () -> this.compra2 = new Compra(null, "Fornecedor Teste", "Produto", "Descrição do produto", "01/01/2001"));
		assertThrows(NullPointerException.class, () -> this.compra2 = new Compra("Cliente Teste", null, "Produto", "Descrição do produto", "01/01/2001"));
		assertThrows(NullPointerException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", null, "Descrição do produto", "01/01/2001"));
		assertThrows(NullPointerException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", "Produto", null, "01/01/2001"));
		assertThrows(NullPointerException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", "Produto", "Descrição do produto", null));
		assertThrows(IllegalArgumentException.class, () -> this.compra2 = new Compra("", "Fornecedor Teste", "Produto", "Descrição do produto", "01/01/2001"));
		assertThrows(IllegalArgumentException.class, () -> this.compra2 = new Compra("Cliente Teste", "", "Produto", "Descrição do produto", "01/01/2001"));
		assertThrows(IllegalArgumentException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", "", "Descrição do produto", "01/01/2001"));
		assertThrows(IllegalArgumentException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", "Produto", "", "01/01/2001"));
		assertThrows(IllegalArgumentException.class, () -> this.compra2 = new Compra("Cliente Teste", "Fornecedor Teste", "Produto", "Descrição do produto", ""));
	}

	@Test
	void testGetPorCliente() {
		assertEquals("Cliente Teste, Fornecedor Teste, Descrição do produto, 01/01/2001", this.compra1.getPorCliente());
		assertThrows(NullPointerException.class, () -> this.compra2.getPorCliente());
	}

	@Test
	void testGetPorFornecedor() {
		assertEquals("Fornecedor Teste, Cliente Teste, Descrição do produto, 01/01/2001", this.compra1.getPorFornecedor());
		assertThrows(NullPointerException.class, () -> this.compra2.getPorFornecedor());
	}

	@Test
	void testGetData() {
		assertEquals("01/01/2001, Cliente Teste, Fornecedor Teste, Descrição do produto", this.compra1.getData());
		assertThrows(NullPointerException.class, () -> this.compra2.getData());
	}

	@Test
	void testToString() {
		assertEquals("Produto - 01-01-2001", this.compra1.toString());
		assertThrows(NullPointerException.class, () -> this.compra2.toString());
	}

}

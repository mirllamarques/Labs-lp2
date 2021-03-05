package saga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoTest {
	Produto produto1;
	Produto produto2;
	Produto produto3;

	@BeforeEach
	void criaProduto() {
		this.produto1 = new Produto("teste1", "sabor: 1", 1.0);
		this.produto2 = new Produto("teste2", "sabor: 2", 2.0);
		assertThrows(NullPointerException.class, () -> this.produto3 = new Produto(null, null, 0.0));
		assertThrows(NullPointerException.class, () -> this.produto3 = new Produto(null, "sabor: null", 10.0));
		assertThrows(NullPointerException.class, () -> this.produto3 = new Produto("teste3", null, -1.0));
		assertThrows(IllegalArgumentException.class, () -> this.produto3 = new Produto(" ", "sabor: illegal argument", 2.0));
		assertThrows(IllegalArgumentException.class, () -> this.produto3 = new Produto("teste3", "", 2.0));
		assertThrows(IllegalArgumentException.class, () -> this.produto3 = new Produto("teste3", "sabor: illegal argument", -30.0));
	}
	@Test
	void testGetSetPreco() {
		assertEquals(1.0, this.produto1.getPreco());
		this.produto1.setPreco(10.0);
		assertEquals(10.0, this.produto1.getPreco());
		assertEquals(2.0, this.produto2.getPreco());
		this.produto2.setPreco(20.0);
		assertEquals(20.0, this.produto2.getPreco());
		assertThrows(IllegalArgumentException.class, () -> this.produto1.setPreco(-1.0));
		assertThrows(IllegalArgumentException.class, () -> this.produto2.setPreco(0.0));
		assertThrows(NullPointerException.class, () -> this.produto3.getPreco());
		assertThrows(NullPointerException.class, () -> this.produto3.setPreco(-1.0));
		assertThrows(NullPointerException.class, () -> this.produto3.setPreco(-0.0));
	}

	@Test
	void testToString() {
		assertEquals( "teste1 - sabor: 1 - R$1,00", this.produto1.toString());
		assertEquals( "teste2 - sabor: 2 - R$2,00", this.produto2.toString());
		assertThrows(NullPointerException.class, () -> this.produto3.toString());
	}
}

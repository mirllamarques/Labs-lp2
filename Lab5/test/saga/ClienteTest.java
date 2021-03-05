package saga;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {
	Cliente cliente1;
	Cliente cliente2;
	Cliente cliente3;
	
	@BeforeEach
	void criaCliente() throws Exception {
		this.cliente1 = new Cliente("12345678911", "teste1", "testando1@junit.com", "Central de testes: testador1");
		this.cliente2 = new Cliente("11987654321", "teste2", "testando2@junit.com", "Central de testes: testador2");
		assertThrows(NullPointerException.class, () -> this.cliente3 = new Cliente(null, null, null, null));
		assertThrows(NullPointerException.class, () -> this.cliente3 = new Cliente(null, "teste3", "testando3@junit.com", "Central de testes: testador3"));
		assertThrows(NullPointerException.class, () -> this.cliente3 = new Cliente("11111111111", null, "testando3@junit.com", "Central de testes: testador3"));
		assertThrows(NullPointerException.class, () -> this.cliente3 = new Cliente("11111111111", "teste3", null, "Central de testes: testador3"));
		assertThrows(NullPointerException.class, () -> this.cliente3 = new Cliente("11111111111", "teste3", "testando3@junit.com", null));
		assertThrows(IllegalArgumentException.class, () -> this.cliente3 = new Cliente(" ", "teste3", "testando3@junit.com", "Central de testes: testador3"));
		assertThrows(IllegalArgumentException.class, () -> this.cliente3 = new Cliente("11111111111", "", "testando3@junit.com", "Central de testes: testador3"));
		assertThrows(IllegalArgumentException.class, () -> this.cliente3 = new Cliente("11111111111", "teste3", "", "Central de testes: testador3"));
		assertThrows(IllegalArgumentException.class, () -> this.cliente3 = new Cliente("11111111111", "teste3", "testando3@junit.com", ""));
		assertThrows(IllegalArgumentException.class, () -> this.cliente3 = new Cliente(" ", "  ", " ", " "));
	}
	@Test
	void testAddContaEGetContas() {
		Conta conta1 = new Conta("teste1", "fornecedorTeste");
		Conta conta2 = new Conta("teste2", "fornecedorTeste");
		Conta conta3 = new Conta("teste2", "fornecedorTeste2");
		Conta conta4 = new Conta("teste3", "fornecedorTeste2");
		this.cliente1.addConta(conta1);
		ArrayList<Conta> contaTeste1 = new ArrayList<>();
		contaTeste1.add(conta1);
		assertEquals(contaTeste1, this.cliente1.getContas());
		this.cliente2.addConta(conta2);
		this.cliente2.addConta(conta3);
		ArrayList<Conta> contaTeste2 = new ArrayList<>();
		contaTeste2.add(conta2);
		contaTeste2.add(conta3);
		assertEquals(contaTeste2, this.cliente2.getContas());
		assertThrows(NullPointerException.class, () -> this.cliente3.addConta(conta4));
		assertThrows(NullPointerException.class, () -> this.cliente3.getContas());
	}

	@Test
	void testGetSetNome() {
		assertEquals("teste1", this.cliente1.getNome());
		this.cliente1.setNome("nome1");
		assertEquals("nome1", cliente1.getNome());
		assertEquals("teste2", this.cliente2.getNome());
		this.cliente2.setNome("nome2");
		assertEquals("nome2", this.cliente2.getNome());
		assertThrows(NullPointerException.class, () -> this.cliente3.getNome());
		assertThrows(NullPointerException.class, () -> this.cliente3.setNome("Tudo que eu faço é dar erro :("));
		assertThrows(NullPointerException.class, () -> this.cliente1.setNome(null));
		assertThrows(IllegalArgumentException.class, () -> this.cliente2.setNome(""));
	}

	@Test
	void testSetEmailEToString() {
		assertEquals("teste1 - Central de testes: testador1 - testando1@junit.com", this.cliente1.toString());
		this.cliente1.setEmail("nome1@junit.com");
		assertEquals("teste1 - Central de testes: testador1 - nome1@junit.com", cliente1.toString());
		assertEquals("teste2 - Central de testes: testador2 - testando2@junit.com", this.cliente2.toString());
		this.cliente2.setEmail("nome2@junit.com");
		assertEquals("teste2 - Central de testes: testador2 - nome2@junit.com", this.cliente2.toString());
		assertThrows(NullPointerException.class, () -> this.cliente3.toString());
		assertThrows(NullPointerException.class, () -> this.cliente3.setEmail("null@error.com :("));
	}

	@Test
	void testSetLocalToString() {
		assertEquals("teste1 - Central de testes: testador1 - testando1@junit.com", this.cliente1.toString());
		this.cliente1.setLocal("Central Suporte");
		assertEquals("teste1 - Central Suporte - testando1@junit.com", this.cliente1.toString());
		assertEquals("teste2 - Central de testes: testador2 - testando2@junit.com", this.cliente2.toString());
		this.cliente2.setLocal("Central Dúvidas");
		assertEquals("teste2 - Central Dúvidas - testando2@junit.com", this.cliente2.toString());
		assertThrows(NullPointerException.class, () -> this.cliente3.toString());
		assertThrows(NullPointerException.class, () -> this.cliente3.setLocal("Onde as coisas dão errado :("));
	}

}

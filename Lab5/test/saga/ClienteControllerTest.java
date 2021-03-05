package saga;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteControllerTest {

	ClienteController controller1;
	ClienteController controller2;
	
	@BeforeEach
	void criaClienteController() {
		this.controller1 = new ClienteController();
		this.controller2 = new ClienteController();
	}
	@Test
	void testGetRepoClientesERemover() throws Exception {
		HashMap<String, Cliente> mapa1 = new HashMap<>();
		assertEquals(mapa1, this.controller1.getRepoClientes());
		HashMap<String, Cliente> mapa2 = new HashMap<>();
		assertEquals(mapa2, this.controller1.getRepoClientes());
		this.controller1.cadastro("12345678911", "sou um teste", "teste@junit.com", "Central de Testes");
		Cliente cliente = new Cliente("12345678911", "sou um teste", "teste@junit.com", "Central de Testes");
		mapa1.put("12345678911", cliente);
		assertEquals(mapa1, this.controller1.getRepoClientes());
		mapa1.remove("12345678911");
		this.controller1.remover("12345678911");
		assertEquals(mapa1, this.controller1.getRepoClientes());
		assertThrows(NullPointerException.class, () -> this.controller1.remover(null));
		assertThrows(IllegalArgumentException.class, () -> this.controller1.remover(""));
	}

	@Test
	void testCadastro() throws Exception {
		this.controller1.cadastro("12345678911", "sou um teste", "teste@junit.com", "Central de Testes");
		this.controller2.cadastro("11987654321", "sou outro teste", "outroteste@junit.com", "Central de Testes");
		assertThrows(NullPointerException.class, () -> this.controller1.cadastro(null, "erro", "error.@junit.com", "Null Pointer"));
		assertThrows(IllegalArgumentException.class, () -> this.controller1.cadastro("", "erro", "error.@junit.com", "Null Pointer"));
		assertThrows(Exception.class, () -> this.controller1.cadastro("123", "erro", "error.@junit.com", "Null Pointer"));
		assertThrows(Exception.class, () -> this.controller1.cadastro("12345678911", "erro", "error.@junit.com", "Illegal Argument"));
	}
	
	@Test
	void testUpdateNomeEToString() throws Exception {
		this.controller1.cadastro("12345678911", "sou um teste", "teste@junit.com", "Central de Testes");
		assertEquals("sou um teste - Central de Testes - teste@junit.com", this.controller1.toString("12345678911"));
		this.controller2.cadastro("11987654321", "sou outro teste", "outroteste@junit.com", "Central de Testes");
		assertEquals("sou outro teste - Central de Testes - outroteste@junit.com", this.controller2.toString("11987654321"));
	}
	
	@Test
	void testChange() {
		assertThrows(NullPointerException.class, () -> this.controller1.change(null, "erro", "Null Pointer"));
		assertThrows(IllegalArgumentException.class, () -> this.controller1.change("", "erro", "Illegal Argument"));
		assertThrows(Exception.class, () -> this.controller1.change("123", "erro", "CPF Inválido"));
		assertThrows(Exception.class, () -> this.controller1.change("01234567891", "erro", " CPF Inexistente"));
		assertThrows(Exception.class, () -> this.controller1.change("12345678911", null, "Null Pointer"));
		assertThrows(Exception.class, () -> this.controller1.change("12345678911", "", "Illegal Argument"));
		assertThrows(Exception.class, () -> this.controller1.change("12345678911", "cpf", "CPF Não Pode Editar"));
		assertThrows(Exception.class, () -> this.controller1.change("12345678911", "erro", " Atributo Inexistente"));
	}
	
	@Test
	void testImprimeTodos() throws Exception {
		this.controller1.cadastro("12345678911", "sou um teste", "teste@junit.com", "Central de Testes");
		this.controller1.cadastro("11987654321", "sou outro teste", "outroteste@junit.com", "Central de Testes");
		assertEquals("sou outro teste - Central de Testes - outroteste@junit.com | sou um teste - Central de Testes - teste@junit.com", this.controller1.imprimeTodos());
		this.controller2.cadastro("00000000000", "bbbbbbbb", "teste@junit.com", "Central de Testes");
		this.controller2.cadastro("11111111111", "aaaaaaaa", "outroteste@junit.com", "Central de Testes");
		assertEquals("aaaaaaaa - Central de Testes - outroteste@junit.com | bbbbbbbb - Central de Testes - teste@junit.com", this.controller2.imprimeTodos());

	}

}

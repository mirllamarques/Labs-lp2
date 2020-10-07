import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Agenda;

class AgendaTest {
	
	private Agenda agenda;
	
	@BeforeEach
	public void criaAgenda() {
		this.agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudêncio", "2101-0000", "", "", "",""); 
		agenda.cadastraContato(2, "Thais", "Araújo", "0000-0001", "(00) 0000-0000", "0000-0007", "2", "3");
		agenda.cadastraContato(100,"Boneco", "Assasino", "3110-0500", "5494-9351", "1229-8888", "3", "1");
	}
	@Test
	void testGetContatos() {
		String msg = "Esperando garantir que o arraylist não seja nulo";
		ArrayList<String> contatos = agenda.getContatos();
		assertNotEquals(null,contatos.get(0), msg);
		assertNotEquals(null,contatos.get(1), msg);
	}

	@Test
	void testGetPrioritarios() {
		String msg = "Esperando garantir que o arraylist não seja nulo";
		ArrayList<String> prioritarios = agenda.getPrioritarios();
		assertNotEquals(null,prioritarios.get(0), msg);
		assertNotEquals(null,prioritarios.get(1), msg);
	}

	@Test
	void testGetZaps() {
		String msg = "Esperando garantir que o arraylist não seja nulo";
		ArrayList<String> zaps = agenda.getPrioritarios();
		assertNotEquals(null,zaps.get(0), msg);
		assertNotEquals(null,zaps.get(1), msg);
	}

	@Test
	void testGetContato() {
		String msg = "Esperando receber nome completo, número prioritário e zap(se houver)\n";
		assertEquals("Matheus Gaudêncio\n2101-0000 (prioritário)\nNão tem (zap)\n", agenda.getContato(0), msg);
		assertEquals("Thais Araújo\n(00) 0000-0000 (prioritário)\n0000-0007 (zap)\n", agenda.getContato(1), msg);
		assertThrows(IndexOutOfBoundsException.class, () -> agenda.getContato(100));
	}

	@Test
	void testCadastraContato() {
		String msg = "Esperado receber um objeto";
		assertEquals("Thais Araújo\n(00) 0000-0000 (prioritário)\n0000-0007 (zap)\n", agenda.getContato(1), msg);
		agenda.cadastraContato(2,"Freddy", "Krueger", "+1 (595) 5555-1234", "", "", "1", "1");
		assertEquals("Freddy Krueger\n+1 (595) 5555-1234 (prioritário)\n+1 (595) 5555-1234 (zap)\n", agenda.getContato(1), msg);
		ArrayList<String> contatos = agenda.getContatos();
		assertThrows(IndexOutOfBoundsException.class, () -> contatos.get(100), msg);	
	}

}

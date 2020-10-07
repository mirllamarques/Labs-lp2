import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Contato;

class ContatoTest {
	
	private Contato contatoTeste1;
	private Contato contatoTeste2;
	private Contato contatoTeste3;
	private Contato contatoTeste4;
	private Contato contatoTeste5;
	private Contato contatoTeste6;
	private Contato contatoTeste7;
	@BeforeEach
	public void criaContato() {
		this.contatoTeste1 = new Contato(1, "Matheus", "Gaudêncio", "2101-0000", "", "", "",""); 
		this.contatoTeste2 = new Contato(100, "Camila", "Pitanga", "4321-5678", "33106000", "", "1", "2");
		this.contatoTeste3 = new Contato(11, "Thais", "Araújo", "0000-0001", "(00) 0000-0000", "0000-0007", "2", "3");
		this.contatoTeste4 = new Contato(20,"Bom", "Dia", "4002-8922", "9999-9090", "", "1", "2");
		this.contatoTeste5 = new Contato(55,"Freddy", "Krueger", "+1 (595) 5555-1234", "", "", "1", "1");
		this.contatoTeste6 = new Contato(30,"Boneco", "Assasino", "3110-0500", "5494-9351", "1229-8888", "3", "1");
		assertThrows(NullPointerException.class, () -> this.contatoTeste7 = new Contato(1, null,null, null, null, null, null, null));
		assertThrows(NullPointerException.class, () -> this.contatoTeste7 = new Contato(1,"Matheus", null, null, null, null, null, null));
		assertThrows(IllegalArgumentException.class, () -> this.contatoTeste7 = new Contato(1, "    ", "Gaudêncio", "2101-0000", "", "", "",""));
		assertThrows(IllegalArgumentException.class, () -> this.contatoTeste7 = new Contato(1,"Matheus",  "    ", "2101-0000", "", "", "",""));
		assertThrows(IllegalArgumentException.class, () -> this.contatoTeste7 = new Contato(1,"Matheus", "Gaudêncio", "", "", "", "",""));
		assertThrows(IllegalArgumentException.class, () -> this.contatoTeste7 = new Contato(1,"", "", "", "", "", "",""));

	}

	@Test
	void testGetPrioritario() {
		String msg = "Esperado receber o nome e telefone cadastrado como prioridade ou, por padrão, o único cadastrado";
		assertEquals("Matheus Gaudêncio - 2101-0000",this.contatoTeste1.getPrioritario(), msg);
		assertEquals("Camila Pitanga - 4321-5678",this.contatoTeste2.getPrioritario(), msg);
		assertEquals("Thais Araújo - (00) 0000-0000",this.contatoTeste3.getPrioritario(), msg);
		assertEquals("Bom Dia - 4002-8922",this.contatoTeste4.getPrioritario(), msg);
		assertEquals("Freddy Krueger - +1 (595) 5555-1234",this.contatoTeste5.getPrioritario(), msg);
		assertEquals("Boneco Assasino - 1229-8888",this.contatoTeste6.getPrioritario(), msg);
	}

	@Test
	void testGetZap() {
		String msg = "Esperado receber o nome e o zap ou a mensagem 'Não tem'";
		assertEquals("Matheus Gaudêncio - Não tem",this.contatoTeste1.getZap(), msg);
		assertEquals("Camila Pitanga - 33106000",this.contatoTeste2.getZap(), msg);
		assertEquals("Thais Araújo - 0000-0007",this.contatoTeste3.getZap(), msg);
		assertEquals("Bom Dia - 9999-9090",this.contatoTeste4.getZap(), msg);
		assertEquals("Freddy Krueger - +1 (595) 5555-1234",this.contatoTeste5.getZap(), msg);
		assertEquals("Boneco Assasino - 3110-0500",this.contatoTeste6.getZap(), msg);
	}

	void testExibirContato() {
		String msg = "Esperado receber a formatação posição do contato e nome completo para futura listagem de contatos";
		assertEquals("1 - Matheus Gaudêncio" ,this.contatoTeste1.exibirContato(), msg);
		assertEquals("100 - Camila Pitanga",this.contatoTeste2.exibirContato(), msg);
		assertEquals("11 - Thais Araújo",this.contatoTeste3.exibirContato(), msg);
		assertEquals("20 - Bom Dia",this.contatoTeste4.exibirContato(), msg);
		assertEquals("55- Freddy Krueger",this.contatoTeste5.exibirContato(), msg);
		assertEquals("30 - Boneco Assasino",this.contatoTeste6.exibirContato(), msg);
	}
	
	@Test
	void testToString() {
		String msg = "Esperado receber nome completo, número prioritário e zap(se houver)\n";
		assertEquals("Matheus Gaudêncio\n2101-0000 (prioritário)\nNão tem (zap)\n" ,this.contatoTeste1.toString(), msg);
		assertEquals("Camila Pitanga\n4321-5678 (prioritário)\n33106000 (zap)\n" ,this.contatoTeste2.toString(), msg);
		assertEquals("Thais Araújo\n(00) 0000-0000 (prioritário)\n0000-0007 (zap)\n" ,this.contatoTeste3.toString(), msg);
		assertEquals("Bom Dia\n4002-8922 (prioritário)\n9999-9090 (zap)\n" ,this.contatoTeste4.toString(), msg);
		assertEquals("Freddy Krueger\n+1 (595) 5555-1234 (prioritário)\n+1 (595) 5555-1234 (zap)\n" ,this.contatoTeste5.toString(), msg);
		assertEquals("Boneco Assasino\n1229-8888 (prioritário)\n3110-0500 (zap)\n" ,this.contatoTeste6.toString(), msg);
	}

}

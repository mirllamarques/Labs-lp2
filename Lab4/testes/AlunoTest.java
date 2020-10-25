import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ControleAluno.Aluno;

class AlunoTest {
	
	private Aluno aluno1;
	private Aluno aluno2;
	private Aluno aluno3;
	
	@BeforeEach
	public void criaAluno() {
		this.aluno1 = new Aluno("100","Winston Churchill","Ciência Política");
		this.aluno2 = new Aluno("200","Bettina Rudolph","Empreendedorismo");
		this.aluno3 = new Aluno("300","Nazaré Tedesco","Enfermagem");
	}

	@Test
	void testToString() {
		assertEquals("100 - Winston Churchill - Ciência Política", this.aluno1.toString());
		assertEquals("200 - Bettina Rudolph - Empreendedorismo", this.aluno2.toString());
		assertEquals("300 - Nazaré Tedesco - Enfermagem", this.aluno3.toString());
	}

}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ControleAluno.Aluno;
import ControleAluno.Grupo;

class GrupoTest {
	
	private Aluno aluno1;
	private Aluno aluno2;
	private Aluno aluno3;
	private Grupo grupo1;
	private Grupo grupo2;

	@BeforeEach
	
	public void criaGrupo() {
		this.aluno1 = new Aluno("100","Winston Churchill","Ciência Política");
		this.aluno2 = new Aluno("200","Bettina Rudolph","Empreendedorismo");
		this.aluno3 = new Aluno("300","Nazaré Tedesco","Enfermagem");
		this.grupo1 = new Grupo("Famosos");
		this.grupo2 = new Grupo("Sequestradores");
	}

	@Test
	void testGetAlunoEAdicionaAluno() {
		this.grupo1.adicionaAluno(aluno1, "100");
		this.grupo1.adicionaAluno(aluno2, "200");
		this.grupo1.adicionaAluno(aluno3, "300");
		this.grupo2.adicionaAluno(aluno3, "300");
		assertEquals(this.aluno1.toString(), this.grupo1.getAluno("100"));
		assertEquals(this.aluno2.toString(), this.grupo1.getAluno("200"));
		assertEquals(this.aluno3.toString(), this.grupo1.getAluno("300"));
		assertEquals(this.aluno3.toString(), this.grupo2.getAluno("300"));
	}

	@Test
	void testGetNome() {
		assertEquals("Famosos", this.grupo1.getNome());
		assertEquals("Sequestradores", this.grupo2.getNome());
	}
	

}

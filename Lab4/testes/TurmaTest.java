import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ControleAluno.Aluno;
import ControleAluno.Turma;

class TurmaTest {
	
	private Aluno aluno1;
	private Aluno aluno2;
	private Aluno aluno3;
	private Turma turma;
	
	@BeforeEach
	public void criaTurma() {
		this.aluno1 = new Aluno("100","Winston Churchill","Ciência Política");
		this.aluno2 = new Aluno("200","Bettina Rudolph","Empreendedorismo");
		this.aluno3 = new Aluno("300","Nazaré Tedesco","Enfermagem");
		this.turma = new Turma();
		this.turma.cadastraAluno("100", "Winston Churchill", "Ciência Política");
		this.turma.cadastraAluno("200","Bettina Rudolph","Empreendedorismo");
		this.turma.cadastraAluno("300","Nazaré Tedesco","Enfermagem");
	}

	@Test
	void testCadastraAlunoEAlunoToString() {
		assertEquals(aluno1.toString(), turma.alunoToString("100"));
		assertEquals(aluno2.toString(), turma.alunoToString("200"));
		assertEquals(aluno3.toString(), turma.alunoToString("300"));
	}

	@Test
	void testAlunoMatriculado() {
		assertTrue(turma.alunoMatriculado("100"));
		assertTrue(turma.alunoMatriculado("200"));
		assertTrue(turma.alunoMatriculado("300"));
		assertFalse(turma.alunoMatriculado("400"));
	}

	@Test
	void testCadastraGrupoEGrupoCadastrado() {
		this.turma.cadastraGrupo("Famosos");
		this.turma.cadastraGrupo("Sequestradores");
		assertTrue(turma.grupoCadastrado("FAMOSOS"));
		assertTrue(turma.grupoCadastrado("SEQUESTRADORES"));
		assertFalse(turma.grupoCadastrado("RICOS"));
	}

	@Test
	void testVerificaGrupo() {
		this.turma.cadastraGrupo("Famosos");
		this.turma.cadastraGrupo("Sequestradores");
		assertTrue(turma.verificaGrupo("100", "FAMOSOS"));
		assertTrue(turma.verificaGrupo("100", "SEQUESTRADORES"));
	}

	@Test
	void testAlocaAluno() {
		this.turma.cadastraGrupo("Famosos");
		this.turma.cadastraGrupo("Sequestradores");
		this.turma.alocaAluno("100", "FAMOSOS");
		this.turma.alocaAluno("200", "FAMOSOS");
		this.turma.alocaAluno("300", "FAMOSOS");
		this.turma.alocaAluno("300", "SEQUESTRADORES");
		assertFalse(turma.verificaGrupo("100", "FAMOSOS"));
		assertFalse(turma.verificaGrupo("200", "FAMOSOS"));
		assertFalse(turma.verificaGrupo("300", "FAMOSOS"));
		assertTrue(turma.verificaGrupo("100", "SEQUESTRADORES"));
		assertFalse(turma.verificaGrupo("300", "SEQUESTRADORES"));
	}

	@Test
	void testGrupoToString() {
		this.turma.cadastraGrupo("Famosos");
		this.turma.cadastraGrupo("Sequestradores");
		this.turma.alocaAluno("100", "FAMOSOS");
		this.turma.alocaAluno("200", "FAMOSOS");
		this.turma.alocaAluno("300", "FAMOSOS");
		this.turma.alocaAluno("300", "SEQUESTRADORES");
		ArrayList<String> grupo1 = this.turma.grupoToString("FAMOSOS");
		ArrayList<String> grupo2 = this.turma.grupoToString("SEQUESTRADORES");
		assertEquals("Alunos do grupo Famosos:", grupo1.get(0));
		assertEquals("* 100 - Winston Churchill - Ciência Política", grupo1.get(1));
        assertEquals("* 200 - Bettina Rudolph - Empreendedorismo", grupo1.get(2));
        assertEquals("* 300 - Nazaré Tedesco - Enfermagem", grupo1.get(3));
        assertEquals("Alunos do grupo Sequestradores:", grupo2.get(0));
        assertEquals("* 300 - Nazaré Tedesco - Enfermagem", grupo2.get(1));
	}

	@Test
	void testRegistraAlunoEResponderamQuadro() {
		this.turma.registraAluno("100");
		this.turma.registraAluno("200");
		this.turma.registraAluno("100");
		this.turma.registraAluno("300");
		ArrayList<String> aluno = this.turma.responderamQuadro();
		assertEquals("100 - Winston Churchill - Ciência Política", aluno.get(0));
		assertEquals("200 - Bettina Rudolph - Empreendedorismo", aluno.get(1));
		assertEquals("100 - Winston Churchill - Ciência Política", aluno.get(2));
        assertEquals("300 - Nazaré Tedesco - Enfermagem", aluno.get(3));

	}

}

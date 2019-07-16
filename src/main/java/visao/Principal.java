package visao;

import java.util.Date;

import modelo.Coordenador;
import modelo.Curso;
import modelo.Disciplina;
import modelo.Horario;
import modelo.Professor;
import modelo.Sala;
import modelo.Sexo;
import modelo.Solicitacao;
import service.CoordenadorService;
import service.CursoService;
import service.DisciplinaService;
import service.HorarioService;
import service.ProfessorService;
import service.SalaService;

public class Principal {

	public static void main(String[] args) {
		
		// TESTE DE COORDENADOR E CURSO:
		
		/*CoordenadorService s = new CoordenadorService();
		CursoService scur = new CursoService();
		
		Curso curso = new Curso();
		curso.setN_periodos(10);
		curso.setNome("Ciencia da computacao");
		
		Coordenador c = new Coordenador();
		c.setCpf("000.000.000-00");
		c.setNome("Testeroni");
		c.setSexo(Sexo.MASCULINO);
		c.setData_nascimento(new Date());
		c.setEndereco("Endereco");
		c.setSiape("siape");
		c.setLogin("login");
		c.setSenha("senha");
		c.setCurso(curso);
		
		curso.setCoordenador(c);
		scur.salvar(curso);*/
		
		
		// TESTE DE PROFESSOR:
		/*ProfessorService pservice = new ProfessorService();
		Professor c = new Professor();
		c.setCpf("000.000.000-00");
		c.setNome("Testeroni");
		c.setSexo(Sexo.MASCULINO);
		c.setData_nascimento(new Date());
		c.setEndereco("Endereco");
		c.setSiape("siape");
		c.setLogin("login1");
		c.setSenha("senha");
		pservice.salvar(c);*/
		
		
		// TESTE DE SALA:
		/*SalaService salas = new SalaService();
		Sala sa = new Sala();
		sa.setCapacidade(100);
		sa.setNome("Sala 01");
		sa.setTipo("seinao");
		
		HorarioService hs = new HorarioService();
		Horario h = new Horario();
		h.setDia(new Date());
		h.setFim(new Date());
		h.setInicio(new Date());

		sa.addHorario(h);
		salas.salvar(sa);*/
		
		
		// TESTE DISCIPLINAS E CURSOS:
		/*CursoService scur = new CursoService();
		Curso curso = new Curso();
		curso.setN_periodos(10);
		curso.setNome("Ciencia da computacao");
		
		DisciplinaService dser = new DisciplinaService();
		Disciplina dis = new Disciplina();
		dis.setAno(2018);
		dis.setCarga_horaria(60);
		dis.setNome("Pesadelo");
		dis.setTipo("Presencial");
		dis.setTipo_sala_requerida("Inferno");
		
		

		Curso curso = scur.buscarPorCodigo(3);
		dis.addCurso(curso);
		dser.salvar(dis);*/
		
		Solicitacao s = new Solicitacao();
		if(s == null)
			System.out.println("NULLLLL");
		else {
			s.setNome("HMm");
			System.out.println(s.getNome());
		}
	}

}

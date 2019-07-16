package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import modelo.Disciplina;
import modelo.Horario;
import modelo.Periodo;
import modelo.Professor;
import modelo.Turma;
import service.DisciplinaService;
import service.HorarioService;
import service.PeriodoService;
import service.ProfessorService;
import service.TurmaService;
import util.FacesMensagens;

@ManagedBean(name="turma")
@SessionScoped
public class TurmaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Turma obj = new Turma();
	private List<Turma> turmas;
	private TurmaService service = new TurmaService();
	
	private ProfessorService professorService = new ProfessorService();
	private PeriodoService periodoService = new PeriodoService();
	private DisciplinaService disciplinaService = new DisciplinaService();
	private HorarioService horarioService = new HorarioService();
	
	private List<Professor> professores;
	private List<Periodo> periodos;
	private List<Disciplina> disciplinas;
	private List<Horario> horarios;
	
	public TurmaBean() {
		setTurmas(service.buscarTodos());
		setProfessores(professorService.buscarTodos());
		setPeriodos(periodoService.buscarTodos());
		setDisciplinas(disciplinaService.buscarTodos());
		setHorarios(horarioService.buscarTodos());
	}
	
	public void salvar() {
		try{
			service.salvar(obj);
			setTurmas(service.buscarTodos()); 
			
			FacesMensagens.info("Registro salvo com sucesso.");
			limpar();
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public void excluir() {
		try{
			service.remover(obj.getCodigo());
			setTurmas(service.buscarTodos()); 
		
			FacesMensagens.info("Registro excluído com sucesso.");
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public String editar() {
		return "cadastro_turma?faces-redirect=true";
	}
	
	public String novo() {
		limpar();
		return "cadastro_turma?faces-redirect=true";
	}
	
	private void limpar() {
		obj = new Turma();
	}
	
	public void preRender(ComponentSystemEvent e) {
		setTurmas(service.buscarTodos());
		setProfessores(professorService.buscarTodos());
		setPeriodos(periodoService.buscarTodos());
		setDisciplinas(disciplinaService.buscarTodos());
		setHorarios(horarioService.buscarTodos());
	}

	public Turma getObj() {
		return obj;
	}

	public void setObj(Turma obj) {
		this.obj = obj;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

}

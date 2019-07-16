package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Curso;
import modelo.Horario;
import modelo.Professor;
import modelo.Sala;
import modelo.Turma;
import service.CursoService;
import service.HorarioService;
import service.PeriodoService;
import service.SalaService;
import service.TurmaService;
import util.FacesMensagens;
import util.NegocioException;

@ManagedBean(name = "horario")
@ViewScoped
public class HorarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Curso> listaCursos;
	private CursoService cursoService = new CursoService();
	private Curso cursoSelecionado;

	private List<Integer> listaAnos;
	private Integer anoSelecionado;
	private PeriodoService periodoService = new PeriodoService();

	private List<Integer> listaSemestre;
	private Integer semestreSelecionado;

	private List<String> ListnomePeriodo;
	private String nomePeriodoSelecionado;

	private SalaService salaService = new SalaService();
	private List<Sala> listaSalas;
	private Sala salaSelecionada;

	private Turma turmaSelecionada;
	private List<Turma> listaTurma;
	private TurmaService turmaService = new TurmaService();

	private Horario horario = new Horario();
	private List<Horario> listaHorario;
	private HorarioService horarioService = new HorarioService();
	private FiltroHorario filtroHorario;
	private TabelaHorario tabelaHorario;

	private TabelaHorario tabelaHorarioSala;
	private TabelaHorario tabelaHorarioProfessor;
	private TabelaHorario tabelaHorarioTurma;

	public HorarioBean() {
		this.setListaCursos(this.cursoService.buscarTodos());
		this.setListaHorario(this.horarioService.buscarTodos());
		this.setTabelaHorario(new TabelaHorario(this.getListaHorario()));

		this.setTabelaHorarioSala(new TabelaHorario());
		this.setTabelaHorarioProfessor(new TabelaHorario());
		this.setTabelaHorarioTurma(new TabelaHorario());

		this.filtroHorario = new FiltroHorario();
	}

	public void salvarHorario() {
		horarioService.salvar(this.horario);
		FacesMensagens.info("Registro salvo");
		limpar();
	}

	private void limpar() {
		horario = new Horario();
	}

	public void cadastrarHorario() {
		setListaSalas(salaService.buscarTodos());
		try {
			setListaTurma(turmaService.buscarTurma(semestreSelecionado, cursoSelecionado, anoSelecionado));
		} catch (NegocioException e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public void changeCurso() {
		this.listaSemestre = null;
		this.listaAnos = null;

		if (cursoSelecionado != null && !cursoSelecionado.equals("")) {
			try {
				this.listaAnos = periodoService.buscarAno(cursoSelecionado);
			} catch (NegocioException e) {
				FacesMensagens.error(e.getMessage());
			}
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, null, null, null, null, null, null);
		} else {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, null, null, null);
			this.listaAnos = null;
			this.listaSemestre = null;
		}
		this.filtraTabela(this.tabelaHorario, this.filtroHorario);
	}

	public void changeSala() {
		if (this.salaSelecionada != null && !this.salaSelecionada.equals("")) {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, this.horario.getSala(), null, null);
		} else {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, this.horario.getSala(), null, null);
		}
		this.filtraTabela(this.tabelaHorarioSala, this.filtroHorario);
	}
	
	public void changeTurma() {
		System.out.println("turma: " + this.horario.getTurma().toString());
		if (this.turmaSelecionada != null && !this.turmaSelecionada.equals("")) {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, null, this.horario.getTurma(), null);
		} else {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, null, this.horario.getTurma(), null);
		}
		this.filtraTabela(this.tabelaHorarioTurma, this.filtroHorario);
		
		Professor prof = this.horario.getTurma().getProfessor();
		if (prof != null && prof.equals("")) {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, null, null, prof);
		} else {
			this.changeFiltroHorarios(this.filtroHorario, null, null, null, null, null, null, prof);
		}
		this.filtraTabela(this.tabelaHorarioProfessor, this.filtroHorario);
	}

	public void changeAno() {
		this.listaSemestre = null;

		if (anoSelecionado != null && !anoSelecionado.equals("")) {
			try {
				this.listaSemestre = periodoService.buscarSemestres(cursoSelecionado, anoSelecionado);
			} catch (NegocioException e) {
				FacesMensagens.error(e.getMessage());
			}
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, anoSelecionado, null, null, null, null,
					null);
		} else {
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, null, null, null, null, null, null);
			this.listaSemestre = null;
			this.listaAnos = null;
		}
		this.filtraTabela(this.tabelaHorario, this.filtroHorario);
	}

	public void changeSemestre() {

		if (semestreSelecionado != null && !semestreSelecionado.equals("")) {
			try {
				this.ListnomePeriodo = periodoService.buscarNomes(this.cursoSelecionado, this.semestreSelecionado,
						this.anoSelecionado);
			} catch (NegocioException e) {
				FacesMensagens.error(e.getMessage());
			}
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, anoSelecionado, semestreSelecionado, null,
					null, null, null);

		} else {
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, anoSelecionado, null, null, null, null,
					null);
		}
		this.filtraTabela(this.tabelaHorario, this.filtroHorario);
	}

	public void changeNomePeriodo() {
		if (nomePeriodoSelecionado != null && !nomePeriodoSelecionado.equals("")) {
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, anoSelecionado, semestreSelecionado,
					nomePeriodoSelecionado, null, null, null);

		} else {
			this.changeFiltroHorarios(this.filtroHorario, cursoSelecionado, anoSelecionado, null, null, null, null,
					null);
		}
		this.filtraTabela(this.tabelaHorario, this.filtroHorario);
	}

	public void changeFiltroHorarios(FiltroHorario filter, Curso curso, Integer ano, Integer semestre,
			String nomePeriodo, Sala sala, Turma turma, Professor professor) {
		filter.setCurso(curso);
		filter.setAno(ano);
		filter.setSemestre(semestre);
		filter.setNomePeriodo(nomePeriodo);
		filter.setSala(sala);
		filter.setTurma(turma);
		filter.setProfessor(professor);
	}

	public void filtraTabela(TabelaHorario tabela, FiltroHorario filter) {
		tabela.setCelulasWithHorarios(this.horarioService.filtraTabelaHorarios(filter));
	}

	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public List<Integer> getListaAnos() {
		return listaAnos;
	}

	public void setListaAnos(List<Integer> listaAnos) {
		this.listaAnos = listaAnos;
	}

	public Integer getAnoSelecionado() {
		return anoSelecionado;
	}

	public void setAnoSelecionado(Integer anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}

	public PeriodoService getPeriodoService() {
		return periodoService;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public List<Integer> getListaSemestre() {
		return listaSemestre;
	}

	public void setListaSemestre(List<Integer> listaSemestre) {
		this.listaSemestre = listaSemestre;
	}

	public Integer getSemestreSelecionado() {
		return semestreSelecionado;
	}

	public void setSemestreSelecionado(Integer semestreSelecionado) {
		this.semestreSelecionado = semestreSelecionado;
	}

	public List<Sala> getListaSalas() {
		return listaSalas;
	}

	public void setListaSalas(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}

	public SalaService getSalaService() {
		return salaService;
	}

	public void setSalaService(SalaService salaService) {
		this.salaService = salaService;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<Turma> getListaTurma() {
		return listaTurma;
	}

	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}

	public TurmaService getTurmaService() {
		return turmaService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public HorarioService getHorarioService() {
		return horarioService;
	}

	public void setHorarioService(HorarioService horarioService) {
		this.horarioService = horarioService;
	}

	public List<Horario> getListaHorario() {
		return listaHorario;
	}

	public void setListaHorario(List<Horario> listaHorario) {
		this.listaHorario = listaHorario;
	}

	public FiltroHorario getFiltroHorario() {
		return filtroHorario;
	}

	public void setFiltroHorario(FiltroHorario filtroHorario) {
		this.filtroHorario = filtroHorario;
	}

	public TabelaHorario getTabelaHorario() {
		return tabelaHorario;
	}

	public void setTabelaHorario(TabelaHorario tabelaHorario) {
		this.tabelaHorario = tabelaHorario;
	}

	public TabelaHorario getTabelaHorarioSala() {
		return tabelaHorarioSala;
	}

	public void setTabelaHorarioSala(TabelaHorario tabelaHorarioSala) {
		this.tabelaHorarioSala = tabelaHorarioSala;
	}

	public TabelaHorario getTabelaHorarioProfessor() {
		return tabelaHorarioProfessor;
	}

	public void setTabelaHorarioProfessor(TabelaHorario tabelaHorarioProfessor) {
		this.tabelaHorarioProfessor = tabelaHorarioProfessor;
	}

	public TabelaHorario getTabelaHorarioTurma() {
		return tabelaHorarioTurma;
	}

	public void setTabelaHorarioTurma(TabelaHorario tabelaHorarioTurma) {
		this.tabelaHorarioTurma = tabelaHorarioTurma;
	}

	public Sala getSalaSelecionada() {
		return salaSelecionada;
	}

	public void setSalaSelecionada(Sala salaSelecionada) {
		this.salaSelecionada = salaSelecionada;
	}

	public List<String> getListnomePeriodo() {
		return ListnomePeriodo;
	}

	public void setListnomePeriodo(List<String> listnomePeriodo) {
		ListnomePeriodo = listnomePeriodo;
	}

	public String getNomePeriodoSelecionado() {
		return nomePeriodoSelecionado;
	}

	public void setNomePeriodoSelecionado(String nomePeriodoSelecionado) {
		this.nomePeriodoSelecionado = nomePeriodoSelecionado;
	}

}

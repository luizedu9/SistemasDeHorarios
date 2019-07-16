package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import modelo.Curso;
import modelo.Disciplina;
import modelo.Solicitacao;
import modelo.SolicitacaoStatus;
import modelo.TipoDisciplina;
import modelo.TipoSala;
import service.CursoService;
import service.DisciplinaService;
import service.SolicitacaoService;
import util.FacesMensagens;

@ManagedBean(name="disciplina")
@SessionScoped
public class DisciplinaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Disciplina obj = new Disciplina();
	private List<Disciplina> disciplinas;
	private DisciplinaService service = new DisciplinaService();
	
	private List<Curso> cursos;
	private List<Curso> selecionados;
	private CursoService cService = new CursoService();
	
	private List<Solicitacao> solicitacoes;
	private Solicitacao solicitacao;
	private SolicitacaoService solService = new SolicitacaoService();
	
	public DisciplinaBean() {
		setDisciplinas(service.buscarTodos());
		setCursos(cService.buscarTodos());
		setSolicitacoes(solService.buscarPendentes());
	}
	
	public void salvar() {
		try{
			// Atende a uma solicitacao:
			if(solicitacao != null) {
				solicitacao.setStatus(SolicitacaoStatus.REGISTRADA);
				solService.salvar(solicitacao);
				setSolicitacoes(solService.buscarPendentes());
			}
			
			service.salvar(obj);
			setDisciplinas(service.buscarTodos()); 
			
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
			setDisciplinas(service.buscarTodos()); 
		
			FacesMensagens.info("Registro excluído com sucesso.");
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public String editar() {
		return "cadastro_disciplina?faces-redirect=true";
	}
	
	public String novo() {
		limpar();
		return "cadastro_disciplina?faces-redirect=true";
	}
	
	private void limpar() {
		obj = new Disciplina();
	}
	
	public void preRender(ComponentSystemEvent e) {
		setDisciplinas(service.buscarTodos());
		setCursos(cService.buscarTodos());
		setSolicitacoes(solService.buscarPendentes());
	}

	public Disciplina getObj() {
		return obj;
	}
	
	public TipoDisciplina[] getTipos() {
		return TipoDisciplina.values();
	}
	
	public TipoSala[] getTipoSalas() {
		return TipoSala.values();
	}

	public void setObj(Disciplina obj) {
		this.obj = obj;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Curso> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Curso> selecionados) {
		this.selecionados = selecionados;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	
}

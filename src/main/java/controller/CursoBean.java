package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import modelo.Coordenador;
import modelo.Curso;
import service.CoordenadorService;
import service.CursoService;
import util.FacesMensagens;

@ManagedBean(name="curso")
@SessionScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Curso obj = new Curso();
	private List<Curso> cursos;
	private CursoService service = new CursoService();
	
	private CoordenadorService coordService = new CoordenadorService();
	private List<Coordenador> coordenadores;
	
	public CursoBean() {
		setCursos(service.buscarTodos());
		setCoordenadores(coordService.buscarTodos());
	}
	
	public void salvar() {
		try{	
			service.salvar(obj);
			setCursos(service.buscarTodos()); 
			
			Coordenador coordenador = null;
			for(Curso cursoi: cursos) {
				if (cursoi.getNome().equals(obj.getNome())) {
					coordenador = obj.getCoordenador();
					coordenador.setCodigoCurso(cursoi.getCodigo());
				}
			}
			System.out.println("AHHHHHHHHHHHHHHHHHHHHH" + coordenador.getNome() + coordenador.getCodigoCurso());
			coordService.salvar(coordenador);
			
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
			setCursos(service.buscarTodos()); 
		
			FacesMensagens.info("Registro excluído com sucesso.");
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public String editar() {
		return "cadastro_curso?faces-redirect=true";
	}
	
	public String novo() {
		limpar();
		return "cadastro_curso?faces-redirect=true";
	}
	
	private void limpar() {
		obj = new Curso();
	}

	public void preRender(ComponentSystemEvent e) {
		setCursos(service.buscarTodos());
		setCoordenadores(coordService.buscarTodos());
	}
	
	public Curso getObj() {
		return obj;
	}

	public void setObj(Curso obj) {
		this.obj = obj;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Coordenador> getCoordenadores() {
		return coordenadores;
	}

	public void setCoordenadores(List<Coordenador> coordenadores) {
		this.coordenadores = coordenadores;
	}

}

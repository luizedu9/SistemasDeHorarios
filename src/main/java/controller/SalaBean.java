package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import util.FacesMensagens;
import modelo.Sala;
import modelo.TipoSala;
import service.SalaService;

@ManagedBean(name="sala")
@SessionScoped
public class SalaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Sala obj = new Sala();
	private List<Sala> salas; 
	private SalaService service = new SalaService();
	
	public SalaBean() {
		setSalas(service.buscarTodos());
	}
	
	public void salvar() {
		try{
			service.salvar(obj);
			setSalas(service.buscarTodos()); 
			
			FacesMensagens.info("Registro salvo com sucesso.");
			limpa();
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public void excluir() {
		try{
			service.remover(obj.getCodigo());
			setSalas(service.buscarTodos()); 
		
			FacesMensagens.info("Registro excluído com sucesso.");
		}
		catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
	
	public String editar() {
		return "cadastro_sala?faces-redirect=true";
	}
	
	public String novo() {
		obj = new Sala();
		return  "cadastro_sala?faces-redirect=true";
	}
	
	private void limpa() {
		obj = new Sala();
	}
	
	public void preRender(ComponentSystemEvent e) {
		setSalas(service.buscarTodos());
	}
	
	public TipoSala[] getTipos() {
		return TipoSala.values();
	}

	public Sala getObj() {
		return obj;
	}

	public void setObj(Sala obj) {
		this.obj = obj;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}

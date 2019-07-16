package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import modelo.Curso;
import modelo.Sexo;
import modelo.Solicitacao;
import modelo.SolicitacaoStatus;
import modelo.TipoDisciplina;
import service.CursoService;
import service.SolicitacaoService;
import util.FacesMensagens;
import util.NegocioException;

@ManagedBean(name = "solib")
@SessionScoped
public class SolicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Solicitacao obj = new Solicitacao();
	private Solicitacao novo = new Solicitacao();
	private List<Solicitacao> solicitacoes;
	private SolicitacaoService service = new SolicitacaoService();
	private String status;
	private List<Curso> cursos;

	public SolicitacaoBean() {
		CursoService cursoservice = new CursoService();
		setSolicitacoes(service.buscarTodos());
		setCursos(cursoservice.buscarTodos());
	}

	public void salvar(int usuario) {
		try {
			novo.setData(new Date());
			novo.setCodigoCoordenador(usuario);
			novo.setCodigoCurso(novo.getCurso().getCodigo());
			service.salvar(novo);
			setSolicitacoes(service.buscarTodos());

			FacesMensagens.info("Registro salvo com sucesso.");
			limpar();
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(obj.getCodigo());
			setSolicitacoes(service.buscarTodos());

			FacesMensagens.info("Registro excluído com sucesso.");
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public String editar() {
		return "cadastro_solicitacao?faces-redirect=true";
	}

	public String novo() {
		limpar();
		return "cadastro_solicitacao?faces-redirect=true";
	}

	private void limpar() {
		obj = new Solicitacao();
		novo = new Solicitacao();
	}

	public void preRender(ComponentSystemEvent e) {
		setSolicitacoes(service.buscarTodos());
	}

	public void preRender(int coordenador) {
		try {
			setSolicitacoes(service.buscarPorCoordenador(coordenador));
		} catch (NegocioException e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public void preRender(String curso) {
		try {
			setSolicitacoes(service.buscarPorCurso(Integer.valueOf(curso)));
		} catch (NumberFormatException | NegocioException e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public Solicitacao getObj() {
		return obj;
	}

	public void setObj(Solicitacao obj) {
		this.obj = obj;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Solicitacao getNovo() {
		return novo;
	}

	public void setNovo(Solicitacao novo) {
		this.novo = novo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String corSolicitacao() {
		if (obj == null)
			return "";

		System.out.println(obj.getStatus());

		if (obj.getStatus() == SolicitacaoStatus.RECUSADA)
			return "solicitacao-recusada";
		else if (obj.getStatus() == SolicitacaoStatus.SOLICITADA)
			return "solicitacao-pendente";
		else if (obj.getStatus() == SolicitacaoStatus.ACEITA)
			return "solicitacao-aceita";

		return "";
	}

	public void recusar() {

		try {
			obj.setStatus(SolicitacaoStatus.RECUSADA);
			service.salvar(obj);
			setSolicitacoes(service.buscarTodos());

			FacesMensagens.info("Status alterado com sucesso.");
			limpar();
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public void aceitar() {

		try {
			obj.setStatus(SolicitacaoStatus.ACEITA);
			service.salvar(obj);
			setSolicitacoes(service.buscarTodos());

			FacesMensagens.info("Status alterado com sucesso.");
			limpar();
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}
}

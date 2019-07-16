package controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import modelo.Coordenador;
import modelo.DirecaoEnsino;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Sexo;
import service.PessoaService;
import util.FacesMensagens;

@ManagedBean(name = "usuario")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa obj = new Pessoa();
	private List<Pessoa> usuarios;
	private PessoaService service = new PessoaService();
	private String[] permissoes;

	public UsuarioBean() {
		setUsuarios(service.buscarTodos());
		obj.setSexo(Sexo.MASCULINO);
	}

	public void salvar() {
		try {
			Set<String> roles = new HashSet<String>();
			for (int i = 0; i < permissoes.length; i++) {
				roles.add(permissoes[i]);
			}

			if (roles.contains("DE")) {
				DirecaoEnsino novo = new DirecaoEnsino();
				novo.setNome(obj.getNome());
				novo.setCpf(obj.getCpf());
				novo.setData_nascimento(obj.getData_nascimento());
				novo.setEmail(obj.getEmail());
				novo.setEndereco(obj.getEndereco());
				novo.setLogin(obj.getLogin());
				novo.setSenha(obj.getSenha());
				novo.setSexo(obj.getSexo());
				novo.setSiape(obj.getSiape());
				novo.setPermissao(roles);
				service.salvar(novo);
			} else if (roles.contains("COORDENADOR")) {
				Coordenador novo = new Coordenador();
				novo.setNome(obj.getNome());
				novo.setCpf(obj.getCpf());
				novo.setData_nascimento(obj.getData_nascimento());
				novo.setEmail(obj.getEmail());
				novo.setEndereco(obj.getEndereco());
				novo.setLogin(obj.getLogin());
				novo.setSenha(obj.getSenha());
				novo.setSexo(obj.getSexo());
				novo.setSiape(obj.getSiape());
				novo.setPermissao(roles);
				service.salvar(novo);
			} else if (roles.contains("PROFESSOR")) {
				Professor novo = new Professor();
				novo.setNome(obj.getNome());
				novo.setCpf(obj.getCpf());
				novo.setData_nascimento(obj.getData_nascimento());
				novo.setEmail(obj.getEmail());
				novo.setEndereco(obj.getEndereco());
				novo.setLogin(obj.getLogin());
				novo.setSenha(obj.getSenha());
				novo.setSexo(obj.getSexo());
				novo.setSiape(obj.getSiape());
				novo.setPermissao(roles);
				service.salvar(novo);
			}

			setUsuarios(service.buscarTodos());
			FacesMensagens.info("Registro salvo com sucesso.");
			limpar();
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(obj.getCodigo());
			setUsuarios(service.buscarTodos());

			FacesMensagens.info("Registro excluído com sucesso.");
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
	}

	public String editar() {
		return "cadastro_usuario?faces-redirect=true";
	}

	public String novo() {
		limpar();
		return "cadastro_usuario?faces-redirect=true";
	}

	private void limpar() {
		obj = new Pessoa();
		obj.setSexo(Sexo.MASCULINO);
		permissoes = null;
	}

	public String atribuirPermissao(String permissao) {

		Set<String> roles = obj.getPermissao();
		if (roles.contains(permissao))
			roles.remove(permissao);
		else
			roles.add(permissao);

		try {
			service.salvar(obj);
		} catch (Exception e) {
			FacesMensagens.error(e.getMessage());
		}
		return null;
	}

	public void preRender(ComponentSystemEvent e) {
		setUsuarios(service.buscarTodos());
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Pessoa getObj() {
		return obj;
	}

	public void setObj(Pessoa obj) {
		this.obj = obj;
	}

	public List<Pessoa> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Pessoa> usuarios) {
		this.usuarios = usuarios;
	}

	public String[] getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(String[] permissoes) {
		this.permissoes = permissoes;
	}

}

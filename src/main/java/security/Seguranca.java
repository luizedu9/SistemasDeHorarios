package security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import modelo.Pessoa;

@ManagedBean
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;
		
		PessoaSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}

	public int getCodigoUsuario() {
		int cod = 0;
		
		PessoaSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			cod = usuarioLogado.getUsuario().getCodigo();
		}
		
		return cod;
	}

	public String getCursoUsuario() {
		
		PessoaSistema usuarioLogado = getUsuarioLogado();
		Pessoa coordenador = null;
		
		if (usuarioLogado != null) {
			coordenador = (Pessoa) usuarioLogado.getUsuario();
		}
		return coordenador.getCodigoCurso().toString();
	}


	private PessoaSistema getUsuarioLogado() {
		PessoaSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (PessoaSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public boolean isPermDE(){
		 ExternalContext externalContext =
		  FacesContext.getCurrentInstance().getExternalContext();
		 
		  return  externalContext.isUserInRole("DE");
	}
	
	public boolean isPermCoordenador(){
		 ExternalContext externalContext =
		  FacesContext.getCurrentInstance().getExternalContext();
		 
		  return  externalContext.isUserInRole("COORDENADOR");
	}
	
	public boolean isPermProfessor(){
		 ExternalContext externalContext =
		  FacesContext.getCurrentInstance().getExternalContext();
		 
		  return  externalContext.isUserInRole("PROFESSOR");
	}
	
	public String getLogout(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		String aux = request.getContextPath()+"/j_spring_security_logout";
		return aux;
	}
	
}

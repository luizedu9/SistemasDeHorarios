package security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import modelo.Pessoa;

public class PessoaSistema extends User{
private static final long serialVersionUID = 1L;
	
	private Pessoa usuario;
	
	public PessoaSistema(Pessoa usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Pessoa getUsuario() {
		return usuario;
}
}

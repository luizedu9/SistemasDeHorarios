package security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import modelo.Pessoa;
import service.PessoaService;
import util.NegocioException;

public class AppUserDetailsService implements UserDetailsService {

		@Override
		public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
			PessoaService usuarios = new PessoaService();
			Pessoa usuario = null;
			try {
				usuario = usuarios.buscarPorLogin(login);
			} catch (NegocioException e) {
				e.printStackTrace();
			}
			
			PessoaSistema user = null;
			
			if (usuario != null) {
				user = new PessoaSistema(usuario, getGrupos(usuario));
			}
			
			return user;
		}

		private Collection<? extends GrantedAuthority> getGrupos(Pessoa usuario) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			
			for (String grupo : usuario.getPermissao()) {
				authorities.add(new SimpleGrantedAuthority(grupo.toUpperCase()));
			}
			
			return authorities;
	}	
	
}

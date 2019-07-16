package service;

import dao.CoordenadorDAO;
import java.util.List;
import modelo.Coordenador;
import util.NegocioException;

public class CoordenadorService {
    
    private CoordenadorDAO dao;
    
    public CoordenadorService(){
        dao = new CoordenadorDAO();
    }
    
    public Coordenador buscarPorCodigo(Integer id) throws NegocioException {
    	if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");     
        return dao.buscarPorCodigo(id);
    }
    
    public List<Coordenador> buscarPorNome(String nome) throws NegocioException {
    	if(nome == null || nome.trim().equals("") || (nome.length() < 4))
    		throw new NegocioException("Registro inválido.");
        return dao.buscarPorNome(nome);
    }
    
    public void salvar(Coordenador entidade) throws NegocioException {
    	if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Nome inválido.");
    	else if(entidade.getSenha() == null || entidade.getSenha().length() < 3)
    		throw new NegocioException("Senha inválida.");
    	else if(entidade.getPermissao() == null || (entidade.getPermissao().size() == 0))
    		throw new NegocioException("Nenhuma permissão atribuída ao usuário.");
    	else if(entidade.getCpf() == null || entidade.getCpf().trim().equals(""))
    		throw new NegocioException("CPF inválido.");
    	else if(entidade.getLogin() == null)
    		throw new NegocioException("Login inválido.");
    	else if((entidade.getPermissao().contains("PROFESSOR")) &&
    		   ((entidade.getSiape() == null) || entidade.getSiape().trim().equals(""))) {
    		throw new NegocioException("O campo SIAPE é requerido para Professores.");
    	}
    	
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id) throws NegocioException {
    	if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");     
        return dao.remover(id);
    }
    
    public List<Coordenador> buscarTodos(){
        return dao.buscarTodos();
    }
    
    public Boolean isLoginValido(String login) throws NegocioException {
        if(login == null || login.trim().equals(""))
            throw new NegocioException("Login inválido.");
        
        return dao.isLoginValido(login);
    }
}

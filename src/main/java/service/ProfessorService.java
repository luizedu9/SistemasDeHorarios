package service;

import dao.ProfessorDAO;
import java.util.List;
import modelo.Professor;
import util.NegocioException;

public class ProfessorService {
    
    private ProfessorDAO dao;
    
    public ProfessorService(){
        dao = new ProfessorDAO();
    }
    
    public Professor buscarPorCodigo(Integer id) {
        if(id == null || id <= 0)
            return null;
        	//throw new NegocioException("Informe um código válido.");       
        return dao.buscarPorCodigo(id);
    }
    
    public List<Professor> buscarPorNome(String nome){ 
        return dao.buscarPorNome(nome);
    }
    
    public void salvar(Professor entidade){
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id){
        return dao.remover(id);
    }
    
    public List<Professor> buscarTodos(){
        return dao.buscarTodos();
    }
    
    public Boolean isLoginValido(String login) throws NegocioException{
        
        if(login == null || login.trim().equals("")){
            throw new NegocioException("Digite o login!");
        }
        
        return dao.isLoginValido(login);
    }
}

package service;

import dao.CursoDAO;
import java.util.List;
import modelo.Curso;
import util.NegocioException;

public class CursoService {
    
    private CursoDAO dao;
    
    public CursoService(){
        dao = new CursoDAO();
    }
    
    public Curso buscarPorCodigo(Integer id) throws NegocioException {
    	if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");
        return dao.buscarPorCodigo(id);
    }
    
    public Curso buscarPorNome(String nome) throws NegocioException { 
    	if(nome == null || nome.trim().equals("") || (nome.length() < 3))
    		throw new NegocioException("Registro inválido.");
        return dao.buscarPorNome(nome);
    }
    
    public void salvar(Curso entidade) throws NegocioException {
    	if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Nome inválido.");
    	else if(entidade.getN_periodos() == null || entidade.getN_periodos() <= 0)
    		throw new NegocioException("O número de períodos deve ser um valor inteiro positivo.");
    	
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id) throws NegocioException {
    	if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");
        return dao.remover(id);
    }
    
    public List<Curso> buscarTodos() {
        return dao.buscarTodos();
    }
 
}

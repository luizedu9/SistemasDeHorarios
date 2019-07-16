package service;

import dao.DisciplinaDAO;
import java.util.List;
import modelo.Disciplina;
import util.NegocioException;

public class DisciplinaService {
    
    private DisciplinaDAO dao;
    
    public DisciplinaService(){
        dao = new DisciplinaDAO();
    }
    
    public Disciplina buscarPorCodigo(Integer id)  throws NegocioException {
    	if((id == null) || (id < 0))
    		throw new NegocioException("Registro inválido.");
    	
        return dao.buscarPorCodigo(id);
    }
    
    public List<Disciplina> buscarPorNome(String nome) throws NegocioException {
    	if(nome == null || nome.trim().equals("") || (nome.length() < 3))
    		throw new NegocioException("Registro inválido.");
        return dao.buscarPorNome(nome);
    }
    
    public void salvar(Disciplina entidade) throws NegocioException {
    	if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Nome inválido.");
    	else if(entidade.getCarga_horaria() == null || (entidade.getCarga_horaria() <= 0))
    		throw new NegocioException("Carga horária deve ser maior do que zero.");
    	else if(entidade.getTipo() == null)
    		throw new NegocioException("Tipo de disciplina inválido.");
    	
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id)  throws NegocioException {
    	if((id == null) || (id < 0))
    		throw new NegocioException("Registro inválido.");
    	
        return dao.remover(id);
    }
    
    public List<Disciplina> buscarTodos(){
        return dao.buscarTodos();
    }
 
}

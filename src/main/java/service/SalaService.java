package service;

import dao.SalaDAO;
import java.util.List;
import modelo.Sala;
import util.NegocioException;

public class SalaService {
    
    private SalaDAO dao;
    
    public SalaService(){
        dao = new SalaDAO();
    }
    
    public Sala buscarPorCodigo(Integer id) throws NegocioException {
        if(id == null || id <= 0)
            throw new NegocioException("Informe um código válido.");   
        return dao.buscarPorCodigo(id);
    }
    
    public List<Sala> buscarPorNome(String nome) throws NegocioException {
    	if(nome == null || nome.trim().equals("") || (nome.length() < 3))
    		throw new NegocioException("Registro inválido.");
    	
        return dao.buscarPorNome(nome);
    }
    
    public void salvar(Sala entidade) throws NegocioException {
    	if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Nome inválido.");
    	else if(entidade.getCapacidade() == null || entidade.getCapacidade() <= 0)
    		throw new NegocioException("A capacidade deve ser um número inteiro positivo.");
    	else if(entidade.getTipo() == null)
    		throw new NegocioException("Informe o tipo da sala.");
    	
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id) throws NegocioException {
    	if(id == null || id <= 0)
            throw new NegocioException("Informe um código válido.");
    	
        return dao.remover(id);
    }
    
    public List<Sala> buscarTodos(){
        return dao.buscarTodos();
    }
 
}

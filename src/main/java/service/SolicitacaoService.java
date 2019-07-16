package service;

import dao.SolicitacaoDAO;
import java.util.List;
import modelo.Solicitacao;
import util.NegocioException;

public class SolicitacaoService {
    
    private SolicitacaoDAO dao;
    
    public SolicitacaoService(){
        dao = new SolicitacaoDAO();
    }
    
    public Solicitacao buscarPorCodigo(Integer id) throws NegocioException {
    	if((id == null) || (id < 0))
    		throw new NegocioException("Registro inválido.");
        return dao.buscarPorCodigo(id);
    }
    
    public List<Solicitacao> buscarPorNome(String nome) throws NegocioException {
    	if(nome == null || nome.trim().equals("") || (nome.length() < 3))
    		throw new NegocioException("Registro inválido.");
    	
        return dao.buscarPorNome(nome);
    }

    public List<Solicitacao> buscarPorCoordenador(Integer coordenador) throws NegocioException {
    	if((coordenador == null) || (coordenador < 0))
    		throw new NegocioException("Registro inválido.");

        return dao.buscarPorCoordenador(coordenador);
    }
    
    public List<Solicitacao> buscarPorCurso(Integer curso) throws NegocioException {
    	if((curso == null) || (curso < 0))
    		throw new NegocioException("Registro inválido.");

        return dao.buscarPorCurso(curso);
    }
    
    public void salvar(Solicitacao entidade) throws NegocioException {
    	if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Informe o nome da disciplina a ser solicitada.");
        dao.salvar(entidade);
    }
    
    public boolean remover(Integer id) throws NegocioException {
    	if((id == null) || (id < 0))
    		throw new NegocioException("Registro inválido.");
    	
        return dao.remover(id);
    }
    
    public List<Solicitacao> buscarTodos() {
        return dao.buscarTodos();
    }
    
    public List<Solicitacao> buscarPendentes() {
    	return dao.buscarPendentes();
    }
 
}

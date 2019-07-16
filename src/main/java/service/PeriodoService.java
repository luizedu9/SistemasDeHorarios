package service;

import dao.PeriodoDAO;
import java.util.List;

import modelo.Curso;
import modelo.Periodo;
import util.NegocioException;

public class PeriodoService {

	private PeriodoDAO dao;

	public PeriodoService() {
		dao = new PeriodoDAO();
	}

	public Periodo buscarPorCodigo(Integer id) throws NegocioException {
		if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");
		return dao.buscarPorCodigo(id);
	}

	public List<Periodo> buscarPorNome(String nome) throws NegocioException {
		if(nome == null || nome.trim().equals("") || (nome.length() < 3))
    		throw new NegocioException("Registro inválido.");
		return dao.buscarPorNome(nome);
	}

	public void salvar(Periodo entidade) throws NegocioException {
		if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getNome() == null || entidade.getNome().trim().equals("")
    			|| (entidade.getNome().length() < 3))
    		throw new NegocioException("Nome inválido.");
    	else if(entidade.getCurso().getClass() == null || entidade.getCurso().getCodigo() < 0)
    		throw new NegocioException("Curso inválido.");
    	else if(entidade.getSemestre() == null || (entidade.getSemestre() < 0))
    		throw new NegocioException("Semestre Inválido.");
    			
		dao.salvar(entidade);
	}

	public boolean remover(Integer id) throws NegocioException {
		if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");
		return dao.remover(id);
	}

	public List<Periodo> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Integer> buscarSemestres(Curso curso, Integer ano) throws NegocioException {
		if(curso.getClass() == null || curso.getCodigo() <= 0)
        	throw new NegocioException("Registro inválido.");
		return dao.buscarSemestres(curso, ano);
	}

	public List<Integer> buscarAno(Curso curso) throws NegocioException {
		if(curso.getClass() == null || curso.getCodigo() <= 0)
        	throw new NegocioException("Registro inválido.");
		return dao.buscarAno(curso);
	}
	
	public List<String> buscarNomes(Curso curso, Integer semestre, Integer ano) throws NegocioException {
		if(curso.getClass() == null || curso.getCodigo() <= 0)
        	throw new NegocioException("Registro inválido.");
		return dao.buscarNomes(curso, semestre, ano);
	}

}

package service;

import dao.TurmaDAO;
import java.util.List;

import modelo.Curso;
import modelo.Turma;
import util.NegocioException;

public class TurmaService {

	private TurmaDAO dao;

	public TurmaService() {
		dao = new TurmaDAO();
	}

	public Turma buscarPorCodigo(Integer id) throws NegocioException {
		if(id == null || id <= 0)
        	throw new NegocioException("Registro inválido.");
		
		return dao.buscarPorCodigo(id);
	}

	public void salvar(Turma entidade) throws NegocioException {
		if(entidade == null)
    		throw new NegocioException("Registro inválido.");
    	else if(entidade.getDisciplina().getCodigo() == null || (entidade.getDisciplina().getCodigo() < 0))
    		throw new NegocioException("Disciplina inválida.");
    	else if(entidade.getProfessor().getCodigo() == null || (entidade.getProfessor().getCodigo() < 0))
    		throw new NegocioException("Professor inválido");
    	else if(entidade.getPeriodo().getCodigo() == null || (entidade.getPeriodo().getCodigo() < 0))
    		throw new NegocioException("Período inválido");
		
		dao.salvar(entidade);
	}

	public boolean remover(Integer id) throws NegocioException {
		if((id == null) || (id < 0))
    		throw new NegocioException("Registro inválido.");
		
		return dao.remover(id);
	}

	public List<Turma> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Turma> buscarTurma(Integer semestre, Curso curso, Integer ano) throws NegocioException {
		if((semestre <= 0) || (curso.getCodigo() == null) || (curso.getCodigo() < 0))
    		throw new NegocioException("Registro inválido.");
		
		return dao.buscarTurma(semestre, curso, ano);
	}

}

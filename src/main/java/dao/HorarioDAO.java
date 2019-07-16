package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controller.FiltroHorario;
import modelo.Curso;
import modelo.Disciplina;
import modelo.Horario;
import modelo.Periodo;
import modelo.Professor;
import modelo.Sala;
import modelo.Turma;

public class HorarioDAO extends HibernateGenericDAO<Horario, Integer> {
    
    public HorarioDAO() {
        
    }
    
    public List<Horario> filtraTabelaHorarios(FiltroHorario filter) {

    	String sql = " SELECT h " + 
    			" FROM Horario h " +
    			" JOIN h.turma t " +
    			" JOIN t.disciplina d " +
    			" JOIN d.cursos c " +
    			" JOIN t.periodo p " +
    			" JOIN t.professor pr " +
    			" JOIN h.sala s " +
    			" WHERE 1 = 1 ";
    			
    	if(filter.getCurso() != null) {
    		sql = sql + " AND c = ?1 ";
    	}
    	if(filter.getAno() != null) {
    		sql = sql + " AND p.ano = ?2 ";
    	}
    	if(filter.getSemestre() != null) {
    		sql = sql + " AND p.semestre = ?3 ";
    	}
    	if(filter.getNomePeriodo() != null ) {
    		sql = sql + " AND p.nome = ?4 ";
    	}
    	if(filter.getSala() != null) {
    		sql = sql + " AND s = ?5 ";
    	}
    	if(filter.getTurma() != null) {
    		sql = sql + " AND t = ?6 ";
    	}
    	if(filter.getProfessor() != null) {
    		sql = sql + " AND pr = ?7 ";
    	}
    	
	    EntityManager em = getEntityManager();
	    TypedQuery<Horario> query = em.createQuery(sql, Horario.class);
	    
	    if(filter.getCurso() != null) {
	    	query.setParameter(1, filter.getCurso());
    	}
    	if(filter.getAno() != null) {
    		query.setParameter(2, filter.getAno());
    	}
    	if(filter.getSemestre() != null) {
    		query.setParameter(3, filter.getSemestre());
    	}
    	if(filter.getNomePeriodo() != null ) {
    		query.setParameter(4, filter.getNomePeriodo());
    	}
    	if(filter.getSala() != null) {
    		query.setParameter(5, filter.getSala());
    	}
    	if(filter.getTurma() != null) {
    		query.setParameter(6, filter.getTurma());
    	}
    	if(filter.getProfessor() != null) {
    		query.setParameter(7, filter.getProfessor());
    	}
	    
	    List<Horario> result = query.getResultList();
	    return result;
    }
    
}

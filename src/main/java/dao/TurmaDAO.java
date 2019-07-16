package dao;

import java.util.List;

import javax.persistence.EntityManager;

import modelo.Curso;
import modelo.Turma;
import util.FabricaEntity;

public class TurmaDAO extends HibernateGenericDAO<Turma, Integer> {

	public TurmaDAO() {

	}

	public List<Turma> buscarTurma(Integer semestre, Curso curso, Integer ano) {

//		select * from turma,periodo where  turma.codPeriodo = periodo.codigo and periodo.codCurso = 1 and periodo.semestre = 1 and periodo.ano = 2017;
		
		String qry = "Select DISTINCT(t) from turma t, periodo p where t.periodo = p and p.curso = :curso and p.semestre = :semestre and p.ano = :ano";
		EntityManager em = FabricaEntity.getEntityManager();
		return em.createQuery(qry, Turma.class).setParameter("curso", curso).setParameter("semestre", semestre).setParameter("ano", ano).getResultList();

	}

}

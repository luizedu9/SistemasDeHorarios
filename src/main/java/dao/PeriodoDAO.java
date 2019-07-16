package dao;

import java.util.List;
import javax.persistence.EntityManager;

import modelo.Curso;
import modelo.Periodo;
import util.FabricaEntity;

public class PeriodoDAO extends HibernateGenericDAO<Periodo, Integer> {

	public PeriodoDAO() {

	}

	public List<Integer> buscarSemestres(Curso curso, Integer ano) {

		String qry = "Select DISTINCT(p.semestre) from periodo p where p.curso = :curso and p.ano = :ano";
		EntityManager em = FabricaEntity.getEntityManager();
		return em.createQuery(qry, Integer.class).setParameter("curso", curso).setParameter("ano", ano).getResultList();

//		String qry = "Select DISTINCT(p.semestre) from periodo p where p.curso = :curso";
//		EntityManager em = FabricaEntity.getEntityManager();
//		return em.createQuery(qry, Integer.class).setParameter("curso", curso).getResultList();

	}

	public List<Integer> buscarAno(Curso curso) {

		String qry = "Select DISTINCT(p.ano) from periodo p where p.curso = :curso";
		EntityManager em = FabricaEntity.getEntityManager();
		return em.createQuery(qry, Integer.class).setParameter("curso", curso).getResultList();

	}

	public List<Periodo> buscarPorNome(String nome) {
		String sql = "Select c from Periodo c " + "where c.nome like ?1";

		EntityManager em = getEntityManager();
		return em.createQuery(sql, Periodo.class).setParameter("1", nome).getResultList();
	}

	public List<String> buscarNomes(Curso curso, Integer semestre, Integer ano) {

		String qry = "Select DISTINCT(p.nome) from periodo p where p.curso = :curso and p.semestre = :semestre and p.ano = :ano";
		EntityManager em = FabricaEntity.getEntityManager();
		return em.createQuery(qry, String.class).setParameter("curso", curso).setParameter("semestre", semestre)
				.setParameter("ano", ano).getResultList();

	}

}

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Curso;

public class CursoDAO extends HibernateGenericDAO<Curso, Integer> {

	public CursoDAO() {

	}

	public Curso buscarPorNome(String nome) {
		String sql = "Select c from Curso c where c.nome = :nome";

		EntityManager em = getEntityManager();
		return em.createQuery(sql, Curso.class).setParameter("nome", nome).getSingleResult();
	}

}

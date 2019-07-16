package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Disciplina;

public class DisciplinaDAO extends HibernateGenericDAO<Disciplina, Integer> {
    
    public DisciplinaDAO() {
        
    }
    
    public List<Disciplina> buscarPorNome(String nome){
        String sql = "Select c from Disciplina c " +
                    "where c.nome like ?1";
       
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Disciplina.class).setParameter("1", nome).getResultList();
    }
    
}

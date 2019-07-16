package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Sala;

public class SalaDAO extends HibernateGenericDAO<Sala, Integer> {
    
    public SalaDAO() {
        
    }
    
    public List<Sala> buscarPorNome(String nome){
        String sql = "Select c from Sala c " +
                    "where c.nome like ?1";
       
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Sala.class).setParameter("1", nome).getResultList();
    }
    
}

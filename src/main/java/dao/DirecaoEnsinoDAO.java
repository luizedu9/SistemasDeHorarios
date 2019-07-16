package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.DirecaoEnsino;

public class DirecaoEnsinoDAO extends HibernateGenericDAO<DirecaoEnsino, Integer> {

    public DirecaoEnsinoDAO() {
        
    }
    
    public List<DirecaoEnsino> buscarPorNome(String nome){
        String sql = "Select c from DirecaoEnsino c " +
                     "where c.nome like ?1";
        EntityManager em = getEntityManager();
        return em.createQuery(sql, DirecaoEnsino.class).setParameter("1", nome).getResultList();
    }
    
    public boolean isLoginValido(String login){
        String sql = "SELECT a FROM DirecaoEnsino a WHERE a.login LIKE :Login";
       
        EntityManager em = getEntityManager();
        DirecaoEnsino user =  em.createQuery(sql, DirecaoEnsino.class)
               .setParameter("Login", login)
               .getSingleResult();
       
        if(user != null)
           return true;
        else
           return false;
    }
    
}

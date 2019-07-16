package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Professor;

public class ProfessorDAO extends HibernateGenericDAO<Professor, Integer> {

    public ProfessorDAO() {
        
    }
    
    public List<Professor> buscarPorNome(String nome){
        String sql = "Select c from Professor c " +
                     "where c.nome like ?1";
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Professor.class).setParameter("1", nome).getResultList();
    }
    
    public boolean isLoginValido(String login){
        String sql = "SELECT a FROM Professor a WHERE a.login LIKE :Login";
       
        EntityManager em = getEntityManager();
        Professor user =  em.createQuery(sql, Professor.class)
               .setParameter("Login", login)
               .getSingleResult();
       
        if(user != null)
           return true;
        else
           return false;
    }
    
}

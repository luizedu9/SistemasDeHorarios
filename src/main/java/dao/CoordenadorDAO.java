package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Coordenador;

public class CoordenadorDAO extends HibernateGenericDAO<Coordenador, Integer> {

    public CoordenadorDAO() {
        
    }
    
    public List<Coordenador> buscarPorNome(String nome){
        String sql = "Select c from Coordenador c " +
                     "where c.nome like ?1";
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Coordenador.class).setParameter("1", nome).getResultList();
    }
    
    public boolean isLoginValido(String login){
        String sql = "SELECT a FROM Coordenador a WHERE a.login LIKE :Login";
       
        EntityManager em = getEntityManager();
        Coordenador user =  em.createQuery(sql, Coordenador.class)
               .setParameter("Login", login)
               .getSingleResult();
       
        if(user != null)
           return true;
        else
           return false;
    }
    
}

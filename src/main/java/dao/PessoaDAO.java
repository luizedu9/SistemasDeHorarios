package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Pessoa;

public class PessoaDAO extends HibernateGenericDAO<Pessoa, Integer> {

    public PessoaDAO() {
        
    }
    
    public List<Pessoa> buscarPorNome(String nome){
        String sql = "Select c from Pessoa c " +
                     "where c.nome = :nome";
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Pessoa.class).setParameter("nome", nome).getResultList();
    }
    
    public Pessoa buscarPorLogin(String login){
        String sql = "Select c from Pessoa c " +
                     "where c.login = :login";
        EntityManager em = getEntityManager();
        Pessoa result;
        try {
        	result = em.createQuery(sql, Pessoa.class).setParameter("login", login).getSingleResult();
        } catch(NoResultException e) {
        	return null;
        }
        
        return result;
    }
    
    public boolean isLoginValido(String login){
        String sql = "SELECT a FROM Pessoa a WHERE a.login LIKE :Login";
       
        EntityManager em = getEntityManager();
        Pessoa user =  em.createQuery(sql, Pessoa.class)
               .setParameter("Login", login)
               .getSingleResult();
       
        if(user != null)
           return true;
        else
           return false;
    }
    
}

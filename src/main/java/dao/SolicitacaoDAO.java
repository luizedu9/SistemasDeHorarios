package dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.Solicitacao;
import modelo.SolicitacaoStatus;

public class SolicitacaoDAO extends HibernateGenericDAO<Solicitacao, Integer> {
    
    public SolicitacaoDAO() {
        
    }
    
    public List<Solicitacao> buscarPorNome(String nome){
        String sql = "Select c from Solicitacao c " +
                    "where c.nome like ?1";
       
        EntityManager em = getEntityManager();
        return em.createQuery(sql, Solicitacao.class).setParameter(1, nome).getResultList();
    }
    
    public List<Solicitacao> buscarPendentes() {
    	String sql = "SELECT s FROM Solicitacao as s " +
    				 "WHERE s.status = :status";
    	EntityManager em = getEntityManager();
    	return em.createQuery(sql, Solicitacao.class).setParameter("status", SolicitacaoStatus.ACEITA).getResultList();
    }
    
    public List<Solicitacao> buscarPorCoordenador(Integer coordenador) {
    	String sql = "SELECT s FROM Solicitacao as s " +
    				 "WHERE s.codigoCoordenador = :coord";
    	EntityManager em = getEntityManager();
    	return em.createQuery(sql, Solicitacao.class).setParameter("coord", coordenador).getResultList();
    }
    
    public List<Solicitacao> buscarPorCurso(Integer curso) {
    	String sql = "SELECT s FROM Solicitacao as s " +
    				 "WHERE s.codigoCurso = :curso";
    	EntityManager em = getEntityManager();
    	return em.createQuery(sql, Solicitacao.class).setParameter("curso", curso).getResultList();
    }
    
}

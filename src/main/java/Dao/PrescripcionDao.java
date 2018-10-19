package Dao;

import entities.Prescripcion;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.FxDialogs;
import utilidades.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class PrescripcionDao {

    private CriteriaBuilder builder;
    private CriteriaQuery<Prescripcion> query;
    private Root<Prescripcion> root;

    public Boolean guardar(Prescripcion e, Boolean insertar){
        Transaction tx=null;
        Boolean resultado=false;
        try(Session session = SessionUtil.getSession()) {
            if(insertar) {
                e.setId(UUID.randomUUID());
            }
            tx= session.beginTransaction();
            session.saveOrUpdate(e);
            tx.commit();
            resultado=true  ;
            session.refresh(e);
        }catch (HibernateException ex){
            tx.rollback();
            resultado=false ;
            FxDialogs.showException("Error","Ha ocurrido un error al guardar los datos",ex);
        }
        return resultado;
    }
}

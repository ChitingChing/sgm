package Dao;

import entities.Medicina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.FxDialogs;
import utilidades.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class MedicinaDao {
    private CriteriaBuilder builder;
    private CriteriaQuery<Medicina> query;
    private Root<Medicina> root;

    public MedicinaDao() {
    }


    public Boolean guardarMedicina(Medicina e, Boolean insertar){
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

    public Medicina getMedicina(String Nombre){
        Medicina p = null;
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            query = builder.createQuery(Medicina.class);
            root = query.from(Medicina.class);
            query.select(root).where(builder.like(builder.lower(root.get("descripcion")), Nombre));
            if (session.createQuery(query).getResultList().size() > 0)
                p = session.createQuery(query).getSingleResult();
        }
        return p;
    }
    public ObservableList<Medicina> getMedicinaByNombre(String Texto){
        ObservableList<Medicina> list = null;
        Texto = "%"+Texto+"%";
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            query = builder.createQuery(Medicina.class);
            root = query.from(Medicina.class);
            query.select(root).where(builder.like(builder.lower(root.get("descripcion")),Texto)).orderBy(builder.desc(root.get("descripcion")));
            //  if (session.createQuery(query).getResultList().size() > 0)
            list = FXCollections.observableArrayList(session.createQuery(query).getResultList());
        }
        return list;
    }

}

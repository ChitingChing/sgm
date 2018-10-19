package Dao;

import entities.FichaPrenatal;
import entities.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.FxDialogs;
import utilidades.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class FichaPrenatalDao {
    private CriteriaBuilder builder;
    private CriteriaQuery<FichaPrenatal> query;
    private Root<FichaPrenatal> root;

    public Boolean guardarFicha(FichaPrenatal ficha, Boolean insertar){
        Transaction tx=null;
        Boolean resultado=false;
        try(Session session = SessionUtil.getSession()) {
            if(insertar) {
                ficha.setId(UUID.randomUUID());
            }
            tx= session.beginTransaction();
            session.saveOrUpdate(ficha);
            tx.commit();
            resultado=true  ;
            session.refresh(ficha);
        }catch (HibernateException ex){
            tx.rollback();
            resultado=false ;
            FxDialogs.showException("Error","Ha ocurrido un error al guardar los datos",ex);
        }
        return resultado;
    }

    public ObservableList<FichaPrenatal> getFichasByCedula(String Cedula){
        ObservableList<FichaPrenatal> fichas=null;
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            query = builder.createQuery(FichaPrenatal.class);
            root = query.from(FichaPrenatal.class);
            query.select(root).where(builder.equal(root.get("paciente").get("cedula"), Cedula)).orderBy(builder.desc(root.get("fechaRegistro")));
            fichas = FXCollections.observableArrayList(session.createQuery(query).getResultList());

        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al consultar los datos",ex);
        }
        return fichas;
    }
}

package Dao;

import entities.Empresa;
import entities.Paciente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilidades.FxDialogs;
import utilidades.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class EmpresaDao {
    private CriteriaBuilder builder;
    private CriteriaQuery<Empresa> query;
    private Root<Empresa> root;

    public Boolean guardarPaciente(Empresa e, Boolean insertar){
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

    public Empresa getEmpresaDatos() {
        Empresa e ;
        try(Session session = SessionUtil.getSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Empresa.class);
            Root<Empresa> root = query.from(Empresa.class);
            query.select(root);
            e = (Empresa) session.createQuery(query).getSingleResult();
        }
        return e;
    }
}

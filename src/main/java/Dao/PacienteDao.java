package Dao;

import entities.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import utilidades.FxDialogs;
import utilidades.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class PacienteDao  {

    private  CriteriaBuilder builder;
    private CriteriaQuery<Paciente> query;
    private Root<Paciente> root;
    public Paciente getPaciente(String Cedula){
        Paciente p = null;
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            query = builder.createQuery(Paciente.class);
            root = query.from(Paciente.class);
            query.select(root).where(builder.equal(root.get("cedula"), Cedula));
            if (session.createQuery(query).getResultList().size() > 0)
                p = session.createQuery(query).getSingleResult();
        }
        return p;
    }
    public ObservableList<Paciente> getPacientesByCedNomApe(String Texto){
        ObservableList<Paciente> pacientes = null;
        Texto = "%"+Texto.toLowerCase()+"%";
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            query = builder.createQuery(Paciente.class);
            root = query.from(Paciente.class);
            query.select(root).where(builder.or(
                    builder.like(root.get("cedula"),Texto),
                    builder.like(builder.lower(root.get("primernombre")),Texto),
                    builder.like(builder.lower(root.get("segundonombre")),Texto),
                    builder.like(builder.lower(root.get("primerapellido")),Texto),
                    builder.like(builder.lower(root.get("segundoapellido")),Texto)
            //        builder.like(root.get("nhistoriaclinica"),Texto)
                    )).
                    orderBy(builder.desc(root.get("primerapellido")),builder.desc(root.get("segundoapellido")),
                            builder.desc(root.get("primernombre")));
          //  if (session.createQuery(query).getResultList().size() > 0)
                pacientes = FXCollections.observableArrayList(session.createQuery(query).getResultList());
        }
        return pacientes;
    }

    public Boolean guardarPaciente(Paciente paciente,Boolean insertar){
        Transaction tx=null;
        Boolean resultado=false;
        try(Session session = SessionUtil.getSession()) {
            if(insertar) {
                paciente.setId(UUID.randomUUID());
                Integer nhistoriacli = getUltimoNHistoriaClinica();
                paciente.setNhistoriaclinica(nhistoriacli+1);
            }
             tx= session.beginTransaction();
            session.saveOrUpdate(paciente);
            tx.commit();
            resultado=true  ;
            session.refresh(paciente);
        }catch (HibernateException ex){
            tx.rollback();
            resultado=false ;
            FxDialogs.showException("Error","Ha ocurrido un error al guardar los datos",ex);
        }
        return resultado;
    }
    public Integer getUltimoNHistoriaClinica(){
        Integer resultado=0;
        try(Session session = SessionUtil.getSession()) {
            builder = session.getCriteriaBuilder();
            CriteriaQuery<Object> query = builder.createQuery(Object.class);
            Root<Paciente> root = query.from(Paciente.class);
            query.select(builder.max(root.get("nhistoriaclinica")));
            if (session.createQuery(query).getSingleResult() != null) {
                resultado = Integer.parseInt(session.createQuery(query).getSingleResult().toString());
            }
        }
        return resultado;
    }
}

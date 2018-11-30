package Dao;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Canton;
import entities.Empresa;
import entities.Patologia;
import entities.Provincia;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.hibernate.Session;
import org.postgresql.util.PSQLException;
import utilidades.ConnectionInfo;
import utilidades.FxDialogs;
import utilidades.SessionUtil;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class Otros {

    public static ConnectionInfo getInfoConnection(){
        ConnectionInfo info = new ConnectionInfo();
        try(Session session = SessionUtil.getSession()) {
            session.doWork(info);
        }catch (Exception ex){
            FxDialogs.showException("","",ex);
        }
        return  info;

    }



    public static ObservableList<Provincia>  getProvincias(){
        ObservableList<Provincia> lista=FXCollections.observableArrayList();
        try(Session session = SessionUtil.getSession()){
            //Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Provincia.class);
           Root<Provincia> root= query.from(Provincia.class);
           query.select(root).where(builder.equal(root.get("estado"),"A")).orderBy(builder.asc(root.get("descripcion")));
           lista= FXCollections.observableList(session.createQuery(query).getResultList());

           //lista =  query.list().stream().forEach(x-> x.g);
        } catch (Exception ex){
            System.out.println(ex.toString());
            FxDialogs.showException("Error","Ha ocurrido un error",ex);
        }
        return lista;
    }
    public Provincia  getProvincia(String Id){
        Provincia p = null;
        try(Session session = SessionUtil.getSession()){
            //Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Provincia.class);
            Root<Provincia> root= query.from(Provincia.class);
            query.select(root).where(builder.equal(root.get("id"),Id)).orderBy(builder.asc(root.get("descripcion")));
            p= (Provincia) session.createQuery(query).getSingleResult();

            //lista =  query.list().stream().forEach(x-> x.g);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        return p;
    }

    public Canton getCanton(String ID){
        Canton lista=null;
        try(Session session = SessionUtil.getSession()){
            //Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Canton.class);
            Root<Canton> root= query.from(Canton.class);
            query.select(root).where(builder.equal(root.get("id"),ID))
                    .orderBy(builder.asc(root.get("descripcion")));
            lista= (Canton) session.createQuery(query).getSingleResult();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        return lista;
    }

    public static ObservableList<Canton> getCantones(String idProvincia){
        ObservableList<Canton> lista=FXCollections.observableArrayList();;
        try(Session session = SessionUtil.getSession()){
            //Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Canton.class);
            Root root= query.from(Canton.class);
            query.select(root).where(builder.equal(root.get("estado"),"A"),
                    builder.equal(root.get("provinciaByIdprovincia").get("id"),idProvincia))
                    .orderBy(builder.asc(root.get("descripcion")));
            lista= FXCollections.observableList(session.createQuery(query).getResultList());
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        return lista;
    }
    public static ObservableList getEstudios(){
        return FXCollections.observableArrayList("Ninguno","Primaria","Secundaria","Tercer Nivel","Cuarto Nivel");
    }
    public static ObservableList getEstadoCiviles(){
        return FXCollections.observableArrayList("Soltero","Casado","Unión Libre","Divorciado","Otro");
    }

    public void showNotificacionPane(NotificationPane notificationPane,String message,Boolean ok){
        notificationPane.setShowFromTop(false);
        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> notificationPane.hide());
        delay.play();
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TIMES_CIRCLE);
        icon.getStyleClass().add("green-icon");
        if(ok){
            icon = new FontAwesomeIconView(FontAwesomeIcon.CHECK_CIRCLE);
            icon.getStyleClass().setAll("green-icon");
        }

        notificationPane.show(message,icon);
    }

    public static ObservableList getPatologias(String Grupo){
        ObservableList lista=FXCollections.observableArrayList();
        try(Session session = SessionUtil.getSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Patologia.class);
            Root root = query.from(Patologia.class);
            query.select(root).where(builder.equal(root.get("estado"),"A"),builder.equal(root.get("grupo"),Grupo))
                    .orderBy(builder.asc(root.get("descripcion")));
            lista = FXCollections.observableArrayList(session.createQuery(query).getResultList());
        }catch (Exception ex){}
        return lista;
    }

    public static ObservableList getAntecedendetes(){

        ObservableList<String> antecedentes = FXCollections.observableArrayList(
                "Diabetes","Tb Pulmonar","Hipertensión","Gemelares","Otra"
        );
        return antecedentes;
    }
    public static ObservableList getAntecedendetesPersonales(){

        ObservableList<String> antecedentes = FXCollections.observableArrayList(
                "Diabetes","Tb Pulmonar","Hipertensión Crónica","Cirugía Pélvica Uterina","Infertilidad"
        );
        return antecedentes;
    }
    public static ObservableList getGrupoSangre(){

        ObservableList list = FXCollections.observableArrayList(
                "A","B","AB","O"
        );
        return list;
    }
    public static ObservableList getPositivoNegativo(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Positivo","Negativo"
        );
        return list;
    }
    public static ObservableList getPresentacion(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Cefálica","Pelviana","Transversa"
        );
        return list;
    }
    public static ObservableList getSexo(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Masculino","Femenino"
        );
        return list;
    }
    public static ObservableList getReanimacionRespiratoria(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "No","Máscara","Tubo"
        );
        return list;
    }
    public static ObservableList getVdrl(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Cefálica","Pelviana","Transversa"
        );
        return list;
    }
    public static ObservableList getExamenFisico(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Normal","Anormal"
        );
        return list;
    }
    public static ObservableList getPeso(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Adecuado","Pequeño","Grande"
        );
        return list;
    }

    public static ObservableList getAlimentos(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Pecho","Mixto","Artificial"
        );
        return list;
    }
    public static ObservableList getEstadoEgreso(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Sano","Traslado","Con Patologías","Fallece"
        );
        return list;
    }
    public static ObservableList getAnticoncepcion(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Ninguna","Referida","Condón","D/U","Píldora","Ligadura",
                "Inyectables","Ritmo","Otro"
        );
        return list;
    }

    public static ObservableList getLoquios(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Ninguna","Referida","Condón","D/U","Píldora","Ligadura",
                "Inyectables","Ritmo","Otro"
        );
        return list;
    }
    public static ObservableList getInicio(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Esporádico","Inducido","Cesaria Electiva"
        );
        return list;
    }
    public static ObservableList getTerminacion(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Espontáneo","Forcep","Cesaria","Otra"
        );
        return list;
    }
    public static ObservableList getMembranas(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "Int","Rot"
        );
        return list;
    }
    public static ObservableList getMuerteIntraUte(){

        ObservableList<String> list = FXCollections.observableArrayList(
                "No","Si","Si emb.","Si ignora momento"
        );
        return list;
    }
    public static ObservableList getMedicacionParto(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "Ninguna","Anest. Reg.","Anest. General","Anest. Tranquil.","Ocitoc.","Antibióticos","Otros"
        );
        return list;
    }
    public static ObservableList getNivelAtencion(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "3°","2°","1°","Domic","Otro"
        );
        return list;
    }
    public static ObservableList getAtendio(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "Médico","Enf. Ost.","Auxiliar","Estudiante Parto","Otro"
        );
        return list;
    }
    public static ObservableList getRH(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "Positivo","Negativo"
        );
        return list;
    }
    public static ObservableList getMeses(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
        );
        return list;
    }

    public static ObservableList getTipoOperacion(){
        ObservableList<String> list = FXCollections.observableArrayList(
                "Efectiva","Emergencia","Paleativa"
        );
        return list;
    }
}

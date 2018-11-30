package controllers;

import entities.Canton;
import entities.Provincia;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.postgresql.util.PSQLException;
import utilidades.ConnectionInfo;
import utilidades.Formularios;
import Dao.Otros;
import utilidades.FxDialogs;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Normalizer;


public class InicioController {

    public static ObservableList<Provincia> provincias ;
    public static ObservableList<Canton> cantonesLosRios;
    public static ObservableList<String> estudios;
    public static ObservableList<String> estadoCiviles;

    @FXML private Label lblConnectioninfo;

    @FXML
    public void initialize(){
        try {

            ConnectionInfo connectionInfo = Otros.getInfoConnection();
            String info = "Conexión: " + connectionInfo.dataBaseUrl + " User: " + connectionInfo.getUsername();
            lblConnectioninfo.setText(info);

            cargarDatos();
        }catch (Exception ex){
            FxDialogs.showException("Error","No se ha podido iniciar la aplicacion",ex);
        }
    }

    public void showBusquedaPaciente() throws IOException {
        //Formularios f = new Formularios(this);
        //f.showBusquedaPaciente();
    }

    public void showPaciente() throws IOException {
      Formularios f = new Formularios();
      f.showPaciente();

    }
    public void showNuevoFichaPrenatal() throws IOException {
        Formularios f = new Formularios();
        f.showNuevoFichaPrenatal();
    }

    public void showEmpresa() throws IOException {
        Formularios f = new Formularios();
        f.showEmpresa();
    }
    public void showMedicinas() throws IOException {
        Formularios f = new Formularios();
        f.showMedicina();
    }
    public  void showPrescripcion() throws IOException {
        Formularios f = new Formularios();
        f.showPrescripcion();
    }

    private void cargarDatos() {
        try {
            provincias = Otros.getProvincias();
            estadoCiviles = Otros.getEstadoCiviles();
            estudios = Otros.getEstudios();
            cantonesLosRios = Otros.getCantones("12");
        } catch (Exception ex) {
            FxDialogs.showException("Error", "No se ha podido iniciar la aplicacion", ex);
        }

    }

}

package controllers;


import Dao.PacienteDao;
import entities.Paciente;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.FxDialogs;

import java.time.LocalDate;

public class BusquedaPacienteController {

    public CustomTextField txtTextoBuscar;
    public TableView<Paciente> tblPaciente = new TableView<>();
    public TableColumn colCedula = new TableColumn("Cédula");
    public TableColumn colPrimerNombre = new TableColumn("Primer Nombre");
    public TableColumn colSegundoNombre = new TableColumn("Segundo Nombre");
    public TableColumn colPrimerApellido = new TableColumn("Primer Apellido");
    public TableColumn colSegundoApellido = new TableColumn("Segundo Apellido");
    public TableColumn colNhistorialClinico = new TableColumn("N° Historia Clínica");
    public TableColumn colFechaNacimiento = new TableColumn("Fecha de Nacimiento");
    public MaskerPane mskEsperar;
    public Paciente pacienteSeleccionado;


    public void initialize(){

        mskEsperar.setVisible(false);
        colCedula.setCellValueFactory(new PropertyValueFactory<Paciente,String>("cedula"));
        colPrimerNombre.setCellValueFactory(new PropertyValueFactory<Paciente,String>("primernombre"));
        colSegundoNombre.setCellValueFactory(new PropertyValueFactory<Paciente,String>("segundonombre"));
        colPrimerApellido.setCellValueFactory(new PropertyValueFactory<Paciente,String>("primerapellido"));
        colSegundoApellido.setCellValueFactory(new PropertyValueFactory<Paciente,String>("segundoapellido"));
        colNhistorialClinico.setCellValueFactory(new PropertyValueFactory<Paciente,String>("nhistoriaclinica"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Paciente,String>("fechanacimiento"));
        tblPaciente.getColumns().setAll(
                colCedula,colNhistorialClinico,colPrimerApellido,colSegundoApellido,colPrimerNombre,colSegundoNombre,colFechaNacimiento);
        colCedula.setMinWidth(100);
        colPrimerApellido.setMinWidth(150);
        colSegundoApellido.setMinWidth(150);
        colPrimerNombre.setMinWidth(150);
        colSegundoNombre.setMinWidth(150);

       tblPaciente.setRowFactory(param ->{
           TableRow<Paciente> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
               if(event.getClickCount()==2 && (!row.isEmpty() )){
                   pacienteSeleccionado = row.getItem();
                   tblPaciente.getScene().getWindow().hide();
               }
           });
           return row;
       });

    }


    public void buscar(){
        try {

            Task<Void> tareaBuscar = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                  //  if (!txtTextoBuscar.getText().trim().equals("")) {
                    try {
                        mskEsperar.setVisible(true);
                        PacienteDao pDao = new PacienteDao();
                        tblPaciente.setItems(pDao.getPacientesByCedNomApe(txtTextoBuscar.getText().trim()));
                        mskEsperar.setVisible(false);
                    }catch (Exception ex){
                        FxDialogs.showException("Error","Ha ocurrido un error",ex);
                    }
                  //}
                    return null;
                }
            };


            Thread threadGuardar = new Thread(tareaBuscar);
            threadGuardar.setDaemon(true);
            threadGuardar.start();

        }catch (Exception ex){
            FxDialogs.showException("Se ha generado un error!!","Dar click para ver mas detalles...",ex);
            mskEsperar.setVisible(false);
        }

    }



}

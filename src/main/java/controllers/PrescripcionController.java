package controllers;

import Dao.MedicinaDao;
import Dao.Otros;
import Dao.PrescripcionDao;
import entities.Medicina;
import entities.Paciente;
import entities.Prescripcion;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.textfield.CustomTextField;
import org.hibernate.mapping.Formula;
import utilidades.Formularios;
import utilidades.FxDialogs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class PrescripcionController {

    @FXML
    private StackPane stackContainer;

    @FXML
    private CustomTextField txtCedula;

    @FXML
    private CustomTextField txtApellidosNombres;

    @FXML
    private CustomTextField txtrNHistoriaLaboral;

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextArea txtMedicamentos;

    @FXML
    private TextArea txtPrescripcion;
    @FXML private MaskerPane esperaMskPane;
    private Medicina medicina;
    private Paciente paciente;
    private Boolean esNuevo;
    @FXML private TextArea txtObservacion;
    private NotificationPane notPane;
    private Prescripcion prescripcion;



    public void Nuevo(){
        txtPrescripcion.setDisable(false);
        txtMedicamentos.setDisable(false);
        txtObservacion.setDisable(false);
        btnGuardar.setDisable(false);
        prescripcion = new Prescripcion();
        esNuevo=true;
        limpiarControles();
    }

    public void ShowBuscar() {
    }
    public void imprimir() throws JRException {


    }

    public void Guardar() {

        try {
            esperaMskPane.setVisible(true);
            prescripcion.setObservacion(txtObservacion.getText());
            prescripcion.setMedicamentos(txtMedicamentos.getText());
            prescripcion.setPrescripcion(txtPrescripcion.getText());
            prescripcion.setFechaPrescripcion(LocalDateTime.now());
            prescripcion.setPacienteId(paciente.getId());
            PrescripcionDao mDao = new PrescripcionDao();
            Task<Boolean> tareaGuardar = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    Boolean result = false;
                    if (mDao.guardar(prescripcion, esNuevo)) {
                        result = true;
                    }
                    return result;
                }
            };

            tareaGuardar.setOnSucceeded(e -> {
                Otros o = new Otros();
                notPane = (NotificationPane) stackContainer.getParent();
                if (tareaGuardar.getValue()) {
                    esNuevo = false;
                    o.showNotificacionPane(notPane, "Datos guardados correctamente.", true);
                } else {
                    o.showNotificacionPane(notPane, "No se pudo guardar los datos.", false);
                }
                esperaMskPane.setVisible(false);
            });

            Thread threadGuardar = new Thread(tareaGuardar);
            threadGuardar.setDaemon(true);
            threadGuardar.start();
        } catch (Exception ex) {
            FxDialogs.showException("Se ha generado un error!!", "Dar click para ver mas detalles...", ex);
            esperaMskPane.setVisible(false);
        }

    }

    public void initialize(){
        txtPrescripcion.setDisable(true);
        txtMedicamentos.setDisable(true);
        txtObservacion.setDisable(true);

        //no implementado la busqueda de prescripciones
      //  btnBuscar.setVisible(false);
    }

    public void showBuscarMedicina() {
        Formularios f = new Formularios();
       medicina= f.showBusquedaMedicina();
       if(medicina!=null){
           if(txtMedicamentos.getText().isEmpty() && txtPrescripcion.getText().isEmpty()){
               txtMedicamentos.appendText(medicina.getDescripcion());
               txtPrescripcion.appendText(medicina.getPrescripcion());
           }else{
               txtMedicamentos.appendText("\n\n");
               txtMedicamentos.appendText(medicina.getDescripcion());

               txtPrescripcion.appendText("\n\n");
               txtPrescripcion.appendText(medicina.getPrescripcion());
           }

       }
    }

    public void showPaciente(){
        esperaMskPane.setVisible(true);
        Formularios f = new Formularios();
        paciente= f.showBusquedaPaciente();
        if(paciente!=null){
            //panelFicha.setDisable(false);
            String apellidosNombres =paciente.getPrimerapellido().toUpperCase() +" "+paciente.getSegundoapellido().toUpperCase() + " "+
                    paciente.getPrimernombre().toUpperCase()+" "+paciente.getSegundonombre().toUpperCase();
            txtCedula.setText(paciente.getCedula());
            txtApellidosNombres.setText(apellidosNombres);
            txtrNHistoriaLaboral.setText(paciente.getNhistoriaclinica());
            btnNuevo.setDisable(false);
            btnGuardar.setDisable(false);
           // btnBuscar.setDisable(false);
        }else{
            txtCedula.clear();
            txtrNHistoriaLaboral.clear();
            txtApellidosNombres.clear();
            btnNuevo.setDisable(true);
            btnGuardar.setDisable(true);
           // btnBuscar.setDisable(true);

        }
        esNuevo=true;
        limpiarControles();
        esperaMskPane.setVisible(false);
    }

    private void limpiarControles() {
        txtPrescripcion.clear();
        txtMedicamentos.clear();
        txtObservacion.clear();
    }

}

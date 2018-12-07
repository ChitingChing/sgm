package controllers;

import Dao.MedicinaDao;
import Dao.Otros;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Medicina;
import entities.Paciente;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.Formularios;
import utilidades.FxDialogs;

public class MedicinaController {

    @FXML
    private StackPane stackContainer;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnGuardar;

    @FXML
    private CustomTextField txtDescripcion;

    @FXML
    private TextArea txtPrescripcion;
    @FXML private MaskerPane esperaMskPane;
    @FXML private GridPane gridDatos;
    public Medicina medicina;
    private Boolean esNuevo;
    private NotificationPane notPane;

    public void Nuevo(){
        medicina = new Medicina();
        txtDescripcion.clear();
        txtPrescripcion.clear();
        esNuevo=true;
        gridDatos.setDisable(false);
        btnGuardar.setDisable(false);
    }
    public void ShowBuscar(){
        esperaMskPane.setVisible(true);
        Formularios f = new Formularios();
        medicina=f.showBusquedaMedicina();
        cargarDatos(medicina);
        esperaMskPane.setVisible(false);

    }

    private void cargarDatos(Medicina m) {
        if(m != null){
            txtDescripcion.setText(m.getDescripcion());
            txtPrescripcion.setText(m.getPrescripcion());
            esNuevo=false;
            btnGuardar.setDisable(false);
            gridDatos.setDisable(false);
        }else{
            txtDescripcion.clear();
            txtPrescripcion.clear();
            esNuevo=true;
            gridDatos.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    public void Guardar() {
        try {
            medicina.setDescripcion(txtDescripcion.getText());
            medicina.setPrescripcion(txtPrescripcion.getText());
            MedicinaDao mDao = new MedicinaDao();
            Task<Boolean> tareaGuardar = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    Boolean result = false;
                    if (mDao.guardarMedicina(medicina, esNuevo)) {
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
}

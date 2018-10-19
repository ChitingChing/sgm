package controllers;

import Dao.EmpresaDao;
import Dao.Otros;
import entities.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.FxDialogs;

import java.util.UUID;

public class EmpresaController {
    @FXML
    private Button btnGuardar;

    @FXML
    private CustomTextField txtRuc;

    @FXML
    private CustomTextField txtNombre;

    @FXML
    private CustomTextField txtDireccion;

    @FXML
    private CustomTextField txtTelefono1;

    @FXML
    private CustomTextField txtTelefono2;

    @FXML
    private CustomTextField txtGerente;
    
    @FXML private StackPane stackContainer;

    private Empresa e;
    private NotificationPane notPane;

    public void initialize(){
        try {
            cargarDatos();
        }catch (Exception ex){
            FxDialogs.showException("Error","Error al cargar datos",ex);
        }
    }
    private void cargarDatos(){
        EmpresaDao eDao= new EmpresaDao();
        e = eDao.getEmpresaDatos();
        txtRuc.setText(e.getRuc());
        txtNombre.setText(e.getNombre());
        txtDireccion.setText(e.getDireccion());
        txtTelefono1.setText(e.getTelefono1());
        txtTelefono2.setText(e.getTelefono2());
        txtGerente.setText(e.getGerente());
    }

    public void guardarEmpresa(){
        Otros o = new Otros();
        notPane = (NotificationPane) stackContainer.getParent();
        Boolean ins=false;
        if(e==null){
            e = new Empresa();
            e.setId(UUID.randomUUID());
            ins=true;
        }
        e.setRuc(txtRuc.getText());
        e.setNombre(txtNombre.getText());
        e.setDireccion(txtDireccion.getText());
        e.setTelefono1(txtTelefono1.getText());
        e.setTelefono2(txtTelefono2.getText());
        e.setGerente(txtGerente.getText());
        EmpresaDao eDAo = new EmpresaDao();
       if( eDAo.guardarPaciente(e,ins)){
           o.showNotificacionPane(notPane, "Datos guardados correctamente.",true  );
       }else{
           o.showNotificacionPane(notPane, "No se han guardado los datos.",false  );
       }

    }
}

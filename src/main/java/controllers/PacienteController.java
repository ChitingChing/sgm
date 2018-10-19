package controllers;

import Dao.Otros;
import Dao.PacienteDao;
import entities.Canton;
import entities.Paciente;
import entities.Provincia;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.*;

import java.io.IOException;

public class PacienteController {

    @FXML
    private CustomTextField txtCedula;

    @FXML
    private CustomTextField txtNHistoriaclinica;

    @FXML
    private CustomTextField txtPrimerApellido;

    @FXML
    private CustomTextField txtSegundoApellido;

    @FXML
    private CustomTextField txtPrimerNombre;

    @FXML
    private CustomTextField txtSegundonombre;

    @FXML
    private CustomTextField txtDireccion;

    @FXML
    private CustomTextField txtLocalidad;

    @FXML
    private PrefixSelectionComboBox<Provincia> cmbProvincia;

    @FXML
    private PrefixSelectionComboBox<Canton> cmbCanton;

    @FXML
    private DatePicker dtpFechaNac;

    @FXML
    private CheckBox chkAlfabeta;

    @FXML
    private PrefixSelectionComboBox<String> cmbEstadoCivil;

    @FXML
    private PrefixSelectionComboBox<String> cmbEstudios;

    @FXML

    private CustomTextField txtEmail;

    @FXML
    private CustomTextField txtCelular;

    @FXML
    private VBox vBoxContainer;
    @FXML
    private StackPane stackContainer;
    @FXML
    private GridPane gridPaneControles;
    @FXML
    private MaskerPane esperaMskPane;
    ////
    private static Paciente paciente;
    private Provincia provinciaDefault;
    private Canton cantonDefault;
    private Boolean nuevo=true;
    private FxValidations validations = new FxValidations();
    private  NotificationPane notPane;
    @FXML private Button btnGuardar;
   @FXML
   public void initialize() {

        try{
            btnGuardar.setDisable(true);
            gridPaneControles.setDisable(true);
            txtNHistoriaclinica.setDisable(true);
            iniciarCombos();
            iniciarValidadores();
        }
        catch (Exception ex) {
            FxDialogs.showException("Error al iniciar", "Ha ocurrido un error al iniciar el formulario", ex);
        }

   }
   private void iniciarCombos(){
       cmbProvincia.getItems().setAll(InicioController.provincias);
       cmbEstadoCivil.getItems().setAll(InicioController.estadoCiviles);
       cmbEstudios.getItems().setAll(InicioController.estudios);
       cmbCanton.getItems().setAll(InicioController.cantonesLosRios);

       provinciaDefault = InicioController.provincias.stream().filter(x-> x.getId().equals("12")).findFirst().get();
       cantonDefault = InicioController.cantonesLosRios.stream().filter(x-> x.getId().equals("1205")).findFirst().get();
       cmbProvincia.getSelectionModel().select(provinciaDefault);
       cmbCanton.getSelectionModel().select(cantonDefault);
       cmbEstudios.getSelectionModel().select(0);
       cmbEstadoCivil.getSelectionModel().select(0);

       //Convertidores de combobox para enviar un objeto y mostrar su descripcion
       cmbProvincia.setConverter(new StringConverter<Provincia>() {
           @Override
           public String toString(Provincia object) {
               return object == null ? null : object.getDescripcion();
           }

           @Override
           public Provincia fromString(String string) {
               return cmbProvincia.getItems().stream().filter(i -> i.getDescripcion().equals(string)).findAny().orElse(null);
           }
       });
       cmbCanton.setConverter(new StringConverter<Canton>() {
           @Override
           public String toString(Canton object) {
               return object == null ? null : object.getDescripcion();
           }

           @Override
           public Canton fromString(String string) {
               return cmbCanton.getItems().stream().filter(i -> i.getDescripcion().equals(string)).findAny().orElse(null);
           }
       });
   }

   private void iniciarValidadores(){
       ValidationSupport validationSupport = validations.getValidationSupport();
       validationSupport.registerValidator(txtCedula,Validator.createRegexValidator("Ingrese una cédula",Regex.CEDULA.getExpresion(), Severity.ERROR));
       validationSupport.registerValidator(txtEmail, Validator.createRegexValidator("Ingrese un email válido", Regex.EMAIL.getExpresion(),Severity.ERROR));
       validationSupport.registerValidator(txtPrimerNombre,Validator.combine(
               Validator.createRegexValidator("Ingrese solo letras en el primer nombre",Regex.LETRASESPACIO.getExpresion(),Severity.ERROR),
               Validator.createEmptyValidator("Ingrese un primer nombre.")));
       validationSupport.registerValidator(txtPrimerApellido,Validator.combine(
               Validator.createRegexValidator("Ingrese solo letras en el primer apellido",Regex.LETRASESPACIO.getExpresion(),Severity.ERROR),
               Validator.createEmptyValidator("Ingrese un primer apellido.")));
       validationSupport.registerValidator(txtSegundoApellido,Validator.combine(
               Validator.createRegexValidator("Ingrese solo letras en el segundo apellido",Regex.LETRASESPACIO.getExpresion(),Severity.ERROR),
               Validator.createEmptyValidator("Ingrese un segundo apellido.")));
       validationSupport.registerValidator(txtSegundonombre,Validator.createRegexValidator("Ingrese solo letras en el segundo nombre",Regex.LETRASESPACIO.getExpresion(),Severity.ERROR));
       validationSupport.registerValidator(txtCelular,Validator.createRegexValidator("Ingrese un numero telefónico",Regex.TELEFONO.getExpresion(), Severity.ERROR));

       validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->
       {
           validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
           validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
       });
   }

   public void showError(){

   }
    public void guardarPaciente() {
        try {

           if( validations.getValidationSupport().getValidationResult().getErrors().size()>0) {
               FxDialogs.showInformation("Error al ingresar datos", "Revise los errores antes de continuar.");
               return;
           }
            paciente= existePaciente(txtCedula.getText().trim());
            if(paciente!=null && nuevo){
                FxDialogs.showError("Cédula ya se encuentra registrada.","Ya se encuentra registrada esta cédula. Favor ingresar otra.");
                return;
            }
            else if(paciente==null){
                paciente = new Paciente();
            }
            esperaMskPane.setVisible(true);
            paciente.setCedula(txtCedula.getText().trim());
            //paciente.setNhistoriaclinica();
            paciente.setPrimernombre(txtPrimerNombre.getText().trim());
            paciente.setSegundonombre(txtSegundonombre.getText().trim());
            paciente.setPrimerapellido(txtPrimerApellido.getText().trim());
            paciente.setSegundoapellido(txtSegundoApellido.getText().trim());
            paciente.setLocalidad(txtLocalidad.getText().trim());
            paciente.setDireccion(txtDireccion.getText().trim());
            paciente.setProvincia(cmbProvincia.getSelectionModel().getSelectedItem().getId());
            paciente.setCanton(cmbCanton.getSelectionModel().getSelectedItem().getId());
            paciente.setFechanacimiento(dtpFechaNac.getValue());
            paciente.setEmail(txtEmail.getText().trim());
            paciente.setCelular(txtCelular.getText().trim());
            paciente.setEstadocivil(cmbEstadoCivil.getSelectionModel().getSelectedItem());
            paciente.setEstudios(cmbEstudios.getSelectionModel().getSelectedItem());
            paciente.setAlfabeta(chkAlfabeta.selectedProperty().getValue());
            PacienteDao pDao = new PacienteDao();

            Task<Boolean> tareaGuardar = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    Boolean result = false;
                    System.out.println("Entro");
                    if( pDao.guardarPaciente(paciente,nuevo)){
                        System.out.println("guardo");
                        result=true;
                    }
                    return result;
                }
            };

            tareaGuardar.setOnSucceeded(e->{
                Otros o = new Otros();
                notPane = (NotificationPane) stackContainer.getParent();
                if(tareaGuardar.getValue()) {
                    txtNHistoriaclinica.setText(paciente.getNhistoriaclinica());
                    nuevo = false;
                    txtCedula.setDisable(true);
                    o.showNotificacionPane(notPane, "Datos guardados correctamente.",true  );
                }else{
                    o.showNotificacionPane(notPane, "No se pudo guardar los datos.",false  );
                }
                esperaMskPane.setVisible(false);
            });

            Thread threadGuardar = new Thread(tareaGuardar);
            threadGuardar.setDaemon(true);
            threadGuardar.start();

        } catch (Exception ex) {
            FxDialogs.showException("Se ha generado un error!!","Dar click para ver mas detalles...",ex);
            esperaMskPane.setVisible(false);
        }

    }

    public void nuevoPaciente(){
       nuevo=true;
       gridPaneControles.setDisable(false);
       paciente = new Paciente();
       limpiarControles();
        btnGuardar.setDisable(false);
    }

    private Paciente existePaciente(String Cedula){
        Boolean existe = false;
        PacienteDao pDao = new PacienteDao();
        Paciente p = pDao.getPaciente(Cedula);

       return p;
    }
    private void limpiarControles(){
       txtCedula.setDisable(false);
        txtCedula.clear();
        txtNHistoriaclinica.clear();
        txtPrimerNombre.clear();
        txtSegundonombre.clear();
        txtPrimerApellido.clear();
        txtSegundoApellido.clear();
        txtLocalidad.clear();
        txtDireccion.clear();
        cmbProvincia.getSelectionModel().select(provinciaDefault);
        cmbCanton.getSelectionModel().select(cantonDefault);
        dtpFechaNac.setValue(null);
        txtEmail.clear();
        txtCelular.clear();
        cmbEstadoCivil.getSelectionModel().select(0);
        cmbEstudios.getSelectionModel().select(0);
        chkAlfabeta.setSelected(false);
    }

    public void getCantonesProvincia(){
        cmbCanton.getItems().clear();
        cmbCanton.getItems().setAll(Otros.getCantones(cmbProvincia.getSelectionModel().getSelectedItem().getId()));
        cmbCanton.getSelectionModel().select(0);
    }

    public void showBuscarPaciente(){

        esperaMskPane.setVisible(true);
        Formularios f = new Formularios();
        cargarDatos(f.showBusquedaPaciente());
        esperaMskPane.setVisible(false);
    }
    public  void cargarDatos(Paciente p) {
       try {
            if(p!=null){
                Otros o = new Otros();
                nuevo = false;
                gridPaneControles.setDisable(false);
                txtCedula.setDisable(true);
                txtCedula.setText(p.getCedula());
                txtNHistoriaclinica.setText(p.getNhistoriaclinica());
                txtPrimerApellido.setText(p.getPrimerapellido());
                txtSegundoApellido.setText(p.getSegundoapellido());
                txtPrimerNombre.setText(p.getPrimernombre());
                txtSegundonombre.setText(p.getSegundonombre());
                txtLocalidad.setText(p.getLocalidad());
                txtDireccion.setText(p.getDireccion());
                txtEmail.setText(p.getEmail());
                txtCelular.setText(p.getCelular());
                cmbProvincia.getSelectionModel().select(o.getProvincia(p.getProvincia()));
                cmbCanton.getSelectionModel().select(o.getCanton(p.getCanton()));
                dtpFechaNac.setValue(p.getFechanacimiento());
                cmbEstadoCivil.getSelectionModel().select(p.getEstadocivil());
                cmbEstudios.getSelectionModel().select(p.getEstudios());
                chkAlfabeta.setSelected(p.getAlfabeta());
                btnGuardar.setDisable(false);
            }else{
                limpiarControles();
                btnGuardar.setDisable(true);
            }
       }catch   (Exception e){
           FxDialogs.showException("Error","Error al cargar datos",e);
       }

    }
}

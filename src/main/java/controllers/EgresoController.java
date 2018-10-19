package controllers;

import Dao.Otros;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import utilidades.FxValidations;
import utilidades.Regex;

public class EgresoController {

    @FXML
    private TitledPane tpaneEgreso;

    @FXML
    private DatePicker dtpFechaEgresoRn;

    @FXML
    private CustomTextField txtPesoEgresoRn;

    @FXML
    private PrefixSelectionComboBox<String> cmbAlimento;

    @FXML
    private PrefixSelectionComboBox<String> cmbEstadoRn;

    @FXML
    private CustomTextField txtResponsableRn;

    @FXML
    private DatePicker dtpFechaEgreso;

    @FXML
    private PrefixSelectionComboBox<String> cmbAnticoncepcion;

    @FXML
    private PrefixSelectionComboBox<String> cmbEstadoMadre;

    @FXML
    private CustomTextField txtResponsableMadre;

    @FXML
    private GridPane gridPaneEgreso;

    private FxValidations validations = new FxValidations();

    public TitledPane getTpaneEgreso() {
        return tpaneEgreso;
    }

    public void setTpaneEgreso(TitledPane tpaneEgreso) {
        this.tpaneEgreso = tpaneEgreso;
    }

    public DatePicker getDtpFechaEgresoRn() {
        return dtpFechaEgresoRn;
    }

    public void setDtpFechaEgresoRn(DatePicker dtpFechaEgresoRn) {
        this.dtpFechaEgresoRn = dtpFechaEgresoRn;
    }

    public CustomTextField getTxtPesoEgresoRn() {
        return txtPesoEgresoRn;
    }

    public void setTxtPesoEgresoRn(CustomTextField txtPesoEgresoRn) {
        this.txtPesoEgresoRn = txtPesoEgresoRn;
    }

    public PrefixSelectionComboBox<String> getCmbAlimento() {
        return cmbAlimento;
    }

    public void setCmbAlimento(PrefixSelectionComboBox<String> cmbAlimento) {
        this.cmbAlimento = cmbAlimento;
    }

    public PrefixSelectionComboBox<String> getCmbEstadoRn() {
        return cmbEstadoRn;
    }

    public void setCmbEstadoRn(PrefixSelectionComboBox<String> cmbEstadoRn) {
        this.cmbEstadoRn = cmbEstadoRn;
    }

    public CustomTextField getTxtResponsableRn() {
        return txtResponsableRn;
    }

    public void setTxtResponsableRn(CustomTextField txtResponsableRn) {
        this.txtResponsableRn = txtResponsableRn;
    }

    public DatePicker getDtpFechaEgreso() {
        return dtpFechaEgreso;
    }

    public void setDtpFechaEgreso(DatePicker dtpFechaEgreso) {
        this.dtpFechaEgreso = dtpFechaEgreso;
    }

    public PrefixSelectionComboBox<String> getCmbAnticoncepcion() {
        return cmbAnticoncepcion;
    }

    public void setCmbAnticoncepcion(PrefixSelectionComboBox<String> cmbAnticoncepcion) {
        this.cmbAnticoncepcion = cmbAnticoncepcion;
    }

    public PrefixSelectionComboBox<String> getCmbEstadoMadre() {
        return cmbEstadoMadre;
    }

    public void setCmbEstadoMadre(PrefixSelectionComboBox<String> cmbEstadoMadre) {
        this.cmbEstadoMadre = cmbEstadoMadre;
    }

    public CustomTextField getTxtResponsableMadre() {
        return txtResponsableMadre;
    }

    public void setTxtResponsableMadre(CustomTextField txtResponsableMadre) {
        this.txtResponsableMadre = txtResponsableMadre;
    }

    public GridPane getGridPaneEgreso() {
        return gridPaneEgreso;
    }

    public void setGridPaneEgreso(GridPane gridPaneEgreso) {
        this.gridPaneEgreso = gridPaneEgreso;
    }

    public void initialize(){
        try{
            iniciarCombos();
            iniciarValidadores();
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }
    }

    private void iniciarCombos(){
        cmbAlimento.getItems().addAll(Otros.getAlimentos());
        cmbAnticoncepcion.getItems().addAll(Otros.getAnticoncepcion());
        cmbEstadoMadre.getItems().addAll(Otros.getEstadoEgreso());
        cmbEstadoRn.getItems().addAll(Otros.getEstadoEgreso());

        gridPaneEgreso.getChildren().filtered(x-> x instanceof PrefixSelectionComboBox).forEach(x-> {
            x.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)
                    ((PrefixSelectionComboBox)x).getSelectionModel().clearSelection();
            });
        });
    }
    private void iniciarValidadores(){
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtPesoEgresoRn, Validator.createRegexValidator("Ingrese un peso válido. Formato (##.##).", Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }


    public void limpiarControles(){
        gridPaneEgreso.getChildren().forEach(node -> Formularios.limpiarControles(node));
    }
}

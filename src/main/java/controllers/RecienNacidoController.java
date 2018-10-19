package controllers;

import Dao.Otros;
import entities.Patologia;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import utilidades.FxValidations;
import utilidades.Regex;

public class RecienNacidoController {

    @FXML
    private TitledPane tpaneRecienNacido;

    @FXML
    private GridPane gridPaneRecienN;

    @FXML
    private PrefixSelectionComboBox<String> cmbSexo;

    @FXML
    private PrefixSelectionComboBox<String> cmbPesoEg;

    @FXML
    private PrefixSelectionComboBox<String> cmbReanimacion;

    @FXML
    private PrefixSelectionComboBox<String> cmbVdrlRn;

    @FXML
    private CustomTextField txtPesoNacer;

    @FXML
    private CustomTextField txtTallaRn;

    @FXML
    private CustomTextField txtPerCefalico;

    @FXML
    private CustomTextField txtEdadExFisico;

    @FXML
    private CustomTextField txtApgar1Min;

    @FXML
    private CustomTextField txtApgar6Min;

    @FXML
    private PrefixSelectionComboBox<String> cmbExFisico;

    @FXML
    private PrefixSelectionComboBox<String> cmbGrupoSanguineoRn;

    @FXML
    private TextArea txtObservacionesRn;

    @FXML
    private CheckBox chkAloj;

    @FXML
    private CheckBox chkHospitalizado;

    @FXML
    private CheckBox chkBcg;

    @FXML
    private CheckBox chkPvo;

    @FXML
    private PrefixSelectionComboBox<String> cmbRhRn;

    @FXML
    private CheckComboBox<Patologia> chkcmbPatologiasRn;

    private FxValidations validations = new FxValidations();

    public TitledPane getTpaneRecienNacido() {
        return tpaneRecienNacido;
    }

    public void setTpaneRecienNacido(TitledPane tpaneRecienNacido) {
        this.tpaneRecienNacido = tpaneRecienNacido;
    }

    public GridPane getGridPaneRecienN() {
        return gridPaneRecienN;
    }

    public void setGridPaneRecienN(GridPane gridPaneRecienN) {
        this.gridPaneRecienN = gridPaneRecienN;
    }

    public PrefixSelectionComboBox<String> getCmbSexo() {
        return cmbSexo;
    }

    public void setCmbSexo(PrefixSelectionComboBox<String> cmbSexo) {
        this.cmbSexo = cmbSexo;
    }

    public PrefixSelectionComboBox<String> getCmbPesoEg() {
        return cmbPesoEg;
    }

    public void setCmbPesoEg(PrefixSelectionComboBox<String> cmbPesoEg) {
        this.cmbPesoEg = cmbPesoEg;
    }

    public PrefixSelectionComboBox<String> getCmbReanimacion() {
        return cmbReanimacion;
    }

    public void setCmbReanimacion(PrefixSelectionComboBox<String> cmbReanimacion) {
        this.cmbReanimacion = cmbReanimacion;
    }

    public PrefixSelectionComboBox<String> getCmbVdrlRn() {
        return cmbVdrlRn;
    }

    public void setCmbVdrlRn(PrefixSelectionComboBox<String> cmbVdrlRn) {
        this.cmbVdrlRn = cmbVdrlRn;
    }

    public CustomTextField getTxtPesoNacer() {
        return txtPesoNacer;
    }

    public void setTxtPesoNacer(CustomTextField txtPesoNacer) {
        this.txtPesoNacer = txtPesoNacer;
    }

    public CustomTextField getTxtTallaRn() {
        return txtTallaRn;
    }

    public void setTxtTallaRn(CustomTextField txtTallaRn) {
        this.txtTallaRn = txtTallaRn;
    }

    public CustomTextField getTxtPerCefalico() {
        return txtPerCefalico;
    }

    public void setTxtPerCefalico(CustomTextField txtPerCefalico) {
        this.txtPerCefalico = txtPerCefalico;
    }

    public CustomTextField getTxtEdadExFisico() {
        return txtEdadExFisico;
    }

    public void setTxtEdadExFisico(CustomTextField txtEdadExFisico) {
        this.txtEdadExFisico = txtEdadExFisico;
    }

    public CustomTextField getTxtApgar1Min() {
        return txtApgar1Min;
    }

    public void setTxtApgar1Min(CustomTextField txtApgar1Min) {
        this.txtApgar1Min = txtApgar1Min;
    }

    public CustomTextField getTxtApgar6Min() {
        return txtApgar6Min;
    }

    public void setTxtApgar6Min(CustomTextField txtApgar6Min) {
        this.txtApgar6Min = txtApgar6Min;
    }

    public PrefixSelectionComboBox<String> getCmbExFisico() {
        return cmbExFisico;
    }

    public void setCmbExFisico(PrefixSelectionComboBox<String> cmbExFisico) {
        this.cmbExFisico = cmbExFisico;
    }

    public PrefixSelectionComboBox<String> getCmbGrupoSanguineoRn() {
        return cmbGrupoSanguineoRn;
    }

    public void setCmbGrupoSanguineoRn(PrefixSelectionComboBox<String> cmbGrupoSanguineoRn) {
        this.cmbGrupoSanguineoRn = cmbGrupoSanguineoRn;
    }

    public TextArea getTxtObservacionesRn() {
        return txtObservacionesRn;
    }

    public void setTxtObservacionesRn(TextArea txtObservacionesRn) {
        this.txtObservacionesRn = txtObservacionesRn;
    }

    public CheckBox getChkAloj() {
        return chkAloj;
    }

    public void setChkAloj(CheckBox chkAloj) {
        this.chkAloj = chkAloj;
    }

    public CheckBox getChkHospitalizado() {
        return chkHospitalizado;
    }

    public void setChkHospitalizado(CheckBox chkHospitalizado) {
        this.chkHospitalizado = chkHospitalizado;
    }

    public CheckBox getChkBcg() {
        return chkBcg;
    }

    public void setChkBcg(CheckBox chkBcg) {
        this.chkBcg = chkBcg;
    }

    public CheckBox getChkPvo() {
        return chkPvo;
    }

    public void setChkPvo(CheckBox chkPvo) {
        this.chkPvo = chkPvo;
    }

    public PrefixSelectionComboBox<String> getCmbRhRn() {
        return cmbRhRn;
    }

    public void setCmbRhRn(PrefixSelectionComboBox<String> cmbRhRn) {
        this.cmbRhRn = cmbRhRn;
    }

    public CheckComboBox<Patologia> getChkcmbPatologiasRn() {
        return chkcmbPatologiasRn;
    }

    public void setChkcmbPatologiasRn(CheckComboBox<Patologia> chkcmbPatologiasRn) {
        this.chkcmbPatologiasRn = chkcmbPatologiasRn;
    }

    public void initialize() {
        try {
            iniciarCombos();
            iniciarValidadores();
        } catch (Exception ex) {
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }
    }

    private void iniciarCombos() {
        cmbSexo.getItems().setAll(Otros.getSexo());
        cmbPesoEg.getItems().setAll(Otros.getPeso());
        cmbReanimacion.getItems().setAll(Otros.getReanimacionRespiratoria());
        cmbVdrlRn.getItems().setAll(Otros.getVdrl());
        cmbExFisico.getItems().setAll(Otros.getExamenFisico());
        chkcmbPatologiasRn.getItems().setAll(Otros.getPatologias("reciennacido"));
        cmbGrupoSanguineoRn.getItems().setAll(Otros.getGrupoSangre());
        cmbRhRn.getItems().setAll(Otros.getRH());

        chkcmbPatologiasRn.setConverter(new StringConverter<Patologia>() {
            @Override
            public String toString(Patologia object) {
                return object == null ? null : object.getDescripcion();
            }

            @Override
            public Patologia fromString(String string) {
                return chkcmbPatologiasRn.getItems().stream().filter(i -> i.getDescripcion().equals(string)).findAny().orElse(null);
            }
        });


        gridPaneRecienN.getChildren().filtered(x -> x instanceof PrefixSelectionComboBox).forEach(x -> {
            x.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)
                    ((PrefixSelectionComboBox) x).getSelectionModel().clearSelection();
            });
        });
    }

    private void iniciarValidadores() {
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtPesoNacer, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtTallaRn, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtPerCefalico, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtEdadExFisico, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtApgar1Min, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtApgar6Min, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }

    public void limpiarControles(){

        gridPaneRecienN.getChildren().forEach(node -> Formularios.limpiarControles(node));
    }

}

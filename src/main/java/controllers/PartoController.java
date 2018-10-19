package controllers;

import Dao.Otros;
import entities.Patologia;
import entities.Provincia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

import javax.validation.Validation;

public class PartoController {
    @FXML
    private TitledPane tpaneParto;

    @FXML
    private GridPane gridPaneParto;

    @FXML
    private CheckBox chkAborto;

    @FXML
    private Spinner<Integer> txtNConsultaPrenatal;

    @FXML
    private CheckBox chkHospital;

    @FXML
    private CheckBox chkCarnet;

    @FXML
    private DatePicker dtpFechaIngreso;

    @FXML
    private CustomTextField txtOrigen;

    @FXML
    private CustomTextField txtTemperaturaParto;

    @FXML
    private CustomTextField txtEdadGestacional;

    @FXML
    private PrefixSelectionComboBox<String> cmbPresentacionParto;

    @FXML
    private CheckBox chkTamanoFetalAdecuado;

    @FXML
    private PrefixSelectionComboBox<String> cmbInicioParto;

    @FXML
    private PrefixSelectionComboBox<String> cmbMembranas;

    @FXML
    private DatePicker dtpFechaRuptura;

    @FXML
    private CustomTextField txtHoraRuptura;

    @FXML
    private CustomTextField txtHoraTerminacion;

    @FXML
    private CustomTextField txtIndicacion;

    @FXML
    private DatePicker dtpFechaTerminacion;

    @FXML
    private PrefixSelectionComboBox<String> cmbTerminacion;

    @FXML
    private PrefixSelectionComboBox<String> cmbMuerteIntraUte;

    @FXML
    private PrefixSelectionComboBox<String> cmbNivelAtencion;

    @FXML
    private PrefixSelectionComboBox<String> cmbAtendioParto;

    @FXML
    private PrefixSelectionComboBox<String> cmbAtendioNeonato;

    @FXML
    private CheckBox chkEpisotomia;

    @FXML
    private CheckBox chkDesgarros;

    @FXML
    private CheckBox chkAlumbEsponta;

    @FXML
    private CheckBox chkPlacentaCompl;

    @FXML
    private CustomTextField txtnombreAtenParto;

    @FXML
    private CustomTextField txtnombreRn;

    @FXML
    private CustomTextField txtnombreAtenNeo;

    @FXML
    private CheckComboBox<Patologia> chkcmbPatologiasParto;
    @FXML
    private CheckComboBox<Patologia> chkcmbPatologiasPP;

    @FXML
    private CheckComboBox<String> chkcmbMedicacionParto;

    @FXML
    private CustomTextField txtHoraIngreso;

    public CheckComboBox<Patologia> getChkcmbPatologiasPP() {
        return chkcmbPatologiasPP;
    }

    public void setChkcmbPatologiasPP(CheckComboBox<Patologia> chkcmbPatologiasPP) {
        this.chkcmbPatologiasPP = chkcmbPatologiasPP;
    }

    public CustomTextField getTxtHoraIngreso() {
        return txtHoraIngreso;
    }

    public void setTxtHoraIngreso(CustomTextField txtHoraIngreso) {
        this.txtHoraIngreso = txtHoraIngreso;
    }

    public TitledPane getTpaneParto() {
        return tpaneParto;
    }

    public void setTpaneParto(TitledPane tpaneParto) {
        this.tpaneParto = tpaneParto;
    }

    public GridPane getGridPaneParto() {
        return gridPaneParto;
    }

    public void setGridPaneParto(GridPane gridPaneParto) {
        this.gridPaneParto = gridPaneParto;
    }

    public CheckBox getChkAborto() {
        return chkAborto;
    }

    public void setChkAborto(CheckBox chkAborto) {
        this.chkAborto = chkAborto;
    }

    public Spinner<Integer> getTxtNConsultaPrenatal() {
        return txtNConsultaPrenatal;
    }

    public void setTxtNConsultaPrenatal(Spinner<Integer> txtNConsultaPrenatal) {
        this.txtNConsultaPrenatal = txtNConsultaPrenatal;
    }

    public CheckBox getChkHospital() {
        return chkHospital;
    }

    public void setChkHospital(CheckBox chkHospital) {
        this.chkHospital = chkHospital;
    }

    public CheckBox getChkCarnet() {
        return chkCarnet;
    }

    public void setChkCarnet(CheckBox chkCarnet) {
        this.chkCarnet = chkCarnet;
    }

    public DatePicker getDtpFechaIngreso() {
        return dtpFechaIngreso;
    }

    public void setDtpFechaIngreso(DatePicker dtpFechaIngreso) {
        this.dtpFechaIngreso = dtpFechaIngreso;
    }

    public CustomTextField getTxtOrigen() {
        return txtOrigen;
    }

    public void setTxtOrigen(CustomTextField txtOrigen) {
        this.txtOrigen = txtOrigen;
    }

    public CustomTextField getTxtTemperaturaParto() {
        return txtTemperaturaParto;
    }

    public void setTxtTemperaturaParto(CustomTextField txtTemperaturaParto) {
        this.txtTemperaturaParto = txtTemperaturaParto;
    }

    public CustomTextField getTxtEdadGestacional() {
        return txtEdadGestacional;
    }

    public void setTxtEdadGestacional(CustomTextField txtEdadGestacional) {
        this.txtEdadGestacional = txtEdadGestacional;
    }

    public PrefixSelectionComboBox<String> getCmbPresentacionParto() {
        return cmbPresentacionParto;
    }

    public void setCmbPresentacionParto(PrefixSelectionComboBox<String> cmbPresentacionParto) {
        this.cmbPresentacionParto = cmbPresentacionParto;
    }

    public CheckBox getChkTamanoFetalAdecuado() {
        return chkTamanoFetalAdecuado;
    }

    public void setChkTamanoFetalAdecuado(CheckBox chkTamanoFetalAdecuado) {
        this.chkTamanoFetalAdecuado = chkTamanoFetalAdecuado;
    }

    public PrefixSelectionComboBox<String> getCmbInicioParto() {
        return cmbInicioParto;
    }

    public void setCmbInicioParto(PrefixSelectionComboBox<String> cmbInicioParto) {
        this.cmbInicioParto = cmbInicioParto;
    }

    public PrefixSelectionComboBox<String> getCmbMembranas() {
        return cmbMembranas;
    }

    public void setCmbMembranas(PrefixSelectionComboBox<String> cmbMembranas) {
        this.cmbMembranas = cmbMembranas;
    }

    public DatePicker getDtpFechaRuptura() {
        return dtpFechaRuptura;
    }

    public void setDtpFechaRuptura(DatePicker dtpFechaRuptura) {
        this.dtpFechaRuptura = dtpFechaRuptura;
    }

    public CustomTextField getTxtHoraRuptura() {
        return txtHoraRuptura;
    }

    public void setTxtHoraRuptura(CustomTextField txtHoraRuptura) {
        this.txtHoraRuptura = txtHoraRuptura;
    }

    public CustomTextField getTxtHoraTerminacion() {
        return txtHoraTerminacion;
    }

    public void setTxtHoraTerminacion(CustomTextField txtHoraTerminacion) {
        this.txtHoraTerminacion = txtHoraTerminacion;
    }

    public CustomTextField getTxtIndicacion() {
        return txtIndicacion;
    }

    public void setTxtIndicacion(CustomTextField txtIndicacion) {
        this.txtIndicacion = txtIndicacion;
    }

    public DatePicker getDtpFechaTerminacion() {
        return dtpFechaTerminacion;
    }

    public void setDtpFechaTerminacion(DatePicker dtpFechaTerminacion) {
        this.dtpFechaTerminacion = dtpFechaTerminacion;
    }

    public PrefixSelectionComboBox<String> getCmbTerminacion() {
        return cmbTerminacion;
    }

    public void setCmbTerminacion(PrefixSelectionComboBox<String> cmbTerminacion) {
        this.cmbTerminacion = cmbTerminacion;
    }

    public PrefixSelectionComboBox<String> getCmbMuerteIntraUte() {
        return cmbMuerteIntraUte;
    }

    public void setCmbMuerteIntraUte(PrefixSelectionComboBox<String> cmbMuerteIntraUte) {
        this.cmbMuerteIntraUte = cmbMuerteIntraUte;
    }

    public PrefixSelectionComboBox<String> getCmbNivelAtencion() {
        return cmbNivelAtencion;
    }

    public void setCmbNivelAtencion(PrefixSelectionComboBox<String> cmbNivelAtencion) {
        this.cmbNivelAtencion = cmbNivelAtencion;
    }

    public PrefixSelectionComboBox<String> getCmbAtendioParto() {
        return cmbAtendioParto;
    }

    public void setCmbAtendioParto(PrefixSelectionComboBox<String> cmbAtendioParto) {
        this.cmbAtendioParto = cmbAtendioParto;
    }

    public PrefixSelectionComboBox<String> getCmbAtendioNeonato() {
        return cmbAtendioNeonato;
    }

    public void setCmbAtendioNeonato(PrefixSelectionComboBox<String> cmbAtendioNeonato) {
        this.cmbAtendioNeonato = cmbAtendioNeonato;
    }

    public CheckBox getChkEpisotomia() {
        return chkEpisotomia;
    }

    public void setChkEpisotomia(CheckBox chkEpisotomia) {
        this.chkEpisotomia = chkEpisotomia;
    }

    public CheckBox getChkDesgarros() {
        return chkDesgarros;
    }

    public void setChkDesgarros(CheckBox chkDesgarros) {
        this.chkDesgarros = chkDesgarros;
    }

    public CheckBox getChkAlumbEsponta() {
        return chkAlumbEsponta;
    }

    public void setChkAlumbEsponta(CheckBox chkAlumbEsponta) {
        this.chkAlumbEsponta = chkAlumbEsponta;
    }

    public CheckBox getChkPlacentaCompl() {
        return chkPlacentaCompl;
    }

    public void setChkPlacentaCompl(CheckBox chkPlacentaCompl) {
        this.chkPlacentaCompl = chkPlacentaCompl;
    }

    public CustomTextField getTxtnombreAtenParto() {
        return txtnombreAtenParto;
    }

    public void setTxtnombreAtenParto(CustomTextField txtnombreAtenParto) {
        this.txtnombreAtenParto = txtnombreAtenParto;
    }

    public CustomTextField getTxtnombreRn() {
        return txtnombreRn;
    }

    public void setTxtnombreRn(CustomTextField txtnombreRn) {
        this.txtnombreRn = txtnombreRn;
    }

    public CustomTextField getTxtnombreAtenNeo() {
        return txtnombreAtenNeo;
    }

    public void setTxtnombreAtenNeo(CustomTextField txtnombreAtenNeo) {
        this.txtnombreAtenNeo = txtnombreAtenNeo;
    }

    public CheckComboBox<Patologia> getChkcmbPatologiasParto() {
        return chkcmbPatologiasParto;
    }

    public void setChkcmbPatologiasParto(CheckComboBox<Patologia> chkcmbPatologiasParto) {
        this.chkcmbPatologiasParto = chkcmbPatologiasParto;
    }

    public CheckComboBox<String> getChkcmbMedicacionParto() {
        return chkcmbMedicacionParto;
    }

    public void setChkcmbMedicacionParto(CheckComboBox<String> chkcmbMedicacionParto) {
        this.chkcmbMedicacionParto = chkcmbMedicacionParto;
    }

    private FxValidations validations = new FxValidations();

    public void initialize(){
        try {
            iniciarCombos();
            iniciarValidadores();
        }catch(Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }

    }
    private void iniciarValidadores(){
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtTemperaturaParto, Validator.createRegexValidator("Ingrese un nuúmero válido. Formato (##.##)", Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtEdadGestacional,Validator.createRegexValidator("Ingrese solo números.",Regex.NUMEROS.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtHoraTerminacion,Validator.createRegexValidator("Ingrese una hora válida. Formato 24h.",Regex.HORAS24.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtHoraRuptura,Validator.createRegexValidator("Ingrese una hora válida. Formato 24h.",Regex.HORAS24.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtHoraIngreso,Validator.createRegexValidator("Ingrese una hora válida. Formato 24h.",Regex.HORAS24.getExpresion(),Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }
    private void iniciarCombos(){
        cmbAtendioNeonato.getItems().setAll(Otros.getAtendio());
        cmbAtendioParto.getItems().setAll(Otros.getAtendio());
        cmbInicioParto.getItems().setAll(Otros.getInicio());
        cmbMembranas.getItems().setAll(Otros.getMembranas());
        cmbMuerteIntraUte.getItems().setAll(Otros.getMuerteIntraUte());
        cmbNivelAtencion.getItems().setAll(Otros.getNivelAtencion());
        cmbPresentacionParto.getItems().setAll(Otros.getPresentacion());
        cmbTerminacion.getItems().setAll(Otros.getTerminacion());
        chkcmbMedicacionParto.getItems().setAll(Otros.getMedicacionParto());
        chkcmbPatologiasParto.getItems().setAll(Otros.getPatologias("parto"));
        chkcmbPatologiasPP.getItems().setAll(Otros.getPatologias("postparto"));


        gridPaneParto.getChildren().filtered(x-> x instanceof PrefixSelectionComboBox).forEach(x-> {
            x.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)
                    ((PrefixSelectionComboBox)x).getSelectionModel().clearSelection();
            });
        });

        chkcmbPatologiasParto.setConverter(new StringConverter<Patologia>() {

            @Override
            public String toString(Patologia object) {
                return object == null ? null : object.getDescripcion();
            }

            @Override
            public Patologia fromString(String string) {
                return chkcmbPatologiasParto.getItems().stream().filter(i -> i.getDescripcion().equals(string)).findAny().orElse(null);
            }
        });

        chkcmbPatologiasPP.setConverter(new StringConverter<Patologia>() {

            @Override
            public String toString(Patologia object) {
                return object == null ? null : object.getDescripcion();
            }

            @Override
            public Patologia fromString(String string) {
                return chkcmbPatologiasPP.getItems().stream().filter(i -> i.getDescripcion().equals(string)).findAny().orElse(null);
            }
        });

        gridPaneParto.getChildren().filtered(x-> x instanceof Spinner).forEach(x-> {
            x.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
            ((Spinner)x).setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99,0));
            //((Spinner) x).setEditable(true);
        });
;    }

    public void limpiarControles(){
        gridPaneParto.getChildren().forEach(node -> Formularios.limpiarControles(node));
    }
}

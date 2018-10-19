package controllers;

import Dao.Otros;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class EmbarazoController {


    public TitledPane tpaneEmbarazo;
    public GridPane gridPaneEmb;
    public CustomTextField txtPesoAnterior;
    public CustomTextField txtTalla;
    public DatePicker dtpFum;
    public DatePicker dtpFpp;
    public PrefixSelectionComboBox<String> cmbAntitetanica1Mes;
    public PrefixSelectionComboBox<String> cmbAntitetanica2Mes;
    public PrefixSelectionComboBox<String> cmbGrupoSanguineo;
    public PrefixSelectionComboBox<String> cmbRh;
    public Spinner<Integer> txtCigarrillosPorDia;
    public DatePicker dtpHb1;
    public DatePicker dtpHb2;
    public CustomTextField txtHb1;
    public CustomTextField txtHb2;
    public PrefixSelectionComboBox<String> cmbVdrl;
    public DatePicker dtpGlucosa;
    public CustomTextField txtGlucosa;
    public DatePicker dtpFechaVdrl;
    public CheckBox chkDudas;
    public CheckBox chkPelvis;
    public CheckBox chkAntitetanica;
    public CheckBox chkSensibilidad;
    public CheckBox chkFuma;
    public CheckBox chkExClinico;
    public CheckBox chkExMamas;
    public CheckBox chkExOdontologico;
    public CheckBox chkPapanicolao;
    public CheckBox chkColposcopia;
    public CheckBox chkCervix;
    public CheckBox chkHospitalizacion;
    public CheckBox chkTraslado;
    public CustomTextField txtLugarTraslado;

    private FxValidations validations = new FxValidations();

    public void initialize(){
        try{

            //evento xq no funciona el backspacedalloweed para limpiar la seleccion con las teclas backspaced o delete
            iniciarCombos();
            iniciarValidadores();

            gridPaneEmb.getChildren().filtered(x-> x instanceof Spinner).forEach(x-> {
                x.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
                ((Spinner)x).setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99,0));
                //((Spinner) x).setEditable(true);
            });

        }catch(Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }
    }

    private void iniciarValidadores(){
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtPesoAnterior, Validator.createRegexValidator("Ingrese un número válido . Formato (##.##)",Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtTalla, Validator.createRegexValidator("Ingrese un número válido . Formato (##.##)",Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtHb1,Validator.createRegexValidator("Ingrese solo números.",Regex.NUMEROS.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtHb2,Validator.createRegexValidator("Ingrese solo números.",Regex.NUMEROS.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtGlucosa,Validator.createRegexValidator("Ingrese solo números.",Regex.NUMEROS.getExpresion(),Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->
        {
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }

    private void iniciarCombos(){
        cmbGrupoSanguineo.getItems().setAll(Otros.getGrupoSangre());
        cmbRh.getItems().setAll(Otros.getRH());
        cmbVdrl.getItems().setAll(Otros.getVdrl());
        cmbAntitetanica1Mes.getItems().setAll(Otros.getMeses());
        cmbAntitetanica2Mes.getItems().setAll(Otros.getMeses());

        gridPaneEmb.getChildren().filtered(x-> x instanceof PrefixSelectionComboBox).forEach(x-> {
            x.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)
                    ((PrefixSelectionComboBox)x).getSelectionModel().clearSelection();
            });
        });
    }
    public String getTexto(){
        return  "HELLO";
    }

    public void limpiarControles(){

        gridPaneEmb.getChildren().forEach(node -> Formularios.limpiarControles(node));

    }
}

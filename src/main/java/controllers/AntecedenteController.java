package controllers;

import Dao.Otros;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import utilidades.FxValidations;
import utilidades.Regex;

import java.text.Normalizer;
import java.util.Date;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class AntecedenteController {

    public GridPane gpaneAnteObs;
    public TitledPane tpaneAntecedentes;
    public CustomTextField txtAntecedentesOtros;
    public CheckComboBox chkcmbAntecedentesPersonales;
    public CheckComboBox chkcmbAntecedentesFamiliares;
    public TitledPane tpaneAntecedentesObstetricos;
    public CheckBox chkRnMenor2500;
    public CheckBox chkGemelares;
    public CustomTextField txtRnMayorPeso;
    public DatePicker dtpFechaFinAnteriorEmbrazo;
    public Spinner<Integer> txtGestas;
    public Spinner<Integer> txtAbortos;
    public Spinner<Integer> txtPartos;
    public Spinner<Integer> txtVaginales;
    public Spinner<Integer> txtCesareas;
    public Spinner<Integer> txtNacidosVivos;
    public Spinner<Integer> txtNacidosMuertos;
    public Spinner<Integer> txtMuertoPrimeraSemana;
    public Spinner<Integer> txtViven;
    public Spinner<Integer> txtMuertoDespPriSemana;
    public GridPane gpaneAntecedentes;

    private FxValidations validations = new FxValidations();

    public void initialize(){
       try{

           iniciarCombos();
           iniciarValidadores();
           gpaneAnteObs.getChildren().filtered(x-> x instanceof Spinner).forEach(x-> {
               x.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
               ((Spinner)x).setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99,0));
               //((Spinner) x).setEditable(true);
           });

       }catch (Exception ex){
           FxDialogs.showException("Error", "Ha ocurrido un error al cargar los antecedentes",ex);
       }


    }

    private void  iniciarValidadores(){
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtRnMayorPeso, Validator.createRegexValidator("Ingrese un número válido. Formato (##.##)",Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->
        {
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }
    private void iniciarCombos(){

        chkcmbAntecedentesFamiliares.getItems().setAll(Otros.getAntecedendetes());
        chkcmbAntecedentesPersonales.getItems().setAll(Otros.getAntecedendetesPersonales());
    }

    public String getTexto(){
        return  txtAbortos.getValue().toString();
    }

    public void limpiarControles(){

        gpaneAntecedentes.getChildren().forEach(node -> Formularios.limpiarControles(node));
        gpaneAnteObs.getChildren().forEach(node -> Formularios.limpiarControles(node));
    }
}

package controllers;

import entities.ConsultaEmbarazo;
import entities.Puerperio;
import entities.TrabajoParto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import utilidades.FxValidations;
import utilidades.Regex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PuerperioController {
    @FXML
    private TitledPane tpanePuerperio;

    @FXML
    private CustomTextField txtTemperatura;

    @FXML
    private CustomTextField txtPulso;

    @FXML
    private CustomTextField txtTensionArterialPuerperio;

    @FXML
    private CustomTextField txtInvoUterina;

    @FXML
    private CustomTextField txtCaracteristicasLoquios;

    @FXML
    private CustomTextField txtHoraPostParto;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Puerperio> gridPuerperio;

    @FXML
    private DatePicker dtpFechaPuerperio;

   @FXML
   private GridPane gpanePuerperio;
    
   public ObservableList<Puerperio> puerperioList = FXCollections.observableArrayList();
   private FxValidations validations = new FxValidations();
   private Boolean esNuevo=false;
   private Puerperio puerperio;
   private FilteredList<Puerperio> listaFiltrada;

    public void initialize(){
        try{
            dtpFechaPuerperio.setValue(LocalDate.now());
            iniciarCombos();
            iniciarValidadores();
            iniciarColumnas();
            gpanePuerperio.setDisable(true);
            btnGuardar.setDisable(true);

            gridPuerperio.setRowFactory(param -> {
                TableRow<Puerperio> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(event.getClickCount()==2 && (!row.isEmpty())){
                        DatosAModificar(row.getItem());
                    }
                });
                return row;
            });
        }
        catch (Exception ex){  
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }
    }



    private void iniciarColumnas() {
        List<TableColumn<Puerperio,String>> columnas = new ArrayList<>();

        TableColumn<Puerperio,String> colFecha =new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPostParto"));
        columnas.add(colFecha);

        TableColumn<Puerperio,String> colHora =new TableColumn<>("Hora");
        colHora.setCellValueFactory(new PropertyValueFactory<>("horaPostParto"));
        columnas.add(colHora);

        TableColumn<Puerperio,String> colTemperatura =new TableColumn<>("Temperatura °C");
        colTemperatura.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        columnas.add(colTemperatura);

        TableColumn<Puerperio,String> colTension =new TableColumn<>("Tension Arterial");
        colTension.setCellValueFactory(new PropertyValueFactory<>("presionArterial"));
        columnas.add(colTension);

        TableColumn<Puerperio,String> colInvolUte =new TableColumn<>("Invol. Uterina");
        colInvolUte.setCellValueFactory(new PropertyValueFactory<>("uterina"));
        columnas.add(colInvolUte);

        TableColumn<Puerperio,String> colLoquios =new TableColumn<>("Loquios");
        colLoquios.setCellValueFactory(new PropertyValueFactory<>("caracteristicasLoquios"));
        columnas.add(colLoquios);

        TableColumn<Puerperio,String> colPulso =new TableColumn<>("Pulso");
        colPulso.setCellValueFactory(new PropertyValueFactory<>("pulso"));
        columnas.add(colPulso);

        gridPuerperio.getColumns().addAll(columnas);
        gridPuerperio.getColumns().forEach(tableColumn -> tableColumn.setMinWidth(150));

        listaFiltrada = new FilteredList<>(puerperioList);
        puerperioList.addListener(new ListChangeListener<Puerperio>() {
            @Override
            public void onChanged(Change<? extends Puerperio> c) {
                listaFiltrada.setPredicate(puerperio -> Character.valueOf('A').equals(puerperio.getEstado()));
            }
        });
        gridPuerperio.setItems(listaFiltrada);
    }

   private void iniciarValidadores(){
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtHoraPostParto, Validator.createRegexValidator("Ingrese una hora válida. Formato 24h", Regex.HORAS24.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtTemperatura, Validator.createRegexValidator("Ingrese un número válido . Formato (##.##)", Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
   }
    
   private void iniciarCombos(){}

   public void limpiarControles(){
       gpanePuerperio.getChildren().forEach(node -> Formularios.limpiarControles(node));
   }
    public void limpiarControlesYLista(){
        gpanePuerperio.getChildren().forEach(node -> Formularios.limpiarControles(node));
        puerperioList.clear();
    }

    public void NuevaPuerperio(){
        limpiarControles();
        puerperio = new Puerperio();
        esNuevo=true;
        gpanePuerperio.setDisable(false);
        btnGuardar.setDisable(false);
    }
    public void guardarPuerperio(){
       try{

           if (validations.getValidationSupport().getValidationResult().getErrors().size() > 0) {
               FxDialogs.showInformation("Error al ingresar datos", "Revise los errores antes de continuar.");
               return;
           }
            puerperio.setEstado('A');
           puerperio.setFechaPostParto(dtpFechaPuerperio.getValue());
           puerperio.setHoraPostParto(txtHoraPostParto.getText().trim());
           if(!txtTemperatura.getText().trim().isEmpty())
                puerperio.setTemperatura(new BigDecimal(txtTemperatura.getText().trim()));
           puerperio.setPresionArterial(txtTensionArterialPuerperio.getText().trim());
           puerperio.setUterina(txtInvoUterina.getText().trim());
           puerperio.setCaracteristicasLoquios(txtCaracteristicasLoquios.getText().trim());
           puerperio.setPulso(txtPulso.getText().trim());

           if(esNuevo){
               puerperio.setId(UUID.randomUUID());
               puerperioList.add(puerperio);
           }else{
               puerperioList.set(puerperioList.indexOf(puerperio),puerperio);
           }
           gpanePuerperio.setDisable(true);
           btnGuardar.setDisable(true);
       }catch (Exception ex){
           FxDialogs.showException("Error","Ha ocurrido un error ver los dtalles",ex);
       }
    }
    private void DatosAModificar(Puerperio item) {
       try{
           limpiarControles();
           dtpFechaPuerperio.setValue(item.getFechaPostParto());
           txtHoraPostParto.setText(item.getHoraPostParto());
           if(item.getTemperatura()!=null)
               txtTemperatura.setText(item.getTemperatura().toString());
           txtTensionArterialPuerperio.setText(item.getPresionArterial());
           txtInvoUterina.setText(item.getUterina());
           txtCaracteristicasLoquios.setText(item.getCaracteristicasLoquios());
           txtPulso.setText(item.getPulso());
           gpanePuerperio.setDisable(false);
           btnGuardar.setDisable(false);
           esNuevo=false;
       }catch (Exception ex){
           FxDialogs.showException("Error","Ha ocurrido un error ver mas en los detalles",ex);
       }
    }

    public void EliminarPuerperio(){
        if(gridPuerperio.getSelectionModel().getSelectedItem()!=null){
            Puerperio temp = gridPuerperio.getSelectionModel().getSelectedItem();
            int indice = puerperioList.indexOf(temp);
            temp.setEstado('I');
            puerperioList.set( indice,temp);
            limpiarControles();
            //remoer del persist
        }
    }
}

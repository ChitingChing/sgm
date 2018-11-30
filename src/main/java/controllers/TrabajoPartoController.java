package controllers;

import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import entities.ConsultaEmbarazo;
import entities.Paciente;
import entities.TrabajoParto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import utilidades.FxValidations;
import utilidades.Regex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class TrabajoPartoController {

    @FXML
    private TitledPane tpaneTrabajoParto;

    @FXML
    private CustomTextField txtHoraTrabParto;

    @FXML
    private CustomTextField txtContracciones;

    @FXML
    private CustomTextField txtTensionArterial;

    @FXML
    private CustomTextField txtFcfTrabParto;

    @FXML
    private CustomTextField txtAlturaUteTrabParto;

    @FXML
    private CustomTextField txtPosicionTrabParto;

    @FXML
    private CustomTextField txtDilatacionCerv;

    @FXML
    private CustomTextField txtMeconioTrabParto;

    @FXML
    private CustomTextField txtNombreExaminador;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<TrabajoParto> tblTrabParto;

    @FXML
    private DatePicker dtpFechaTrabParto;

    @FXML
    private CustomTextField txtDuracionContra;

    @FXML
    private GridPane gpaneTrabparto;

    private FxValidations validations = new FxValidations();
    public ObservableList<TrabajoParto> trabajoPartoList = FXCollections.observableArrayList();
    private Boolean esNuevo=false;
    private TrabajoParto trabajoParto;
    private FilteredList<TrabajoParto> listaFiltrada;

    public void initialize(){
        try{
            btnGuardar.setText("Agregar");
            btnGuardar.setDisable(true);
            dtpFechaTrabParto.setValue(LocalDate.now());

            iniciarValidaciones();
            iniciarColumnas();
            gpaneTrabparto.setDisable(true);


            tblTrabParto.setRowFactory(param -> {
                TableRow<TrabajoParto> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(event.getClickCount()==2 && (!row.isEmpty() )){
                        DatosAModificar(row.getItem());
                    }
                });
                return row;
            });
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }

    }


    private void iniciarValidaciones(){

        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtContracciones, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtHoraTrabParto,Validator.createRegexValidator("Ingrese una hora válida. Formtao 24h",Regex.HORAS24.getExpresion(),Severity.ERROR));
        validationSupport.registerValidator(txtDuracionContra,Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(dtpFechaTrabParto,Validator.createEmptyValidator("Ingrese una fecha",Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }

    private void iniciarColumnas(){

        List<TableColumn<TrabajoParto,String>> columnas = new ArrayList<>();

        TableColumn<TrabajoParto,String> colFechahora =new TableColumn<>("Fecha");
        colFechahora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        columnas.add(colFechahora);

        TableColumn<TrabajoParto,String> colTension =new TableColumn<>("Tensión Arterial");
        colTension.setCellValueFactory(new PropertyValueFactory<>("tensionArterial"));
        columnas.add(colTension);

        TableColumn<TrabajoParto,String> colContracciones =new TableColumn<>("Contracciones");
        colContracciones.setCellValueFactory(new PropertyValueFactory<>("contraccionesFrecuencia"));
        columnas.add(colContracciones);

        TableColumn<TrabajoParto,String> colDuraccion =new TableColumn<>("Duración");
        colDuraccion.setCellValueFactory(new PropertyValueFactory<>("duracionContraccion"));
        columnas.add(colDuraccion);

        TableColumn<TrabajoParto,String> colFcf =new TableColumn<>("FCF");
        colFcf.setCellValueFactory(new PropertyValueFactory<>("fcf"));
        columnas.add(colFcf);

        TableColumn<TrabajoParto,String> colAltUte =new TableColumn<>("Altura Ute.");
        colAltUte.setCellValueFactory(new PropertyValueFactory<>("altura"));
        columnas.add(colAltUte);

        TableColumn<TrabajoParto,String> colPosicion =new TableColumn<>("Posición");
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        columnas.add(colPosicion);

        TableColumn<TrabajoParto,String> colDilCerv =new TableColumn<>("Dilatación Cervical");
        colDilCerv.setCellValueFactory(new PropertyValueFactory<>("dilatacion"));
        columnas.add(colDilCerv);

        TableColumn<TrabajoParto,String> colMeconio =new TableColumn<>("Meconio");
        colMeconio.setCellValueFactory(new PropertyValueFactory<>("meconio"));
        columnas.add(colMeconio);

        TableColumn<TrabajoParto,String> colExaminador =new TableColumn<>("Exasminador");
        colExaminador.setCellValueFactory(new PropertyValueFactory<>("nombreExaminador"));
        columnas.add(colExaminador);

        tblTrabParto.getColumns().addAll(columnas);

        tblTrabParto.getColumns().forEach(tableColumn -> tableColumn.setMinWidth(100));

        listaFiltrada = new FilteredList<>(trabajoPartoList);
        trabajoPartoList.addListener(new ListChangeListener<TrabajoParto>() {
            @Override
            public void onChanged(Change<? extends TrabajoParto> c) {
                listaFiltrada.setPredicate(trabajoParto -> Character.valueOf('A').equals(trabajoParto.getEstado()));
            }
        });
        tblTrabParto.setItems(listaFiltrada);
    }
    public void limpiarControles(){

         gpaneTrabparto.getChildren().forEach(node -> Formularios.limpiarControles(node));
         btnGuardar.setDisable(true);
         btnGuardar.setText("Agregar");
         gpaneTrabparto.setDisable(true);
    }

    public void limpiarControlesYLista(){

        gpaneTrabparto.getChildren().forEach(node -> Formularios.limpiarControles(node));
        trabajoPartoList.clear();
        gpaneTrabparto.setDisable(true);
    }

    public void NuevaTrabParto(){
        limpiarControles();
        trabajoParto = new TrabajoParto();
        esNuevo=true;
        gpaneTrabparto.setDisable(false);
        btnGuardar.setDisable(false);
        dtpFechaTrabParto.setValue(LocalDate.now());

    }

    public void guardarTrabParto(){
        try {
            if (validations.getValidationSupport().getValidationResult().getErrors().size() > 0) {
                FxDialogs.showInformation("Error al ingresar datos", "Revise los errores antes de continuar.");
                return;
            }
            trabajoParto.setEstado('A');
            LocalDateTime fecha= LocalDateTime.of(dtpFechaTrabParto.getValue(), LocalTime.parse(txtHoraTrabParto.getText().trim()));
            trabajoParto.setFechaHora(fecha);
            trabajoParto.setTensionArterial(txtTensionArterial.getText().trim());
            if(!txtContracciones.getText().trim().isEmpty())
                trabajoParto.setContraccionesFrecuencia(Integer.parseInt(txtContracciones.getText().trim()));
            if(!txtDuracionContra.getText().trim().isEmpty())
                trabajoParto.setDuracionContraccion(Integer.parseInt(txtDuracionContra.getText().trim()));
            trabajoParto.setFcf(txtFcfTrabParto.getText().trim());
            trabajoParto.setAltura(txtAlturaUteTrabParto.getText().trim());
            trabajoParto.setPosicion(txtPosicionTrabParto.getText().trim());
            trabajoParto.setDilatacion(txtDilatacionCerv.getText().trim());
            trabajoParto.setMeconio(txtMeconioTrabParto.getText().trim());
            trabajoParto.setNombreExaminador(txtNombreExaminador.getText().trim());
            if(esNuevo){
                trabajoParto.setId(UUID.randomUUID());
                trabajoPartoList.add(trabajoParto);
            }else{
                trabajoPartoList.set(trabajoPartoList.indexOf(trabajoParto),trabajoParto);
            }

            gpaneTrabparto.setDisable(true);
            btnGuardar.setDisable(true);
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver los dtalles",ex);
        }
    }

    private void DatosAModificar(TrabajoParto item) {
        try {
            limpiarControles();
            dtpFechaTrabParto.setValue(item.getFechaHora().toLocalDate());
            txtHoraTrabParto.setText(item.getFechaHora().toLocalTime().toString());
            txtTensionArterial.setText(item.getTensionArterial());
            if (item.getContraccionesFrecuencia() != null)
                txtContracciones.setText(item.getContraccionesFrecuencia().toString());
            if (item.getDuracionContraccion() != null)
                txtDuracionContra.setText(item.getDuracionContraccion().toString());
            txtFcfTrabParto.setText(item.getFcf());
            txtAlturaUteTrabParto.setText(item.getAltura());
            txtPosicionTrabParto.setText(item.getPosicion());
            txtDilatacionCerv.setText(item.getDilatacion());
            txtMeconioTrabParto.setText(item.getMeconio());
            txtNombreExaminador.setText(item.getNombreExaminador());
            trabajoParto=item;
            gpaneTrabparto.setDisable(false);
            btnGuardar.setDisable(false);
            btnGuardar.setText("Actualizar");
            esNuevo=false;
        }catch (Exception ex){
        FxDialogs.showException("Error","Ha ocurrido un error ver mas en los detalles",ex);
        }
    }
    public void EliminarTrabParto(){
        if(tblTrabParto.getSelectionModel().getSelectedItem()!=null){
            TrabajoParto temp = tblTrabParto.getSelectionModel().getSelectedItem();
            int indice = trabajoPartoList.indexOf(temp);
            temp.setEstado('I');
            trabajoPartoList.set( indice,temp);
            limpiarControles();
            //remoer del persist
        }
    }
}

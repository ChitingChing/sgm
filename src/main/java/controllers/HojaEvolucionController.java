package controllers;

import entities.HojaEvolucionPrescripcion;
import entities.Medicina;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.Formularios;
import utilidades.FxDialogs;
import utilidades.FxValidations;
import utilidades.Regex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HojaEvolucionController {
    @FXML
    private StackPane stackContainer;
    @FXML private VBox containerControles;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private CustomTextField txtHora;

    @FXML
    private TextArea txtEvolucion;

    @FXML
    private TextArea txtMedicamentos;

    @FXML
    private TextArea txtPrescripcion;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<HojaEvolucionPrescripcion> tblEvolucion;

    @FXML
    private MaskerPane esperaMskPane;

    public ObservableList<HojaEvolucionPrescripcion> listaItems = FXCollections.observableArrayList();
    private FxValidations validations = new FxValidations();
    private Boolean esNuevo=false;
    private HojaEvolucionPrescripcion itemSeleccionado;
    private FilteredList<HojaEvolucionPrescripcion> listaFiltrada;
    private Medicina medicina;

    public void initialize(){

        try{
            btnGuardar.setText("Agregar");
            btnGuardar.setDisable(true);
            dtpFecha.setValue(LocalDate.now());

            iniciarValidadores();
            iniciarColumnas();
            containerControles.setDisable(true);


            tblEvolucion.setRowFactory(param -> {
                TableRow<HojaEvolucionPrescripcion> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(event.getClickCount()==2 && (!row.isEmpty())){
                        DatosAModificar(row.getItem());
                    }
                });
                return row;
            });
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error al cargar el formulario",ex);
        }
    }

    private void iniciarValidadores() {
        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtHora, Validator.createRegexValidator("Ingrese una hora válida. Formato 24h,", Regex.HORAS24.getExpresion(), Severity.ERROR));

        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));
        });
    }

    private void iniciarColumnas() {
        List<TableColumn<HojaEvolucionPrescripcion,?>> columnas = new ArrayList<>();

        TableColumn<HojaEvolucionPrescripcion, LocalDate> colFecha =new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnas.add(colFecha);

        TableColumn<HojaEvolucionPrescripcion,String> colHora =new TableColumn<>("Hora");
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columnas.add(colHora);

        TableColumn<HojaEvolucionPrescripcion,String> colEvolucion =new TableColumn<>("Evolucion");
        colEvolucion.setCellValueFactory(new PropertyValueFactory<>("evolucion"));
        columnas.add(colEvolucion);


        tblEvolucion.getColumns().addAll(columnas);
        tblEvolucion.getColumns().forEach(tableColumn -> tableColumn.setMinWidth(150));

        listaFiltrada = new FilteredList<>(listaItems);
        listaItems.addListener(new ListChangeListener<HojaEvolucionPrescripcion>() {
            @Override
            public void onChanged(Change<? extends HojaEvolucionPrescripcion> c) {

                listaFiltrada.setPredicate(i ->  Character.valueOf('A').equals(i.getEstado()));
            }
        });
        tblEvolucion.setItems(listaFiltrada);
    }
    public void limpiarControles(){
        dtpFecha.setValue(LocalDate.now());
        txtHora.clear();
        txtEvolucion.clear();
        txtMedicamentos.clear();
        txtPrescripcion.clear();
        btnGuardar.setDisable(true);
        btnGuardar.setText("Agregar");
        containerControles.setDisable(true);
    }

    public void limpiarControlesYLista(){
        dtpFecha.setValue(LocalDate.now());
        txtHora.clear();
        txtEvolucion.clear();
        txtMedicamentos.clear();
        txtPrescripcion.clear();
        listaItems.clear();
        containerControles.setDisable(true);
    }

    public void showBuscarMedicina() {
        Formularios f = new Formularios();
        medicina= f.showBusquedaMedicina();
        if(medicina!=null){
            if(txtMedicamentos.getText().isEmpty() && txtPrescripcion.getText().isEmpty()){
                txtMedicamentos.appendText(medicina.getDescripcion());
                txtPrescripcion.appendText(medicina.getPrescripcion());
            }else{
                txtMedicamentos.appendText("\n\n");
                txtMedicamentos.appendText(medicina.getDescripcion());

                txtPrescripcion.appendText("\n\n");
                txtPrescripcion.appendText(medicina.getPrescripcion());
            }

        }
    }

    public void nuevo(){
        limpiarControles();
        itemSeleccionado = new HojaEvolucionPrescripcion();
        esNuevo=true;
        containerControles.setDisable(false);
        btnGuardar.setDisable(false);
        btnGuardar.setText("Agregar");
        dtpFecha.setValue(LocalDate.now());
    }
    public void guardar(){
        try{
            if (validations.getValidationSupport().getValidationResult().getErrors().size() > 0) {
                FxDialogs.showInformation("Error al ingresar datos", "Revise los errores antes de continuar.");
                return;
            }
            //validaciones si las hay
            itemSeleccionado.setEstado('A');
            itemSeleccionado.setFecha(dtpFecha.getValue());
            itemSeleccionado.setHora(txtHora.getText().trim());
            itemSeleccionado.setEvolucion(txtEvolucion.getText().trim());
            itemSeleccionado.setMedicamentos(txtMedicamentos.getText().trim());
            itemSeleccionado.setPrescripcion(txtPrescripcion.getText().trim());

            if(esNuevo){
                itemSeleccionado.setId(UUID.randomUUID());
                listaItems.add(itemSeleccionado);
            }else{
                listaItems.set(listaItems.indexOf(itemSeleccionado),itemSeleccionado);
            }

            containerControles.setDisable(true);
            btnGuardar.setDisable(true);

        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver los dtalles",ex);
        }
    }
    public void eliminar(){
        if(tblEvolucion.getSelectionModel().getSelectedItem()!=null){
            HojaEvolucionPrescripcion temp = tblEvolucion.getSelectionModel().getSelectedItem();
            int indice = listaItems.indexOf(temp);
            temp.setEstado('I');
            listaItems.set(indice,temp);
            limpiarControles();
        }
    }

    private void DatosAModificar(HojaEvolucionPrescripcion item){
        try{
            limpiarControles();
            dtpFecha.setValue(item.getFecha());
            txtHora.setText(item.getHora());
            txtEvolucion.setText(item.getEvolucion());
            txtMedicamentos.setText(item.getMedicamentos());
            txtPrescripcion.setText(item.getPrescripcion());
            containerControles.setDisable(false);
            btnGuardar.setDisable(false);
            btnGuardar.setText("Actualizar");
            esNuevo=false;
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver mas en los detalles",ex);
        }
    }
}

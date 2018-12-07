package controllers;

import Dao.Otros;
import entities.ConsultaEmbarazo;
import entities.TrabajoParto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utilidades.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ConsultaEmbarazoController {
    @FXML
    private TitledPane tpaneConsultaEmbarazo;

    @FXML
    private DatePicker dtpFechaConsulta;

    @FXML
    private CustomTextField txtSemanaAmenorreas;

    @FXML
    private CustomTextField txtPeso;

    @FXML
    private CustomTextField txtTensionArterialConsulta;

    @FXML
    private CustomTextField txtAltUterina;

    @FXML
    private CustomTextField txtFcf;

    @FXML
    private CustomTextField txtMovFetal;

    @FXML
    private CustomTextField txtEdema;

    @FXML
    private CustomTextField txtAlbuminuria;

    @FXML
    private CustomTextField txtExaminador;

    @FXML
    private CustomTextField txtSangradoGenital;

    @FXML
    private PrefixSelectionComboBox<String> cmbPresentacion;

    @FXML
    private Button btnNuevoConsulta;

    @FXML
    private Button btnGuardarConsulta;

    @FXML
    private Button btnEliminarConsulta;

    @FXML
    private TableView<ConsultaEmbarazo> gridConsulta;
    @FXML
    private GridPane gridPaneConsEmb;

    private FxValidations validations = new FxValidations();
    private ConsultaEmbarazo consultaEmb;
    public ObservableList<ConsultaEmbarazo> consultaEmbarazoList = FXCollections.observableArrayList();
    private Boolean esNuevo=false;
    private FilteredList<ConsultaEmbarazo> listaFiltrada;
    public void initialize() {

        try{
            btnGuardarConsulta.setText("Agregar");
            btnGuardarConsulta.setDisable(true);
            dtpFechaConsulta.setValue(LocalDate.now());

            iniciarCombos();
            iniciarValidadores();
            iniciarColumnas();
            gridPaneConsEmb.setDisable(true);


            gridConsulta.setRowFactory(param -> {
                TableRow<ConsultaEmbarazo> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(event.getClickCount()==2 && (!row.isEmpty() )){
                        DatosAModificar(row.getItem());
                    }
                });
                return  row;
            });
        }catch (Exception ex){
            FxDialogs.showException("Error","Error al iniciar el formulario Consulta Embarazo",ex);
        }

    }


    private void iniciarCombos(){
        cmbPresentacion.getItems().setAll(Otros.getPresentacion());

        gridPaneConsEmb.getChildren().filtered(x-> x instanceof PrefixSelectionComboBox).forEach(x-> {
            x.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE)
                    ((PrefixSelectionComboBox)x).getSelectionModel().clearSelection();
            });
        });
    }

    private void iniciarValidadores(){

        ValidationSupport validationSupport = validations.getValidationSupport();
        validationSupport.registerValidator(txtSemanaAmenorreas, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtAltUterina, Validator.createRegexValidator("Ingrese solo números.", Regex.NUMEROS.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(txtPeso, Validator.createRegexValidator("Ingrese un número válido . Formato (##.##)", Regex.NUMERODECIMAL2.getExpresion(), Severity.ERROR));
        validationSupport.registerValidator(dtpFechaConsulta,Validator.createEmptyValidator("Ingrese una fecha",Severity.ERROR));
        validationSupport.validationResultProperty().addListener( (o, oldValue, newValue) ->{
            validations.getValidationSupport().getRegisteredControls().stream().forEach(x -> x.setTooltip(null));
            validations.getValidationSupport().getValidationResult().getErrors().stream().forEach(x -> x.getTarget().setTooltip(new Tooltip(x.getText())));

        });

    }

    private void iniciarColumnas(){

        TableUtils.installCopyPasteHandler(gridConsulta);
        gridConsulta.getSelectionModel().setCellSelectionEnabled(true);
        gridConsulta.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<TableColumn<ConsultaEmbarazo,String>> columnas = new ArrayList<>();

        TableColumn<ConsultaEmbarazo,String> colFecha =new TableColumn<> ("Fecha Consulta");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaConsulta"));
        columnas.add(colFecha);

        TableColumn<ConsultaEmbarazo,String> colSemana =new TableColumn<>("Semana Amenorreas");
        colSemana.setCellValueFactory(new PropertyValueFactory<>("semanasAmenorreas"));
        columnas.add(colSemana);

        TableColumn<ConsultaEmbarazo,String> colPeso =new TableColumn<>("Peso (Kg)");
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnas.add(colPeso);

        TableColumn<ConsultaEmbarazo,String> colTension =new TableColumn<>("Tensión Arterial");
        colTension.setCellValueFactory(new PropertyValueFactory<>("presionArterial"));
        columnas.add(colTension);

        TableColumn<ConsultaEmbarazo,String> colAltUte =new TableColumn<>("Altura Uterina");
        colAltUte.setCellValueFactory(new PropertyValueFactory<>("alturaUterina"));
        columnas.add(colAltUte);

        TableColumn<ConsultaEmbarazo,String> colPresentacion =new TableColumn<>("Presentación");
        colPresentacion.setCellValueFactory(new PropertyValueFactory<>("presentacion"));
        columnas.add(colPresentacion);

        TableColumn<ConsultaEmbarazo,String> colFcf =new TableColumn<>("FCF");
        colFcf.setCellValueFactory(new PropertyValueFactory<>("fcf"));
        columnas.add(colFcf);

        TableColumn<ConsultaEmbarazo,String> colMovFetal =new TableColumn<>("Movimiento Fetal");
        colMovFetal.setCellValueFactory(new PropertyValueFactory<>("movFetal"));
        columnas.add(colMovFetal);

        TableColumn<ConsultaEmbarazo,String> colEdema =new TableColumn<>("Edema");
        colEdema.setCellValueFactory(new PropertyValueFactory<>("edema"));
        columnas.add(colEdema);

        TableColumn<ConsultaEmbarazo,String> colAlbuminuria =new TableColumn<>("Albuminuria");
        colAlbuminuria.setCellValueFactory(new PropertyValueFactory<>("albuminuria"));
        columnas.add(colAlbuminuria);

        TableColumn<ConsultaEmbarazo,String> colSangrado =new TableColumn<>("Sangrado Genital");
        colSangrado.setCellValueFactory(new PropertyValueFactory<>("sangradoGenital"));
        columnas.add(colSangrado);

        TableColumn<ConsultaEmbarazo,String> colExaminador =new TableColumn<>("Examinador");
        colExaminador.setCellValueFactory(new PropertyValueFactory<>("nombreExaminador"));
        columnas.add(colExaminador);

        gridConsulta.getColumns().addAll(columnas);

        gridConsulta.getColumns().forEach(tableColumn -> tableColumn.setMinWidth(80));

        listaFiltrada = new FilteredList<>(consultaEmbarazoList);
        consultaEmbarazoList.addListener(new ListChangeListener<ConsultaEmbarazo>() {
            @Override
            public void onChanged(Change<? extends ConsultaEmbarazo> c) {
                listaFiltrada.setPredicate(consultaEmbarazo -> Character.valueOf('A').equals(consultaEmbarazo.getEstado()));
            }
        });
        gridConsulta.setItems(listaFiltrada);


    }
    public void limpiarControles(){

        gridPaneConsEmb.getChildren().forEach(node -> Formularios.limpiarControles(node));
        btnGuardarConsulta.setText("Agregar");
        btnGuardarConsulta.setDisable(true);
        gridPaneConsEmb.setDisable(true);
    }
    public void limpiarControlesYLista(){

        gridPaneConsEmb.getChildren().forEach(node -> Formularios.limpiarControles(node));
        consultaEmbarazoList.clear();
        gridPaneConsEmb.setDisable(true);

    }

    public void NuevaConsultaEmb(){
        limpiarControles();
        consultaEmb = new ConsultaEmbarazo();
        esNuevo=true;
        gridPaneConsEmb.setDisable(false);
        btnGuardarConsulta.setDisable(false);
        btnGuardarConsulta.setText("Agregar");
        dtpFechaConsulta.setValue(LocalDate.now());

    }
    public void guardarConsultaEmb(){
        try {

            if(validations.getValidationSupport().getValidationResult().getErrors().size()>0) {
                FxDialogs.showInformation("Error al ingresar datos", "Revise los errores antes de continuar.");
                return;
            }
            consultaEmb.setEstado('A');
            LocalDateTime fecha = LocalDateTime.of(dtpFechaConsulta.getValue(), LocalTime.MIN);
            consultaEmb.setFechaConsulta(fecha);
            if (!txtSemanaAmenorreas.getText().trim().isEmpty())
                consultaEmb.setSemanasAmenorreas(Integer.parseInt(txtSemanaAmenorreas.getText().trim()));
            if (!txtPeso.getText().trim().isEmpty())
                consultaEmb.setPeso(new BigDecimal(txtPeso.getText().trim()));
            consultaEmb.setPresionArterial(txtTensionArterialConsulta.getText().trim());
            if (!txtAltUterina.getText().trim().isEmpty())
                consultaEmb.setAlturaUterina(Integer.parseInt(txtAltUterina.getText().trim()));
            consultaEmb.setPresentacion(cmbPresentacion.getSelectionModel().getSelectedItem());
            consultaEmb.setFcf(txtFcf.getText().trim());
            consultaEmb.setMovFetal(txtMovFetal.getText().trim());
            consultaEmb.setEdema(txtEdema.getText().trim());
            consultaEmb.setAlbuminuria(txtAlbuminuria.getText().trim());
            consultaEmb.setSangradoGenital(txtSangradoGenital.getText().trim());
            consultaEmb.setNombreExaminador(txtExaminador.getText().trim());
            if (esNuevo) {
                consultaEmb.setId(UUID.randomUUID());
                consultaEmbarazoList.add(consultaEmb);
            }else{
                consultaEmbarazoList.set(consultaEmbarazoList.indexOf(consultaEmb),consultaEmb);
            }


            gridPaneConsEmb.setDisable(true);
            btnGuardarConsulta.setDisable(true );
            limpiarControles();
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver los dtalles",ex);
        }
    }


    private void DatosAModificar(ConsultaEmbarazo emb) {
        try {
            limpiarControles();
            consultaEmb = emb;
            dtpFechaConsulta.setValue(consultaEmb.getFechaConsulta().toLocalDate());
            if (consultaEmb.getSemanasAmenorreas() != null)
                txtSemanaAmenorreas.setText(consultaEmb.getSemanasAmenorreas().toString());
            if (consultaEmb.getPeso() != null)
                txtPeso.setText(consultaEmb.getPeso().toString());
            txtTensionArterialConsulta.setText(consultaEmb.getPresionArterial());
            if (consultaEmb.getAlturaUterina() != null)
                txtAltUterina.setText(consultaEmb.getAlturaUterina().toString());
            cmbPresentacion.getSelectionModel().select(consultaEmb.getPresentacion());
            txtFcf.setText(consultaEmb.getFcf());
            txtMovFetal.setText(consultaEmb.getMovFetal());
            txtEdema.setText(consultaEmb.getEdema());
            txtAlbuminuria.setText(consultaEmb.getAlbuminuria());
            txtSangradoGenital.setText(consultaEmb.getSangradoGenital());
            txtExaminador.setText(consultaEmb.getNombreExaminador());
            gridPaneConsEmb.setDisable(false);
            btnGuardarConsulta.setDisable(false);
            btnGuardarConsulta.setText("Actualizar");
            esNuevo=false;
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver mas en los detalles",ex);
        }
    }

    private void getConsulta(){

    }
    public void EliminarConsultaEmb(){
        if(gridConsulta.getSelectionModel().getSelectedItem()!=null){
            ConsultaEmbarazo temp = gridConsulta.getSelectionModel().getSelectedItem();
            int indice = consultaEmbarazoList.indexOf(temp);
            temp.setEstado('I');
            consultaEmbarazoList.set( indice,temp);
            limpiarControles();
            //remoer del persist
        }
    }
}

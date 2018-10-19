package controllers;

import Dao.Otros;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.Formularios;

public class PostOperatorioController {

    @FXML private VBox vBoxContainer;
    @FXML
    private CustomTextField txtServicio;

    @FXML
    private CustomTextField txtSala;

    @FXML
    private CustomTextField txtCama;

    @FXML
    private TextArea txtPreOperatorio;

    @FXML
    private TextArea txtPostOperatorio;

    @FXML
    private CustomTextField txtProyectada;

    @FXML
    private CheckComboBox<String> cmbchkTipoOperacion;

    @FXML
    private CustomTextField txtRealizada;

    @FXML
    private CustomTextField txtCirujano;

    @FXML
    private CustomTextField txtPrimerAyudante;

    @FXML
    private CustomTextField txtSegundoAyudante;

    @FXML
    private CustomTextField txtTercerAyudante;

    @FXML
    private DatePicker dtpFechaOperacion;

    @FXML
    private CustomTextField txtHoraInicioOperacion;

    @FXML
    private CustomTextField txtInstrumentista;

    @FXML
    private CustomTextField txtCirculante;

    @FXML
    private CustomTextField txtAnestesia;

    @FXML
    private CustomTextField txtAyudanteAnestesia;

    @FXML
    private CustomTextField txtTipoAnestesia;

    @FXML
    private CustomTextField txtHoraFinOperacion;

    @FXML
    private TextArea txtDieresis;

    @FXML
    private TextArea txtExposicion;

    @FXML
    private TextArea txtExploracion;

    @FXML
    private TextArea txtProcedimientoOperatorio;

    public CustomTextField getTxtServicio() {
        return txtServicio;
    }

    public void setTxtServicio(CustomTextField txtServicio) {
        this.txtServicio = txtServicio;
    }

    public CustomTextField getTxtSala() {
        return txtSala;
    }

    public void setTxtSala(CustomTextField txtSala) {
        this.txtSala = txtSala;
    }

    public CustomTextField getTxtCama() {
        return txtCama;
    }

    public void setTxtCama(CustomTextField txtCama) {
        this.txtCama = txtCama;
    }

    public TextArea getTxtPreOperatorio() {
        return txtPreOperatorio;
    }

    public void setTxtPreOperatorio(TextArea txtPreOperatorio) {
        this.txtPreOperatorio = txtPreOperatorio;
    }

    public TextArea getTxtPostOperatorio() {
        return txtPostOperatorio;
    }

    public void setTxtPostOperatorio(TextArea txtPostOperatorio) {
        this.txtPostOperatorio = txtPostOperatorio;
    }

    public CustomTextField getTxtProyectada() {
        return txtProyectada;
    }

    public void setTxtProyectada(CustomTextField txtProyectada) {
        this.txtProyectada = txtProyectada;
    }

    public CheckComboBox<String> getCmbchkTipoOperacion() {
        return cmbchkTipoOperacion;
    }

    public void setCmbchkTipoOperacion(CheckComboBox<String> cmbchkTipoOperacion) {
        this.cmbchkTipoOperacion = cmbchkTipoOperacion;
    }

    public CustomTextField getTxtRealizada() {
        return txtRealizada;
    }

    public void setTxtRealizada(CustomTextField txtRealizada) {
        this.txtRealizada = txtRealizada;
    }

    public CustomTextField getTxtCirujano() {
        return txtCirujano;
    }

    public void setTxtCirujano(CustomTextField txtCirujano) {
        this.txtCirujano = txtCirujano;
    }

    public CustomTextField getTxtPrimerAyudante() {
        return txtPrimerAyudante;
    }

    public void setTxtPrimerAyudante(CustomTextField txtPrimerAyudante) {
        this.txtPrimerAyudante = txtPrimerAyudante;
    }

    public CustomTextField getTxtSegundoAyudante() {
        return txtSegundoAyudante;
    }

    public void setTxtSegundoAyudante(CustomTextField txtSegundoAyudante) {
        this.txtSegundoAyudante = txtSegundoAyudante;
    }

    public CustomTextField getTxtTercerAyudante() {
        return txtTercerAyudante;
    }

    public void setTxtTercerAyudante(CustomTextField txtTercerAyudante) {
        this.txtTercerAyudante = txtTercerAyudante;
    }

    public DatePicker getDtpFechaOperacion() {
        return dtpFechaOperacion;
    }

    public void setDtpFechaOperacion(DatePicker dtpFechaOperacion) {
        this.dtpFechaOperacion = dtpFechaOperacion;
    }

    public CustomTextField getTxtHoraInicioOperacion() {
        return txtHoraInicioOperacion;
    }

    public void setTxtHoraInicioOperacion(CustomTextField txtHoraInicioOperacion) {
        this.txtHoraInicioOperacion = txtHoraInicioOperacion;
    }

    public CustomTextField getTxtInstrumentista() {
        return txtInstrumentista;
    }

    public void setTxtInstrumentista(CustomTextField txtInstrumentista) {
        this.txtInstrumentista = txtInstrumentista;
    }

    public CustomTextField getTxtCirculante() {
        return txtCirculante;
    }

    public void setTxtCirculante(CustomTextField txtCirculante) {
        this.txtCirculante = txtCirculante;
    }

    public CustomTextField getTxtAnestesia() {
        return txtAnestesia;
    }

    public void setTxtAnestesia(CustomTextField txtAnestesia) {
        this.txtAnestesia = txtAnestesia;
    }

    public CustomTextField getTxtAyudanteAnestesia() {
        return txtAyudanteAnestesia;
    }

    public void setTxtAyudanteAnestesia(CustomTextField txtAyudanteAnestesia) {
        this.txtAyudanteAnestesia = txtAyudanteAnestesia;
    }

    public CustomTextField getTxtTipoAnestesia() {
        return txtTipoAnestesia;
    }

    public void setTxtTipoAnestesia(CustomTextField txtTipoAnestesia) {
        this.txtTipoAnestesia = txtTipoAnestesia;
    }

    public CustomTextField getTxtHoraFinOperacion() {
        return txtHoraFinOperacion;
    }

    public void setTxtHoraFinOperacion(CustomTextField txtHoraFinOperacion) {
        this.txtHoraFinOperacion = txtHoraFinOperacion;
    }

    public TextArea getTxtDieresis() {
        return txtDieresis;
    }

    public void setTxtDieresis(TextArea txtDieresis) {
        this.txtDieresis = txtDieresis;
    }

    public TextArea getTxtExposicion() {
        return txtExposicion;
    }

    public void setTxtExposicion(TextArea txtExposicion) {
        this.txtExposicion = txtExposicion;
    }

    public TextArea getTxtExploracion() {
        return txtExploracion;
    }

    public void setTxtExploracion(TextArea txtExploracion) {
        this.txtExploracion = txtExploracion;
    }

    public TextArea getTxtProcedimientoOperatorio() {
        return txtProcedimientoOperatorio;
    }

    public void setTxtProcedimientoOperatorio(TextArea txtProcedimientoOperatorio) {
        this.txtProcedimientoOperatorio = txtProcedimientoOperatorio;
    }

    public void initialize() {
        try {
            iniciarCombos();
        } catch (Exception ex) {

        }
    }

    private void iniciarCombos() {
        cmbchkTipoOperacion.getItems().setAll(Otros.getTipoOperacion());
    }


    public void limpiarControles() {
        vBoxContainer.getChildren().forEach(Formularios::limpiarControles);
    }
}

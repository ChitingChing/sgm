package controllers;

import Dao.EmpresaDao;
import Dao.FichaPrenatalDao;
import Dao.Otros;
import entities.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.Formularios;
import utilidades.FxDialogs;

import javax.xml.soap.AttachmentPart;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FichaPrenatalController {

    @FXML
    private MaskerPane esperaMskPane;
    @FXML
    private TabPane panelFicha;
    @FXML
    private Tab tabAntecedentes = new Tab();
    @FXML
    private Tab tabEmbarazo = new Tab();
    @FXML
    private Tab tabConsultaEmbarazo= new Tab();
    @FXML
    private Tab tabParto= new Tab();
    @FXML
    private Tab tabTrabajoParto= new Tab();
    @FXML
    private Tab tabRecienNacido= new Tab();
    @FXML
    private Tab tabPuerperio = new Tab();
    @FXML
    private Tab tabEgreso = new Tab();
    @FXML
    private Tab tabPostOperatorio = new Tab();
    @FXML
    private  Tab tabHojaEvolucion = new Tab();
    @FXML
    private Tab tabArchivos = new Tab();
    @FXML
    public StackPane stackContainer;
    @FXML private CustomTextField txtCedula;
    @FXML private CustomTextField txtApellidosNombres;
    @FXML private CustomTextField txtrNHistoriaLaboral;
    @FXML private Button btnGuardar;
    @FXML private  Button btnBuscarficha;
    @FXML private  Button btnNuevo;
    private  NotificationPane notPane;

    private EmbarazoController embController;
    private  AntecedenteController anteController;
    private ConsultaEmbarazoController consultaEmbarazoController;
    private  PartoController partoController;
    private  TrabajoPartoController trabPartoController;
    private RecienNacidoController recienNacidoController;
    private PuerperioController puerperioController;
    private  EgresoController egresoController;
    private  PostOperatorioController postOperatorioController;
    private  HojaEvolucionController hojaEvolucionController;
    private ArchivoFichaPrenatalController archivoFichaController;
    private Boolean esNuevo;

    private Paciente paciente;
    private FichaPrenatal ficha;
    private TrabajoParto trabajoParto;
    private ConsultaEmbarazo consultaEmbarazo;
    private Puerperio puerperio;

    private String rutaCarpetaArchivos;

    public void initialize()  {

        try {
            esperaMskPane.setVisible(false);
            btnGuardar.setDisable(true);
            btnNuevo.setDisable(true);
            btnBuscarficha.setDisable(true);
            panelFicha.setDisable(true);

            Parent root;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Antecedente.fxml"));
            root = loader.load();
            anteController = loader.getController();
            tabAntecedentes.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/Embarazo.fxml"));
            root = loader.load();
            embController = loader.getController();
            tabEmbarazo.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/ConsultaEmbarazo.fxml"));
            root = loader.load();
            consultaEmbarazoController = loader.getController();
            tabConsultaEmbarazo.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/Parto.fxml"));
            root = loader.load();
            partoController = loader.getController();
            tabParto.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/TrabajoParto.fxml"));
            root = loader.load();
            trabPartoController = loader.getController();
            tabTrabajoParto.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/RecienNacido.fxml"));
            root = loader.load();
            recienNacidoController = loader.getController();
            tabRecienNacido.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/Puerperio.fxml"));
            root = loader.load();
            puerperioController = loader.getController();
            tabPuerperio.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/Egreso.fxml"));
            root = loader.load();
            egresoController = loader.getController();
            tabEgreso.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/PostOperatorio.fxml"));
            root = loader.load();
            postOperatorioController = loader.getController();
            tabPostOperatorio.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/HojaEvolucion.fxml"));
            root = loader.load();
            hojaEvolucionController = loader.getController();
            tabHojaEvolucion.setContent(root);

            loader = new FXMLLoader(getClass().getResource("/fxml/ArchivoFichaPrenatal.fxml"));
            root = loader.load();
            archivoFichaController = loader.getController();
            tabArchivos.setContent(root);

            EmpresaDao emp = new EmpresaDao();
            Empresa e = emp.getEmpresaDatos();
            if(e!=null) {
                rutaCarpetaArchivos = e.getDirectorioarchivos();

                File f = new File(rutaCarpetaArchivos);
                if (!f.isDirectory()) {
                    FxDialogs.showError("Error", "La carpeta donde se guardarán los archivos no existe, favor verificar antes de continuar");
                    throw new Exception();
                }
            }else{
                FxDialogs.showError("Error", "La carpeta donde se guardarán los archivos no existe, favor verificar antes de continuar");
                throw new Exception();
            }


        }catch (Exception ex){
            FxDialogs.showException("Error","No se pudo cargar el formulario",ex);
            esperaMskPane.getScene().getWindow().hide();
        }
    }

    public void showBuscarFicha(){
        esperaMskPane.setVisible(true);
        limpiarControles();
        Formularios f = new Formularios();
        ficha = f.showBusquedaFicha(txtCedula.getText());
        cargarDatosDeFicha();
        esperaMskPane.setVisible(false);
    }

    private void cargarDatosDeFicha() {
        try {
            if(ficha!=null) {
                if(ficha.getAnteFamiliares()!=null) {
                    Stream.of(ficha.getAnteFamiliares().split(",")).forEach(s -> anteController.chkcmbAntecedentesFamiliares.getCheckModel().check(s.trim()));
                }
                if(ficha.getAntePersonales()!=null) {
                    Stream.of(ficha.getAntePersonales().split(",")).forEach(s -> anteController.chkcmbAntecedentesPersonales.getCheckModel().check(s.trim()));
                }
                anteController.txtAntecedentesOtros.setText(ficha.getAnteOtros());
                anteController.chkRnMenor2500.selectedProperty().setValue(ficha.getRnMenor2500g());
                anteController.chkRnMenor2500.selectedProperty().setValue(ficha.getRnMenor2500g());
                anteController.chkGemelares.selectedProperty().setValue(ficha.getAnteGemelares());
                anteController.txtGestas.getValueFactory().setValue(ficha.getAnteGestas());
                anteController.txtAbortos.getValueFactory().setValue(ficha.getAnteAbortos());
                anteController.txtPartos.getValueFactory().setValue(ficha.getAntePartos());
                anteController.txtVaginales.getValueFactory().setValue(ficha.getAnteVaginales());
                anteController.txtCesareas.getValueFactory().setValue(ficha.getAnteCesareas());
                anteController.txtNacidosVivos.getValueFactory().setValue(ficha.getAnteNacidosVivos());
                anteController.txtNacidosMuertos.getValueFactory().setValue(ficha.getAnteNacidosMuertos());
                anteController.txtMuertoPrimeraSemana.getValueFactory().setValue(ficha.getAnteMuertos1Semana());
                anteController.txtViven.getValueFactory().setValue(ficha.getAnteViven());
                anteController.txtMuertoDespPriSemana.getValueFactory().setValue(ficha.getAnteMuertosmas1Semana());
                if (ficha.getAnteRnMayorPeso() != null)
                    anteController.txtRnMayorPeso.setText(ficha.getAnteRnMayorPeso().toString());
                anteController.dtpFechaFinAnteriorEmbrazo.setValue(ficha.getAnteFechaFinAnteriorEmbarazo());
                //Embarazo
                if (ficha.getEmbPesoAnterior() != null)
                    embController.txtPesoAnterior.setText(ficha.getEmbPesoAnterior().toString());
                if (ficha.getEmbTalla() != null) embController.txtTalla.setText(ficha.getEmbTalla().toString());
                embController.dtpFum.setValue(ficha.getEmbFum());
                embController.dtpFpp.setValue(ficha.getEmbFfp());
                embController.chkDudas.selectedProperty().setValue(ficha.getEmbDudas());
                embController.chkAntitetanica.selectedProperty().setValue(ficha.getEmbAntitetanicaPrevia());
                embController.cmbAntitetanica1Mes.getSelectionModel().select(ficha.getEmbAntitetanicaMesGestaActual1());
                embController.cmbAntitetanica2Mes.getSelectionModel().select(ficha.getEmbAntitetanicaMesGestaActual2());
                embController.cmbGrupoSanguineo.getSelectionModel().select(ficha.getEmbGrupoSanguineo());
                embController.cmbRh.getSelectionModel().select(ficha.getEmbRh());
                embController.chkSensibilidad.selectedProperty().setValue(ficha.getEmbSensibilidad());
                embController.chkFuma.selectedProperty().setValue(ficha.getEmbFuma());
                embController.txtCigarrillosPorDia.getValueFactory().setValue(ficha.getEmbCigarrilosPorDia());
                embController.chkExClinico.selectedProperty().setValue(ficha.getEmbExClinico());
                embController.chkExMamas.selectedProperty().setValue(ficha.getEmbExMamas());
                embController.chkExOdontologico.selectedProperty().setValue(ficha.getEmbExOdontologico());
                embController.chkPelvis.selectedProperty().setValue(ficha.getEmbExPelvis());
                embController.chkPapanicolao.selectedProperty().setValue(ficha.getEmbExPapanicolao());
                embController.chkColposcopia.selectedProperty().setValue(ficha.getEmbExColposcopia());
                embController.chkCervix.selectedProperty().setValue(ficha.getEmbExCervix());
                embController.cmbVdrl.getSelectionModel().select(ficha.getEmbVdrl());
                if (ficha.getEmbGlucosa() != null) embController.txtGlucosa.setText(ficha.getEmbGlucosa().toString());
                if (ficha.getEmbHb1() != null) embController.txtHb1.setText(ficha.getEmbHb1().toString());
                if (ficha.getEmbHb2() != null) embController.txtHb2.setText(ficha.getEmbHb2().toString());
                embController.dtpFechaVdrl.setValue(ficha.getEmbFechaVdrl());
                embController.dtpGlucosa.setValue(ficha.getEmbFechaGlucosa());
                embController.dtpHb1.setValue(ficha.getEmbFechaHb1());
                embController.dtpHb2.setValue(ficha.getEmbFechaHb2());
                embController.chkHospitalizacion.selectedProperty().setValue(ficha.getEmbHospitalizacion());
                embController.chkTraslado.selectedProperty().setValue(ficha.getEmbTraslado());
                embController.txtLugarTraslado.setText(ficha.getEmbLugarTraslado());
//        //consulta embarazo

                consultaEmbarazoController.consultaEmbarazoList.setAll(ficha.getConsultaEmbarazoCollection());
//        //parto
                partoController.getChkAborto().selectedProperty().setValue(ficha.getPartEsAborto());
                partoController.getTxtNConsultaPrenatal().getValueFactory().setValue(ficha.getPartConsultaPrenatal());
                partoController.getChkHospital().selectedProperty().setValue(ficha.getPartEnHospital());
                partoController.getChkCarnet().selectedProperty().setValue(ficha.getPartCarnet());
                partoController.getTxtOrigen().setText(ficha.getPartOrigenParto());
                if (ficha.getPartEdadGestacion() != null)
                    partoController.getTxtEdadGestacional().setText(ficha.getPartEdadGestacion().toString());
                partoController.getCmbPresentacionParto().getSelectionModel().select(ficha.getPartPresentacion());
                partoController.getChkTamanoFetalAdecuado().selectedProperty().setValue(ficha.getPartTamanoFetalAdecuado());
                if (ficha.getPartTemperaturaParto() != null)
                    partoController.getTxtTemperaturaParto().setText(ficha.getPartTemperaturaParto().toString());
                partoController.getCmbMembranas().getSelectionModel().select(ficha.getPartMembranas());
                if (ficha.getPartFechaRuptura() != null) {
                    partoController.getDtpFechaRuptura().setValue(ficha.getPartFechaRuptura().toLocalDate());
                    partoController.getTxtHoraRuptura().setText(ficha.getPartFechaRuptura().toLocalTime().toString());
                }
                partoController.getCmbInicioParto().getSelectionModel().select(ficha.getPartInicio());
                partoController.getCmbTerminacion().getSelectionModel().select(ficha.getPartTerminacion());
                if (ficha.getPartFechaTerminacion() != null) {
                    partoController.getDtpFechaTerminacion().setValue(ficha.getPartFechaTerminacion().toLocalDate());
                    partoController.getTxtHoraTerminacion().setText(ficha.getPartFechaTerminacion().toLocalTime().toString());
                }
                if(ficha.getPartPatologias()!=null) {
                    Stream.of(ficha.getPartPatologias().split(",")).forEach(s -> {
                        partoController.getChkcmbPatologiasParto().getItems().stream().forEach(patologia -> {
                            if (patologia.getDescripcion().equals(s))
                                partoController.getChkcmbPatologiasParto().getCheckModel().check(patologia);
                        });
                    });
                }
                if(ficha.getPartPatologiaspp()!=null) {
                    Stream.of(ficha.getPartPatologiaspp().split(",")).forEach(s -> {
                        partoController.getChkcmbPatologiasPP().getItems().stream().forEach(patologia -> {
                            if (patologia.getDescripcion().equals(s))
                                partoController.getChkcmbPatologiasPP().getCheckModel().check(patologia);
                        });
                    });
                }
                partoController.getTxtIndicacion().setText(ficha.getPartIndicacionPrincipal());
                partoController.getCmbMuerteIntraUte().getSelectionModel().select(ficha.getPartMuerteIntegerraUt());
                partoController.getChkEpisotomia().selectedProperty().setValue(ficha.getPartEpisiotomia());
                partoController.getChkDesgarros().selectedProperty().setValue(ficha.getPartDesgarro());
                partoController.getChkAlumbEsponta().selectedProperty().setValue(ficha.getPartAlumbEspont());
                partoController.getChkPlacentaCompl().selectedProperty().setValue(ficha.getPartPlacentaComp());
                if(ficha.getPartMedicacionParto()!=null) {
                    Stream.of(ficha.getPartMedicacionParto().split(",")).forEach(s -> partoController.getChkcmbMedicacionParto().getCheckModel().check(s));
                }
                partoController.getCmbNivelAtencion().getSelectionModel().select(ficha.getPartNivelAtencion());
                partoController.getTxtnombreRn().setText(ficha.getPartNombreRn());
                partoController.getCmbAtendioParto().getSelectionModel().select(ficha.getPartAtendioPartoCargo());
                partoController.getTxtnombreAtenParto().setText(ficha.getPartAtendioPartoNombre());
                partoController.getCmbAtendioNeonato().getSelectionModel().select(ficha.getPartAtendioNeonatoCargo());
                partoController.getTxtnombreAtenNeo().setText(ficha.getPartAtendioNeonatoNombre());
                if (ficha.getPartFechaIngresoParto() != null) {
                    partoController.getDtpFechaIngreso().setValue(ficha.getPartFechaIngresoParto().toLocalDate());
                    partoController.getTxtHoraIngreso().setText(ficha.getPartFechaIngresoParto().toLocalTime().toString());
                }
//        //trab parto
                trabPartoController.trabajoPartoList.setAll(ficha.getTrabajoPartoCollection());
//        //Recien nacido
                recienNacidoController.getCmbSexo().getSelectionModel().select(ficha.getRnSexo());
                if (ficha.getRnPesoNacer() != null)
                    recienNacidoController.getTxtPesoNacer().setText(ficha.getRnPesoNacer().toString());
                if (ficha.getRnTalla() != null)
                    recienNacidoController.getTxtTallaRn().setText(ficha.getRnTalla().toString());
                if (ficha.getRnPerCefalico() != null)
                    recienNacidoController.getTxtPerCefalico().setText(ficha.getRnPerCefalico().toString());
                if (ficha.getRnEdadPorExFisico() != null)
                    recienNacidoController.getTxtEdadExFisico().setText(ficha.getRnEdadPorExFisico().toString());
                recienNacidoController.getCmbPesoEg().getSelectionModel().select(ficha.getRnPesoEdadGestacional());
                if (ficha.getRnApgar1min() != null)
                    recienNacidoController.getTxtApgar1Min().setText(ficha.getRnApgar1min().toString());
                if (ficha.getRnApgar5min() != null)
                    recienNacidoController.getTxtApgar6Min().setText(ficha.getRnApgar5min().toString());
                recienNacidoController.getCmbReanimacion().getSelectionModel().select(ficha.getRnReaminRespir());
                recienNacidoController.getCmbVdrlRn().getSelectionModel().select(ficha.getRnVdrl());
                recienNacidoController.getCmbExFisico().getSelectionModel().select(ficha.getRnExamenFisico());
                if(ficha.getRnPatologias()!=null) {
                    Stream.of(ficha.getRnPatologias().split(",")).forEach(s -> {
                        recienNacidoController.getChkcmbPatologiasRn().getItems().stream().forEach(patologia -> {
                            if (patologia.getDescripcion().equals(s))
                                recienNacidoController.getChkcmbPatologiasRn().getCheckModel().check(patologia);
                        });
                    });
                }
                recienNacidoController.getChkAloj().selectedProperty().setValue(ficha.getRnAlojConjunto());
                recienNacidoController.getChkHospitalizado().selectedProperty().setValue(ficha.getRnHospitalizado());
                recienNacidoController.getChkBcg().selectedProperty().setValue(ficha.getRnBcg());
                recienNacidoController.getChkPvo().selectedProperty().setValue(ficha.getRnPvo());
                recienNacidoController.getCmbGrupoSanguineoRn().getSelectionModel().select(ficha.getRnGrupoSanguineo());
                recienNacidoController.getCmbRhRn().getSelectionModel().select(ficha.getRnRhh());
                recienNacidoController.getTxtObservacionesRn().setText(ficha.getRnObservaciones());
//        //puerperio
                puerperioController.puerperioList.setAll(ficha.getPuerperioCollection());
//        //egreso
                egresoController.getDtpFechaEgresoRn().setValue(ficha.getEgrFechaEgresoRn());
                egresoController.getCmbEstadoRn().getSelectionModel().select(ficha.getEgrEstadoRn());
                if (ficha.getEgrPesoEgresoRn() != null)
                    egresoController.getTxtPesoEgresoRn().setText(ficha.getEgrPesoEgresoRn().toString());
                egresoController.getCmbAlimento().getSelectionModel().select(ficha.getEgrAlimento());
                egresoController.getTxtResponsableRn().setText(ficha.getEgrResponsableEgresoRn());
                egresoController.getDtpFechaEgreso().setValue(ficha.getEgrFechaEgresoMaterno());
                egresoController.getCmbEstadoMadre().getSelectionModel().select(ficha.getEgrEstadoMaterno());
                egresoController.getCmbAnticoncepcion().getSelectionModel().select(ficha.getEgrAnticoncepcion());
                egresoController.getTxtResponsableMadre().setText(ficha.getEgrResponsableEgresoMaterno());
                //record operatorio
                postOperatorioController.getTxtServicio().setText(ficha.getRecOpServicio());
                postOperatorioController.getTxtSala().setText(ficha.getRecOpSala());
                postOperatorioController.getTxtCama().setText(ficha.getRecOpCama());
                postOperatorioController.getTxtPreOperatorio().setText(ficha.getRecOpPreoperatorio());
                postOperatorioController.getTxtPostOperatorio().setText(ficha.getRecOpPostoperatorio());
                postOperatorioController.getTxtProyectada().setText(ficha.getRecOpProyectada());
                if(ficha.getRecOpTipoOperacion()!=null) {
                    Stream.of(ficha.getRecOpTipoOperacion().split(",")).forEach(s -> postOperatorioController.getCmbchkTipoOperacion().getItems().stream().forEach(i -> {
                        if (i.equals(s))
                            postOperatorioController.getCmbchkTipoOperacion().getCheckModel().check(i);
                    }));
                }
                postOperatorioController.getTxtRealizada().setText(ficha.getRecOpRealizada());
                postOperatorioController.getTxtCirujano().setText(ficha.getRecOpCirculante());
                postOperatorioController.getTxtPrimerAyudante().setText(ficha.getRecOpPrimerAyudante());
                postOperatorioController.getTxtSegundoAyudante().setText(ficha.getRecOpSegundoAyudante());
                postOperatorioController.getTxtTercerAyudante().setText(ficha.getRecOpTercerAyudante());
                postOperatorioController.getDtpFechaOperacion().setValue(ficha.getRecOpFechaOperacion());
                postOperatorioController.getTxtHoraInicioOperacion().setText(ficha.getRecOpHoraInicio());
                postOperatorioController.getTxtHoraFinOperacion().setText(ficha.getRecOpHoraTerminacion());
                postOperatorioController.getTxtInstrumentista().setText(ficha.getRecOpInstrumentista());
                postOperatorioController.getTxtCirculante().setText(ficha.getRecOpCirculante());
                postOperatorioController.getTxtAnestesia().setText(ficha.getRecOpAnestesia());
                postOperatorioController.getTxtAyudanteAnestesia().setText(ficha.getRecOpAyudanteAnestesia());
                postOperatorioController.getTxtTipoAnestesia().setText(ficha.getRecOpTipoAnestesia());
                postOperatorioController.getTxtDieresis().setText(ficha.getRecOpDieresis());
                postOperatorioController.getTxtExposicion().setText(ficha.getRecOpExposicion());
                postOperatorioController.getTxtExploracion().setText(ficha.getRecOpExploHallazgosQui());
                postOperatorioController.getTxtProcedimientoOperatorio().setText(ficha.getRecOpProcedimientoOperatorio());
                // hoja evolucion
                hojaEvolucionController.listaItems.setAll(ficha.getHojaEvolucionPrescripcionCollection());
                //Archivos
                archivoFichaController.archivoFichaPrenatalList.setAll(ficha.getArchivoFichaPrenatalCollection());
                esNuevo = false;
                panelFicha.setDisable(false);
            }
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error. Ver mas en detalles",ex );
            esperaMskPane.setVisible(false);
        }
    }

    private void limpiarControles(){
        anteController.limpiarControles();
        embController.limpiarControles();
        consultaEmbarazoController.limpiarControlesYLista();
        partoController.limpiarControles();
        trabPartoController.limpiarControlesYLista();
        recienNacidoController.limpiarControles();
        egresoController.limpiarControles();
        puerperioController.limpiarControlesYLista();
        postOperatorioController.limpiarControles();
        hojaEvolucionController.limpiarControlesYLista();
        archivoFichaController.limpiarControlesYLista();

        ficha = null;
        consultaEmbarazo = null;
        trabajoParto = null;
        puerperio = null;
    }

    public void nuevaFicha(){
        esperaMskPane.setVisible(true);
        esNuevo=true;
        limpiarControles();
        esperaMskPane.setVisible(false);
        btnGuardar.setDisable(false);
        panelFicha.setDisable(false);
    }

    public void showPaciente(){
         esperaMskPane.setVisible(true);
         Formularios f = new Formularios();
         paciente= f.showBusquedaPaciente();
        if(paciente!=null){
            //panelFicha.setDisable(false);
            String apellidosNombres =paciente.getPrimerapellido().toUpperCase() +" "+paciente.getSegundoapellido().toUpperCase() + " "+
                    paciente.getPrimernombre().toUpperCase()+" "+paciente.getSegundonombre().toUpperCase();
            txtCedula.setText(paciente.getCedula());
            txtApellidosNombres.setText(apellidosNombres);
            txtrNHistoriaLaboral.setText(paciente.getNhistoriaclinica().toString());
            btnNuevo.setDisable(false);
            btnGuardar.setDisable(false);
            btnBuscarficha.setDisable(false);

        }else{
            txtCedula.clear();
            txtrNHistoriaLaboral.clear();
            txtApellidosNombres.clear();
            btnNuevo.setDisable(true);
            btnGuardar.setDisable(true);
            btnBuscarficha.setDisable(true);
        }
        panelFicha.setDisable(true);
        esNuevo=true;
        limpiarControles();
        esperaMskPane.setVisible(false);
    }
    public void guardarFicha(){
        try{
           if(!setDatosFicha())
               return;
            FichaPrenatalDao fDao= new FichaPrenatalDao();
            esperaMskPane.setVisible(true);
            Task<Boolean> tareaGuardarFicha = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    Boolean result = false;
                    copiarArchivos(archivoFichaController.archivoFichaPrenatalList);
                    result = fDao.guardarFicha(ficha,esNuevo);
                    return result;
                }
            };

            tareaGuardarFicha.setOnSucceeded(e->{
                Otros o = new Otros();
                notPane = (NotificationPane) stackContainer.getParent();
                if(tareaGuardarFicha.getValue()) {
                    esNuevo = false;
                    o.showNotificacionPane(notPane, "Datos guardados correctamente.",true  );


                }else{
                    o.showNotificacionPane(notPane, "No se pudo guardar los datos.",false  );
                }
                esperaMskPane.setVisible(false);
            });

            tareaGuardarFicha.setOnFailed(event -> {
                esperaMskPane.setVisible(false);
                FxDialogs.showException("Error",event.getSource().getException().getMessage(),new Exception(event.getSource().getException()));
            });
            copiarArchivos(archivoFichaController.archivoFichaPrenatalList);
            Thread threadGuardar = new Thread(tareaGuardarFicha);
            threadGuardar.setDaemon(true);
            threadGuardar.start();

        }catch (Exception ex) {
            FxDialogs.showException("Error","Ha ocurrido un error al guardar",ex );
            esperaMskPane.setVisible(false);
        }

    }

    private Boolean setDatosFicha(){
        Boolean ok =false;
        try {
            if(esNuevo) {
                ficha = new FichaPrenatal();
                ficha.setPaciente(paciente);
                ficha.setId(UUID.randomUUID());
                ficha.setFechaRegistro(LocalDateTime.now());
            }

            //Antecedente
            ficha.setAnteFamiliares(anteController.chkcmbAntecedentesFamiliares.getCheckModel().getCheckedItems().stream().collect(Collectors.joining(",")).toString());
            ficha.setAntePersonales(anteController.chkcmbAntecedentesPersonales.getCheckModel().getCheckedItems().stream().collect(Collectors.joining(",")).toString());
            ficha.setAnteOtros(anteController.txtAntecedentesOtros.getText().trim());
            ficha.setAnteRnMenor2500(anteController.chkRnMenor2500.isSelected());
            ficha.setAnteGemelares(anteController.chkGemelares.isSelected());
            ficha.setAnteGestas(anteController.txtGestas.getValue());
            ficha.setAnteAbortos(anteController.txtAbortos.getValue());
            ficha.setAntePartos(anteController.txtPartos.getValue());
            ficha.setAnteVaginales(anteController.txtVaginales.getValue());
            ficha.setAnteCesareas(anteController.txtCesareas.getValue());
            ficha.setAnteNacidosVivos(anteController.txtNacidosVivos.getValue());
            ficha.setAnteNacidosMuertos(anteController.txtNacidosMuertos.getValue());
            ficha.setAnteMuertos1Semana(anteController.txtMuertoPrimeraSemana.getValue());
            ficha.setAnteViven(anteController.txtViven.getValue());
            ficha.setAnteMuertosmas1Semana(anteController.txtMuertoDespPriSemana.getValue());
            if(!anteController.txtRnMayorPeso.getText().trim().isEmpty())
                ficha.setAnteRnMayorPeso(new BigDecimal(anteController.txtRnMayorPeso.getText().trim()));
            ficha.setAnteFechaFinAnteriorEmbarazo(anteController.dtpFechaFinAnteriorEmbrazo.getValue());
            //Embarazo
            if(!embController.txtPesoAnterior.getText().trim().isEmpty())
                ficha.setEmbPesoAnterior(new BigDecimal(embController.txtPesoAnterior.getText().trim()));
            if(!embController.txtTalla.getText().trim().isEmpty())
                ficha.setEmbTalla(Integer.parseInt(embController.txtTalla.getText().trim()));
            ficha.setEmbFum(embController.dtpFum.getValue());
            ficha.setEmbFfp(embController.dtpFpp.getValue());
            ficha.setEmbDudas(embController.chkDudas.isSelected());
            ficha.setEmbAntitetanicaPrevia(embController.chkAntitetanica.isSelected());
            ficha.setEmbAntitetanicaMesGestaActual1(embController.cmbAntitetanica1Mes.getSelectionModel().getSelectedItem());
            ficha.setEmbAntitetanicaMesGestaActual2(embController.cmbAntitetanica2Mes.getSelectionModel().getSelectedItem());
            ficha.setEmbGrupoSanguineo(embController.cmbGrupoSanguineo.getSelectionModel().getSelectedItem());
            ficha.setEmbRh(embController.cmbRh.getSelectionModel().getSelectedItem());
            ficha.setEmbSensibilidad(embController.chkSensibilidad.isSelected());
            ficha.setEmbFuma(embController.chkFuma.isSelected());
            ficha.setEmbCigarrilosPorDia(embController.txtCigarrillosPorDia.getValue());
            ficha.setEmbExClinico(embController.chkExClinico.isSelected());
            ficha.setEmbExMamas(embController.chkExMamas.isSelected());
            ficha.setEmbExOdontologico(embController.chkExOdontologico.isSelected());
            ficha.setEmbExPelvis(embController.chkPelvis.isSelected());
            ficha.setEmbExPapanicolao(embController.chkPapanicolao.isSelected());
            ficha.setEmbExColposcopia(embController.chkColposcopia.isSelected());
            ficha.setEmbExCervix(embController.chkCervix.isSelected());
            ficha.setEmbVdrl(embController.cmbVdrl.getSelectionModel().getSelectedItem());
            if(!embController.txtGlucosa.getText().trim().isEmpty())
                ficha.setEmbGlucosa(Integer.parseInt(embController.txtGlucosa.getText().trim()));
            if(!embController.txtHb1.getText().trim().isEmpty())
                ficha.setEmbHb1(Integer.parseInt(embController.txtHb1.getText().trim()));
            if(!embController.txtHb2.getText().trim().isEmpty())
                ficha.setEmbHb2(Integer.parseInt(embController.txtHb2.getText().trim()));
            ficha.setEmbFechaVdrl(embController.dtpFechaVdrl.getValue());
            ficha.setEmbFechaGlucosa(embController.dtpGlucosa.getValue());
            ficha.setEmbFechaHb1(embController.dtpHb1.getValue());
            ficha.setEmbFechaHb2(embController.dtpHb2.getValue());
            ficha.setEmbHospitalizacion(embController.chkHospitalizacion.isSelected());
            ficha.setEmbTraslado(embController.chkTraslado.isSelected());
            ficha.setEmbLugarTraslado(embController.txtLugarTraslado.getText().trim());
            //consulta embarazo
            consultaEmbarazoController.consultaEmbarazoList.stream().forEach(x-> x.setFichaPrenatal(ficha));
            ficha.setConsultaEmbarazoCollection(consultaEmbarazoController.consultaEmbarazoList);
            //parto
            ficha.setPartEsAborto(partoController.getChkAborto().isSelected());
            ficha.setPartConsultaPrenatal(partoController.getTxtNConsultaPrenatal().getValue());
            ficha.setPartEnHospital(partoController.getChkHospital().isSelected());
            ficha.setPartCarnet(partoController.getChkCarnet().isSelected());
            ficha.setPartOrigenParto(partoController.getTxtOrigen().getText().trim());
            if(!partoController.getTxtEdadGestacional().getText().trim().isEmpty())
                ficha.setPartEdadGestacion(Integer.parseInt(partoController.getTxtEdadGestacional().getText().trim()));
            ficha.setPartPresentacion(partoController.getCmbPresentacionParto().getSelectionModel().getSelectedItem());
            ficha.setPartTamanoFetalAdecuado(partoController.getChkTamanoFetalAdecuado().isSelected());
            if(!partoController.getTxtTemperaturaParto().getText().trim().isEmpty())
                ficha.setPartTemperaturaParto(new BigDecimal(partoController.getTxtTemperaturaParto().getText().trim()));
            ficha.setPartMembranas(partoController.getCmbMembranas().getSelectionModel().getSelectedItem());
            LocalDateTime fecharupt=null;
            if(partoController.getDtpFechaRuptura().getValue()!=null) {
                fecharupt = LocalDateTime.of(partoController.getDtpFechaRuptura().getValue(), LocalTime.MIN);
                if(!partoController.getTxtHoraRuptura().getText().trim().isEmpty()) {
                    fecharupt = LocalDateTime.of(partoController.getDtpFechaRuptura().getValue(), LocalTime.parse(partoController.getTxtHoraRuptura().getText().trim()));
                }
            }
            ficha.setPartFechaRuptura(fecharupt);
            ficha.setPartInicio(partoController.getCmbInicioParto().getSelectionModel().getSelectedItem());
            ficha.setPartTerminacion(partoController.getCmbTerminacion().getSelectionModel().getSelectedItem());
            LocalDateTime fechaTerm=null;
            if(partoController.getDtpFechaTerminacion().getValue()!=null) {
                fechaTerm = LocalDateTime.of(partoController.getDtpFechaTerminacion().getValue(), LocalTime.MIN);
                if(!partoController.getTxtHoraTerminacion().getText().trim().isEmpty()) {
                    fechaTerm = LocalDateTime.of(partoController.getDtpFechaTerminacion().getValue(), LocalTime.parse(partoController.getTxtHoraTerminacion().getText().trim()));
                }
            }
            ficha.setPartFechaTerminacion(fechaTerm);
            ficha.setPartPatologias(partoController.getChkcmbPatologiasParto().getCheckModel().getCheckedItems().stream().map(p->p.getDescripcion()).collect(Collectors.joining(",")));
            ficha.setPartPatologiaspp(partoController.getChkcmbPatologiasPP().getCheckModel().getCheckedItems().stream().map(p->p.getDescripcion()).collect(Collectors.joining(",")));
            ficha.setPartIndicacionPrincipal(partoController.getTxtIndicacion().getText().trim());
            ficha.setPartMuerteIntegerraUt(partoController.getCmbMuerteIntraUte().getSelectionModel().getSelectedItem());
            ficha.setPartEpisiotomia(partoController.getChkEpisotomia().isSelected());
            ficha.setPartDesgarro(partoController.getChkDesgarros().isSelected());
            ficha.setPartAlumbEspont(partoController.getChkAlumbEsponta().isSelected());
            ficha.setPartPlacentaComp(partoController.getChkPlacentaCompl().isSelected());
            ficha.setPartMedicacionParto(partoController.getChkcmbMedicacionParto().getCheckModel().getCheckedItems().stream().collect(Collectors.joining(",")));
            ficha.setPartNivelAtencion(partoController.getCmbNivelAtencion().getSelectionModel().getSelectedItem());
            ficha.setPartNombreRn(partoController.getTxtnombreRn().getText().trim());
            ficha.setPartAtendioPartoCargo(partoController.getCmbAtendioParto().getSelectionModel().getSelectedItem());
            ficha.setPartAtendioPartoNombre(partoController.getTxtnombreAtenParto().getText().trim());
            ficha.setPartAtendioNeonatoCargo(partoController.getCmbAtendioNeonato().getSelectionModel().getSelectedItem());
            ficha.setPartAtendioNeonatoNombre(partoController.getTxtnombreAtenNeo().getText().trim());
            LocalDateTime fechaIngreso=null;
            if(partoController.getDtpFechaIngreso().getValue()!=null) {
                fechaIngreso = LocalDateTime.of(partoController.getDtpFechaIngreso().getValue(), LocalTime.MIN);
                if(!partoController.getTxtHoraIngreso().getText().trim().isEmpty()) {
                    fechaIngreso = LocalDateTime.of(partoController.getDtpFechaIngreso().getValue(), LocalTime.parse(partoController.getTxtHoraIngreso().getText().trim()));
                }
            }
            ficha.setPartFechaIngresoParto(fechaIngreso);
            //trab parto
            trabPartoController.trabajoPartoList.stream().forEach(x->x.setFichaPrenatal(ficha));
            ficha.setTrabajoPartoCollection(trabPartoController.trabajoPartoList);
            //Recien nacido
            ficha.setRnSexo(recienNacidoController.getCmbSexo().getSelectionModel().getSelectedItem());
            if(!recienNacidoController.getTxtPesoNacer().getText().trim().isEmpty())
                ficha.setRnPesoNacer(Integer.parseInt(recienNacidoController.getTxtPesoNacer().getText().trim()));
            if(!recienNacidoController.getTxtTallaRn().getText().trim().isEmpty())
                ficha.setRnTalla(Integer.parseInt(recienNacidoController.getTxtTallaRn().getText().trim()));
            if(!recienNacidoController.getTxtPerCefalico().getText().trim().isEmpty())
                ficha.setRnPerCefalico(Integer.parseInt(recienNacidoController.getTxtPerCefalico().getText().trim()));
            if(!recienNacidoController.getTxtEdadExFisico().getText().trim().isEmpty())
                ficha.setRnEdadPorExFisico(Integer.parseInt(recienNacidoController.getTxtEdadExFisico().getText().trim()));
            ficha.setRnPesoEdadGestacional(recienNacidoController.getCmbPesoEg().getSelectionModel().getSelectedItem());
            if(!recienNacidoController.getTxtApgar1Min().getText().trim().isEmpty())
                ficha.setRnApgar1min(Integer.parseInt(recienNacidoController.getTxtApgar1Min().getText().trim()));
            if(!recienNacidoController.getTxtApgar6Min().getText().trim().isEmpty())
                ficha.setRnApgar5min(Integer.parseInt(recienNacidoController.getTxtApgar6Min().getText().trim()));
            ficha.setRnReaminRespir(recienNacidoController.getCmbReanimacion().getSelectionModel().getSelectedItem());
            ficha.setRnVdrl(recienNacidoController.getCmbVdrlRn().getSelectionModel().getSelectedItem());
            ficha.setRnExamenFisico(recienNacidoController.getCmbExFisico().getSelectionModel().getSelectedItem());
            ficha.setRnPatologias(recienNacidoController.getChkcmbPatologiasRn().getCheckModel().getCheckedItems().stream().map(p->p.getDescripcion()).collect(Collectors.joining(",")));
            ficha.setRnAlojConjunto(recienNacidoController.getChkAloj().isSelected());
            ficha.setRnHospitalizado(recienNacidoController.getChkHospitalizado().isSelected());
            ficha.setRnBcg(recienNacidoController.getChkBcg().isSelected());
            ficha.setRnPvo(recienNacidoController.getChkPvo().isSelected());
            ficha.setRnGrupoSanguineo(recienNacidoController.getCmbGrupoSanguineoRn().getSelectionModel().getSelectedItem());
            ficha.setRnRhh(recienNacidoController.getCmbRhRn().getSelectionModel().getSelectedItem());
            ficha.setRnObservaciones(recienNacidoController.getTxtObservacionesRn().getText().trim());
            //puerperio
            puerperioController.puerperioList.stream().forEach(x->x.setFichaPrenatal(ficha));
            ficha.setPuerperioCollection(puerperioController.puerperioList);
            //egreso
            ficha.setEgrFechaEgresoRn(egresoController.getDtpFechaEgresoRn().getValue());
            ficha.setEgrEstadoRn(egresoController.getCmbEstadoRn().getSelectionModel().getSelectedItem());
            if(!egresoController.getTxtPesoEgresoRn().getText().trim().isEmpty())
                ficha.setEgrPesoEgresoRn(Integer.parseInt(egresoController.getTxtPesoEgresoRn().getText().trim()));
            ficha.setEgrAlimento(egresoController.getCmbAlimento().getSelectionModel().getSelectedItem());
            ficha.setEgrResponsableEgresoRn(egresoController.getTxtResponsableRn().getText().trim());
            ficha.setEgrFechaEgresoMaterno(egresoController.getDtpFechaEgreso().getValue());
            ficha.setEgrEstadoMaterno(egresoController.getCmbEstadoMadre().getSelectionModel().getSelectedItem());
            ficha.setEgrAnticoncepcion(egresoController.getCmbAnticoncepcion().getSelectionModel().getSelectedItem());
            ficha.setEgrResponsableEgresoMaterno(egresoController.getTxtResponsableMadre().getText().trim());
            //record operatorio

            ficha.setRecOpServicio(postOperatorioController.getTxtServicio().getText().trim());
            ficha.setRecOpSala(postOperatorioController.getTxtSala().getText().trim());
            ficha.setRecOpCama(postOperatorioController.getTxtCama().getText().trim());
            ficha.setRecOpPreoperatorio(postOperatorioController.getTxtPreOperatorio().getText().trim());
            ficha.setRecOpPostoperatorio(postOperatorioController.getTxtPostOperatorio().getText().trim());
            ficha.setRecOpProyectada(postOperatorioController.getTxtProyectada().getText().trim());
            ficha.setRecOpTipoOperacion(postOperatorioController.getCmbchkTipoOperacion().getCheckModel().getCheckedItems().stream().collect(Collectors.joining(",")));
            ficha.setRecOpRealizada(postOperatorioController.getTxtRealizada().getText().trim());
            ficha.setRecOpCirujano(postOperatorioController.getTxtCirujano().getText().trim());
            ficha.setRecOpPrimerAyudante(postOperatorioController.getTxtPrimerAyudante().getText().trim());
            ficha.setRecOpSegundoAyudante(postOperatorioController.getTxtSegundoAyudante().getText().trim());
            ficha.setRecOpTercerAyudante(postOperatorioController.getTxtTercerAyudante().getText().trim());
            ficha.setRecOpFechaOperacion(postOperatorioController.getDtpFechaOperacion().getValue());
            ficha.setRecOpHoraInicio(postOperatorioController.getTxtHoraInicioOperacion().getText().trim());
            ficha.setRecOpHoraTerminacion(postOperatorioController.getTxtHoraFinOperacion().getText().trim());
            ficha.setRecOpInstrumentista(postOperatorioController.getTxtInstrumentista().getText().trim());
            ficha.setRecOpCirculante(postOperatorioController.getTxtCirculante().getText().trim());
            ficha.setRecOpAnestesia(postOperatorioController.getTxtAnestesia().getText().trim());
            ficha.setRecOpAyudanteAnestesia(postOperatorioController.getTxtAyudanteAnestesia().getText().trim());
            ficha.setRecOpTipoAnestesia(postOperatorioController.getTxtTipoAnestesia().getText().trim());
            ficha.setRecOpDieresis(postOperatorioController.getTxtDieresis().getText().trim());
            ficha.setRecOpExposicion(postOperatorioController.getTxtExposicion().getText().trim());
            ficha.setRecOpExploHallazgosQui(postOperatorioController.getTxtExploracion().getText().trim());
            ficha.setRecOpProcedimientoOperatorio(postOperatorioController.getTxtProcedimientoOperatorio().getText().trim());

            //hojaevolucion
            hojaEvolucionController.listaItems.stream().forEach(x->x.setFichaPrenatal(ficha));
            ficha.setHojaEvolucionPrescripcionCollection(hojaEvolucionController.listaItems);

            //archivos
            archivoFichaController.archivoFichaPrenatalList.stream().forEach(x->{
                x.setFichaPrenatal(ficha);
                x.setRutaarchivo("\\"+ficha.getPaciente().getId()+"\\"+ficha.getId()+"\\"+x.getNombrearchivo());
            });
            ficha.setArchivoFichaPrenatalCollection(archivoFichaController.archivoFichaPrenatalList);

        ok= true;



        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver en detalles",ex);
            ok=false;
        }
        return ok;
    }

    private void copiarArchivos(Collection<ArchivoFichaPrenatal> ListaArchivos) {
        Boolean ok =true;
        EmpresaDao emp = new EmpresaDao();
        Empresa e = emp.getEmpresaDatos();

        if(e!=null) {
            rutaCarpetaArchivos = e.getDirectorioarchivos();
        }

        ListaArchivos.stream().forEach(x -> {
            try {
                File f = new File(rutaCarpetaArchivos+x.getRutaarchivo());
                f = new File(f.getPath());

                if(x.getRutaarchivo() != null && x.getRutaarchivoorigen() !=null && !x.getCopiado() && Character.valueOf('A').equals(x.getEstado())) {

                    Path origen = Paths.get(x.getRutaarchivoorigen());
                    Path destino = Paths.get(rutaCarpetaArchivos+x.getRutaarchivo());
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    x.setCopiado(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                FxDialogs.showException("Error", "Ha ocurrido un error. Consulte mas en el detalle", ex);
            }
        });

    }


}

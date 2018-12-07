package controllers;

import Dao.FichaPrenatalDao;
import entities.FichaPrenatal;
import entities.Puerperio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.MaskerPane;
import utilidades.FxDialogs;
import utilidades.TableUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusquedaFichaController {
    @FXML
    private TableView<FichaPrenatal> tblFicha;

    @FXML
    private MaskerPane mskEspera;
    public FichaPrenatal ficha=null;

    public void initialize(){
        try{
            iniciarColumnas();
        }catch (Exception ex){
            FxDialogs.showException("Se ha generado un error!!","Dar click para ver mas detalles...",ex);
            mskEspera.setVisible(false);
        }
    }

    private void iniciarColumnas() {
        TableUtils.installCopyPasteHandler(tblFicha);
        tblFicha.getSelectionModel().setCellSelectionEnabled(true);
        tblFicha.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<TableColumn<FichaPrenatal,?>> columnas = new ArrayList<>();

        TableColumn<FichaPrenatal, LocalDateTime > colFecha =new TableColumn<>("Fecha Registro");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colFecha.setCellFactory((TableColumn<FichaPrenatal, LocalDateTime> column) -> {
            return new TableCell<FichaPrenatal, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    }
                }
            };
        });
        columnas.add(colFecha);

        TableColumn<FichaPrenatal,LocalDateTime> colFechaIngreso =new TableColumn<>("Fecha Ingreso Parto");
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("partFechaIngreso"));
        colFechaIngreso.setCellFactory((TableColumn<FichaPrenatal, LocalDateTime> column) -> {
            return new TableCell<FichaPrenatal, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    }
                }
            };
        });
        columnas.add(colFechaIngreso);

        TableColumn<FichaPrenatal,LocalDateTime> colFechaTerm =new TableColumn<>("Fecha Terminación Parto");
        colFechaTerm.setCellValueFactory(new PropertyValueFactory<>("partFechaTerminacion"));
        colFechaTerm.setCellFactory((TableColumn<FichaPrenatal, LocalDateTime> column) -> {
            return new TableCell<FichaPrenatal, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    }
                }
            };
        });
        columnas.add(colFechaTerm);

        TableColumn<FichaPrenatal,Boolean> colEsAborto =new TableColumn<>("Aborto");
        colEsAborto.setCellValueFactory(new PropertyValueFactory<>("partEsAborto"));
        columnas.add(colEsAborto);

        TableColumn<FichaPrenatal,String> colTerminacion =new TableColumn<>("Terminación Parto");
        colTerminacion.setCellValueFactory(new PropertyValueFactory<>("partTerminacion"));
        columnas.add(colTerminacion);

        TableColumn<FichaPrenatal,String> colRnNombre =new TableColumn<>("Nombre Recien Nacido");
        colRnNombre.setCellValueFactory(new PropertyValueFactory<>("partNombreRn"));
        columnas.add(colRnNombre);

        TableColumn<FichaPrenatal,String> colAtendioPartNombre =new TableColumn<>("Nombre Atendió Parto");
        colAtendioPartNombre.setCellValueFactory(new PropertyValueFactory<>("partAtendioPartoNombre"));
        columnas.add(colAtendioPartNombre);

        tblFicha.getColumns().addAll(columnas);

        tblFicha.setRowFactory(param -> {
            TableRow<FichaPrenatal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount()==2 && (!row.isEmpty() )){
                    ficha = row.getItem();
                    tblFicha.getScene().getWindow().hide();
                }
            });
            return row;
        });

    }
    public void cargarFichas(String Cedula){
        FichaPrenatalDao fDao= new FichaPrenatalDao();
        tblFicha.setItems(FXCollections.observableList(fDao.getFichasByCedula(Cedula)));
        mskEspera.setVisible(false);
    }

}


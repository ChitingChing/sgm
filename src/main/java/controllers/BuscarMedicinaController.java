package controllers;

import Dao.MedicinaDao;
import entities.Medicina;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.FxDialogs;
import utilidades.TableUtils;

import java.util.ArrayList;
import java.util.List;

public class BuscarMedicinaController {

    @FXML
    private CustomTextField txtTextoBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<Medicina> tblMedicina;

    @FXML
    private MaskerPane mskEsperar;
    private ObservableList<Medicina> medicinaList;
    public Medicina medicina;

    
    public void initialize(){
        try{
            iniciarColumnas();
        }catch (Exception ex){
            FxDialogs.showException("Se ha generado un error!!", "Dar click para ver mas detalles...", ex);
        }
    }

    private void iniciarColumnas() {

        TableUtils.installCopyPasteHandler(tblMedicina);
        tblMedicina.getSelectionModel().setCellSelectionEnabled(true);
        tblMedicina.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<TableColumn<Medicina, String>> columnas = new ArrayList<>();
        TableColumn<Medicina, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnas.add(colDescripcion);

        TableColumn<Medicina, String> colPrescripcion = new TableColumn<>("Prescripción");
        colPrescripcion.setCellValueFactory(new PropertyValueFactory<>("prescripcion"));
        columnas.add(colPrescripcion);



        tblMedicina.getColumns().addAll(columnas);
        tblMedicina.getColumns().forEach(medicinaTableColumn -> medicinaTableColumn.setMinWidth(200));

        tblMedicina.setRowFactory(param -> {
            TableRow<Medicina> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    medicina = row.getItem();
                    tblMedicina.getScene().getWindow().hide();
                }
            });
            return row;
        });

    }

    public void buscar(){
        try {

            Task<Void> tareaBuscar = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    //if (!txtTextoBuscar.getText().trim().equals("")) {
                        mskEsperar.setVisible(true);
                        MedicinaDao mDao = new MedicinaDao();
                        tblMedicina.setItems(mDao.getMedicinaByNombre(txtTextoBuscar.getText().trim()));
                        mskEsperar.setVisible(false);
                   // }
                    return null;
                }
            };

            Thread threadGuardar = new Thread(tareaBuscar);
            threadGuardar.setDaemon(true);
            threadGuardar.start();

        }catch (Exception ex){
            FxDialogs.showException("Se ha generado un error!!","Dar click para ver mas detalles...",ex);
            mskEsperar.setVisible(false);
        }

    }

}

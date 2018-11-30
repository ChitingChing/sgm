package controllers;

import Dao.EmpresaDao;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import entities.ArchivoFichaPrenatal;
import entities.Empresa;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomTextField;
import utilidades.Formularios;
import utilidades.FxDialogs;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class ArchivoFichaPrenatalController {


    @FXML
    private VBox vbArchivos;

    @FXML
    private CustomTextField txtrutaArchivo;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<ArchivoFichaPrenatal> tblArchivos;

    @FXML private CustomTextField txtDescripcion;

    public ObservableList<ArchivoFichaPrenatal>  archivoFichaPrenatalList = FXCollections.observableArrayList();
    private FilteredList<ArchivoFichaPrenatal> listaFiltrada;
    private String rutaCarpetaArchivos;


    public void initialize(){
        EmpresaDao emp = new EmpresaDao();
        Empresa e = emp.getEmpresaDatos();
        if(e!=null)
            rutaCarpetaArchivos = e.getDirectorioarchivos();

        iniciarColumnas();

        tblArchivos.setRowFactory(param -> {
            TableRow<ArchivoFichaPrenatal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount()==2 && (!row.isEmpty())){

                   File f = new File(rutaCarpetaArchivos+row.getItem().getRutaarchivo());
                   Desktop d = Desktop.getDesktop();
                    try {
                        d.open(f);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        FxDialogs.showException("Error","No seha podido abrir el archivo.",e1);
                    }
                }
            });
            return row;
        });

        archivoFichaPrenatalList.addListener(new ListChangeListener<ArchivoFichaPrenatal>() {
            @Override
            public void onChanged(Change<? extends ArchivoFichaPrenatal> c) {
                System.out.println("cambio");
                tblArchivos.refresh();
            }
        });

        btnEliminar.setOnAction(event -> eliminar());

    }
    public void escogerArchivo() throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes","*.png","*.jpg","*.jpeg"));
        File file = fc.showOpenDialog(btnBuscar.getScene().getWindow());
        if(file != null) {
            txtrutaArchivo.setText(file.getPath());
            txtDescripcion.clear();
        }

    }

    private void iniciarColumnas(){
        List<TableColumn<ArchivoFichaPrenatal,String>> columnas = new ArrayList<>();

        TableColumn<ArchivoFichaPrenatal,String> colNombre =new TableColumn<>("Nombre del Archivo");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombrearchivo"));
        columnas.add(colNombre);

        TableColumn<ArchivoFichaPrenatal,String> colDescripcion =new TableColumn<>("Descripción del Archivo");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnas.add(colDescripcion);

        TableColumn<ArchivoFichaPrenatal,String> colRutaArchivo =new TableColumn<>("Ruta del Archivo");
        colRutaArchivo.setCellValueFactory(new PropertyValueFactory<>("rutaarchivo"));
        columnas.add(colRutaArchivo);

        tblArchivos.getColumns().addAll(columnas);

        listaFiltrada = new FilteredList<>(archivoFichaPrenatalList);
        archivoFichaPrenatalList.addListener(new ListChangeListener<ArchivoFichaPrenatal>() {
            @Override
            public void onChanged(Change<? extends ArchivoFichaPrenatal> c) {
                listaFiltrada.setPredicate(archivoFichaPrenatal -> Character.valueOf('A').equals(archivoFichaPrenatal.getEstado()));
            }
        });

        tblArchivos.setItems(listaFiltrada);
        tblArchivos.getColumns().stream().forEach(c->c.setMinWidth(200));
    }

    public void limpiarControles(){
        txtrutaArchivo.clear();
    }
    public void limpiarControlesYLista(){
        txtrutaArchivo.clear();
        archivoFichaPrenatalList.clear();
    }

    public void agregar(){
        try {
            File f = new File(txtrutaArchivo.getText());

            long repetidos= archivoFichaPrenatalList.stream().filter(x-> x.getNombrearchivo().equals(f.getName())).count();
            if (f.exists() && repetidos==0 ) {
                ArchivoFichaPrenatal archivo = new ArchivoFichaPrenatal();
                archivo.setId(UUID.randomUUID());
                archivo.setExtensionarchivo(getExtensionByStringHandling(f.getName()).toString());
                archivo.setNombrearchivo(f.getName());
                //archivo.setRutaarchivo(); // se agrega antes de guardar la ficha por el id de la ficha que se genera en ese momento
                archivo.setDescripcion(txtDescripcion.getText());
                archivo.setRutaarchivoorigen(txtrutaArchivo.getText());
                archivo.setCopiado(false);
                archivo.setEstado('A');
                archivoFichaPrenatalList.add(archivo);
            } else {
                FxDialogs.showError("Error", "El archivo especificado no es válido o ya se encuentra uno con el mismo nombre. Favor elegir nuevamente el archivo.");
            }
        }catch (Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error ver los detalles",ex);
        }
    }

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public void eliminar(){
        try{
            ArchivoFichaPrenatal temp =tblArchivos.getSelectionModel().getSelectedItem();
            if(temp!= null){
                int i= archivoFichaPrenatalList.indexOf(temp);
                temp.setEstado('E');
                archivoFichaPrenatalList.set(i,temp);
                FxDialogs.showInformation("asd","affd");
            }
        }
        catch(Exception ex){
            FxDialogs.showException("Error","Ha ocurrido un error",ex);
        }
    }
}

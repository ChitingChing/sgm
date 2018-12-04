package controllers;

import Dao.EmpresaDao;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import entities.Canton;
import entities.Empresa;
import entities.Provincia;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.postgresql.util.PSQLException;
import utilidades.ConnectionInfo;
import utilidades.Formularios;
import Dao.Otros;
import utilidades.FxDialogs;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.stream.Stream;


public class InicioController {

    public static ObservableList<Provincia> provincias ;
    public static ObservableList<Canton> cantonesLosRios;
    public static ObservableList<String> estudios;
    public static ObservableList<String> estadoCiviles;

    @FXML private Label lblConnectioninfo;

    @FXML
    public void initialize(){
        try {

            ConnectionInfo connectionInfo = Otros.getInfoConnection();
            String info = "Conexión: " + connectionInfo.dataBaseUrl + " User: " + connectionInfo.getUsername();
            lblConnectioninfo.setText(info);

            cargarDatos();
        }catch (Exception ex){
            FxDialogs.showException("Error","No se ha podido iniciar la aplicacion",ex);
        }
    }

    public void showBusquedaPaciente() throws IOException {
        //Formularios f = new Formularios(this);
        //f.showBusquedaPaciente();
    }

    public void showPaciente() throws IOException {
      Formularios f = new Formularios();
      f.showPaciente();

    }
    public void showNuevoFichaPrenatal() throws IOException {
        Formularios f = new Formularios();
        f.showNuevoFichaPrenatal();
    }

    public void showEmpresa() throws IOException {
        Formularios f = new Formularios();
        f.showEmpresa();
    }
    public void showMedicinas() throws IOException {
        Formularios f = new Formularios();
        f.showMedicina();
    }
    public  void showPrescripcion() throws IOException {
//        Formularios f = new Formularios();
//        f.showPrescripcion();
        Document document = new Document();
        try {
            File f = new File("D:\\prescripcion"+ LocalDateTime.now().toLocalDate()+".pdf");
/*            PdfWriter.getInstance(document,new FileOutputStream(f.getPath()));
            document.open();
            PdfPTable table = new PdfPTable(2);
            addTableHeader(table);
            addRows(table);

            document.add(table);
            document.close();
            FileOutputStream a ;*/

            HtmlConverter.convertToPdf("<h1>TST</>",new PdfWriter(f));

            Desktop.getDesktop().open(f);

        } catch (Exception e) {
            e.printStackTrace();
            FxDialogs.showException("Error","Ha ocurrido un error",e);
        }

    }

    private void addTableHeader(PdfPTable table) {
        EmpresaDao ed = new EmpresaDao();
        Empresa e = ed.getEmpresaDatos();
        Stream.of(e.getNombre())
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

    }
    private void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private void cargarDatos() {
        try {
            provincias = Otros.getProvincias();
            estadoCiviles = Otros.getEstadoCiviles();
            estudios = Otros.getEstudios();
            cantonesLosRios = Otros.getCantones("12");
        } catch (Exception ex) {
            FxDialogs.showException("Error", "No se ha podido iniciar la aplicacion", ex);
        }

    }

}

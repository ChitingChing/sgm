package utilidades;

import controllers.*;
import entities.FichaPrenatal;
import entities.Medicina;
import entities.Paciente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.IOException;

public class Formularios {

    private InicioController inicioController;

    public Formularios(InicioController controller) {
        inicioController = controller;
    }

    public Formularios() {
    }

    public void showBusquedaPaciente(Object controller) throws IOException {

    }

    public void showPaciente() throws IOException {
        NotificationPane np = new NotificationPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Paciente.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        np.setContent(root);
        Scene scene = new Scene(np);
        scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ingreso de Pacientes");
        stage.showAndWait();

    }

    public void showNuevoFichaPrenatal() throws IOException {
        NotificationPane np = new NotificationPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FichaPrenatal.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        np.setContent(root);
        Scene scene = new Scene(np);
        scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nuevo Ficha Prenatal");
        stage.show();

    }

    public Paciente showBusquedaPaciente() {
        Paciente p = null;
        try {
            NotificationPane np = new NotificationPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BusquedaPaciente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            np.setContent(root);
            Scene scene = new Scene(np);
            scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Búsqueda de Pacientes");
            stage.showAndWait();

            BusquedaPacienteController controller = loader.getController();
            if (controller.pacienteSeleccionado != null) {
                p = controller.pacienteSeleccionado;
            }
        } catch (Exception ex) {
            FxDialogs.showException("Error", "Error al mostrar el formulario", ex);
        }
        return p;
    }

    public Medicina showBusquedaMedicina() {
        Medicina p = null;
        try {
            NotificationPane np = new NotificationPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BuscarMedicina.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            np.setContent(root);
            Scene scene = new Scene(np);
            scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Búsqueda de Medicinas");
            stage.showAndWait();

            BuscarMedicinaController controller = loader.getController();
            if (controller.medicina != null) {
                p = controller.medicina;
            }
        } catch (Exception ex) {
            FxDialogs.showException("Error", "Error al mostrar el formulario", ex);
        }
        return p;
    }

    public FichaPrenatal showBusquedaFicha(String Cedula) {
        FichaPrenatal f = null;
        try {
            NotificationPane np = new NotificationPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BusquedaFicha.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            np.setContent(root);
            Scene scene = new Scene(np);
            scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Búsqueda de Fichas Prenatales");
            BusquedaFichaController controller = loader.getController();
            controller.cargarFichas(Cedula);
            stage.showAndWait();
            f = controller.ficha;

        } catch (Exception ex) {
            FxDialogs.showException("Error", "Error al mostrar el formulario", ex);
        }
        return f;
    }

    public static void limpiarControles(Node node) {

        try {
            if (node instanceof CustomTextField) {
                ((CustomTextField) node).clear();
            } else if (node instanceof PrefixSelectionComboBox) {
                ((PrefixSelectionComboBox) node).getSelectionModel().clearSelection();
            }else if(node instanceof CheckBox){
                ((CheckBox)node).setSelected(false);
            }else if (node instanceof CheckComboBox) {
                ((CheckComboBox) node).getCheckModel().clearChecks();
            }else if (node instanceof Spinner) {
                ((Spinner) node).getValueFactory().setValue(0);
            } else if (node instanceof DatePicker) {
                ((DatePicker) node).setValue(null);
            }  else if (node instanceof TextArea) {
                ((TextArea) node).clear();
            }   else if(node instanceof HBox){
                ((HBox)node).getChildren().forEach(x -> Formularios.limpiarControles(x));
            } else if (node instanceof VBox){
                ((VBox)node).getChildren().forEach(x -> Formularios.limpiarControles(x));
            } else if(node instanceof GridPane){
                ((GridPane)node).getChildren().forEach(x -> Formularios.limpiarControles(x));
            } else if(node instanceof TitledPane){
                Formularios.limpiarControles(((TitledPane)node).getContent());
            }
        } catch (Exception ex) {
            FxDialogs.showException("Error", "Error a limpiar los controles", ex);

        }

    }

    public void showEmpresa() throws IOException {
        NotificationPane np = new NotificationPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Empresa.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        np.setContent(root);
        Scene scene = new Scene(np);
        scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Datos del Hospital");
        stage.showAndWait();
    }

    public void showMedicina() throws IOException {
        NotificationPane np = new NotificationPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Medicina.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        np.setContent(root);
        Scene scene = new Scene(np);
        scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Medicinas");
        stage.showAndWait();
    }

    public void showPrescripcion() throws IOException {
        NotificationPane np = new NotificationPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Prescripcion.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        np.setContent(root);
        Scene scene = new Scene(np);
        scene.getStylesheets().add(getClass().getResource("/css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Prescripcion");
        stage.showAndWait();
    }
}

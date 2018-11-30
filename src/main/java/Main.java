import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilidades.FxDialogs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


            Connection connection = null;

            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost/sgm", "postgres",
                        "postgres");
                connection.close();
            } catch (SQLException e) {
                FxDialogs.showException("Error","No se ha podido conectar a la base de datos",e);

            }

            if(connection!=null) {
                Parent root = FXMLLoader.load(getClass().getResource("fxml/Inicio.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setMaximized(true);
                primaryStage.setScene(scene);
                primaryStage.setTitle("HOSPITAL DEL DIA CALDERON");
                primaryStage.show();
            }else{
                Platform.exit();
            }

    }


}

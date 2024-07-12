package AtmClone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtmApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm.fxml"));


        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.setTitle("ATM Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


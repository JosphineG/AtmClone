package AtmClone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AtmApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getClassLoader().getResource("signUp.fxml");
//        if (fxmlLocation == null) {
//            System.err.println("Could not find signUp.fxml");
//            return;
//        }
//        System.out.println("FXML file located at: " + fxmlLocation);

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.setTitle("ATM Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

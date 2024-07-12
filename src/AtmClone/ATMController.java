package AtmClone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ATMController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label invalidLoginLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView brandingImageView;

    private User authUser;
    private Bank theBank;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            URL brandingFileUrl = getClass().getResource("/images/login.png");
            System.out.println(brandingFileUrl);

            if (brandingFileUrl != null) {
                Image brandingImage = new Image(brandingFileUrl.toString());
                brandingImageView.setImage(brandingImage);
            } else {
                System.out.println("File not found: images/login.png");
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

        theBank = new Bank("ABC");
    }

    @FXML
    public void handleLoginButtonAction() {
        if (validateLogin()) {
            welcomeLabel.setText("Welcome " + authUser.getFirstName());
            invalidLoginLabel.setVisible(false);
            loadDashboard();
        } else {
            showAlert("Login Error", "Invalid Logins. Please Try Again!");
            invalidLoginLabel.setVisible(true);
        }
    }

    private void showAlert(String title, String message) {
        System.out.println(title + ": " + message);
    }

    public void resetLoginForm() {
        usernameTextField.clear();
        enterPasswordField.clear();
        invalidLoginLabel.setVisible(false);
    }

    private boolean validateLogin() {
        MyJDBC connectNow = new MyJDBC();
        Connection connection = connectNow.getConnection();

        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();

        String verifyLogin = "SELECT * FROM users WHERE userName = '" + username + "' AND password ='" + password + "'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(verifyLogin)) {

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String userId = resultSet.getString("userId");
                authUser = new User(firstName, lastName, password, theBank);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setAuthUser(authUser);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package AtmClone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Button signUpButton;

    @FXML
    public void initialize() {
    }

    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String username = userNameTextField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all the details");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String dbPassword = "vero@nicah27";

        String sql = "INSERT INTO users(firstName, lastName, email, password, username) VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, username);
            pstmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "Welcome " + firstName);

            navigateToLoginPage(event);

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error!", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void navigateToLoginPage(ActionEvent event) {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getClassLoader().getResource("../atm.fxml"));
            Scene loginScene = new Scene(loginPage);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to navigate to login page.");
        }
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) {
    }
}

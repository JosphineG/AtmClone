package AtmClone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private User authUser;

    @FXML
    private Button quitButton;
    @FXML
    private Button transferButton;
    @FXML
    private Button viewTransactionHistoryButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button depositButton;
    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
        updateUI();
    }

    private void updateUI() {
        if (authUser != null) {
            welcomeLabel.setText("Welcome, " + authUser.getFirstName() + "!");
        }
    }

    @FXML
    private void handleQuitButtonAction() {
        System.out.println("Quit button pressed");
    }

    @FXML
    private void handleTransferButtonAction() {
        System.out.println("Transfer button pressed");
    }

    @FXML
    private void handleTransactionHistoryButtonAction() {
        System.out.println("View transaction history button pressed");
        loadTransactionHistoryPage();
    }

    @FXML
    private void handleWithdrawButtonAction() {
        System.out.println("Withdraw button pressed");
    }

    @FXML
    private void handleDepositButtonAction() {
        System.out.println("Deposit button pressed");

    }

    private void loadTransactionHistoryPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transactionHistory.fxml"));
            Parent root = loader.load();

            TransHistoryController controller = loader.getController();
            if (controller != null) {
                controller.setAuthUser(authUser);
            } else {
                System.err.println("Failed to load TransHistoryController");
            }

            Stage stage = (Stage) viewTransactionHistoryButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

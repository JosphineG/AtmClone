package AtmClone;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    }

    @FXML
    private void handleWithdrawButtonAction() {
        System.out.println("Withdraw button pressed");
    }

    @FXML
    private void handleDepositButtonAction() {
        System.out.println("Deposit button pressed");
    }
}

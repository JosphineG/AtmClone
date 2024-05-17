package AtmClone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ATMController {

    @FXML
    private TextField userIDField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextArea transactionHistoryArea;

    @FXML
    private TextField amountField;

    @FXML
    private TextField fromAccountField;

    @FXML
    private TextField toAccountField;

    @FXML
    private Pane loginPane;

    @FXML
    private Pane userPane;

    private Bank theBank = new Bank("Kenya Commercial Bank");

    private User authUser;

    @FXML
    public void handleLoginButtonAction() {
        String uuid = userIDField.getText();
        String pin = pinField.getText();
        authUser = theBank.userLogin(uuid, pin);

        if (authUser != null) {
            java.awt.Label welcomeUserLabel = null;
            welcomeUserLabel.setText("Welcome " + authUser.getfirstName());
            loginPane.setVisible(false);
            userPane.setVisible(true);
        } else {
            showAlert("Login Error", "Incorrect user ID/pin. Please try again.");
        }
    }

    @FXML
    private void handleTransactionHistory() {
        int acctIdx = Integer.parseInt(fromAccountField.getText()) - 1;
        transactionHistoryArea.setText(String.valueOf(authUser.getAcctTransHistory(acctIdx)));
    }

    @FXML
    private void handleWithdraw() {
        int acctIdx = Integer.parseInt(fromAccountField.getText()) - 1;
        double amount = Double.parseDouble(amountField.getText());
        String memo = "Withdrawal";

        if (authUser.getAcctBalance(acctIdx) >= amount) {
            authUser.addAcctTransaction(acctIdx, -amount, memo);
            showAlert("Success", "Withdrawal successful.");
        } else {
            showAlert("Error", "Insufficient funds.");
        }
    }

    @FXML
    private void handleDeposit() {
        int acctIdx = Integer.parseInt(toAccountField.getText()) - 1;
        double amount = Double.parseDouble(amountField.getText());
        String memo = "Deposit";

        authUser.addAcctTransaction(acctIdx, amount, memo);
        showAlert("Success", "Deposit successful.");
    }

    @FXML
    private void handleTransfer() {
        int fromAcctIdx = Integer.parseInt(fromAccountField.getText()) - 1;
        int toAcctIdx = Integer.parseInt(toAccountField.getText()) - 1;
        double amount = Double.parseDouble(amountField.getText());

        if (authUser.getAcctBalance(fromAcctIdx) >= amount) {
            authUser.addAcctTransaction(fromAcctIdx, -amount, String.format("Transfer to account %s", authUser.getAcctUUID(toAcctIdx)));
            authUser.addAcctTransaction(toAcctIdx, amount, String.format("Transfer from account %s", authUser.getAcctUUID(fromAcctIdx)));
            showAlert("Success", "Transfer successful.");
        } else {
            showAlert("Error", "Insufficient funds.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    public void handleLoginButtonAction(ActionEvent actionEvent) {
//    }

    public void handleTransactionHistoryButtonAction(ActionEvent actionEvent) {
    }

    public void handleWithdrawButtonAction(ActionEvent actionEvent) {
    }

    public void handleDepositButtonAction(ActionEvent actionEvent) {
    }

    public void handleTransferButtonAction(ActionEvent actionEvent) {
    }

    public void handleQuitButtonAction(ActionEvent actionEvent) {
    }
}

package AtmClone;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class TransHistoryController {

    @FXML
    private AnchorPane transactionPane;

    @FXML
    private RadioButton personalAccountRadio;

    @FXML
    private RadioButton savingsAccountRadio;

    @FXML
    private RadioButton sharedAccountRadio;

    @FXML
    private Label transactionHistoryLabel1;

    @FXML
    private Label transactionHistoryLabel2;

    @FXML
    private Label transactionHistoryLabel3;

    @FXML
    private Label welcomeLabel;

    private User authUser;


    @FXML
    public void initialize() {

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
    private void handleAccountSelection() {
        if (personalAccountRadio.isSelected()) {
            loadTransactionHistory("Personal Account");
        } else if (savingsAccountRadio.isSelected()) {
            loadTransactionHistory("Savings Account");
        } else if (sharedAccountRadio.isSelected()) {
            loadTransactionHistory("Shared Account");
        }
    }

    private void loadTransactionHistory(String accountType) {
        transactionHistoryLabel1.setText("");
        transactionHistoryLabel2.setText("");
        transactionHistoryLabel3.setText("");


        switch (accountType) {
            case "Personal Account":
                transactionHistoryLabel1.setText("Withdrawal\n12/05/2024  02:20PM\nAmount: 13000.00 Kshs");
                transactionHistoryLabel2.setText("Deposit\n12/05/2024  02:20PM\nAmount: 12500.00 Kshs");
                transactionHistoryLabel3.setText("Transfer\n12/05/2024  02:20PM\nAmount: 15000.00 Kshs");
                break;
            case "Savings Account":
                transactionHistoryLabel1.setText("Deposit\n12/05/2024  02:20PM\nAmount: 12500.00 Kshs");
                transactionHistoryLabel2.setText("Withdrawal\n12/05/2024  02:20PM\nAmount: 10000.00 Kshs");
                transactionHistoryLabel3.setText("Transfer\n12/05/2024  02:20PM\nAmount: 8000.00 Kshs");
                break;
            case "Shared Account":
                transactionHistoryLabel1.setText("Transfer\n12/05/2024  02:20PM\nAmount: 5000.00 Kshs");
                transactionHistoryLabel2.setText("Deposit\n12/05/2024  02:20PM\nAmount: 7000.00 Kshs");
                transactionHistoryLabel3.setText("Withdrawal\n12/05/2024  02:20PM\nAmount: 6000.00 Kshs");
                break;
        }
    }
}

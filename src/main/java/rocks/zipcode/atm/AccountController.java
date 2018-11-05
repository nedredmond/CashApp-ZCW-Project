package rocks.zipcode.atm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;

/**
 * Controller class for the second vista.
 */
public class AccountController {

    /**
     * Event handler fired when the user requests a previous vista.
     *
     * @param event the event that triggered the handler.
     */

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private JFXTextArea textArea;

    @FXML
    protected void initialize() {
        textArea.setText(CashMachine.INSTANCE.toString());

        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private Tab tabInfo;

    @FXML
    private Tab tabWithdraw;

    @FXML
    private Tab tabDeposit;

    @FXML
    private TextField amountField;

    @FXML
    private JFXButton amountSubmit;

    @FXML
    void displayInfo(Event event) {
        CashMachine.INSTANCE.toString();
    }

    @FXML
    void transactionTab(Event event) {
        if (amountField.isDisabled()) {
            amountField.setDisable(false);
            amountSubmit.setDisable(false);
        } else {
            amountField.setDisable((true));
            amountSubmit.setDisable(true);
        }
    }

    @FXML
    void submit(Event event) {
        int amount = Integer.parseInt(amountField.getText());

        switch (tabPane.getSelectionModel().getSelectedItem().getText())
        {
            case "Withdraw": CashMachine.INSTANCE.withdraw(amount);
                break;
            case "Deposit": CashMachine.INSTANCE.deposit(amount);
                break;
            default: textArea.setText("Please select a tab.");
        }
        textArea.setText(CashMachine.INSTANCE.toString());
    }

    @FXML
    void logOut(Event event) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CashMachine.INSTANCE.logOut();
        SceneSwitcher.loadVista(SceneSwitcher.VISTA_1);
    }

}
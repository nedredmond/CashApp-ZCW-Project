import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rocks.zipcode.atm.CashMachine;
import rocks.zipcode.atm.bank.Transaction;

import java.util.Date;
import java.util.Observable;
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

        table.setEditable(true);
        typCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("credit"));
        datCol.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
        amtCol.setCellValueFactory(new PropertyValueFactory<Transaction, Long>("amount"));
        balCol.setCellValueFactory(new PropertyValueFactory<Transaction, Long>("balance"));
        table.getColumns().addAll(typCol,datCol,amtCol,balCol);
        table.setItems(data);

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
    private TableView<Transaction> table;

    private final ObservableList<Transaction> data =
            FXCollections.observableArrayList();

    @FXML TableColumn<Transaction, String> typCol;
    @FXML TableColumn<Transaction, Date> datCol;
    @FXML TableColumn<Transaction, Long> amtCol;
    @FXML TableColumn<Transaction, Long> balCol;

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

        String type = "";
        int before = CashMachine.INSTANCE.getBalance();

        switch (tabPane.getSelectionModel().getSelectedItem().getText())
        {
            case "Withdraw": CashMachine.INSTANCE.withdraw(amount);
                type = "Debit";
                break;
            case "Deposit": CashMachine.INSTANCE.deposit(amount);
                type = "Credit";
                break;
            default: textArea.setText("Please select a tab.");
        }
        textArea.setText(CashMachine.INSTANCE.toString());

        if (CashMachine.INSTANCE.getBalance() != before) {
            data.add(0, new Transaction(type, Integer.parseInt(amountField.getText()), CashMachine.INSTANCE.getBalance()));
        }
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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import rocks.zipcode.atm.CashMachine;

/**
 * Controller class for the first vista.
 */
public class LoginController {

//    /**
//     * Event handler fired when the user requests a new vista.
//     *
//     * @param event the event that triggered the handler.
//     */

    @FXML
    protected void initialize() {
        pinEntry.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pinEntry.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private JFXTextField emailEntry;

    @FXML
    private JFXPasswordField pinEntry;

    @FXML
    void logIn(ActionEvent event) {
        String email = emailEntry.getText();
        int pin = Integer.parseInt(pinEntry.getText());

        //add if statement to log in & switch scenes if email and password match
        CashMachine.INSTANCE.login(email,pin);

        SceneSwitcher.loadVista(SceneSwitcher.VISTA_2);
    }

}
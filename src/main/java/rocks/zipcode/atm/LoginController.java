package rocks.zipcode.atm;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller class for the first vista.
 */
public class LoginController {

    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */

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
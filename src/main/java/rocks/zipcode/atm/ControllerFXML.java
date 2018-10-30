package rocks.zipcode.atm;

import com.jfoenix.controls.JFXButton;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rocks.zipcode.atm.bank.Bank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ControllerFXML implements Initializable {

    @FXML
    Stage stage;

    @FXML
    private VBox content;

    @FXML
    private TextField field = new TextField();

    @FXML
    private CashMachine cashMachine = new CashMachine(new Bank());

    @FXML
    public JFXButton btnLogin;

    @FXML void loginAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();

//        try {
//            mainContent = loader.load(getClass().getResource("/AccountScreen.fxml"));
//            System.out.println("tried");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("caught");
//        }
//        Scene accountView = new Scene(mainContent);

        content.getChildren().setAll((Node) loader.load(getClass().getResource("/AccountScreen.fxml")));

//        this.stage.setScene(accountView);
//        this.stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

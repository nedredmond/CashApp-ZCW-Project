package rocks.zipcode.atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
    private Stage window;
    Scene mainContent, loginScene;
    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Zip Cloud Bank");


        //Log In Layout
        Label loginLabel = new Label("Welcome to Zip Cloud Bank!\nPlease log in.");
        Button btnLogIn = new Button("Log In");
        btnLogIn.setStyle("-fx-font-size: 15pt");
        btnLogIn.setOnAction(e -> {
            mainContent = new Scene(mainContent());
            window.setScene(mainContent);
        });

        GridPane loginLayout = new GridPane();
        loginLayout.setHgap(12);
        loginLayout.setVgap(12);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.getChildren().addAll(loginLabel, field, btnLogIn);

        loginScene = new Scene(loginLayout,600, 600);
        window.setScene(loginScene);


        window.show();
    }

    private Parent mainContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Log Out");
        btnExit.setOnAction(e -> {
            cashMachine.logOut();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

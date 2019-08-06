package atmsimulatorsystem.assistant.ui.ApplicationFormPage3;

import atmsimulatorsystem.assistant.ui.ApplicationFormPage1.ApplicationFormPage1Controller;
import atmsimulatorsystem.assistant.ui.Database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class ApplicationFormPage3Controller implements Initializable {
    
    DatabaseHandler databasehandler;
    
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblCardNumber;
    @FXML
    private Label lblPinNumber;
    @FXML
    private CheckBox checkBoxAtmCard;
    @FXML
    private CheckBox checkBoxInternetBanking;
    @FXML
    private CheckBox checkBoxMobileBanking;
    @FXML
    private CheckBox checkBoxEmailAlerts;
    @FXML
    private CheckBox checkBoxAChequeBook;
    @FXML
    private CheckBox checkBoxEStatement;
    @FXML
    private CheckBox checkBoxDeclaration;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       databasehandler = new DatabaseHandler();
       checkData();
    }    

    @FXML
    private void saveButtonAction(ActionEvent event) {
        String serviceRequired = checkBoxAtmCard.getText();
        Random r = new Random();
        String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        String pinNumber = String.format("%06d", (Object) Integer.valueOf(r.nextInt(100001)));
        //System.out.println(randomNumber);
        if (serviceRequired.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;

        }
        
        String qu = "INSERT INTO PERSONALDETAILS3(randomNumber,pinNumber,service_required) VALUES ("
                + "'" + randomNumber + "',"
                + "'" + pinNumber + "',"
                + "'" + serviceRequired + "'"
                + ")";
        System.out.println(qu);
        
        if (databasehandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failure");
            alert.showAndWait();
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
    }
    private void checkData() {
        String qu = "SELECT * FROM PERSONALDETAILS3";
        ResultSet rs = databasehandler.execQuery(qu);
        try {
            while (rs.next()) {
                String titlex = rs.getString("Id");
                System.out.println(titlex);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationFormPage1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}

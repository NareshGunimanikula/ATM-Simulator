/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmsimulatorsystem.assistant.ui.ApplicationFormPage2;

import atmsimulatorsystem.assistant.ui.ApplicationFormPage1.ApplicationFormPage1;
import atmsimulatorsystem.assistant.ui.ApplicationFormPage1.ApplicationFormPage1Controller;
import atmsimulatorsystem.assistant.ui.Database.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ApplicationFormPage2Controller implements Initializable {

    DatabaseHandler databasehandler;
    
    @FXML
    private Font x1;
    @FXML
    private ChoiceBox<?> comboBoxStatus;
    @FXML
    private TextField txtSinNumber;
    @FXML
    private ChoiceBox<?> comboBoxReligion;
    @FXML
    private ChoiceBox<?> comboBoxIncome;
    @FXML
    private ChoiceBox<?> comboBoxEducation;
    @FXML
    private ChoiceBox<?> comboBoxOccupation;
    @FXML
    private RadioButton radioExistActYes;
    @FXML
    private RadioButton radioExistActNo;
    @FXML
    private RadioButton radioSavAct;
    @FXML
    private RadioButton radioCurAct;
    @FXML
    private RadioButton radioChqAct;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        databasehandler = new DatabaseHandler();
        checkData();
    }    

    @FXML
    private void saveButtonAction(ActionEvent event) {
        int id = 1;
        String religion = comboBoxReligion.getSelectionModel().toString();
        String income = comboBoxIncome.getSelectionModel().toString();
        String education = comboBoxEducation.getSelectionModel().toString();
        String occupation = comboBoxOccupation.getSelectionModel().toString();
        String sinNumber = txtSinNumber.getText();
        String status = comboBoxStatus.getSelectionModel().toString();
        Boolean existingAccYes =  radioExistActYes.isSelected();
        Boolean existingAccNo = radioExistActNo.isSelected();
        Boolean savingAcc = radioSavAct.isSelected();
        Boolean currentAcc = radioCurAct.isSelected();
        Boolean chequingAcc = radioChqAct.isSelected();
        String existingAcc = "yes";
        String accountType= "saving account";
        //char gender = (male.toString() || female.toString()) ? male:female;
        if (sinNumber.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;

        }

        String qu = "INSERT INTO PERSONALDETAILS2(religion,income,education_qualification,occupation,sin_number,status,existing_account,account_type) VALUES ("
                + "'" + religion + "',"
                + "'" + income + "',"
                + "'" + education + "',"
                + "'" + occupation + "',"
                + "'" + sinNumber + "',"
                + "'" + status + "',"
                + "'" + existingAcc + "',"
                + "'" + accountType + "'"
                + ")";
        System.out.println(qu);
        
        if (databasehandler.execAction(qu)) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Success");
//            alert.showAndWait();
loadWindow("/atmsimulatorsystem/assistant/ui/ApplicationFormPage3/ApplicationFormPage3.fxml", "Personal Details 3");
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
        String qu = "SELECT * FROM PERSONALDETAILS2";
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
    void loadWindow(String location, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationFormPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package atmsimulatorsystem.assistant.ui.ApplicationFormPage1;

import atmsimulatorsystem.assistant.ui.Database.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApplicationFormPage1Controller implements Initializable {
    
    ObservableList list = FXCollections.observableArrayList();
    DatabaseHandler databasehandler;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMiddleName;
    @FXML
    private TextField txtFatherName;
    @FXML
    private Font x1;
    @FXML
    private RadioButton radioGenderMale;
    @FXML
    private RadioButton radioGenderFemale;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPinCode;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBack;
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<String> comboMarital;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databasehandler = new DatabaseHandler();
        checkData();
        //loadData();
    }
    
    @FXML
    private void saveButtonAction(ActionEvent event) {
        String fName = txtFirstName.getText();
        String mName = txtLastName.getText();
        String lName = txtMiddleName.getText();
        String faName = txtFatherName.getText();
        Boolean male = radioGenderMale.isSelected();
        Boolean female = radioGenderFemale.isSelected();
        String maritalStatus =  comboMarital.getValue();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String pinCode = txtPinCode.getText();
        String state = txtState.getText();
        String emailAaddress = txtEmailAddress.getText();
        char gender = 'M';
        if (fName.isEmpty() || emailAaddress.isEmpty() || lName.isEmpty()
                || faName.isEmpty() || address.isEmpty() || city.isEmpty()
                || pinCode.isEmpty() || state.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }
        
        String qu = "INSERT INTO PERSONALDETAILS1(first_name,middle_name,last_name,"
                + "father_name,gender,email_address,marital_status,address,"
                + "city,pin_code,state) VALUES ("
                + "'" + fName + "',"
                + "'" + mName + "',"
                + "'" + lName + "',"
                + "'" + faName + "',"
                + "'" + gender + "',"
                + "'" + emailAaddress + "',"
                + "'" + maritalStatus + "',"
                + "'" + address + "',"
                + "'" + city + "',"
                + "'" + pinCode + "',"
                + "'" + state + "'"
                + ")";
        System.out.println(qu);
        
        if (databasehandler.execAction(qu)) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Success");
//            alert.showAndWait();
            loadWindow("/atmsimulatorsystem/assistant/ui/ApplicationFormPage2/ApplicationFormPage2.fxml", "Personal Details 2");
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
//    private void loadData(){
//        list.removeAll(list);
//        String a = "Married";
//        String b = "UnMarried";
//        String c = "Single";
//        String d = "Others";
//        list.addAll(a,b,c,d);
//        comboMarital.getItems().addAll(list);
//    }
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


package medicaldoctor.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import medicaldoctor.core.AppSession;

public class PatientRegistrationController implements Initializable, ParentController{
    
    @FXML
    TitledPane newPatientTiltedPane, returningPatientTiltedPane;
    
    @FXML
    TextField textFirstName, textLastName, ageNumber, textSex;
    
    @FXML
    TextField textMedicalInsurance, textBirthdate, textPrimaryDoctory;
    
    @FXML
    TextField textStreetMailingAddress, textCityMailingAddress;
    
    @FXML
    ChoiceBox stateMailingAddressChoiceBox, stateBillingAddressChoiceBox;
    
    @FXML
    TextField zipCodeMailingAddressNumber, textStreetBillingAddress, textCityBillingAddress, zipCodeBillingAddressNumber, textDoctor, textVisitDate;
    
    @FXML
    TextArea textAreaChiefComplaint, textAreaPresentIllness;
    
    @FXML
    void submitButtonClick(ActionEvent event){
        
    }
    
    @FXML
    void selectButtonClick(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        for(String i : LookUp.STATES){
            stateMailingAddressChoiceBox.getItems().add(i);
            stateBillingAddressChoiceBox.getItems().add(i);
        }
    }

    @Override
    public void setScreenParent(ControllerManager page){
        AppSession.CONTROLLER_MANAGER = page;
    }
    
}
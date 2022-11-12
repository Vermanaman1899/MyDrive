package Controllers;

import Request.SignupRequest;
import Response.SignupResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.*;

public class signup{
    @FXML
    public Button Signup;

    @FXML
    public TextField SignupName;

    @FXML
    public TextField SignupEmailID;

    @FXML
    public TextField SignupUsername;

    @FXML
    public PasswordField SignupPassword;

    @FXML
    public PasswordField ConfirmSignupPassword;
    @FXML
    public TextField SignupPhone;


    public void OnClickSignup(ActionEvent actionEvent) {
        if (SignupPassword.getText().equals(ConfirmSignupPassword.getText())) {
            SignupRequest registerRequest = new SignupRequest(SignupName.getText(), SignupEmailID.getText(), SignupUsername.getText(), SignupPassword.getText(),SignupPhone.getText());
            Main.sendRequest(registerRequest);
            System.out.println("Register request sent");
            SignupResponse response = (SignupResponse) Main.getResponse();
            assert response != null;
            if (response.getMessage().length() == 0) {
                System.out.println("Please Try Again");
            } else {
                System.out.println("Signup success!");
            }
        } else {
            System.out.println("Please enter correct info");
        }
    }

}

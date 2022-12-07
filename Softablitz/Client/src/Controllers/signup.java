package Controllers;

import Request.SignupRequest;
import Response.SignupResponse;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import Services.*;

public class signup {
    @FXML
    public Button Signup;
    @FXML
    public Button BackToLoginPage;

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

    Connection connection;
    PreparedStatement pst;

    public void OnClickCreateAccount(ActionEvent actionEvent) {

//        int access=0;
//        New comment

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
                Loader loader = new Loader("../Views/home page.fxml", Signup, "Home Page");

            }
        } else {
            System.out.println("Please enter correct info");
        }

    }
    public void onClickBackToLoginPage() {
        Loader loader = new Loader("../Views/login.fxml", BackToLoginPage, "Home Page");
    }
}



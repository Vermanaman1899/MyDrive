package Controllers;

import Request.LoginRequest;
import Response.LoginResponse;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


//import main.Main;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class login{
    static String loginEmailId = "";
    @FXML
    public Button Login;

    @FXML
    public TextField LoginEmailID;

    @FXML
    public PasswordField LoginPassword;

    @FXML
    public Button GotoSignup;


//    String loginEmail = LoginEmailID.getText();
//    String loginPassword = LoginPassword.getText();

    public void OnClickLogin(ActionEvent actionEvent) {
        try{
        LoginRequest request = new LoginRequest(LoginEmailID.getText(), LoginPassword.getText());
        main.Main.sendRequest(request);
        LoginResponse response = (LoginResponse) main.Main.getResponse();

        Alert alert;
        if (response == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Incorrect information. Please try again.");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION, "Login successful.");
            loginEmailId = LoginEmailID.getText();
            Loader loader = new Loader("../Views/home page.fxml", Login, "Home Page");
        }
        alert.showAndWait();
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SwitchToSignup(ActionEvent actionEvent) {
        Loader loader = new Loader("../Views/signup.fxml", GotoSignup, "Sign Up");
    }
}

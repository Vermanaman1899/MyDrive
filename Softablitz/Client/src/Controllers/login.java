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

import java.io.IOException;

public class login{
    @FXML
    public Button Login;

    @FXML
    public TextField LoginUsername;

    @FXML
    public PasswordField LoginPassword;

    @FXML
    public Button GotoSignup;

    public void OnClickLogin(ActionEvent actionEvent) {
        LoginRequest request = new LoginRequest(LoginUsername.getText(), LoginPassword.getText());
        Main.sendRequest(request);
        LoginResponse response = (LoginResponse) Main.getResponse();
        Alert alert;
        if (response == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Incorrect information. Please try again.");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION, "Login successful.");
        }
        alert.showAndWait();
    }

    public void SwitchToSignup(ActionEvent actionEvent) {
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("../Views/signup.fxml"));
        Scene scene = null;
        Stage stage = (Stage) GotoSignup.getScene().getWindow();
        try {
            scene = new Scene(registerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Sign Up");
    }
}

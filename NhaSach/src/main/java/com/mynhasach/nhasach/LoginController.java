package com.mynhasach.nhasach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUserName;
    @FXML private TextField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Hyperlink hlinkForgotPass;
    @FXML private Hyperlink hLinkSignUp;

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void SwitchToRegister(){

        try {
            App.setRoot("register");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

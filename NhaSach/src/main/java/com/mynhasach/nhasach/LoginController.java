package com.mynhasach.nhasach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mynhasach.service.LoginService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class LoginController {

    @FXML private TextField txtUserName;
    @FXML private TextField txtPassword;
    @FXML private Button btnLogin;
    @FXML private VBox vbLogin;
    @FXML private Label tAlertUser;
    @FXML private Label tAlertPass;


    public void login(){

        this.btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkLogin(actionEvent);
            }
        });

    }


    public void checkLogin( ActionEvent actionEvent){
        if(txtUserName.getText().isEmpty()) {
            tAlertUser.setVisible(true);
            return;
        }

        if(txtPassword.getText().isEmpty()) {
            tAlertPass.setVisible(true);
            return;
        }
        try {
            String username = txtUserName.getText().toString();
            String userPass = txtPassword.getText().toString();

            Util ut = new Util();
            userPass = ut.md5(userPass);

            Connection conn = null;
            LoginService loginsv = new LoginService(conn);
            String password = loginsv.getPasswordByUsername(username);

            if (userPass.equals(password)){
                App.setUser(loginsv.getLoginByUsername(username));
                App.setIsAuthenticate(true);
                SwitchToManager(actionEvent);
            }else if(!userPass.equals(password)){
                tAlertPass.setText("password is incorrect");
                tAlertPass.setVisible(true);
            }

        } catch (SQLException | IOException throwables) {
            tAlertPass.setText("Username is invalid !!!");
            tAlertPass.setVisible(true);
        }
    }

    public void textUserChange(){
        tAlertUser.setText("Please enter your user name !!!");
        tAlertUser.setVisible(false);
    }

    public void textPassChange(){
        tAlertPass.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() != KeyCode.ENTER){
                tAlertPass.setText("Please enter a password !!!");
                tAlertPass.setVisible(false);
            }
        });
    }

    public void SwitchToRegister(){
        try {
            App.setRoot("register");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SwitchToManager(ActionEvent event) throws IOException {
        try {
            App.setRoot("manager");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

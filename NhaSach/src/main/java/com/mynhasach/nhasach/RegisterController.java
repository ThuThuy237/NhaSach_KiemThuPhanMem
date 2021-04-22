package com.mynhasach.nhasach;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mynhasach.pojo.Login;
import com.mynhasach.service.LoginService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class RegisterController {
    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private PasswordField confirm;
    @FXML private PasswordField password;
    @FXML private Label err;
    @FXML private Window window;
    @FXML private Button regis;

    public void register(){
        regis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                submit();
            }
        });

    }

    public void submit(){
        if (!checkMail(email.getText()))
            err.setText("Not correct format for email address !!!");
        else if (checkPass() && checkFill() && checkName(username.getText())){
            Login login = new Login();
            Util util = new Util();

            login.setUsername(username.getText());
            login.setPassword(util.md5(password.getText()));
            login.setEmail(email.getText());

            try {
                LoginService loginService = new LoginService();
                if (loginService.addLogin(login)){
                    util.showAlert(Alert.AlertType.INFORMATION, window,"Register","Successful", 2900);
                    this.switchToLogin();
                }
            } catch (SQLException throwables) {
                err.setText("Fail !!! Please try again.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean checkMail(String mail){
        String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pt = Pattern.compile(regex);
        Matcher matcher = pt.matcher(mail);
        if (matcher.find()){
            return true;
        }
        return false;
    }

    public Boolean checkFill(){
        if(username.getText().isEmpty()|| email.getText().isEmpty() ||
        password.getText().isEmpty() || confirm.getText().isEmpty()){
            err.setText("required (*)");
            return false;
        }
        return true;
    }

    public Boolean checkPass(){
        String pass = password.getText().toString();
        String conf = confirm.getText().toString();

        if (!pass.equals(conf)&& !(password.getText().isEmpty() || confirm.getText().isEmpty())){
            err.setText("Password does not match !!!");
            return false;
        }
        return true;
    }

    public Boolean checkName(String name){
        try {
            LoginService loginService = new LoginService();
            if (loginService.findUsername(name)){
                this.err.setText("This username is already taken !!!");
                return false;
            }
            return true;
        } catch (SQLException throwables) {
            return true;
        }
    }

    public void switchToLogin(){
        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
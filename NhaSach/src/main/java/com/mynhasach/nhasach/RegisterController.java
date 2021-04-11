package com.mynhasach.nhasach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

public class RegisterController {

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void switchToLogin(){

        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    @FXML
//    private void switchToPrimary() throws IOException {
//        App.setRoot("login");
//    }
}
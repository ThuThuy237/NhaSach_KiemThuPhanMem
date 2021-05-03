package com.mynhasach.nhasach;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    @FXML private Label lbusername;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.lbusername.setText(App.getUser().getUsername());
    }

}

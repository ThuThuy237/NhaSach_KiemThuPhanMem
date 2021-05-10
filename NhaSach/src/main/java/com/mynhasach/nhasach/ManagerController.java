package com.mynhasach.nhasach;


import com.mynhasach.pojo.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    @FXML private Label lbusername;
    @FXML private ImageView imageView;
    @FXML private Button btnNhapSach, btnBanSach, btnCustomer,btnReceipt, btnQuanLy, btnLogOut, btnBookManage;
    @FXML private AnchorPane containerPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.lbusername.setText(App.getUser().getUsername());


        btnBanSach.setOnMouseClicked(e->{
            try {
                LoadPane("SellBook.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnNhapSach.setOnMouseClicked(e->{
            try {
                LoadPane("BuyBook.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnCustomer.setOnMouseClicked(e->{
            try {
                LoadPane("ManageCustomer.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnReceipt.setOnMouseClicked(e->{
            try {
                LoadPane("ManageCategory.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnBookManage.setOnMouseClicked(e->{
            try {
                LoadPane("ManageListBook.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnQuanLy.setOnMouseClicked(e->{
            try {
                LoadPane("ManageAccount.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnLogOut.setOnMouseClicked(e->{
            logout();
        });
    }

    public void logout(){
        try {
            App.setIsAuthenticate(false);
            App.setUser(new Login());
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadPane(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        containerPane.getChildren().removeAll();
        containerPane.getChildren().setAll(pane);
    }




}

package com.mynhasach.nhasach;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Login;
import com.mynhasach.service.CategoryService;
import com.mynhasach.service.LoginService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageAccount implements Initializable {
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField PassWord;
    @FXML
    private TextField Email;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<Login> tableAccount;
    @FXML
    private TableColumn<Login,String> colUserName;
    @FXML
    private TableColumn<Login,String> colPass;
    @FXML
    private TableColumn<Login, String> colEmail;
    @FXML
    private TableColumn<Login, String> colRole;
    @FXML
    private Window window;
    private Login account;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadListAccount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(checkEmpty() & checkEmail(Email.getText())){
                    try {
                        Util ut = new Util();

                        account = new Login(tfUserName.getText(),ut.md5(PassWord.getText()),Email.getText(),"");
                        LoginService loginService = new LoginService();
                       if (checkName(tfUserName.getText())){
                           if (loginService.addLogin(account)){
                               try {
                                   new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Add new Account success!!!", 1000);
                                   loadListAccount();
                                   resetForm();
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }else {
                               try {
                                   new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","add fail!!!", 1000);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }
                           loadListAccount();
                       }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        new Util().showAlert(Alert.AlertType.INFORMATION, window,"!!!","Please complete information!", 2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tableAccount.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select row you want to delete", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        LoginService service = new LoginService();
                        if(service.deleteLogin(tableAccount.getSelectionModel().getSelectedItem().getId()) ){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Delete success!", 0);
                                loadListAccount();
                                resetForm();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Fail !!!", 0);
                                loadListAccount();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tableAccount.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select row you want to update", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {

                    if(checkEmpty()){

                        try {
                            Util ut = new Util();
                            account = new Login(tableAccount.getSelectionModel().getSelectedItem().getId()
                                    , tfUserName.getText(),ut.md5(PassWord.getText()),Email.getText(),"");
                            LoginService loginService = new LoginService();
                            if (loginService.updateLogin(account)){
                                try {
                                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Update account success!!!", 1000);
                                    loadListAccount();
                                    resetForm();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                try {
                                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","update fail!!!", 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            loadListAccount();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            new Util().showAlert(Alert.AlertType.INFORMATION, window,"Error","Please complete information!", 2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
        this.tableAccount.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(e -> {
                tfUserName.setText(tableAccount.getSelectionModel().getSelectedItem().getUsername());
                PassWord.setPromptText("input Pass !!!");
                Email.setText(String.valueOf(tableAccount.getSelectionModel().getSelectedItem().getEmail()));
            });
            return r;
        });

    }

    private void resetForm() {
        tfUserName.setText("");
        PassWord.setText("");
        Email.setText("");
    }

    private boolean checkEmail(String mail) {
        String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pt = Pattern.compile(regex);
        Matcher matcher = pt.matcher(mail);
        if (matcher.find()){
            return true;
        }
        try {
            new Util().showAlert(Alert.AlertType.INFORMATION, window,"Error","Email doesn't match format!", 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkEmpty() {
        if (!tfUserName.getText().isEmpty() & !PassWord.getText().isEmpty() & !Email.getText().isEmpty()){
            return true;
        }
        return false;
    }

    private void loadListAccount() throws SQLException, ParseException {
        colUserName.setCellValueFactory(new PropertyValueFactory<Login,String>("username"));
        colPass.setCellValueFactory(new PropertyValueFactory<Login,String>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Login,String>("email"));
        LoginService loginService = new LoginService();
        tableAccount.setItems(FXCollections.observableList(loginService.getLogins()));

    }

    public Boolean checkName(String name){
        try {
            LoginService loginService = new LoginService();
            if (loginService.findUsername(name)){
                try {
                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"Error","This username is already taken !!!", 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }
            return true;
        } catch (SQLException throwables) {
            return true;
        }
    }

}

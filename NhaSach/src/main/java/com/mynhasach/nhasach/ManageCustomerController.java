package com.mynhasach.nhasach;

import com.mynhasach.pojo.Book;
import com.mynhasach.pojo.BuyDetail;
import com.mynhasach.pojo.Customer;
import com.mynhasach.service.BookService;
import com.mynhasach.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPhone;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<Customer> tableCustomer;
    @FXML
    private TableColumn<Customer,String> colName;
    @FXML
    private TableColumn<Customer,String> colGender;
    @FXML
    private TableColumn<Customer, Date> colDateofBirth;
    @FXML
    private TableColumn<Customer,String> colAddress;
    @FXML
    private TableColumn<Customer,String> colPhone;
    private Customer customer;
    @FXML
    private Window window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadListCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObservableList<String> list = FXCollections.observableArrayList("Male","Female");
        cbGender.setItems(list);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(checkEmpty()){

                    try {
                        customer = new Customer(tfFullName.getText(),cbGender.getValue(), datePicker.getValue(),
                                tfAddress.getText(), tfPhone.getText());
                        CustomerService customerService = new CustomerService();
                        if (customerService.addCustomer(customer)){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Add new Customer success!!!", 1000);
                                loadListCustomer();
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
                        loadListCustomer();
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
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tableCustomer.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select the customer you want to delete", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        CustomerService customerService = new CustomerService();
                        if(customerService.deleteCustomer(tableCustomer.getSelectionModel().getSelectedItem().getId()) ){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Delete success!", 0);
                                loadListCustomer();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Fail !!!", 0);
                                loadListCustomer();
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
                if(tableCustomer.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select the customer you want to update", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {

                    if(checkEmpty()){

                        try {
                            customer = new Customer(tableCustomer.getSelectionModel().getSelectedItem().getId()
                                    ,tfFullName.getText(),cbGender.getValue(), datePicker.getValue(),
                                    tfAddress.getText(), tfPhone.getText());
                            CustomerService customerService = new CustomerService();
                            if (customerService.updateCustomer(customer)){
                                try {
                                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Update Customer success!!!", 1000);
                                    loadListCustomer();
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
                            loadListCustomer();
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
        this.tableCustomer.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(e -> {
                tfFullName.setText(tableCustomer.getSelectionModel().getSelectedItem().getName());
                cbGender.setValue(tableCustomer.getSelectionModel().getSelectedItem().getGender());
                datePicker.setValue(tableCustomer.getSelectionModel().getSelectedItem().getBirthday());
                tfAddress.setText(tableCustomer.getSelectionModel().getSelectedItem().getAddress());
                tfPhone.setText(tableCustomer.getSelectionModel().getSelectedItem().getPhone());
            });
            return r;
        });

    }

    private boolean checkEmpty() {
        if (!tfFullName.getText().isEmpty() & !tfAddress.getText().isEmpty() &
                !cbGender.getValue().isEmpty() & datePicker.getValue() != null
        & !tfPhone.getText().isEmpty()){
            return true;
        }
        return false;
    }

    private void loadListCustomer() throws SQLException, ParseException {
        colName.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<Customer,String>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
        colDateofBirth.setCellValueFactory(new PropertyValueFactory<Customer,Date>("birthday"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("phone"));
        CustomerService customerService = new CustomerService();
        tableCustomer.setItems(FXCollections.observableList(customerService.getCustomers()));

    }
}

package com.mynhasach.nhasach;

import com.mynhasach.pojo.*;
import com.mynhasach.service.*;
import javafx.collections.FXCollections;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellBookController implements Initializable {
    @FXML
    private ComboBox<Customer> customer;
    @FXML
    private ComboBox<Book> book;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private TableView<Book> tableBook = new TableView<Book>();
    @FXML
    private TableColumn<Book,String> nameColumn;
    @FXML
    private TableView<OrderDetail> tableSell;
    @FXML
    private TableColumn<OrderDetail,Integer> nameOfBookSell;
    @FXML
    private TableColumn<OrderDetail,Integer> numOfBookSell;
    @FXML
    private TableColumn<OrderDetail, BigDecimal> totalPrice;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button addReceipt;
    private List<OrderDetail> orderDetails;
    private Order order;
    @FXML private Window window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetails = new ArrayList<>();
        try {
            loadListBook(0);
            setDataComboBox();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }

        category.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    try {
                        loadListBook(newValue.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        );

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(checkEmpty()){
                    Double tong = Double.parseDouble(price.getText()) * Double.parseDouble(quantity.getText());
                    orderDetails.add(new OrderDetail(book.getValue().getId(),
                            Integer.parseInt(quantity.getText()), BigDecimal.valueOf(tong)));
                    loadDataSell();
                }else {
                    try {
                        new Util().showAlert(Alert.AlertType.INFORMATION, window,"add book to receipt","Please complete information!", 2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tableSell.getSelectionModel().isEmpty()){

                }else {
                    orderDetails.remove(tableSell.getSelectionModel().getSelectedIndex());
                    loadDataSell();

                }
            }
        });

        addReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    if (customer.getValue()!=null){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        int total = 0;
                        for (OrderDetail od: orderDetails
                        ) {
                            Double db = Double.parseDouble(String.valueOf(od.getPrice())) * od.getQuantity();
                            total += db;
                        }
                        order = new Order(now,BigDecimal.valueOf(total), customer.getValue().getId(), App.getUser().getId());

                        OrderService orderService = new OrderService();
                        OrderDetailService orderDetailService = new OrderDetailService();
                        Boolean commitOrder = false;
                        if(!tableSell.getItems().isEmpty()){
                            commitOrder = orderService.addOrder(order);
                        }
                        Boolean commitDetail = true;
                        for (OrderDetail o: orderDetails
                             ) {
                            o.setOrderId(orderService.getLastId());
                            commitDetail = orderDetailService.addOrderDetail(o);
                        }
                        if (commitOrder & commitDetail){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Sell book success!!!", 1000);
                                orderDetails.clear();
                                loadDataSell();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","no book in receipt!!!", 2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }else {
                        try {
                            new Util().showAlert(Alert.AlertType.INFORMATION, window,"add book to receipt","Please complete information!", 2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

//        customer.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
//            this.order.setCusId(newValue.getId());
//        });

        tableBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                book.setValue(newSelection);
                book.setValue(newSelection);
            }
        });

        book.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    price.setText(newValue.getPrice().toString());

                }
        );
    }

    private void loadDataSell() {
        nameOfBookSell.setCellValueFactory(new PropertyValueFactory<OrderDetail,Integer>("bookId"));
        numOfBookSell.setCellValueFactory(new PropertyValueFactory<OrderDetail,Integer>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<OrderDetail,BigDecimal>("price"));
        tableSell.setItems(FXCollections.observableList(orderDetails));
    }

    private boolean checkEmpty() {
        if (!book.getId().isEmpty() &
                !quantity.getText().isEmpty()){
            return true;
        }
        return false;
    }


    private void setDataComboBox() throws SQLException, ParseException {
        CategoryService categoryService = new CategoryService();
        category.setItems(FXCollections.observableList(categoryService.getCates("")));

        BookService bookService = new BookService();
        book.setItems(FXCollections.observableList(bookService.getBooks()));

        CustomerService customerService = new CustomerService();
        customer.setItems(FXCollections.observableList(customerService.getCustomers()));
    }

    private void loadListBook(int catID) throws SQLException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        BookService bookService = new BookService();
        if (catID!=0){
            tableBook.setItems(FXCollections.observableList(bookService.getBooksByCate(catID) ));
        }else {
            tableBook.setItems(FXCollections.observableList(bookService.getBooks() ));
        }

    }

}

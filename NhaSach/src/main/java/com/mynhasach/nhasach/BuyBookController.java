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

public class BuyBookController implements Initializable {
    @FXML
    private ComboBox<Supplier> supplier;
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
    private TableView<BuyDetail> tableImport;
    @FXML
    private TableColumn<BuyDetail,Integer> nameOfBookImport;
    @FXML
    private TableColumn<BuyDetail,Integer> numOfBookImport;
    @FXML
    private TableColumn<BuyDetail, BigDecimal> totalPrice;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button addReceipt;
    private List<BuyDetail> buyDetails;
    private Buy buy;
    @FXML private Window window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buyDetails = new ArrayList<>();
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
                    buyDetails.add(new BuyDetail(book.getValue().getId(),
                            Integer.parseInt(quantity.getText()), BigDecimal.valueOf(tong)));
                    loadDataImport();
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
                if(tableImport.getSelectionModel().isEmpty()){

                }else {
                    buyDetails.remove(tableImport.getSelectionModel().getSelectedIndex());
                    loadDataImport();

                }
            }
        });

        addReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    if (supplier.getValue()!=null){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        int total = 0;
                        for (BuyDetail od: buyDetails
                        ) {
                            Double db = Double.parseDouble(String.valueOf(od.getPrice())) * od.getQuantity();
                            total += db;
                        }
                        buy = new Buy(now, BigDecimal.valueOf(total), supplier.getValue().getId(), App.getUser().getId());

                        BuyService buyService = new BuyService();
                        BuyDetailService buyDetailService = new BuyDetailService();
                        Boolean commitBuy = false;
                        if(!tableImport.getItems().isEmpty()){
                            commitBuy = buyService.addBuy(buy);
                        }
                        Boolean commitDetail = true;
                        for (BuyDetail o: buyDetails
                        ) {
                            o.setBuyId(buyService.getLastId());
                            commitDetail = buyDetailService.addBuyDetail(o);
                        }
                        if (commitBuy & commitDetail){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Sell book success!!!", 1000);
                                buyDetails.clear();
                                loadDataImport();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","no book in receipt!!!", 1000);
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

    private void loadDataImport() {
        nameOfBookImport.setCellValueFactory(new PropertyValueFactory<BuyDetail,Integer>("bookId"));
        numOfBookImport.setCellValueFactory(new PropertyValueFactory<BuyDetail,Integer>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<BuyDetail,BigDecimal>("price"));
        tableImport.setItems(FXCollections.observableList(buyDetails));
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

        SupplierService supplierService = new SupplierService();
        supplier.setItems(FXCollections.observableList(supplierService.getSuppliers()));
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

package com.mynhasach.nhasach;

import com.mynhasach.pojo.Book;
import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Customer;
import com.mynhasach.service.BookService;
import com.mynhasach.service.CategoryService;
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

public class ManageListbookController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfInventory;
    @FXML
    private TextField tfImportPrice;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<Book> tableBook;
    @FXML
    private TableColumn<Book,String> colName;
    @FXML
    private TableColumn<Book,String> colAuthor;
    @FXML
    private TableColumn<Book, Integer> colInventory;
    @FXML
    private TableColumn<Book,BigDecimal> colImportPrice;
    @FXML
    private TableColumn<Book, BigDecimal> colPrice;
    private Book book;
    @FXML
    private Window window;
    private BigDecimal Price = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadListBook();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CategoryService categoryService = null;
        try {
            categoryService = new CategoryService();
            category.setItems(FXCollections.observableList(categoryService.getCates("")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tfImportPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            cal();
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(checkEmpty()){
                    try {
                        book = new Book(tfName.getText(),tfAuthor.getText(), Integer.parseInt(tfInventory.getText()),
                                BigDecimal.valueOf(Double.parseDouble(tfImportPrice.getText())),
                               Price,"",category.getSelectionModel().getSelectedItem().getId());
                        BookService bookService = new BookService();
                        if (bookService.addBook(book)){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Add new Book success!!!", 1000);
                                loadListBook();
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
                        loadListBook();
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
                if(tableBook.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select the book you want to delete", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        BookService bookService = new BookService();
                        if(bookService.deleteBook(tableBook.getSelectionModel().getSelectedItem().getId()) ){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Delete success!", 0);
                                loadListBook();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Fail !!!", 0);
                                loadListBook();
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
                if(tableBook.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select book you want to update", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {

                    if(checkEmpty()){

                        try {
                            book = new Book(tableBook.getSelectionModel().getSelectedItem().getId()
                                    , tfName.getText(),tfAuthor.getText(), Integer.parseInt(tfInventory.getText()),
                                    BigDecimal.valueOf(Double.parseDouble(tfImportPrice.getText())),
                                    Price,"",category.getSelectionModel().getSelectedItem().getId());
                            BookService bookServicee = new BookService();
                            if (bookServicee.updateBook(book)){
                                try {
                                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Update Book success!!!", 1000);
                                    loadListBook();
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
                            loadListBook();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            new Util().showAlert(Alert.AlertType.INFORMATION, window,"add book to receipt","Please complete information!", 2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
        CategoryService finalCategoryService = categoryService;
        this.tableBook.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(e -> {
                tfName.setText(tableBook.getSelectionModel().getSelectedItem().getName());
                tfAuthor.setText(tableBook.getSelectionModel().getSelectedItem().getAuthor());
                tfImportPrice.setText(tableBook.getSelectionModel().getSelectedItem().getImportPrice().toString());
                tfInventory.setText(String.valueOf(tableBook.getSelectionModel().getSelectedItem().getInventory()));
                try {
                    category.setValue(finalCategoryService.getCateById(
                            tableBook.getSelectionModel().getSelectedItem().getCategoryId()
                    ));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            return r;
        });

    }

    private boolean checkEmpty() {
        if (!tfName.getText().isEmpty() & !tfAuthor.getText().isEmpty() &
                !category.getSelectionModel().isEmpty() & !tfInventory.getText().isEmpty()
                & !tfImportPrice.getText().isEmpty()){
            return true;
        }
        return false;
    }

    private void loadListBook() throws SQLException, ParseException {
        colName.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        colImportPrice.setCellValueFactory(new PropertyValueFactory<Book,BigDecimal>("importPrice"));
        colInventory.setCellValueFactory(new PropertyValueFactory<Book,Integer>("inventory"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Book,BigDecimal>("price"));
        BookService bookService = new BookService();
        tableBook.setItems(FXCollections.observableList(bookService.getBooks()));

    }

    private void cal(){
        int pr = (Integer.parseInt(tfImportPrice.getText())) * 125 /100;
        Price = BigDecimal.valueOf(Double.parseDouble(String.valueOf(pr)));
    }
}

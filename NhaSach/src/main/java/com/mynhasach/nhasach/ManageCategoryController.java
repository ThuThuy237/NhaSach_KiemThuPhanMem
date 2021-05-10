package com.mynhasach.nhasach;

import com.mynhasach.pojo.Book;
import com.mynhasach.pojo.Category;
import com.mynhasach.service.BookService;
import com.mynhasach.service.CategoryService;
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
import java.util.ResourceBundle;

public class ManageCategoryController implements Initializable {
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDecrible;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private TableColumn<Category,Integer> colId;
    @FXML
    private TableColumn<Category,String> colName;
    @FXML
    private TableColumn<Category, String> colDes;
    @FXML
    private Category category;
    @FXML
    private Window window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadListCate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(checkEmpty()){
                    try {
                        category = new Category(tfName.getText(),tfDecrible.getText());
                        CategoryService categoryServicee = new CategoryService();
                        if (categoryServicee.addCate(category)){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Add new Cate success!!!", 1000);
                                loadListCate();
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
                        loadListCate();
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
                if(tableCategory.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select row you want to delete", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        CategoryService servicece = new CategoryService();
                        if(servicece.deleteCate(tableCategory.getSelectionModel().getSelectedItem().getId()) ){
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Delete success!", 0);
                                loadListCate();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                new Util().showAlert(Alert.AlertType.INFORMATION, window,"Notion","Fail !!!", 0);
                                loadListCate();
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
                if(tableCategory.getSelectionModel().isEmpty()){
                    try {
                        new Util().showAlert(Alert.AlertType.ERROR, window,"Error","Select row you want to update", 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {

                    if(checkEmpty()){

                        try {
                            category = new Category(tableCategory.getSelectionModel().getSelectedItem().getId()
                                    , tfName.getText(),tfDecrible.getText());
                            CategoryService categoryService = new CategoryService();
                            if (categoryService.updateCate(category)){
                                try {
                                    new Util().showAlert(Alert.AlertType.INFORMATION, window,"notifications","Update Category success!!!", 1000);
                                    loadListCate();
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
                            loadListCate();
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
        this.tableCategory.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(e -> {
                tfName.setText(tableCategory.getSelectionModel().getSelectedItem().getName());
                tfDecrible.setText(tableCategory.getSelectionModel().getSelectedItem().getDescribe());
                tfId.setText(String.valueOf(tableCategory.getSelectionModel().getSelectedItem().getId()));
            });
            return r;
        });

    }

    private boolean checkEmpty() {
        if (!tfName.getText().isEmpty() & !tfId.getText().isEmpty() & !tfDecrible.getText().isEmpty()){
            return true;
        }
        return false;
    }

    private void loadListCate() throws SQLException, ParseException {
        colName.setCellValueFactory(new PropertyValueFactory<Category,String>("name"));
        colDes.setCellValueFactory(new PropertyValueFactory<Category,String>("describe"));
        colId.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        CategoryService categoryService = new CategoryService();
        tableCategory.setItems(FXCollections.observableList(categoryService.getCates("")));

    }

}

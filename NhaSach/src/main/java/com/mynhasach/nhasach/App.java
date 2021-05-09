package com.mynhasach.nhasach;

import com.mynhasach.pojo.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Login user;
    private static Boolean isAuthenticate = false;

    public static Login getUser() {
        return user;
    }

    public static void setUser(Login user) {
        App.user = user;
    }

    public static Boolean getIsAuthenticate() {
        return isAuthenticate;
    }

    public static void setIsAuthenticate(Boolean isAuthenticate) {
        App.isAuthenticate = isAuthenticate;
    }


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 830, 599);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("BookStore Manager");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
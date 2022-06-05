package com.mzielinski.commission;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

import static java.util.Objects.requireNonNull;
import static javafx.fxml.FXMLLoader.load;

public class CommissionApp extends Application {

    private static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;

    private static final URL fxml = requireNonNull(CommissionApp.class.getClassLoader()
            .getResource("commission.fxml"), "Cannot find commission.fxml file");
    private static final URL css = requireNonNull(CommissionApp.class.getClassLoader()
            .getResource("application.css"), "Cannot find application.fxml file");

    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(load(fxml), WIDTH, HEIGHT);
            scene.getStylesheets().add(css.toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(true);
            primaryStage.setTitle("Konrad 1.0 - prowizja 0,28%");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

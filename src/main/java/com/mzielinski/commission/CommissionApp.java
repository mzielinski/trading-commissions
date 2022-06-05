package com.mzielinski.commission;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

import static java.util.Objects.requireNonNull;
import static javafx.fxml.FXMLLoader.load;

public class CommissionApp extends Application {

    private static final String TITLE = "Konrad 1.0 - prowizja 0,28%";

    private static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;

    private static final URL fxml = requireNonNull(CommissionApp.class.getClassLoader()
            .getResource("commission.fxml"), "Cannot find commission.fxml file");
    private static final URL css = requireNonNull(CommissionApp.class.getClassLoader()
            .getResource("application.css"), "Cannot find application.fxml file");

    @Override
    public void start(Stage main) {
        try {
            Scene scene = new Scene(load(fxml), WIDTH, HEIGHT);
            scene.getStylesheets().add(css.toExternalForm());
            main.setScene(scene);
            main.show();
            main.setResizable(true);
            main.setTitle(TITLE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package app.graphic.ui.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import app.graphic.ui.controllers.Controller;

import java.io.IOException;

public final class StageMethods {

    private static String normalizePath(String url) {
        return url.charAt(0) == '/' ? url : "/" + url;
    }

    public static void closeWindow(Stage stage) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public static Controller initStage(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StageMethods.class.getResource(normalizePath(path)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.setTitle(title);
            return controller;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при открытии формы ", e);
        }
    }

}

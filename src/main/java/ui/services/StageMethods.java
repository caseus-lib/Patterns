package ui.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.controllers.Controller;

import java.io.IOException;
import java.util.Optional;

public final class StageMethods {

    public static Stage initStage(Parent parent, String title, Optional<Double> width, Optional<Double> height) {
        Stage stage = new Stage();
        stage.setTitle(title);
        Node firstChildren = Optional.ofNullable(parent.getChildrenUnmodifiable().get(0)).orElse(parent);
        AnchorPane mainContainer = Optional.ofNullable((AnchorPane) firstChildren.getParent()).orElse(null);
        stage.setScene(new Scene(parent,
                width.orElse(Optional.of(mainContainer.getPrefWidth()).orElse((double) 600)),
                height.orElse((Optional.of(mainContainer.getPrefHeight()).orElse((double) 400)))));
        return stage;
    }

    public static Stage initStage(Class clazz, String url, String title,
                                  Optional<Double> width, Optional<Double> height) throws IOException {
        return initStage(getParentFormResource(clazz, url), title, width, height);
    }

    public static Stage initStage(Class clazz, String url, String title) throws IOException {
        return initStage(clazz, url, title, Optional.empty(), Optional.empty());
    }

    public static Stage initStage(Parent parent, String title) {
        return initStage(parent, title, Optional.empty(), Optional.empty());
    }

    private static Parent getParentFormResource(Class clazz, String url) throws IOException {
        try {
            return FXMLLoader.load(clazz.getClass().getResource(normalizePath(url)));
        } catch (IOException e) {
            throw e;
        }
    }

    private static String normalizePath(String url) {
        return url.charAt(0) == '/' ? url : "/" + url;
    }

    public static void closeWindow(Stage stage) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public static Stage initStage(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StageMethods.class.getResource(normalizePath(path)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            ((Controller) loader.getController()).setStage(stage);
            stage.setScene(scene);
            stage.setTitle(title);
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

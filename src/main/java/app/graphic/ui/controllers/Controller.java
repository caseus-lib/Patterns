package app.graphic.ui.controllers;

import javafx.stage.Stage;

public class Controller {

    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showAndWait(Stage stage) {
        if (stage == null)
            return;
        stage.showAndWait();
    }

    public void show() {
        stage.show();
    }

}

package ui.controllers;

import javafx.stage.Stage;
import ui.services.StageMethods;

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

}

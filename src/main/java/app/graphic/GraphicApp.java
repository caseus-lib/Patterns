package app.graphic;

import app.executor.Steps;
import app.executor.Worker;
import javafx.application.Application;
import javafx.stage.Stage;
import app.graphic.ui.controllers.Controller;
import app.graphic.ui.services.StageMethods;

public class GraphicApp extends Application implements Worker {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Controller controller = StageMethods.initStage("forms/bakeryForm.fxml", "Волшебная пекарня");
        if (!(controller instanceof Steps)) {
            throw new RuntimeException("Контроллер не имплементирует интерфейс для работы пекарни");
        }
        controller.show();
    }
}

package app.graphic;

import app.executor.Steps;
import app.executor.Worker;
import app.graphic.ui.controllers.Controller;
import app.graphic.ui.services.StageMethods;
import javafx.application.Application;
import javafx.stage.Stage;
import process.bakery.BakeryShopSteps;

public class GraphicApp extends Application implements Worker {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Controller controller = StageMethods.initStage("forms/bakeryForm.fxml", "Волшебная пекарня");
        if (!(controller instanceof Steps || controller instanceof BakeryShopSteps)) {
            throw new RuntimeException("Контроллер не имплементирует интерфейс для работы пекарни");
        }
        controller.show();
    }
}

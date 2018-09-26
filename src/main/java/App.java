import app.Initialization;
import app.Steps;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.controllers.Controller;
import ui.services.StageMethods;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Initialization.init();
        Controller controller = StageMethods.initStage("forms/bakeryForm.fxml", "Волшебная пекарня");
        if (!(controller instanceof Steps)) {
            throw new RuntimeException("Контроллер не имплементирует интерфейс для работы пекарни");
        }
        controller.show();
    }
}

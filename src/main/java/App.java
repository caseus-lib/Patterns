import javafx.application.Application;
import javafx.stage.Stage;
import ui.services.StageMethods;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageMethods.initStage("forms/kitchenForm.fxml", "Кухня").show();
    }
}

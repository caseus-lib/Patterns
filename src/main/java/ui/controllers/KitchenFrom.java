package ui.controllers;

import javafx.scene.control.Button;

public class KitchenFrom extends Controller {
    public Button openFridgeButton;
    public Button cookButton;

    public void openFridgeButtonClick() {
        showAndWait(new FridgeForm().init());
    }

    public void cookButtonClick() {

    }
}

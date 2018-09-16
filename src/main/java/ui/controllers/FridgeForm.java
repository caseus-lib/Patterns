package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import magical.kitchen.Fridge;
import magical.kitchen.Storage;
import magical.products.Product;
import ui.services.StageMethods;

public class FridgeForm extends Controller {

    private Storage fridge = Fridge.getInstance();

    public ListView<Product> productView;
    public Button addButton;
    public Button removeButton;
    private ObservableList<Product> productObservableList;

    @FXML
    private void initialize() {
        productObservableList = FXCollections.observableArrayList(fridge.getAllProducts());
        productView.setItems(productObservableList);
    }

    public Stage init() {
        return StageMethods.initStage("forms/fridgeForm.fxml", "Холодильник");
    }

    public void addButtonClick() {
        showAndWait(new ProductAddInFridgeFrom().init());
        updateList();
    }

    private void updateList() {
        productObservableList.clear();
        productObservableList.addAll(fridge.getAllProducts());
    }

    public void removeButtonClick() {
        Product selectedItem = productView.getSelectionModel().getSelectedItem();
        fridge.remove(selectedItem);
        updateList();
    }
}

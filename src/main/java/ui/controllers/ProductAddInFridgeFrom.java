package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import magical.kitchen.Fridge;
import magical.powers.MagicalPower;
import magical.powers.PowersFactory;
import magical.products.MagicalProduct;
import ui.services.StageMethods;

public class ProductAddInFridgeFrom extends Controller {

    private Fridge fridge = Fridge.getInstance();

    public TextField titleField;
    public ChoiceBox<MagicalPower> powerField;
    public TextField amountField;
    public Button addButtonField;

    private ObservableList<MagicalPower> magicalPowers;

    private StringConverter<MagicalPower> stringConverter = new StringConverter<MagicalPower>() {
        @Override
        public String toString(MagicalPower object) {
            return object.getName();
        }

        @Override
        public MagicalPower fromString(String string) {
            return PowersFactory.getByName(string);
        }
    };

    @FXML
    private void initialize() {
        magicalPowers = FXCollections.observableArrayList();
        magicalPowers.addAll(PowersFactory.getPowersList());
        powerField.setItems(magicalPowers);
        powerField.setConverter(stringConverter);
    }

    public Stage init() {
        return StageMethods.initStage("forms/productAddForm.fxml",
                "Добавить продукт в холодильник");
    }

    public void addButtonFieldClick() {
        MagicalProduct magicalProduct = new MagicalProduct();
        magicalProduct.setName(titleField.getText());
        MagicalPower selectedItem = powerField.getSelectionModel().getSelectedItem();
        selectedItem.setAmount(Integer.parseInt(amountField.getText()));
        magicalProduct.addPower(selectedItem);
        fridge.add(magicalProduct);
        StageMethods.closeWindow(stage);
    }
}

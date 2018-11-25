package app.graphic.ui.controllers;

import app.executor.Behavior;
import app.executor.BehaviorExecutor;
import app.executor.Steps;
import app.executor.StepsWorker;
import app.graphic.ui.services.Size;
import app.graphic.ui.view.ProductImageButton;
import app.graphic.ui.view.ProductImageFactory;
import enums.Color;
import enums.ContextType;
import environment.creatures.Person;
import environment.creatures.Unicorn;
import environment.kitchen.ShowCase;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.products.SweetBox;
import environment.sale.MagicalAdapter;
import exception.NoProductFound;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import process.hall.Monitor;
import process.observer.Observer;
import process.observer.Subject;
import viewer.Context;
import viewer.ProductViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BakeryForm extends Controller implements Steps, Observer {

    public ImageView girlImage;
    public ImageView boyImage;
    public ImageView unicornImage;
    public ImageView cakeImage;
    public Button cakeButton;
    public ImageView boxImage;
    public ImageView rainbowImage;
    public ImageView magicImage;
    public TextArea customerTextArea;
    public TextArea sellerTextArea;
    public ImageView emptyImage;
    public ImageView customerDialogImage;
    public ImageView sellerDialogImage;
    public Button nextStepButton;
    public AnchorPane mainPane;
    public RadioButton redColor;
    public RadioButton greenColor;
    public RadioButton blueColor;
    public CheckBox withBow;
    public AnchorPane boxPane;
    public ListView<Integer> inWaitingListView;
    public ListView<Integer> cookingListView;
    public ListView<Integer> readyListView;

    private List<ImageView> images;

    private Behavior executor = new BehaviorExecutor();

    private StepsWorker stepsWorker;
    private ProductViewFactory productImageFactory = ProductImageFactory.getInstance();
    private Monitor monitor = new Monitor();

    @FXML
    private void initialize() {
        images = new ArrayList<>();
        images.add(girlImage);
        images.add(boyImage);
        images.add(unicornImage);
        images.add(cakeImage);
        images.add(boxImage);
        images.add(rainbowImage);
        images.add(magicImage);
        images.add(emptyImage);
        images.add(customerDialogImage);
        stepsWorker = new StepsWorker(this);
        cakeButton.setVisible(false);
        initShowCase();
        hideAll();
        emptyImage.setVisible(true);
    }

    private void initShowCase() {
        ShowCase.getInstance().getProductAmountMap().forEach((s, integer) -> {
            ProductImageButton productImage = (ProductImageButton) productImageFactory.getProductImage(
                    ShowCase.getInstance().getByName(s).orElseThrow(() -> new NoProductFound(s)),
                    new Context(ContextType.SHOW_CASE, new Size(100, 100), integer));
        });
    }

    @Override
    public void girlHasCame() {
        hideAll();
        setCustomerVisible(girlImage);
        customerTextArea.setText("Добрый день!");
        executor.assignNewOrdinalCreature(new Person("Мария"));
    }

    @Override
    public void boyHasCame() {
        hideAll();
        setCustomerVisible(boyImage);
        customerTextArea.setText("Здравствуйте!");
        executor.assignNewOrdinalCreature(new Person("Павел"));
    }

    @Override
    public void unicornHasCame() {
        hideAll();
        setCustomerVisible(unicornImage);
        customerTextArea.setText("Gjkzdadioadk!");
        executor.assignNewOrdinalCreature(new MagicalAdapter(new Unicorn()));
    }

    @Override
    public void personRequestsForMinPower() {
        MagicalPower magicalPower = executor.retrieveOrdinalCreatureMinPower();
        customerTextArea.setText("Мои силы: \n" + executor.retrieveCreatureInformation() +
                                 "Мне бы добавить себе такой силы, как: " + magicalPower.getName());
        executor.setBoxActiveState(false);
    }

    @Override
    public void personRequestsForBox() {
        executor.setBoxActiveState(true);
        customerTextArea.setText("Соберите мне, пожалуйста, волшебную коробку со сладостями");
        showBoxParameters(true);
    }

    @Override
    public void unicornRequestsForRainbow() {
        executor.setBoxActiveState(false);
        customerTextArea.setText("Tsejf ksjdfuwn jfuiwkw kdmkfs");
    }

    @Override
    public void personGetsProduct() {
        executor.consumeProduct();
        customerTextArea.setText("Спасибо! Теперь мои силы: \n" + executor.retrieveCreatureInformation());
    }

    @Override
    public void unicornGetsProduct() {
        executor.consumeProduct();
        customerTextArea.setText("Thfjsnfussk!");
    }

    @Override
    public void adapterWorks() {
        cakeButton.setVisible(false);
        magicImage.setVisible(true);
    }

    @Override
    public void personRequestsForMinPowerWithSpice() {
        MagicalPower magicalPower = executor.retrieveOrdinalCreatureMinPower();
        customerTextArea.setText("Мои силы: \n" + executor.retrieveCreatureInformation() +
                                 "Мне бы добавить себе такой силы, как: " + magicalPower.getName() +
                                 "\nИ обязательно добавить специй!");
        executor.setBoxActiveState(false);
    }

    @Override
    public void sellerGivesProductWithSpice() {
        Optional<Product> optionalProduct = executor.askForProduct();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            executor.saleProduct(product);
            String message = "Без специй:\n" + product.getName() + "\n" + product.getMagicalPowerList().toString();
            product = executor.decorateProduct();
            sellerTextArea.setText(message + "\nСо специями:\n" +
                                   product.getName() + "\n" + product.getMagicalPowerList().toString());
            updateButton(product);
            cakeButton.setVisible(true);
        } else {
            sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
        }
    }

    @Override
    public void sellerGivesProduct() {
        if (executor.isBoxActive()) {
            showBoxParameters(true);
            SweetBox product = executor.askForBox();
            buildBox(product);
            updateButton(product);
            sellerTextArea.setText(
                    "Держите волшебный набор" + "\n" +
                    "Состав коробки:\n" + product.getInfoAboutComponents()
                                  );
            cakeButton.setVisible(true);
        } else {
            Optional<Product> optionalProduct = executor.askForProduct();
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                executor.saleProduct(product);
                sellerTextArea.setText("Держите " + product.toString());
                updateButton(product);
                cakeButton.setVisible(true);
            } else {
                sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
            }
        }
    }

    private void buildBox(SweetBox box) {
        if (redColor.isSelected())
            box.buildColorBox(Color.RED);
        else {
            if (greenColor.isSelected())
                box.buildColorBox(Color.GREEN);
            else {
                if (blueColor.isSelected())
                    box.buildColorBox(Color.BLUE);
                else
                    box.buildColorBox(Color.WHITE);
            }
        }
        if (withBow.isSelected()) {
            box.buildBoxWithBow();
        }
        box.setName(box.getColor() + (box.getWithBow() ? "-bow" : ""));
    }

    private void updateButton(Product product) {
        mainPane.getChildren().remove(cakeButton);
        cakeButton = (ProductImageButton) productImageFactory.getProductImage(product,
                                                                              new Context(ContextType.GOODS,
                                                                                          new Size(50, 50)));
        cakeButton.setLayoutX(177);
        cakeButton.setLayoutY(200);
        mainPane.getChildren().add(cakeButton);
    }

    @Override
    public void watchShowCase() {

    }

    private void setCustomerVisible(ImageView imageView) {
        imageView.setVisible(true);
        customerDialogImage.setVisible(true);
        customerTextArea.setVisible(true);
    }

    private void hideAll() {
        images.forEach(imageView -> imageView.setVisible(false));
        customerTextArea.setVisible(false);
        cakeButton.setVisible(false);
        showBoxParameters(false);
    }

    private void showBoxParameters(boolean value) {
        boxPane.setVisible(value);
        greenColor.setVisible(value);
        redColor.setVisible(value);
        blueColor.setVisible(value);
        withBow.setVisible(value);
    }

    public void nextStep() {
        stepsWorker.nextStep();
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Monitor) {
            inWaitingListView.setItems(FXCollections.observableArrayList(monitor.getWaitingOrderList()));
            cookingListView.setItems(FXCollections.observableArrayList(monitor.getCookingOrderList()));
            readyListView.setItems(FXCollections.observableArrayList(monitor.getReadyOrderList()));
        }
    }
}

package app.graphic.ui.controllers;

import app.graphic.ui.services.Size;
import app.graphic.ui.view.ProductImageButton;
import app.graphic.ui.view.ProductImageFactory;
import enums.Color;
import enums.ContextType;
import enums.ProductType;
import enums.TimesOfDay;
import environment.creatures.Person;
import environment.creatures.Unicorn;
import environment.products.Product;
import environment.products.SweetBox;
import environment.sale.MagicalAdapter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import process.bakery.BakeryShopBehavior;
import process.bakery.BakeryShopBehaviorExecutor;
import process.bakery.BakeryShopSteps;
import process.bakery.BakeryShopStepsWorker;
import process.hall.Monitor;
import process.kitchen.ExtraditionPlace;
import process.model.Order;
import viewer.Context;
import viewer.ProductViewFactory;

import java.util.ArrayList;
import java.util.List;

public class BakeryForm extends Controller implements BakeryShopSteps {

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
    public ListView<Integer> inWaitingListView;
    public ListView<Integer> cookingListView;
    public ListView<Integer> readyListView;
    public FlowPane extraditionPane;
    public ImageView badWitchImage;
    public ImageView fairyImage;
    public ImageView sunImage;
    public ImageView moonImage;
    public TextArea journalText;
    public AnchorPane boxPane;
    public RadioButton redColor;
    public RadioButton greenColor;
    public RadioButton blueColor;
    public CheckBox withBow;

    private List<ImageView> images;

    private BakeryShopBehavior executor = new BakeryShopBehaviorExecutor();

    private BakeryShopStepsWorker stepsWorker;
    private ProductViewFactory productImageFactory = ProductImageFactory.getInstance();
    private Monitor monitor = Monitor.getInstance();
    private ExtraditionPlace extraditionPlace = ExtraditionPlace.getInstance();

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
        images.add(badWitchImage);
        images.add(fairyImage);
        stepsWorker = new BakeryShopStepsWorker(this);
        cakeButton.setVisible(false);
        hideAll();
        emptyImage.setVisible(true);
    }

    @Override
    public void aksForBox() {
        customerTextArea.setText("Соберите мне, пожалуйста, волшебную коробку со сладостями");
        showBoxParameters(true);
    }

    @Override
    public void saleBox() {
        cakeButton.setVisible(true);
        showBoxParameters(true);
        SweetBox product = executor.askForBox();
        buildBox(product);
        updateButton(product);
        sellerTextArea.setText(
                "Держите волшебный набор" + "\n" +
                "Состав коробки:\n" + product.getInfoAboutComponents()
                              );
        cakeButton.setVisible(true);
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
        cakeButton.setLayoutX(452);
        cakeButton.setLayoutY(330);
        mainPane.getChildren().add(cakeButton);
    }

    @Override
    public void setDay() {
        sunImage.setVisible(true);
        moonImage.setVisible(false);
        executor.setTimesOfDay(TimesOfDay.DAY);
    }

    @Override
    public void setNight() {
        sunImage.setVisible(false);
        moonImage.setVisible(true);
        executor.setTimesOfDay(TimesOfDay.NIGHT);
    }

    @Override
    public void girlHasCame() {
        hideAll();
        setCustomerVisible(girlImage);
        customerTextArea.setText("Добрый день!");
        executor.assignNewOrdinalCreature(new Person("Мария"));
        executor.visitCreature();
        updateJournal();
    }

    @Override
    public void boyHasCame() {
        hideAll();
        setCustomerVisible(boyImage);
        customerTextArea.setText("Здравствуйте!");
        executor.assignNewOrdinalCreature(new Person("Павел"));
        executor.visitCreature();
        updateJournal();
    }

    @Override
    public void unicornHasCame() {
        hideAll();
        setCustomerVisible(unicornImage);
        customerTextArea.setText("Gjkzdadioadk!");
        executor.assignNewOrdinalCreature(new MagicalAdapter(new Unicorn()));
        executor.visitCreature();
        updateJournal();
    }

    @Override
    public void witchHasCome() {
        badWitchImage.setVisible(true);
        executor.destroyAll();
    }

    @Override
    public void fairyHasCome() {
        badWitchImage.setVisible(false);
        fairyImage.setVisible(true);
        executor.recoverAll();
    }

    @Override
    public void personRequestsForCandy() {
        customerTextArea.setText("Мне так хочется 5 конфет!");
        executor.askForProductAmount(ProductType.CANDY, 5);
    }

    @Override
    public void personRequestsForBiscuit() {
        customerTextArea.setText("Дайте мне, пожалуйста, 2 печеньки.");
        executor.askForProductAmount(ProductType.BISCUIT, 2);
    }

    @Override
    public void personRequestsForCake() {
        customerTextArea.setText("Хочу торт!");
        executor.askForProductAmount(ProductType.CAKE, 1);
    }

    @Override
    public void sellerAcceptOrder() {
        sellerTextArea.setText("Спасибо за заказ!\n" + executor.retrieveOrderInformation() +
                               "\nНомер вашего заказа: " + executor.generateOrderNumber());
        executor.acceptOrder();
    }

    private Button buildProductButton(Product product) {
        return (ProductImageButton) productImageFactory.getProductImage(product,
                                                                        new Context(ContextType.POOL,
                                                                                    new Size(50, 50)));
    }

    private void buildExtraditionPlace() {
        extraditionPane.getChildren().clear();
        extraditionPlace.getReadyOrderList().forEach((order, products) -> {
            extraditionPane.getChildren().add(buildOrderButton(order));
            products.forEach(product -> extraditionPane.getChildren().add(buildProductButton(product)));
        });
    }

    private void updateJournal() {
        journalText.setText(executor.getJournalText());
    }

    private Button buildOrderButton(Order order) {
        Button button = new Button();
        button.setText(order.getOrderNumber() + "");
        button.setOnMouseClicked(event -> extraditionPlace.remove(order));
        button.setMinWidth(extraditionPane.getPrefWidth());
        return button;
    }

    private void setCustomerVisible(ImageView imageView) {
        imageView.setVisible(true);
        customerDialogImage.setVisible(true);
        customerTextArea.setVisible(true);
    }

    private void hideAll() {
        sellerTextArea.setText("");
        cakeButton.setVisible(false);
        images.forEach(imageView -> imageView.setVisible(false));
        customerTextArea.setVisible(false);
        showBoxParameters(false);
    }

    private void showBoxParameters (boolean value){
        boxPane.setVisible(value);
        greenColor.setVisible(value);
        redColor.setVisible(value);
        blueColor.setVisible(value);
        withBow.setVisible(value);
    }

    public void nextStep() {
        stepsWorker.nextStep();
    }

    public void update() {
        updateWaitingList();
        updateCookingList();
        updateReadyList();
        buildExtraditionPlace();
    }

    private synchronized void updateWaitingList() {
        inWaitingListView.setItems(FXCollections.observableArrayList(monitor.getWaitingOrderList()));
    }

    private synchronized void updateCookingList() {
        cookingListView.setItems(FXCollections.observableArrayList(monitor.getCookingOrderList()));
    }

    private synchronized void updateReadyList() {
        readyListView.setItems(FXCollections.observableArrayList(monitor.getReadyOrderList()));
    }
}

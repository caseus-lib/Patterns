package app.graphic.ui.controllers;

import app.graphic.ui.services.Size;
import app.graphic.ui.view.ProductImageButton;
import app.graphic.ui.view.ProductImageFactory;
import enums.ContextType;
import enums.ProductType;
import environment.creatures.Person;
import environment.creatures.Unicorn;
import environment.products.Product;
import environment.sale.MagicalAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import process.bakery.BakeryShopBehavior;
import process.bakery.BakeryShopBehaviorExecutor;
import process.bakery.BakeryShopSteps;
import process.bakery.BakeryShopStepsWorker;
import process.hall.Monitor;
import process.observer.Observer;
import process.observer.Subject;
import viewer.Context;
import viewer.ProductViewFactory;

import java.util.ArrayList;
import java.util.List;

public class BakeryForm extends Controller implements BakeryShopSteps, Observer {

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

    private List<ImageView> images;

    private BakeryShopBehavior executor = new BakeryShopBehaviorExecutor();

    private BakeryShopStepsWorker stepsWorker;
    private ProductViewFactory productImageFactory = ProductImageFactory.getInstance();
    private Monitor monitor = Monitor.getInstance();
    private ObservableList<String> inWaitingList;

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
        stepsWorker = new BakeryShopStepsWorker(this);
        cakeButton.setVisible(false);
        hideAll();
        emptyImage.setVisible(true);
        monitor.attach(this);
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

    private void updateButton(Product product) {
        mainPane.getChildren().remove(cakeButton);
        cakeButton = (ProductImageButton) productImageFactory.getProductImage(product,
                                                                              new Context(ContextType.GOODS,
                                                                                          new Size(50, 50)));
        cakeButton.setLayoutX(177);
        cakeButton.setLayoutY(200);
        mainPane.getChildren().add(cakeButton);
    }

    private void setCustomerVisible(ImageView imageView) {
        imageView.setVisible(true);
        customerDialogImage.setVisible(true);
        customerTextArea.setVisible(true);
    }

    private void hideAll() {
        sellerTextArea.setText("");
        images.forEach(imageView -> imageView.setVisible(false));
        customerTextArea.setVisible(false);
        cakeButton.setVisible(false);
    }

    public void nextStep() {
        stepsWorker.nextStep();
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Monitor) {
            updateWaitingList();
            updateCookingList();
            updateReadyList();
        }
    }

    private synchronized void updateWaitingList() {
        System.out.println(Thread.currentThread().getId());
        inWaitingListView.setItems(FXCollections.observableArrayList(monitor.getWaitingOrderList()));
    }

    private synchronized void updateCookingList() {
        cookingListView.setItems(FXCollections.observableArrayList(monitor.getCookingOrderList()));
    }

    private synchronized void updateReadyList() {
        readyListView.setItems(FXCollections.observableArrayList(monitor.getReadyOrderList()));
    }
}

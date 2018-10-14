package app.graphic.ui.controllers;

import app.executor.Steps;
import app.executor.StepsWorker;
import app.graphic.ui.services.Size;
import app.graphic.ui.view.ProductImageButton;
import app.graphic.ui.view.ProductImageFactory;
import enums.ContextType;
import environment.creatures.OrdinalCreature;
import environment.creatures.Person;
import environment.creatures.Unicorn;
import environment.kitchen.BoxMaker;
import environment.kitchen.ShowCase;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.products.SpiceDecorator;
import environment.sale.Bakery;
import environment.sale.MagicalAdapter;
import environment.sale.SellerProxy;
import exception.NoProductFound;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import viewer.Context;
import viewer.ProductViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BakeryForm extends Controller implements Steps {

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
    public FlowPane showCaseFlowPane;
    public AnchorPane mainPane;

    private List<ImageView> images;

    private StepsWorker stepsWorker;

    private Bakery seller;
    private Product product;
    private MagicalPower magicalPower;
    private OrdinalCreature ordinalCreature;
    private boolean getBox = false;
    private ProductViewFactory productImageFactory = ProductImageFactory.getInstance();

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
        seller = new SellerProxy();
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
            showCaseFlowPane.getChildren().add(productImage);
        });
    }

    @Override
    public void girlHasCame() {
        hideAll();
        setCustomerVisible(girlImage);
        customerTextArea.setText("Добрый день!");
        ordinalCreature = new Person("Мария");
    }

    @Override
    public void boyHasCame() {
        hideAll();
        setCustomerVisible(boyImage);
        customerTextArea.setText("Здравствуйте!");
        ordinalCreature = new Person("Павел");
    }

    @Override
    public void unicornHasCame() {
        hideAll();
        setCustomerVisible(unicornImage);
        customerTextArea.setText("Gjkzdadioadk!");
        ordinalCreature = new MagicalAdapter(new Unicorn());
    }

    @Override
    public void personRequestsForMinPower() {
        magicalPower = ordinalCreature.getMinPower();
        customerTextArea.setText("Мои силы: \n" + ordinalCreature.toString() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName());
        getBox = false;
    }

    @Override
    public void personRequestsForBox() {
        getBox = true;
        customerTextArea.setText("Соберите мне, пожалуйста, волшебную коробку со сладостями");
    }

    @Override
    public void unicornRequestsForRainbow() {
        getBox = false;
        customerTextArea.setText("Tsejf ksjdfuwn jfuiwkw kdmkfs");
    }

    @Override
    public void personGetsProduct() {
        ordinalCreature.consume(product);
        customerTextArea.setText("Спасибо! Теперь мои силы: \n" + ordinalCreature.toString());
    }

    @Override
    public void unicornGetsProduct() {
        magicImage.setVisible(false);
        ordinalCreature.consume(product);
        rainbowImage.setVisible(true);
        customerTextArea.setText("Thfjsnfussk!");
    }

    @Override
    public void adapterWorks() {
        cakeButton.setVisible(false);
        magicImage.setVisible(true);
    }

    @Override
    public void personRequestsForMinPowerWithSpice() {
        magicalPower = ordinalCreature.getMinPower();
        customerTextArea.setText("Мои силы: \n" + ordinalCreature.toString() +
                "Мне бы добавить себе такой силы, как: " + magicalPower.getName() +
                "\nИ обязательно добавить специй!");
        getBox = false;
    }

    @Override
    public void sellerGivesProductWithSpice() {
        Optional<Product> optionalProduct = seller.hasProduct(magicalPower);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            seller.saleProduct(product);
            String message = "Без специй:\n" + product.getName() + "\n" + product.getMagicalPowerList().toString();
            product = new SpiceDecorator(product);
            sellerTextArea.setText(message + "\nСо специями:\n" +
                    product.getName() + "\n" + product.getMagicalPowerList().toString());
            updateButton();
            cakeButton.setVisible(true);
        } else {
            sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
        }
    }

    @Override
    public void sellerGivesProduct() {
        if (getBox) {
            product = BoxMaker.createRandomBox();
            sellerTextArea.setText(
                    "Держите " + product.toString() + "\n" +
                            "Состав коробки:\n" + product.getInfoAboutComponents()
            );
            updateButton();
            cakeButton.setVisible(true);
        } else {
            Optional<Product> optionalProduct = seller.hasProduct(magicalPower);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                seller.saleProduct(product);
                sellerTextArea.setText("Держите " + product.toString());
                updateButton();
                cakeButton.setVisible(true);
            } else {
                sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
            }
        }
    }

    private void updateButton() {
        mainPane.getChildren().remove(cakeButton);
        cakeButton = (ProductImageButton) productImageFactory.getProductImage(product,
                new Context(ContextType.GOODS, new Size(50, 50)));
        cakeButton.setLayoutX(177);
        cakeButton.setLayoutY(200);
        mainPane.getChildren().add(cakeButton);
    }

    @Override
    public void watchShowCase() {
        showCaseFlowPane.setVisible(true);
    }

    private void setCustomerVisible(ImageView imageView) {
        imageView.setVisible(true);
        customerDialogImage.setVisible(true);
        customerTextArea.setVisible(true);
    }

    private void hideAll() {
        images.forEach(imageView -> imageView.setVisible(false));
        customerTextArea.setVisible(false);
        showCaseFlowPane.setVisible(false);
        cakeButton.setVisible(false);
    }

    public void nextStep() {
        stepsWorker.nextStep();
    }
}

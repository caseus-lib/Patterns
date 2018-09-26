package ui.controllers;

import app.Steps;
import creatures.OrdinalCreature;
import creatures.Person;
import creatures.Unicorn;
import iterator.ForwardIterator;
import iterator.Iterator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import magical.kitchen.BoxMaker;
import magical.kitchen.ShowCase;
import magical.powers.MagicalPower;
import magical.products.Product;
import magical.products.SpiceDecorator;
import sale.Bakery;
import sale.MagicalAdapter;
import sale.SellerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BakeryForm extends Controller implements Steps {

    public ImageView girlImage;
    public ImageView boyImage;
    public ImageView unicornImage;
    public ImageView cakeImage;
    public ImageView boxImage;
    public ImageView rainbowImage;
    public ImageView magicImage;
    public TextArea customerTextArea;
    public TextArea sellerTextArea;
    public ImageView emptyImage;
    public ImageView customerDialogImage;
    public ImageView sellerDialogImage;
    public Button nextStepButton;
    public TextArea caseShow;

    private List<ImageView> images;

    private List<Runnable> stepsArray;
    private Iterator<Runnable> iterator;

    private Bakery seller;
    private Product product;
    private MagicalPower magicalPower;
    private OrdinalCreature ordinalCreature;
    private boolean getBox = false;

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
        initSteps();
        iterator = new ForwardIterator<>(stepsArray);
        iterator.first();
        hideAll();
        emptyImage.setVisible(true);
    }

    private void initSteps() {
        stepsArray = new ArrayList<>();
        stepsArray.add(this::watchShowCase);
        stepsArray.add(this::girlHasCame);
        stepsArray.add(this::personRequestsForMinPower);
        stepsArray.add(this::sellerGivesProduct);
        stepsArray.add(this::personGetsProduct);
        stepsArray.add(this::boyHasCame);
        stepsArray.add(this::personRequestsForBox);
        stepsArray.add(this::sellerGivesProduct);
        stepsArray.add(this::personGetsProduct);
        stepsArray.add(this::unicornHasCame);
        stepsArray.add(this::unicornRequestsForRainbow);
        stepsArray.add(this::sellerGivesProduct);
        stepsArray.add(this::adapterWorks);
        stepsArray.add(this::unicornGetsProduct);
        stepsArray.add(this::girlHasCame);
        stepsArray.add(this::personRequestsForMinPowerWithSpice);
        stepsArray.add(this::sellerGivesProductWithSpice);
        stepsArray.add(this::personGetsProduct);
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
        cakeImage.setVisible(false);
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
            cakeImage.setVisible(true);
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
            boxImage.setVisible(true);
        } else {
            Optional<Product> optionalProduct = seller.hasProduct(magicalPower);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                seller.saleProduct(product);
                sellerTextArea.setText("Держите " + product.toString());
                cakeImage.setVisible(true);
            } else {
                sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
            }
        }
    }

    @Override
    public void watchShowCase() {
        caseShow.setVisible(true);
        caseShow.setText(
                ShowCase.getInstance().getAllProducts()
                .stream()
                .map(bake -> bake.getName() + "\n" + bake.getInfoAboutComponents()).
                collect(Collectors.joining("\n"))
        );
    }

    private void setCustomerVisible(ImageView imageView) {
        imageView.setVisible(true);
        customerDialogImage.setVisible(true);
        customerTextArea.setVisible(true);
    }

    private void hideAll() {
        images.forEach(imageView -> imageView.setVisible(false));
        customerTextArea.setVisible(false);
        caseShow.setVisible(false);
    }

    public void nextStep() {
        if (!iterator.isDone()) {
            iterator.currentItem().run();
            iterator.next();
        }
    }
}

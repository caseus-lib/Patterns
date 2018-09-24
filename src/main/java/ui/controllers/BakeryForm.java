package ui.controllers;

import app.Steps;
import creatures.MagicalCreature;
import creatures.OrdinalCreature;
import creatures.Person;
import creatures.Unicorn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import magical.kitchen.BoxMaker;
import magical.powers.MagicalPower;
import magical.products.Product;
import sale.Bakery;
import sale.MagicalAdapter;
import sale.Seller;
import sale.SellerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BakeryForm implements Steps {

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

    private List<ImageView> images;

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
        customerTextArea.setText("Мне бы добавить себе такой силы, как: " + magicalPower.getName());
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
        customerTextArea.setText("Спасибо! Теперь мои силы: \n" + ordinalCreature.toString());
    }

    @Override
    public void unicornGetsProduct() {
        rainbowImage.setVisible(true);
        customerTextArea.setText("Thfjsnfussk!");
    }

    @Override
    public void adapterWorks() {
        cakeImage.setVisible(false);
        magicImage.setVisible(true);
    }

    @Override
    public void sellerGivesProduct() {
        if (getBox) {
            product = BoxMaker.createRandomBox();
            sellerTextArea.setText("Держите " + product.toString());
            boxImage.setVisible(true);
        }
        else {
            Optional<Product> product = seller.hasProduct(magicalPower);
            if (product.isPresent()) {
                Product productForSale = product.get();
                seller.saleProduct(productForSale);
                sellerTextArea.setText("Держите " + product.toString());
                cakeImage.setVisible(true);
            } else {
                sellerTextArea.setText("К сожалению, все законичилось. Приходите завтра!");
            }
        }
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
    }
}

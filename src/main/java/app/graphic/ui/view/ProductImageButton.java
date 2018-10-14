package app.graphic.ui.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import environment.products.Product;
import printer.ProductView;

public class ProductImageButton extends Button implements ProductView {

    private Product product;
    private boolean isImageVisible;
    private ImageView image;

    public ProductImageButton() {
        super();
    }

    public ProductImageButton(Product product) {
        isImageVisible = true;
        this.product = product;
        image = new ImageView(new Image(getClass().getResourceAsStream(
                "/img/products/" + product.getName() + ".png")));
        initNode();
        changeState();
    }

    private void initNode() {
        this.setOnMouseClicked(event -> changeState());
    }

    private void changeState() {
        if (isImageVisible) {
            setGraphic(image);
            setText("");
        } else {
            setGraphic(null);
            setText(product.getName() + "\n" + product.getInfoAboutComponents());
        }
        isImageVisible = !isImageVisible;
    }

    @Override
    public void setSize(double width, double height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

    @Override
    public ProductView clone(Product product) {
        return new ProductImageButton(product);
    }

}

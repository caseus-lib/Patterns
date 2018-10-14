package app.graphic.ui.components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import environment.products.Product;

public class ProductImageButton extends Button {

    private Product product;
    private boolean isImageVisible;
    private ImageView image;

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

    public void setSize(double width, double height) {
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

}

package app.graphic.ui.view;

import enums.ContextType;
import environment.Counter;
import environment.products.Product;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewer.Context;
import viewer.ProductView;

public class ProductImageButton extends Button implements ProductView {

    private Product product;
    private boolean isImageVisible;
    private ImageView image;
    private String text;

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

    public void setImage(ImageView image) {
        this.image = image;
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
            setText(text);
        }
        isImageVisible = !isImageVisible;
    }

    @Override
    public void setState(Context context) {
        if (context.getContextType() == ContextType.BOX) {
            text = "Волшебная коробка" +
                   "\nСтоимость: " + Counter.getInstance().countTotalProductCost(product);
            setTooltip(null);
        } else if (context.getContextType() == ContextType.SHOW_CASE) {
            text = product.getName() +
                   "\n" + product.getInfoAboutComponents();
            setTooltip(new Tooltip(context.getAmount().toString()));
        } else {
            text = product.getName() +
                   "\nСтоимость: " + Counter.getInstance().countTotalProductCost(product);
            setTooltip(null);
        }
        image.setFitHeight(context.getSize().getY());
        image.setFitWidth(context.getSize().getX());
    }

    @Override
    public ProductView clone(Product product) {
        return new ProductImageButton(product);
    }

}

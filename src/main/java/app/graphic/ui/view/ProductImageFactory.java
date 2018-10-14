package app.graphic.ui.view;

import printer.ProductViewFactory;

public class ProductImageFactory extends ProductViewFactory {

    private static ProductImageFactory ourInstance = new ProductImageFactory();

    public static ProductImageFactory getInstance() {
        return ourInstance;
    }

    private ProductImageFactory() {
        super(new ProductImageButton());
    }
}

package app.console.view;

import printer.ProductViewFactory;

public class ProductDescriptionFactory extends ProductViewFactory {

    private static ProductDescriptionFactory ourInstance = new ProductDescriptionFactory();

    public static ProductDescriptionFactory getInstance() {
        return ourInstance;
    }

    private ProductDescriptionFactory() {
        super(new ProductDescriptionText());
    }
}

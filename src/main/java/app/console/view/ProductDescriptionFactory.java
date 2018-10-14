package app.console.view;

import viewer.ProductViewFactory;

public class ProductDescriptionFactory extends ProductViewFactory {

    private static ProductDescriptionFactory ourInstance = new ProductDescriptionFactory();

    public static ProductDescriptionFactory getInstance() {
        return ourInstance;
    }

    private ProductDescriptionFactory() {
        super(new ProductDescriptionText());
    }
}

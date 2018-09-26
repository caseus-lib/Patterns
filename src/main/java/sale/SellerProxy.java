package sale;

import magical.powers.MagicalPower;
import magical.products.Product;

import java.util.Optional;

public class SellerProxy implements Bakery {

    private Seller seller;

    public SellerProxy() {
        seller = new Seller();
    }

    @Override
    public void addProduct(Product...product) {
        seller.addProduct(product);
    }

    @Override
    public Optional<Product> hasProduct(MagicalPower magicalPower) {
        System.out.println("(S) - You want " + magicalPower.getName());
        Optional<Product> product = seller.hasProduct(magicalPower);
        if (!product.isPresent()) {
            return seller.hasAnyProduct();
        }
        return product;
    }

    @Override
    public void saleProduct(Product product) {
        seller.saleProduct(product);
    }

    @Override
    public Optional<Product> hasAnyProduct() {
        return seller.hasAnyProduct();
    }
}

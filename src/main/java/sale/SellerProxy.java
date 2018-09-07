package sale;

import magical.bake.Bake;
import magical.powers.MagicalPower;

import java.util.Optional;

public class SellerProxy implements Bakery {

    private Seller seller;

    public SellerProxy() {
        seller = new Seller();
    }

    @Override
    public void addBake(Bake ...bake) {
        seller.addBake(bake);
    }

    @Override
    public Optional<Bake> hasBake(MagicalPower magicalPower) {
        System.out.println("(S) - You want " + magicalPower.getName());
        Optional<Bake> bake = seller.hasBake(magicalPower);
        if (!bake.isPresent()) {
            System.out.println("(S) - There is no any bake to give you " + magicalPower.getName());
            return seller.hasAnyBake();
        }
        return bake;
    }

    @Override
    public void saleBake(Bake bake) {
        seller.saleBake(bake);
    }

    @Override
    public Optional<Bake> hasAnyBake() {
        return seller.hasAnyBake();
    }
}

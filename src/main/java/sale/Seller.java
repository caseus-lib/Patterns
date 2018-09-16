package sale;

import magical.bake.Bake;
import magical.powers.MagicalPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Seller implements Bakery {
    private List<Bake> bakes;

    Seller() {
        bakes = new ArrayList<>();
    }

    @Override
    public void addBake(Bake ...bake) {
        bakes.addAll(Arrays.asList(bake));
    }

    @Override
    public void saleBake(Bake bake) {
        bakes.remove(bake);
    }

    @Override
    public Optional<Bake> hasBake(MagicalPower magicalPower) {
        return bakes.stream()
                .filter(bake -> bake.hasPower(magicalPower))
                .findFirst();
    }

    @Override
    public Optional<Bake> hasAnyBake() {
        return bakes.stream().findAny();
    }

}

package sale;

import magical.bake.Bake;
import magical.powers.MagicalPower;

import java.util.Optional;

public interface Bakery {

    void addBake(Bake ... bake);
    Optional<Bake> hasBake(MagicalPower magicalPower);
    Optional<Bake> hasAnyBake();
    void saleBake(Bake bake);

}

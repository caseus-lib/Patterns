package environment.creatures;

import environment.magical.powers.*;
import exception.NoPowerFound;
import iterator.Iterator;
import environment.products.Product;

public class Person implements OrdinalCreature {

    private MagicalPowerList powers;
    private String name = "anonymous";

    private Person() {
        powers = new MagicalPowerList();
        powers.add(new Happiness(0));
        powers.add(new Responsibility(0));
        powers.add(new Strength(0));
    }

    public Person(String name) {
        this();
        this.name = name;
    }

    public void consume(Product product) {
        addPower(product.getMagicalPowerList());
    }

    private void addPower(MagicalPowerList magicalPowerList) {
        powers.merge(magicalPowerList);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        Iterator<MagicalPower> iterator = powers.createIterator();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            info.append(iterator.currentItem().toString()).append('\n');
        }
        return info.toString();
    }

    @Override
    public MagicalPower getMinPower() {
        return powers.getStream()
                .min(MagicalPower::compareTo)
                .orElseThrow(NoPowerFound::new);
    }

    public String getName() {
        return name;
    }
}

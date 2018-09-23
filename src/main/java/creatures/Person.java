package creatures;

import exception.NoPowerFound;
import iterator.Iterator;
import magical.powers.*;
import magical.products.Product;

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
        System.out.println("I'm " + name + " and I'm consuming " + product.getName() + " with power " +
                product.getMagicalPowerList().toString());
        addPower(product.getMagicalPowerList());
    }

    public void addPower(MagicalPowerList magicalPowerList) {
        powers.merge(magicalPowerList);
    }

    public void printInfo() {
        System.out.println("I'm " + name + " and my powers are:");
        Iterator<MagicalPower> iterator = powers.createIterator();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            iterator.currentItem().printPower();
        }
    }

    public MagicalPower getMinPower() {
        return powers.getStream()
                .min(MagicalPower::compareTo)
                .orElseThrow(NoPowerFound::new);
    }

    public String getName() {
        return name;
    }
}

package people;

import exception.NoPowerFound;
import iterator.Iterator;
import magical.bake.Bake;
import magical.powers.*;

public class Person {

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

    public void consumeBake(Bake bake) {
        System.out.println("I'm " + name + " and I'm consuming " + bake.getName() + " with power " +
                bake.getMagicalPower().getName());
        addPower(bake.getMagicalPower());
    }

    public void addPower(MagicalPower magicalPower) {
        powers.add(magicalPower);
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

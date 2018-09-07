package people;

import exception.NoPowerFound;
import magical.bake.Bake;
import magical.powers.Happiness;
import magical.powers.MagicalPower;
import magical.powers.Responsibility;
import magical.powers.Strength;

import java.util.*;

public class Person {

    private List<MagicalPower> powers;
    private String name = "anonymous";

    private Person() {
        powers = new ArrayList<>();
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
        powers.forEach(power -> power.merge(magicalPower));
    }

    public void printInfo() {
        System.out.println("I'm " + name + " and my powers are:");
        powers.forEach(MagicalPower::printPower);
    }

    public MagicalPower getMinPower() {
        return powers.stream()
                .min(MagicalPower::compareTo)
                .orElseThrow(NoPowerFound::new);
    }

    public String getName() {
        return name;
    }
}

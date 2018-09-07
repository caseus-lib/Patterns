import magical.bake.Bake;
import magical.bake.Biscuit;
import magical.bake.Cake;
import magical.bake.Candy;
import magical.powers.Happiness;
import magical.powers.Responsibility;
import magical.powers.Strength;
import people.Person;
import sale.SellerProxy;
import sale.Bakery;

import java.util.Optional;

public class Main {

    private static Bakery bakery;

    private static void doIteration(Person person) {
        person.printInfo();

        Optional<Bake> bake = bakery.hasBake(person.getMinPower());
        if (bake.isPresent()) {
            Bake bakeForSale = bake.get();
            bakery.saleBake(bakeForSale);
            person.consumeBake(bakeForSale);
        }
        else {
            System.out.println("I have not get any bake");
        }

        person.printInfo();
        System.out.println();
    }

    public static void main(String[] args) {

        bakery = new SellerProxy();
        bakery.addBake(
                new Cake(),
                new Cake(),
                new Biscuit(),
                new Candy(),
                new Candy(),
                new Candy()
        );

        Person sadGirl = new Person("Maria");
        sadGirl.addPower(new Happiness(2));
        sadGirl.addPower(new Responsibility(5));
        sadGirl.addPower(new Strength(3));

        doIteration(sadGirl);

        Person weakBoy = new Person("Artur");
        weakBoy.addPower(new Happiness(50));
        weakBoy.addPower(new Strength(1));
        weakBoy.addPower(new Responsibility(100));

        for (int i = 0; i < 2; i++) {
            doIteration(weakBoy);
        }


        Person sadWeakRecklessMan = new Person("Karl");
        sadWeakRecklessMan.addPower(new Happiness(1));
        sadWeakRecklessMan.addPower(new Responsibility(1));
        sadWeakRecklessMan.addPower(new Strength(1));

        for (int i = 0; i < 3; i++) {
            doIteration(sadWeakRecklessMan);
        }


    }

}

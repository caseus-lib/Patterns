package magical.powers;

public class Happiness extends MagicalPower {

    public Happiness(Integer amount) {
        super(amount);
    }

    @Override
    public void printPower() {
        System.out.println("Happiness - a state of well-being and contentment ");
        System.out.println("\t units: " + amount);
    }

    @Override
    public String getName() {
        return "Happiness";
    }

    @Override
    public boolean same(MagicalPower magicalPower) {
        return magicalPower instanceof Happiness;
    }
}

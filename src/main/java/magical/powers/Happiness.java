package magical.powers;

public class Happiness extends MagicalPower {

    public Happiness(Integer amount) {
        super(amount);
    }

    public Happiness() {
        super();
    }

    private Happiness(MagicalPower magicalPower) {
        super(magicalPower);
    }

    @Override
    public MagicalPower copy() {
        return new Happiness(this);
    }

    @Override
    public void printPower() {
        System.out.println("Happiness - a state of well-being and contentment ");
        System.out.println("\t units: " + amount);
    }

    @Override
    public String getName() {
        return "Счастье";
    }

    @Override
    public boolean same(MagicalPower magicalPower) {
        return magicalPower instanceof Happiness;
    }
}

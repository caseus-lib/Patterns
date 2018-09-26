package magical.powers;

public class Strength extends MagicalPower {

    public Strength(Integer amount) {
        super(amount);
    }

    private Strength(MagicalPower magicalPower) {
        super(magicalPower);
    }

    public Strength() {
        super();
    }

    @Override
    public MagicalPower copy() {
        return new Strength(this);
    }

    @Override
    public void printPower() {
        System.out.println("Strength - the quality or state of being strong : capacity for exertion or endurance");
        System.out.println("\t units: " + amount);
    }

    @Override
    public String getName() {
        return "Сила";
    }

    @Override
    public boolean same(MagicalPower magicalPower) {
        return magicalPower instanceof Strength;
    }
}

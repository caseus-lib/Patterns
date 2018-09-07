package magical.powers;

public class Strength extends MagicalPower {

    public Strength(Integer amount) {
        super(amount);
    }

    @Override
    public void printPower() {
        System.out.println("Strength - the quality or state of being strong : capacity for exertion or endurance");
        System.out.println("\t units: " + amount);
    }

    @Override
    public String getName() {
        return "Strength";
    }

    @Override
    public boolean same(MagicalPower magicalPower) {
        return magicalPower instanceof Strength;
    }
}

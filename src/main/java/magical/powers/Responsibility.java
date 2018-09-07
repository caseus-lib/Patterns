package magical.powers;

public class Responsibility extends MagicalPower {

    public Responsibility(Integer amount) {
        super(amount);
    }

    @Override
    public void printPower() {
        System.out.println("Responsibility - the quality or state of being responsible: " +
                "such as moral, legal, or mental accountability");
        System.out.println("\t units: " + amount);
    }

    @Override
    public String getName() {
        return "Responsibility";
    }

    @Override
    public boolean same(MagicalPower magicalPower) {
        return magicalPower instanceof Responsibility;
    }
}

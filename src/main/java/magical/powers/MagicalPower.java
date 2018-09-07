package magical.powers;

public abstract class MagicalPower implements Comparable<MagicalPower> {

    Integer amount;

    public MagicalPower(Integer amount) {
        this.amount = amount;
    }

    public abstract void printPower();

    public abstract String getName();

    public void merge(MagicalPower magicalPower){
        if (same(magicalPower))
            amount += magicalPower.amount;
    }

    @Override
    public int compareTo(MagicalPower o) {
        if (this.amount > o.amount) return 1;
        if (this.amount < o.amount) return -1;
        return 0;
    }

    public abstract boolean same(MagicalPower magicalPower);
}

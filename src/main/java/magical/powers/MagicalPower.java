package magical.powers;

import javafx.util.StringConverter;

public abstract class MagicalPower implements Comparable<MagicalPower> {

    Integer amount;

    public MagicalPower() {
    }

    public MagicalPower(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public abstract void printPower();

    public abstract String getName();

    public boolean merge(MagicalPower magicalPower){
        if (same(magicalPower)) {
            amount += magicalPower.amount;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(MagicalPower o) {
        if (this.amount > o.amount) return 1;
        if (this.amount < o.amount) return -1;
        return 0;
    }

    public abstract boolean same(MagicalPower magicalPower);
}

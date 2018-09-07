package magical.bake;

import magical.powers.MagicalPower;

public abstract class Bake {

    private MagicalPower magicalPower;
    private String name;

    Bake(MagicalPower magicalPower, String name) {
        this.magicalPower = magicalPower;
        this.name = name;
    }

    public MagicalPower getMagicalPower() {
        return magicalPower;
    }

    public void setMagicalPower(MagicalPower magicalPower) {
        this.magicalPower = magicalPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasPower(MagicalPower magicalPower) {
        return this.magicalPower.same(magicalPower);
    }
}

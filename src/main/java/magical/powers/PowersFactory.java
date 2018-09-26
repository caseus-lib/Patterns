package magical.powers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowersFactory {

    public static MagicalPower getByName(String name) {
        return getPowersList().stream()
                .filter(
                        magicalPower ->
                                name.equals(magicalPower.getName())
                )
                .findFirst()
                .orElse(new Happiness(0));
    }

    public static List<MagicalPower> getPowersList() {
        List<MagicalPower> magicalPowerList = new ArrayList<>();
        magicalPowerList.add(new Happiness(0));
        magicalPowerList.add(new Responsibility(0));
        magicalPowerList.add(new Strength(0));
        return magicalPowerList;
    }

    public static MagicalPower getRandomPower()  {
        MagicalPower magicalPower = getPowersList().stream().findAny().orElse(new Happiness());
        magicalPower.setAmount(new Random().nextInt(1000) + 1);
        return magicalPower;
    }

}

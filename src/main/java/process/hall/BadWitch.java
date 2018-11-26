package process.hall;

import process.kitchen.ExtraditionPlace;

import java.lang.reflect.Field;
import java.util.Collections;

public class BadWitch {

    private ExtraditionPlace extraditionPlace = ExtraditionPlace.getInstance();

    public void destroyAll() {
        try {
            Field f1 = extraditionPlace.getClass().getDeclaredField("readyOrderList");
            f1.setAccessible(true);
            f1.set(extraditionPlace, Collections.emptyMap());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

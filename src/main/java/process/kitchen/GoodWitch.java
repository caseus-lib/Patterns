package process.kitchen;

public class GoodWitch {

    private static GoodWitch ourInstance = new GoodWitch();

    public static GoodWitch getInstance() {
        return ourInstance;
    }

    private GoodWitch() {
    }

    private ExtraditionState extraditionState;

    public void setMemento(ExtraditionMemento extraditionMemento) {
        extraditionState = extraditionMemento.getExtraditionState();
    }

    public ExtraditionMemento createMemento(ExtraditionState state) {
        return new ExtraditionMemento(state);
    }

}

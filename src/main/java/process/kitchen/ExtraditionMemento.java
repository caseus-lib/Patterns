package process.kitchen;

public class ExtraditionMemento {

    private ExtraditionState extraditionState;

    public ExtraditionMemento() {
    }

    public ExtraditionMemento(ExtraditionState extraditionState) {
        this.extraditionState = extraditionState;
    }

    public ExtraditionState getExtraditionState() {
        return extraditionState;
    }

    public void setExtraditionState(ExtraditionState extraditionState) {
        this.extraditionState = extraditionState;
    }
}

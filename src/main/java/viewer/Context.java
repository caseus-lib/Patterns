package viewer;

import app.graphic.ui.services.Size;
import enums.ContextType;

public class Context {

    private ContextType contextType;
    private Size size;
    private Integer amount;

    public Context(ContextType contextType, Size size, Integer amount) {
        this.contextType = contextType;
        this.size = size;
        this.amount = amount;
    }

    public Context(ContextType contextType) {
        this.contextType = contextType;
    }

    public Context(ContextType contextType, Integer amount) {
        this.contextType = contextType;
        this.amount = amount;
    }

    public Context(ContextType contextType, Size size) {
        this.contextType = contextType;
        this.size = size;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public Size getSize() {
        return size;
    }

    public Integer getAmount() {
        return amount;
    }
}

package app;

import app.console.ConsoleApp;
import app.executor.Work;
import app.graphic.GraphicApp;

public class ApplicationCreator implements Application {

    @Override
    public Work createApplication() {
        if (findIfApplicationIsGraphic()) {
            return new App(new GraphicApp());
        }
        else {
            return new App(new ConsoleApp());
        }
    }

    private boolean findIfApplicationIsGraphic() {
        return(System.getProperty("graphic", "true").equals("true"));
    }

}

package app;

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

    private boolean  findIfApplicationIsGraphic() {
        return(System.getProperty("graphic", "true").equals("true"));
    }

}

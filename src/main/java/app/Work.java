package app;

public abstract class Work {

    private Worker worker;

    public Work(Worker worker) {
        this.worker = worker;
    }

    public void startApplication() {
        Initialization.init();
        this.worker.startApplication();
    }

}

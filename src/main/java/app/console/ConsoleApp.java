package app.console;

import app.executor.Worker;

public class ConsoleApp implements Worker {

    @Override
    public void startApplication() {
        new ConsoleOutput().startProcess();
    }
}

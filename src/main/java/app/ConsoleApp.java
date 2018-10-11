package app;

import console.ConsoleOutput;

public class ConsoleApp implements Worker {

    @Override
    public void startApplication() {
        new ConsoleOutput().startProcess();
    }
}

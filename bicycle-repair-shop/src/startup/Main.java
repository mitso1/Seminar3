package startup;

import controller.Controller;
import integration.Printer;
import integration.RegistryCreator;
import view.View;

/** Starts the application. */
public class Main {
    private Main() {
    }

    /**
     * Starts the application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        RegistryCreator registryCreator = new RegistryCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(registryCreator, printer);
        View view = new View(controller);

        view.sampleExecution();
    }
}

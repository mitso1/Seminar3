package test;

import integration.CustomerRegistryTest;
import model.RepairOrderTest;

/** Runs the basic test suite. */
public class TestRunner {
    private TestRunner() {
    }

    /**
     * Runs all basic tests.
     *
     * @param args The test runner does not take any command line parameters.
     */
    public static void main(String[] args) {
        int failures = 0;
        failures += new RepairOrderTest().runTests();
        failures += new CustomerRegistryTest().runTests();

        if (failures == 0) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Tests failed: " + failures);
            System.exit(1);
        }
    }
}

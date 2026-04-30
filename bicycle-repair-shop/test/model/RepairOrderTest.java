package model;

/** Basic tests for {@link RepairOrder}. */
public class RepairOrderTest {
    private int failures;

    /**
     * Runs all tests.
     *
     * @return The number of failed tests.
     */
    public int runTests() {
        testRepairOrderBecomesReadyForApproval();
        testTotalCostIsCalculatedFromAllRepairTasks();
        return failures;
    }

    private void testRepairOrderBecomesReadyForApproval() {
        RepairOrder repairOrder = createRepairOrder();
        repairOrder.setDiagnosticResult("Battery is worn.");
        repairOrder.addRepairTask("Replace battery", 120, 3000);
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, repairOrder.getState(),
                "repair order should become ready for approval");
    }

    private void testTotalCostIsCalculatedFromAllRepairTasks() {
        RepairOrder repairOrder = createRepairOrder();
        repairOrder.addRepairTask("Replace battery", 120, 3000);
        repairOrder.addRepairTask("Adjust brake sensor", 45, 450);
        assertEquals(3450, repairOrder.getTotalCost(), "total cost should include all repair tasks");
    }

    private RepairOrder createRepairOrder() {
        BikeDTO bike = new BikeDTO("TEST-1", "Test Bike", "Test Brand");
        CustomerDTO customer = new CustomerDTO("0700000000", "Test", "Customer", "test@example.com", bike);
        return new RepairOrder(customer, bike, "Test problem");
    }

    private void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            failures++;
            System.out.println("FAILED: " + message + ". Expected " + expected + " but was " + actual + ".");
        }
    }
}

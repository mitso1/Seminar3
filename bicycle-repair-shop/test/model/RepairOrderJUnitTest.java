package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** JUnit tests for {@link RepairOrder}. */
public class RepairOrderJUnitTest {
    @Test
    void repairOrderBecomesReadyForApproval() {
        RepairOrder repairOrder = createRepairOrder();
        repairOrder.setDiagnosticResult("Battery is worn.");
        repairOrder.addRepairTask("Replace battery", 120, 3000);
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, repairOrder.getState(),
                "A repair order with diagnostic report and tasks shall be ready for approval.");
    }

    @Test
    void totalCostIsCalculatedFromAllRepairTasks() {
        RepairOrder repairOrder = createRepairOrder();
        repairOrder.addRepairTask("Replace battery", 120, 3000);
        repairOrder.addRepairTask("Adjust brake sensor", 45, 450);
        assertEquals(3450, repairOrder.getTotalCost(), "Total cost shall include all repair tasks.");
    }

    private RepairOrder createRepairOrder() {
        BikeDTO bike = new BikeDTO("TEST-1", "Test Bike", "Test Brand");
        CustomerDTO customer = new CustomerDTO("0700000000", "Test", "Customer", "test@example.com", bike);
        return new RepairOrder(customer, bike, "Test problem");
    }
}

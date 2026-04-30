package integration;

import model.RepairOrder;
import model.RepairTaskDTO;

/** Handles repair order printouts. */
public class Printer {
    /** Creates a printer. */
    public Printer() {
    }

    /**
     * Prints a repair order.
     *
     * @param repairOrder The repair order to print.
     */
    public void printRepairOrder(RepairOrder repairOrder) {
        if (repairOrder == null) {
            System.out.println("No repair order to print.");
            return;
        }
        System.out.println("Name: " + repairOrder.getCustomer().getFirstName() + " "
                + repairOrder.getCustomer().getLastName());
        System.out.println("Email: " + repairOrder.getCustomer().getEmail());
        System.out.println("Phone: " + repairOrder.getCustomer().getNumber());
        System.out.println("Brand: " + repairOrder.getBike().getBrand());
        System.out.println("Model: " + repairOrder.getBike().getModel());
        System.out.println("Serial Number: " + repairOrder.getBike().getBikeSerialNo());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Problem: " + repairOrder.getProblemDesc());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Diagnostic Report: " + repairOrder.getDiagnosticReport());
        System.out.println("Repair Tasks:");
        for (RepairTaskDTO repairTask : repairOrder.getRepairTasks()) {
            System.out.println("- " + repairTask.getTaskDescription() + ", "
                    + repairTask.getEstimatedTime() + " minutes, cost " + repairTask.getCost());
        }
        System.out.println("Total Cost: " + repairOrder.getTotalCost());
        System.out.println("Estimated Completion: " + repairOrder.getEstimatedCompletionDate());
    }
}

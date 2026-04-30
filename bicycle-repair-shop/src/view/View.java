package view;

import controller.Controller;
import model.BikeDTO;
import model.CustomerDTO;
import model.RepairOrder;
import model.RepairTaskDTO;

/** Simulates the user interface with hard-coded controller calls. */
public class View {
    private final Controller controller;

    /**
     * Creates a view.
     *
     * @param controller The controller used for all operations.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Runs the basic flow.
     */
    public void sampleExecution() {
        String phoneNumber = "0701234567";
        printHeading("Search customer and bike");
        CustomerDTO customer = controller.findCustomer(phoneNumber);
        BikeDTO bike = controller.findBike(phoneNumber);
        printCustomer(customer);
        printBike(bike);

        printHeading("Create repair order");
        RepairOrder repairOrder = controller.createRepairOrder(customer, bike, "The battery loses charge quickly.");
        printRepairOrderSummary(repairOrder);

        printHeading("Technician updates repair order");
        controller.addDiagnosticReport(repairOrder.getId(), "Battery cells are worn and brake sensor needs adjustment.");
        controller.addRepairTask(repairOrder.getId(), "Replace battery pack", 180, 3200);
        controller.addRepairTask(repairOrder.getId(), "Adjust brake sensor", 45, 450);
        printRepairOrderSummary(repairOrder);

        printHeading("Customer accepts repair order");
        System.out.println("Total cost: " + repairOrder.getTotalCost());
        controller.acceptRepairOrder(repairOrder.getId());
        printRepairOrderSummary(repairOrder);

        printHeading("Printed repair order");
        controller.printRepairOrder(repairOrder.getId());
    }

    private void printHeading(String heading) {
        System.out.println();
        System.out.println(heading);
    }

    private void printCustomer(CustomerDTO customer) {
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Phone: " + customer.getNumber());
        System.out.println("Email: " + customer.getEmail());
    }

    private void printBike(BikeDTO bike) {
        System.out.println("Bike: " + bike.getBrand() + " " + bike.getModel());
        System.out.println("Serial number: " + bike.getBikeSerialNo());
    }

    private void printRepairOrderSummary(RepairOrder repairOrder) {
        System.out.println("Repair order id: " + repairOrder.getId());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Problem: " + repairOrder.getProblemDesc());
        System.out.println("Diagnostic report: " + repairOrder.getDiagnosticReport());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Tasks:");
        for (RepairTaskDTO repairTask : repairOrder.getRepairTasks()) {
            System.out.println("- " + repairTask.getTaskDescription() + ", " + repairTask.getEstimatedTime()
                    + " minutes, cost " + repairTask.getCost());
        }
        System.out.println("Estimated completion: " + repairOrder.getEstimatedCompletionDate());
    }
}

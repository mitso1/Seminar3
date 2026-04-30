package controller;

import java.util.ArrayList;
import java.util.UUID;

import integration.CustomerRegistry;
import integration.Printer;
import integration.RegistryCreator;
import integration.RepairOrderRegistry;
import model.BikeDTO;
import model.CustomerDTO;
import model.RepairOrder;
import model.RepairOrderState;

/** Coordinates calls between the view and the lower layers. */
public class Controller {
    private final Printer printer;
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;

    /** Creates a controller with default registries and printer. */
    public Controller() {
        this(new RegistryCreator(), new Printer());
    }

    /**
     * Creates a controller.
     *
     * @param registryCreator Provides the registries.
     * @param printer Prints repair orders.
     */
    public Controller(RegistryCreator registryCreator, Printer printer) {
        RegistryCreator safeRegistryCreator = registryCreator == null ? new RegistryCreator() : registryCreator;
        this.printer = printer == null ? new Printer() : printer;
        this.customerRegistry = safeRegistryCreator.getCustomerRegistry();
        this.repairOrderRegistry = safeRegistryCreator.getRepairOrderRegistry();
    }

    /**
     * Finds a customer.
     *
     * @param phoneNumber The phone number to search for.
     * @return The matching customer, or <code>null</code> if none was found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        return customerRegistry.findCustomer(phoneNumber);
    }

    /**
     * Finds a customer's bike.
     *
     * @param phoneNumber The phone number to search for.
     * @return The customer's bike, or <code>null</code> if no customer was found.
     */
    public BikeDTO findBike(String phoneNumber) {
        CustomerDTO customer = findCustomer(phoneNumber);
        return customer == null ? null : customer.getBike();
    }

    /**
     * Adds a customer.
     *
     * @param phoneNumber The customer's phone number.
     * @param firstName The customer's first name.
     * @param lastName The customer's last name.
     * @param email The customer's email address.
     * @return The added customer.
     */
    public CustomerDTO addCustomer(String phoneNumber, String firstName, String lastName, String email) {
        CustomerDTO customer = new CustomerDTO(phoneNumber, firstName, lastName, email, null);
        customerRegistry.addCustomer(customer);
        return customer;
    }

    /**
     * Creates a repair order.
     *
     * @param customer The customer who owns the bike.
     * @param bike The bike to repair.
     * @param problemDescription The customer's description of the problem.
     * @return The created repair order.
     */
    public RepairOrder createRepairOrder(CustomerDTO customer, BikeDTO bike, String problemDescription) {
        RepairOrder repairOrder = new RepairOrder(customer, bike, problemDescription);
        repairOrderRegistry.saveRepairOrder(repairOrder);
        return repairOrder;
    }

    /**
     * Adds a diagnostic report.
     *
     * @param repairOrderId The id of the repair order.
     * @param report The diagnostic report.
     */
    public void addDiagnosticReport(UUID repairOrderId, String report) {
        RepairOrder repairOrder = findExistingRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.setDiagnosticResult(report);
            repairOrderRegistry.updateRepairOrder(repairOrder);
        }
    }

    /**
     * Adds a repair task.
     *
     * @param repairOrderId The id of the repair order.
     * @param description The task description.
     * @param estimatedTime The estimated time in minutes.
     * @param cost The task cost.
     */
    public void addRepairTask(UUID repairOrderId, String description, int estimatedTime, int cost) {
        RepairOrder repairOrder = findExistingRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addRepairTask(description, estimatedTime, cost);
            repairOrderRegistry.updateRepairOrder(repairOrder);
        }
    }

    /**
     * Accepts a repair order.
     *
     * @param repairOrderId The id of the repair order.
     */
    public void acceptRepairOrder(UUID repairOrderId) {
        changeRepairOrderState(repairOrderId, RepairOrderState.ACCEPTED);
    }

    /**
     * Rejects a repair order.
     *
     * @param repairOrderId The id of the repair order.
     */
    public void rejectRepairOrder(UUID repairOrderId) {
        changeRepairOrderState(repairOrderId, RepairOrderState.REJECTED);
    }

    /**
     * Starts work on a repair order.
     *
     * @param repairOrderId The id of the repair order.
     */
    public void startRepair(UUID repairOrderId) {
        changeRepairOrderState(repairOrderId, RepairOrderState.ACCEPTED);
    }

    /**
     * Completes a repair order.
     *
     * @param repairOrderId The id of the repair order.
     */
    public void completeRepair(UUID repairOrderId) {
        changeRepairOrderState(repairOrderId, RepairOrderState.COMPLETED);
    }

    /**
     * Lists all repair orders.
     *
     * @return All repair orders.
     */
    public ArrayList<RepairOrder> getAllRepairOrders() {
        return repairOrderRegistry.getAllRepairOrders();
    }

    /**
     * Prints a repair order.
     *
     * @param repairOrderId The id of the repair order.
     */
    public void printRepairOrder(UUID repairOrderId) {
        printer.printRepairOrder(findExistingRepairOrder(repairOrderId));
    }

    private void changeRepairOrderState(UUID repairOrderId, RepairOrderState newState) {
        RepairOrder repairOrder = findExistingRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.changeState(newState);
            repairOrderRegistry.updateRepairOrder(repairOrder);
        }
    }

    private RepairOrder findExistingRepairOrder(UUID repairOrderId) {
        return repairOrderId == null ? null : repairOrderRegistry.findRepairOrder(repairOrderId);
    }
}

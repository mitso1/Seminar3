package integration;

import java.util.ArrayList;
import java.util.UUID;

import model.CustomerDTO;
import model.RepairOrder;
import model.RepairOrderState;

/** Stores and finds repair orders. */
public class RepairOrderRegistry {
    private final ArrayList<RepairOrder> repairOrders;

    /**
     * Creates an empty registry.
     */
    public RepairOrderRegistry() {
        this.repairOrders = new ArrayList<RepairOrder>();
    }

    /**
     * Creates a registry with the specified repair orders.
     *
     * @param repairOrders The repair orders to store.
     */
    public RepairOrderRegistry(ArrayList<RepairOrder> repairOrders) {
        this.repairOrders = repairOrders == null ? new ArrayList<RepairOrder>() : repairOrders;
    }

    /**
     * Saves a repair order.
     *
     * @param repairOrder The repair order to save.
     */
    public void saveRepairOrder(RepairOrder repairOrder) {
        if (repairOrder != null) {
            repairOrders.add(repairOrder);
        }
    }

    /**
     * Finds a repair order.
     *
     * @param repairOrderId The repair order id to search for.
     * @return The matching repair order, or <code>null</code> if none was found.
     */
    public RepairOrder findRepairOrder(UUID repairOrderId) {
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getId().equals(repairOrderId)) {
                return repairOrder;
            }
        }
        return null;
    }

    /**
     * Updates a repair order.
     *
     * @param updatedRepairOrder The repair order to update.
     */
    public void updateRepairOrder(RepairOrder updatedRepairOrder) {
        if (updatedRepairOrder == null) {
            return;
        }
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId().equals(updatedRepairOrder.getId())) {
                repairOrders.set(i, updatedRepairOrder);
                return;
            }
        }
        saveRepairOrder(updatedRepairOrder);
    }

    /**
     * Lists all repair orders.
     *
     * @return All repair orders in this registry.
     */
    public ArrayList<RepairOrder> retrieveAllRepairOrders() {
        return getAllRepairOrders();
    }

    /**
     * Lists all repair orders.
     *
     * @return All repair orders in this registry.
     */
    public ArrayList<RepairOrder> getAllRepairOrders() {
        return new ArrayList<RepairOrder>(repairOrders);
    }

    /**
     * Finds repair orders for a customer.
     *
     * @param customer The customer whose repair orders shall be found.
     * @return All repair orders belonging to the specified customer.
     */
    public ArrayList<RepairOrder> findRepairOrdersByCustomer(CustomerDTO customer) {
        ArrayList<RepairOrder> foundOrders = new ArrayList<>();
        for (RepairOrder order : repairOrders) {
            if (order.getCustomer().getNumber().equalsIgnoreCase(customer.getNumber())) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    /**
     * Finds repair orders by customer phone number.
     *
     * @param number The customer phone number.
     * @return All repair orders belonging to the specified customer.
     */
    public ArrayList<RepairOrder> findRepairOrdersByNumber(String number) {
        ArrayList<RepairOrder> foundOrders = new ArrayList<>();
        for (RepairOrder order : repairOrders) {
            if (order.getCustomer().getNumber().equalsIgnoreCase(number)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    /**
     * Finds repair orders by bike serial number.
     *
     * @param bikeSerialNo The bike serial number.
     * @return All repair orders for the specified bike.
     */
    public ArrayList<RepairOrder> findRepairOrdersBySerial(String bikeSerialNo) {
        ArrayList<RepairOrder> foundOrders = new ArrayList<>();
        for (RepairOrder order : repairOrders) {
            if (order.getBike().getBikeSerialNo().equalsIgnoreCase(bikeSerialNo)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    /**
     * Changes the first repair order found for a phone number.
     *
     * @param phoneNumber The customer phone number.
     * @param newState The new repair order state.
     */
    public void changeRepairOrderState(String phoneNumber, RepairOrderState newState) {
        RepairOrder repairOrder = findFirstRepairOrderByPhoneNumber(phoneNumber);
        repairOrder.changeState(newState);
    }

    /**
     * Sets the diagnostic report of the first order found for a phone number.
     *
     * @param phoneNumber The customer phone number.
     * @param diagnosticReport The diagnostic report.
     */
    public void setRepairOrderDiagnostic(String phoneNumber, String diagnosticReport) {
        RepairOrder repairOrder = findFirstRepairOrderByPhoneNumber(phoneNumber);
        repairOrder.setDiagnosticResult(diagnosticReport);
    }

    /**
     * Adds a task to the first order found for a phone number.
     *
     * @param phoneNumber The customer phone number.
     * @param estimatedTime The estimated time in minutes.
     * @param description The task description.
     * @param cost The task cost.
     */
    public void addRepairTask(String phoneNumber, int estimatedTime, String description, int cost) {
        RepairOrder repairOrder = findFirstRepairOrderByPhoneNumber(phoneNumber);
        repairOrder.addRepairTask(description, estimatedTime, cost);
    }

    private RepairOrder findFirstRepairOrderByPhoneNumber(String phoneNumber) {
        ArrayList<RepairOrder> foundOrders = findRepairOrdersByNumber(phoneNumber);
        if (foundOrders.isEmpty()) {
            return null;
        }
        return foundOrders.get(0);
    }
}

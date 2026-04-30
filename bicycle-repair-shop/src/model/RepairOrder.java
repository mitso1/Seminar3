package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/** A repair order for a customer's bike. */
public class RepairOrder {
    private final UUID id;
    private RepairOrderState state;
    private final BikeDTO bike;
    private final CustomerDTO customer;
    private final String problemDescription;
    private final LocalDate date;
    private final ArrayList<RepairTaskDTO> repairTasks;
    private String diagnosticReport;

    /**
     * Creates a repair order.
     *
     * @param customer The customer who owns the bike.
     * @param bike The bike to repair.
     * @param problemDescription The customer's description of the problem.
     */
    public RepairOrder(CustomerDTO customer, BikeDTO bike, String problemDescription) {
        this(UUID.randomUUID(), "", bike, customer, problemDescription, LocalDate.now(),
                new ArrayList<RepairTaskDTO>(), RepairOrderState.NEWLY_CREATED);
    }

    /**
     * Creates a repair order with all fields specified.
     *
     * @param id The repair order id.
     * @param diagnosticReport The diagnostic report.
     * @param bike The bike to repair.
     * @param customer The customer who owns the bike.
     * @param problemDescription The customer's description of the problem.
     * @param date The date when the repair order was created.
     * @param repairTasks The repair tasks belonging to the order.
     * @param state The repair order state.
     */
    public RepairOrder(UUID id, String diagnosticReport, BikeDTO bike, CustomerDTO customer, String problemDescription,
            LocalDate date, ArrayList<RepairTaskDTO> repairTasks, RepairOrderState state) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.diagnosticReport = diagnosticReport == null ? "" : diagnosticReport;
        this.bike = bike;
        this.customer = customer;
        this.problemDescription = textOrEmpty(problemDescription);
        this.date = date == null ? LocalDate.now() : date;
        this.repairTasks = repairTasks == null ? new ArrayList<RepairTaskDTO>() : repairTasks;
        this.state = state == null ? RepairOrderState.NEWLY_CREATED : state;
    }

    /**
     * Returns the state.
     *
     * @return The repair order state.
     */
    public RepairOrderState getState() {
        return state;
    }

    /**
     * Returns the diagnostic report.
     *
     * @return The diagnostic report.
     */
    public String getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * Returns the bike.
     *
     * @return The bike to repair.
     */
    public BikeDTO getBike() {
        return bike;
    }

    /**
     * Returns the customer.
     *
     * @return The customer who owns the bike.
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * Returns the problem description.
     *
     * @return The customer's description of the problem.
     */
    public String getProblemDesc() {
        return problemDescription;
    }

    /**
     * Returns the creation date.
     *
     * @return The date when this repair order was created.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the repair tasks.
     *
     * @return The repair tasks belonging to this order.
     */
    public ArrayList<RepairTaskDTO> getRepairTasks() {
        return new ArrayList<RepairTaskDTO>(repairTasks);
    }

    /**
     * Returns the id.
     *
     * @return The repair order id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the total task cost.
     *
     * @return The total cost.
     */
    public int getTotalCost() {
        int totalCost = 0;
        for (RepairTaskDTO repairTask : repairTasks) {
            totalCost += repairTask.getCost();
        }
        return totalCost;
    }

    /**
     * Returns the estimated completion date.
     *
     * @return The estimated completion date.
     */
    public LocalDate getEstimatedCompletionDate() {
        int totalEstimatedMinutes = 0;
        for (RepairTaskDTO repairTask : repairTasks) {
            totalEstimatedMinutes += repairTask.getEstimatedTime();
        }
        int minutesPerWorkDay = 8 * 60;
        int estimatedWorkDays = Math.max(1, (totalEstimatedMinutes + minutesPerWorkDay - 1) / minutesPerWorkDay);
        return date.plusDays(estimatedWorkDays);
    }

    /**
     * Marks this order as accepted.
     */
    public void accept() {
        state = RepairOrderState.ACCEPTED;
    }

    /**
     * Marks this order as rejected.
     */
    public void reject() {
        state = RepairOrderState.REJECTED;
    }

    /**
     * Adds a diagnostic result.
     *
     * @param diagnosticResult The diagnostic result.
     */
    public void setDiagnosticResult(String diagnosticResult) {
        diagnosticReport = textOrEmpty(diagnosticResult);
        updateApprovalState();
    }

    /**
     * Adds a repair task.
     *
     * @param taskDescription The task description.
     * @param estimatedTime The estimated time in minutes.
     * @param cost The task cost.
     */
    public void addRepairTask(String taskDescription, int estimatedTime, int cost) {
        repairTasks.add(new RepairTaskDTO(taskDescription, estimatedTime, UUID.randomUUID(), cost));
        updateApprovalState();
    }

    /**
     * Changes the state.
     *
     * @param newState The new repair order state.
     */
    public void changeState(RepairOrderState newState) {
        if (newState != null) {
            state = newState;
        }
    }

    private void updateApprovalState() {
        if (!diagnosticReport.isEmpty() && !repairTasks.isEmpty() && state == RepairOrderState.NEWLY_CREATED) {
            state = RepairOrderState.READY_FOR_APPROVAL;
        }
    }

    private String textOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }
}

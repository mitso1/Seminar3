package model;

import java.util.UUID;

/** Information about a repair task. */
public final class RepairTaskDTO {
    private final String taskDescription;
    private final int estimatedTime;
    private final UUID taskId;
    private final int cost;

    /**
     * Creates repair task information.
     *
     * @param taskDescription The task description.
     * @param estimatedTime The estimated time in minutes.
     * @param taskId The task id. A new id is generated if this is <code>null</code>.
     * @param cost The task cost.
     */
    public RepairTaskDTO(String taskDescription, int estimatedTime, UUID taskId, int cost) {
        this.taskDescription = textOrEmpty(taskDescription);
        this.estimatedTime = nonNegativeOrZero(estimatedTime);
        this.taskId = taskId == null ? UUID.randomUUID() : taskId;
        this.cost = nonNegativeOrZero(cost);
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the estimated time.
     *
     * @return The estimated time in minutes.
     */
    public int getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Returns the task id.
     *
     * @return The task id.
     */
    public UUID getTaskId() {
        return taskId;
    }

    /**
     * Returns the task cost.
     *
     * @return The task cost.
     */
    public int getCost() {
        return cost;
    }

    private String textOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }

    private int nonNegativeOrZero(int value) {
        return Math.max(value, 0);
    }
}

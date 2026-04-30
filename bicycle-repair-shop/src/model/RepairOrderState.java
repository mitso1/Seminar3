package model;

/** States a repair order can have. */
public enum RepairOrderState {
    /** Created, without diagnostic report or repair tasks. */
    NEWLY_CREATED,
    /** Ready for the customer to accept or reject. */
    READY_FOR_APPROVAL,
    /** Accepted by the customer. */
    ACCEPTED,
    /** Rejected by the customer. */
    REJECTED,
    /** Repair work is completed. */
    COMPLETED,
    /** The customer has payed. */
    PAYED
}

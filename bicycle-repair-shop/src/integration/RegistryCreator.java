package integration;

/** Creates and provides the registries. */
public class RegistryCreator {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;

    /**
     * Creates default registries.
     */
    public RegistryCreator() {
        this.customerRegistry = new CustomerRegistry();
        this.repairOrderRegistry = new RepairOrderRegistry();
    }

    /**
     * Creates a registry provider.
     *
     * @param customerRegistry The customer registry.
     * @param repairOrderRegistry The repair order registry.
     */
    public RegistryCreator(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry) {
        this.customerRegistry = customerRegistry == null ? new CustomerRegistry() : customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry == null ? new RepairOrderRegistry() : repairOrderRegistry;
    }

    /**
     * Returns the customer registry.
     *
     * @return The customer registry.
     */
    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    /**
     * Returns the repair order registry.
     *
     * @return The repair order registry.
     */
    public RepairOrderRegistry getRepairOrderRegistry() {
        return repairOrderRegistry;
    }

}

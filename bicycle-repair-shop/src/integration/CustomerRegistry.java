package integration;

import java.util.ArrayList;

import model.BikeDTO;
import model.CustomerDTO;

/** Stores and finds customers. */
public class CustomerRegistry {
    private final ArrayList<CustomerDTO> customers;

    /**
     * Creates a registry with example customers.
     */
    public CustomerRegistry() {
        this.customers = new ArrayList<CustomerDTO>();
        addExampleCustomers();
    }

    /**
     * Creates a registry with the specified customers.
     *
     * @param customers The customers to store.
     */
    public CustomerRegistry(ArrayList<CustomerDTO> customers) {
        this.customers = customers == null ? new ArrayList<CustomerDTO>() : customers;
    }

    /**
     * Adds a customer.
     *
     * @param customer The customer to add.
     */
    public void addCustomer(CustomerDTO customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    /**
     * Finds a customer.
     *
     * @param phoneNumber The phone number to search for.
     * @return The matching customer, or <code>null</code> if none was found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        for (CustomerDTO customer : customers) {
            if (customer.getNumber().equalsIgnoreCase(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Lists all customers.
     *
     * @return All customers in this registry.
     */
    public ArrayList<CustomerDTO> getAllCustomers() {
        return new ArrayList<CustomerDTO>(customers);
    }

    private void addExampleCustomers() {
        customers.add(new CustomerDTO("0701234567", "Alex", "Lind", "alex@example.com",
                new BikeDTO("EB-1001", "Turbo Vado", "Specialized")));
        customers.add(new CustomerDTO("0707654321", "Sam", "Berg", "sam@example.com",
                new BikeDTO("EB-2002", "Allant+ 7", "Trek")));
    }
}

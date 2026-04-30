package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.CustomerDTO;

/** JUnit tests for {@link CustomerRegistry}. */
public class CustomerRegistryJUnitTest {
    @Test
    void findsExistingCustomerByPhoneNumber() {
        CustomerRegistry registry = new CustomerRegistry();
        CustomerDTO customer = registry.findCustomer("0701234567");
        assertEquals("Alex", customer.getFirstName(), "The registry shall find existing customers.");
    }

    @Test
    void existingCustomerContainsBikeDetails() {
        CustomerRegistry registry = new CustomerRegistry();
        CustomerDTO customer = registry.findCustomer("0701234567");
        assertEquals("EB-1001", customer.getBike().getBikeSerialNo(),
                "The customer registry shall contain bike details.");
    }
}

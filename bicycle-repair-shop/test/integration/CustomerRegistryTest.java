package integration;

import model.CustomerDTO;

/** Basic tests for {@link CustomerRegistry}. */
public class CustomerRegistryTest {
    private int failures;

    /**
     * Runs all tests.
     *
     * @return The number of failed tests.
     */
    public int runTests() {
        testFindsExistingCustomerByPhoneNumber();
        testExistingCustomerContainsBikeDetails();
        return failures;
    }

    private void testFindsExistingCustomerByPhoneNumber() {
        CustomerRegistry registry = new CustomerRegistry();
        CustomerDTO customer = registry.findCustomer("0701234567");
        assertEquals("Alex", customer.getFirstName(), "registry should find existing customer");
    }

    private void testExistingCustomerContainsBikeDetails() {
        CustomerRegistry registry = new CustomerRegistry();
        CustomerDTO customer = registry.findCustomer("0701234567");
        assertEquals("EB-1001", customer.getBike().getBikeSerialNo(), "customer registry should contain bike details");
    }

    private void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            failures++;
            System.out.println("FAILED: " + message + ". Expected " + expected + " but was " + actual + ".");
        }
    }
}

package model;

/** Information about a customer. */
public final class CustomerDTO {
    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final BikeDTO bike;

    /**
     * Creates customer information.
     *
     * @param phoneNumber The customer's phone number.
     * @param firstName The customer's first name.
     * @param lastName The customer's last name.
     * @param email The customer's email address.
     * @param bike The customer's bike.
     */
    public CustomerDTO(String phoneNumber, String firstName, String lastName, String email, BikeDTO bike) {
        this.phoneNumber = textOrEmpty(phoneNumber);
        this.firstName = textOrEmpty(firstName);
        this.lastName = textOrEmpty(lastName);
        this.email = textOrEmpty(email);
        this.bike = bike;
    }

    /**
     * Returns the phone number.
     *
     * @return The customer's phone number.
     */
    public String getNumber() {
        return phoneNumber;
    }

    /**
     * Returns the first name.
     *
     * @return The customer's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name.
     *
     * @return The customer's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the email address.
     *
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer's bike.
     *
     * @return The customer's bike.
     */
    public BikeDTO getBike() {
        return bike;
    }

    private String textOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }
}

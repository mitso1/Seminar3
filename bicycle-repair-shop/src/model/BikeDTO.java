package model;

/** Information about a bike. */
public final class BikeDTO {
    private final String bikeSerialNumber;
    private final String model;
    private final String brand;

    /**
     * Creates bike information.
     *
     * @param bikeSerialNumber The bike serial number.
     * @param model The bike model.
     * @param brand The bike brand.
     */
    public BikeDTO(String bikeSerialNumber, String model, String brand) {
        this.bikeSerialNumber = textOrEmpty(bikeSerialNumber);
        this.model = textOrEmpty(model);
        this.brand = textOrEmpty(brand);
    }

    /**
     * Returns the serial number.
     *
     * @return The bike serial number.
     */
    public String getBikeSerialNo() {
        return bikeSerialNumber;
    }

    /**
     * Returns the model.
     *
     * @return The bike model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the brand.
     *
     * @return The bike brand.
     */
    public String getBrand() {
        return brand;
    }

    private String textOrEmpty(String value) {
        return value == null ? "" : value.trim();
    }
}

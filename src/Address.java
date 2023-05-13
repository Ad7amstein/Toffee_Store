/**
 * Address class represents a physical address with various components
 * 
 * @author Omar Abdullah Abdalhaleem
 */
public class Address {
    // private instance variables
    private String government;
    private String district;
    private String street;
    private String buildingNo;
    private String floor;
    private String flat;
    private String landmark;

    /**
     * Sets the address components
     *
     * @param government The name of the government in charge of the location
     * @param district   The name of the district or area within the government's
     *                   jurisdiction
     * @param street     The name of the street where the building is located
     * @param buildingNo The number of the building on the street
     * @param floor      The floor number of the building where the address is
     *                   located
     * @param flat       The number of the flat or apartment within the building
     * @param landmark   A nearby landmark or point of reference to help locate the
     *                   address
     */
    public Address(String government, String district, String street, String buildingNo, String floor,
            String flat, String landmark) {
        this.government = government;
        this.district = district;
        this.street = street;
        this.buildingNo = buildingNo;
        this.floor = floor;
        this.flat = flat;
        this.landmark = landmark;
    }
    /**
     * Sets the address components
     *
     * @param government The name of the government in charge of the location
     * @param district   The name of the district or area within the government's
     *                   jurisdiction
     * @param street     The name of the street where the building is located
     * @param buildingNo The number of the building on the street
     * @param floor      The floor number of the building where the address is
     *                   located
     * @param flat       The number of the flat or apartment within the building
     * @param landmark   A nearby landmark or point of reference to help locate the
     *                   address
     */
    public void setAddress(String government, String district, String street, String buildingNo, String floor,
            String flat, String landmark) {
        this.government = government;
        this.district = district;
        this.street = street;
        this.buildingNo = buildingNo;
        this.floor = floor;
        this.flat = flat;
        this.landmark = landmark;
    }

    /**
     * Constructs an Address object from a string in the format:
     * government-district-street-buildingNo-floor-flat-landmark
     *
     * @param address The string representing the address
     */
    public Address(String address) {
        String[] values = address.split("-");

        String val1 = values[0];
        String val2 = values[1];
        String val3 = values[2];
        String val4 = values[3];
        String val5 = values[4];
        String val6 = values[5];
        String val7 = values[6];

        this.government = val1;
        this.district = val2;
        this.street = val3;
        this.buildingNo = val4;
        this.floor = val5;
        this.flat = val6;
        this.landmark = val7;
    }

    /**
     * Returns the complete address as a string
     *
     * @return the complete address as a string
     */
    public String getAddress() {
        String address = government + "-" + district + "-" + street + "-" + buildingNo + "-" + floor + "-" + flat + "-"
                + landmark;
        return address;
    }
}

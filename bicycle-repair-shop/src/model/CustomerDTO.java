package model;

public class CustomerDTO {
    String phoneNumber;
    String firstName;
    String lastName;


    public CustomerDTO(String phoneNumber, String firstName, String lastName){
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getNumber(){
        return phoneNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
}

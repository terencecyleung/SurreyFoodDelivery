package t27.surreyfooddeliveryapp;

public class Customer {
    private String email;
    private String password;
    private String name;
    private String number;
    private String address;

    public Customer(){}

    public Customer(String email, String password,String name, String number, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.number = number;
        this.address = address;
    }
}

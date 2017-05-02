package t27.surreyfooddeliveryapp.objectstodb;

import t27.surreyfooddeliveryapp.objectstodb.Customer;

public class RestaurantCustomer extends Customer {

    public RestaurantCustomer(String email, String password,String name, String number, String address) {
        super(email, password, name, number, address);
    }
}

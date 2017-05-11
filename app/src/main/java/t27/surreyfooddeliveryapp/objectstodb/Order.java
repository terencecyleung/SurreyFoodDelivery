package t27.surreyfooddeliveryapp.objectstodb;

/**
 * Created by Kent on 2017-05-10.
 */


/*
* used for customer and restaurant order.
* constructor is only responsible for initializing the necessary attributes in an order.
* when instantiate the object, use setters to initialize the additional attributes that come along with the order
*
*
*
* */
public class Order {
    //necessary
    private String notification_token;
    private String orderType;
    private String drop_cust_name;

    private String drop_phone;
    private String drop_address;
    private String order_detail;
    private String payment_method;
    private String state;

    //customer additional
    private String dropoff_address_detail;
    private String dropoff_email;

    //restaurant additional
    private String cust_total;
    private String rest_name;
    private String rest_phone;
    private String rest_email;
    private String rest_address;
    private String rest_ready_min;

    public Order(){}

    public Order(    String notification_token,
                     String orderType,
                     String drop_cust_name,
                     String drop_phone,
                     String drop_address,
                     String order_detail,
                     String payment_method,
                     String state) {
        this.notification_token = notification_token;
        this.orderType=  orderType;
        this.drop_cust_name = drop_cust_name;
        this.drop_phone = drop_phone;
        this.drop_address = drop_address;
        this.order_detail = order_detail;
        this.payment_method = payment_method;
        this.state = state;
    }

    public String getNotification_token() {
        return notification_token;
    }

    public void setNotification_token(String notification_token) {
        this.notification_token = notification_token;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDrop_cust_name() {
        return drop_cust_name;
    }

    public void setDrop_cust_name(String drop_cust_name) {
        this.drop_cust_name = drop_cust_name;
    }

    public String getDrop_phone() {
        return drop_phone;
    }

    public void setDrop_phone(String drop_phone) {
        this.drop_phone = drop_phone;
    }

    public String getDrop_address() {
        return drop_address;
    }

    public void setDrop_address(String drop_address) {
        this.drop_address = drop_address;
    }

    public String getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(String order_detail) {
        this.order_detail = order_detail;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDropoff_address_detail() {
        return dropoff_address_detail;
    }

    public void setDropoff_address_detail(String dropoff_address_detail) {
        this.dropoff_address_detail = dropoff_address_detail;
    }

    public String getDropoff_email() {
        return dropoff_email;
    }

    public void setDropoff_email(String dropoff_email) {
        this.dropoff_email = dropoff_email;
    }

    public String getCust_total() {
        return cust_total;
    }

    public void setCust_total(String cust_total) {
        this.cust_total = cust_total;
    }

    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    public String getRest_phone() {
        return rest_phone;
    }

    public void setRest_phone(String rest_phone) {
        this.rest_phone = rest_phone;
    }

    public String getRest_email() {
        return rest_email;
    }

    public void setRest_email(String rest_email) {
        this.rest_email = rest_email;
    }

    public String getRest_address() {
        return rest_address;
    }

    public void setRest_address(String rest_address) {
        this.rest_address = rest_address;
    }

    public String getRest_ready_min() {
        return rest_ready_min;
    }

    public void setRest_ready_min(String rest_ready_min) {
        this.rest_ready_min = rest_ready_min;
    }












}

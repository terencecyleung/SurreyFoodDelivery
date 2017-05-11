package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import t27.surreyfooddeliveryapp.objectstodb.Customer;
import t27.surreyfooddeliveryapp.objectstodb.Order;

import com.google.firebase.iid.FirebaseInstanceId;

public class CustomerOrderActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    SharedPreferences shared_preference;
    //input fields
    private String name;
    private String phone;
    private String email;
    private String address;
    private String address_detail;
    private String order_detail;
    private String preferred_payment_method;

    //xml input fields
    private EditText name_et;
    private EditText phone_et;
    private EditText email_et;
    private EditText address_et;
    private EditText address_detail_et;
    private EditText order_detail_et;
    private RadioGroup preferred_payment_method_RadioGroup;
    private RadioButton preferred_payment_method_RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        //get EditTexts from xml
        name_et = (EditText)findViewById(R.id.cust_order_name_edittext);
        phone_et = (EditText)findViewById(R.id.cust_order_phone_edittext);
        email_et = (EditText)findViewById(R.id.cust_order_email_edittext);
        address_et = (EditText)findViewById(R.id.cust_order_address_edittext);
        address_detail_et = (EditText)findViewById(R.id.cust_order_address_detail_edittext);
        order_detail_et = (EditText)findViewById(R.id.cust_order_detail_edittext);
        preferred_payment_method_RadioGroup = (RadioGroup) findViewById(R.id.preferred_payment_radioGroup);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get back the customer object
        shared_preference = getApplicationContext().getSharedPreferences(getString(R.string.User_info), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shared_preference.getString("userObject", null);
        if (json != null) {
            Customer cur_customer = gson.fromJson(json, Customer.class);
            String cur_name = cur_customer.getName();
            String cur_address = cur_customer.getAddress();
            String cur_address_detail = cur_customer.getAddressDetail();
            String cur_email = cur_customer.getEmail();
            String cur_phone = cur_customer.getNumber();

            name_et.setText(cur_name);
            phone_et.setText(cur_phone);
            email_et.setText(cur_email);
            address_et.setText(cur_address);
            address_detail_et.setText(cur_address_detail);
        }
    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this,HomeActivity.class);
        startActivity(newIntent);
    }

    public void cust_placeOrder_click(View view) {
        //get input strings from EditTexts
        name = name_et.getText().toString();
        phone = phone_et.getText().toString();
        email = email_et.getText().toString();
        address = address_et.getText().toString();
        address_detail = address_detail_et.getText().toString();
        order_detail = order_detail_et.getText().toString();
        preferred_payment_method_RadioButton = (RadioButton) findViewById(preferred_payment_method_RadioGroup.getCheckedRadioButtonId());
        preferred_payment_method = preferred_payment_method_RadioButton.getText().toString();


        //input validations
        if (!InputValidation.isValidName(name)) {
            name_et.setError("invalid name");
            Toast.makeText(CustomerOrderActivity.this, "Please enter a valid name",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(!InputValidation.isValidPhoneNumber(phone)){
            phone_et.setError("invalid phone number");
            Toast.makeText(CustomerOrderActivity.this, "Please enter a valid phone number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(!InputValidation.isValidAddress(address)){
            address_et.setError("invalid address");
            Toast.makeText(CustomerOrderActivity.this, "Please enter a valid address",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(!InputValidation.isValidOrderDetail(order_detail)){
            order_detail_et.setError("invalid order detail");
            Toast.makeText(CustomerOrderActivity.this, "Please enter order details",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations passed, display a success toast
        Toast.makeText(CustomerOrderActivity.this, "Order successfully placed.",
                Toast.LENGTH_SHORT).show();

        //get the token for notification to store in the order in db
        SharedPreferences tokenPre = getApplicationContext().getSharedPreferences("notifToken",Context.MODE_PRIVATE);
        String token= tokenPre.getString("token",null);
        //Log.d("tokenM",token);
        //place order
        placeOrder(token,
                name,
                phone,
                email,
                address,
                address_detail,
                order_detail,
                preferred_payment_method);

        //go to currentOrders activity
        Intent from_cust_order_to_current_order = new Intent(this, CurrentOrderActivity.class);
        from_cust_order_to_current_order.putExtra("caller_activity", "CustomerOrderActivity");
        startActivity(from_cust_order_to_current_order);

    }


    //TODO send order info to dispatcher/db
    private void placeOrder(String token,
            String name,
            String phone,
            String email,
            String address,
            String address_detail,
            String order_detail,
            String preferred_payment_method){

        Order newOrder = new Order(token,
                                    "customer",
                                    name,
                                    phone,
                                    address,
                                    order_detail,
                                    preferred_payment_method,
                                    "pending");

        mDatabaseRef.child("order").push().setValue(newOrder, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Order could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Order successfully.");
                }
            }
        });

        /*String notification_token,
        String orderType,
        String drop_cust_name,
        String drop_phone,
        String drop_address,
        String order_detail,
        String payment_method,
        String state*/



    }
}

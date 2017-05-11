package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CustomerOrderActivity extends AppCompatActivity {

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

        //get EditTexts from xml
        name_et = (EditText)findViewById(R.id.cust_order_name_edittext);
        phone_et = (EditText)findViewById(R.id.cust_order_phone_edittext);
        email_et = (EditText)findViewById(R.id.cust_order_email_edittext);
        address_et = (EditText)findViewById(R.id.cust_order_address_edittext);
        address_detail_et = (EditText)findViewById(R.id.cust_order_address_detail_edittext);
        order_detail_et = (EditText)findViewById(R.id.cust_order_detail_edittext);
        preferred_payment_method_RadioGroup = (RadioGroup) findViewById(R.id.preferred_payment_radioGroup);
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

        //place order
        placeOrder();

        //go to currentOrders activity
        Intent from_cust_order_to_current_order = new Intent(this, CurrentOrderActivity.class);
        from_cust_order_to_current_order.putExtra("caller_activity", "CustomerOrderActivity");
        startActivity(from_cust_order_to_current_order);

    }


    //TODO send order info to dispatcher/db
    private void placeOrder(){

    }
}

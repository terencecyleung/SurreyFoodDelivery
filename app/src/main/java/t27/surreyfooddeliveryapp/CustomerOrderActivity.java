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

        //get input fields in xml
        name_et = (EditText)findViewById(R.id.cust_order_name_edittext);
        phone_et = (EditText)findViewById(R.id.cust_order_phone_edittext);
    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this,HomeActivity.class);
        startActivity(newIntent);
    }

    public void cust_placeOrder_click(View view) {

        //TODO change true to validation function

        if(true) {


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


    //TODO send order detail to db
    private void placeOrder(){

    }
}

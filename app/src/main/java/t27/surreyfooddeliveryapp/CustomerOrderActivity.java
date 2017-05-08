package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerorder);
    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this,HomeActivity.class);
        startActivity(newIntent);
    }

    public void cust_placeOrder_click(View view) {

        //TODO change true to validation function
        if(true) {
            //passed validation
            Intent from_cust_order_to_current_order = new Intent(this, CurrentOrderActivity.class);
            from_cust_order_to_current_order.putExtra("caller_activity", "CustomerOrderActivity");
            startActivity(from_cust_order_to_current_order);
        }

        //fails to place order
    }
}

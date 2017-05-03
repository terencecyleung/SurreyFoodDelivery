package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RequestDriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_driver);
    }

    public void restaurant_order_click(View view) {
        //TODO change true to validation function
        if(true) {
            //passed validation
            Intent from_rest_order_to_current_order = new Intent(this, CurrentOrderActivity.class);
            startActivity(from_rest_order_to_current_order);
        }

        //fails to place order
    }
}

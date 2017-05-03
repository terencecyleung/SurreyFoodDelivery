package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void customer_order_click(View view) {
        Intent fromHomeToCustomerOrder = new Intent(this, CustomerOrderActivity.class);
        startActivity(fromHomeToCustomerOrder);
    }

    public void restaurant_order_click(View view) {
        Intent fromHomeToRequestDriver = new Intent(this, RequestDriverActivity.class);
        startActivity(fromHomeToRequestDriver);
    }

    public void profile_click(View view) {
        //TODO distiguish guest and customer and restaurant

        Intent fromHomeToProfile = new Intent(this,ProfileActivity.class);
        startActivity(fromHomeToProfile);
    }
}

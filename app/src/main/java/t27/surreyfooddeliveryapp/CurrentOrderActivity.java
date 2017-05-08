package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurrentOrderActivity extends AppCompatActivity {
    private Intent Intent_get_it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        Intent_get_it = getIntent();
    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this, HomeActivity.class);
        startActivity(newIntent);
    }

    @Override
    public void onBackPressed() {


        String caller_activity = Intent_get_it.getStringExtra("caller_activity");
        if(caller_activity.equals("CustomerOrderActivity") || caller_activity.equals("RequestDriverActivity")) {
            Intent backToHome_after_order = new Intent(this,HomeActivity.class);
            backToHome_after_order.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(backToHome_after_order);
        } else {
            super.onBackPressed();
        }
    }

    public void profile_click(View view) {
        //TODO distiguish guest and customer and restaurant
        Intent fromCurrentOrderToProfile = new Intent(this,ProfileActivity.class);
        startActivity(fromCurrentOrderToProfile);
    }
}

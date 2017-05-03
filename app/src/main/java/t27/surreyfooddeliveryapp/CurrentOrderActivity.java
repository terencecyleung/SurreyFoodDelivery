package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CurrentOrderActivity extends AppCompatActivity {
    private Intent Intent_get_it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        Intent_get_it = getIntent();
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
}

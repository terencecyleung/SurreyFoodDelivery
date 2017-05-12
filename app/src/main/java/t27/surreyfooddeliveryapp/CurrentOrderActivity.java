package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import t27.surreyfooddeliveryapp.objectstodb.Customer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CurrentOrderActivity extends AppCompatActivity {
    private Intent Intent_get_it;
    SharedPreferences shar_pre;
    //ImageView icon_profile;
    String account_type = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        Intent_get_it = getIntent();

        //icon_profile = (ImageView) findViewById(R.id.profile_icon);
        //get back the customer object
        shar_pre = getApplicationContext().getSharedPreferences(getString(R.string.User_info), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shar_pre.getString("userObject", null);

        //log in as customer or restaurant
        //or logged in before
        if (json != null) {
            Customer cur_customer = gson.fromJson(json, Customer.class);
            //get type
            account_type = cur_customer.getAccountType();
            //set button order text based on type(restaurant or customer)
/*            if (account_type.equals("customer")) {
                icon_profile.setImageResource(R.drawable.customericon);
            } else if (account_type.equals("restaurant")) {
                icon_profile.setImageResource(R.drawable.restauranticon);
            }*/
        }

    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this, HomeActivity.class);
        startActivity(newIntent);
    }

    @Override
    public void onBackPressed() {


        String caller_activity = Intent_get_it.getStringExtra("caller_activity");
        if (caller_activity.equals("CustomerOrderActivity") || caller_activity.equals("RequestDriverActivity")) {
            Intent backToHome_after_order = new Intent(this, HomeActivity.class);
            backToHome_after_order.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(backToHome_after_order);
        } else {
            super.onBackPressed();
        }
    }

    public void profile_click(View view) {
        //TODO distiguish guest and customer and restaurant
        Intent fromCurrentOrderToProfile = new Intent(this, ProfileActivity.class);
        startActivity(fromCurrentOrderToProfile);
    }
}

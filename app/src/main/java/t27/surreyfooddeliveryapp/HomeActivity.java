package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import t27.surreyfooddeliveryapp.objectstodb.Customer;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences shar_pre;
    String account_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button order_btn = (Button) findViewById(R.id.button_customer_order);

        //get back the customer object
        shar_pre = getApplicationContext().getSharedPreferences(getString(R.string.User_infor), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shar_pre.getString("userObject", null);
        Customer cur_customer = gson.fromJson(json, Customer.class);

        //get type
        account_type = cur_customer.getAccountType();

        if(account_type.equals("Customer")) {
            order_btn.setText(getString(R.string.customer_order_btn_text));
        } else if(account_type.equals("Restaurant")) {
            order_btn.setText(getString(R.string.resta_order_btn_text));
        }




    }

    public void order_click(View view) {
        if(account_type.equals("Customer")) {
            Intent fromHomeToCustomerOrder = new Intent(this, CustomerOrderActivity.class);
            startActivity(fromHomeToCustomerOrder);
        } else if(account_type.equals("Restaurant")) {
            Intent fromHomeToRequestDriver = new Intent(this, RequestDriverActivity.class);
            startActivity(fromHomeToRequestDriver);
        }

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

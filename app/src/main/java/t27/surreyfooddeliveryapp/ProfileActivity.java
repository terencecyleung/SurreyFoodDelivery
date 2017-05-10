package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import t27.surreyfooddeliveryapp.objectstodb.Customer;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences shar_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get input fields
        TextView name_tv = (TextView) findViewById(R.id.name);
        TextView address_tv = (TextView)findViewById(R.id.address);
        TextView email_tv = (TextView)findViewById(R.id.email);
        TextView phone_tv = (TextView) findViewById(R.id.phone);

        //get back the customer object
        shar_pre = getApplicationContext().getSharedPreferences(getString(R.string.User_infor), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shar_pre.getString("userObject", null);
        if(json!=null) {
            Customer cur_customer = gson.fromJson(json, Customer.class);
            String cur_name = cur_customer.getName();
            String cur_address = cur_customer.getAddress();
            String cur_email = cur_customer.getEmail();
            String cur_phone = cur_customer.getNumber();

            name_tv.setText(cur_name);
            address_tv.setText(cur_address);
            email_tv.setText(cur_email);
            phone_tv.setText(cur_phone);
        }


    }

    public void editProfile_click(View view) {
        Intent from_profile_to_edit_pro = new Intent(this,EditProfileActivity.class);
        startActivity(from_profile_to_edit_pro);
    }

    public void changePassword_click(View view) {
        Intent from_profile_to_chagng_pass = new Intent(this,ChangePasswordActivity.class);
        startActivity(from_profile_to_chagng_pass);
    }

    public void logout_click(View view) {

        //login out function
        logout_fun();


        Intent from_profile_to_logout = new Intent(this,WelcomeActivity.class);
        from_profile_to_logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(from_profile_to_logout);
    }

    public void current_order_click(View view) {
        Intent from_profile_to_currentorder = new Intent(this,CurrentOrderActivity.class);
        from_profile_to_currentorder.putExtra("caller_activity", "ProfileActivity");
        startActivity(from_profile_to_currentorder);
    }

    private void logout_fun() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        SharedPreferences userInfo_Prefs =  getApplicationContext().getSharedPreferences(getString(R.string.User_infor), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = userInfo_Prefs.edit();
        prefsEditor.putString("userUID",null);
        prefsEditor.putString("userObject", null);
        prefsEditor.apply();
    }


}

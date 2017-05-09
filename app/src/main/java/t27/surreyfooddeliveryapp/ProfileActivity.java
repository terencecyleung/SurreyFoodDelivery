package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void editProfile_click(View view) {
        Intent from_profile_to_edit_pro = new Intent(this,EditProfileActivity.class);
        startActivity(from_profile_to_edit_pro);
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
        SharedPreferences userInfo_Prefs =  getApplicationContext().getSharedPreferences(getString(R.string.User_infor), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = userInfo_Prefs.edit();
        prefsEditor.putString("userUID",null);
        prefsEditor.putString("userObject", null);
        prefsEditor.apply();
    }
}

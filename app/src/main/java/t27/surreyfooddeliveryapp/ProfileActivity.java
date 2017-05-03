package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

        //TODo login out function

        Intent from_profile_to_logout = new Intent(this,WelcomeActivity.class);
        startActivity(from_profile_to_logout);
    }

    public void current_order_click(View view) {
        Intent from_profile_to_currentorder = new Intent(this,CurrentOrderActivity.class);
        startActivity(from_profile_to_currentorder);
    }
}

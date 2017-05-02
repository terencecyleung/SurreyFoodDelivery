package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login_registered_click(View view) {

        //TODO change true to validation function
        if(true) {
            //passed validation
            Intent fromloginToHome = new Intent(this, HomeActivity.class);
            startActivity(fromloginToHome);
        }

        //fails to login in
    }
}

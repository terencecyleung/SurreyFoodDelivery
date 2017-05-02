package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register_submit(View view) {
        //TODO change true to add validation function
        if(true) {

            //passed validation
            Intent registerSucceed = new Intent(this,LoginActivity.class);
            startActivity(registerSucceed);

        }

        //if it fails to register, toast
    }
}

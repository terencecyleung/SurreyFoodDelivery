package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void login_click(View view) {
        Intent fromWelcomeToLogin = new Intent(this,LoginActivity.class);
        startActivity(fromWelcomeToLogin);
    }

    public void register_click(View view) {
        Intent fromWelcomeToRegister = new Intent(this,RegisterActivity.class);
        startActivity(fromWelcomeToRegister);
    }
}

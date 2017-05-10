package t27.surreyfooddeliveryapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    String TAG = "ChangePasswordActivity";

    EditText oldpass_et;
    EditText newpass_et;
    EditText newpass_et2;

    String oldpass;
    String newpass;
    String newpass2;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldpass_et = (EditText) findViewById(R.id.edit_pass);
        newpass_et = (EditText) findViewById(R.id.edit_new_pass);
        newpass_et2 = (EditText) findViewById(R.id.edit_confirm_pass);


    }

    public void change_pass_click(View view) {
        oldpass = oldpass_et.getText().toString();
        newpass = newpass_et.getText().toString();
        newpass2 = newpass_et2.getText().toString();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null&& !oldpass.isEmpty()) {
            email = user.getEmail();
            // Get auth credentials from the user for re-authentication. The example below shows
            // email and password credentials but there are multiple possible providers,
            // such as GoogleAuthProvider or FacebookAuthProvider.
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, oldpass);
            //new passwords are not null and they are the same
            if(!newpass.isEmpty()&&!newpass2.isEmpty()&&newpass.equals(newpass2)){
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Password updated");
                                                Toast.makeText(ChangePasswordActivity.this, "Password updated",
                                                        Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                try {
                                                    throw task.getException();
                                                } catch (Exception e) {
                                                    Toast.makeText(ChangePasswordActivity.this, e.getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                Log.d(TAG, "Error password not updated");
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, "Error invalid credential",
                                            Toast.LENGTH_SHORT).show();

                                    Log.d(TAG, "Error auth failed");
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Please enter identical new passwords",
                        Toast.LENGTH_SHORT).show();

            }


        }




    }
}

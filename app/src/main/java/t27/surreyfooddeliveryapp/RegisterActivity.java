package t27.surreyfooddeliveryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import t27.surreyfooddeliveryapp.objectstodb.Customer;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    //xml input fields
    private EditText email_EditText;
    private EditText password_EditText;
    private EditText password2_EditText;
    private EditText name_EditText;
    private EditText phone_EditText;
    private EditText address_EditText;
    private EditText addressDetail_EditText;
    private RadioGroup type_RadioGroup;
    private RadioButton accountType_RadioButton;

    //inputs
    private String accountUID;
    private String email;
    private String password;
    private String password2;
    private String name;
    private String phone;
    private String address;
    private String addressDetail;
    private String accountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get input fields in xml
        email_EditText = (EditText) findViewById(R.id.email_et);
        password_EditText = (EditText) findViewById(R.id.password_et);
        password2_EditText = (EditText) findViewById(R.id.password2_et);
        name_EditText = (EditText) findViewById(R.id.name_et);
        phone_EditText = (EditText) findViewById(R.id.phone_et);
        address_EditText = (EditText) findViewById(R.id.address_et);
        addressDetail_EditText = (EditText) findViewById(R.id.addressDetail_et);
        type_RadioGroup = (RadioGroup) findViewById(R.id.types_radioGroup);

        //get fireBase db reference
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        //get fireBase Auth reference
        mAuth = FirebaseAuth.getInstance();
    }

    public void register_submit(View view) {
        //get inputs for validation and registration
        accountType_RadioButton = (RadioButton) findViewById(type_RadioGroup.getCheckedRadioButtonId());

        accountType = accountType_RadioButton.getText().toString();
        email = email_EditText.getText().toString();
        password = password_EditText.getText().toString();
        password2 = password2_EditText.getText().toString();
        name = name_EditText.getText().toString();
        phone = phone_EditText.getText().toString();
        addressDetail = addressDetail_EditText.getText().toString();
        address = address_EditText.getText().toString();

        //TODO change true to add validation function
        if (true) {

            //passed validation, so register the user
            register_account();


        }
    }


    //registration function to authentication and database
    private void register_account() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.register_failed,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //store to the database
                            FirebaseUser user = task.getResult().getUser();
                            String accountUID = user.getUid();
                            Log.d(TAG, "onComplete: uid=" + accountUID);

                            if (user != null) {
                                Customer newCustomer = new Customer(accountUID,
                                        accountType,
                                        email,
                                        password,
                                        name,
                                        phone,
                                        address,
                                        addressDetail);

                                mDatabaseRef.child("users").child(accountUID).setValue(newCustomer);

                                Toast.makeText(RegisterActivity.this, R.string.register_succeed,
                                        Toast.LENGTH_SHORT).show();

                                Intent registerSucceed = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(registerSucceed);
                                finish();
                            }
                        }

                        // ...
                    }
                });
    }
}

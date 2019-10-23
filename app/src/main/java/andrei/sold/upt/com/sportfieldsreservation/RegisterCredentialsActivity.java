package andrei.sold.upt.com.sportfieldsreservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import andrei.sold.upt.com.sportfieldsreservation.Models.User;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterCredentialsActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference userRef;

    private ProgressDialog progressDialog;

    private EditText emailUser, passwordUser, confirmPasswordUser;
    private Button registerUserButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        firebaseAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        progressDialog = new ProgressDialog(this);

        emailUser = (EditText) findViewById(R.id.emailUser);
        passwordUser = (EditText) findViewById(R.id.passwordUser);
        confirmPasswordUser = (EditText) findViewById(R.id.confirmPasswordUser);


        registerUserButton = (Button) findViewById(R.id.buttonRegister);
        registerUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        registerUserCredentials();
    }

    private void registerUserCredentials() {
        String email = emailUser.getText().toString().trim();
        String password = passwordUser.getText().toString().trim();
        String confirmPassword = confirmPasswordUser.getText().toString().trim();

        if (validateTextBox(TextUtils.isEmpty(email), "Please enter email!")) return;

        if (validateTextBox(TextUtils.isEmpty(email), "Please enter password!")) return;
        if (validateTextBox(!password.equals(confirmPassword), "Passwords doesn't match!")) return;

        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterCredentialsActivity.this, "Succes!", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent registerIntent = new Intent(getApplicationContext(), SportsActivity.class);
                            startActivity(registerIntent);
                        } else {
                            Toast.makeText(RegisterCredentialsActivity.this, "Account already created!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }



    private boolean validateTextBox(boolean empty, String s) {
        if (empty) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}

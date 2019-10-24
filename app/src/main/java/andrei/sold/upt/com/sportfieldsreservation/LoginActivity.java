package andrei.sold.upt.com.sportfieldsreservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailUser, passwordUser;
    private MaterialButton loginButton;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
                FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser != null) {
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                    finish();
                }
            }
        };
        progressDialog = new ProgressDialog(this);

        emailUser = (EditText) findViewById(R.id.emailLogin);
        passwordUser = (EditText) findViewById(R.id.passwordLogin);

        loginButton = (MaterialButton) findViewById(R.id.nextButtonRegister);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        loginUser();
    }

    private void loginUser() {

        String email = emailUser.getText().toString().trim();
        String password = passwordUser.getText().toString().trim();

        if (validateCredentials(email, "Please enter email!")) return;

        if (validateCredentials(password, "Please enter password!")) return;

        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "User not found or password incorrect!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validateCredentials(String email, String s) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}

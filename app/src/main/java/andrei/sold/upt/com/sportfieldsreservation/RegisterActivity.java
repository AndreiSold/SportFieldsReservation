package andrei.sold.upt.com.sportfieldsreservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import andrei.sold.upt.com.sportfieldsreservation.Models.User;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference userDatabaseRef;
    private EditText nameUser, ageUser, locationUser, numberUser;
    private MaterialButton nextButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nextButton = (MaterialButton) findViewById(R.id.nextButtonRegister);

        nameUser = (EditText) findViewById(R.id.nameUser);
        ageUser = (EditText) findViewById(R.id.ageUser);
        locationUser = (EditText) findViewById(R.id.locationUser);
        numberUser = (EditText) findViewById(R.id.numberUser);

        userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        nextButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        registerUser();
        startActivity(new Intent(this, RegisterCredentialsActivity.class));
    }

    private void registerUser() {

        String name = nameUser.getText().toString().trim();
        String age = ageUser.getText().toString().trim();
        String location = locationUser.getText().toString().trim();
        String number = numberUser.getText().toString().trim();

        if (validateTextBox(name, "Please enter your name!")) return;

        if (validateTextBox(age, "Please enter your age!")) return;

        if (validateTextBox(location, "Please enter your location!")) return;

        if (validateTextBox(number, "Please enter your phone number !")) return;

        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        String id = userDatabaseRef.push().getKey();
        User user = new User(name, number, age, location, "");
        userDatabaseRef.child(id).setValue(user);


    }

    private boolean validateTextBox(String name, String s) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}

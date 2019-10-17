package andrei.sold.upt.com.sportfieldsreservation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference userRef;
    private EditText nameUser, ageUser, locationUser, numberUser;
    private MaterialButton nextButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nextButton = (MaterialButton) findViewById(R.id.nextButtonRegister);

        nameUser = (EditText) findViewById(R.id.nameUser);
        ageUser = (EditText) findViewById(R.id.ageUser);
        locationUser = (EditText) findViewById(R.id.locationUser);
        numberUser = (EditText) findViewById(R.id.numberUser);

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        registerUser();
    }

    private void registerUser() {

        String name = nameUser.getText().toString().trim();
        String age = ageUser.getText().toString().trim();
        String location = locationUser.getText().toString().trim();
        String number = numberUser.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "Please enter your age!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(location)) {
            Toast.makeText(this, "Please enter your location!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "Please enter your phone number !", Toast.LENGTH_LONG).show();
            return;
        }

    }
}

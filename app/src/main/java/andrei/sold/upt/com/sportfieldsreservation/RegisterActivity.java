package andrei.sold.upt.com.sportfieldsreservation;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import andrei.sold.upt.com.sportfieldsreservation.Models.User;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference userDatabaseRef;
    private EditText nameUser, birthdateUser, locationUser, numberUser;
    private MaterialButton nextButton;
    private ProgressDialog progressDialog;

    private String id;
    private Calendar myCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myCalendar = Calendar.getInstance();

        nextButton = (MaterialButton) findViewById(R.id.nextButtonRegister);

        nameUser = (EditText) findViewById(R.id.nameUser);
        birthdateUser = (EditText) findViewById(R.id.birthdateUser);
        locationUser = (EditText) findViewById(R.id.locationUser);
        numberUser = (EditText) findViewById(R.id.numberUser);

        userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        nextButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        final DatePickerDialog.OnDateSetListener date = initializeDatePicker();
        birthdateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener initializeDatePicker() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
    }

    @Override
    public void onClick(View v) {
        registerUser();
        Intent intent = new Intent(this, RegisterCredentialsActivity.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }

    private void registerUser() {

        String name = nameUser.getText().toString().trim();
        String age = birthdateUser.getText().toString().trim();
        String location = locationUser.getText().toString().trim();
        String number = numberUser.getText().toString().trim();

        if (validateTextBox(name, "Please enter your name!")) return;

        if (validateTextBox(age, "Please enter your age!")) return;

        if (validateTextBox(location, "Please enter your location!")) return;

        if (validateTextBox(number, "Please enter your phone number !")) return;

        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        id = userDatabaseRef.push().getKey();
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdateUser.setText(sdf.format(myCalendar.getTime()));
    }
}

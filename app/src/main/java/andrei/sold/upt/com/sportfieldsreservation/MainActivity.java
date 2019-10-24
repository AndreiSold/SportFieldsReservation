package andrei.sold.upt.com.sportfieldsreservation;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton buttonSignIn, buttonJoinNow;
    LoginButton loginButton;
    CallbackManager callbackManager;

    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (MaterialButton) findViewById(R.id.buttonSignIn);
        buttonJoinNow = (MaterialButton) findViewById(R.id.buttonJoinNow);

        buttonSignIn.setOnClickListener(this);
        buttonJoinNow.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Main Activity", "FACEBOOK SUCCESS");
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }

            @Override
            public void onCancel() {
                Log.d("Main Activity", "FACEBOOK CANCELED");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("Main Activity", "FACEBOOK ERROR");

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonJoinNow:
                startActivity(new Intent(this, RegisterActivity.class));
                break;


        }
    }
}

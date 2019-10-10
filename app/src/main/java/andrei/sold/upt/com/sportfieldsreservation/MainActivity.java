package andrei.sold.upt.com.sportfieldsreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton buttonSignIn, buttonJoinNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (MaterialButton) findViewById(R.id.buttonSignIn);
        buttonJoinNow = (MaterialButton) findViewById(R.id.buttonJoinNow);

        buttonSignIn.setOnClickListener(this);
        buttonJoinNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                startActivity(new Intent(this, LoginActivity.class));
            case R.id.buttonJoinNow:
                startActivity(new Intent(this, RegisterActivity.class));

        }
    }
}

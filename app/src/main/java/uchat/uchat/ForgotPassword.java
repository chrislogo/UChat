package uchat.uchat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    RadioGroup retrieve_group;
    FloatingActionButton retrieve_submit, retrieve_cancel;
    TextInputEditText retrieve_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle("Retrieve Password");

        retrieve_email = (TextInputEditText) findViewById(R.id.retrieve_email);
        retrieve_submit = (FloatingActionButton) findViewById(R.id.retrieve_submit_button);
        retrieve_cancel = (FloatingActionButton) findViewById(R.id.retrieve_cancel_button);

        retrieve_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });

        retrieve_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}

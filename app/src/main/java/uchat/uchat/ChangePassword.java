package uchat.uchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    TextInputEditText new_password, confirm_password;
    FloatingActionButton reset_submit, reset_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("Change Password");

        new_password = (TextInputEditText) findViewById(R.id.new_password);
        confirm_password = (TextInputEditText) findViewById(R.id.confirm_password);
        reset_submit = (FloatingActionButton) findViewById(R.id.reset_submit_button);
        reset_cancel = (FloatingActionButton) findViewById(R.id.reset_cancel_button);

        reset_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new_password.getText().toString().equals(confirm_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else
                    Toast.makeText(getApplicationContext(), "ERROR: Passwords Don't Match", Toast.LENGTH_SHORT).show();
            }
        });

        reset_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}

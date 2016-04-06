package uchat.uchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateUser extends AppCompatActivity {

    EditText first_name, last_name, user_email, register_username, register_password, user_major;
    Spinner grade_year;
    Button register_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        user_email = (EditText) findViewById(R.id.user_email);
        register_username = (EditText) findViewById(R.id.register_username);
        register_password = (EditText) findViewById(R.id.register_password);
        user_major = (EditText) findViewById(R.id.major);

        grade_year = (Spinner) findViewById(R.id.grade_year);

        register_submit = (Button) findViewById(R.id.register_submit);

        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

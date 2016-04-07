package uchat.uchat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    ImageView login_logo;
    EditText login_username, login_password;
    Button reg_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_logo = (ImageView) findViewById(R.id.logo_login);
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);
        reg_button = (Button) findViewById(R.id.reg_button);
        login_logo.setImageResource(R.mipmap.uchat_logo);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),CreateUser.class));
            }
        });
    }

    // change later
    public void Validate_Cred(View v)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}

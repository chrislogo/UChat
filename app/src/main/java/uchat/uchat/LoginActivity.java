package uchat.uchat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    ImageView login_logo;
    EditText login_username, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_logo = (ImageView) findViewById(R.id.logo_login);
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);

        login_logo.setImageResource(R.mipmap.uchat_logo);

    }
}

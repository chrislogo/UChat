package uchat.uchat;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    ImageView profile_image;
    TextView profile_name, profile_email, profile_username, profile_secquest,
            profile_answer, profile_year, profile_major, profile_word_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        profile_image = (ImageView) findViewById(R.id.profile_image);

        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_email = (TextView) findViewById(R.id.profile_email);
        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_secquest = (TextView) findViewById(R.id.profile_secquest);
        profile_answer = (TextView) findViewById(R.id.profile_answer);
        profile_year = (TextView) findViewById(R.id.profile_year);
        profile_major = (TextView) findViewById(R.id.profile_major);
        profile_word_description = (TextView) findViewById(R.id.profile_word_description);


    }
}



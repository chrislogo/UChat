package uchat.uchat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    RadioGroup retrieve_group;
    FloatingActionButton retrieve_submit, retrieve_cancel;
    TextInputEditText retrieve_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.forgot_pass_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.forgot_pass_toolbar);

        retrieve_email = (TextInputEditText) findViewById(R.id.retrieve_email);
        retrieve_submit = (FloatingActionButton) findViewById(R.id.retrieve_submit_button);
        retrieve_cancel = (FloatingActionButton) findViewById(R.id.retrieve_cancel_button);

        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Retrieve Password");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));

        retrieve_cancel.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        retrieve_submit.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));

        retrieve_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });

        retrieve_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

}

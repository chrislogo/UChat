package uchat.uchat;

import android.content.Intent;
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
import android.widget.RadioGroup;

public class QuestionActivity extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    FloatingActionButton question_submit, question_cancel;
    TextInputEditText retrieve_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.question_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.question_toolbar);

        retrieve_question = (TextInputEditText) findViewById(R.id.question);
        question_submit = (FloatingActionButton) findViewById(R.id.question_submit_button);
        question_cancel = (FloatingActionButton) findViewById(R.id.question_cancel_button);

        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Security Question");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));

        question_cancel.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        question_submit.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));

        question_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });

        question_cancel.setOnClickListener(new View.OnClickListener() {
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

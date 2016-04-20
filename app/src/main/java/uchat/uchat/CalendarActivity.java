package uchat.uchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Raidel on 4/20/2016.
 */
public class CalendarActivity extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.calendar_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.calendar_toolbar);



        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Calendar");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.search);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        settingsMenuItem = menu.findItem(R.id.profile);
        s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        settingsMenuItem = menu.findItem(R.id.rateaprof);
        s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        settingsMenuItem = menu.findItem(R.id.logout);
        s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        else if(item.getItemId() == R.id.search)
        {
            startActivity(new Intent(getBaseContext(), MajorChoice.class));
        }
        else if(item.getItemId() == R.id.profile)
        {
            startActivity(new Intent(getBaseContext(), Profile.class));
        }
        else if(item.getItemId() == R.id.rateaprof)
        {
            startActivity(new Intent(getBaseContext(), RateProfessor.class));
        }
        else if(item.getItemId() == R.id.logout)
        {
            startActivity(new Intent(getBaseContext(), LogoutActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}

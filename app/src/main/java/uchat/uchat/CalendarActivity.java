package uchat.uchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.CalendarView;
import android.widget.Toast;

/**
 * Created by Raidel on 4/20/2016.
 */
public class CalendarActivity extends AppCompatActivity {

    FloatingActionButton view_button, add_button;
    CalendarView    user_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.calendar_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Calendar");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        view_button = (FloatingActionButton) findViewById(R.id.view_event);
        add_button = (FloatingActionButton) findViewById(R.id.add_event);
        user_calendar = (CalendarView) findViewById(R.id.user_calendar);

        view_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        add_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));

        initializeCalendar();

        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, ViewCalendar.class));
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, AddCalendarItem.class));
            }
        });

    }

    public void initializeCalendar() {

        // sets whether to show the week number.
        user_calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        user_calendar.setFirstDayOfWeek(2);

        //The background color for the selected week.
        //user_calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.Garnet));

        //sets the color for the dates of an unfocused month.
        user_calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.Gold));

        //sets the color for the separator line between weeks.
        user_calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.Gold));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        user_calendar.setSelectedDateVerticalBar(R.color.Garnet);

        //sets the listener to be notified upon selected date change.
        user_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}

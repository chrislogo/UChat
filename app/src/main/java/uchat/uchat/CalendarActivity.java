package uchat.uchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raidel on 4/20/2016.
 */
public class CalendarActivity extends AppCompatActivity {

    FloatingActionButton view_button, add_button;
    MaterialCalendarView user_calendar;
    CalendarDay date_selected;
    TextInputEditText user_event;
    ArrayList<String> date_event_format;


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
        user_calendar = (MaterialCalendarView) findViewById(R.id.user_calendar);
        user_event = (TextInputEditText) findViewById(R.id.add_item);

        SharedPreferences get_pref = this.getSharedPreferences("event_array", 0);
        if (get_pref.contains("array_list")) {
            Set<String> get_set = get_pref.getStringSet("array_list", null);
            date_event_format = new ArrayList<>(get_set);
        }else
            date_event_format = new ArrayList<>();

        view_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        add_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        date_selected = CalendarDay.today();

        initializeCalendar();

        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("event_array", 0);
                SharedPreferences.Editor editor = pref.edit();
                Set<String> set = new HashSet<>();
                set.addAll(date_event_format);
                editor.putStringSet("array_list", set);
                editor.commit();

                Intent intent = new Intent(CalendarActivity.this, ViewCalendar.class);
                //intent.putStringArrayListExtra("date_event_format", date_event_format);
                startActivity(intent);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_event.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Event Empty", Toast.LENGTH_SHORT).show();
                }else{
                    date_event_format.add(date_selected.getMonth() + "/" + date_selected.getDay() + "/" + date_selected.getYear()+ ": "
                            + user_event.getText().toString());
                    Toast.makeText(getApplicationContext(), "Event Added", Toast.LENGTH_SHORT).show();
                    user_event.getText().clear();
                }
            }
        });

    }

    public void initializeCalendar() {

        user_calendar.setFirstDayOfWeek(2);

        user_calendar.setDateSelected(CalendarDay.today(), true);

        user_calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                date_selected = date;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.logout);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.logout)
        {
            startActivity(new Intent(getBaseContext(), LogoutActivity.class));
        }

        finish();
        return super.onOptionsItemSelected(item);
    }

}

package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class ViewCalendar extends AppCompatActivity {

    ArrayList<String> date_event_format;
    ListView calendar_event_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calendar);

        calendar_event_list = (ListView) findViewById(R.id.calendar_event_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.calendarView_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("To Do:");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        SharedPreferences pref = this.getSharedPreferences("event_array", 0);
        Set<String> set = pref.getStringSet("array_list", null);
        date_event_format =new ArrayList<>(set);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, date_event_format);
        calendar_event_list.setAdapter(adapter);
    }
}

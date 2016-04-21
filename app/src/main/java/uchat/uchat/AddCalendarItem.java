package uchat.uchat;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddCalendarItem extends AppCompatActivity {

    FloatingActionButton cancel_button, add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.calendarAdd_toolbar);
        setSupportActionBar(toolbar);

        cancel_button = (FloatingActionButton) findViewById(R.id.cancel_add_button);
        add_button = (FloatingActionButton) findViewById(R.id.add_submit);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add To Do:");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        cancel_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        add_button.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
    }
}

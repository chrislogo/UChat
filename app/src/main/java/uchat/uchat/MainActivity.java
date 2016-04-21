package uchat.uchat;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.Toast;

/**
 * Created by Raidel on 4/20/2016.
 */
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost(); // The activity TabHost
        TabHost.TabSpec spec;

        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        spec = tabHost.newTabSpec("chat_layout").setIndicator(null,
                getResources().getDrawable(R.drawable.chat_icon)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent(MainActivity.this, CalendarActivity.class);
        spec = tabHost.newTabSpec("user_calendar").setIndicator(null,
                getResources().getDrawable(R.drawable.calendar_icon)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent(MainActivity.this, RateProfessor.class);
        spec = tabHost.newTabSpec( "rate_prof" ).setIndicator( null,
                getResources().getDrawable(R.drawable.rate_icon)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent(MainActivity.this, CardRecycler.class);
        spec = tabHost.newTabSpec( "user_profile" ).setIndicator( null,
                getResources().getDrawable(R.drawable.profile_icon)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(), "Tab ID = " + tabId,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

}

package uchat.uchat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class ChatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigationDrawerItemTitles = new String[9];
        mNavigationDrawerItemTitles[0] = "Guy 1";
        mNavigationDrawerItemTitles[1] = "Guy 2";
        mNavigationDrawerItemTitles[2] = "Guy 3";
        mNavigationDrawerItemTitles[3] = "Guy 4";
        mNavigationDrawerItemTitles[4] = "Guy 5";
        mNavigationDrawerItemTitles[5] = "Guy 6";
        mNavigationDrawerItemTitles[6] = "Guy 7";
        mNavigationDrawerItemTitles[7] = "Guy 8";




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mDrawerList = (ListView) findViewById(R.id.nav_chatlist);

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNavigationDrawerItemTitles);

        mDrawerList.setAdapter((new OnlineUsersAdapter(this, mNavigationDrawerItemTitles)));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // grab preferences
                // SharedPreferences settings = getSharedPreferences(, 0);

                // cursor.moveToPosition(position);
                // String a = cursor.getString(cursor.getColumnIndexOrThrow("Name"));


                //boolean from_add = settings.getBoolean("", false);

                // SharedPreferences.Editor editor = settings.edit();

                // editor.putString("", a);
                //editor.apply();

                // Some sort of shared prefs to load another chat here

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        settingsMenuItem = menu.findItem(R.id.logout);
        s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.optionMenuTextColor)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.search)
        {

        }
        else if(item.getItemId() == R.id.profile)
        {
            startActivity(new Intent(getBaseContext(), Profile.class));
        }
        else if(item.getItemId() == R.id.logout)
        {
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        }

        //return true;

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

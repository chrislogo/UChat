package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;
    private String [] users;
    StringRequest stringRequest;
    String url = "http://73.42.47.33/online-users.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences shared_pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);

        String pref_response = shared_pref.getString("username", "");

        mDrawerList = (ListView) findViewById(R.id.nav_chatlist);


        stringRequest = new StringRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    //get json array returned from scrpit
                    JSONArray jsonArray = new JSONArray(response);
                    //set String arrya equal to length of json array
                    mNavigationDrawerItemTitles =  new String[jsonArray.length()];

                    //iterate through all the json array
                    for (int i=0; i < mNavigationDrawerItemTitles.length; i++){
                        //get JSONObject at JSONArray[i]
                        //This lets us see grab the value from key
                        JSONObject jo = jsonArray.getJSONObject(i);
                        //grab value from key and store it in string array
                        mNavigationDrawerItemTitles[i] = jo.getString("username");
                    }

                    //had to put this in here for scoping purposed, but this might cause scoping
                    //issues later on when we try and implement onclick for the online users
                    mDrawerList.setAdapter((new OnlineUsersAdapter(ChatActivity.this, mNavigationDrawerItemTitles)));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("Error", error.getMessage());
                    }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

       //IF WE GET ERRORS, MAKE SURE PREF !NULL




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // mDrawerList = (ListView) findViewById(R.id.nav_chatlist);

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNavigationDrawerItemTitles);

        //mDrawerList.setAdapter((new OnlineUsersAdapter(this, mNavigationDrawerItemTitles)));


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //*****************We should use an intent here, no need to keep variable alive***********

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

    private void showJSON(String json){
        JSONObject jsonObject=null;

        try{
            jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray(json);

            users =  new String[results.length()];

            for (int i=0; i < results.length(); i++){
                Toast.makeText(ChatActivity.this, "in function", Toast.LENGTH_SHORT).show();
                JSONObject jo = results.getJSONObject(i);
                users[i] = jo.getString("username");
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
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
        if(item.getItemId() == R.id.search)
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

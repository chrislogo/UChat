package uchat.uchat;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateUser extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    TextInputEditText first_name, last_name, user_email, answer, register_username, register_password;
    AppCompatSpinner question, grad_year, user_major;
    FloatingActionButton register_submit, cancel_button;
    RequestQueue requestQueue;
    String insertUrl = "http://73.42.47.33/insert_user.php";
    String selected_question, selected_grad_year, selected_major;
    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.create_user_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.create_user_toolbar);

        first_name = (TextInputEditText) findViewById(R.id.first_name);
        last_name = (TextInputEditText) findViewById(R.id.last_name);
        user_email = (TextInputEditText) findViewById(R.id.user_email);
        register_username = (TextInputEditText) findViewById(R.id.register_username);
        register_password = (TextInputEditText) findViewById(R.id.register_password);
        answer = (TextInputEditText) findViewById(R.id.answer);

        question = (AppCompatSpinner) findViewById(R.id.question);
        grad_year = (AppCompatSpinner) findViewById(R.id.year);
        user_major = (AppCompatSpinner) findViewById(R.id.major);

        register_submit = (FloatingActionButton) findViewById(R.id.register_submit);
        cancel_button = (FloatingActionButton) findViewById(R.id.cancel_button);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Create Account");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sec_quest, R.layout.spinner_view);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.grade_year, R.layout.spinner_view);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grad_year.setAdapter(adapter2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.offered_majors, R.layout.spinner_view);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_major.setAdapter(adapter3);

        // Grab Spinner Selection
        question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_question = question.getItemAtPosition(position).toString();
                //((TextView) question.getItemAtPosition(position)).setTextColor(getResources().getColor(R.color.White));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        grad_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_grad_year = grad_year.getItemAtPosition(position).toString();
                //((TextView) grad_year.getItemAtPosition(position)).setTextColor(getResources().getColor(R.color.White));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        user_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_major = user_major.getItemAtPosition(position).toString();
                //((TextView) user_major.getItemAtPosition(position)).setTextColor(getResources().getColor(R.color.White));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        register_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            //check the first slot of returned object for success
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(),"" + jsonObject.getString("success"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_LONG).show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("username", register_username.getText().toString());
                        hashMap.put("password", register_password.getText().toString());
                        hashMap.put("email", user_email.getText().toString());
                        hashMap.put("question", question.getSelectedItem().toString());
                        hashMap.put("answer", answer.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
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

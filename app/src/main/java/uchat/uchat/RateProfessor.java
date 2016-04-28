package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
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

public class RateProfessor extends AppCompatActivity {

    //rating data: Two Strings and a float.
    String selected_course, selected_prof, comment,username, id;
    String url = "http://73.42.47.33/rmp_insert.php";
    float rating;

    Spinner courseSpinner;
    Spinner profSpinner;
    RatingBar rbar;
    Button submit_button;
    Button view_ratings;
    EditText comText;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.rate_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rate My Professor");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        submit_button = (Button) findViewById(R.id.rapsubmit);
        view_ratings = (Button) findViewById(R.id.view_rate_btn);
        courseSpinner = (Spinner) findViewById(R.id.class_spin);
        profSpinner = (Spinner) findViewById(R.id.prof_spin);
        rbar = (RatingBar) findViewById(R.id.ratingBar);
        comText = (EditText) findViewById(R.id.commentText);

        SharedPreferences shared_pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);
        username = shared_pref.getString("username", "");
        id = shared_pref.getString("id","");
        //spinner init
        ArrayAdapter<CharSequence> spindapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> spindapter2 = ArrayAdapter.createFromResource(this, R.array.professors, android.R.layout.simple_spinner_item);

        spindapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseSpinner.setAdapter(spindapter);
        profSpinner.setAdapter(spindapter2);

        //grab spinner data
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_course = courseSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //grab spinner data
        profSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_prof = profSpinner.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar rbar, float f, boolean bool) {
                rating = f; //rating bar just returns a float.  bool determines whether it was from user or not.
            }
        });

        //submit button
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jo = new JSONObject(response);
                            if (jo.getString("result").equals("success"))
                                Toast.makeText(getApplicationContext(), "Rating successfully stored.", Toast.LENGTH_SHORT).show();
                            else if (jo.getString("result").equals("exist_err"))
                                Toast.makeText(RateProfessor.this, "You have already submitted a review for this professor", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RateProfessor.this, "Error connecting to server", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RateProfessor.this, LoginActivity.class));
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("professor", profSpinner.getSelectedItem().toString());
                        String id_str = "" + id;
                        hashMap.put("stu_id", id);
                        hashMap.put("course", courseSpinner.getSelectedItem().toString());
                        String rating_str = "" + rating;
                        hashMap.put("rating", rating_str);
                        hashMap.put("review", comText.getText().toString() );
                        return hashMap;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(RateProfessor.this);
                requestQueue.add(stringRequest);
            }
        });

        //submit button
       view_ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RateProfessor.this, ViewProfessors.class));
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

package uchat.uchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    TextInputEditText new_password, confirm_password;
    FloatingActionButton reset_submit, reset_cancel;
    StringRequest stringRequest;
    String url = "http://73.42.47.33/update-pw.php?password=";
    String email;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.change_pass_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.change_pass_toolbar);

        new_password = (TextInputEditText) findViewById(R.id.new_password);
        confirm_password = (TextInputEditText) findViewById(R.id.confirm_password);
        reset_submit = (FloatingActionButton) findViewById(R.id.reset_submit_button);
        reset_cancel = (FloatingActionButton) findViewById(R.id.reset_cancel_button);

        extras = getIntent().getExtras();
        if (extras != null){
            email = extras.getString("email");
        }

        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Change Password");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));

        reset_cancel.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        reset_submit.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));

        reset_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new_password.getText().toString().equals(confirm_password.getText().toString())) {
                    url+=new_password.getText().toString();
                    url+= "&email=" + email;
                    Log.i("URL:::",url);
                    stringRequest = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                    Toast.makeText(ChangePassword.this, "Successfully updated password!", Toast.LENGTH_LONG).show();
                        }

                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("Error", error.getMessage());
                                }
                            });
                    RequestQueue requestQueue = Volley.newRequestQueue(ChangePassword.this);
                    requestQueue.add(stringRequest);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else
                    new_password.setError("Passwords do not match!");
            }
        });

        reset_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}

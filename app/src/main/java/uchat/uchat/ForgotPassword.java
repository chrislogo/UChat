package uchat.uchat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassword extends AppCompatActivity {

    CollapsingToolbarLayout create_user_collapse;
    Toolbar create_user_toolbar;

    RadioGroup retrieve_group;
    FloatingActionButton retrieve_submit, retrieve_cancel;
    TextInputEditText retrieve_email;
    StringRequest stringRequest;
    String url = "http://73.42.47.33/recover-pw.php?email=";
    String question;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        create_user_collapse = (CollapsingToolbarLayout) findViewById(R.id.forgot_pass_collapse);
        create_user_toolbar = (Toolbar) findViewById(R.id.forgot_pass_toolbar);

        retrieve_email = (TextInputEditText) findViewById(R.id.retrieve_email);
        retrieve_submit = (FloatingActionButton) findViewById(R.id.retrieve_submit_button);
        retrieve_cancel = (FloatingActionButton) findViewById(R.id.retrieve_cancel_button);

        setSupportActionBar(create_user_toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        create_user_collapse.setTitle("Retrieve Password");
        create_user_collapse.setExpandedTitleColor(getResources().getColor(R.color.White));
        create_user_collapse.setCollapsedTitleTextColor(getResources().getColor(R.color.White));

        retrieve_cancel.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));
        retrieve_submit.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));


        retrieve_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean error = false;
                if (retrieve_email.getText().toString().isEmpty()) {
                    retrieve_email.setError("Email cannot be empty");
                } else {
                    url += retrieve_email.getText().toString();
                    final Bundle bundle = new Bundle();
                    stringRequest = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("STATE::", "TOP");
                                retrieve_email.setError(null);
                                JSONObject jo = new JSONObject(response);
                                String quest = jo.getString("question");

                                if (quest.equals("error")) {
                                    retrieve_email.setError("Email does not exist");
                                    Log.i("EMAIL", jo.getString("question"));
                                } else {

                                    bundle.putString("question", jo.getString("question"));
                                    bundle.putString("answer", jo.getString("answer"));
                                    bundle.putString("email", retrieve_email.getText().toString());
                                    Toast.makeText(ForgotPassword.this, jo.getString("question"), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("Error", error.getMessage());
                                }
                            });
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                    requestQueue.add(stringRequest);


                }

            }
        });

        retrieve_cancel.setOnClickListener(new View.OnClickListener() {
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

    public void GoBack(View v)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

}

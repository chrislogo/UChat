package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class ClassChoice extends AppCompatActivity {

    StringRequest stringRequest;
    String url = "http://73.42.47.33/enroll-insert.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_choice);

        final ListView course = (ListView) findViewById(R.id.courses);

        course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jo = new JSONObject(response);

                            if (jo.getString("result").equals("success"))
                                Toast.makeText(getApplicationContext(), "Class successfully stored.", Toast.LENGTH_SHORT).show();
                            if (jo.getString("result").equals("exist_err")){
                                Toast.makeText(getApplicationContext(), "Error adding class.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", error.getMessage());
                        Toast.makeText(getApplicationContext(), "Error connecting to server.", Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        SharedPreferences shared_pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);
                        String username = shared_pref.getString("username", "");
                        String course_name = course.getItemAtPosition(pos).toString();

                        hashMap.put("username", username);
                        hashMap.put("course", course_name);
                        return hashMap;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ClassChoice.this);
                requestQueue.add(stringRequest);
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }


        });
    }
}



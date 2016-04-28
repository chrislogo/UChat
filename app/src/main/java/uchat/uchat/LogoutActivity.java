package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LogoutActivity extends AppCompatActivity {

    String logout_url = "http://73.42.47.33/logout.php?username=";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences shared_pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);
        final String pref_response = shared_pref.getString("username", "");

        logout_url+=pref_response;
        request = new StringRequest(Request.Method.GET, logout_url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Toast.makeText(LogoutActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
                   }
               }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(LogoutActivity.this, "Error removing from database", Toast.LENGTH_LONG).show();
                    }
               });
        requestQueue.add(request);

        startActivity(new Intent(this, LoginActivity.class));
    }
}
package uchat.uchat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class LoginActivity extends AppCompatActivity {

    ImageView login_logo;
    EditText login_username, login_password;
    Button reg_button,log_button;
    String log_url = "http://73.42.47.33/login.php";
    RequestQueue requestQueue;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_logo = (ImageView) findViewById(R.id.logo_login);
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);
        reg_button = (Button) findViewById(R.id.reg_button);
        log_button = (Button) findViewById(R.id.log_button);
        login_logo.setImageResource(R.mipmap.uchat_logo);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateUser.class));
            }
        });


        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, log_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //check for success JSON object
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("success"), Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "" + jsonObject.get("error"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //uses key,pair to match up with PHP code, first param is key
                        //into MYSQL variable name second is the actual value taken from
                        //textview
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("username", login_username.getText().toString());
                        hashMap.put("password", login_password.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }

}

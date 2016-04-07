package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CreateUser extends AppCompatActivity {

    EditText first_name, last_name, user_email, answer, register_username, register_password, user_major;
    Spinner question;
    Button register_submit, cancel_button;
    RequestQueue requestQueue;
    String insertUrl = "http://73.42.47.33/insert_user.php";
    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        user_email = (EditText) findViewById(R.id.user_email);
        register_username = (EditText) findViewById(R.id.register_username);
        register_password = (EditText) findViewById(R.id.register_password);
        //user_major = (EditText) findViewById(R.id.major);
        answer = (EditText) findViewById(R.id.answer);
        question = (Spinner) findViewById(R.id.question);

        register_submit = (Button) findViewById(R.id.register_submit);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

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
                        hashMap.put("answer", answer.toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }

}

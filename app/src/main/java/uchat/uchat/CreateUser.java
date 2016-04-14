package uchat.uchat;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
        setTitle("Create Account");

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
                        hashMap.put("answer", answer.toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }

}

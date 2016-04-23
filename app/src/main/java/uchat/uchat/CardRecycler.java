package uchat.uchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardRecycler extends AppCompatActivity{
    private List<ChatRoomCard> persons;
    private RecyclerView rv;
    FloatingActionButton fab;
    RVAdapter rvAdapter;
    StringRequest request;
    String url = "http://73.42.47.33/enroll-get.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_recycler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.card_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select Chat");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        rv=(RecyclerView)findViewById(R.id.rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        fab.setBackgroundTintList(getResources().getColorStateList(R.color.Garnet));

        persons = new ArrayList<>();

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                String []courses = {""};
                try{
                    JSONArray ja = new JSONArray(response);
                    courses = new String[ja.length()];
                    //String [] courses = new Reviews[ja.length()];
                    for(int i = 0; i < ja.length(); i++){
                        JSONObject jo = ja.getJSONObject(i);
                        Log.i("IN RESPONSE:::::", jo.getString("course"));
                        courses[i] = jo.getString("course");
                        //persons.add(new ChatRoomCard(jo.getString("course")));
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
                finally {
                    for(int i = 0; i < courses.length; i++){
                        persons.add(new ChatRoomCard(courses[i]));
                    }

                    rvAdapter = new RVAdapter(persons);
                    rv.setAdapter(rvAdapter);

                    rvAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
                        public void onItemClick(View v, int position) {
                            TextView tv = (TextView) v.findViewById(R.id.class_name);
                            SharedPreferences pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("course", tv.getText().toString());
                            editor.apply();

                            startActivity(new Intent(CardRecycler.this, ChatActivity.class));
                            finish();
                        }
                    });
                }


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(CardRecycler.this, "Error connecting to database", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                SharedPreferences shared_pref = getApplicationContext().getSharedPreferences(LoginActivity.pref_string, 0);
                String username = shared_pref.getString("username", "");
                hashMap.put("username", username);
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CardRecycler.this);
        requestQueue.add(request);






        //initializeData();
        //initializeAdapter();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardRecycler.this, MajorChoice.class));
                finish();
            }
        });
    }


    /*private void initializeAdapter(){

    }*/
}
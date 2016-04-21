package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

public class BrowseRatings extends AppCompatActivity {

    ListView review;

    String name;
    String url="http://73.42.47.33/rmp-browse.php";
    StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_ratings);

        review = (ListView) findViewById(R.id.reviews);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
        }

        Log.i("NAME:::::", name);

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONArray ja = new JSONArray(response);
                    Reviews []reviews = new Reviews[ja.length()];
                    for(int i = 0; i < ja.length(); i++){
                        JSONObject jo = ja.getJSONObject(i);
                        Log.i("IN RESPONSE:::::", jo.getString("name"));
                        reviews[i] = new Reviews( jo.getString("name"),jo.getString("rating"),jo.getString("review"));
                    }
                    ReviewAdapter adapter = new ReviewAdapter(BrowseRatings.this,
                            R.layout.single_view_review, reviews);

                    // Assign adapter to ListView
                    review.setAdapter(adapter);

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(BrowseRatings.this, "Error connecting to database", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("prof", name);
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BrowseRatings.this);
        requestQueue.add(request);

    }
}

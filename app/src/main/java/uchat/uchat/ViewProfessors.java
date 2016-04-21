package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ViewProfessors extends AppCompatActivity {

    ListView professors;
    StringRequest request;
    String url = "http://73.42.47.33/rmp-view.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_professors);
        professors = (ListView) findViewById(R.id.prof_ratings);

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONArray ja = new JSONArray(response);
                    Professors []prof_list  =  new Professors[ja.length()];
                    for(int i = 0; i < ja.length(); i++){
                        JSONObject jo = ja.getJSONObject(i);
                        prof_list[i] = new Professors( jo.getString("name"),jo.getString("rating"));
                    }

                    ProfessorsAdapter adapter = new ProfessorsAdapter(ViewProfessors.this, R.layout.single_view_prof, prof_list);

                    // Assign adapter to ListView
                    professors.setAdapter(adapter);

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(ViewProfessors.this,"Error connecting to database",Toast.LENGTH_LONG).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(ViewProfessors.this);
        requestQueue.add(request);




        professors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                TextView tv = (TextView) arg1.findViewById(R.id.prof_name);
                Bundle bundle = new Bundle();
                bundle.putString("name", tv.getText().toString());
                Intent intent = new Intent(ViewProfessors.this, BrowseRatings.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

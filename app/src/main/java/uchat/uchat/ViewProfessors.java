package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewProfessors extends AppCompatActivity {

    ListView professors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_professors);

        professors = (ListView) findViewById(R.id.prof_ratings);

        String []names = getResources().getStringArray(R.array.professors);
        Professors []prof_list = new Professors[names.length-1];
        float rating = (float)3.2;
        int j = 0;
        for(int i = 1; i < names.length; i++)
        {
            String rating_str = Float.toString(rating) + "/5.0";
            prof_list[j] = new Professors(names[i], rating_str);
            j++;
        }

        ProfessorsAdapter adapter = new ProfessorsAdapter(this,
                R.layout.single_view_prof, prof_list);

        // Assign adapter to ListView
        professors.setAdapter(adapter);

        professors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                // get the professors reviews

                startActivity(new Intent(ViewProfessors.this, BrowseRatings.class));
            }
        });
    }
}

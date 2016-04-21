package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BrowseRatings extends AppCompatActivity {

    ListView review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_ratings);

        review = (ListView) findViewById(R.id.reviews);

        String []courses = getResources().getStringArray(R.array.courses);
        Reviews []reviews = new Reviews[courses.length-1];
        float rating = (float)3.2;
        int j = 0;
        for(int i = 1; i < courses.length; i++)
        {
            String rating_str = Float.toString(rating) + "/5.0";
            String descrip = "The teacher was amazing.\nThis is the next line here.";
            reviews[j] = new Reviews(courses[i], rating_str, descrip);
            j++;
        }

        ReviewAdapter adapter = new ReviewAdapter(this,
                R.layout.single_view_review, reviews);

        // Assign adapter to ListView
        review.setAdapter(adapter);
    }
}

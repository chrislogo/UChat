package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class RateProfessor extends AppCompatActivity {

    //rating data: Two Strings and a float.
    String selected_course, selected_prof, comment;
    float rating;

    Spinner courseSpinner;
    Spinner profSpinner;
    RatingBar rbar;
    Button submit_button;
    Button view_ratings;
    EditText comText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);

        submit_button = (Button) findViewById(R.id.rapsubmit);
        view_ratings = (Button) findViewById(R.id.view_rate_btn);
        courseSpinner = (Spinner) findViewById(R.id.class_spin);
        profSpinner = (Spinner) findViewById(R.id.prof_spin);
        rbar = (RatingBar) findViewById(R.id.ratingBar);
        comText = (EditText) findViewById(R.id.commentText);

        //spinner init
        ArrayAdapter<CharSequence> spindapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> spindapter2 = ArrayAdapter.createFromResource(this, R.array.professors, android.R.layout.simple_spinner_item);

        spindapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseSpinner.setAdapter(spindapter);
        profSpinner.setAdapter(spindapter2);

        //grab spinner data
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_course = courseSpinner.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //grab spinner data
        profSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_prof = profSpinner.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar rbar, float f, boolean bool) {
                rating = f; //rating bar just returns a float.  bool determines whether it was from user or not.
            }
        });

        //submit button
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment = comText.getText().toString();
                Toast.makeText(getApplicationContext(), "Rating successfully stored.", Toast.LENGTH_SHORT).show();
            }
        });

        //submit button
       view_ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RateProfessor.this, ViewProfessors.class));
            }
        });

    }
}

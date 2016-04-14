package uchat.uchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RateProfessor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);

        Spinner courseSpinner = (Spinner) findViewById(R.id.class_spin);
        Spinner profSpinner = (Spinner) findViewById(R.id.prof_spin);

        //spinner init
        ArrayAdapter<CharSequence> spindapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> spindapter2 = ArrayAdapter.createFromResource(this, R.array.professors, android.R.layout.simple_spinner_item);

        spindapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseSpinner.setAdapter(spindapter);
        profSpinner.setAdapter(spindapter2);

       /* courseSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String courseSelected = parent.getItemAtPosition(position).toString();

            }
        });*/
    }
}

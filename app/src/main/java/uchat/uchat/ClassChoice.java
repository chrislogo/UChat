package uchat.uchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ClassChoice extends AppCompatActivity {

    cardInterface cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_choice);

        final ListView course = (ListView) findViewById(R.id.courses);

        course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getBaseContext(), MainActivity.class));
                cards.addCard("COP4610", R.drawable.profile_icon);
            }
        });
    }
}

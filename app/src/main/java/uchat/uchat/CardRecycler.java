package uchat.uchat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CardRecycler extends AppCompatActivity {

    private List<ChatRoomCard> persons;
    private RecyclerView rv;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_recycler);

        rv=(RecyclerView)findViewById(R.id.rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new ChatRoomCard("COP4656", R.drawable.profile_icon));

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
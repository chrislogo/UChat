package uchat.uchat;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CardRecycler extends AppCompatActivity{

    private List<ChatRoomCard> persons;
    private RecyclerView rv;
    FloatingActionButton fab;
    RVAdapter rvAdapter;

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

        initializeData();
        initializeAdapter();

        rvAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            public void onItemClick(View v, int position) {
                startActivity(new Intent(CardRecycler.this, ChatActivity.class));

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardRecycler.this, MajorChoice.class));
            }
        });
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new ChatRoomCard("COP4656", "Leon Brown",R.drawable.profile_icon));
        persons.add(new ChatRoomCard("COP4610", "Zhi Wang" ,R.drawable.profile_icon));
        persons.add(new ChatRoomCard("COP4530", "Zhenhai Duan",R.drawable.profile_icon));
        persons.add(new ChatRoomCard("COP3330", "Bob Myers",R.drawable.profile_icon));
        persons.add(new ChatRoomCard("COP3014", "Melina Vastola",R.drawable.profile_icon));
    }

    private void initializeAdapter(){
        rvAdapter = new RVAdapter(persons);
        rv.setAdapter(rvAdapter);
    }
}
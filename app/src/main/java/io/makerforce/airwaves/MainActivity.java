package io.makerforce.airwaves;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter songAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Song> queue = new ArrayList<Song>();
    ArrayList<Song> music = new ArrayList<Song>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.queue:
                    updateData(queue);
                    return true;
                case R.id.music:
                    updateData(music);
                    return true;
                case R.id.devices:
                    //DO WHAT??
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext()); //no of columns
        recyclerView.setLayoutManager(layoutManager);
        Song pew = new Song("I Really Like You", "Carly Rae Jepsen", "EMOTION", "fdsjfsjk", "3:41");
        queue.add(pew);
        queue.add(pew);
        queue.add(pew);
        queue.add(pew);
        queue.add(pew);
        queue.add(pew);
        queue.add(pew);
        Song daft = new Song("Give Life Back to Music", "Daft Punk", "Random Access Memories", "fdsdfsdf", "4:34");
        music.add(daft);
        music.add(daft);
        music.add(daft);
        music.add(daft);
        music.add(daft);
        music.add(daft);
        music.add(daft);
        songAdapter = new SongAdapter(getApplicationContext(), queue);
        recyclerView.setAdapter(songAdapter);
    }

    public void updateData(ArrayList<Song> list) {
        //modify queue
        songAdapter = new SongAdapter(getApplicationContext(), list);
        recyclerView.swapAdapter(songAdapter, false);
    }

}

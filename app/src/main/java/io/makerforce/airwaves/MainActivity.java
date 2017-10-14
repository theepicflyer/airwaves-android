package io.makerforce.airwaves;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {
    String ip = "http://172.22.152.16:10202";
    long diff;
    final int delay = 1000;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(ip);
        } catch (URISyntaxException e) {
        }
    }
    final Handler handler = new Handler();

    MediaPlayer mediaPlayer = new MediaPlayer();

    static String TAG = "Airwaves";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        try {
//            mediaPlayer.setDataSource("/storage/emulated/0/Music/Adele 25 Delux FLAC/01 Hello.flac");
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        mediaPlayer = MediaPlayer.create(this, R.raw.test);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button joinButton = (Button) findViewById(R.id.joinButton);
        final EditText groupIDText = (EditText) findViewById(R.id.groupIDText);
        joinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mSocket.emit("joingroup", groupIDText.getText());
            }
        });
        Log.d(TAG, "Before Connect");
         //Move this where needed
        mSocket.on("timepong", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                diff = SystemClock.uptimeMillis() - (int) args[0];
                Log.d(TAG, "Diff: " + Long.toString(diff));
            }
        });
        mSocket.on("play", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.start();
                    }
                }, delay - diff -  (int) args[0]);
                Log.d(TAG, "Play?");

            }
        });
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.d(TAG, "Connected, emitting");
                mSocket.emit("timeping", SystemClock.uptimeMillis());
            }
        });
        //send out play

        mSocket.connect();
    }

    public Emitter.Listener onPong = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            //Do something
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
}

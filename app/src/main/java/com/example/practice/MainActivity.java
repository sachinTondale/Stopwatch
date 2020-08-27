package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int Second=0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null)
        {
            Second=savedInstanceState.getInt("Second");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }

        Runtimer();

    }
    @Override
    protected void onResume()
    {
        super.onResume();

        if (wasRunning)
        {
            running=true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("Second",Second);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning=true;
        running=false;
    }

    private void Runtimer() {

        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int Hour=Second/3600;
                int Minute=(Second%3600)/60;
                int second =Second%60;
                TextView textView=(TextView)findViewById(R.id.Timer);
                String Time=String.format(Locale.getDefault(),"%d:%02d:%02d",Hour,Minute,second);
                textView.setText(Time);

                if (running)
                {
                    Second++;
                }
                handler.postDelayed(this,1000);


            }

        });
    }

    public void Start_timer(View view) {
        running=true;
    }

    public void Stop_timer(View view) {
        running=false;
    }

    public void Reset_timer(View view) {
        running=false;
        Second=0;
    }
}

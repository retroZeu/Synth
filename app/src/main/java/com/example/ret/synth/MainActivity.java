package com.example.ret.synth;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cButton, dButton, eButton, fButton, gButton, aButton, bButton, cHighButton, challenge2, twinkle;
    private MediaPlayer cNote, dNote, eNote, fNote, gNote, aNote, bNote, cHighNote;
    private MediaPlayer[] mediaArray;
    private NumberPicker note, times;
    private final short WHOLE_NOTE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNumberPickers();
        wireWidgets();
        setListeners();
        createMediaPlayers();
        mediaArray = new MediaPlayer[] {cNote, dNote, eNote, fNote, gNote, aNote, bNote, cHighNote};
    }

    private void createMediaPlayers() {
        cNote = MediaPlayer.create(this, R.raw.scalec);
        dNote = MediaPlayer.create(this, R.raw.scaled);
        eNote = MediaPlayer.create(this, R.raw.scalee);
        fNote = MediaPlayer.create(this, R.raw.scalef);
        gNote = MediaPlayer.create(this, R.raw.scaleg);
        aNote = MediaPlayer.create(this, R.raw.scalehigha);
        bNote = MediaPlayer.create(this, R.raw.scalehighb);
        cHighNote = MediaPlayer.create(this, R.raw.scalehighc);
    }

    public void setNumberPickers() {
        note = (NumberPicker) findViewById(R.id.numberPicker_note);
        note.setMinValue(0); note.setMaxValue(7);
        note.setDisplayedValues( new String[] {"C", "D", "E", "F", "G", "A", "B", "^C"} );

        times = (NumberPicker) findViewById(R.id.numberPicker_time);
        times.setMinValue(0); times.setMaxValue(9);
    }

    private void setListeners() {


        twinkle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int[] part1 = new int[] {1,1,5,5,6,6,5,4,4,3,3,2,2,1};
                int[] part2 = new int[] {5,5,4,4,3,3,2};

                int startIndex = 0; //startIndex

                //THE SONG
                while (startIndex < part1.length) {
                    play(part1[startIndex]-1);
                    delayPlaying(WHOLE_NOTE);
                    startIndex++;
                }
                delayPlaying(WHOLE_NOTE);
                for (int i = 0; i < 2; i++) { //play twice
                    startIndex = 0;
                    while (startIndex < part2.length) {
                        play(part2[startIndex] - 1);
                        delayPlaying(WHOLE_NOTE);
                        startIndex++;
                    }
                    delayPlaying(WHOLE_NOTE);
                    i++;
                }
                delayPlaying(WHOLE_NOTE);
                startIndex = 0;
                while (startIndex < part1.length) {
                    play(part1[startIndex]-1);
                    delayPlaying(WHOLE_NOTE);
                    startIndex++;
                }
            }
        });


        challenge2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int num = note.getValue();
                int x = times.getValue();

                while (x > 0) {
                    play(num);
                    delayPlaying(WHOLE_NOTE);
                    x--;
                }
            }
        });


        cButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
        fButton.setOnClickListener(this);
        gButton.setOnClickListener(this);
        aButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        cHighButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        twinkle = (Button) findViewById(R.id.button_twinkle);
        challenge2 = (Button) findViewById(R.id.button_challenge_2);
        cButton = (Button) findViewById(R.id.button_c);
        dButton = (Button) findViewById(R.id.button_d);
        eButton = (Button) findViewById(R.id.button_e);
        fButton = (Button) findViewById(R.id.button_f);
        gButton = (Button) findViewById(R.id.button_g);
        aButton = (Button) findViewById(R.id.button_a);
        bButton = (Button) findViewById(R.id.button_b);
        cHighButton = (Button) findViewById(R.id.button_c_high);
    }

    private void delayPlaying(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Log.e("SynthesizerActivity","Audio playback interrupted");
        }
    }

    public void play(int no) {
        mediaArray[no].seekTo(0);
        mediaArray[no].start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_c:
                play(0);
                break;
            case R.id.button_d:
                play(1);
                break;
            case R.id.button_e:
                play(2);
                break;
            case R.id.button_f:
                play(3);
                break;
            case R.id.button_g:
                play(4);
                break;
            case R.id.button_a:
                play(5);
                break;
            case R.id.button_b:
                play(6);
                break;
            case R.id.button_c_high:
                play(7);
                break;
            default:
                //something
        }
    }



}

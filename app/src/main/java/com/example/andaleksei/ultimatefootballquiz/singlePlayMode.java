package com.example.andaleksei.ultimatefootballquiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class singlePlayMode extends AppCompatActivity {

    private dataBase database;

    private final String drawable = "drawable";
    private final String COINS = "coins";

    private void updateProgressBars() {
        dataBase database = new dataBase(this);

        List<ProgressBar> bars = new ArrayList<ProgressBar>();

        bars.add((ProgressBar) findViewById(R.id.progressBarFootballers));
        bars.add((ProgressBar) findViewById(R.id.progressBarClubs));
        bars.add((ProgressBar) findViewById(R.id.progressBarTransfers));
        bars.add((ProgressBar) findViewById(R.id.progressBarLegends));

        for (int i = 0; i < bars.size(); i++) {
            Integer completedItems = database.getNumberOfCompletedItems(i + 1);
            Integer numberOfAllItems = database.getNumberOfElements(i + 1);
            Double result = completedItems.doubleValue() / numberOfAllItems * 100;
            bars.get(i).setProgress(result.intValue());
        }
    }

    private void setFont() {

        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                getResources().getString(R.string.font));

        TextView textview = (TextView) findViewById(R.id.textViewFootballers);

        textview.setTypeface(custom_font);

        textview = (TextView) findViewById(R.id.textViewClubs);

        textview.setTypeface(custom_font);

        textview = (TextView) findViewById(R.id.textViewTransfers);

        textview.setTypeface(custom_font);

        textview = (TextView) findViewById(R.id.textViewLegends);

        textview.setTypeface(custom_font);
    }

    private void setPhotoToLegends() {
        LinearLayout legendContainer = (LinearLayout) findViewById(R.id.legend);
        ImageView legendPhoto = (ImageView) findViewById(R.id.legendPhoto);

        if (database.getLastUnlockedItem("legendTable") >= 1) {
            legendContainer.setBackgroundColor(Color.parseColor("#FDD835"));
            legendPhoto.setImageResource(R.drawable.henry);

            legendContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent singlePlayMenuIntent = new Intent(singlePlayMode.this, singlePlayMenu.class);

                    singlePlayMenuIntent.putExtra("game mode", 4);

                    startActivity(singlePlayMenuIntent);
                }
            });
        } else {
            legendContainer.setBackgroundColor(Color.TRANSPARENT);
            legendPhoto.setImageResource(R.drawable.lock);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play_mode);

        database = new dataBase(this);

        updateProgressBars();

        setFont();

        setPhotoToLegends();

        LinearLayout playersButton = (LinearLayout) findViewById(R.id.players);
        playersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singlePlayMenuIntent = new Intent(singlePlayMode.this, singlePlayMenu.class);

                singlePlayMenuIntent.putExtra("game mode", 1);

                startActivity(singlePlayMenuIntent);
            }
        });

        LinearLayout  clubsButton = (LinearLayout) findViewById(R.id.clubs);
        clubsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singlePlayMenuIntent = new Intent(singlePlayMode.this, singlePlayMenu.class);

                singlePlayMenuIntent.putExtra("game mode", 2);

                startActivity(singlePlayMenuIntent);
            }
        });

        LinearLayout  transferButton = (LinearLayout) findViewById(R.id.transfer);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singlePlayMenuIntent = new Intent(singlePlayMode.this, singlePlayMenu.class);

                singlePlayMenuIntent.putExtra("game mode", 3);

                startActivity(singlePlayMenuIntent);
            }
        });

        ImageView settings = (ImageView) findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(singlePlayMode.this, settings.class);

                startActivity(settingsIntent);
            }
        });

        LinearLayout coinsContainer = (LinearLayout) findViewById(R.id.coinsContainer);

        coinsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coinsIntent = new Intent(singlePlayMode.this, coins.class);

                startActivity(coinsIntent);
            }
        });

        TextView coins = (TextView) findViewById(R.id.coins);

        coins.setText("" + database.getVariableValue(COINS));
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateProgressBars();

        setPhotoToLegends();

        TextView coins = (TextView) findViewById(R.id.coins);

        coins.setText("" + database.getVariableValue(COINS));
    }
}

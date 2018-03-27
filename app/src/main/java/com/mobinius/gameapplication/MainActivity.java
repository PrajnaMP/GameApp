package com.mobinius.gameapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobinius.gameapplication.fragments.DealerFragment;
import com.mobinius.gameapplication.fragments.PlayerFourFragment;
import com.mobinius.gameapplication.fragments.PlayerOneFragment;
import com.mobinius.gameapplication.fragments.PlayerThreeFragment;
import com.mobinius.gameapplication.fragments.PlayerTwoFragment;
import com.mobinius.gameapplication.model.PlayerClass;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.sql.Array;

public class MainActivity extends AppCompatActivity {

    public static TextView mDealt;
    public static ImageView mDealtImageView;
    public static Bus bus;
    public static TextView mWinner;
    public static TextView mNewGame;

    public enum Card {
        Spade(0),
        Club(1),
        Heart(2),
        Diamond(3),
        Joker(4);
        private int value;

        Card(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDealt = (TextView) findViewById(R.id.dealt_text_view);
        mDealtImageView = (ImageView) findViewById(R.id.dealt_image_view);
        mWinner = (TextView) findViewById(R.id.winner_text_view);
        mNewGame = (TextView) findViewById(R.id.new_game_text_view);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartActivity(MainActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe
    public void getMessage(Integer n) {
        int aCardType = DealerFragment.cards[n];
        Card cardType = Card.values()[aCardType];
        int imageId = 0;
        switch (cardType) {
            case Spade:
                imageId = R.drawable.spade_image;
                break;

            case Club:
                imageId = R.drawable.club_image;
                break;

            case Heart:
                imageId = R.drawable.heart_image;
                break;

            case Diamond:
                imageId = R.drawable.diamond_image;
                break;

            default:
                imageId = R.drawable.joker_image;
                break;
        }
        mDealtImageView.setImageResource(imageId);
        updateScore(n);
    }

    private void updateScore(Integer n) {

        int aCardType = DealerFragment.cards[n];
        Card cardType = Card.values()[aCardType];

        switch (cardType) {
            case Spade:
                PlayerOneFragment.spadeBus.post(1);
                break;
            case Club:
                PlayerTwoFragment.clubBus.post(1);

                break;
            case Heart:
                PlayerThreeFragment.heartBus.post(1);

                break;
            case Diamond:
                PlayerFourFragment.diamondBus.post(1);

                break;
            case Joker:
                PlayerOneFragment.spadeBus.post(1);
                PlayerTwoFragment.clubBus.post(1);
                PlayerThreeFragment.heartBus.post(1);
                PlayerFourFragment.diamondBus.post(1);
                break;
        }
    }

    public void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
}



















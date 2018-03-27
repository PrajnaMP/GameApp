package com.mobinius.gameapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobinius.gameapplication.R;
import com.mobinius.gameapplication.model.PlayerClass;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by prajna on 19/7/17.
 */

public class PlayerThreeFragment extends Fragment {
    private View mParentView;
    private TextView mPlayer;
    private ImageView mHeartImageView;
    private TextView mScore;
    public static Bus heartBus;
    PlayerClass playerClass;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        playerClass=createDefaultPlayer() ;
        mParentView = inflater.inflate(R.layout.fragment_three, container);
        mPlayer = (TextView) mParentView.findViewById(R.id.player_three_text_view);
        mHeartImageView = (ImageView) mParentView.findViewById(R.id.player_three_image_view);
        mScore = (TextView) mParentView.findViewById(R.id.player_three_score_view);

        mPlayer.setText(playerClass.getName());
        mHeartImageView.setImageResource(playerClass.getCardShape());
        mScore.setText(""+playerClass.getScore());
        return mParentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        heartBus = new Bus(ThreadEnforcer.MAIN);
        heartBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        heartBus.unregister(this);
    }

    @Subscribe
    public void getHeartScore(Integer n) {
        Integer scoreValue = playerClass.getScore();
        int latestScore = scoreValue - n;
        if (latestScore == 0) {
            playerClass.setScore(latestScore);
            mScore.setText("" + playerClass.getScore());
            DealerFragment.makeDealerInvisible("Player 3");
        } else {
            playerClass.setScore(latestScore);
            mScore.setText("" + playerClass.getScore());
        }
    }

    private PlayerClass createDefaultPlayer() {
        PlayerClass playerClass=new PlayerClass("Player 3",R.drawable.heart_image,10);
        return playerClass;
    }
}

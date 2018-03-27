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
 * Created by prajna on 18/7/17.
 */

public class PlayerOneFragment extends Fragment {
    private View mParentView;
    private TextView mPlayer;
    private ImageView mSpadeImageView;
    private TextView mScore;
    public static Bus spadeBus;
    PlayerClass playerClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        playerClass=createDefaultPlayer() ;
        mParentView = inflater.inflate(R.layout.fragment_one, container);
        mPlayer = (TextView) mParentView.findViewById(R.id.player_one_text_view);
        mSpadeImageView = (ImageView) mParentView.findViewById(R.id.player_one_image_view);
        mScore = (TextView) mParentView.findViewById(R.id.player_one_score_view);
        mPlayer.setText(playerClass.getName());
        mSpadeImageView.setImageResource(playerClass.getCardShape());
        mScore.setText(""+playerClass.getScore());
        return mParentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        spadeBus = new Bus(ThreadEnforcer.MAIN);
        spadeBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        spadeBus.unregister(this);
    }

    @Subscribe
    public void getSpadeScore(Integer n) {
        Integer scoreValue = playerClass.getScore();
        int latestScore = scoreValue - n;
        if (latestScore == 0) {
            playerClass.setScore(latestScore);
            mScore.setText(""+playerClass.getScore());
            DealerFragment.makeDealerInvisible("Player 1");
        } else {
            playerClass.setScore(latestScore);
            mScore.setText(""+playerClass.getScore());
        }
    }

    private PlayerClass createDefaultPlayer() {
        PlayerClass playerClass=new PlayerClass("Player 1",R.drawable.spade_image,10);
        return playerClass;
    }
}

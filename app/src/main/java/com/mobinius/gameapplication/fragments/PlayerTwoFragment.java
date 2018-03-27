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

public class PlayerTwoFragment extends Fragment {
    private View mParentView;
    private TextView mPlayer;
    private ImageView mClubImageView;
    private TextView mScore;
    public static Bus clubBus;
    PlayerClass playerClass;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        playerClass=createDefaultPlayer() ;
        mParentView = inflater.inflate(R.layout.fragment_two, container);
        mPlayer = (TextView) mParentView.findViewById(R.id.player_two_text_view);
        mClubImageView = (ImageView) mParentView.findViewById(R.id.player_two_image_view);
        mScore = (TextView) mParentView.findViewById(R.id.player_two_score_view);

        mPlayer.setText(playerClass.getName());
        mClubImageView.setImageResource(playerClass.getCardShape());
        mScore.setText(""+playerClass.getScore());
        return mParentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        clubBus = new Bus(ThreadEnforcer.MAIN);
        clubBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        clubBus.unregister(this);
    }

    @Subscribe
    public void getClubScore(Integer n) {
        Integer scoreValue = playerClass.getScore();
        int latestScore = scoreValue - n;
        if (latestScore == 0) {
            playerClass.setScore(latestScore);
            mScore.setText("" +playerClass.getScore());
            DealerFragment.makeDealerInvisible("Player 2");
        } else {
            playerClass.setScore(latestScore);
            mScore.setText("" + playerClass.getScore());
        }
    }

    private PlayerClass createDefaultPlayer() {
    PlayerClass playerClass=new PlayerClass("Player 2",R.drawable.club_image,10);
        return playerClass;
    }
}

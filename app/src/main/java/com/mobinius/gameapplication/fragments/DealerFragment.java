package com.mobinius.gameapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobinius.gameapplication.MainActivity;
import com.mobinius.gameapplication.R;

import java.util.Random;

/**
 * Created by prajna on 19/7/17.
 */

public class DealerFragment extends Fragment {
    private static TextView mDealer;
    private static Button mDraw;
    private View mParentView;
    public static Integer[] cards = {MainActivity.Card.Spade.getValue(), MainActivity.Card.Club.getValue(), MainActivity.Card.Heart.getValue(),
            MainActivity.Card.Diamond.getValue(), MainActivity.Card.Joker.getValue()};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_dealer, container);
        mDealer = (TextView) mParentView.findViewById(R.id.dealer_text_view);
        mDraw = (Button) mParentView.findViewById(R.id.draw_button);
        mDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max=4;
                int min=0;
                int diff=max-min;
                Random rn = new Random();
                int i = rn.nextInt(diff+1);
                i+=min;
                MainActivity.bus.post(i);
            }
        });
        return mParentView;
    }

    public static void makeDealerInvisible(String winner) {
        mDealer.setVisibility(View.INVISIBLE);
        mDraw.setVisibility(View.INVISIBLE);
        MainActivity.mDealt.setVisibility(View.INVISIBLE);
        MainActivity.mDealtImageView.setVisibility(View.INVISIBLE);
        MainActivity.mWinner.setVisibility(View.VISIBLE);
        MainActivity.mNewGame.setVisibility(View.VISIBLE);
        MainActivity.mWinner.setText(winner + " has won the Game");
    }
}

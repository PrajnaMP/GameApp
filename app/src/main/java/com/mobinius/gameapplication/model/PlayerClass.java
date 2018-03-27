package com.mobinius.gameapplication.model;

import com.mobinius.gameapplication.R;

/**
 * Created by prajna on 19/7/17.
 */

public class PlayerClass {
    private String name;
    private int cardShape;
    private int score;

    public PlayerClass() {

    }

    public PlayerClass(String name, int cardShape, int score) {
        this.name = name;
        this.cardShape = cardShape;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardShape() {
        return cardShape;
    }

    public void setCardShape(int cardShape) {
        this.cardShape = cardShape;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

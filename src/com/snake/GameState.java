package com.snake;

import javax.swing.*;
import java.awt.*;

public class GameState {

    private Timer timer;
    private Game game;
    private int scale = 10;
    private boolean over = false;
    private boolean paused = false;
    private int time = 0;
    private int score = 0;
    private int ticks = 0;


    public GameState(Game game) {
        this.game = game;
    }

    public Dimension getDim() {
        return game.getDim();
    }

    public void handleSnakeKeyEvent() {
        if (over)
            game.start();
        else
            paused = !paused;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getTime() {
        return time;
    }

    public void incrementTime() {
        this.time++;
    }

    public int getScore() {
        return score;
    }

    public void incrementScoreBy(int score) {
        this.score += score;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getTicks() {
        return ticks;
    }

    public void incrementTicks() {
        this.ticks++;
    }

    public int getScale() {
        return scale;
    }

    public Game getGame() {
        return game;
    }
}

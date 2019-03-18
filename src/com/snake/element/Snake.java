package com.snake.element;

import com.snake.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {

    private GameState gameState;

    public ArrayList<Point> snakeParts = new ArrayList<>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    public int direction = DOWN, tailLength = 10;

    public Point head;

    public Snake(GameState gameState) {
        this.gameState = gameState;
        reset();
    }

    public void reset() {
        tailLength = 3;
        direction = DOWN;
        head = new Point(0, -1);
        snakeParts.clear();
        for (int i = 0; i < tailLength; i++) {
            snakeParts.add(new Point(head.x, head.y));
        }
    }

    public void handleGameAction(Cherry cherry) {
        gameState.incrementTicks();

        if (gameState.getTicks() % 5 == 0 && head != null && !gameState.isOver() && !gameState.isPaused()) {

            gameState.incrementTime();

            snakeParts.add(new Point(head.x, head.y));

            if (snakeParts.size() > tailLength)
                snakeParts.remove(0);

            if (direction == UP)

                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                    head = new Point(head.x, head.y - 1);
                else
                    gameState.setOver(true);

            if (direction == DOWN)

                if (head.y + 1 <= 55 && noTailAt(head.x, head.y + 1))
                    head = new Point(head.x, head.y + 1);
                else
                    gameState.setOver(true);

            if (direction == LEFT)

                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                    head = new Point(head.x - 1, head.y);
                else
                    gameState.setOver(true);

            if (direction == RIGHT)

                if (head.x + 1 <= 58 && noTailAt(head.x + 1, head.y))
                    head = new Point(head.x + 1, head.y);
                else
                    gameState.setOver(true);

            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);

            }
            checkCherryCollision(cherry);
        }
    }

    private void checkCherryCollision(Cherry cherry) {
        if (cherry != null) {
            if (head.equals(cherry)) {
                gameState.incrementScoreBy(10);
                tailLength++;
                cherry.randomizeLocation();
            }
        }
    }

    public boolean noTailAt(int x, int y) {

        for (Point point : snakeParts) {

            if (point.equals(new Point(x, y)))
                return false;
        }

        return true;
    }


    @Override
    public void keyPressed(KeyEvent e) {

        int i = e.getKeyCode();

        if (i == KeyEvent.VK_LEFT && direction != RIGHT)
            direction = LEFT;

        if (i == KeyEvent.VK_RIGHT && direction != LEFT)
            direction = RIGHT;

        if (i == KeyEvent.VK_UP && direction != DOWN)
            direction = UP;

        if (i == KeyEvent.VK_DOWN && direction != UP)
            direction = DOWN;

        if (i == KeyEvent.VK_SPACE)

            gameState.handleSnakeKeyEvent();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

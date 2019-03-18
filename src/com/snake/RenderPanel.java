package com.snake;

import com.snake.element.Snake;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    public static Color color = new Color(5402);

    private GameState gameState;
    private Snake snake;

    public RenderPanel(GameState gameState){
        this.gameState = gameState;
    };

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(color);

        g.fillRect(0, 0, 605, 600);

        g.setColor(Color.ORANGE);

        for (Point point : snake.snakeParts) {
            g.fillRect(point.x * gameState.getScale(), point.y * gameState.getScale(), gameState.getScale(), gameState.getScale());
        }
        g.fillRect(snake.head.x * gameState.getScale(), snake.head.y * gameState.getScale(), gameState.getScale(), gameState.getScale());

        g.setColor(Color.RED);

        g.fillRect(gameState.getGame().getCherry().x * gameState.getScale(), gameState.getGame().getCherry().y * gameState.getScale(), gameState.getScale(), gameState.getScale());

        String string = "Score: " + gameState.getScore() + ", Length: " + snake.tailLength + ", Time: " + gameState.getTime() / 20;

        g.setColor(Color.white);

        g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

        string = "Game Over!";

        if (gameState.isOver()) {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) gameState.getDim().getHeight() / 4);
        }

        string = "Paused!";

        if (gameState.isPaused() && !gameState.isOver()) {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) gameState.getDim().getHeight() / 4);
        }
    }

}

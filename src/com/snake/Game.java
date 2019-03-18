package com.snake;

import com.snake.element.Cherry;
import com.snake.element.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {

    private Dimension dim;

    GameState gameState;
    private RenderPanel renderPanel;
    private Snake snake;
    private Cherry cherry;

    public Game(String frameName) {
        super(frameName);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setVisible(true);
        setSize(605, 600);
        setResizable(false);
        setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Game game = new Game("Snake");
        game.init();
        game.start();
    }

    public void init() {
        gameState = new GameState(this);
        add(renderPanel = new RenderPanel(gameState));
        cherry = new Cherry();
        snake = new Snake(gameState);
        renderPanel.setSnake(snake);
        addKeyListener(snake);
        gameState.setTimer(new Timer(20, this));
    }

    public void start() {
        this.snake.reset();
        gameState.getTimer().start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        snake.handleGameAction(cherry);
    }

    public Dimension getDim() {
        return dim;
    }

    public Cherry getCherry() {
        return cherry;
    }
}

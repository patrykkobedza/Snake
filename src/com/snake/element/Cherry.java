package com.snake.element;

import java.awt.*;
import java.util.Random;

public class Cherry extends Point {

    private static Random random = new Random();

    public Cherry() {
        super(random.nextInt(58), random.nextInt(55));
    }

    public void randomizeLocation(){
        setLocation(random.nextInt(57), random.nextInt(54));
    }
}

package com.github.svart63.dc.service;

import com.github.svart63.dc.events.MouseMoveEvent;
import lombok.SneakyThrows;

import java.awt.*;

public class MouseMoveService implements EventService<MouseMoveEvent> {
    private static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private final Robot robot;

    @SneakyThrows
    public MouseMoveService() {
        robot = new Robot();
    }

    @SneakyThrows
    @Override
    public synchronized void doAction(MouseMoveEvent event) {
        Point location = MouseInfo.getPointerInfo().getLocation();
        int moveToX = calcPosition(event.getX(), location.x, WIDTH);
        int moveToY = calcPosition(event.getY(), location.y, HEIGHT);
        robot.mouseMove(moveToX, moveToY);
    }

    private int calcPosition(int eventPos, int currentPos, double screenLimit) {
        int newPos = currentPos + eventPos;
        int moveTo = currentPos;
        if (newPos < screenLimit && newPos > 0) {
            moveTo = newPos;
        }
        return moveTo;
    }
}

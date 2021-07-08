package com.github.svart63.dc.service;

import com.github.svart63.dc.events.MouseMoveEvent;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service("mouseMoveService")
public class MouseMoveService implements EventService<MouseMoveEvent> {
    private static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double smoothCoefficient = 0.3d;
    private final Robot robot;

    @SneakyThrows
    public MouseMoveService() {
        robot = new Robot();
    }

    @SneakyThrows
    @Override
    public synchronized void doAction(MouseMoveEvent event) {
        Point location = MouseInfo.getPointerInfo().getLocation();
        int moveToX = calcPosition(smooth(event.getX()), location.x, WIDTH);
        int moveToY = calcPosition(smooth(event.getY()), location.y, HEIGHT);
        robot.mouseMove(moveToX, moveToY);
    }

    private int smooth(int value) {
        return ((Double) (value * smoothCoefficient)).intValue();
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

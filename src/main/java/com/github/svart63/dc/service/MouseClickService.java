package com.github.svart63.dc.service;

import com.github.svart63.dc.events.MouseClickEvent;
import com.github.svart63.dc.events.MouseMoveEvent;
import lombok.SneakyThrows;

import java.awt.*;

public class MouseClickService implements EventService<MouseClickEvent> {
    private final Robot robot;

    @SneakyThrows
    public MouseClickService() {
        robot = new Robot();
    }

    @Override
    public void doAction(MouseClickEvent event) {
        robot.mousePress(event.getMouseButton().buttonCode());
        robot.mouseRelease(event.getMouseButton().buttonCode());
    }
}

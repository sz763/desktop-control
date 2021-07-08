package com.github.svart63.dc.service;

import com.github.svart63.dc.events.MouseClickEvent;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.awt.*;
@Service("mouseClickService")
public class MouseClickService implements EventService<MouseClickEvent> {
    private final Robot robot;

    @SneakyThrows
    public MouseClickService() {
        robot = new Robot();
    }

    @Override
    public void doAction(MouseClickEvent event) {
        robot.mousePress(event.getMbtn().buttonCode());
        robot.mouseRelease(event.getMbtn().buttonCode());
    }
}

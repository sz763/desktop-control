package com.github.svart63.dc.service;

import com.github.svart63.dc.events.KeyboardEvent;
import lombok.SneakyThrows;

import java.awt.*;

public class KeyboardService implements EventService<KeyboardEvent> {
    private final Robot robot;
    @SneakyThrows
    public KeyboardService() {
        robot = new Robot();
    }

    @Override
    public void doAction(KeyboardEvent event) {

        char key = event.getKey();
        robot.keyPress(key);
        robot.keyRelease(key);
    }
}

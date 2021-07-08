package com.github.svart63.dc.service;

import com.github.svart63.dc.events.KeyboardEvent;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.KeyEvent;
@Service("keyboardService")
public class KeyboardService implements EventService<KeyboardEvent> {
    private final Robot robot;

    @SneakyThrows
    public KeyboardService() {
        robot = new Robot();
    }

    @Override
    public void doAction(KeyboardEvent event) {
        var chars = event.getTxt().toCharArray();
        for (char aChar : chars) {
            int keycode = KeyEvent.getExtendedKeyCodeForChar(aChar);
            robot.keyPress(keycode);
            robot.keyRelease(keycode);
        }
    }
}

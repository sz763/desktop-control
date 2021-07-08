package com.github.svart63.dc.service;

import com.github.svart63.dc.events.BackspaceEvent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
@Service("backspaceService")
public class BackspaceService implements EventService<BackspaceEvent> {
    private final Robot robot;

    @SneakyThrows
    public BackspaceService() {
        robot = new Robot();
    }

    @Override
    public void doAction(BackspaceEvent event) {
        robot.keyPress(VK_BACK_SPACE);
        robot.keyRelease(VK_BACK_SPACE);
    }
}

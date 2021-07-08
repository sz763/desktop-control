package com.github.svart63.dc.controller;

import com.github.svart63.dc.events.BackspaceEvent;
import com.github.svart63.dc.events.KeyboardEvent;
import com.github.svart63.dc.events.MouseClickEvent;
import com.github.svart63.dc.events.MouseMoveEvent;
import com.github.svart63.dc.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class KeyboardEventController {
    private final EventService<KeyboardEvent> keyboardService;
    private final EventService<BackspaceEvent> backspaceService;

    public KeyboardEventController(
            @Qualifier("keyboardService") EventService<KeyboardEvent> keyboardService,
            @Qualifier("backspaceService") EventService<BackspaceEvent> backspaceService) {
        this.keyboardService = keyboardService;
        this.backspaceService = backspaceService;
    }

    @MessageMapping("/keyboard/text")
    public void handleMouseMove(KeyboardEvent event) {
        log.debug("Handle event mouse move: {}", event);
        keyboardService.doAction(event);
    }

    @MessageMapping("/keyboard/backspace")
    public void handleMouseClick(BackspaceEvent event) {
        log.debug("Handle event mouse click: {}", event);
        backspaceService.doAction(event);
    }

}

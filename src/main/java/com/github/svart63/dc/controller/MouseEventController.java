package com.github.svart63.dc.controller;

import com.github.svart63.dc.events.MouseClickEvent;
import com.github.svart63.dc.events.MouseMoveEvent;
import com.github.svart63.dc.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MouseEventController {
    private final EventService<MouseMoveEvent> mouseMoveService;
    private final EventService<MouseClickEvent> mouseClickService;

    public MouseEventController(
            @Qualifier("mouseMoveService") EventService<MouseMoveEvent> eventService,
            @Qualifier("mouseClickService") EventService<MouseClickEvent> mouseClickService) {
        this.mouseMoveService = eventService;
        this.mouseClickService = mouseClickService;
    }

    @MessageMapping("/mouse/move")
    public void handleMouseMove(MouseMoveEvent event) {
        log.debug("Handle event mouse move: {}", event);
        mouseMoveService.doAction(event);
    }

    @MessageMapping("/mouse/click")
    public void handleMouseClick(MouseClickEvent event) {
        log.debug("Handle event mouse click: {}", event);
        mouseClickService.doAction(event);
    }

}

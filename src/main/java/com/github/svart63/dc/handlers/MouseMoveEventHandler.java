package com.github.svart63.dc.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.svart63.dc.events.MouseMoveEvent;
import com.github.svart63.dc.service.EventService;
import com.github.svart63.dc.service.MouseMoveService;

public class MouseMoveEventHandler extends AbstractJsonEvent<MouseMoveEvent> {
    private final EventService<MouseMoveEvent> mouseMoveService = new MouseMoveService();

    @Override
    protected TypeReference<MouseMoveEvent> getTypeRef() {
        return new TypeReference<>() {
        };
    }

    @Override
    public void handle(MouseMoveEvent event) {
        mouseMoveService.doAction(event);
    }
}

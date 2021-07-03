package com.github.svart63.dc.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.svart63.dc.events.MouseClickEvent;
import com.github.svart63.dc.service.EventService;
import com.github.svart63.dc.service.MouseClickService;

public class MouseClickEventHandler extends AbstractJsonEvent<MouseClickEvent> {
    private final EventService<MouseClickEvent> mouseClickService = new MouseClickService();

    @Override
    protected TypeReference<MouseClickEvent> getTypeRef() {
        return new TypeReference<>() {
        };
    }

    @Override
    public void handle(MouseClickEvent event) {
        mouseClickService.doAction(event);
    }
}

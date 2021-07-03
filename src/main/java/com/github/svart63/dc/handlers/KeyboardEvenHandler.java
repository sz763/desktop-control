package com.github.svart63.dc.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.svart63.dc.events.KeyboardEvent;
import com.github.svart63.dc.service.EventService;
import com.github.svart63.dc.service.KeyboardService;

public class KeyboardEvenHandler extends AbstractJsonEvent<KeyboardEvent> {
    private final EventService<KeyboardEvent> keyboardService = new KeyboardService();
    @Override
    protected TypeReference<KeyboardEvent> getTypeRef() {
        return new TypeReference<>() {
        };
    }

    @Override
    public void handle(KeyboardEvent event) {
        keyboardService.doAction(event);
    }
}
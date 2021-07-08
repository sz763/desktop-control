package com.github.svart63.dc.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.svart63.dc.events.BackspaceEvent;
import com.github.svart63.dc.service.BackspaceService;
import com.github.svart63.dc.service.EventService;

public class BackspaceEventHandler extends AbstractJsonEvent<BackspaceEvent> {
    private EventService<BackspaceEvent> eventService = new BackspaceService();
    private final TypeReference<BackspaceEvent> typeReference = new TypeReference<>() {
    };

    @Override
    protected TypeReference<BackspaceEvent> getTypeRef() {

        return typeReference;
    }

    @Override
    public void handle(BackspaceEvent event) {
        eventService.doAction(event);
    }
}

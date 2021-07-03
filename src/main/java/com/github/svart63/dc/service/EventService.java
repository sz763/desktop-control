package com.github.svart63.dc.service;

import com.github.svart63.dc.events.DesktopEvent;

public interface EventService<T extends DesktopEvent> {
    void doAction(T event);
}

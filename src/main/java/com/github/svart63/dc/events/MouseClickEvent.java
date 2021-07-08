package com.github.svart63.dc.events;

import lombok.Data;

@Data
public class MouseClickEvent implements DesktopEvent {
    private MouseButton mbtn;
}

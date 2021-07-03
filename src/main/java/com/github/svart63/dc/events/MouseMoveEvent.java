package com.github.svart63.dc.events;

import lombok.Data;

@Data
public class MouseMoveEvent implements DesktopEvent {
    private int x;
    private int y;
}

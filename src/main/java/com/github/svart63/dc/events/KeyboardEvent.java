package com.github.svart63.dc.events;

import lombok.Data;

@Data
public class KeyboardEvent implements DesktopEvent{
    private char key;
}

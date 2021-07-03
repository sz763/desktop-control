package com.github.svart63.dc.events;

import static java.awt.event.InputEvent.*;

public enum MouseButton {
    LEFT(BUTTON1_DOWN_MASK), RIGHT(BUTTON3_DOWN_MASK);

    private final int buttonCode;

    MouseButton(int buttonCode) {
        this.buttonCode = buttonCode;
    }

    public int buttonCode() {
        return buttonCode;
    }
}

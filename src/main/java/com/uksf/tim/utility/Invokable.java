package com.uksf.tim.utility;

import com.uksf.tim.core.Core;

public class Invokable {

    /**
     * Invokable instance
     */
    public static Invokable instance = new Invokable();

    /**
     * displays settings page, state 50
     */
    public void showSettings() {
        Core.getInstanceUI().changeState(50);
    }
}

package main.java.com.uksf.tim.utility;

import main.java.com.uksf.tim.core.Core;

public class Invokable {

    public static Invokable instance;

    public Invokable() {
        instance = this;
    }

    public void openSettings() {
        Core.getInstanceUI().changeState(50);
    }
}

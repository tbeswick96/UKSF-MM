package main.java.com.uksf.tim.gui;

import main.java.com.uksf.tim.gui.components.BottomPanel;
import main.java.com.uksf.tim.gui.components.MainPanel;
import main.java.com.uksf.tim.gui.components.SettingsPanel;

class ComponentCreator {

    static MainPanel mainPanel() {
        return new MainPanel();
    }

    static BottomPanel bottomPanel() {
        return new BottomPanel();
    }

    static SettingsPanel settingsPanel() {
        return new SettingsPanel();
    }
}

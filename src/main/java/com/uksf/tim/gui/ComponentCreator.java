package com.uksf.tim.gui;


import com.uksf.tim.gui.components.BottomPanel;
import com.uksf.tim.gui.components.MainPanel;
import com.uksf.tim.gui.components.SettingsPanel;

class ComponentCreator {

    /**
     * Creates main panel instance
     * @return main panel instance
     */
    static MainPanel mainPanel() {
        return new MainPanel();
    }

    /**
     * Creates bottom panel instance
     * @return bottom panel instance
     */
    static BottomPanel bottomPanel() {
        return new BottomPanel();
    }


    /**
     * Creates settings panel instance
     * @return settings panel instance
     */
    static SettingsPanel settingsPanel() {
        return new SettingsPanel();
    }
}
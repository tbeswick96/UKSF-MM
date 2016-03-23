/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.gui;


import com.uksf.tim.gui.components.panels.BottomPanel;
import com.uksf.tim.gui.components.panels.MainPanel;
import com.uksf.tim.gui.components.panels.SettingsPanel;
import com.uksf.tim.gui.components.panels.TopPanel;

import javax.swing.*;

/**
 * @author Tim
 */
class ComponentCreator {

    /**
     * Creates main panel instance
     * @return main panel instance
     */
    static MainPanel mainPanel() {
        return new MainPanel();
    }

	/**
	 * Creates top panel instance
	 * @return top panel instance
	 */
	static TopPanel topPanel(JFrame parent) {
		return new TopPanel(parent);
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

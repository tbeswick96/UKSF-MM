/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.gui;


import com.uksf.updater.gui.components.panels.MainPanel;

import javax.swing.*;


/**
 * @author Tim
 */
class ComponentCreator {

    /**
     * Creates main panel instance
     * @return main panel instance
     */
    static MainPanel mainPanel(JFrame parent) {
			return new MainPanel(parent);
	}
}

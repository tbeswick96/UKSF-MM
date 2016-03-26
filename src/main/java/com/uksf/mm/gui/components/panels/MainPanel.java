/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * @author Tim
 */
public class MainPanel extends JPanel {

    /**
     * Creates main panel
     */
    public MainPanel() {
        //setOpaque(true);
		setOpaque(false);
		//setBackground(COLOUR_FOREGROUND);
        setLayout(new MigLayout("fill", "0[]0", "0[]0"));
    }
}

/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tim
 */
public class MainPanel extends JScrollPane {


    /**
     * Creates main panel
     */
    public MainPanel() {
		setOpaque(false);
		getViewport().setOpaque(false);
		setBorder(null);
		setViewportBorder(null);
    }

	public void addPanel(Component panel) {
		getViewport().add(panel);
	}

	public void removePanel(Component panel) {
		getViewport().remove(panel);
	}
}

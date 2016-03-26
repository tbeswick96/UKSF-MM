/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components;

import javax.swing.*;
import java.awt.*;

import static com.uksf.mm.core.utility.Info.COLOUR_BLACK;
import static com.uksf.mm.core.utility.Info.COLOUR_FOREGROUND;
import static com.uksf.mm.core.utility.Info.FONT_STANDARD;

/**
 * @author Tim
 */
public class CustomDropdown extends JComboBox {

	public CustomDropdown() {
		setFont(new Font(FONT_STANDARD.getName(), FONT_STANDARD.getStyle(), 14));
		setBackground(COLOUR_FOREGROUND);
		setForeground(COLOUR_BLACK);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, getHeight());
	}
}

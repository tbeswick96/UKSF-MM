/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.dropdown;

import javax.swing.*;
import java.awt.*;

import static com.uksf.mm.core.utility.Info.COLOUR_TRANSPARENT;
import static com.uksf.mm.core.utility.Info.FONT_STANDARD;

/**
 * @author Tim
 */
class CustomDropdown extends JComboBox {

	CustomDropdown() {
		setFont(new Font(FONT_STANDARD.getName(), FONT_STANDARD.getStyle(), 14));
		setOpaque(false);
		setBackground(COLOUR_TRANSPARENT);
	}
	@Override public Dimension getPreferredSize() {
		return new Dimension(150, getHeight());
	}
}

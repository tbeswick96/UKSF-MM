/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import com.uksf.mm.gui.components.labels.CustomLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.mm.core.utility.Info.*;

/**
 * @author Tim
 */
public class MissionPanel extends JPanel {

	public MissionPanel() {
		setOpaque(false);
		setBackground(COLOUR_TRANSPARENT);
		setLayout(new MigLayout("fill, debug", "0[]0[]0", "0[]0[]0"));

		GenericPanel generalPanel = new GenericPanel("debug", "5[]5[]20[]5[]5", "5[]5", false, COLOUR_TRANSPARENT);
		CustomLabel nameLabel = new CustomLabel("Name", FONT_STANDARD.getStyle(), 16, false,COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		JTextField nameField = new JTextField((MISSION_SELECTED.name.split("\\."))[0]);

		generalPanel.add(nameLabel, "cell 0 0");
		generalPanel.add(nameField, "cell 1 0");

		add(generalPanel, "grow, cell 1 0");
	}
}

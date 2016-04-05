/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import com.uksf.mm.gui.components.labels.CustomLabel;
import com.uksf.mm.gui.components.labels.CustomTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.mm.core.utility.Info.*;

/**
 * @author Tim
 */
class MissionPanel extends JPanel {

	private final CustomTextField nameField, authorField;

	MissionPanel() {
		setOpaque(false); setBackground(COLOUR_TRANSPARENT); setLayout(new MigLayout("fill", "0[]0[300]0", "0[]0[]0"));

		GenericPanel generalPanel = new GenericPanel("", "5[]5[100]20[]5[100]5", "5[]5[]5", false, COLOUR_TRANSPARENT);
		CustomLabel nameLabel = new CustomLabel("Name", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		nameField = new CustomTextField((MISSION_SELECTED.name.split("\\."))[0], true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "");
		CustomLabel authorLabel = new CustomLabel("Author", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		authorField = new CustomTextField(MISSION_SELECTED.author, true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "");

		generalPanel.add(nameLabel, "cell 0 0"); generalPanel.add(nameField, "grow, cell 1 0");
		generalPanel.add(authorLabel, "cell 2 0"); generalPanel.add(authorField, "grow, cell 3 0");

		GenericPanel lobbyPanel = new GenericPanel("fill", "5[]5", "5[]5", true, COLOUR_FOREGROUND);

		add(lobbyPanel, "grow, push, cell 0 0"); add(generalPanel, "top, cell 1 0");

		functionality();
	}

	private void functionality() {
		nameField.addChangeListener(e -> {
			MISSION_SELECTED.name = nameField.getText();
			MISSION_SELECTED.path = FOLDER_MISSIONS + "/" + MISSION_SELECTED.name + "." + MISSION_SELECTED.path.split("\\.")[1];
		});
		authorField.addChangeListener(e -> MISSION_SELECTED.author = authorField.getText());
	}
}

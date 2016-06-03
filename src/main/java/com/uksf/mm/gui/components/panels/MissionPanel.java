/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import com.uksf.mm.gui.components.buttons.CustomCheckbox;
import com.uksf.mm.gui.components.labels.CustomLabel;
import com.uksf.mm.gui.components.labels.CustomTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.mm.core.utility.Info.*;

/**
 * @author Tim
 */
class MissionPanel extends JPanel {

	/**
	 * Objects for input
	 */
	private final CustomTextField nameField, authorField;
	private final CustomCheckbox binarizeCheckbox;

	/**
	 * Creates mission panel
	 */
	MissionPanel() {
		setOpaque(false);
		setBackground(COLOUR_TRANSPARENT);
		setLayout(new MigLayout("fill", "0[]0[300]0", "0[]0[]0"));

		GenericPanel lobbyPanel = new GenericPanel("", "5[]5", "5[]5", true, COLOUR_FOREGROUND);
		GenericPanel generalPanel = new GenericPanel("fill", "10[]10[]10[]10[]10", "5[]5[]5", false, COLOUR_TRANSPARENT);
		CustomLabel nameLabel = new CustomLabel("Name", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Changes mission filename");
		nameField = new CustomTextField((MISSION_SELECTED.name.split("\\."))[0], true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "Changes mission filename");
		CustomLabel authorLabel = new CustomLabel("Author", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Changes mission author");
		authorField = new CustomTextField(MISSION_SELECTED.author, true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "Changes mission author");
		CustomLabel binarizeLabel = new CustomLabel("Binarize", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "This will binarize the mission when next saved within ArmA\n(This program does not binarize missions)");
		binarizeCheckbox = new CustomCheckbox("", MISSION_SELECTED.binarized.equalsIgnoreCase("binarizationWanted=1;"), 16, false, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "This will binarize the mission when next saved within ArmA\n(This program does not binarize missions)");

		generalPanel.add(nameLabel, "cell 0 0"); generalPanel.add(nameField, "push, grow, cell 1 0, span 4");
		generalPanel.add(authorLabel, "cell 0 1"); generalPanel.add(authorField, "push, grow, cell 1 1");
		generalPanel.add(binarizeLabel, "cell 2 1"); generalPanel.add(binarizeCheckbox, "cell 3 1");

		add(lobbyPanel, "grow, push, cell 0 0");
		add(generalPanel, "top, growx, cell 1 0");

		functionality();
	}

	/**
	 * Adds functionality to objects
	 */
	private void functionality() {
		nameField.addChangeListener(e -> MISSION_SELECTED.setName(nameField.getText()));
		authorField.addChangeListener(e -> MISSION_SELECTED.setAuthor(authorField.getText()));
		binarizeCheckbox.addActionListener(e -> MISSION_SELECTED.setBinarized(binarizeCheckbox.isSelected()));
	}
}

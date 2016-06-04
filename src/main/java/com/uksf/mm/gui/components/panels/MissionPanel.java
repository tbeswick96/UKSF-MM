/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import com.uksf.mm.gui.components.buttons.CustomButton;
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
	 * Creates mission panel...messy :)
	 */
	MissionPanel() {
		setOpaque(false);
		setBackground(COLOUR_TRANSPARENT);
		setLayout(new MigLayout("fill", "0[]0[300]0", "0[]0"));

		GenericPanel lobbyPanel = new GenericPanel("fill", "5[]5", "5[]5", true, COLOUR_FOREGROUND);
		GenericPanel dataPanel = new GenericPanel("fillx", "5[]5", "0[]0[]0[]0", false, COLOUR_TRANSPARENT);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL); separator1.setBackground(COLOUR_TRANSPARENT); separator1.setForeground(COLOUR_BACKGROUND_DARK);

		GenericPanel generalPanel = new GenericPanel("fill", "10[]10[]10[]10[]10", "5[]5[]5[]5", false, COLOUR_TRANSPARENT);
		GenericPanel toolsPanel = new GenericPanel("fill", "10[]10[]10", "5[]5", false, COLOUR_TRANSPARENT);

		CustomLabel nameLabel = new CustomLabel("Name", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Changes mission filename");
		nameField = new CustomTextField(MISSION_SELECTED.name.substring(0, MISSION_SELECTED.name.lastIndexOf(".")), true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "Changes mission filename");
		CustomLabel authorLabel = new CustomLabel("Author", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Changes mission author");
		authorField = new CustomTextField(MISSION_SELECTED.author, true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "Changes mission author");
		CustomLabel binarizeLabel = new CustomLabel("Binarize", FONT_STANDARD.getStyle(), 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "This will binarize the mission when next saved within ArmA\n(This program does not binarize missions)");
		binarizeCheckbox = new CustomCheckbox("", MISSION_SELECTED.binarized.equalsIgnoreCase("binarizationWanted=1;"), 16, false, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "This will binarize the mission when next saved within ArmA\n(This program does not binarize missions)");
		CustomButton clearAddons = new CustomButton(27, 27, 1, ICON_ADDONS.getImage(), ICON_ADDONS_HOVER.getImage(), COLOUR_TRANSPARENT, "clearAddons", "Clears SQM addon list.\nEnables loading without required mods in-game");

		generalPanel.add(nameLabel, "cell 0 0"); generalPanel.add(nameField, "push, grow, cell 1 0, span 4");
		generalPanel.add(authorLabel, "cell 0 1"); generalPanel.add(authorField, "push, grow, cell 1 1");
		generalPanel.add(binarizeLabel, "cell 2 1"); generalPanel.add(binarizeCheckbox, "cell 3 1");
		toolsPanel.add(clearAddons, "cell 0 0");

		dataPanel.add(generalPanel, "grow, cell 0 0");
		dataPanel.add(separator1, "grow, cell 0 1");
		dataPanel.add(toolsPanel, "shrink, cell 0 2");

		add(lobbyPanel, "grow, push, cell 0 0");
		add(dataPanel, "grow, cell 1 0");

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

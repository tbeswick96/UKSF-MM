/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;


import com.uksf.mm.core.Core;
import com.uksf.mm.core.Settings;
import com.uksf.mm.gui.components.buttons.CustomButtonText;
import com.uksf.mm.gui.components.buttons.CustomRadioButton;
import com.uksf.mm.gui.components.labels.CustomLabel;
import com.uksf.mm.core.utility.Info;
import com.uksf.mm.core.utility.LogHandler;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class SettingsPanel extends JPanel {

	/**
	 * Settings panel objects
	 */
    private CustomRadioButton checkOnLaunch, checkWeekly, checkNever;
    private CustomButtonText updateNow;

	private JTextField folderPath;

	/**
     * Creates settings panel
     */
    public SettingsPanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("", "10[center]10[center]10", "10[center]10[center]10"));

		folderSettings();
        programSettings();
        buttonFunctionality();
    }

	/**
	 * Make program settings
	 */
    private void programSettings() {
        GenericPanel programSettings = new GenericPanel("", "5[]20[]5", "5[]5", false, COLOUR_TRANSPARENT);
        programSettings.setBorder(BORDER_STANDARD);

        CustomLabel updateCheck = new CustomLabel("Update Check", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Update Settings");
        ButtonGroup group = new ButtonGroup();
        checkOnLaunch = new CustomRadioButton("On Launch"); checkOnLaunch.setSelected(UPDATE_CHECK);
        checkWeekly = new CustomRadioButton("Weekly"); checkWeekly.setSelected(UPDATE_WEEK);
        checkNever = new CustomRadioButton("Never"); checkNever.setSelected(!UPDATE_CHECK);
        updateNow = new CustomButtonText("Update Now", FONT_STANDARD, 16, "updateNow");
        group.add(checkOnLaunch); group.add(checkWeekly); group.add(checkNever);

        GenericPanel updateButtons = new GenericPanel("", "0[]0[]0[]18[]0", "0[]0", false, COLOUR_TRANSPARENT);
        updateButtons.add(checkOnLaunch, "shrink, cell 0 0");
        updateButtons.add(checkWeekly, "shrink, cell 1 0");
        updateButtons.add(checkNever, "shrink, cell 2 0");
        updateButtons.add(updateNow, "cell 3 0");

        programSettings.add(updateCheck, "grow, cell 0 0");
        programSettings.add(updateButtons, "cell 1 0");

        add(programSettings, "al center center, grow, cell 0 1");
    }

	/**
	 * Make mission folder settings
	 */
	private void folderSettings() {
		GenericPanel folderSettings = new GenericPanel("", "5[]20[]5[]5", "5[]5", false, COLOUR_TRANSPARENT);
		folderSettings.setBorder(BORDER_STANDARD);

		CustomLabel folder = new CustomLabel("Mission Folder", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Missions are loaded from this folder");
		folderPath = new JTextField();
		folderPath.setPreferredSize(new Dimension(253, 25));
		folderPath.setText(FOLDER_MISSIONS);
		CustomButtonText folderChange = new CustomButtonText("Change", FONT_STANDARD, 16, "changeMissionsFolder");

		folderSettings.add(folder, "grow, cell 0 0");
		folderSettings.add(folderPath, "cell 1 0");
		folderSettings.add(folderChange, "cell 2 0");

		add(folderSettings, "al center center, grow, cell 0 0");
	}

	/**
	 * Add all button functionality
	 */
    private void buttonFunctionality() {
        settingsUpdate();
    }

	/**
	 * Functionality for update settings
	 */
    private void settingsUpdate() {
        checkOnLaunch.addActionListener(e -> Settings.set("update_check", true));
        checkWeekly.addActionListener(e -> Settings.setMultiple(new String[]{"update_week", "update_time"}, new Object[]{true, Settings.weekAhead()}));
        checkNever.addActionListener(e -> Settings.setMultiple(new String[]{"update_check", "update_week"}, new Object[]{false, false}));
		enableUpdate(false);
    }

	/**
	 * Switches the state of the update button in the settings panel
	 * @param state new enabled state
	 */
	public void enableUpdate(boolean state) {
		updateNow.setEnabled(state);
	}

	/**
	 * Changes missions folder
	 */
	public void changeMissionsFolder() {
		FILE_CHOOSER = new JFileChooser();
		FILE_CHOOSER.setDialogTitle("Select missions folder");
		FILE_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		boolean fileOk = false;
		while(!fileOk) {
			int returnValue = Info.FILE_CHOOSER.showOpenDialog(Core.getInstanceUI());
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFolder = FILE_CHOOSER.getSelectedFile();
				if(selectedFolder.getAbsolutePath().contains("missions")) {
					String path = selectedFolder.getAbsolutePath().replace("\\", "/");
					LogHandler.logSeverity(INFO, "Missions folder chosen: '" + path + "'");
					folderPath.setText(path);
					folderPath.setCaretPosition(0);
					Settings.set("folder_missions", path);
					fileOk = true;
				} else {
					JOptionPane.showMessageDialog(Core.getInstanceUI(), "Not a missions folder", "Invalid folder", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				fileOk = true;
			}
		}
	}
}

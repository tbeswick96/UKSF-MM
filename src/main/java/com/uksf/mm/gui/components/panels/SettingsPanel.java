/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;


import com.uksf.mm.core.Core;
import com.uksf.mm.core.Settings;
import com.uksf.mm.core.utility.Info;
import com.uksf.mm.core.utility.LogHandler;
import com.uksf.mm.core.utility.loaders.MapLoad;
import com.uksf.mm.core.utility.loaders.MissionLoad;
import com.uksf.mm.gui.components.buttons.CustomButtonText;
import com.uksf.mm.gui.components.buttons.CustomCheckbox;
import com.uksf.mm.gui.components.buttons.CustomRadioButton;
import com.uksf.mm.gui.components.labels.CustomLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static com.uksf.mm.core.utility.LogHandler.Severity.WARNING;

/**
 * @author Tim
 */
public class SettingsPanel extends JPanel {

	/**
	 * Main settings panel
	 */
	private GenericPanel mainSettingsPanel;

	/**
	 * Update settings panel objects
	 */
    private CustomRadioButton checkOnLaunch, checkWeekly, checkNever;
    private CustomButtonText updateNow;

	/**
	 * Folder settings panel objects
	 */
	private JTextField folderPath;
	private CustomCheckbox backupBox;

	/**
     * Creates settings panel
     */
    public SettingsPanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("fill", "10[]10", "10[]10[]10"));

		mainSettingsPanel = new GenericPanel("fill", "10[]10[]10", "10[]10[]10", false, COLOUR_TRANSPARENT);
		folderSettings();
        programSettings();

		add(mainSettingsPanel, "push, top, cell 0 0");
		credits();

        buttonFunctionality();
    }

	/**
	 * Make mission folder settings
	 */
	private void folderSettings() {
		GenericPanel folderSettings = new GenericPanel("", "5[]20[]5[]5", "5[]5[]5", false, COLOUR_TRANSPARENT);
		folderSettings.setBorder(BORDER_STANDARD);

		CustomLabel folder = new CustomLabel("Mission Folder", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Missions are loaded from this folder");
		folderPath = new JTextField();
		folderPath.setPreferredSize(new Dimension(247, 25));
		folderPath.setText(FOLDER_MISSIONS);
		CustomButtonText folderChange = new CustomButtonText("Change", FONT_STANDARD, 16, "changeMissionsFolder");

		CustomLabel backup = new CustomLabel("SQM Backup", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, SQM files are backed up when saving");
		backupBox = new CustomCheckbox("", SQM_BACKUP, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, SQM files are backed up when saving");

		folderSettings.add(folder, "grow, cell 0 0");
		folderSettings.add(folderPath, "cell 1 0");
		folderSettings.add(folderChange, "cell 2 0");

		folderSettings.add(backup, "grow, cell 0 1");
		folderSettings.add(backupBox, "cell 1 1");

		mainSettingsPanel.add(folderSettings, "al center center, grow, cell 0 0");
	}

	/**
	 * Make program settings
	 */
    private void programSettings() {
        GenericPanel programSettings = new GenericPanel("", "5[]20[]5", "5[]5", false, COLOUR_TRANSPARENT);
        programSettings.setBorder(BORDER_STANDARD);

        CustomLabel updateCheck = new CustomLabel("Update Check", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When to check for updates");
        ButtonGroup group = new ButtonGroup();
        checkOnLaunch = new CustomRadioButton("On Launch");
        checkWeekly = new CustomRadioButton("Weekly");
        checkNever = new CustomRadioButton("Never");
        updateNow = new CustomButtonText("Update Now", FONT_STANDARD, 16, "updateNow");
        group.add(checkOnLaunch); group.add(checkWeekly); group.add(checkNever);

        GenericPanel updateButtons = new GenericPanel("", "0[]0[]0[]18[]0", "0[]0", false, COLOUR_TRANSPARENT);
        updateButtons.add(checkOnLaunch, "shrink, cell 0 0");
        updateButtons.add(checkWeekly, "shrink, cell 1 0");
        updateButtons.add(checkNever, "shrink, cell 2 0");
        updateButtons.add(updateNow, "cell 3 0");

        programSettings.add(updateCheck, "grow, cell 0 0");
        programSettings.add(updateButtons, "cell 1 0");

		mainSettingsPanel.add(programSettings, "al center center, grow, cell 0 1");
    }

	/**
	 * Create credits area
	 */
	private void credits() {
		GenericPanel creditsPanel = new GenericPanel("fill", "5[]5", "5[]0[]0[]5", false, COLOUR_TRANSPARENT);

		CustomLabel author = new CustomLabel("Tim Beswick www.uk-sf.com", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		CustomLabel copyright = new CustomLabel("Copyright (c) Tim UKSF 2016", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		CustomLabel license = new CustomLabel("This program is released under GPLv3", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");

		creditsPanel.add(author, "center, cell 0 0");
		creditsPanel.add(copyright, "center, cell 0 1");
		creditsPanel.add(license, "center, cell 0 2");

		add(creditsPanel, "center, cell 0 1");
	}

	/**
	 * Add all button functionality
	 */
    private void buttonFunctionality() {
        settingsUpdate();
		settingsFolder();
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
	 * Functionality for folder settings
	 */
	private void settingsFolder() {
		backupBox.addActionListener(e -> Settings.set("sqm_backup", backupBox.isSelected()));
	}

	/**
	 * Change the selected update option
	 * @param launch check on launch state
	 * @param week check of week state
	 * @param never check never state
	 */
	public void changeCheckStates(boolean launch, boolean week, boolean never) {
		checkNever.setSelected(never);
		checkWeekly.setSelected(week);
		checkOnLaunch.setSelected(launch);
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
					MissionLoad.loadMissions();
					MapLoad.loadMaps();
					SwingUtilities.invokeLater(() -> Core.getInstanceUI().updateDropdowns());
				} else {
					JOptionPane.showMessageDialog(Core.getInstanceUI(), "Not a missions folder", "Invalid folder", JOptionPane.ERROR_MESSAGE);
					LogHandler.logSeverity(WARNING, "Mission folder invalid");
				}
			} else {
				fileOk = true;
				LogHandler.logSeverity(INFO, "Mission folder selection cancelled");
			}
		}
	}
}

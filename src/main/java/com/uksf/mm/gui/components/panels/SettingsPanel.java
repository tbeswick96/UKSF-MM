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
import com.uksf.mm.gui.components.labels.CustomLabel;
import com.uksf.mm.gui.components.labels.CustomTextField;
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
	private GenericPanel mainSettingsPanel, creditsPanel;

	/**
	 * Settings objects
	 */
	private CustomTextField folderPath;
	private CustomCheckbox backupBox, logsEnabled;

	/**
     * Creates settings panel
     */
    public SettingsPanel() {
		setOpaque(false);
		setLayout(new MigLayout("fill", "10[]10", "10[]10[]10"));

		mainSettingsPanel = new GenericPanel("fill", "10[]10[]10", "10[]10[]10", false, COLOUR_TRANSPARENT);
		folderSettings();
        programSettings();
		credits();

		add(mainSettingsPanel, "push, top, cell 0 0");
		add(creditsPanel, "center, cell 0 1");

        buttonFunctionality();
    }

	/**
	 * Make mission folder settings
	 */
	private void folderSettings() {
		GenericPanel folderSettings = new GenericPanel("", "5[]20[]5[]5", "5[]5[]5", false, COLOUR_TRANSPARENT);
		folderSettings.setBorder(BORDER_STANDARD);

		CustomLabel folder = new CustomLabel("Mission Folder", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Missions are loaded from this folder");
		folderPath = new CustomTextField("", true, COLOUR_BACKGROUND_LIGHT, COLOUR_WHITE, "Missions are loaded from this folder"){
			@Override public Dimension getPreferredSize() {
				return new Dimension(247, getHeight());
			}
		};
		folderPath.setPreferredSize(new Dimension(247, 25));
		folderPath.setText(FOLDER_MISSIONS);
		CustomButtonText folderChange = new CustomButtonText("Change", FONT_STANDARD, 16, "changeMissionsFolder", "Change folder where missions are loaded from");

		CustomLabel backup = new CustomLabel("SQM Backup", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, SQM files are backed up before saving");
		backupBox = new CustomCheckbox("", SQM_BACKUP, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, SQM files are backed up before saving");

		folderSettings.add(folder, "grow, cell 0 0"); folderSettings.add(folderPath, "cell 1 0"); folderSettings.add(folderChange, "cell 2 0");
		folderSettings.add(backup, "grow, cell 0 1"); folderSettings.add(backupBox, "cell 1 1");

		mainSettingsPanel.add(folderSettings, "al center center, grow, cell 0 0");
	}

	/**
	 * Make program settings
	 */
    private void programSettings() {
        GenericPanel programSettings = new GenericPanel("", "5[]20[]5", "5[]5", false, COLOUR_TRANSPARENT);
        programSettings.setBorder(BORDER_STANDARD);

		CustomLabel logs = new CustomLabel("Logs Enabled", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, log files will be saved to:\n" + LOGS.getAbsolutePath());
		logsEnabled = new CustomCheckbox("", LOGS_ENABLED, 16, true, COLOUR_TRANSPARENT, COLOUR_WHITE, "When enabled, log files will be saved to:\n" + LOGS.getAbsolutePath());

		programSettings.add(logs, "cell 0 0"); programSettings.add(logsEnabled, "cell 1 0");

		mainSettingsPanel.add(programSettings, "al center center, grow, cell 0 1");
    }

	/**
	 * Create credits area
	 */
	private void credits() {
		creditsPanel = new GenericPanel("fill", "5[]5", "5[]0[]0[]5", false, COLOUR_TRANSPARENT);

		CustomLabel author = new CustomLabel("Tim Beswick - www.uk-sf.com", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Dev Glub");
		CustomLabel copyright = new CustomLabel("Copyright (c) Tim UKSF 2016", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Don't steal mah stuff :(");
		CustomLabel license = new CustomLabel("This program is released under GPLv3", Font.PLAIN, 10, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "Plz comply");

		creditsPanel.add(author, "center, cell 0 0");
		creditsPanel.add(copyright, "center, cell 0 1");
		creditsPanel.add(license, "center, cell 0 2");
	}

	/**
	 * Add all button functionality
	 */
    private void buttonFunctionality() {
		settingsFolder();
		settingsProgram();
    }

	/**
	 * Functionality for folder settings
	 */
	private void settingsFolder() {
		backupBox.addActionListener(e -> Settings.set("sqm_backup", backupBox.isSelected()));
	}

	/**
	 * Functionality for program settings
	 */
	private void settingsProgram() {
		logsEnabled.addActionListener(e -> {
			boolean log = logsEnabled.isSelected();
			if(log) {
				LOGS_ENABLED = true;
				LogHandler.instance.startLogging();
				LogHandler.logNoTime(HASHSPACE);
				LogHandler.logSeverity(INFO, "Logging enabled, starting");
			} else {
				LogHandler.logNoTime(HASHSPACE);
				LogHandler.logSeverity(INFO, "Logging disabled, stopping");
			}
			Settings.set("logs_enabled", log);
		});
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
				if(selectedFolder.getAbsolutePath().toLowerCase().contains("missions")) {
					String path = selectedFolder.getAbsolutePath().replace("\\", "/");
					LogHandler.logSeverity(INFO, "Missions folder chosen: '" + path + "'");
					folderPath.setText(path);
					folderPath.setCaretPosition(0);
					Settings.set("folder_missions", path);
					fileOk = true;
					Core.getInstance().defaultAuthor();
					MissionLoad.loadMissions();
					MapLoad.loadMaps();
					SwingUtilities.invokeLater(() -> Core.getInstanceUI().updateDropdowns());
				} else {
					JOptionPane.showMessageDialog(Core.getInstanceUI(), "Not a missions folder", "Invalid folder", JOptionPane.ERROR_MESSAGE);
					LogHandler.logSeverity(WARNING, "'" + selectedFolder.getAbsolutePath() + "' is invalid");
				}
			} else {
				fileOk = true;
				LogHandler.logSeverity(INFO, "Mission folder selection cancelled");
			}
		}
	}
}

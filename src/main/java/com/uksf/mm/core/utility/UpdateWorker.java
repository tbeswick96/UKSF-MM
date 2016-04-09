/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.Settings;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;

import static com.uksf.mm.core.Core.error;
import static com.uksf.mm.core.Core.nonFatalError;
import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class UpdateWorker extends SwingWorker<Void, Void> {

	/**
	 * Run update check
	 * @return nothing
	 * @throws Exception ui error
	 */
	@Override
	protected Void doInBackground() throws Exception {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Update check running");
		Settings.set("update_time", Settings.weekAhead());
		if(versionCheck()) {
			LogHandler.logSeverity(INFO, "Update is available. Current version: '" + VERSION + "' Latest version: '" + VERSION_LATEST + "'");
			if(UPDATE_CHECK || (UPDATE_WEEK && isWeekAhead())) {
				update();
			}
			SwingUtilities.invokeLater(() -> Core.getInstanceUI().enableUpdate(true));
		}
		if(UPDATER_UPDATED) {
			updateUpdater();
		}
		SwingUtilities.invokeLater(() -> {
			Core.getInstanceUI().changeCheckStates(UPDATE_CHECK, UPDATE_WEEK, !UPDATE_CHECK);
			Core.getInstanceUI().updateVersionText();
		});
		return null;
	}

	/**
	 * Check version against online version
	 * @return true if version is different
	 */
	private static boolean versionCheck() {
		try {
			URL url = new URL("http://www.uk-sf.com/mm/LATEST.txt"); InputStream stream = url.openStream();
			StringBuilder buffer = new StringBuilder(""); int character; while(true) {
				if((character = stream.read()) == - 1) break; buffer.append((char) character);
			}
			VERSION_LATEST = buffer.substring(buffer.indexOf("<Version>") + "<Version>".length(), buffer.indexOf("</Version>"));
			if(VERSION_LATEST.equals(VERSION)) return false;
		} catch(IOException e) {
			error(e);
		} return true;
	}

	/**
	 * Checks if current date is later than date in registry
	 * @return true if current date is younger
	 */
	private static boolean isWeekAhead() {
		try {
			if(DATEFORMAT.parse(UPDATE_TIME).before(DATEFORMAT.parse(DATEFORMAT.format(DATE)))) {
				return true;
			}
		} catch(ParseException e) {
			error(e);
		} return false;
	}

	/**
	 * Prompt user for update
	 */
	private static void update() {
		String message = "Current Version: " +
				VERSION +
				"\nLatest Version: " +
				VERSION_LATEST +
				"\n\nWould you like to download and update to the latest version?";
		JTextArea text = new JTextArea(message); text.setBorder(null); text.setOpaque(false);
		text.setFont(UIManager.getFont("Label.font"));
		Object[] options = {"Update", "Cancel", "Cancel, don't ask again",};
		int input = JOptionPane.showOptionDialog(Core.getInstanceUI(), //Parent frame
				text, //Message
				"Update Available", //Title
				JOptionPane.YES_NO_CANCEL_OPTION, //Option type
				JOptionPane.INFORMATION_MESSAGE, //Message type
				LOGO_LIGHT_64, //Icon
				options, //Options
				options[0] //Default Option
		);
		handleInput(input);
	}

	/**
	 * Handles input from dialog
	 * @param input state of input (ok, cancel)
	 */
	private static void handleInput(int input) {
		switch(input) {
			case JOptionPane.OK_OPTION: runUpdate(); break; case JOptionPane.CANCEL_OPTION: stopShow(); break;
		}
		Core.getInstanceUI().requestFocus();
	}

	/**
	 * Runs update tasks
	 */
	public static void runUpdate() {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Updating");
		try {
			Runtime.getRuntime().exec("UKSF-MM-Updater.exe");
		} catch(Exception exception) {
			nonFatalError(exception);
		}
	}

	/**
	 * Sets update setting to not show again
	 */
	private static void stopShow() {
		Settings.setMultiple(new String[]{"update_check", "update_week"}, new Object[]{false, false});
	}

	/**
	 * Runs update for updater
	 */
	private static void updateUpdater() {
		LogHandler.logNoTime(HASHSPACE); LogHandler.logSeverity(INFO, "Updating the updater");
		final InstallWorker installWorker = new InstallWorker();
		installWorker.addPropertyChangeListener(pcEvt -> {
			if(pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
				Settings.set("updater_updated", false);
			}
		});
		installWorker.execute();
	}
}

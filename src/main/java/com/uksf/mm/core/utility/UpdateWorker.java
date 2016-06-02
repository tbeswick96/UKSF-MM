/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import com.uksf.mm.core.Settings;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.uksf.mm.core.Core.nonFatalError;
import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static com.uksf.mm.core.utility.LogHandler.Severity.WARNING;

/**
 * @author Tim
 */
public class UpdateWorker extends SwingWorker<Void, Void> {

	private static String versionLatest;

	/**
	 * Run update check
	 * @return nothing
	 * @throws Exception ui error
	 */
	@Override
	protected Void doInBackground() throws Exception {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Update check running");
		if(versionCheck()) {
			LogHandler.logSeverity(INFO, "Update is available. Current version: '" + VERSION + "' Latest version: '" + versionLatest + "'");
			runUpdate();
		}
		if(UPDATER_UPDATED) {
			updateUpdater();
		}
		return null;
	}

	/**
	 * Check version against online version
	 * @return true if version is different
	 */
	private static boolean versionCheck() {
		try {
			URL url = new URL("http://www.uk-sf.com/mm/LATEST.txt");
			if(checkConnection(url)) {
				throw new IOException();
			}
			InputStream stream = url.openStream();
			StringBuilder buffer = new StringBuilder("");
			int character;
			while(true) {
				if((character = stream.read()) == -1) break;
				buffer.append((char) character);
			}
			versionLatest = buffer.substring(buffer.indexOf("<Version>") + "<Version>".length(), buffer.indexOf("</Version>"));
			if(versionLatest.equals(VERSION)) return false;
		} catch(IOException e) {
			LogHandler.logSeverity(WARNING, "Cannot reach 'www.uk-sf.com', update check not run");
			return false;
		}
		return true;
	}

	/**
	 * Checks if connection to URL can be established
	 * @param url url to check
	 * @return connection state
	 * @throws IOException
	 */
	private static boolean checkConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(1500);
		connection.setReadTimeout(1500);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		connection.connect();
		return connection.getResponseCode() == 404;
	}

	/**
	 * Runs update tasks
	 */
	private static void runUpdate() {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Updating");
		try {
			LogHandler.logSeverity(INFO, "Would run updater here...");
			//Runtime.getRuntime().exec("UKSF-MM-Updater.exe");
		} catch(Exception exception) {
			nonFatalError(exception);
		}
	}

	/**
	 * Runs update for updater
	 */
	private static void updateUpdater() {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Updating the updater");
		final InstallWorker installWorker = new InstallWorker();
		installWorker.addPropertyChangeListener(pcEvt -> {
			if(pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
				Settings.set("updater_updated", false);
			}
		});
		installWorker.execute();
	}
}

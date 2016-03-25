/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.core;

import com.uksf.updater.utility.LogHandler;
import com.uksf.updater.utility.Network;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static com.uksf.updater.core.Core.error;
import static com.uksf.updater.utility.Info.*;
import static com.uksf.updater.utility.Network.getDataFromTag;

/**
 * @author Tim
 */
public class Update implements Runnable {

    /**
     * Run update check
     */
    public void run() {
        if(versionCheck()) {
            LogHandler.logNoTime(HASHSPACE);
            LogHandler.log("Updater update is available. Current version: '" + VERSION + "' Latest version: '" + VERSION_LATEST + "'");
            update();
			Settings.set("updater_updated", true);
        }
    }

    /**
     * Check version against online version
     * @return true if version is different
     */
    private boolean versionCheck() {
        try {
			VERSION_LATEST = Network.getDataFromTag("<UpdaterVersion>");
            if(VERSION_LATEST.equals(VERSION)) return false;
        } catch(IOException e) {
			error(e);
        }
        return true;
    }

    /**
     * Prompt user for update
     */
    private void update() {
		File file = new File("Updater.zip");
		try {
			URL url = new URL(getDataFromTag("<UpdaterDownload>"));
			LogHandler.log("Connecting to: '" + url.toString() + "'");
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
			connection.connect();

			int filesize = connection.getContentLength();
			LogHandler.log("File size: " + filesize);
			if(filesize <= 0) {
				LogHandler.log("Cannot find file at '" + url + "'");
			} else if(file.length() != filesize) {
				int totalDataRead = 0;
				try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
					FileOutputStream fos = new FileOutputStream(file);
					try (BufferedOutputStream out = new BufferedOutputStream(fos, 1024)) {
						byte[] buffer = new byte[1024];
						int bytesRead;
						while ((bytesRead = in.read(buffer)) >= 0) {
							totalDataRead = totalDataRead + bytesRead;
							out.write(buffer, 0, bytesRead);
						}
					}
				}
			}
			LogHandler.log("Updater update downloaded to: '" + file.getAbsolutePath() + "'");
		} catch (Exception exception) {
			error(exception);
		}
    }
}

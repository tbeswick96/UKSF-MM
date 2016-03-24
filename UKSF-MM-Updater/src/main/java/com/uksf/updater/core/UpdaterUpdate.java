/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.core;

import com.uksf.updater.utility.LogHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static com.uksf.updater.utility.Info.*;

/**
 * @author Tim
 */
public class UpdaterUpdate implements Runnable {

    /**
     * Run update check
     */
    public void run() {
        if(versionCheck()) {
            LogHandler.logNoTime(HASHSPACE);
            LogHandler.log("Updater update is available. Current version: '" + VERSION + "' Latest version: '" + VERSION_LATEST + "'");
            update();
        }
    }

    /**
     * Check version against online version
     * @return true if version is different
     */
    private boolean versionCheck() {
        try {
            URL url = new URL("http://www.uk-sf.com/mm/UPDATERVERSION.txt");
            Scanner scanner = new Scanner(url.openStream());
            VERSION_LATEST = scanner.next();
            if(VERSION_LATEST.equals(VERSION)) return false;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Prompt user for update
     */
    private void update() {
		try {
			URL url = new URL("http://www.uk-sf.com/mm/UpdaterLatest.zip");
			System.out.println(url.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
			connection.connect();

			int filesize = connection.getContentLength();
			if(filesize <= 0) {
				displayError(i, null);
			} else if(file.length() != filesize) {
				System.out.println(filesize);
				int totalDataRead = 0;

				try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
					FileOutputStream fos = new FileOutputStream(file);
					try (BufferedOutputStream out = new BufferedOutputStream(fos, 1024)) {
						byte[] buffer = new byte[1024];
						int bytesRead;
						while ((bytesRead = in.read(buffer)) >= 0) {
							totalDataRead = totalDataRead + bytesRead;
							out.write(buffer, 0, bytesRead);
							setProgress((totalDataRead * 100) / filesize);
						}
					}
				}
			}
		} catch (Exception exception) {
			displayError(i, exception);
		}
    }
}

/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.core;

import com.uksf.updater.utility.LogHandler;
import com.uksf.updater.utility.Network;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static com.uksf.updater.core.Core.*;

/**
 * @author Tim
 */
public class DownloadWorker extends SwingWorker<Void, Void> {

	@Override
	protected Void doInBackground() throws Exception {
		File file = new File("Update.zip");
		try {
			URL url = new URL(Network.getDataFromTag("<Download>"));
			LogHandler.log("Connecting to: '" + url.toString() + "'");
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
			connection.connect();

			int filesize = connection.getContentLength();
			LogHandler.log("File size: " + filesize);
			if(filesize <= 0) {
				LogHandler.log("Cannot find file at '" + url + "'");
			} else if(file.length() != filesize) {
				System.out.println("test");
				long totalDataRead = 0;
				try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
					FileOutputStream fos = new FileOutputStream(file);
					try (BufferedOutputStream out = new BufferedOutputStream(fos, 1024)) {
						byte[] buffer = new byte[1024];
						int bytesRead;
						while ((bytesRead = in.read(buffer)) >= 0) {
							totalDataRead = totalDataRead + bytesRead;
							out.write(buffer, 0, bytesRead);
							int percent = (int) ((totalDataRead * 100) / filesize);
							setProgress(percent);
						}
					}
				}
			}
			LogHandler.log("Update downloaded to: '" + file.getAbsolutePath() + "'");
		} catch (Exception exception) {
			error(exception);
		}
		return null;
	}
}

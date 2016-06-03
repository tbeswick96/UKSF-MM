/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Tim
 */

class InstallWorker extends SwingWorker<Void, Void> {

	/**
	 * Run install task in background
	 * @return null
	 * @throws Exception on error
	 */
	@Override protected Void doInBackground() throws Exception {
		LogHandler.log("Unzipping update");
		int BUFFER = 2048;
		BufferedOutputStream outputStream;
		BufferedInputStream inputStream;
		ZipEntry entry;
		ZipFile zipfile = new ZipFile("Updater.zip");
		Enumeration entries = zipfile.entries();
		(new File("")).mkdir();
		while(entries.hasMoreElements()) {
			entry = (ZipEntry) entries.nextElement();
			LogHandler.log("Extracting: " + entry);
			if(entry.isDirectory()) {
				(new File("" + entry.getName())).mkdir();
			} else {
				(new File("" + entry.getName())).createNewFile();
				inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream("" + entry.getName());
				outputStream = new BufferedOutputStream(fos, BUFFER);
				while((count = inputStream.read(data, 0, BUFFER))!= -1) {
					outputStream.write(data, 0, count);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			}
		}
		zipfile.close();
		LogHandler.log("Unzipped update");
		cleanup();
		return null;
	}

	/**
	 * Cleans up download
	 */
	private void cleanup() {
		LogHandler.log("Performing clean up");
		File file = new File("Updater.zip");
		if(file.delete()) {
			LogHandler.log("Deleted file: " + file.getAbsolutePath());
		} else {
			LogHandler.log("Cannot delete file: " + file.getAbsolutePath());
		}
	}
}

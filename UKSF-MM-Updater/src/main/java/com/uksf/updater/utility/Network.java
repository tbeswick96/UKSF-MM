/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Tim
 */
public class Network {
	/**
	 * Gets data from given tag
	 * @param tag tag in Latest file on server to fetch data from
	 * @return data
	 * @throws IOException file read error
	 */
	public static String getDataFromTag(String tag) throws IOException {
		URL url = new URL("http://www.uk-sf.com/mm/LATEST.txt");
		InputStream stream = url.openStream();
		StringBuilder buffer = new StringBuilder("");
		int character;
		while(true) {
			if((character = stream.read()) == -1) break;
			buffer.append((char) character);
		}
		return buffer.substring(buffer.lastIndexOf(tag) + tag.length(), buffer.indexOf(tag.replace("<", "</")));
	}
}

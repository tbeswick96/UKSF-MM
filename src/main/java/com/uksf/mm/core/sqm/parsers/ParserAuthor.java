/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm.parsers;

import com.uksf.mm.core.utility.LogHandler;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.uksf.mm.core.utility.Info.DEFAULT_AUTHOR;
import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class ParserAuthor {

	public static boolean getAuthor() {
		LogHandler.logSeverity(INFO, "Reading mission author");
		String author;
		String all = "";
		for(String line : MISSION_SELECTED.scenarioData) {
			all += line;
		}
		Matcher matcher = ((Pattern.compile("\"([^\"]*)\"")).matcher(all));
		if(matcher.find()) {
			author = matcher.group(1);
			if(!Objects.equals(author, "")) {
				MISSION_SELECTED.author = author;
				LogHandler.logSeverity(INFO, "Read mission author as '" + MISSION_SELECTED.author + "'");
				return true;
			} else {
				MISSION_SELECTED.author = DEFAULT_AUTHOR;
				LogHandler.logSeverity(INFO, "Read mission author as default author'" + MISSION_SELECTED.author + "'");
				return true;
			}
		}
		return false;
	}
}

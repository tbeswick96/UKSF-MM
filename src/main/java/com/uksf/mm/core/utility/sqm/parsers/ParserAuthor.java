/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.sqm.parsers;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;

/**
 * @author Tim
 */
public class ParserAuthor {

	public static boolean getAuthor() {
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
				return true;
			}
		}
		return false;
	}
}

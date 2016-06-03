/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm.parsers;

import com.uksf.mm.core.sqm.SqmList;
import com.uksf.mm.core.utility.LogHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class ParserIntel {

	public static boolean getIntel() {
		LogHandler.logSeverity(INFO, "Reading mission intel");
		String all = "";
		if(MISSION_SELECTED.missionIntel == null || !MISSION_SELECTED.missionIntel.toString().contains("=")) {
			MISSION_SELECTED.missionIntel = new SqmList(Arrays.asList("\tclass Intel", "\t{" ,
					"\t\ttimeOfChanges=1800;",
					"\t\tstartWeather=0;",
					"\t\tstartWind=0.1;",
					"\t\tstartWaves=0.1;",
					"\t\tforecastWeather=0;",
					"\t\tforecastWind=0.1;",
					"\t\tforecastWaves=0.1;",
					"\t\tforecastLightnings=0.1;",
					"\t\tyear=2016;",
					"\t\tmonth=3;",
					"\t\tday=1;",
					"\t\thour=8;",
					"\t\tminute=0;",
					"\t\tstartFogDecay=0.013;",
					"\t\tforecastFogDecay=0.013;",
					"\t};"));
		}
		for(String line : MISSION_SELECTED.missionIntel) {
			all += line;
		}
		MISSION_SELECTED.intel = new ArrayList<>();
		String intel;
		//TODO: Find a cleaner way of doing the regex matching for the intel.

		Matcher matcher = Pattern.compile("(?<=timeOfChanges)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=startWeather)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=startWind)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=startWaves)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=forecastWeather)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=forecastWind)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=forecastWaves)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=forecastLightnings)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=year)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=month)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=day)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=hour)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=minute)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=startFogDecay)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}
		matcher = Pattern.compile("(?<=forecastFogDecay)=.+?;").matcher(all);
		if(matcher.find()) {
			intel = matcher.group().replace("=", "").replace(";", "");
			if(!Objects.equals(intel, "")) MISSION_SELECTED.intel.add(intel);
		}

		LogHandler.logSeverity(INFO, "Read intel as '" + MISSION_SELECTED.intel + "'");
		return true;
	}
}

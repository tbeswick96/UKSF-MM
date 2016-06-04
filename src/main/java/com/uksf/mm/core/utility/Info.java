/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import com.uksf.mm.core.sqm.Mission;
import com.uksf.mm.core.sqm.SqmList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Tim
 */

public class Info {
    //Program
    public static final String VERSION = "0.1";
	public static boolean LOG_CREATED = false;

    //Directories
	static File APPDATA = new File(System.getenv("LOCALAPPDATA"));
	public static File LOGS = new File(APPDATA + "/UKSF-MM/");
	public static String ICONS = "/assets/icons/";
	public static String LOGOS = "/assets/logos/";
	public static String FONTS = "/assets/fonts/";

    //User Settings
    public static boolean HAS_SETUP;
	public static boolean UPDATER_UPDATED;
	public static boolean LOGS_ENABLED = true;
	public static String FOLDER_MISSIONS;
	public static boolean SQM_BACKUP;

    //Colours
    public static final Color COLOUR_WHITE = new Color(255, 255, 255);
    public static final Color COLOUR_BLACK = new Color(0, 0, 0);
    public static final Color COLOUR_TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color COLOUR_BACKGROUND_LIGHT = new Color(65, 65, 65);
    public static final Color COLOUR_BACKGROUND_LIGHTER = new Color(55, 55, 55);
    public static final Color COLOUR_BACKGROUND = new Color(45, 45, 45);
    public static final Color COLOUR_BACKGROUND_DARKER = new Color(40, 40, 40);
    public static final Color COLOUR_BACKGROUND_DARK = new Color(35, 35, 35);
    public static final Color COLOUR_FOREGROUND = new Color(222, 154, 37);
    public static final Color COLOUR_FOREGROUND_DARK = new Color(222, 106, 20);

    //Window
    public static final Dimension WINDOW_SIZE = new Dimension(1240, 640);
    public static final Dimension WINDOW_SIZE_MIN = new Dimension(800, 300);
    public static final String WINDOW_TITLE = "UKSF Mission Manager";
    public static final Border BORDER_STANDARD = BorderFactory.createLineBorder(COLOUR_BACKGROUND_DARK, 1);
	public static final Border BORDER_COLOURED = BorderFactory.createLineBorder(COLOUR_FOREGROUND, 1);

    //Logos
    public static ImageIcon LOGO_LIGHT_16;
    public static ImageIcon LOGO_LIGHT_32;
    public static ImageIcon LOGO_LIGHT_64;
    public static ImageIcon LOGO_64;

    //Icons
    public static ImageIcon ICON_HOME;
    public static ImageIcon ICON_HOME_HOVER ;
    public static ImageIcon ICON_SETTINGS;
    public static ImageIcon ICON_SETTINGS_HOVER;
	public static ImageIcon ICON_MINIMIZE ;
	public static ImageIcon ICON_MINIMIZE_HOVER;
	public static ImageIcon ICON_MAXIMIZE;
	public static ImageIcon ICON_MAXIMIZE_HOVER;
	public static ImageIcon ICON_CLOSE;
	public static ImageIcon ICON_CLOSE_HOVER;
	public static ImageIcon ICON_REFRESH;
	public static ImageIcon ICON_REFRESH_HOVER;
	public static ImageIcon ICON_ADDONS;
	public static ImageIcon ICON_ADDONS_HOVER;
	public static ImageIcon FEELSBADMAN;

    //Fonts
    public static Font FONT_STANDARD;
	public static Font FONT_BOLD;
	public static Font FONT_TOOLTIP;

    //Date
    public static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");
    public static DateFormat TIMEFORMAT = new SimpleDateFormat("HH:mm:ss");
    public static Date DATE = new Date();

    //Strings
    public static String TAB = "    ";
    public static String HASHSPACE = "\n#############################################";

	//Utilities
	public static JFileChooser FILE_CHOOSER;

	//Dropdowns
	public static ArrayList<String> MAPS = new ArrayList<>();
	public static ArrayList<Mission> MISSIONS = new ArrayList<>();

	//Mission
	public static Mission MISSION_SELECTED;
	public static String SQM_VERSION = "52";

	//Mission Defaults
	public static String DEFAULT_VERSION = "version=52;";
	public static String DEFAULT_BINARIZED = "binarizationWanted=0;";
	public static String DEFAULT_SEED = "randomSeed=12623053;";
	public static String DEFAULT_AUTHOR = "UKSF";
	public static SqmList DEFAULT_EDITORDATA = new SqmList(Arrays.asList(
			"class EditorData",
			"{",
			"\tmoveGridStep=0.25;",
			"\tangleGridStep=1.5707964;",
			"\tscaleGridStep=1;",
			"\tautoGroupingDist=10;",
			"\ttoggles=37;",
			"\tclass ItemIDProvider",
			"\t{",
			"\t\tnextID=0;",
			"\t};",
			"\tclass MarkerIDProvider",
			"\t{",
			"\t\tnextID=0;",
			"\t};",
			"\tclass Camera",
			"\t{",
			"\t\tpos[]={0,0,0};",
			"\t\tdir[]={0,0,0};",
			"\t\tup[]={0,0,0};",
			"\t\taside[]={0,0,0};",
			"\t};",
			"};"
	));
	public static SqmList DEFAULT_ADDONS = new SqmList(Arrays.asList(
			"addons[]=",
			"{\n",
			"};"
	));
	public static SqmList DEFAULT_ADDONSMETA = new SqmList(Arrays.asList(
			"class AddonsMetaData",
			"{",
			"\tclass List",
			"\t{",
			"\t\titems=0;",
			"\t};",
			"};"
	));
	public static SqmList DEFAULT_SCENARIODATA = new SqmList(Arrays.asList(
			"class ScenarioData",
			"{",
			"\tauthor=" + DEFAULT_AUTHOR + ";",
			"};"
	));
	public static SqmList DEFAULT_INTEL = new SqmList(Arrays.asList(
			"class Intel",
			"\t{",
			"\t\ttimeOfChanges=1800;",
			"\t\tstartWeather=0;",
			"\t\tstartWind=0.1;",
			"\t\tstartWaves=0.1;",
			"\t\tforecastWeather=0;",
			"\t\tforecastWind=0.1;",
			"\t\tforecastWaves=0.1;",
			"\t\tforecastLightnings=0.1;",
			"\t\tyear=2035;",
			"\t\tmonth=7;",
			"\t\tday=6;",
			"\t\thour=11;" ,
			"\t\tminute=-2;",
			"\t\tstartFogDecay=0.01;",
			"\t\tforecastFogDecay=0.01;",
			"\t\tclass CustomAttributes",
			"\t\t{",
			"\t\t\tnAttributes=0;",
			"\t\t};",
			"\t};"
	));
	public static SqmList DEFAULT_MISSIONDATA = new SqmList(Arrays.asList(
			"class Entities",
			"\t{",
			"\t\titems=0;",
			"\t};"
	));
}

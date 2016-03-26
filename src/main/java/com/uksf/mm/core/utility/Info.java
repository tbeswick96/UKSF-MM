/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Tim
 */
@SuppressWarnings("ALL")
public class Info {
    //Program settings
    public static final String VERSION = "0.2";
    public static String VERSION_LATEST = "0.1";

    //Directories
	static File APPDATA = new File(System.getenv("LOCALAPPDATA"));
	static File LOGS = new File(APPDATA + "/UKSF-MM/");
	public static String ICONS = "/assets/icons/";
	public static String LOGOS = "/assets/logos/";
	public static String FONTS = "/assets/fonts/";

    //User Settings
    public static boolean HAS_SETUP;
    public static boolean UPDATE_CHECK;
    public static boolean UPDATE_WEEK;
    public static String UPDATE_TIME;
	public static boolean UPDATER_UPDATED;
	public static String FOLDER_MISSIONS;

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

    //Window settings
    public static final Dimension WINDOW_SIZE = new Dimension(1240, 640);
    public static final Dimension WINDOW_SIZE_MIN = new Dimension(600, 400);
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

    //Fonts
    public static Font FONT_STANDARD;
	public static Font FONT_BOLD;
	public static Font FONT_TOOLTIP;

    //Date
    public static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd__hh-mm-ss");
    public static DateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm:ss");
    public static Date DATE = new Date();

    //Strings
    public static String TAB = "    ";
    public static String HASHSPACE = "\n#############################################";

	//Utilities
	public static JFileChooser FILE_CHOOSER;

	//Dropdowns
	public static ArrayList<String> MAPS;
	public static ArrayList<String> MISSIONS;
}

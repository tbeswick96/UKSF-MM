/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.utility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tim
 */
@SuppressWarnings("ALL")
public class Info {
    //Program settings
    public static final String VERSION = "0.1";
    public static String VERSION_LATEST = "0.1";

    //Directories
	static File APPDATA = new File(System.getenv("LOCALAPPDATA"));
	static File LOGS = new File(APPDATA + "\\UKSF-MM\\");
	static File ICONS = new File("src\\main\\resources\\assets\\icons\\");
	static File LOGOS = new File("src\\main\\resources\\assets\\logos\\");
	static File FONTS = new File("src\\main\\resources\\assets\\fonts\\");

    //User Settings
    public static boolean HAS_SETUP;
    public static boolean UPDATE_CHECK;
    public static boolean UPDATE_WEEK;
    public static String UPDATE_TIME;

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

    //Logos
    public static final ImageIcon LOGO_LIGHT_16 = new ImageIcon(LOGOS + "\\light16.png");
    public static final ImageIcon LOGO_LIGHT_32 = new ImageIcon(LOGOS + "\\light32.png");
    public static final ImageIcon LOGO_LIGHT_64 = new ImageIcon(LOGOS + "\\light64.png");
    public static final ImageIcon LOGO_64 = new ImageIcon(LOGOS + "\\uksf64.png");

    //Icons
    public static final ImageIcon ICON_HOME = new ImageIcon(ICONS + "\\home.png");
    public static final ImageIcon ICON_HOME_HOVER = new ImageIcon(ICONS + "\\home_hover.png");
    public static final ImageIcon ICON_SETTINGS = new ImageIcon(ICONS + "\\settings.png");
    public static final ImageIcon ICON_SETTINGS_HOVER = new ImageIcon(ICONS + "\\settings_hover.png");
	public static final ImageIcon ICON_MINIMIZE = new ImageIcon(ICONS + "\\minimize.png");
	public static final ImageIcon ICON_MINIMIZE_HOVER = new ImageIcon(ICONS + "\\minimize_hover.png");
	public static final ImageIcon ICON_MAXIMIZE = new ImageIcon(ICONS + "\\maximize.png");
	public static final ImageIcon ICON_MAXIMIZE_HOVER = new ImageIcon(ICONS + "\\maximize_hover.png");
	public static final ImageIcon ICON_CLOSE = new ImageIcon(ICONS + "\\close.png");
	public static final ImageIcon ICON_CLOSE_HOVER = new ImageIcon(ICONS + "\\close_hover.png");

    //Fonts
    public static Font FONT_STANDARD;
    public static Font FONT_THIN;
    public static Font FONT_TOOLTIP;

    //Date
    public static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd__hh-mm-ss");
    public static DateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm:ss");
    public static Date DATE = new Date();

    //Strings
    public static String TAB = "    ";
    public static String HASHSPACE = "\n#############################################";
}

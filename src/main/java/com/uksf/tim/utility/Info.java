package com.uksf.tim.utility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Info {
    //Program settings
    public static final String VERSION = "0.1";
    public static String VERSION_LATEST = "0.1";

    //Directories
    static File APPDATA = new File(System.getenv("LOCALAPPDATA"));

    //User Settings
    public static boolean HAS_SETUP;
    public static boolean UPDATE_CHECK;
    public static boolean UPDATE_WEEK;
    public static String UPDATE_TIME;

    //Colours
    public static final Color COLOUR_BACKGROUND = new Color(45, 45, 45);
    public static final Color COLOUR_BACKGROUND_DARKER = new Color(40, 40, 40);
    public static final Color COLOUR_BACKGROUND_DARK = new Color(35, 35, 35);
    public static final Color COLOUR_WHITE = new Color(255, 255, 255);
    public static final Color COLOUR_TRANSPARENT = new Color(0, 0, 0, 0);

    //Window settings
    public static final Dimension WINDOW_SIZE = new Dimension(1240, 640);
    public static final Dimension WINDOW_SIZE_MIN = new Dimension(600, 400);
    public static final String WINDOW_TITLE = "UKSF Mission Manager";
    public static final Border BORDER_STANDARD = BorderFactory.createLineBorder(COLOUR_BACKGROUND_DARK, 1);

    //Logos
    public static final ImageIcon LOGO_LIGHT_16 = new ImageIcon("src\\main\\resources\\assets\\logos\\light16.png");
    public static final ImageIcon LOGO_LIGHT_32 = new ImageIcon("src\\main\\resources\\assets\\logos\\light32.png");
    public static final ImageIcon LOGO_LIGHT_64 = new ImageIcon("src\\main\\resources\\assets\\logos\\light64.png");
    public static final ImageIcon LOGO_64 = new ImageIcon("src\\main\\resources\\assets\\logos\\uksf64.png");

    //Icons
    public static final ImageIcon ICON_HOME = new ImageIcon("src\\main\\resources\\assets\\icons\\home.png");
    public static final ImageIcon ICON_HOME_HOVER = new ImageIcon("src\\main\\resources\\assets\\icons\\home_hover.png");
    public static final ImageIcon ICON_SETTINGS = new ImageIcon("src\\main\\resources\\assets\\icons\\settings.png");
    public static final ImageIcon ICON_SETTINGS_HOVER = new ImageIcon("src\\main\\resources\\assets\\icons\\settings_hover.png");

    //Date
    public static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd__hh-mm-ss");
    public static DateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm:ss");
    public static Date DATE = new Date();

    //Strings
    public static String TAB = "    ";
    public static String HASHSPACE = "\n#############################################";
}

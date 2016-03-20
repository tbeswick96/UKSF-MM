package main.java.com.uksf.tim;

import javax.swing.*;
import java.awt.*;

public class Info {
    //Program settings
    public static final String VERSION = "0.1";
    public static String VERSION_LATEST = "0.1";

    //User SettingsPanel
    public static boolean HAS_SETUP;
    public static boolean UPDATE_CHECK;

    //Window settings
    public static final Dimension WINDOW_SIZE = new Dimension(1240, 640);
    public static final String WINDOW_TITLE = "UKSF Mission Manager";

    //Colours
    public static final Color COLOUR_BACKGROUND = new Color(45, 45, 45);
    public static final Color COLOUR_BACKGROUND_DARK = new Color(35, 35, 35);
    public static final Color COLOUR_WHITE = new Color(255, 255, 255);
    public static final Color COLOUR_TRANSPARENT = new Color(0, 0, 0, 0);

    //Logos
    public static final ImageIcon LOGO_LIGHT_16 = new ImageIcon("src\\main\\resources\\assets\\logos\\light16.png");
    public static final ImageIcon LOGO_LIGHT_32 = new ImageIcon("src\\main\\resources\\assets\\logos\\light32.png");
    public static final ImageIcon LOGO_LIGHT_64 = new ImageIcon("src\\main\\resources\\assets\\logos\\light64.png");
    public static final ImageIcon LOGO_64 = new ImageIcon("src\\main\\resources\\assets\\logos\\uksf64.png");

    //Icons
    public static final ImageIcon ICON_SETTINGS = new ImageIcon("src\\main\\resources\\assets\\icons\\settings.png");
    public static final ImageIcon ICON_SETTINGS_HOVER = new ImageIcon("src\\main\\resources\\assets\\icons\\settings_hover.png");
}

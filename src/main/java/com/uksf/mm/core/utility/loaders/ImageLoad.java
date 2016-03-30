/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.loaders;

import com.uksf.mm.core.utility.LogHandler;

import javax.swing.*;
import java.io.IOException;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static org.apache.commons.io.IOUtils.toByteArray;

/**
 * @author Tim
 */
public class ImageLoad {

	/**
	 * ImageLoad instance reference
	 */
	public static ImageLoad instance = new ImageLoad();

	/**
	 * Loads images from resources
	 * @throws IOException file read error
	 */
	public void loadImages() throws IOException {
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.logSeverity(INFO, "Loading images");
		LOGO_LIGHT_16 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light16.png")));
		LOGO_LIGHT_32 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light32.png")));
		LOGO_LIGHT_64 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light64.png")));
		LOGO_64 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "uksf64.png")));

		ICON_HOME = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "home.png")));
		ICON_HOME_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "home_hover.png")));
		ICON_SETTINGS = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "settings.png")));
		ICON_SETTINGS_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "settings_hover.png")));
		ICON_MINIMIZE = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "minimize.png")));
		ICON_MINIMIZE_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "minimize_hover.png")));
		ICON_MAXIMIZE = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "maximize.png")));
		ICON_MAXIMIZE_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "maximize_hover.png")));
		ICON_CLOSE = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "close.png")));
		ICON_CLOSE_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "close_hover.png")));
		ICON_REFRESH = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "refresh.png")));
		ICON_REFRESH_HOVER = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "refresh_hover.png")));
		FEELSBADMAN = new ImageIcon(toByteArray(getClass().getResourceAsStream(ICONS + "fbm64.png")));
        LogHandler.logSeverity(INFO, "Images loaded");
    }
}

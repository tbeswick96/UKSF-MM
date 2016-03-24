/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.utility.loaders;


import com.uksf.updater.utility.LogHandler;

import javax.swing.*;
import java.io.IOException;

import static com.uksf.updater.utility.Info.*;
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
        LogHandler.log("Updater loading images");
		LOGO_LIGHT_16 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light16.png")));
		LOGO_LIGHT_32 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light32.png")));
		LOGO_LIGHT_64 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "light64.png")));
		LOGO_64 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "uksf64.png")));
		LOGO_256 = new ImageIcon(toByteArray(getClass().getResourceAsStream(LOGOS + "uksf256.png")));
        LogHandler.log("Updater images loaded");
    }
}

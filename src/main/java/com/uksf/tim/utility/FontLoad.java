/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.utility;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class FontLoad {

    public static void loadFonts() throws IOException, FontFormatException {
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.logSeverity(INFO, "Loading fonts");
        FONT_STANDARD = Font.createFont(Font.TRUETYPE_FONT, new File(FONTS + "\\Bariol_Light.otf"));
        FONT_THIN = Font.createFont(Font.TRUETYPE_FONT, new File(FONTS + "\\Bariol_Light.otf"));
        FONT_TOOLTIP = new Font(FONT_THIN.getFontName(), FONT_THIN.getStyle(), 16);
        LogHandler.logSeverity(INFO, "Fonts loaded");
    }
}

package com.uksf.tim.utility;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.uksf.tim.utility.Info.HASHSPACE;
import static com.uksf.tim.utility.Info.HELVETICAROMAN;
import static com.uksf.tim.utility.Info.HELVETICATOOLTIP;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

public class FontLoad {

    public static void loadFonts() throws IOException, FontFormatException {
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.logSeverity(INFO, "Loading fonts");
        HELVETICAROMAN = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\resources\\assets\\Fonts\\HelveticaNeueLTStd-Md.otf"));
        HELVETICATOOLTIP = new Font(HELVETICAROMAN.getFontName(), HELVETICAROMAN.getStyle(), 11);
        LogHandler.logSeverity(INFO, "Fonts loaded");
    }
}

package com.uksf.tim.utility;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class StringMetrics {
    /**
     * Gets size of string fron font and text
     * @param font font to check size with
     * @param context font render context to check with
     * @param message text to get size of
     * @return Rectangle of size
     */
    public static Rectangle2D getBounds(Font font, FontRenderContext context, String message) {
        return font.getStringBounds(message, context);
    }
}

package com.uksf.tim.utility;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class StringMetrics {
    public static Rectangle2D getBounds(Font font, FontRenderContext context, String message) {
        return font.getStringBounds(message, context);
    }
}

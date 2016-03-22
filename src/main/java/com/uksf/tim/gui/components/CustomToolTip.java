package com.uksf.tim.gui.components;

import com.uksf.tim.utility.StringMetrics;

import javax.swing.*;
import java.awt.*;

import static com.uksf.tim.utility.Info.*;

public class CustomToolTip extends JLabel {

    /**
     * Tooltip to display
     */
    private String text;

    /**
     * Width, height and string width, height
     */
    private int width, height, stringWidth, stringHeight;

    /**
     * Create custom tooltip
     * @param text tooltip text to display
     * @param graphics graphics2D object to get string size with
     */
    public CustomToolTip(String text, Graphics2D graphics) {
        super();
        this.text = text;
        setBackground(COLOUR_WHITE);
        setFont(FONT_TOOLTIP);

        graphics.setFont(FONT_TOOLTIP);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        stringWidth = (int) StringMetrics.getBounds(graphics.getFont(), graphics.getFontRenderContext(), text).getWidth();
        stringHeight = (int) StringMetrics.getBounds(graphics.getFont(), graphics.getFontRenderContext(), text).getHeight();
        width = (int) (stringWidth * 1.2);
        height = (int) (stringHeight * 1.5);
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Paints button with custom icons
     * @param graphics graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        g.setFont(FONT_TOOLTIP);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (width - stringWidth) / 2;
        int y = (height - stringHeight) * 2;

        g.setColor(COLOUR_BLACK);
        g.drawString(text, x, y);
    }
}

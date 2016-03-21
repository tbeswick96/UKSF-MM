package com.uksf.tim.gui.components.panels;

import com.uksf.tim.utility.StringMetrics;

import javax.swing.*;
import java.awt.*;

import static com.uksf.tim.utility.Info.COLOUR_BACKGROUND_DARK;
import static com.uksf.tim.utility.Info.COLOUR_FOREGROUND_DARK;
import static com.uksf.tim.utility.Info.HELVETICATOOLTIP;

public class ToolTipPanel extends JPanel {

    /**
     * Width of tooltip
     */
    private float width;

    /**
     * Height of tooltip
     */
    private float height;

    /**
     * Text of tooltip
     */
    private String text;

    public ToolTipPanel() {
        super();
        validate();
        repaint();
        setBorder(null);
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(HELVETICATOOLTIP);

        double stringWidth = StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), text).getWidth();
        double stringHeight = StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), text).getHeight();
        width = (float) (stringWidth * 1.2);
        height = (float) (stringHeight * 1.5);
        float x = (float) ((width / 2) - (stringWidth / 2));
        float y = (float) ((height - stringHeight) * 2);
        setPreferredSize(new Dimension((int) (width), (int) (height)));

        g.setColor(COLOUR_BACKGROUND_DARK);
        g.fillRect(0, 0, (int) width, (int) height);
        g.setColor(COLOUR_FOREGROUND_DARK);
        g.fillRect(1, 1, (int) width - 2, (int) height - 2);
        g.setColor(COLOUR_BACKGROUND_DARK);
        g.drawString(text, x, y);
    }
}

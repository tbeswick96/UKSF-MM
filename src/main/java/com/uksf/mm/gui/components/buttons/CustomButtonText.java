/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.buttons;


import com.uksf.mm.utility.Invokable;
import com.uksf.mm.utility.StringMetrics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import static com.uksf.mm.core.Core.error;
import static com.uksf.mm.utility.Info.*;
import static com.uksf.mm.utility.LogHandler.Severity;
import static com.uksf.mm.utility.LogHandler.logSeverity;

/**
 * @author Tim
 */
public class CustomButtonText extends JPanel implements MouseListener {

    /**
     * Hover state. True when hovering, false when not
     */
    private boolean hovered = false;

    /**
     * Text to display
     */
    private String text;

    /**
     * Font to display text with
     */
    private Font font;

    /**
     * Method name to call on click
     */
    private String toCall;

    /**
     * Button width and height
     */
    private int width, height;

    /**
     * Create custom button with text
     * @param text text to display
     * @param font font to display
     * @param fontSize text to display
     * @param toCall method to call on click
     */
    public CustomButtonText(String text, Font font, int fontSize, String toCall) {
        this.text = text;
        this.font = new Font(font.getFontName(), font.getStyle(), fontSize);
        this.toCall = toCall;

        setSize(getPreferredSize());
        setBorder(null);
        setOpaque(false);
        addMouseListener(this);
    }

    /**
     * Set size to width x height
     * @return dimension width x height
     */
    @Override public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /**
     * Set size to width x height
     * @return dimension width x height
     */
    @Override public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    /**
     * Paints button with custom string
     * @param graphics graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int stringWidth = (int) StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), text).getWidth();
        int stringHeight = (int) StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), text).getHeight();
        width = (int) (stringWidth * 1.2);
        height = (int) (stringHeight * 1.5);
        int x = (width / 2) - (stringWidth / 2);
        int y = (height - stringHeight) * 2;
        setSize(width, height);
        revalidate();

        if(hovered) {
            g.setColor(COLOUR_FOREGROUND_DARK);
            g.fillRect(0, 0, width, height);
            g.setColor(COLOUR_BACKGROUND_DARK);
            g.drawString(text, x, y);
        } else {
            g.setColor(COLOUR_FOREGROUND);
            g.fillRect(1, 1, width - 2, height - 2);
            g.setColor(COLOUR_BACKGROUND_DARK);
            g.drawString(text, x, y);
        }
    }

    /**
     * Mouse clicked event
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Invokable.class.getMethod(toCall).invoke(Invokable.instance);
        } catch(NoSuchMethodException exception) {
            logSeverity(Severity.CRITICAL, "No such method: " + toCall);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            error(exception);
        }
    }

    /**
     * Mouse pressed event
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Mouse released event
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Mouse entered event
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
    }

    /**
     * Mouse exited event
     * @param e event
     */
    @Override
    public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
    }
}

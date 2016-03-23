/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.gui.components.buttons;


import com.uksf.tim.gui.components.labels.CustomToolTip;
import com.uksf.tim.utility.Invokable;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import static com.uksf.tim.core.Core.error;
import static com.uksf.tim.utility.Info.BORDER_STANDARD;
import static com.uksf.tim.utility.Info.COLOUR_TRANSPARENT;
import static com.uksf.tim.utility.LogHandler.Severity;
import static com.uksf.tim.utility.LogHandler.logSeverity;

/**
 * @author Tim
 */
public class CustomButton extends JPanel implements MouseListener {

    /**
     * Hover state. True when hovering, false when not
     */
    private boolean hovered = false;

	private int width, height, hoverOffset;

    /**
     * Images for base icon and hover icon
     */
    private Image icon, icon_hovered;

	private Color hoveredColour;

    /**
     * Method name to call on click
     */
    private String toCall;

    /**
     * Tooltip text to display on hover
     */
    private String tooltipText;

    /**
     * Graphics object (allows obtaining of string size for tooltip)
     */
    private Graphics2D g;

	/**
	 * Create custom button
	 * @param width width of button
	 * @param height height of button
	 * @param hoverOffset size change to apply on hover
	 * @param icon base icon
	 * @param icon_hovered hover icon
	 * @param hoveredColour hover icon
	 * @param toCall method name to call
	 * @param tooltipText button tooltip text
	 */
    public CustomButton(int width, int height, int hoverOffset, Image icon, Image icon_hovered, Color hoveredColour, String toCall, String tooltipText) {
		this.width = width;
		this.height = height;
		this.hoverOffset = hoverOffset;
        this.icon = icon;
        this.icon_hovered = icon_hovered;
		this.hoveredColour = hoveredColour;
        this.toCall = toCall;
        this.tooltipText = tooltipText;

        setSize(getPreferredSize());
        setBorder(null);
        setOpaque(false);
        addMouseListener(this);
        setToolTipText("");
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getMaximumSize() { return new Dimension(width, height); }

    /**
     * Paints button with custom icons
     * @param graphics graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(hovered) {
			g.setColor(hoveredColour);
			g.fillRect(0, 0, width, height);
            g.drawImage(icon_hovered, 0, 0, width, height, null);
        } else {
			g.setColor(COLOUR_TRANSPARENT);
			g.fillRect(0, 0, width, height);
            g.drawImage(icon, hoverOffset/2, hoverOffset/2, width - hoverOffset, height - hoverOffset, null);
        }
    }

    /**
     * Create custom tooltip
     * @return tooltip object
     */
    @Override public JToolTip createToolTip() {
        JToolTip tooltip = super.createToolTip();
        tooltip.setBorder(BORDER_STANDARD);
        tooltip.setLayout(new MigLayout("fill", "0[]0", "0[]0"));

        CustomToolTip label = new CustomToolTip(tooltipText, g);
        tooltip.setPreferredSize(label.getPreferredSize());

        tooltip.add(label, "grow");
        return tooltip;
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
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Mouse released event
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

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

/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.labels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static com.uksf.mm.core.utility.Info.BORDER_STANDARD;
import static com.uksf.mm.core.utility.Info.FONT_STANDARD;

/**
 * @author Tim
 */
public class CustomLabel extends JLabel {

	/**
	 * Graphics object for tooltip
	 */
	private Graphics2D g;

	/**
	 * Tooltip text
	 */
	private String toolTipText;

	public CustomLabel(String text, int style, int fontSize, boolean opaque, Color background, Color textColour, String toolTipText) {
		super(text);
		this.toolTipText = toolTipText;
		setOpaque(opaque);
		setFont(new Font(FONT_STANDARD.getFontName(), style, fontSize));
		setBackground(background);
		setForeground(textColour);
		setToolTipText("");
	}

	/**
	 * Create custom tooltip
	 * @return tooltip object
	 */
	@Override public JToolTip createToolTip() {
		JToolTip tooltip = super.createToolTip();
		tooltip.setBorder(BORDER_STANDARD);
		tooltip.setLayout(new MigLayout("fill", "0[]0", "0[]0"));

		CustomToolTip label = new CustomToolTip(toolTipText, g);
		tooltip.setPreferredSize(label.getPreferredSize());

		tooltip.add(label, "grow");
		return tooltip;
	}

	/**
	 * Paints button with custom icons
	 * @param graphics graphics object
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}

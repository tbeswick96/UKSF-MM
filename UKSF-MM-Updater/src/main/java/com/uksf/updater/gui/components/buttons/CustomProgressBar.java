/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.gui.components.buttons;

import com.uksf.updater.gui.components.labels.CustomToolTip;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

import static com.uksf.updater.utility.Info.*;

/**
 * @author Tim
 */
public class CustomProgressBar extends JProgressBar {
	
	private Graphics2D g;
	
	public CustomProgressBar() {
		super(0, 100);
		setBackground(COLOUR_BACKGROUND);
		setForeground(COLOUR_FOREGROUND);
		setBorder(null);
		setOpaque(false);
		setStringPainted(true);
		setBorder(BORDER_STANDARD);
		setFont(new Font(FONT_STANDARD.getFontName(), FONT_STANDARD.getStyle(), 16));
		setString("0%");
		setValue(0);
		setToolTipText("0%");
		setPreferredSize(new Dimension(350, 25));

		setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return COLOUR_WHITE; }
			protected Color getSelectionForeground() { return COLOUR_BACKGROUND_DARK; }
		});
	}
	
	/**
	 * Paints button with custom icons
	 * @param graphics graphics object
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		g = (Graphics2D) graphics;
	}
	
	/**
	 * Create custom tooltip
	 * @return tooltip object
	 */
	@Override public JToolTip createToolTip() {
		JToolTip tooltip = super.createToolTip();
		tooltip.setBorder(BORDER_STANDARD);
		tooltip.setLayout(new MigLayout("fill", "0[]0", "0[]0"));
		
		CustomToolTip label = new CustomToolTip(getToolTipText(), g);
		tooltip.setPreferredSize(label.getPreferredSize());
		
		tooltip.add(label, "grow");
		return tooltip;
	}
}

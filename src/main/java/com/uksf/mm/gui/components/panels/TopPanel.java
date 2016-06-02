/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;

import com.uksf.mm.core.utility.StringMetrics;
import com.uksf.mm.gui.components.buttons.CustomButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.uksf.mm.core.utility.Info.*;

/**
 * @author Tim
 */
public class TopPanel extends JPanel {

	private Point initialClick;

	private int width, height = 36;

	/**
	 * Creates main panel
	 */
	public TopPanel(JFrame parent) {
		setOpaque(true);
		setBackground(COLOUR_FOREGROUND);
		setLayout(new MigLayout("al center center, fill", "5[]5[]0", "0[]0"));

		CustomButton minimize = new CustomButton(23, 23, 0, ICON_MINIMIZE.getImage(), ICON_MINIMIZE_HOVER.getImage(), COLOUR_TRANSPARENT, "minimize", "Minimize");
		CustomButton maximize = new CustomButton(23, 23, 0, ICON_MAXIMIZE.getImage(), ICON_MAXIMIZE_HOVER.getImage(), COLOUR_TRANSPARENT, "maximize", "Maximize");
		CustomButton close = new CustomButton(23, 23, 0, ICON_CLOSE.getImage(), ICON_CLOSE_HOVER.getImage(), COLOUR_TRANSPARENT, "close", "Close");

		GenericPanel panel = new GenericPanel("fill", "5[23]5[23]5[23]5", "0[]0", false, COLOUR_TRANSPARENT);
		panel.add(minimize, "push");
		panel.add(maximize, "push");
		panel.add(close, "push");
		add(Box.createHorizontalGlue(), "grow, push");
		add(panel, "grow, align right");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				getComponentAt(initialClick);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = parent.getLocation().x;
				int y = parent.getLocation().y;
				int xNew = x + (x + e.getX()) - (x + initialClick.x);
				int yNew = y + (y + e.getY()) - (y + initialClick.y);
				parent.setLocation(xNew, yNew);
			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		width = getParent().getWidth();

		//Paint title bar
		g.setColor(COLOUR_BACKGROUND_DARKER);
		g.fillRect(0, 0, width, height);

		//Paint title
		g.setColor(COLOUR_FOREGROUND);
		g.setFont(new Font(FONT_STANDARD.getFontName(), FONT_STANDARD.getStyle(), 24));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int stringWidth = (int) StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), WINDOW_TITLE).getWidth();
		int stringHeight = (int) StringMetrics.getBounds(g.getFont(), g.getFontRenderContext(), WINDOW_TITLE).getHeight();
		g.drawString(WINDOW_TITLE, (width - stringWidth) / 2, (30 - stringHeight) / 2 + getFontMetrics(g.getFont()).getAscent());
	}
}

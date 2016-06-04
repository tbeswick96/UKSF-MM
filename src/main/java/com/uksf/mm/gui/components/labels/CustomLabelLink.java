/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.labels;

import com.uksf.mm.core.Core;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.uksf.mm.core.utility.Info.COLOUR_FOREGROUND;

/**
 * @author Tim
 */
public class CustomLabelLink extends CustomLabel {

	/**
	 * Create custom label with a link
	 * @param text label text, with link
	 * @param style font style
	 * @param fontSize font size
	 * @param opaque opaque background
	 * @param background background colour
	 * @param textColour text colour
	 * @param toolTipText tooltip text
	 */
	public CustomLabelLink(String text, int style, int fontSize, boolean opaque, Color background, Color textColour, String toolTipText) {
		super(text, style, fontSize, opaque, background, textColour, toolTipText);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(text));
				} catch (URISyntaxException | IOException exception) {
					Core.nonFatalError(exception);
				}
			}
			@Override public void mouseEntered(MouseEvent e) {
				setForeground(COLOUR_FOREGROUND);
			}
			@Override public void mouseExited(MouseEvent e) {
				setForeground(textColour);
			}
		});
	}
}

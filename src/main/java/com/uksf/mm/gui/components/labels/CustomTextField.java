/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.labels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

import static com.uksf.mm.core.utility.Info.BORDER_COLOURED;
import static com.uksf.mm.core.utility.Info.BORDER_STANDARD;

/**
 * @author Tim
 */
public class CustomTextField extends JTextField {

	/**
	 * Graphics object for tooltip
	 */
	private Graphics2D g;

	/**
	 * Tooltip text
	 */
	private String toolTipText;

	/**
	 * Create custom label
	 * @param text        label text
	 * @param opaque      opaque background
	 * @param background  background colour
	 * @param textColour  text colour
	 * @param toolTipText tooltip text
	 */
	public CustomTextField(String text, boolean opaque, Color background, Color textColour, String toolTipText) {
		super(text); this.toolTipText = toolTipText;
		setOpaque(opaque);
		setBackground(background);
		setForeground(textColour);
		setCaretColor(textColour);
		setBorder(new CompoundBorder(BORDER_COLOURED, new EmptyBorder(5, 5, 5, 5)));
		if(! toolTipText.equals("")) {
			setToolTipText("");
		}
	}

	/**
	 * Create custom tooltip
	 * @return tooltip object
	 */
	@Override
	public JToolTip createToolTip() {
		JToolTip tooltip = super.createToolTip();
		tooltip.setBorder(BORDER_STANDARD);
		tooltip.setLayout(new MigLayout("fill", "0[]0", "0[]0"));

		CustomToolTip label = new CustomToolTip(toolTipText, g);
		tooltip.setPreferredSize(label.getPreferredSize());

		tooltip.add(label, "grow"); return tooltip;
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

	/**
	 *
	 * @param changeListener a listener to receive {@link ChangeEvent}s
	 *                       when the text is changed; the source object for the events
	 *                       will be the text component
	 */
	public void addChangeListener(ChangeListener changeListener) {
		Objects.requireNonNull(this);
		Objects.requireNonNull(changeListener);
		DocumentListener documentListener = new DocumentListener() {
			private int lastChange = 0, lastNotifiedChange = 0;

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				lastChange++;
				SwingUtilities.invokeLater(() -> {
					if(lastNotifiedChange != lastChange) {
						lastNotifiedChange = lastChange; changeListener.stateChanged(new ChangeEvent(this));
					}
				});
			}
		};
		this.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
			Document document1 = (Document) e.getOldValue();
			Document document2 = (Document) e.getNewValue();
			if(document1 != null) document1.removeDocumentListener(documentListener);
			if(document2 != null) document2.addDocumentListener(documentListener);
			documentListener.changedUpdate(null);
		});
		Document document = this.getDocument();
		if(document != null) document.addDocumentListener(documentListener);
	}
}

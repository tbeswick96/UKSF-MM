/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.dropdown;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.LogHandler;
import com.uksf.mm.gui.components.labels.CustomLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
@SuppressWarnings("unchecked")
public class MapDropdown extends CustomDropdown {

	/**
	 * Holds the current list of names
	 */
	private String[] names;

	/**
	 * Creates map list dropdown
	 * Adds actionlistener for filtering mission dropdown
	 */
	public MapDropdown() {
		super();
		addActionListener(e -> {
			if(getSelectedIndex() >= 0) {
				String map = names[getSelectedIndex()];
				LogHandler.logNoTime(HASHSPACE);
				LogHandler.logSeverity(INFO, "Filtering by map: '" + map + "'");
				SwingUtilities.invokeLater(() -> Core.getInstanceUI().filterMap(map));
			}
		});
		updateList();
	}

	/**
	 * Update the list based on the latest list of maps
	 */
	public void updateList() {
		removeAllItems();
		ArrayList<String> maps = MAPS;
		names = new String[maps.size()];
		if(names.length == 0) {
			names = new String[1];
			names[0] = "No maps";
			addItem(0);
		} else {
			int index = 0;
			for(String map : maps) {
				names[index] = map;
				addItem(index);
				index++;
			}
		}
		setRenderer(new CustomDropDownCellRenderer());
	}

	/**
	 * Custom cell renderer
	 */
	private class CustomDropDownCellRenderer extends CustomLabel implements ListCellRenderer {

		/**
		 * Call to custom label
		 */
		CustomDropDownCellRenderer() {
			super("", FONT_STANDARD.getStyle(), 14, true, COLOUR_WHITE, COLOUR_BLACK, "");
		}

		/**
		 * Gets component to render
		 * @param list list of objects
		 * @param value current value
		 * @param index current index
		 * @param isSelected true if selected
		 * @param cellHasFocus true if has focus
		 * @return component to render
		 */
		@Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			int selectionIndex = (Integer) value;

			if(isSelected) {
				setBackground(COLOUR_FOREGROUND_DARK);
				setForeground(COLOUR_BLACK);
			} else {
				setBackground(COLOUR_BACKGROUND_LIGHT);
				setForeground(COLOUR_WHITE);
			}
			if(selectionIndex < names.length) {
				String map = names[selectionIndex];
				if(map != null) {
					setText(map);
				}
			}
			return this;
		}
	}
}

/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;


import com.uksf.mm.gui.components.labels.CustomLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;

import static com.uksf.mm.utility.Info.*;

/**
 * @author Tim
 */
public class HomePanel extends JPanel {

    /**
     * Creates home panel
     */
    public HomePanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("fill", "0[]0", "0[]0"));


		GenericPanel selectionPanel = new GenericPanel("al left center", "0[]5[]5[]5[]0", "0[]0", true, COLOUR_BACKGROUND_LIGHTER);
		selectionPanel.setPreferredSize(new Dimension(getWidth(), 35));
		CustomLabel mapText = new CustomLabel("Map:", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		CustomLabel missionText = new CustomLabel("Mission:", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		JComboBox mapSelection = new JComboBox();
		JComboBox missionSelection = new JComboBox();
		selectionPanel.add(mapText, "cell 0 0");
		selectionPanel.add(mapSelection, "cell 1 0");
		selectionPanel.add(missionText, "cell 2 0");
		selectionPanel.add(missionSelection, "cell 3 0");

		add(selectionPanel, "dock north, growx, cell 0 0");
    }
}
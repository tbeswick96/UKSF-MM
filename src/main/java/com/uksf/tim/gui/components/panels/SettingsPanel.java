/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.gui.components.panels;


import com.uksf.tim.core.Settings;
import com.uksf.tim.gui.components.buttons.CustomButtonText;
import com.uksf.tim.gui.components.buttons.CustomRadioButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.tim.utility.Info.*;

/**
 * @author Tim
 */
public class SettingsPanel extends JPanel {

    private CustomRadioButton checkOnLaunch, checkWeekly, checkNever;
    private CustomButtonText updateNow;

    /**
     * Creates settings panel
     */
    public SettingsPanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("", "5[]5[]5", "5[]5[]5"));

        programSettings();
        buttonFunctionality();
    }

    private void programSettings() {
        GenericPanel programSettings = new GenericPanel("", "10[]20[]10", "10[]10", false, COLOUR_TRANSPARENT);
        programSettings.setBorder(BORDER_STANDARD);

        JLabel updateCheck = new JLabel("Update Check"); updateCheck.setForeground(COLOUR_WHITE);
        ButtonGroup group = new ButtonGroup();
        checkOnLaunch = new CustomRadioButton("On Launch"); checkOnLaunch.setSelected(UPDATE_CHECK);
        checkWeekly = new CustomRadioButton("Weekly"); checkWeekly.setSelected(UPDATE_WEEK);
        checkNever = new CustomRadioButton("Never"); checkNever.setSelected(!UPDATE_CHECK);
        updateNow = new CustomButtonText("Update Now", FONT_STANDARD, 16, "updateNow");

        group.add(checkOnLaunch); group.add(checkWeekly); group.add(checkNever);
        GenericPanel updateButtons = new GenericPanel("", "0[]0[]0[]5[]10[]0", "0[]0", false, COLOUR_TRANSPARENT);
        updateButtons.add(checkOnLaunch, "shrink, cell 1 0");
        updateButtons.add(checkWeekly, "shrink, cell 2 0");
        updateButtons.add(checkNever, "shrink, cell 3 0");
        updateButtons.add(new JSeparator(JSeparator.VERTICAL), "growy, cell 4 0");
        updateButtons.add(updateNow, "cell 5 0");

        programSettings.add(updateCheck, "shrink, cell 0 0");
        programSettings.add(updateButtons, "shrink, cell 1 0");

        add(programSettings, "dock north");
    }

    private void buttonFunctionality() {
        settingUpdate();
    }

    private void settingUpdate() {
        checkOnLaunch.addActionListener(e -> Settings.set("update_check", true));
        checkWeekly.addActionListener(e -> Settings.setMultiple(new String[]{"update_week", "update_time"}, new Object[]{true, Settings.weekAhead()}));
        checkNever.addActionListener(e -> Settings.setMultiple(new String[]{"update_check", "update_week"}, new Object[]{false, false}));
    }
}

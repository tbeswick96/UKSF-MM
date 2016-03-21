package com.uksf.tim.gui.components;


import com.uksf.tim.core.Settings;
import com.uksf.tim.gui.components.buttons.CustomRadioButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.tim.utility.Info.*;

public class SettingsPanel extends JPanel {

    private CustomRadioButton checkOnLaunch, checkWeekly, checkNever;

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

        group.add(checkOnLaunch); group.add(checkWeekly); group.add(checkNever);
        GenericPanel updateButtons = new GenericPanel("", "0[]0[]0[]0", "0[]0", false, COLOUR_TRANSPARENT);
        updateButtons.add(checkOnLaunch, "shrink, cell 1 0");
        updateButtons.add(checkWeekly, "shrink, cell 2 0");
        updateButtons.add(checkNever, "shrink, cell 3 0");

        programSettings.add(updateCheck, "shrink, cell 0 0");
        programSettings.add(updateButtons, "shrink, cell 1 0");

        add(programSettings, "dock north");
    }

    private void buttonFunctionality() {
        settingUpdate();
    }

    private void settingUpdate() {
        checkOnLaunch.addActionListener(e -> Settings.setBool("update_check", true));
        checkWeekly.addActionListener(e -> {
            Settings.setBool("update_week", true);
            Settings.setString("update_time", Settings.weekAhead());
        });
        checkNever.addActionListener(e -> {
            Settings.setBool("update_check", false);
            Settings.setBool("update_week", false);
        });
    }
}

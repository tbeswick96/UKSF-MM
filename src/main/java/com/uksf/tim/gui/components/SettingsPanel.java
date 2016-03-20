package main.java.com.uksf.tim.gui.components;

import main.java.com.uksf.tim.gui.components.buttons.CustomRadioButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static main.java.com.uksf.tim.Info.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("fill", "5[]5[]5", "5[]5[]5"));

        GenericPanel programSettings = new GenericPanel("fill", "10[]20[]10", "10[]10");
        programSettings.setBorder(BorderFactory.createLineBorder(COLOUR_BACKGROUND_DARK, 2));

        JLabel updateCheck = new JLabel("Update Check"); updateCheck.setForeground(COLOUR_WHITE);
        ButtonGroup group = new ButtonGroup();
        CustomRadioButton checkOnLaunch = new CustomRadioButton("On Launch");
        CustomRadioButton checkWeekly = new CustomRadioButton("Weekly");
        CustomRadioButton checkNever = new CustomRadioButton("Never");

        group.add(checkOnLaunch); group.add(checkWeekly); group.add(checkNever);

        GenericPanel updateButtons = new GenericPanel("", "0[]0[]0[]0", "0[]0");
        updateButtons.add(checkOnLaunch, "cell 1 0");
        updateButtons.add(checkWeekly, "cell 2 0");
        updateButtons.add(checkNever, "cell 3 0");

        programSettings.add(updateCheck, "push, cell 0 0");
        programSettings.add(updateButtons, "push, cell 1 0");

        add(programSettings, "dock north");
    }
}

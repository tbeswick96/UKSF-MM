package main.java.com.uksf.tim.gui.components;

import main.java.com.uksf.tim.gui.components.buttons.CustomButton25;

import javax.swing.*;

import static main.java.com.uksf.tim.Info.ICON_SETTINGS;
import static main.java.com.uksf.tim.Info.ICON_SETTINGS_HOVER;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        CustomButton25 settings = new CustomButton25(ICON_SETTINGS.getImage(), ICON_SETTINGS_HOVER.getImage(), "openSettings");
        add(settings);
        add(Box.createHorizontalGlue());
    }
}

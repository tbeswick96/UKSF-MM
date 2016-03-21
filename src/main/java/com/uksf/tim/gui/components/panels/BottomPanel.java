package com.uksf.tim.gui.components.panels;


import com.uksf.tim.gui.components.buttons.CustomButton25;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.uksf.tim.utility.Info.*;

public class BottomPanel extends JPanel {

    /**
     * Creates bottom panel
     */
    public BottomPanel() {
        setOpaque(true);
        setBackground(COLOUR_BACKGROUND_DARKER);
        setLayout(new MigLayout("", "2[]5[]2", "2[]2"));

        CustomButton25 home = new CustomButton25(ICON_HOME.getImage(), ICON_HOME_HOVER.getImage(), "showHome", "Home");
        CustomButton25 settings = new CustomButton25(ICON_SETTINGS.getImage(), ICON_SETTINGS_HOVER.getImage(), "showSettings", "Settings");

        add(home);
        add(settings);
    }
}

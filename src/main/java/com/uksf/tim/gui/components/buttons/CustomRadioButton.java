package com.uksf.tim.gui.components.buttons;

import javax.swing.*;
import static com.uksf.tim.utility.Info.*;

public class CustomRadioButton extends JRadioButton {

    /**
     * Creates radio button with given text
     * @param text text displayed next to button
     */
    public CustomRadioButton(String text) {
        super(text);
        setOpaque(false);
        setForeground(COLOUR_WHITE);
        setFocusPainted(false);
    }
}

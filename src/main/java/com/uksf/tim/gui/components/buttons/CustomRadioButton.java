/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

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

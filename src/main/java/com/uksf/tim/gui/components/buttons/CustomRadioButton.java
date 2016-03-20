package main.java.com.uksf.tim.gui.components.buttons;

import javax.swing.*;

import static main.java.com.uksf.tim.Info.COLOUR_WHITE;

public class CustomRadioButton extends JRadioButton {
    public CustomRadioButton(String text) {
        super(text);
        setOpaque(false);
        setForeground(COLOUR_WHITE);
        setFocusPainted(false);
    }
}

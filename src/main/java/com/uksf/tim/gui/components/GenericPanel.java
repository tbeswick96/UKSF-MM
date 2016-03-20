package main.java.com.uksf.tim.gui.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class GenericPanel extends JPanel {
    public GenericPanel(String layout, String columns, String rows) {
        setOpaque(false);
        setLayout(new MigLayout(layout, columns, rows));
    }
}

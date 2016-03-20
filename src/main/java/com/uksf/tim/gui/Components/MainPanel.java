package main.java.com.uksf.tim.gui.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setOpaque(false);
        setLayout(new MigLayout("fill", "", ""));
    }
}

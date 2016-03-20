package main.java.com.uksf.tim.gui;

import main.java.com.uksf.tim.Info;
import main.java.com.uksf.tim.core.Core;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class GUIStart extends JFrame {

    public GUIStart() {
        initWindow();
    }

    private void initWindow() {
        setTitle(Info.WINDOW_TITLE);
        setSize(Info.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black ));
            UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
            ImageIcon img = new ImageIcon("src\\main\\resources\\assets\\logos\\uksf16.png");
            setIconImage(img.getImage());
        } catch (Exception e) {
            sendError(e);
        }

        getContentPane().setBackground(new Color(45, 45, 45));

        setVisible(true);
    }

    public void sendError(Exception exception) {
        Core.getInstance().error(exception);
    }
}

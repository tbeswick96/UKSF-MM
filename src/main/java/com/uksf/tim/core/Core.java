package main.java.com.uksf.tim.core;

import main.java.com.uksf.tim.gui.GUIStart;

import javax.swing.*;
import java.awt.*;

public class Core {

    private static Core instance;

    public Core() {
        instance = this;

        try {
            SwingUtilities.invokeLater(() -> new GUIStart());
        } catch(Exception exception) {
            error(exception);
        }
    }

    public static Core getInstance() {
        return Core.instance;
    }

    public void error(Exception exception) {
        exception.printStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMessage());
        builder.append("\n");
        for (StackTraceElement element : exception.getStackTrace()) {
            builder.append(element.toString());
            builder.append("\n");
        }
        JTextArea printText = new JTextArea("Something went wrong.\n\n" + builder.toString());
        JScrollPane print = new JScrollPane(printText){
            @Override public Dimension getPreferredSize() {
                return new Dimension(500, 300);
            }
        };
        JOptionPane.showMessageDialog(null, print, "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(print);
        System.exit(0);
    }
}

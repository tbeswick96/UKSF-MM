package main.java.com.uksf.tim.gui;

import main.java.com.uksf.tim.gui.components.BottomPanel;
import main.java.com.uksf.tim.gui.components.MainPanel;
import main.java.com.uksf.tim.gui.components.SettingsPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.java.com.uksf.tim.Info.*;
import static main.java.com.uksf.tim.core.Core.error;

public class UI extends JFrame {

    private int state = 0;
    private MainPanel mainPanel;
    private BottomPanel bottomPanel;
    private SettingsPanel settingsPanel;

    public UI() {
        initWindow();
    }

    private void initWindow() {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_SIZE);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(COLOUR_BACKGROUND);
        try {
            ArrayList<Image> icons = new ArrayList<>();
            icons.add(LOGO_LIGHT_16.getImage());
            icons.add(LOGO_LIGHT_32.getImage());
            icons.add(LOGO_LIGHT_64.getImage());
            setIconImages(icons);
        } catch (Exception e) {
            error(e);
        }

        //Set layout to miglayout
        setLayout(new MigLayout(
                "fill", //fill space
                "2[]2", //column constraints
                "2[]2[]2" //row constraints
        ));

        //Create components
        createComponents();

        //Add components
        add(bottomPanel, "cell 0 1");
        updateFromState();

        //Set window to visible
        setVisible(true);
    }

    private void createComponents() {
        mainPanel = ComponentCreator.mainPanel();
        bottomPanel = ComponentCreator.bottomPanel();
        settingsPanel = ComponentCreator.settingsPanel();
    }

    private void updateFromState() {
        switch(state) {
            case 0:
                add(mainPanel, "grow, push, cell 0 0");
                break;
            case 50:
                remove(mainPanel);
                add(settingsPanel, "grow, push, cell 0 0");
                break;
            default:
                add(mainPanel, "grow, push, cell 0 0");
                break;
        }
        repaint();
        revalidate();
    }

    public void changeState(int newState) {
        state = newState;
        try {
            SwingUtilities.invokeLater(this::updateFromState);
        } catch(Exception exception) {
            error(exception);
        }
    }
}

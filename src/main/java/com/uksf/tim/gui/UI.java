package com.uksf.tim.gui;


import com.uksf.tim.gui.components.BottomPanel;
import com.uksf.tim.gui.components.MainPanel;
import com.uksf.tim.gui.components.SettingsPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.core.Core.error;

public class UI extends JFrame {

    /**
     * Current state of interface. Defines what is displayed in the main screen
     */
    private int state = 0;

    /**
     * Main panel instance. Holds other content panels
     */
    private MainPanel mainPanel;

    /**
     * Bottom panel instance. Holds persistent functionality components
     */
    private BottomPanel bottomPanel;

    /**
     * Settings panel instance. Displays program settings
     */
    private SettingsPanel settingsPanel;

    /**
     * Create UI
     */
    public UI() {
        initUI();
    }

    /**
     * Initialise UI
     */
    private void initUI() {
        //Set title, size, and position
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_SIZE);
        setMinimumSize(WINDOW_SIZE_MIN);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Set background and icons
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
        add(mainPanel, "grow, push, cell 0 0");
        add(bottomPanel, "grow, cell 0 1");
        updateFromState();

        //Set window to visible
        setVisible(true);
    }

    /**
     * Creates base components
     */
    private void createComponents() {
        mainPanel = ComponentCreator.mainPanel();
        bottomPanel = ComponentCreator.bottomPanel();
        settingsPanel = ComponentCreator.settingsPanel();
    }

    /**
     * Updates display of mainPanel depending current state
     */
    private void updateFromState() {
        switch(state) {
            case 0:
                break;
            case 50:
                mainPanel.add(settingsPanel, "grow, push, cell 0 0");
                break;
            default:
                break;
        }
        repaint();
        revalidate();
    }

    /**
     * Chnages state and calls an update
     * @param newState state value to change to
     */
    public void changeState(int newState) {
        state = newState;
        try {
            SwingUtilities.invokeLater(this::updateFromState);
        } catch(Exception exception) {
            error(exception);
        }
    }
}

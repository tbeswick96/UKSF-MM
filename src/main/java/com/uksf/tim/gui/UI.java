/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.tim.gui;


import com.uksf.tim.gui.components.panels.BottomPanel;
import com.uksf.tim.gui.components.panels.MainPanel;
import com.uksf.tim.gui.components.panels.SettingsPanel;
import com.uksf.tim.utility.LogHandler;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.uksf.tim.core.Core.error;
import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

public class UI extends JFrame implements MouseInputListener {

    /**
     * Start position of mouse when dragging
     */
    private Point startPos = null;

    /**
     * Path for resize
     */
    private java.awt.geom.GeneralPath gp;

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
     * List of panels currently active in the main panel
     */
    private ArrayList<JPanel> added;

    /**
     * Create UI
     */
    public UI() {
        added = new ArrayList<>();
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
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Add resize mouse listeners
        addMouseListener(this);
        addMouseMotionListener(this);

        //Set background and icons
        setBackground(COLOUR_BACKGROUND);
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
                "2[]2[]0" //row constraints
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
        LogHandler.logSeverity(INFO, "State changed");
        removeAdded();
        switch(state) {
            case 0:
                LogHandler.logSeverity(INFO, "State 0");
                break;
            case 50:
                LogHandler.logSeverity(INFO, "State 50");
                mainPanel.add(settingsPanel, "grow, push, cell 0 0");
                added.add(settingsPanel);
                break;
            default:
                break;
        }
        revalidate();
        repaint();
    }

    /**
     * Removes added panels from frame and clears list
     */
    private void removeAdded() {
        if(added.isEmpty()) return;
        for(JPanel panel : added) {
            mainPanel.remove(panel);
        }
        added.clear();
    }

    /**
     * Changes state and calls an update
     * @param newState state value to change to
     */
    public void changeState(int newState) {
        LogHandler.logSeverity(INFO, "State change requested. Current state: " + state + " Requested State: " + newState);
        if(state != newState) {
            state = newState;
            try {
                SwingUtilities.invokeLater(this::updateFromState);
            } catch(Exception exception) {
                error(exception);
            }
            return;
        }
        LogHandler.logSeverity(INFO, "State change denied");
    }

    /**
     * Paint event, better resizing
     * @param g graphics object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();

        g.setColor(COLOUR_FOREGROUND);
        g.drawLine(width - 7, height, width, height - 7);
        g.drawLine(width - 11, height, width, height - 11);
        g.drawLine(width - 15, height, width, height - 15);

        gp = new java.awt.geom.GeneralPath();
        gp.moveTo(width - 17, height);
        gp.lineTo(width, height - 17);
        gp.lineTo(width, height);
        gp.closePath();
    }

    /**
     * Mouse clicked event
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Mouse pressed event
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (gp.contains(e.getPoint())) {
            startPos = new Point(getWidth()-e.getX(), getHeight()-e.getY());
        }
    }

    /**
     * Mouse released event
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        startPos = null;
    }

    /**
     * Mouse entered event
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Mouse exited event
     * @param e event
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Mouse dragged event
     * @param e event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (startPos != null) {
            int dx = e.getX() + startPos.x;
            int dy = e.getY() + startPos.y;
            setSize(dx, dy);
            repaint();
        }
    }

    /**
     * Mouse moved event
     * @param e event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (gp.contains(e.getPoint())) {
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

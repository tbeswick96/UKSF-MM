/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui;


import com.uksf.mm.core.utility.LogHandler;
import com.uksf.mm.core.sqm.SqmLoader;
import com.uksf.mm.core.sqm.SqmSaver;
import com.uksf.mm.core.sqm.parsers.ParserAuthor;
import com.uksf.mm.gui.components.panels.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.uksf.mm.core.Core.error;
import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static com.uksf.mm.core.utility.LogHandler.Severity.WARNING;

/**
 * @author Tim
 */
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
     * Panel instances
     */
    private MainPanel mainPanel;
	private TopPanel topPanel;
    private BottomPanel bottomPanel;
	private HomePanel homePanel;
    private SettingsPanel settingsPanel;

    /**
     * List of panels currently active in the main panel
     */
    private ArrayList<JPanel> added;

	/**
     * Create UI
     */
    public UI() {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "UI Started");
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
		setUndecorated(true);
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
                "0[]0", //column constraints
                "0[]0[]0[]0" //row constraints
        ));

        //Create components
        createComponents();

        //Add components
		add(topPanel, "grow, cell 0 0");
        add(mainPanel, "grow, push, cell 0 1");
        add(bottomPanel, "grow, cell 0 2");
        updateFromState();

        //Set window to visible
        setVisible(true);
    }

    /**
     * Creates base components
     */
    private void createComponents() {
		topPanel = new TopPanel(this);
		mainPanel = new MainPanel();
        bottomPanel = new BottomPanel();
		homePanel = new HomePanel();
        settingsPanel = new SettingsPanel();
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
				mainPanel.addPanel(homePanel);
				added.add(homePanel);
                break;
            case 50:
                LogHandler.logSeverity(INFO, "State 50");
                mainPanel.addPanel(settingsPanel);
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
            mainPanel.removePanel(panel);
        }
        added.clear();
    }

    /**
     * Changes state and calls an update
     * @param newState state value to change to
     */
    public void changeState(int newState) {
		LogHandler.logNoTime(HASHSPACE);
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
        LogHandler.logSeverity(WARNING, "State change denied");
    }

	/**
	 * Switches the state of the update button in the settings panel
	 * @param state new enabled state
	 */
	public void enableUpdate(boolean state) {
		try {
			SwingUtilities.invokeLater(() -> settingsPanel.enableUpdate(state));
		} catch(Exception exception) {
			error(exception);
		}
	}

	/**
	 * Opens file chooser to change missions folder
	 */
	public void changeMissionsFolder() {
		try {
			SwingUtilities.invokeLater(() -> settingsPanel.changeMissionsFolder());
		} catch(Exception exception) {
			error(exception);
		}
	}

	/**
	 * Change the selected update option
	 * @param launch check on launch state
	 * @param week check of week state
	 * @param never check never state
	 */
	public void changeCheckStates(boolean launch, boolean week, boolean never) {
		settingsPanel.changeCheckStates(launch, week, never);
	}

	/**
	 * Updates version text
	 */
	public void updateVersionText() {
		bottomPanel.updateVersionText();
	}

	/**
	 * Update the dropdown lists
	 */
	public void updateDropdowns() {
		homePanel.updateLists();
	}

	/**
	 * Filters mission list based on selected map name
	 * @param name map to name to filter by
	 */
	public void filterMap(String name) {homePanel.filterMap(name);}

	/**
	 * Loads selected mission
	 * @return true if load worked
	 */
	public boolean loadSelectedMission() {
		MISSION_SELECTED = homePanel.getSelectedMission();
		boolean state = SqmLoader.loadMission();
		state = state && ParserAuthor.getAuthor();
		if(state) {
			homePanel.addMission();
			revalidate();
			repaint();
		}
		return state;
	}

	/**
	 * Saves selected mission
	 */
	public void saveSelectedMission() {
		SqmSaver.saveMission();
	}

	/**
	 * Minimizes program
	 */
	public void minimize() {
		setExtendedState(Frame.ICONIFIED);
	}

	/**
	 * Maximizes program
	 */
	public void maximize() {
		if(getExtendedState() < 1) {
			setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
		} else {
			setExtendedState(Frame.NORMAL);
		}
	}

    /**
     * Paint event, better resizing
     * @param graphics graphics object
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        int width = getWidth();
        int height = getHeight();
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

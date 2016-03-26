/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.gui;


import com.uksf.updater.gui.components.panels.MainPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.uksf.updater.core.Core.error;
import static com.uksf.updater.utility.Info.*;

/**
 * @author Tim
 */
public class UI extends JFrame {
    /**
     * Panel instances
     */
    private MainPanel mainPanel;

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
        setSize(WINDOW_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);
		setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                "0[]0" //row constraints
        ));

        //Create components
        createComponents();

        //Add components
        add(mainPanel, "grow, push");

        //Set window to visible
        setVisible(true);
    }

    /**
     * Creates base components
     */
    private void createComponents() {
        mainPanel = new MainPanel(this);
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
}

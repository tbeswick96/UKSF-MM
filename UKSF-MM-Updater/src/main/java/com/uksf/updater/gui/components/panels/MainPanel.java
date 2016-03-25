/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.gui.components.panels;

import com.uksf.updater.core.Core;
import com.uksf.updater.core.DownloadWorker;
import com.uksf.updater.gui.components.buttons.CustomProgressBar;
import com.uksf.updater.utility.Network;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import static com.uksf.updater.core.Core.error;
import static com.uksf.updater.utility.Info.*;

/**
 * @author Tim
 */
public class MainPanel extends JPanel {

	private Point initialClick;

    /**
     * Creates main panel
     */
    public MainPanel(JFrame parent) {
		setOpaque(false);
		setBorder(BORDER_COLOURED);
        setLayout(new MigLayout("", "0[center]0", "0[bottom]0[top]0"));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				getComponentAt(initialClick);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = parent.getLocation().x;
				int y = parent.getLocation().y;
				int xNew = x + (x + e.getX()) - (x + initialClick.x);
				int yNew = y + (y + e.getY()) - (y + initialClick.y);
				parent.setLocation(xNew, yNew);
			}
		});

		try {
			init();
		} catch(IOException e) {
			error(e);
		}
	}

	private void init() throws IOException {
		GenericPanel infoPanel = new GenericPanel("", "0[center]0", "0[]5[]0", false, COLOUR_TRANSPARENT);
		GenericPanel infoPanelText = new GenericPanel("", "0[]0[]0", "0[]0", false, COLOUR_TRANSPARENT);
		JLabel infoLogo = new JLabel(LOGO_256);
		JLabel infoText = new JLabel("Updating to version: ");
		JLabel infoVersion = new JLabel(Network.getDataFromTag("<Version>"));
		infoText.setFont(new Font(FONT_STANDARD.getFontName(), FONT_STANDARD.getStyle(), 20));
		infoVersion.setFont(new Font(FONT_STANDARD.getFontName(), FONT_STANDARD.getStyle(), 20));
		infoText.setForeground(COLOUR_WHITE); infoVersion.setForeground(COLOUR_FOREGROUND);

		GenericPanel downloadPanel = new GenericPanel("", "0[center]0", "0[]5[]0", false, COLOUR_TRANSPARENT);
		JLabel downloadText = new JLabel("Downloading");
		downloadText.setFont(new Font(FONT_STANDARD.getFontName(), FONT_STANDARD.getStyle(), 20));
		downloadText.setForeground(COLOUR_WHITE);
		CustomProgressBar downloadProgress = new CustomProgressBar();

		infoPanelText.add(infoText, "cell 0 1");
		infoPanelText.add(infoVersion, "push, cell 1 1");
		infoPanel.add(infoLogo, "span, cell 0 0");
		infoPanel.add(infoPanelText, "cell 0 1");
		downloadPanel.add(downloadText, "cell 0 0");
		downloadPanel.add(downloadProgress, "cell 0 1");

		add(infoPanel, "push, cell 0 0");
		add(downloadPanel, "push, cell 0 1");


		final DownloadWorker downloadWorker = new DownloadWorker();
		downloadWorker.addPropertyChangeListener(pcEvt -> {
			if ("progress".equals(pcEvt.getPropertyName())) {
				int percent = (int) pcEvt.getNewValue();
				downloadProgress.setValue(percent);
				downloadProgress.setString(percent + "%");
				downloadProgress.setToolTipText(percent + "%");
				repaint();
			} else if (pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
				downloadProgress.setValue(100);
				downloadText.setText("Download Finished");
				Core.getInstance().runMain();
			}
		});
		SwingUtilities.invokeLater(downloadWorker:: execute);
	}
}

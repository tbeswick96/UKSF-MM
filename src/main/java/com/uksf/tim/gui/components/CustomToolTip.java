package com.uksf.tim.gui.components;

import com.uksf.tim.gui.components.panels.ToolTipPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CustomToolTip extends JToolTip {

    /**
     * Tooltip panel components
     */
    private ToolTipPanel toolTipPanel;

    public CustomToolTip() {
        super();
        setBorder(null);
        toolTipPanel = new ToolTipPanel();
        setLayout(new MigLayout("fill", "0[]0", "0[]0"));
        add(toolTipPanel, "grow, cell 0 0");
    }

    @Override public Dimension getPreferredSize() {
        return toolTipPanel.getPreferredSize();
    }

    @Override public void setTipText(String tipText) {
        if (tipText != null && !tipText.isEmpty()) {
            toolTipPanel.setText(tipText);
        } else {
            super.setTipText(tipText);
        }
    }
}

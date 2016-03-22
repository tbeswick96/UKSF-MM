package com.uksf.tim.gui.components.buttons;


import com.uksf.tim.gui.components.CustomToolTip;
import com.uksf.tim.utility.Invokable;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import static com.uksf.tim.core.Core.error;
import static com.uksf.tim.utility.Info.BORDER_STANDARD;
import static com.uksf.tim.utility.Info.COLOUR_TRANSPARENT;
import static com.uksf.tim.utility.LogHandler.Severity;
import static com.uksf.tim.utility.LogHandler.logSeverity;

public class CustomButton25 extends JPanel implements MouseListener {

    /**
     * Hover state. True when hovering, false when not
     */
    private boolean hovered = false;

    /**
     * Images for base icon and hover icon
     */
    private Image icon, icon_hovered;

    /**
     * Method name to call on click
     */
    private String toCall;

    /**
     * Tooltip text to display on hover
     */
    private String tooltipText;

    /**
     * Graphics object (allows obtaining of string size for tooltip)
     */
    private Graphics2D g;

    /**
     * Create custom button
     * @param icon base icon
     * @param icon_hovered hover icon
     * @param toCall method name to call
     */
    public CustomButton25(Image icon, Image icon_hovered, String toCall, String tooltipText) {
        this.icon = icon;
        this.icon_hovered = icon_hovered;
        this.toCall = toCall;
        this.tooltipText = tooltipText;

        setSize(getPreferredSize());
        setBorder(null);
        setOpaque(false);
        addMouseListener(this);
        setToolTipText("");
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getPreferredSize() {
        return new Dimension(27, 27);
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getMaximumSize() { return new Dimension(27, 27); }

    /**
     * Paints button with custom icons
     * @param graphics graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(COLOUR_TRANSPARENT);
        g.fillRect(0, 0, 27, 27);
        if(hovered) {
            g.drawImage(icon_hovered, 0, 0, 27, 27, null);
        } else {
            g.drawImage(icon, 1, 1, 25, 25, null);
        }
    }

    /**
     * Create custom tooltip
     * @return tooltip object
     */
    @Override public JToolTip createToolTip() {
        JToolTip tooltip = super.createToolTip();
        tooltip.setBorder(BORDER_STANDARD);
        tooltip.setLayout(new MigLayout("fill", "0[]0", "0[]0"));

        CustomToolTip label = new CustomToolTip(tooltipText, g);
        tooltip.setPreferredSize(label.getPreferredSize());

        tooltip.add(label, "grow");
        return tooltip;
    }

    /**
     * Mouse clicked event
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Invokable.class.getMethod(toCall).invoke(Invokable.instance);
        } catch(NoSuchMethodException exception) {
            logSeverity(Severity.CRITICAL, "No such method: " + toCall);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            error(exception);
        }
    }

    /**
     * Mouse pressed event
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Mouse released event
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Mouse entered event
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
    }

    /**
     * Mouse exited event
     * @param e event
     */
    @Override
    public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
    }
}

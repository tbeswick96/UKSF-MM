package com.uksf.tim.gui.components.buttons;


import com.uksf.tim.utility.Invokable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import static com.uksf.tim.core.Core.error;
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
     * Create custom button
     * @param ic base icon
     * @param ic_hovered hover icon
     * @param method method name to call
     */
    public CustomButton25(Image ic, Image ic_hovered, String method) {
        setSize(getPreferredSize());
        setBorder(null);
        setOpaque(false);
        addMouseListener(this);

        icon = ic;
        icon_hovered = ic_hovered;
        toCall = method;
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getPreferredSize() {
        return new Dimension(getX() + 27, getY() + 27);
    }

    /**
     * Set size to 27x27
     * @return dimension 27x27
     */
    @Override public Dimension getMaximumSize() { return new Dimension(getX() + 27, getY() + 27); }

    /**
     * Paints button with custom icons
     * @param graphics graphics object
     */
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
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

package main.java.com.uksf.tim.gui.components.buttons;

import main.java.com.uksf.tim.utility.Invokable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import static main.java.com.uksf.tim.Info.COLOUR_TRANSPARENT;
import static main.java.com.uksf.tim.core.Core.error;

public class CustomButton25 extends JPanel implements MouseListener {

    private boolean hovered = false;
    private Image icon, icon_hovered;
    private String toCall;

    public CustomButton25(Image ic, Image ic_hovered, String method) {
        setSize(getPreferredSize());
        setBorder(null);
        setOpaque(false);
        addMouseListener(this);

        icon = ic;
        icon_hovered = ic_hovered;
        toCall = method;
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(getX() + 27, getY() + 27);
    }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Invokable.class.getMethod(toCall).invoke(Invokable.instance);
        } catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            error(exception);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
    }
}

package GUI;

import Visuals.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class MyGlassPane extends JComponent implements MenuListener, WindowFocusListener {
    Point point;
    BufferedImage dragIcon;
    public Point offset;
    public Point imgPoint;
    CBListener listener;

    public void menuCanceled(MenuEvent e) {
        setVisible(true);
    }

    public void menuDeselected(MenuEvent e) {
        setVisible(true);
    }

    public void menuSelected(MenuEvent e) {
        setVisible(false);
    }

    public void windowGainedFocus(WindowEvent e) {
        setVisible(false);
    }

    public void windowLostFocus(WindowEvent e) {
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(dragIcon != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(dragIcon, imgPoint.x, imgPoint.y, this);
            g2d.dispose();
        }
    }

    protected Rectangle getImageBounds() {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);
        if (listener.temp != null) {
            Point rectPoint = SwingUtilities.convertPoint(listener.component.getParent(), listener.component.getLocation(), this);
            bounds.setLocation(rectPoint);
            bounds.setSize(listener.component.getWidth(), listener.component.getHeight());
        }
        return bounds;
    }

    public void setPoint(Point p) {
        point = p;
    }

    public void setDragIcon(BufferedImage b) {
        dragIcon = b;
    }

    public MyGlassPane(Container contentPane, MenuBuilder menuBar) {
        listener = new CBListener(menuBar, this, contentPane);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
}

class CBListener extends MouseInputAdapter {
    Toolkit toolkit;
    Component optionsButton;
    MenuBuilder menuBar;
    MyGlassPane glassPane;
    Container contentPane;
    ArrayList<Component> cs = new ArrayList();
    Point glassPanePoint;
    Container container;
    Point containerPoint;
    public Component component;
    public BufferedImage temp;

    public CBListener(MenuBuilder menuBar, MyGlassPane glassPane, Container contentPane) {
        toolkit = Toolkit.getDefaultToolkit();
        this.menuBar = menuBar;
        this.optionsButton = (Component)menuBar.menu;
        this.glassPane = glassPane;
        this.contentPane = contentPane;
        ChessSquare[][] chessSquares = menuBar.p.getSquares();
        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            ChessSquare chessSq = chessSquares[i][j];
            cs.add(chessSq);
          }
        }
    }


    public void mouseDragged(MouseEvent e) {
      if(!menuBar.p.locked) {
        if(!menuBar.p.highlightedSquares.isEmpty()){
          if (glassPane.offset != null) {
              ((ChessSquare)component).setIcon(null);
              glassPane.setDragIcon(temp);
              Point mp = e.getPoint();
              glassPane.imgPoint.x = mp.x - glassPane.offset.x;
              glassPane.imgPoint.y = mp.y - glassPane.offset.y;
              glassPane.repaint();
          }
          if(!ButtonListener.dragEvent)
            ButtonListener.dragEvent = true;
       }
     }
    }

    public void mouseClicked(MouseEvent e) {
      //  redispatchMouseEvent(e);
    }

    public void mousePressed(MouseEvent e) {
      if(!menuBar.p.locked) {
        glassPane.imgPoint = e.getPoint();
        redispatchMouseEvent(e);
        Rectangle bounds = glassPane.getImageBounds();
        Point mp = e.getPoint();
        if (bounds.contains(mp)) {
            glassPane.offset = new Point();
            glassPane.offset.x = (temp.getWidth()/2);
            glassPane.offset.y = (temp.getHeight()/2);
        }
      }
    }

    public void mouseReleased(MouseEvent e) {
      if(!menuBar.p.locked) {
        glassPane.setDragIcon(null);
        glassPane.repaint();
        menuBar.p.updateBoard();
        redispatchMouseEvent(e);
        glassPane.offset = null;

        if(menuBar.p.highlightedSquares.isEmpty() && !ButtonListener.moveMade) {

        } else {
          temp = null;
        }
     }
   }




    private void redispatchMouseEvent(MouseEvent e) {
        glassPanePoint = e.getPoint();
        if (menuBar.getBounds().contains(glassPanePoint)) {
            container = (Container)menuBar;
            containerPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, container);
            component = SwingUtilities.getDeepestComponentAt(container, containerPoint.x, containerPoint.y);
        } else {
            container = contentPane;
            containerPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, container);
            component = SwingUtilities.getDeepestComponentAt(container, containerPoint.x, containerPoint.y);
        }

        if ((component != null) && ((component.equals(optionsButton)) || (cs.contains(component)))) {
          if(cs.contains(component) && e.getID() != MouseEvent.MOUSE_RELEASED) {
              String[] command = ((ChessSquare)component).getActionCommand().split(" ");
                  temp = Images.getBufferedImage(command[0], command[1]);
          }
          Point componentPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, component);
          component.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(), e.getModifiers(), componentPoint.x, componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
        }
    }
}

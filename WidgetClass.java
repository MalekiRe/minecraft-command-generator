import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class WidgetClass extends JPanel implements MouseInputListener, Cloneable
{
      protected WidgetContainer container;
      private JTextField textField;
      private int borderThickness;
      private int borderRoundess;
      private boolean roundBorder;
      private String widgetName;
      private boolean firstDraw = false;
      protected int offset = 0; //An Offset to fix glitchy sides when moving around.
      private int x;
      private int y;
      private int width;
      private int height;
      protected boolean draggable;
      protected boolean isContainerVisible = true;
      
      public WidgetClass()
      {
         this.x = 0;
         this.y = 0;
         this.width = 10;
         this.height = 10;
         draggable = false;
         this.borderThickness = 10;
         this.borderRoundess = 20;
         this.roundBorder = true;
         this.setLayout(null);
         
         enableInputMethods(true);   
         addMouseListener(this);
         addMouseMotionListener(this);
         if(isContainerVisible)
         {
            container = new WidgetContainer();
         }
      
      }
      public WidgetClass(int x, int y, int width, int height, boolean isContainerVisible)
      {
         this();
         this.width = width+(offset*2);
         this.height = height+(offset*2);
         this.x = x-offset;
         this.y = y-offset;
         
         if(isContainerVisible)
         {
            container = new WidgetContainer(offset, offset, width, height);
         }
         this.isContainerVisible = isContainerVisible;
         if(isContainerVisible)
         {
            this.add(container);
         }
         else
         {
            //container.setBounds(0, 0, 0, 0);
         }
         this.setBounds(this.x, this.y, this.width, this.height);
         this.reload();
         
         

         
      }
      public void addName(String widgetName)
      {
         if(this.isContainerVisible)
         {
            this.widgetName = widgetName;
            this.container.setText(widgetName);
            this.reload();
         }
      }
      @Override
      public void paintComponent(Graphics graphics)
      {
         if(!this.firstDraw)
         {
            this.firstDraw = true;
            if(this.isContainerVisible)
            {
               this.add(container);
            }
            else
            {
               //this.container.setBounds(-100, -100, 0, 0);
            }
         }
         if(draggable == false)
         {
            this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
         }
      }
      public boolean Contains(int x, int y, int layerDepth)
      {
         if((int)this.container.getLocationOnScreen().getX() < x && (int)this.container.getLocationOnScreen().getY() < y)
         {
            if((int)this.container.getLocationOnScreen().getX()+this.getWidth() > x)
            {
               if((int)this.container.getLocationOnScreen().getY()+this.getHeight() > y)
               {
                  return true;
               }
            }
         }
         return false;
      }
      public String getText()
      {
         return "";
      }
      public void setX(int x){this.x = x-offset;}
      public void setY(int y){this.y = y-offset;}
      public void setWidth(int width){this.width = width+(offset*2);}
      public void setHeight(int height){this.height = height+(offset*2);}
      public void setBounds(int x, int y, int width, int height)
      {
         this.setX(x);
         this.setY(y);
         this.setWidth(width);
         this.setHeight(height);
         
         super.setBounds(x, y, width, height);
         container.setBounds(offset, offset, width, height);
         this.reload();
         
         //container.setBounds(offset, offset, width-offset*2, height-offset*2);
      }
      public int getX()
      { 
      if(!this.draggable){return this.x;}
      else{return super.getX();}
      }
      public int getY()
      {
      if(!this.draggable){return this.y;}
      else{return super.getY();}
      }
      public int getWidth()
      {
      if(!this.draggable){return this.width;}
      else{return super.getWidth();}
      }
      public int getHeight()
      {
      if(!this.draggable){return this.height;}
      else{return super.getHeight();}
      }
      
      public GhostRoundedJTextField getVariableObject(){return new GhostRoundedJTextField();}
      public void setDraggable(boolean draggable){this.draggable = draggable;}
      public void setContainerVisible(boolean visible){this.isContainerVisible = visible;}
      public boolean getContainerVisible(){return this.isContainerVisible;}
      public void mouseExited(MouseEvent e){}
      public void mouseEntered(MouseEvent e){} 
      public void mouseReleased(MouseEvent e){}
      public void mousePressed(MouseEvent e){}
      public void mouseClicked(MouseEvent e){}
      public void mouseMoved(MouseEvent e){}
      public void mouseDragged(MouseEvent e){}
      
      public void reload()
      {
         this.repaint();
         if(this.isContainerVisible)
         {
            this.container.repaint();
         }
         this.revalidate();
         
         //this.revalidate();
      }
      public Object clone() throws
                   CloneNotSupportedException 
    { 
        return super.clone(); 
    } 
      
      public static void main(String args[])
      {
      
         ComponentMover cm = new ComponentMover();
         JFrame frame = new JFrame("Test Frame");
         frame.setSize(800, 800);
         frame.setLayout(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         WidgetClass widget1 = new WidgetClass(30, 30, 400, 100, true);
         widget1.setContainerVisible(true);
         frame.add(widget1);
         cm.registerComponent(widget1);
         widget1.setDraggable(true);
         frame.setVisible(true);
         frame.repaint();
         frame.revalidate();
         widget1.addName("execute");
      
      }
}
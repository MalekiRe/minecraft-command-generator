import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
public class WidgetFrame extends JFrame implements MouseInputListener
{
   private ArrayList<Component> allComponents = new ArrayList<Component>();
   public WidgetFrame()
   {
      super();
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
   }
   public WidgetFrame(String s1)
   {
      super(s1);
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
   }
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){} 
   public void mouseReleased(MouseEvent e){}
   public void mousePressed(MouseEvent e)
   {
      System.out.println("asdfsdfsf");
      for(Component c : this.getComponents())
      {
         if(c instanceof WidgetBlock)
         {
            if(((WidgetBlock)c).getIsBeingDragged())
            {
               this.getLayeredPane().moveToFront((WidgetBlock)(c));
            }
         }
      }
   }
   public void mouseClicked(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e)
   {
      System.out.println("gjfkjdkg");
      for(Component c : this.getComponents())
         {
            if(c instanceof WidgetBlock)
            {
               if(((WidgetBlock)c).getIsBeingDragged())
               {
                  this.getLayeredPane().moveToBack((WidgetBlock)(c));
               }
            }
         }
   }
   public static void main(String[] args)
   {
      WidgetFrame frame = new WidgetFrame();
      frame.setSize(100, 100);
      frame.setVisible(true);
   }
   
}
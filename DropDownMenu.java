import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
public class DropDownMenu extends WidgetBlock
{
   String listOfItem[];
   
   public static void main(String args[])
   {
      
      JFrame frame = new JFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      UpsideDownTriangle triangle = new UpsideDownTriangle(25, 25, 50, 50);
      frame.add(triangle);
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
   }
}
class UpsideDownTriangle extends JComponent implements MouseInputListener
{
   int x[] = new int[3];
   int y[] = new int[3];
   Color color = Color.blue;
   public UpsideDownTriangle(int x, int y, int width, int height)
   {
      int xTemp[] = {0, (width/2), width};
      int yTemp[] = {0, height, 0};
      this.x = xTemp;
      this.y = yTemp;
      this.setBounds(x, y, width, height);
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      g.setColor(color);
      g.fillPolygon(x, y, 3);
      
      
   }
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){} 
   public void mouseReleased(MouseEvent e){}
   public void mousePressed(MouseEvent e){}
   public void mouseClicked(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e){}
}

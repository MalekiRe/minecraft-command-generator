import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
public class GhostRoundedJTextField extends RoundedJTextField implements MouseInputListener,FocusListener
{
   String ghostText = "";
   private int size = 10;
   public GhostRoundedJTextField()
   {
      super(10, 0, 0, 10, 10);
      addListeners();
   }
   public GhostRoundedJTextField(int size)
   {
      super(size, 0, 0, 10, 10);
      addListeners();
   }
   public GhostRoundedJTextField(int size, String ghostText)
   {
      this(size);
      this.ghostText = ghostText;
      this.setText(ghostText);
      addListeners();
   }
   public GhostRoundedJTextField(int x, int y, int width, int height)
   {
      this(10);
      this.setBounds(x, y, width, height);
      addListeners();
   }
   public GhostRoundedJTextField(int size, int x, int y, int width, int height)
   {
      super(size, x, y, width, height);
      addListeners();
   }
   public GhostRoundedJTextField(int size, int x, int y, int width, int height, String ghostText)
   {
      super(size, x, y, width, height);
      this.setBounds(x, y, width, height);
      this.ghostText = ghostText;
      this.setText(ghostText);
      addListeners();
   }
   public void reload()
   {
      this.repaint();
   }
   public void addListeners()
   {
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
      addFocusListener(this);
   }
   public void focusGained(FocusEvent e) 
   {
      if(this.getText().equals(this.ghostText))
      {
         this.setText("");
      }
   }
      
   public void focusLost(FocusEvent e)
   {
      if(this.getText().equals(""))
      {
         this.setText(this.ghostText);
      }
   }
   
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){} 
   public void mouseReleased(MouseEvent e){}
   public void mousePressed(MouseEvent e){}
   public void mouseClicked(MouseEvent e){}
   public void mouseMoved(MouseEvent e){this.reload();}
   public void mouseDragged(MouseEvent e){}


public static void main(String args[])
{

   
         JFrame frame = new JFrame("Test Frame");
         frame.setSize(800, 800);
         frame.setLayout(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         GhostRoundedJTextField dummy1 = new GhostRoundedJTextField(20, 20, 20, 300, 100, "test2");
         GhostRoundedJTextField dummy2 = new GhostRoundedJTextField(100, 600, 20, 300, 100, "test2");
         frame.getContentPane().add(dummy1);
         frame.getContentPane().add(dummy2);
         frame.setVisible(true);

}
}
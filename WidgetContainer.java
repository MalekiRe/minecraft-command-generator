import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
public class WidgetContainer extends JLayeredPane implements Cloneable
{

   private int roundess;
   private boolean roundBorder;
   public String text = "";
   public Color color;
   public int stringWidth = 0;
   public WidgetContainer()
   {
      this.roundess = 10;
      this.roundBorder = true;
      this.color = Color.red;
   }
   public WidgetContainer(int x, int y, int width, int height)
   {
      this();
      this.setBounds(x, y, width, height);
   }
   public WidgetContainer(int x, int y, int width, int height, boolean roundBorder)
   {
      this(x, y, width, height);
      this.roundBorder = roundBorder;
   }
   public WidgetContainer(int x, int y, int width, int height, String text)
   {
      this(x, y, width, height);
      this.text = text;
   }
   @Override
   public void paintComponent(Graphics graphics)
   {
      graphics.setColor(this.color);
      graphics.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.getRoundess(), this.getRoundess());
      if(!text.equals(""))
      {
         graphics.setColor(Color.black);
         this.stringWidth = graphics.getFontMetrics().stringWidth(text);
         
         graphics.drawString(this.text, 0, (this.getHeight()/2)+5);
         
      }
   }
   public void reload()
   {
      this.repaint();
   }
   public int getRoundess()
   {
      return this.roundess;
   }
   public void setColor(Color color)
   {
      this.color = color;
   }
   public void setRoundess(int roundess)
   {
      this.roundess = roundess;
   }
   public void setText(String text)
   {
      this.text = text;
   }
   public Object clone() throws
                   CloneNotSupportedException 
    { 
         return super.clone();
    }
}
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
public class WidgetWithVariable extends WidgetClass
{
   public GhostRoundedJTextField variable1 = new GhostRoundedJTextField();
   private String ghostText;
   private int borderSize = 30;
   private int arrayIndex = 0;
   public WidgetWithVariable()
   {
      super();
      if(this.isContainerVisible)
      {
         this.setVariableObject(new GhostRoundedJTextField());
      }
      this.borderSize = 80;
   }
   public WidgetWithVariable(int x, int y, int width, int height, String ghostText, boolean isContainerVisible)
   {
      
      super(x, y, width, height, isContainerVisible);
      this.ghostText = ghostText;
      this.borderSize = 30;
      this.variable1 = new GhostRoundedJTextField(10, this.offset+borderSize, this.offset+borderSize, width-(borderSize*2), height-(borderSize*2), ghostText);
      this.setVariableObject(new GhostRoundedJTextField(10, this.offset+borderSize, this.offset+borderSize, width-(borderSize*2), height-(borderSize*2), ghostText));
      if(this.isContainerVisible)
      {
         this.add(variable1);
      }
      this.variable1.setWidgetWithVariable(this);
      
   }
   public String getText()
   {
      return variable1.getText();
   }
   public void setBounds(int x, int y, int width, int height)
   {
      super.setBounds(x, y, width, height);
      this.borderSize = 10;
      if(this.variable1 == null)
      {
         this.variable1 = new GhostRoundedJTextField(10, this.offset+this.borderSize, this.offset+borderSize, width-(this.borderSize*2), height-(this.borderSize*2), this.ghostText);
         if(this.isContainerVisible)
         {
         this.add(variable1);
         }
      }
      if(this.isContainerVisible == false)
      {
         this.variable1.setBounds(x+this.borderSize, y+this.borderSize, width-(this.borderSize*2), height-(this.borderSize*2));
      }
      this.variable1.setBounds(this.offset+this.borderSize, this.offset+this.borderSize, width-(this.borderSize*2), height-(this.borderSize*2));
      this.reload();
   }
   public void reload()
   {
      super.reload();
      this.borderSize = 10;
      if(this.variable1 == null)
      {
         this.variable1 = new GhostRoundedJTextField(10, this.offset+this.borderSize, this.offset+borderSize, (this.borderSize*2), (this.borderSize*2), this.ghostText);
         if(this.isContainerVisible)
         {
            this.add(variable1);
         }
         else
         {
            this.variable1.setBounds(this.getX()+this.borderSize, this.getY()+this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
         }
      }
      if(this.isContainerVisible)
         {
            this.add(variable1);
         }
         else
         {
            this.variable1.setBounds(this.getX()+this.borderSize, this.getY()+this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
         }
      this.variable1.reload();
      //this.getVariableObject().setBounds(this.offset+this.borderSize, this.offset+this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
   }
   public GhostRoundedJTextField getVariableObject(){return this.variable1;}
   public void setVariableObject(GhostRoundedJTextField variableDummy){this.variable1 = variableDummy;}
   public int getArrayIndex(){return this.arrayIndex;}
   public void setArrayIndex(int arrayIndex){this.arrayIndex = arrayIndex;}
   public void mouseMoved(MouseEvent e){}
   public static void main(String args[])
   {
   
      ComponentMover cm = new ComponentMover();
      JFrame frame = new JFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      WidgetWithVariable widget1 = new WidgetWithVariable(30, 30, 400, 100, "test", true);
      
      frame.add(widget1);
      cm.registerComponent(widget1);
      widget1.setDraggable(true);
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
   
   }
}
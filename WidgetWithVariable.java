import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
public class WidgetWithVariable extends WidgetClass implements Cloneable
{
   public GhostRoundedJTextField variable1;
   private String ghostText;
   private int borderSize = 30;
   private int arrayIndex = 0;
   int defaultX;
   int defaultY;
   int defaultWidth;
   int defaultHeight;
   String listOfItems[];
   boolean isUsingDropDown = true;
   JComboBox dropDown;
   public WidgetWithVariable()
   {
      super(0);
      this.draggable = false;
      this.isContainerVisible = false;
      
      this.borderSize = 80;
   }
   public WidgetWithVariable(int x, int y, int width, int height, String ghostText, boolean isContainerVisible)
   {
      
      super(x, y, width, height, isContainerVisible);
      this.isUsingDropDown = false;
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
   public WidgetWithVariable(int x, int y, int width, int height, String ghostText, boolean isContainerVisible, String s1[])
   {
      
      super(x, y, width, height, isContainerVisible);
      this.setDropDownStringList(s1);
      this.ghostText = ghostText;
      this.borderSize = 30;
      
      
      
   }
   public void setDefaults(int x, int y, int width, int height)
   {
      this.defaultX = x;
      this.defaultY = y;
      this.defaultWidth = width;
      this.defaultHeight = height;
   }
   public void resetToDefaultSize()
   {
      this.setBounds(this.defaultX, this.defaultY, this.defaultWidth, this.defaultHeight);
   }
   public Object clone() throws
                   CloneNotSupportedException 
    { 
         WidgetWithVariable cloned = (WidgetWithVariable)super.clone();
         cloned.variable1 = new GhostRoundedJTextField(10);
         return cloned;
    }
   public String getText()
   {
      if(this.isUsingDropDown == false)
      {
         if(!variable1.getText().equals(variable1.ghostText))
         {
            return variable1.getText();
         }
         else
         {
            return "";
         }
      }
      else
      {
         return (String)this.dropDown.getSelectedItem();
      }  
   }
   public void setBounds(int x, int y, int width, int height)
   {
      super.setBounds(x, y, width, height);
      if(this.isUsingDropDown == false)
      {
         
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
         
      }
      else
      {
         if(variable1 != null)
         {
            this.remove(variable1);
         }
         if(this.isContainerVisible)
         {
            this.dropDown.setBounds(this.borderSize, this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
         }
         else
         {
            this.dropDown.setBounds(x+this.borderSize, y+this.borderSize, width-(this.borderSize*2), height-(this.borderSize*2));
         }
      }
      this.reload();
      
   }
   public void reload()
   {
      super.reload();
      this.borderSize = 10;
      if(this.isUsingDropDown == false)
      {
         
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
      }
      else
      {
         //variable1 = null;
         if(variable1 != null)
         {
            //this.remove(variable1);
         }
         if(this.dropDown != null)
         {
            /*
            this.dropDown.setBounds(this.borderSize, this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
            if(this.getComponents().length == 0)
            {
               this.add(dropDown);
            }
            */
         }
      }
      //this.getVariableObject().setBounds(this.offset+this.borderSize, this.offset+this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
   }
   public void setDropDownStringList(String s1[])
   {
      this.listOfItems = s1;
      this.dropDown = new JComboBox(this.listOfItems);
      this.isUsingDropDown = true;
      /*
      this.add(this.dropDown);
      */
      this.dropDown.setBounds(this.borderSize, this.borderSize, this.getWidth()-(this.borderSize*2), this.getHeight()-(this.borderSize*2));
      if(variable1 != null)
         {
            //this.remove(variable1);
         }
      this.reload();
      //variable1 = null;
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
      String s1[] = {"1", "2", "3", "4"};
      WidgetWithVariable widget1 = new WidgetWithVariable(30, 30, 400, 100, "test", true, s1);
      
      frame.add(widget1);
      cm.registerComponent(widget1);
      widget1.setDraggable(true);
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
   
   }
}
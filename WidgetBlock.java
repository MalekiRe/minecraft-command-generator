import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
public class WidgetBlock extends WidgetClass
{
   private ArrayList<WidgetClass> variables;
   int originalX = 0;
   int originalY = 0;
   private int level = 0;
   private boolean isContained = false;
   private ArrayList<WidgetBlock> allWidgetBlocks;
   public WidgetBlock()
   {
      super();
      variables = new ArrayList<WidgetClass>();
      this.allWidgetBlocks = new ArrayList<WidgetBlock>();
   }
   public WidgetBlock(int x, int y, int width, int height, ArrayList<WidgetBlock> allWidgetBlocks)
   {
      super(x, y, width, height, true);
      variables = new ArrayList<WidgetClass>();
      this.allWidgetBlocks = allWidgetBlocks;
      this.allWidgetBlocks.add(this);
   }
   public void addVariable(WidgetWithVariable variable)
   {
      variable.setBounds(50+(this.variables.size()*100), 0, 100, 100);
      
      
      this.variables.add(variable);
      this.add(variable.getVariableObject());
      this.reload();
      
   }
   public void switchVariable(WidgetBlock widget, int index)
   {
      widget.isContained = true;
      widget.setLevel(this.getLevel()+1);
      this.variables.set(index, widget);
      Component[] components = this.getComponents();
      for(int i1 = 0; i1 < components.length; i1++)
      {
         
               components[i1].enable(false);
            
      }
         for(int i2 = 0; i2 < variables.size(); i2++)
         {
            if(this.variables.get(i2).isContainerVisible)
            {
               this.variables.get(i2).enable(true);
            }
            else
            {
               this.variables.get(i2).getVariableObject().enable(true);
            }
         }
      
      
      this.reload();
   }
   public String getText()
   {
      String returnString = "";
      for(WidgetClass var : variables)
      {
         returnString += " "+var.getText();
      }
      return returnString;
   }
   public void reload()
   {
      super.reload();
      
      if(variables != null)
      {
         int spacer = 20;
         int cumulativeWidth = 0;
         for(int i1 = 0; i1 < variables.size(); i1++)
         {
            
            
            
            
            if(variables.get(i1).draggable == true)
            {
               variables.get(i1).setBounds(this.getX()+spacer+cumulativeWidth+i1*(variables.get(i1).getWidth()), this.getY(), variables.get(i1).getWidth(), variables.get(i1).getHeight());
            }
            else
            {
               variables.get(i1).setBounds(spacer+cumulativeWidth+i1*(variables.get(i1).getWidth()), 0, variables.get(i1).getWidth(), variables.get(i1).getHeight());
            }
            cumulativeWidth += variables.get(i1).getWidth();
            variables.get(i1).reload();
         }
      }
      this.originalX = this.getX();
      this.originalY = this.getY();
      
      
      
   }
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e)
   {
      if(this.isContained == false)
      {
         if(e.getButton() == MouseEvent.BUTTON3)
         {
         this.reload();
         }
      }
   }
   public void mouseClicked(MouseEvent e)
   {
      this.originalX = this.getX();
      this.originalY = this.getY();
      System.out.println(this.getText());
      
   }
   public void resetPosition()
   {
      this.setBounds(this.originalX, this.originalY, this.getWidth(), this.getHeight());
      this.reload();
   }
   public void mouseReleased(MouseEvent e)
   {  
      int lowestLevelWidgetContained = -1;
      int lowestWidgetIndex = -1;
      int lowestVariableIndex = -1;
      
      for(int i1 = 0; i1 < allWidgetBlocks.size(); i1++)
      {  
         this.revalidate();
         allWidgetBlocks.get(i1).revalidate();
         WidgetBlock dummy1 = allWidgetBlocks.get(i1);
         
         
         
         if(this.thisContainsThat(this.getX(), this.getY(), dummy1.getX(), dummy1.getY(), dummy1.getWidth(), dummy1.getHeight()))
         {
            
            for(int i2 = 0; i2 < dummy1.getVariableArray().size(); i2++)
            {
            
               
               if(dummy1.getVariableArray().get(i2).getContainerVisible() == false)
               {
                  int otherX = dummy1.getVariableArray().get(i2).getX()+dummy1.getX();
                  int otherY = dummy1.getVariableArray().get(i2).getY()+dummy1.getY();
                  int otherWidth = dummy1.getVariableArray().get(i2).getWidth();
                  int otherHeight = dummy1.getVariableArray().get(i2).getHeight();
                  if(this.thisContainsThat(this.getX(), this.getY(), otherX, otherY, otherWidth, otherHeight))
                  {
                     
                     if(lowestWidgetIndex != -1)
                     {
                        
                        if(dummy1.getLevel() > lowestLevelWidgetContained)
                        {
                           lowestLevelWidgetContained = dummy1.getLevel();
                        }
                        lowestWidgetIndex = i1;
                        lowestVariableIndex = i2;
                     }
                     else
                     {
                        
                        lowestWidgetIndex = i1;
                        lowestVariableIndex = i2;
                        
                     }
                     
                  }
               }
               
            }
         }
      }
      System.out.println(lowestWidgetIndex != -1);
      if(lowestWidgetIndex != -1 && lowestVariableIndex != -1)
      {
         this.allWidgetBlocks.get(lowestWidgetIndex).switchVariable(this, lowestVariableIndex);
         this.isContained = true;
      }
      else
      {
         
      }
      if(this.isContained == true)
      {
         this.resetPosition();
      }
   }
   public int getLevel()
   {
      return this.level;
   }
   public void setLevel(int level)
   {
      this.level = level;
   }
   public ArrayList<WidgetClass> getVariableArray()
   {
      return this.variables;
   }
   public boolean thisContainsThat(int thisX, int thisY, int dummyX, int dummyY, int dummyWidth, int dummyHeight)
   {
      if(thisX > dummyX)
         {
         //System.out.println("X");
            if(thisY > dummyY)
            {
               //System.out.println("X+Y");
               if(thisY < dummyY+dummyHeight)
               {
                  //System.out.println("Heigh");
                  if(thisX < dummyX+dummyWidth)
                  {
                     return true;
                  }
                  
               }
             }
            
         }
         return false;
   }  
   public static void main(String args[])
   {
   
      ComponentMover cm = new ComponentMover();
      ArrayList<WidgetBlock> allWidgetBlocks = new ArrayList<WidgetBlock>();
      JFrame frame = new JFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      WidgetBlock widget1 = new WidgetBlock(30, 60, 600, 150, allWidgetBlocks);
      WidgetBlock widget2 = new WidgetBlock(30, 200, 200, 100, allWidgetBlocks);
      //WidgetWithVariable test3 = new WidgetWithVariable(40, 40 , 100, 100, "test", false);
      WidgetWithVariable test1 = new WidgetWithVariable(30, 30, 100, 100, "test", false);
      WidgetWithVariable test2 = new WidgetWithVariable(10, 10, 10, 10, "test", false);
      //WidgetWithVariable test4 = new WidgetWithVariable(10, 10, 10, 10, "test4", false);
      
      widget2.setDraggable(true);
      widget2.addVariable(test2);
      //widget2.addVariable(test4);
      cm.registerComponent(widget1);
      cm.registerComponent(widget2);
      
      widget1.setDraggable(true);
      
      widget1.addVariable(test1);
      //widget1.addVariable(test2);
      
      frame.add(widget2);
      frame.add(widget1);
      
      frame.setVisible(true);
      //widget1.switchVariable(widget2, 0);
      frame.repaint();
      frame.revalidate();
     
   }
}
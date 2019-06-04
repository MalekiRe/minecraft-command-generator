import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.io.Serializable;
public class WidgetBlock extends WidgetClass implements Serializable
{
   public ArrayList<WidgetClass> variables;
   private int originalX = 0;
   private int originalY = 0;
   private int level = 0;
   public int layerLevel = 0;
   private int widgetVariableIndex = 0;
   private boolean isContained = false;
   private boolean isBeingDragged = false;
   public boolean mouseHeld = false;
   public WidgetBlock widgetBlockContainer;
   private WidgetBlockArray allWidgetBlocks;
   private WidgetFrame frame;
   public DragAndDropWidgetMenu panel;
   boolean usingFrame = true;
   boolean isSquished = true;
   public WidgetBlock()
   {
      super();
      variables = new ArrayList<WidgetClass>();
   }
   public WidgetBlock(int x, int y, int width, int height)
   {
      super(x, y, width, height, true);
      this.variables = new ArrayList<WidgetClass>();
      
   }
   public void setWidgetFrame(WidgetFrame frame)
   {
      this.frame = frame;
      this.usingFrame = true;
   }
   public void setPanel(DragAndDropWidgetMenu panel)
   {
      this.panel = panel;
      this.usingFrame = false;
   }
   public void setAllWidgetBlocks(WidgetBlockArray widgetBlockArray)
   {
      this.allWidgetBlocks = widgetBlockArray;
   }
   public void addVariable(String varName)
   {
      addVariable(new WidgetWithVariable(10, 10, 10, 10, varName, false));
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
      widget.setWidgetBlockContainer(this);
      GhostRoundedJTextField dummy1 = this.variables.get(index).getVariableObject();
      
      
      
      Component[] components = this.getComponents();
      for(int i1 = 0; i1 < components.length; i1++)
      {          
         if(dummy1 == components[i1])
         {
            widget.setWidgetVariableIndex(i1);
       
         }
         components[i1].enable(false); 
      }
      this.variables.set(index, widget);
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
   public Object clone() throws
                   CloneNotSupportedException 
    { 
        WidgetBlock cloned = (WidgetBlock)super.clone();
        ArrayList<WidgetClass> dummy1 = cloned.getVariableArray();
        for(int i2 = 0; i2 < this.getVariableArray().size(); i2++)
        {
            dummy1.set(i2, (WidgetClass)this.getVariableArray().get(i2).clone());
            
              
        }
        return cloned;
    }
   public void reload() //Function to reload the widgetBlock
   {
      super.reload(); //Calls the reload from the widgetClass class
      //variables.get(i1).reload();
      if(variables != null) //If there are variables
      {
         int spacer = 20;//How far apart the objects should be.
         int cumulativeWidth = 0;//Self-Explanitory
         for(int i1 = 0; i1 < variables.size(); i1++)
         {

            if(variables.get(i1).draggable == true)
            {//Spacing problem cuased by the getWidth method. Not the same for both the widbet block adn j text field.
               variables.get(i1).setBounds(this.getX()+cumulativeWidth, this.getY(), variables.get(i1).getWidth(), variables.get(i1).getHeight());
               cumulativeWidth += variables.get(i1).getWidth();
            }
            else
            {
               variables.get(i1).setBounds(spacer+cumulativeWidth, 0, variables.get(i1).getWidth(), variables.get(i1).getHeight());
            }
            cumulativeWidth += variables.get(i1).getWidth();
            variables.get(i1).reload();
         }
      }
      this.originalX = this.getX();//Setting orignal x and y so if we move the object and drage it back into place it will go where it should.
      this.originalY = this.getY();
      
      
      
   }
   public void changeLayerOfAll()
   {
      if(this.frame != null)
      {
         this.frame.getLayeredPane().moveToFront(this);
         for(int i1 = 0; i1 < this.getVariableArray().size(); i1++)
         {
            
            
            
            if(this.getVariableArray().get(i1) instanceof WidgetBlock)
            {
               
               ((WidgetBlock)this.getVariableArray().get(i1)).changeLayerOfAll();
               
            }
               
            
         }
      }
   }
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e)
   {
      this.setIsBeingDragged(true);
      this.changeLayerOfAll();
      
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
      System.out.println("oioioi");
      if(this.panel != null)
      {
         System.out.println("fdsfs");
         this.panel.duplicateWidget(this, e.getX(), e.getY());
      }
      if(this.allWidgetBlocks != null)
      {
         if(e.getButton() == MouseEvent.BUTTON1) {
            this.allWidgetBlocks.showPopupMenu(this, e);
         }
      }
   }
   public void resetPosition()
   {
      this.setBounds(this.originalX, this.originalY, this.getWidth(), this.getHeight());
      this.reload();
   }
   public void mouseReleased(MouseEvent e)
   {
      this.mouseHeld = false;
      int lowestLevelWidgetContained = -1;
      int lowestWidgetIndex = -1;
      int lowestVariableIndex = -1;
      if(allWidgetBlocks != null)
      {
         for(int i1 = 0; i1 < this.allWidgetBlocks.size(); i1++)
         {  
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
      
      if(lowestWidgetIndex != -1 && lowestVariableIndex != -1)
      {
         if(this.isContained == false)
         {
            this.allWidgetBlocks.get(lowestWidgetIndex).switchVariable(this, lowestVariableIndex);
            this.isContained = true;
         }
      }
      else
      {
         if(this.isContained == true)
         {
            Component dummy2 = this.getWidgetBlockContainer().getComponents()[this.getWidgetVariableIndex()];
            dummy2.enable(true);
            this.isContained = false;
            
            System.out.println(this.getWidgetVariableIndex());
            
            if(dummy2 instanceof GhostRoundedJTextField)
            {
               
                  GhostRoundedJTextField dummy = (GhostRoundedJTextField)dummy2;
                  this.getWidgetBlockContainer().variables.set(this.getWidgetVariableIndex(), dummy.getWidgetWithVariable());
                  
                  
            }
         }
      }
      }
      if(this.isContained == true)
      {
         this.resetPosition();
      }
      if(this.getWidgetBlockContainer() != null)
      {
         this.getWidgetBlockContainer().reload();
      }
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
   public int getLevel()
   {
      return this.level;
   }
   public void setLevel(int level)
   {
      this.level = level;
   }
   public void setContained(boolean isContained)
   {
      this.isContained = isContained;
   }
   public void setWidgetBlockContainer(WidgetBlock widgetBlockDummy)
   {
      this.widgetBlockContainer = widgetBlockDummy;
   }
   public WidgetBlock getWidgetBlockContainer()
   {
      return this.widgetBlockContainer;
   }
   public void setWidgetVariableIndex(int index)
   {
      this.widgetVariableIndex = index;
   }
   public int getWidgetVariableIndex()
   {
      return this.widgetVariableIndex;
   }
   public ArrayList<WidgetClass> getVariableArray()
   {
      return this.variables;
   }
   public boolean getIsBeingDragged()
   {
      return this.isBeingDragged;
   }
   public void setIsBeingDragged(boolean isBeingDragged)
   {
      this.isBeingDragged = isBeingDragged;
   }
   public ArrayList<WidgetClass> getAllVariablesInArray()
   {
      ArrayList<WidgetClass> allVariablesInArray = new ArrayList<WidgetClass>();
      
      for(int i2 = 0; i2 < this.getVariableArray().size(); i2++)
      {
         if(this.getVariableArray().get(i2) instanceof WidgetBlock)
         {
            allVariablesInArray.add(this.getVariableArray().get(i2));
            allVariablesInArray.addAll(((WidgetBlock)this.getVariableArray().get(i2)).getAllVariablesInArray());
         }
      }
      
      return allVariablesInArray;
   }
   public static void main(String args[])
   {
   
      
      WidgetBlockArray allWidgetBlocks = new WidgetBlockArray();
      WidgetFrame frame = new WidgetFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      WidgetBlock widget1 = new WidgetBlock(30, 60, 700, 150);
      WidgetBlock widget2 = new WidgetBlock(30, 200, 300, 100);
      WidgetBlock widget3 = new WidgetBlock(30, 50, 200, 100);
      allWidgetBlocks.add(widget1);
      allWidgetBlocks.add(widget2);
      allWidgetBlocks.add(widget3);
      widget1.setWidgetFrame(frame);
      widget2.setWidgetFrame(frame);
      widget3.setWidgetFrame(frame);
      WidgetWithVariable test3 = new WidgetWithVariable(40, 40 , 100, 100, "test", false);
      WidgetWithVariable test1 = new WidgetWithVariable(30, 30, 400, 100, "test", false);
      WidgetWithVariable test2 = new WidgetWithVariable(10, 10, 10, 10, "test", false);
      WidgetWithVariable test4 = new WidgetWithVariable(10, 10, 10, 10, "test4", false);
      WidgetWithVariable test5 = new WidgetWithVariable(10, 10, 10, 10, "Test 5", false);
      
      widget2.setDraggable(true);
      widget2.addVariable(test3);
      widget2.addVariable(test4);
      widget1.setDraggable(true);
      widget3.setDraggable(true);
      widget1.addVariable(test1);
      widget1.addVariable(test2);
      widget3.addVariable(test5);
      //widget1.switchVariable(widget2, 0);
      frame.getLayeredPane().add(widget3, new Integer(0), -3);
      //frame.getFrame().add(widget3);
      frame.getLayeredPane().add(widget1, new Integer(0), -2);
      frame.getLayeredPane().add(widget2, new Integer(0), -4);
      //frame.getFrame().add(widget2);
      
      
      //frame.getFrame().add(widget1);
      frame.setVisible(true);
      
      frame.repaint();
      frame.revalidate();
      
      
   }
}
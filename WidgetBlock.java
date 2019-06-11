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
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class WidgetBlock extends WidgetClass implements Serializable
{
   public ArrayList<WidgetClass> variables;
   private int originalX = 0;
   private int originalY = 0;
   public int defaultHeight = 0;
   public boolean containsAnotherWidgetBlock = false;
   private int level = 0;
   public int layerLevel = 0;
   private int widgetVariableIndex = 0;
   public boolean isContained = false;
   private boolean isBeingDragged = false;
   public boolean mouseHeld = false;
   public WidgetBlock widgetBlockContainer;
   public WidgetBlockArray allWidgetBlocks;
   public WidgetFrame frame;
   public DragAndDropWidgetMenu panel;
   boolean usingFrame = true;
   boolean isSquished = true;
   int arrayPosition = 0;
   public WidgetWithVariable replacedVariable;
   
   public WidgetBlock()
   {
      super(0);
      variables = new ArrayList<WidgetClass>();
   }
   public WidgetBlock(int x, int y, int width, int height)
   {
      super(x, y, width, height, true);
      this.variables = new ArrayList<WidgetClass>();
      this.defaultHeight = height;
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
      addVariable(new WidgetWithVariable(10, 10, this.getWidth(), this.getHeight(), varName, false));
   }
   public void addVariable(String varName, String[] s1)
   {
      addVariable(new WidgetWithVariable(10, 10, this.getHeight(), this.getHeight(), varName, false, s1));
      
   }
   
   
   public void setColor(Color color)
   {
      this.container.color = color;
   }
   public void addVariable(WidgetWithVariable variable)
   {
      variable.setBounds(10+(this.variables.size()*this.getWidth()), 0, this.getHeight(), this.getHeight());
      variable.reload();
      variable.setDefaults(variable.getX(), variable.getY(), variable.getWidth(), variable.getHeight());
      
      if(variable.isUsingDropDown == false)
      {
         this.add(variable.getVariableObject());
      }
      else
      {
         this.add(variable.dropDown);
      }
      this.variables.add(variable);
      this.reload();
      
   }
   public void playSound(String fileName)
   {
      try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource(fileName);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
      
   }
   public void switchVariable(WidgetBlock widget, int index)
   {
      this.playSound("Click On.wav");
      this.containsAnotherWidgetBlock = true;
      widget.isContained = true;
      widget.setLevel(this.getLevel()+1);
      widget.setWidgetBlockContainer(this);
      GhostRoundedJTextField dummy1 = this.variables.get(index).getVariableObject();
      //WidgetWithVariable dummy2 = (WidgetWithVariable)this.variables.get(index);
      //this.variables.get(index).setBounds(this.variables.get(index).getX(), this.variables.get(index).getY(), widget.getWidth(), widget.getHeight());
      widget.replacedVariable = (WidgetWithVariable)this.variables.get(index);
      this.setHeight(widget.getHeight()+30);
      this.reload();
      this.containsAnotherWidgetBlock = true;
      Component[] components = this.getComponents();
      for(int i1 = 0; i1 < components.length; i1++)
      {          
         if(dummy1 == components[i1])
         {
            widget.setWidgetVariableIndex(i1);
            
         }
         if(components[i1] instanceof JComboBox)
         {
               components[i1].enable(true);
            
         }
         else
         {
            components[i1].enable(false);
         }
          
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
      widget.replacedVariable.reload();      
      widget.changeLayerOfAll();
      this.reload();
      widget.reload();
      this.repaint();
      this.revalidate();
      this.frame.repaint();
      this.frame.revalidate();
      //dummy2.setBounds(dummy2.getX(), widget.getY(), widget.getWidth(), widget.getHeight());
   }
   public String getText()
   {
      String returnString = "";
      for(WidgetClass var : variables)
      {
         returnString += var.getText();
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
         boolean doesThisHaveAWidgetBlock = false;
         int spacer = 0;//How far apart the objects should be.
         int cumulativeWidth = this.getLabelWidth();//Self-Explanitory
         for(int i1 = 0; i1 < variables.size(); i1++)
         {

            if(variables.get(i1).draggable == true)
            {
               variables.get(i1).setBounds(this.getX()+cumulativeWidth, this.getY()+(this.getHeight()/2)-(variables.get(i1).getHeight()/2), variables.get(i1).getWidth(), variables.get(i1).getHeight());
               ((WidgetBlock)variables.get(i1)).replacedVariable.setBounds(-this.getX()+variables.get(i1).getX(), -this.getY()+variables.get(i1).getY(), variables.get(i1).getWidth(), variables.get(i1).getHeight());
               cumulativeWidth += variables.get(i1).getWidth();
               doesThisHaveAWidgetBlock = true;
            }
            else
            {
               
               variables.get(i1).setBounds(spacer+cumulativeWidth, -((this.defaultHeight-this.getHeight())/2), variables.get(i1).getWidth(), variables.get(i1).getHeight());
               //this.getComponents()[i1].setBounds(spacer+cumulativeWidth, ((this.defaultHeight-this.getHeight())), variables.get(i1).getWidth(), variables.get(i1).getHeight());

               cumulativeWidth += variables.get(i1).getWidth();
            }
            
            variables.get(i1).reload();
            
         }
         this.setWidth(cumulativeWidth);
         super.reload();
         //this.widgetContainer.setWidth(cumulativeWidth*4);
         if(this.containsAnotherWidgetBlock == false)
         {
            this.setHeight(this.defaultHeight);
         }
         
         this.containsAnotherWidgetBlock = doesThisHaveAWidgetBlock;
         
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
   public ArrayList<WidgetBlock> getAllPossibleWidgets()
   {
      ArrayList<WidgetBlock> allPossibleWidgets = new ArrayList<WidgetBlock>();
      for(int i1 = 0; i1 < this.getVariableArray().size(); i1++)
      {
         
         
         
         if(this.getVariableArray().get(i1) instanceof WidgetBlock)
         {
            allPossibleWidgets.add((WidgetBlock)this.getVariableArray().get(i1));
            allPossibleWidgets.addAll(((WidgetBlock)this.getVariableArray().get(i1)).getAllPossibleWidgets());
            
         }
            
         
      }
      return allPossibleWidgets;

   }
   public void mousePressed(MouseEvent e)
   {
      if(e.getButton() == MouseEvent.BUTTON3 && this.allWidgetBlocks != null) {
            this.allWidgetBlocks.showPopupMenu(this, e.getX(), e.getY());
            
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
      
      if(this.panel != null)
      {
         if(Math.random() >= 0.5)
         {
            this.playSound("Pop1.wav");
         }
         else
         {
            this.playSound("Pop2.wav");  
         }
         this.panel.duplicateWidget(this, e.getX()+5, e.getY()+5);
      }
      if(this.allWidgetBlocks != null)
      {
         if(e.getButton() == MouseEvent.BUTTON3) {
            this.allWidgetBlocks.showPopupMenu(this, e.getX(), e.getY());
            
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
                  boolean hasWidgetBlock = false;
                  GhostRoundedJTextField dummy = (GhostRoundedJTextField)dummy2;
                  dummy.widgetWithVariable.resetToDefaultSize();
                  this.getWidgetBlockContainer().variables.set(this.getWidgetVariableIndex(), dummy.getWidgetWithVariable());
                  for(int i1 = 0; i1 < this.getWidgetBlockContainer().variables.size(); i1++)
                  {
                     if(this.getWidgetBlockContainer().variables.get(i1) instanceof WidgetBlock)
                     {
                        hasWidgetBlock = true;  
                     }
                  }
                  this.getWidgetBlockContainer().containsAnotherWidgetBlock = hasWidgetBlock;
                  this.getWidgetBlockContainer().reload();
                  this.playSound("Click Off.wav");
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
      String s1[] = {"1", "2"};
      WidgetWithVariable test3 = new WidgetWithVariable(40, 40 , 100, 100, "test", false);
      WidgetWithVariable test1 = new WidgetWithVariable(30, 30, 400, 100, "test", false);
      WidgetWithVariable test2 = new WidgetWithVariable(10, 10, 10, 10, "test", false);
      WidgetWithVariable test4 = new WidgetWithVariable(10, 10, 10, 10, "test4", false);
      WidgetWithVariable test5 = new WidgetWithVariable(10, 10, 10, 10, "Test 5", false);
      
      widget2.setDraggable(true);
      widget2.addVariable(test3);
      widget2.addVariable(test4);
      widget1.setDraggable(true);
      widget1.addVariable("bla bla", s1);
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
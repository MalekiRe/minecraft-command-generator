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
   private boolean isContained = false;
   public WidgetBlock()
   {
      super();
      variables = new ArrayList<WidgetClass>();
   }
   public WidgetBlock(int x, int y, int width, int height)
   {
      super(x, y, width, height, true);
      variables = new ArrayList<WidgetClass>();
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
      this.variables.set(index, widget);
      this.removeAll();
      for(int i1 = 0; i1 < variables.size(); i1++)
      {
         if(this.variables.get(i1).isContainerVisible)
         {
            this.add(this.variables.get(i1));
         }
         else
         {
            this.add(this.variables.get(i1).getVariableObject());
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
      if(this.isContained == true)
      {
         this.resetPosition();
      }
   }
   public static void main(String args[])
   {
   
      ComponentMover cm = new ComponentMover();
      ArrayList<WidgetBlock> allWidgetBlocks = new ArrayList<WidgetBlock>();
      JFrame frame = new JFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      WidgetBlock widget1 = new WidgetBlock(30, 60, 700, 150);
      WidgetBlock widget2 = new WidgetBlock(30, 200, 300, 100);
      WidgetWithVariable test3 = new WidgetWithVariable(40, 40 , 100, 100, "test", false);
      WidgetWithVariable test1 = new WidgetWithVariable(30, 30, 400, 100, "test", false);
      WidgetWithVariable test2 = new WidgetWithVariable(10, 10, 10, 10, "test", false);
      WidgetWithVariable test4 = new WidgetWithVariable(10, 10, 10, 10, "test4", false);
      
      widget2.setDraggable(true);
      widget2.addVariable(test3);
      widget2.addVariable(test4);
      cm.registerComponent(widget1);
      cm.registerComponent(widget2);
      
      widget1.setDraggable(true);
      
      widget1.addVariable(test1);
      widget1.addVariable(test2);
      widget1.switchVariable(widget2, 0);
      frame.add(widget2);
      frame.add(widget1);
      
      frame.setVisible(true);
      
      frame.repaint();
      frame.revalidate();
      
   }
}
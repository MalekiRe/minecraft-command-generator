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
   private WidgetBlockArray widgetBlockArray = new WidgetBlockArray();
   public int numberOfBlocksAdded = 0;
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
   public void addWidgetBlockArray(WidgetBlockArray array)
   {
      this.widgetBlockArray = array;
      this.widgetBlockArray.frame = this;
      this.widgetBlockArray.usingFrame = true;
   }
   public void changeLayerOfWidgetBlock()
   {
      
      for(Component c : this.getComponents())
      {
         if(c instanceof WidgetBlock)
         {
            if(((WidgetBlock)c).getIsBeingDragged())
            {
               this.getLayeredPane().moveToFront((WidgetBlock)(c));
               System.out.println("asdfsdfsf");
            }
         }
      }
   }
   public void addWidgetBlocks(WidgetBlockArray allWidgetBlocks)
   {
      widgetBlockArray = allWidgetBlocks;
      Integer numberZero = new Integer(0);
      for(int i1 = 0; i1 < allWidgetBlocks.size(); i1++)
      {
         allWidgetBlocks.get(i1).setWidgetFrame(this);
         this.getLayeredPane().add(allWidgetBlocks.get(i1), numberZero, (i1*-1));
         
         allWidgetBlocks.get(i1).layerLevel = (i1*-1);
      }
      allWidgetBlocks.frame = this;
   }
   public void addWidgetBlock(WidgetBlock block)
   {
      numberOfBlocksAdded++;
      Integer numberZero = new Integer(0);
      block.setWidgetFrame(this);
      this.getLayeredPane().add(block, numberZero, numberOfBlocksAdded*-1);
      
      
   }
   public void addDragAndDropWidgetMenu(DragAndDropWidgetMenu menu)
   {
      this.add(menu);
      menu.frame = this;
   }
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){} 
   public void mouseReleased(MouseEvent e){}
   public void mousePressed(MouseEvent e){}
   public void mouseClicked(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e){}
   public static void main(String[] args)
   {
      WidgetFrame frame = new WidgetFrame();
      frame.setSize(100, 100);
      frame.setVisible(true);
   }
   
}
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
public class WidgetBlockArray
{
   private ArrayList<WidgetBlock> arrayList = new ArrayList<WidgetBlock>();
   public UnoptimizedDeepCopy copyer;
   ComponentMover cm = new ComponentMover();
   public WidgetFrame frame;
   public DragAndDropWidgetMenu panel;
   boolean usingFrame = false;
   PopupMenu popupMenu = new PopupMenu();
   public boolean add(WidgetBlock block)
   {
      block.setAllWidgetBlocks(this);
      this.cm.registerComponent(block);
      for(int i2 = 0; i2 < block.getAllVariablesInArray().size(); i2++)
      {
         if(block.getAllVariablesInArray().get(i2) instanceof WidgetBlock)
         {
            
            this.add((WidgetBlock)block.getAllVariablesInArray().get(i2));
         }
         else if(block.getAllVariablesInArray().get(i2) instanceof CommandWidget)
         {
            
            this.add((CommandWidget)block.getAllVariablesInArray().get(i2));
         }
      }
      if(this.usingFrame)
      {
         block.setWidgetFrame(this.frame);
      }
      else
      {
         block.setPanel(this.panel);
      }
      return this.arrayList.add(block);
      
   }
   public void addCopyer(UnoptimizedDeepCopy copyer)
   {
      this.copyer = copyer;
   }
   public void addCommandWidgetClone(CommandWidget widget, int x, int y)
   {
      if(widget != null && copyer.copy(widget) != null)
      {
         Object obj = copyer.copy(widget);
         
         if(obj instanceof CommandWidget)
         {
            CommandWidget commandWidget = (CommandWidget)obj;
            commandWidget.setX(x);
            commandWidget.setY(y);
            this.add(commandWidget);
            if(usingFrame == true)
            {
               frame.addWidgetBlock(commandWidget);
            }
            else
            {
               panel.add(commandWidget);
            }
            commandWidget.setBounds(x, y, commandWidget.getWidth(), commandWidget.getHeight());
            
         }
      }
      
   }
   public void showPopupMenu(WidgetBlock widget, MouseEvent e)
   {
      this.frame.mousePressed(e);
   }
   public void addPopupMenu(PopupMenu popupMenu)
   {
      this.popupMenu = popupMenu;
      this.popupMenu.allWidgetBlocks = this;
   }
   public int size()
   {
      return this.arrayList.size();
   }
   public WidgetBlock get(int i1)
   {
      return this.arrayList.get(i1);
   }
}
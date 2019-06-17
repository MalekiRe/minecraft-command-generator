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
   JScrollPane scrollFrame;
   JLayeredPane pane = new JLayeredPane();
   public WidgetFrame()
   {
      super();
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
      scrollFrame = new JScrollPane(this.pane);
      pane.setAutoscrolls(true);
      pane.setPreferredSize(new Dimension(800,800));
      scrollFrame.setPreferredSize(new Dimension( 10000,900));
      this.getContentPane().add(scrollFrame);
      this.pane.setLayout(null);
   }
   public WidgetFrame(String s1)
   {
      super(s1);
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
      scrollFrame = new JScrollPane(this.pane);
      pane.setAutoscrolls(true);
      pane.setPreferredSize(new Dimension(10000,800));
      scrollFrame.setPreferredSize(new Dimension( 10000,900));
      this.getContentPane().add(scrollFrame);
      this.pane.setLayout(null);
      
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
               this.pane.moveToFront((WidgetBlock)(c));
               //System.out.println("asdfsdfsf");
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
         this.pane.add(allWidgetBlocks.get(i1), numberZero, (i1*-1));
         
         allWidgetBlocks.get(i1).layerLevel = (i1*-1);
      }
      allWidgetBlocks.frame = this;
   }
   public void addWidgetBlock(WidgetBlock block)
   {
      numberOfBlocksAdded++;
      Integer numberZero = new Integer(0);
      block.setWidgetFrame(this);
      this.pane.add(block, numberZero, numberOfBlocksAdded*-1);
      
      
   }
   public void addDragAndDropWidgetMenu(DragAndDropWidgetMenu menu)
   {
      this.pane.add(menu, new Integer(0), -1);
      menu.frame = this;
   }
   public JLayeredPane getLayeredPane()
   {
      return this.pane;
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
      WidgetFrame frame = new WidgetFrame("sdfsd");
      frame.setSize(100, 100);
      frame.setVisible(true);
      frame.setLayout(null);
      WidgetBlockArray allWidgetBlocks = new WidgetBlockArray();
      WidgetBlock widget1 = new WidgetBlock(30, 60, 700, 150);
      allWidgetBlocks.add(widget1);
      widget1.setWidgetFrame(frame);
   }
   
}
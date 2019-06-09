import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class DragAndDropWidgetMenu extends JPanel implements MouseInputListener
{
   WidgetBlockArray allWidgetBlocks;
   UnoptimizedDeepCopy myCopyer = new UnoptimizedDeepCopy();
   ArrayList<WidgetBlock> widgetsOnPanel = new ArrayList<WidgetBlock>();
   ArrayList<WidgetBlock> widgetList = new ArrayList<WidgetBlock>();
   WidgetFrame frame;
   Color color;
   Color borderColor = new Color(40, 200, 40);
   int borderSize = 12;
   int widthSpacer = 10;
   int heightSpacer = 10;
   int width;
   int height;
   public DragAndDropWidgetMenu(int width, int height, Color color, WidgetBlockArray allWidgetBlocks)
   {
      this.setLayout(null);
      this.setBounds(0, 0, width, height);
      this.color = color;
      this.width = width;
      this.height = height;
      this.borderColor = getDarkerColor(this.color, 30);
      this.allWidgetBlocks = allWidgetBlocks;
      enableInputMethods(true);   
      addMouseListener(this);
      addMouseMotionListener(this);
      
      
     
      
      
      
   }
   public Color getDarkerColor(Color color, int darken)
   {
      int red = color.getRed();
      int green = color.getGreen();
      int blue = color.getBlue();
      if(red >= darken) {red -= darken;}
      else {red = 0;}
      if(green >= darken) {green -= darken;}
      else {green = 0;}
      if(blue >= darken) {blue -= darken;}
      else {blue = 0;}
      return new Color(red, green, blue);
   }
   
   @Override
   public void paintComponent(Graphics g)
   {  
      
      g.setColor(this.borderColor);
      g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 20, 20);
      g.setColor(this.color);
      g.fillRect(borderSize/2, borderSize/2, this.getWidth()-borderSize, this.getHeight()-borderSize);
      int cumulativeWidth = widthSpacer;
      int cumulativeHeight = heightSpacer;
      for(int i1 = 0; i1 < this.widgetsOnPanel.size(); i1++)
      {
         //System.out.println(cumulativeWidth);
         //System.out.println(this.width);
         //System.out.println(cumulativeWidth+this.widgetsOnPanel.get(i1).getWidth());
         if(cumulativeWidth+this.widgetsOnPanel.get(i1).getWidth() <= this.width)
         {  
            this.widgetsOnPanel.get(i1).setX(cumulativeWidth+widthSpacer);
            this.widgetsOnPanel.get(i1).setBounds(cumulativeWidth, cumulativeHeight, this.widgetsOnPanel.get(i1).getWidth(), this.widgetsOnPanel.get(i1).getHeight());
            cumulativeWidth += this.widgetsOnPanel.get(i1).getWidth()+widthSpacer;
         }
         else
         {  ////IMPORTANT CHANGE THIS i1-1 Later so it chatches when it is negative.
           
            cumulativeHeight += this.widgetsOnPanel.get(i1-1).getHeight()+heightSpacer;
            cumulativeWidth = widthSpacer;
            this.widgetsOnPanel.get(i1).setX(cumulativeWidth);
            
            this.widgetsOnPanel.get(i1).setY(cumulativeHeight);
            this.widgetsOnPanel.get(i1).setBounds(cumulativeWidth, cumulativeHeight, this.widgetsOnPanel.get(i1).getWidth(), this.widgetsOnPanel.get(i1).getHeight());
            cumulativeWidth += this.widgetsOnPanel.get(i1).getWidth()+widthSpacer;
         }
         
      }
      
      
   }
   public void addCommandWidget(CommandWidget widget)
   {
      CommandWidget widget2 = (CommandWidget)myCopyer.copy(widget);
      this.add(widget2);
      this.widgetsOnPanel.add(widget2);
      widget2.setPanel(this);   
      widget2.panel = this; 
      this.widgetList.add(widget);  
   }
   public void duplicateWidget(WidgetBlock widget, int x, int y)
   {
      
      for(int i1 = 0; i1 < this.widgetsOnPanel.size(); i1++)
      {
         
         if(((CommandWidget)this.widgetsOnPanel.get(i1)).header.equals(((CommandWidget)widget).header))
         {
            System.out.println("gjfj");
            this.allWidgetBlocks.addCommandWidgetClone((CommandWidget)this.widgetList.get(i1), x, y);
            
         }
         
      }
      
   }
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){} 
   public void mouseReleased(MouseEvent e){}
   public void mousePressed(MouseEvent e)
   {
         
   }
   public void mouseClicked(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}
   public void mouseDragged(MouseEvent e){}
   public static void main(String args[])
   {  
      UnoptimizedDeepCopy myCopyer = new UnoptimizedDeepCopy();
      WidgetBlockArray allWidgetBlocks = new WidgetBlockArray();
      PopupMenu popupMenu = new PopupMenu();
      allWidgetBlocks.addPopupMenu(popupMenu);
      FileParser fileParser = new FileParser();
      ArrayList<CommandWidget> commandWidgets = fileParser.getFileParse("commandGeneratorFile");
      allWidgetBlocks.addCopyer(myCopyer);
      WidgetFrame frame = new WidgetFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWidgetBlockArray(allWidgetBlocks);
      DragAndDropWidgetHolder myWidgetPane = new DragAndDropWidgetHolder(600, 400, Color.GREEN, allWidgetBlocks);
      
      //CommandWidget setBlockCommand = new CommandWidget("/setblock", new VariableTemplate("Block", ""), new VariableTemplate("Cords", ""));
      for(int i1 = 0; i1 < commandWidgets.size(); i1++)
      {
         myWidgetPane.getPane().addCommandWidget(commandWidgets.get(i1));
      }
      myWidgetPane.repaint();
      frame.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {showPopup(e);}
      @Override
      public void mouseReleased(MouseEvent e) {showPopup(e);}
      private void showPopup(MouseEvent e) {if(e.isPopupTrigger()) {
      popupMenu.show(e.getComponent(),e.getX(), e.getY());
      popupMenu.x = e.getX();
      popupMenu.y = e.getY();
      popupMenu.numberOfPopupsMade = 0;
      }}});
      frame.add(popupMenu);
      frame.add(myWidgetPane);
      
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
      
      }
}

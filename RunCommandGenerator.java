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
public class RunCommandGenerator
{
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
      DragAndDropWidgetHolder myWidgetPane = new DragAndDropWidgetHolder(600, 400, new Color(50, 100, 25), allWidgetBlocks);
      
      //CommandWidget setBlockCommand = new CommandWidget("/setblock", new VariableTemplate("Block", ""), new VariableTemplate("Cords", ""));
      for(int i1 = 0; i1 < commandWidgets.size(); i1++)
      {
         commandWidgets.get(i1).reload();
         myWidgetPane.getPane().addCommandWidget(commandWidgets.get(i1));
      }
      try{
      CompilationWidget compilationWidget = new CompilationWidget();
      compilationWidget.allWidgetBlocks = allWidgetBlocks;
      frame.add(compilationWidget);
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
      frame.getLayeredPane().add(myWidgetPane, new Integer(0), -1);
      
      
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

   }
}
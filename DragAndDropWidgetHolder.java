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
public class DragAndDropWidgetHolder extends JPanel
{
   
   DragAndDropWidgetMenu dragAndDropWidgetMenu;
   int fullWidth;
   int fullHeight;
   public DragAndDropWidgetHolder(int width, int height, Color color, WidgetBlockArray allWidgetBlocks)
   {
      this.setBounds(0, 0, width, height);
      dragAndDropWidgetMenu = new DragAndDropWidgetMenu(width, height, color, allWidgetBlocks);
      fullWidth = width;
      fullHeight = 10000;
      
      dragAndDropWidgetMenu.setPreferredSize(new Dimension( fullWidth,fullHeight));
      JScrollPane scrollFrame = new JScrollPane(dragAndDropWidgetMenu);
      dragAndDropWidgetMenu.setAutoscrolls(true);
      scrollFrame.setPreferredSize(new Dimension( width,height));
      this.add(scrollFrame);
      
      
      
   }
   public DragAndDropWidgetMenu getPane()
   {
      return this.dragAndDropWidgetMenu;
   }
}
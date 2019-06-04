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
      allWidgetBlocks.addCopyer(myCopyer);
      WidgetFrame frame = new WidgetFrame("Test Frame");
      frame.setSize(800, 800);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWidgetBlockArray(allWidgetBlocks);
      CommandWidget setBlockCommand = new CommandWidget("/setblock", new VariableTemplate("Block", ""), new VariableTemplate("Cords", ""));
      
      allWidgetBlocks.addCommandWidgetClone(setBlockCommand, 20, 40);
      
      
      allWidgetBlocks.addCommandWidgetClone(setBlockCommand, 100, 50);
      
      
      frame.setVisible(true);
      frame.repaint();
      frame.revalidate();
   }
}
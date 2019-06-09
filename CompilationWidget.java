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
import java.io.*;
public class CompilationWidget extends JButton implements ActionListener
{
   
   WidgetBlockArray allWidgetBlocks = new WidgetBlockArray();
   public CompilationWidget() throws IOException
   {
      this.setSize(300, 300);
      this.setBounds(0, 800-300, 300, 300);
      this.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e) 
       {
          
          try{
           compileCommands();
          }
          catch(Exception ea)
          {
            ea.printStackTrace();
          }
      }
   public void compileCommands()
      throws IOException {
      try{
       String str = "Hello";
       BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedFunction.mcfunction"));
       for(int i1 = 0; i1 < allWidgetBlocks.size(); i1++)
       {
         if(allWidgetBlocks.get(i1).isContained == false)
         {
            writer.write(allWidgetBlocks.get(i1).getText()+"\n");
         }
       }
       writer.close();
       }
       catch(Exception e)
       {
       
       }
   }
}
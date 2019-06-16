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
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
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
   public void compileCommands()
      throws IOException {
      try{
         this.playSound("Bell.wav");
       String str = "Hello";
       BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedFunction.mcfunction"));
       for(int i1 = 0; i1 < allWidgetBlocks.size(); i1++)
       {
         if(allWidgetBlocks.get(i1).isContained == false)
         {
            int i2 = -1;
            String outputText = allWidgetBlocks.get(i1).getText();
            i2 = allWidgetBlocks.get(i1).getText().indexOf("\\n");
            if(i2 != -1)
            {
               while(i2 != -1)
               {
                  
                  writer.write(outputText.substring(0, i2)+"\n");
                  outputText = outputText.substring(i2+2, outputText.length());
                  i2 = outputText.indexOf("\\n");
                  
               }
               writer.write(outputText);
               
            }
            else
            {
               writer.write(allWidgetBlocks.get(i1).getText()+"\n");
            }
         }
       }
       writer.close();
       }
       catch(Exception e)
       {
       
       }
   }
}
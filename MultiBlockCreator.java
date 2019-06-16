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
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class MultiBlockCreator extends JPanel
{

   public ArrayList<ExtraTextField> dropDowns = new ArrayList<ExtraTextField>();
   public MultiBlockCreator()
   {
      
   }
   public MultiBlockCreator(int x, int z)
   {
      
      this.setAmount(x, z);
      this.reload();
   }
   public void setAmount(int x, int z)
   {
      //JComboBox dummy = new JComboBox();
      GridLayout layout = new GridLayout(x, z);
      this.setLayout(layout);
      for(int i2 = 0; i2 < x; i2++)
      {
         for(int i3 = 0; i3 < z; i3++)
         {
            ExtraTextField field = new ExtraTextField();
            field.defaultx = i2;
            field.defaultz = i3;
            dropDowns.add(field);
            //dropDowns.get(dropDowns.size()-1).setText("null");
            //dropDowns.get(dropDowns.size()-1).x = i2;
            //dropDowns.get(dropDowns.size()-1).z = i3;
         }
      }
   }
   public void reload()
   {
      this.removeAll();
      for(int i2 = 0; i2 < dropDowns.size(); i2++)
      {
         this.add(dropDowns.get(i2));
      }
   }
   public static void main(String args[])
   {
      JFrame frame = new JFrame("hello");
      frame.setSize(800,800);
      FinalFullMultiBlockCreator creator = new FinalFullMultiBlockCreator();
      frame.add(creator);
      //frame.pack();
      frame.setVisible(true);
   }
}
class FinalFullMultiBlockCreator extends JPanel implements ActionListener
{  
   JButton startMultiBlock = new JButton("Start MultiBlock");
   JTextField nameOfFile = new JTextField("MultiBlockFunction.mcfunction");
   JTextField x = new JTextField("3");
   JTextField y = new JTextField("3");
   JTextField z = new JTextField("3");
   GroupLayout layout = new GroupLayout(this);
   JPanel temp = new JPanel();
   public FinalFullMultiBlockCreator()
   {
      //layout.linkSize(SwingConstants.HORIZONTAL, x, y, z);
      layout.setHorizontalGroup(layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
      .addComponent(startMultiBlock)
      .addComponent(x)
      .addComponent(y)
      .addComponent(z)
      .addComponent(nameOfFile)
      .addComponent(temp)
      )
    );
    
    layout.setVerticalGroup(layout.createSequentialGroup()
    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(startMultiBlock)
        )
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(x)
      )
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(y)
      )
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(z)
      )
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(nameOfFile)
      )
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(temp)
      )
      
   );
      this.setLayout(layout);
      //this.add(x);
      //this.add(y);
      //this.add(z);
      //this.add(startMultiBlock);
      startMultiBlock.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e) 
   {
      String action = e.getActionCommand();
      if(action.equals("Start MultiBlock"))
      {
         FullMultiBlockCreator creator1 = new FullMultiBlockCreator(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), Integer.parseInt(z.getText()));
         creator1.fileName = nameOfFile.getText();
         layout.replace(temp, creator1);
         temp = creator1;
         this.repaint();
         this.revalidate();
         
      }
   }
}
class FullMultiBlockCreator extends JPanel implements ActionListener
{
   ArrayList<MultiBlockCreator> layers = new ArrayList<MultiBlockCreator>();
   String fileName = "";
   JButton up = new JButton("Up");
   JButton down = new JButton("Down");
   JButton getFunction = new JButton("Get Function");
   JComboBox typeOfFunction = new JComboBox();
   JTextField displayNumber = new JTextField("0");
   JTextField xStartingPosition = new JTextField("0");
   JTextField yStartingPosition = new JTextField("0");
   JTextField zStartingPosition = new JTextField("0");
   JTextField entity = new JTextField("at @e");
   JPanel holder = new JPanel();
   CardLayout layout = new CardLayout();
   GroupLayout thisLayout = new GroupLayout(this);
   int position = 1;
   int amountX;
   int amountY;
   int amountZ;
   public FullMultiBlockCreator()
   {
      typeOfFunction.addItem("SetBlock");
      typeOfFunction.addItem("DetectBlock");
      //this.setLayout(new FlowLayout());
      this.setLayout(thisLayout);
      //this.add(displayNumber);
      //this.add(holder);
      //this.add(up);
      //this.add(down);
      //this.add(getFunction);
      //this.add(setSize);
      up.addActionListener(this);
      down.addActionListener(this);
      //setSize.addActionListener(this);
      getFunction.addActionListener(this);
      this.displayNumber.setText(position+"");
   }
   public FullMultiBlockCreator(int x, int y, int z)
   {
      this();
      setAmount(x, y, z);
      reload();
   }
   public void actionPerformed(ActionEvent e) 
   {
      String action = e.getActionCommand();
      if(action.equals("Up"))
      {
         if(position+1 > layers.size())
         {
         
         }
         else
         {
            layout.next(holder);
            position++;
         }
      }
      else if(action.equals("Down"))
      {
         
         if(position-1 < 1)
         {
            
         }
         else
         {
            layout.previous(holder);
            position--;
         }
      }
      else if(action.equals("Get Function"))
      {
         try{
         BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
         boolean isSetBlock;
         String selectedText = (String)typeOfFunction.getItemAt(typeOfFunction.getSelectedIndex());
         if(selectedText.equals("SetBlock"))
         {
            isSetBlock = true;
         }
         else
         {
            isSetBlock = false;
         }
         for(int i2 = 0; i2 < layers.size(); i2++)
         {
            for(int i3 = 0; i3 < layers.get(i2).dropDowns.size(); i3++)
            {  
               
               layers.get(i2).dropDowns.get(i3).y = i2+Integer.parseInt(yStartingPosition.getText());
               layers.get(i2).dropDowns.get(i3).x = Integer.parseInt(xStartingPosition.getText())+layers.get(i2).dropDowns.get(i3).defaultx;
               layers.get(i2).dropDowns.get(i3).z = Integer.parseInt(zStartingPosition.getText())+layers.get(i2).dropDowns.get(i3).defaultz;
               if(!layers.get(i2).dropDowns.get(i3).getText().equals(""))
               {
                  if(isSetBlock)
                  {
                     String thingToWrite ="setblock "+layers.get(i2).dropDowns.get(i3).getText()+"\n"; 
                     writer.write(thingToWrite);
                     System.out.println(thingToWrite);
                  }
                  else
                  {
                     writer.write("execute "+entity.getText()+" if block "+layers.get(i2).dropDowns.get(i3).getText()+" run ");
                  }
               }
               else
               {
                  //System.out.println("gdfjgkdfj");
               }
               
            }
         }
         writer.close();
         }
         catch(Exception ead){
            ead.printStackTrace();
         }
         
      }
      else if(action.equals("Set Size"))
      {
         
      }
      this.displayNumber.setText(position+"");
   }
   public void setAmount(int x, int y, int z)
   {
      amountX = x;
      amountY = y;
      amountZ = z;
      for(int i2 = 0; i2 < z; i2++)
      {
         layers.add(new MultiBlockCreator(x, z));
      }
   }
   public void reload()
   {
      
      holder.setLayout(layout);
      
      holder.removeAll();
      for(int i2 = 0; i2 < this.layers.size(); i2++)
      {
         
         //this.layers.get(i2).setVisible(false);
         holder.add(layers.get(i2));
         /*if(i2 == 0)
         {
            this.layers.get(i2).setVisible(true);
         }*/
      }
      thisLayout.setAutoCreateGaps(true);
      thisLayout.setAutoCreateContainerGaps(true);
      thisLayout.setHorizontalGroup(
      thisLayout.createSequentialGroup()
      
         
         
         .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
         .addComponent(displayNumber, 0, 50, 100)
         .addComponent(holder)
         
         .addComponent(up, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(down, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         
         .addComponent(getFunction, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              
              .addComponent(typeOfFunction, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(entity)
              .addComponent(xStartingPosition, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(yStartingPosition)
          .addComponent(zStartingPosition)
              
              
   ));
   thisLayout.setVerticalGroup(
      thisLayout.createSequentialGroup()
      .addComponent(displayNumber, 0, 30, 30)
      .addComponent(up, 0, 30, 30)
         .addComponent(down, 0, 30, 30)
         .addComponent(getFunction, 0, 30, 30)
         .addComponent(typeOfFunction, 0, 30, 30)
         .addComponent(entity, 0, 30, 30)
         .addComponent(xStartingPosition, 0, 30, 30)
              .addComponent(yStartingPosition, 0, 30, 30)
              .addComponent(zStartingPosition, 0, 30, 30)
         .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              
              .addComponent(holder)
              
              
         
         
   ));
   }
}
class ExtraTextField extends JTextField
{
   int x = 0;
   int y = 0;
   int z = 0;
   int defaultx = 0;
   int defaulty = 0;
   int defaultz = 0;
   boolean isSetBlock = true;
   JTextField dummy = new JTextField();
   public String getText()
   {
      String text = super.getText();
      if(!text.equals(""))
      {
         text = "~"+x+" ~"+y+" ~"+z+" minecraft:"+text;
         return text;
      }
      return "";
   }
}

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;  // Notice these dynamic imports
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Random;
public class WidgetBlockArray
{
   public ArrayList<WidgetBlock> arrayList = new ArrayList<WidgetBlock>();
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

   public void addCommandWidgetClone(CommandWidget widget, int x, int y)
   {
      if(widget != null)
      {
         
         /*
         if(Math.random() <= 0.5)
         {
            this.playSound("Pop2.wav");
         }
         else
         {
            this.playSound("Pop1.wav");
         }
         */
         WidgetFrame tempFrame = widget.frame;
         DragAndDropWidgetMenu tempPanel = widget.panel;
         widget.allWidgetBlocks = null;
         widget.frame = null;
         widget.panel = null;
         ArrayList<WidgetBlock> allPossibleWidgets = widget.getAllPossibleWidgets();
         ArrayList<Integer> widgetContainerLevels = new ArrayList<Integer>();
         for(int i1 = 0; i1 < allPossibleWidgets.size(); i1++)
         {
            allPossibleWidgets.get(i1).arrayPosition = -1;
         }
         for(int i1 = 0; i1 < allPossibleWidgets.size(); i1++)
         {
            allPossibleWidgets.get(i1).allWidgetBlocks = null;
            allPossibleWidgets.get(i1).frame = null;
            allPossibleWidgets.get(i1).panel = null;
            for(int i2 = 0; i2 < allPossibleWidgets.size(); i2++)
            {
               if(allPossibleWidgets.get(i1) == allPossibleWidgets.get(i2).widgetBlockContainer)
               {
                  allPossibleWidgets.get(i2).arrayPosition = i1;
               }
            }
         }
         Object obj = copyer.copy(widget);
         widget.allWidgetBlocks = this;
         widget.frame = tempFrame;
         widget.panel = tempPanel;
         for(int i1 = 0; i1 < allPossibleWidgets.size(); i1++)
         {
            allPossibleWidgets.get(i1).allWidgetBlocks = this;
            allPossibleWidgets.get(i1).frame = tempFrame;
            allPossibleWidgets.get(i1).panel = tempPanel;
         }
         if(obj instanceof CommandWidget)
         {
            CommandWidget commandWidget = (CommandWidget)obj;
            ArrayList<WidgetBlock> allPossibleWidgets2 = commandWidget.getAllPossibleWidgets();
            for(int i1 = 0; i1 < allPossibleWidgets2.size(); i1++)
            {
               allPossibleWidgets2.get(i1).setAllWidgetBlocks(this);
               allPossibleWidgets2.get(i1).frame = tempFrame;
               allPossibleWidgets2.get(i1).panel = tempPanel;
               if(allPossibleWidgets2.get(i1).arrayPosition != -1)
               {
                  allPossibleWidgets2.get(i1).widgetBlockContainer = allPossibleWidgets2.get(allPossibleWidgets2.get(i1).arrayPosition);
               }
               this.frame.addWidgetBlock(allPossibleWidgets2.get(i1));
            }
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
            commandWidget.changeLayerOfAll();
            
         }
      }
      
   }
   public void showPopupMenu(WidgetBlock widget, int x, int y)
   {
      int popupMenuOffset = 20;
      this.popupMenu.widgetBlock = widget;
      this.popupMenu.show(this.frame, x+widget.getX()+popupMenuOffset, y+widget.getY()+popupMenuOffset);
      this.popupMenu.x = x+widget.getX()+popupMenuOffset;
      this.popupMenu.y = y+widget.getY()+popupMenuOffset;
      this.popupMenu.numberOfPopupsMade = 0;
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
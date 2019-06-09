import javax.swing.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;
import java.awt.*;
public class PopupMenu extends JPopupMenu implements ActionListener{
    private JMenuItem copy = new JMenuItem("Copy");
    private JMenuItem paste = new JMenuItem("Paste");
    WidgetBlockArray allWidgetBlocks;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    WidgetBlock widgetBlock;
    int x;
    int y;
    int numberOfPopupsMade = 0;
    public PopupMenu()
    {
      super();
      copy.setMnemonic(KeyEvent.VK_P);
      copy.getAccessibleContext().setAccessibleDescription("New Project");
      paste.setMnemonic(KeyEvent.VK_P);
      paste.getAccessibleContext().setAccessibleDescription("New Project");
      this.add(copy);
      this.add(paste);
      copy.addActionListener(new ActionListener(){
    public void actionPerformed(    final ActionEvent arg0){
      if(widgetBlock != null)
      {
         saveObject(widgetBlock);
      }
    }
  }
);
paste.addActionListener(new ActionListener(){
    public void actionPerformed( ActionEvent arg0){
      if(widgetBlock != null)
      {
         allWidgetBlocks.addCommandWidgetClone((CommandWidget)returnObject(), (numberOfPopupsMade*5)+x, (numberOfPopupsMade*5)+y);
         
      }
    }
  }
);
    }
    public void saveObject(Object obj)
    {
      try{
         WidgetBlock block = (WidgetBlock)obj;
         WidgetFrame tempFrame = block.frame;
         DragAndDropWidgetMenu tempPanel = block.panel;
         WidgetBlock tempBlockContainer = block.widgetBlockContainer;
         block.allWidgetBlocks = null;
         block.frame = null;
         block.panel = null;
         block.widgetBlockContainer = null;
         ArrayList<WidgetBlock> allPossibleWidgets = block.getAllPossibleWidgets();
         for(int i1 = 0; i1 < allPossibleWidgets.size(); i1++)
         {
            allPossibleWidgets.get(i1).allWidgetBlocks = null;
            allPossibleWidgets.get(i1).frame = null;
            allPossibleWidgets.get(i1).panel = null;
            
         }
         ObjectOutputStream out = new ObjectOutputStream(bos);
         out.writeObject(block);
         block.allWidgetBlocks = this.allWidgetBlocks;
         block.frame = tempFrame;
         block.panel = tempPanel;
         block.widgetBlockContainer = tempBlockContainer;
         for(int i1 = 0; i1 < allPossibleWidgets.size(); i1++)
         {
            allPossibleWidgets.get(i1).allWidgetBlocks = this.allWidgetBlocks;
            allPossibleWidgets.get(i1).frame = tempFrame;
            allPossibleWidgets.get(i1).panel = tempPanel;
         }
         
         
         }
         catch (Exception e){
        e.printStackTrace();
    }
    }
    public Object returnObject()
    {
      Object obj = null;
      try{
      ByteArrayInputStream bis = new ByteArrayInputStream(this.bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        CommandWidget commandWidget = (CommandWidget)in.readObject();
       return commandWidget;
       }
       catch (Exception e){
        e.printStackTrace();
    }
    return obj;
    }
    public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, "New Project clicked!");
            }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Popup Menu Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel panel = new JPanel();
         PopupMenu popupMenu = new PopupMenu();
         JButton button = new JButton();
         button.setPreferredSize(new Dimension(40, 40));
         
         
         //popupMenu.saveObject(button);
         
        
         
         
         //frame.getContentPane().add((JComponent)popupMenu.returnObject());
         //frame.getContentPane().add((JComponent)popupMenu.returnObject());
         //frame.add(new JButton());
         
         
         frame.addMouseListener(new MouseAdapter() {
 
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
 
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                
                    popupMenu.show(e.getComponent(),
                            e.getX(), e.getY());
                           
                }
            }
        });
 
        
        frame.pack();
        frame.setVisible(true);
       
    }
    
}
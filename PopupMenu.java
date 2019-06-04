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
import javax.activation.DataHandler;
public class PopupMenu extends JPopupMenu implements ActionListener{
    private JMenuItem duplicate = new JMenuItem("Duplicate");
    WidgetBlockArray allWidgetBlocks;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    public PopupMenu()
    {
      super();
      duplicate.setMnemonic(KeyEvent.VK_P);
      duplicate.getAccessibleContext().setAccessibleDescription("New Project");
      
      this.add(duplicate);
    }
    public void saveObject(Object obj)
    {
      try{
         ObjectOutputStream out = new ObjectOutputStream(bos);
         out.writeObject(obj);
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
       return in.readObject();
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
         
         
         popupMenu.saveObject(button);
         
        
         
         
         //frame.getContentPane().add((JComponent)popupMenu.returnObject());
         //frame.getContentPane().add((JComponent)popupMenu.returnObject());
         frame.add(new JButton());
         
         
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
    public static byte[] toBytes(DataHandler handler) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    handler.writeTo(output);
    return output.toByteArray();
}
}
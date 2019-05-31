import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
class RoundedJTextField extends JTextField //Creates a JTextField with rounded edges
{
    private Shape shape;
    public RoundedJTextField(int size, int x, int y, int width, int height) {
        super(size);
        setOpaque(false);
        enableInputMethods(true);   
        this.setBounds(x, y, width, height);
        
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
    
public static void main(String args[])
{

   
         JFrame frame = new JFrame("Test Frame");
         frame.setSize(800, 800);
         frame.setLayout(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().add(new RoundedJTextField(50, 10, 10, 50, 100));
         frame.setVisible(true);

}
    
}
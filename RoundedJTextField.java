import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.geom.RoundRectangle2D;
class RoundedJTextField extends JTextField implements Cloneable//Creates a JTextField with rounded edges
{
    private Shape shape;
    int roundness = 0;
    int level = 25;
    public RoundedJTextField(int size, int x, int y, int width, int height) {
        super(size);
        
        setOpaque(false);
        enableInputMethods(true);   
        if(height <= level)
         {
            this.roundness = 0;
         }
         else
         {
            this.roundness = 15;
         }
        this.setBounds(x, y, width, height);
        
        
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         if(this.getHeight() <= this.level)
         {
            this.roundness = 0;
         }
         else
         {
            this.roundness = 15;
         }
         /*
         if(this.getHeight() <= level)
         {
            g.fillRect(0, 0, getWidth()-1, getHeight()-1);
         }
         else
         { */
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, this.roundness, this.roundness);
         //}
         
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         /*
         if(this.getHeight() <= level)
         {
            g.drawRect(0, 0, getWidth()-1, getHeight()-1);
         }
         else
         {*/
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, this.roundness, this.roundness);
         //}
         
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, this.roundness, this.roundness);
         }
         return shape.contains(x, y);
    }
    public Object clone() throws
                   CloneNotSupportedException 
    { 
         return super.clone();
    }
public static void main(String args[])
{

   
         JFrame frame = new JFrame("Test Frame");
         frame.setSize(800, 800);
         frame.setLayout(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().add(new RoundedJTextField(40, 10, 10, 50, 40));
         frame.setVisible(true);

}
    
}
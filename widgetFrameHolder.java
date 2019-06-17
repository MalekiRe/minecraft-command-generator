public class widgetFrameHolder extends JFrame
{
   int fullWidth;
   int fullHeight;
   public widgetFrameHolder(int width, int height, String text)
   {
      this.setBounds(0, 0, width, height);
      WidgetFrame = new DragAndDropWidgetMenu(width, height, color, allWidgetBlocks);
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
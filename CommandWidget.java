import java.util.ArrayList;
import java.io.Serializable;
public class CommandWidget extends WidgetBlock implements Cloneable, Serializable
{
   public String header = "";
   public ArrayList<String> name = new ArrayList<String>();
   public ArrayList<String> concatFunction = new ArrayList<String>();
   boolean isSquished = true;
   public CommandWidget()
   {
      super(0, 0, 150, 100);
      this.setDraggable(true);
   }
   public CommandWidget(String header, VariableTemplate... varTemplate)
   {
      super(0, 0, varTemplate.length*150, 100);
      this.setHeader(header);
      for(VariableTemplate var : varTemplate)
      {
         this.addVariable(var.getName(), var.getConcatFunc());
      }
      this.setDraggable(true);
   }
   public CommandWidget(CommandWidget widget)
   {
      super(0, 0, widget.concatFunction.size()*150, 100);
      
      this.setHeader(widget.getHeader());
      for(int i2 = 0; i2 < widget.concatFunction.size(); i2++)
      {
         this.addVariable(widget.name.get(i2), widget.concatFunction.get(i2));
      }
      
   }
   public CommandWidget(CommandWidget widget, int x, int y)
   {
      
      super(x, y, widget.concatFunction.size()*150, 100);
      
      this.setHeader(widget.getHeader());
      for(int i2 = 0; i2 < widget.concatFunction.size(); i2++)
      {
         this.addVariable(widget.name.get(i2), widget.concatFunction.get(i2));
      }
      ArrayList<WidgetClass> allVarsInArray = widget.getAllVariablesInArray();
      for(WidgetClass widgetClass : allVarsInArray)
      {
         
      }
   }
   public ArrayList<WidgetClass> getAllVarsInArray()
   {
      return this.getAllVariablesInArray();
   }
   public void setHeader(String header)
   {
      this.header = header;
   }
   public String getHeader()
   {
      this.setBounds(this.getX(), this.getY(), this.concatFunction.size()*150, 100);
      this.setWidth(this.concatFunction.size()*150);
      return this.header;
   }
   public void addVariable(String name, String concatFunc)
   {
      super.addVariable(name);
      this.concatFunction.add(concatFunc);
      this.name.add(name);
      this.setBounds(this.getX(), this.getY(), this.concatFunction.size()*150, 100);
      this.setWidth(this.concatFunction.size()*150);
   }
   public String getText()
   {
      String returnString = this.header;
      for(int i2 = 0; i2 < this.variables.size(); i2++)
      {
         returnString += " "+concatFunction.get(i2)+variables.get(i2).getText();
      }
      return returnString;
   } 
   public ArrayList<String> getNames()
   {
      return this.name;
   }
   public ArrayList<String> getConcatFunctions()
   {
      return this.concatFunction;
   }
   public Object clone() throws
                   CloneNotSupportedException 
    { 
        CommandWidget cloned = (CommandWidget)super.clone();
        
        for(int i2 = 0; i2 < this.getVariableArray().size(); i2++)
        {
            ArrayList<WidgetClass> dummy1 = cloned.getVariableArray();
            dummy1 = (ArrayList<WidgetClass>)this.getVariableArray().clone();
            if(cloned.widgetBlockContainer != null)
            {
               cloned.widgetBlockContainer = null;
            }
            
            cloned.setContained(false);
        }
        
        return cloned;
    } 
}
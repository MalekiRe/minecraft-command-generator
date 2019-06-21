import java.util.ArrayList;
import java.io.Serializable;
public class CommandWidget extends WidgetBlock implements Cloneable, Serializable
{
   public String header = ""; //What the command starts concatination with
   public ArrayList<VariableTemplate> allCommandVariables = new ArrayList<VariableTemplate>(); //A list of all of the variables it has.
   boolean isSquished = true;//Weither or not the CommandWidget is being squished.
   int defaultx = 65;//Just easy setup for defualt x and y.
   int defaulty = 65;
   public CommandWidget()
   {
      super(0, 0, 65, 65);//Default x and y setup dosn't work becuase super constructor, so have to input it manually.
      this.setDraggable(true);//Set the ability for it to be dragged around indepndily.
   }
   public CommandWidget(int x, int y, int width, int height)
   {
      super(x, y, width, height);//Constructre but with put in variables.
      this.setDraggable(true);
   }
   public CommandWidget(String header, VariableTemplate... varTemplate) //Add as many variable templates ads needed to the countructer.
   {
      super(0, 0, varTemplate.length*50, 75);//Set the size so that it fits the variables.
      this.setHeader(header);//Set the header to the header.
      for(VariableTemplate var : varTemplate)
      {
         this.addVariable(var);//Add variables inputed into the the variables arraylist.
      }
      this.setDraggable(true);
   }
   public CommandWidget(String header, String s1[], String startConcatFunction, String endConcatFunction)
   {
      super(0, 0, 50, 50);
      this.setHeader(header);
      
      this.addVariable(new VariableTemplate("", startConcatFunction, endConcatFunction), s1);
      
      this.setDraggable(true);
   }
   public ArrayList<WidgetClass> getAllVarsInArray()
   {
      return this.getAllVariablesInArray(); //Returns the arraylist of variables.
   }
   public void setHeader(String header)//Sets the header, so it is possible ot change it later.
   {
      this.header = header;
      //this.addName(header);
   }
   public String getHeader()//Gets the header, for possible later checking or concatination.
   {
      //Also resets the bounds and the width.
      this.setBounds(this.getX(), this.getY(), this.allCommandVariables.size()*defaultx, defaulty);
      this.setWidth(this.allCommandVariables.size()*defaultx);
      
      return this.header;
   }
   public void addVariable(VariableTemplate var)
   {
      super.addVariable(var.getName());
      this.allCommandVariables.add(var);
      this.setBounds(this.getX(), this.getY(), this.allCommandVariables.size()*defaultx, defaulty);
      this.setWidth(this.allCommandVariables.size()*defaultx);
   }
   public void addVariable(VariableTemplate var, String s1[])
   {
      super.addVariable(var.getName(), s1);
      this.allCommandVariables.add(var);
      this.setBounds(this.getX(), this.getY(), this.allCommandVariables.size()*defaultx, defaulty);
      this.setWidth(this.allCommandVariables.size()*defaultx);
   }
   public String getText()
   {
      String returnString = this.header;
      String startConcat = "";
      String endConcat = "";
      for(int i2 = 0; i2 < this.variables.size(); i2++)
      {
         startConcat = allCommandVariables.get(i2).getStartConcatFunction();
         endConcat = allCommandVariables.get(i2).getEndConcatFunction();
         
         if(variables.get(i2).getText().equals(""))
         {
          returnString += variables.get(i2).getText();
         }
         else
         {
            returnString += startConcat+variables.get(i2).getText()+endConcat;
         }
         
      }
      return returnString;
   } 
   public ArrayList<VariableTemplate> getAllCommandVariable()
   {
      return this.allCommandVariables;
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

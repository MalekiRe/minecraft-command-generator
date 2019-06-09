import java.io.Serializable;
public class VariableTemplate implements Serializable
{
   String name = "";
   String startConcatFunction = "";
   String endConcatFunction = "";
   public VariableTemplate(String name, String startConcatFunction, String endConcatFunction)
   {
      this.name = name;
      this.startConcatFunction = startConcatFunction;
      this.endConcatFunction = endConcatFunction;
   }
   public String getName()
   {
      if(this.name != null)
      {
         return this.name;
      }
      else
      {
         return "";
      }
   }
   public String getStartConcatFunction()
   {
      if(this.startConcatFunction != null)
      {
         return this.startConcatFunction;
      }
      else
      {
         return "";
      }
   }
   public String getEndConcatFunction()
   {
      if(this.endConcatFunction != null)
      {
         return this.endConcatFunction;
      }
      else
      {
         return "";
      }
   }
}
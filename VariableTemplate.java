public class VariableTemplate
{
   String name = "";
   String concatFunc = "";
   public VariableTemplate(String name, String concatFunc)
   {
      this.name = name;
      this.concatFunc = concatFunc;
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
   public String getConcatFunc()
   {
      if(this.concatFunc != null)
      {
         return this.concatFunc;
      }
      else
      {
         return "";
      }
   }
}
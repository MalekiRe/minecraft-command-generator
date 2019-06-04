import java.io.*;
import java.util.ArrayList;
public class FileParser
{
   public ArrayList<CommandWidget> getFileParse(String fileName)
   {

        // The name of the file to open.
        
         ArrayList<CommandWidget> commandWidgets = new ArrayList<CommandWidget>();
        // This will reference one line at a time
        String line = null;
         FileParser parser = new FileParser();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            ArrayList<String> strings = new ArrayList<String>();
            while((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }   

            // Always close files.
            bufferedReader.close(); 
            commandWidgets = parser.parseFile(strings); 
            for(int i1 = 0; i1 < commandWidgets.size(); i1++)
            {
               System.out.println("Header : "+commandWidgets.get(i1).getHeader());
               for(int i2 = i1; i2 < commandWidgets.get(i1).getNames().size(); i2++)
               {
                  System.out.println("     Name : "+commandWidgets.get(i1).getNames().get(i2));
                  System.out.println("     ConcatFunction : "+commandWidgets.get(i1).getConcatFunctions().get(i2));
               }
            }
            return commandWidgets;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return commandWidgets;
    }
    public ArrayList<CommandWidget> parseFile(ArrayList<String> strings)
    {
      ArrayList<CommandWidget> commandWidgets = new ArrayList<CommandWidget>();
      CommandWidget commandWidgetDummy = new CommandWidget();
      for(int i1 = 0; i1 < strings.size(); i1++)
      {
         commandWidgetDummy = new CommandWidget();
         if(strings.get(i1).equals("<Command>"))
         {
            if(strings.get(i1+1).equals("   <Header>"))
            {
               if(strings.get(i1+2).substring(0,7).equals("      <"))
               {
                  if(strings.get(i1+2).substring(7, 8).equals("\""))
                  {
                     commandWidgetDummy.setHeader(strings.get(i1+2).substring(8,strings.get(i1+2).length()-2));
                     
                     i1++;
                  }
               }
               int i2 = i1+2;
               while(strings.get(i2).equals("   <VariableTemplate>") )
               {
                  
                  commandWidgetDummy.addVariable(strings.get(i2+1).substring(8, strings.get(i2+1).length()-2), strings.get(i2+2).substring(8, strings.get(i2+2).length()-2));
                  i2 += 3;
               }
               i1 = i2;
            }
         }
         commandWidgets.add(commandWidgetDummy);
      }
      
      return commandWidgets;
      
    }

}
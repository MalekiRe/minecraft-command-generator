import java.io.*;
import java.util.ArrayList;
import java.awt.Color;
public class FileParser
{//8
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
            /*
            for(int i1 = 0; i1 < commandWidgets.size(); i1++)
            {
               System.out.println("Header : "+commandWidgets.get(i1).getHeader());
               for(int i2 = i1; i2 < commandWidgets.get(i1).getNames().size(); i2++)
               {
                  System.out.println("     Name : "+commandWidgets.get(i1).getNames().get(i2));
                  System.out.println("     ConcatFunction : "+commandWidgets.get(i1).getConcatFunctions().get(i2));
               }
            }
            */
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
    /*
    public ArrayList<CommandWidget> parseFile(ArrayList<String> strings)
    {//7
      ArrayList<CommandWidget> commandWidgets = new ArrayList<CommandWidget>();
      CommandWidget commandWidgetDummy = new CommandWidget();
      for(int i1 = 0; i1 < strings.size(); i1++)
      {//6
         commandWidgetDummy = new CommandWidget();
         if(strings.get(i1).equals("<Command>"))
         {//5
            
            if(strings.get(i1+1).equals("   <Header>"))
            {//4
               if(strings.get(i1+2).substring(0,7).equals("      <"))
               {//2
                  if(strings.get(i1+2).substring(7, 8).equals("\""))
                  {//1
                     commandWidgetDummy.setHeader(strings.get(i1+2).substring(8,strings.get(i1+2).length()-2));
                     
                     i1++;
                  }//1
               }//2
               int i2 = i1+2;
               while(strings.get(i2).equals("   <VariableTemplate>") )
               {//3
                  String name = strings.get(i2+1).substring(8, strings.get(i2+1).length()-2);
                  String startConcat = strings.get(i2+2).substring(8, strings.get(i2+2).length()-2);
                  String endConcat = strings.get(i2+3).substring(8, strings.get(i2+3).length()-2);
                  commandWidgetDummy.addVariable(new VariableTemplate(name, startConcat, endConcat));
                  i2 += 4;
               }//3
               i1 = i2;
            }//4
            commandWidgets.add(commandWidgetDummy);
         }//5
         
         
      }//6
      return commandWidgets;
      
      
    }//7
    */
    public ArrayList<CommandWidget> parseFile(ArrayList<String> strings)
    {//7
      ArrayList<CommandWidget> commandWidgets = new ArrayList<CommandWidget>();
      CommandWidget commandWidgetDummy = new CommandWidget();
      for(int i1 = 0; i1 < strings.size(); i1++)
      {//6
         commandWidgetDummy = new CommandWidget();
         if(strings.get(i1).equals("<Command>"))
         {//5
            if(strings.get(i1+1).equals("   <Label>"))
            {
               i1++;
               commandWidgetDummy.addName(strings.get(i1+1).substring(8,strings.get(i1+1).length()-2));
               i1++;
            }
            if(strings.get(i1+1).equals("   <Header>"))
            {//4
               if(strings.get(i1+2).substring(0,7).equals("      <"))
               {//2
                  if(strings.get(i1+2).substring(7, 8).equals("\""))
                  {//1
                     commandWidgetDummy.setHeader(strings.get(i1+2).substring(8,strings.get(i1+2).length()-2));
                     
                     i1++;
                  }//1
               }//2
               int i2 = i1+2;
               while(strings.get(i2).equals("   <VariableTemplate>") || strings.get(i2).equals("   <DropDown>"))
               {//3
                  if(strings.get(i2).equals("   <VariableTemplate>"))
                  {
                     String name = strings.get(i2+1).substring(8, strings.get(i2+1).length()-2);
                     String startConcat = strings.get(i2+2).substring(8, strings.get(i2+2).length()-2);
                     String endConcat = strings.get(i2+3).substring(8, strings.get(i2+3).length()-2);
                     commandWidgetDummy.addVariable(new VariableTemplate(name, startConcat, endConcat));
                     i2 += 4;
                  }
                  else
                  {
                     String startConcat = strings.get(i2+1).substring(8, strings.get(i2+1).length()-2);
                     String endConcat = strings.get(i2+2).substring(8, strings.get(i2+2).length()-2);
                     i2+= 3;
                     ArrayList<String> dropDownOptions = new ArrayList<String>();
                     while(!strings.get(i2).equals("   <EndDropDown>"))
                     {
                        dropDownOptions.add(strings.get(i2).substring(8, strings.get(i2).length()-2));
                        i2++;
                     }
                     String s1[] = new String[dropDownOptions.size()];
                     for(int i3 = 0; i3 < dropDownOptions.size(); i3++)
                     {
                        s1[i3] = dropDownOptions.get(i3);
                        System.out.println(s1[i3]);
                     }
                     
                     commandWidgetDummy.addVariable(new VariableTemplate("", startConcat, endConcat), s1);
                     /*
                     String s1[] = new String[dropDownOptions.size()];
                     for(int i3 = 0; i3 < dropDownOptions.size(); i3++)
                     {
                        s1[i3] = dropDownOptions.get(i3);
                     } 
                     ((WidgetWithVariable)commandWidgetDummy.variables.get(commandWidgetDummy.variables.size()-1)).setDropDownStringList(s1);
                     */
                     i2++;
                  }
                  
               }//3
               i1 = i2;
            }//4
            if(strings.get(i1).contains("   <Color>"))
            {
               int Red = 0;
               int Green = 0;
               int Blue = 0;
               if(strings.get(i1+1).substring(0,11).equals("      <Red:"))
               {
                  Red = Integer.parseInt(strings.get(i1+1).substring(11,strings.get(i1+1).length()-1));
               }
               if(strings.get(i1+2).substring(0,13).equals("      <Green:"))
               {
                  Green = Integer.parseInt(strings.get(i1+2).substring(13,strings.get(i1+2).length()-1));
               }
               if(strings.get(i1+3).substring(0,12).equals("      <Blue:"))
               {
                  Blue = Integer.parseInt(strings.get(i1+3).substring(12,strings.get(i1+3).length()-1));
                  System.out.println(Blue);
               }
               commandWidgetDummy.setColor(new Color(Red, Green, Blue));
            }
            commandWidgets.add(commandWidgetDummy);
         }//5
         
         
      }//6
      return commandWidgets;
      
      
    }//7
}//8
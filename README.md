# minecraft-command-generator
A scratch like interface for minecraft commands

This program is made up of two different programs. First their is the simple multiblock command generator. This program allows you to easliy create or detect minecraft structures and specifiy the file name you want to output to. The second is the true command generator. This command generator is not speicfic to minecraft commands. It takes in a file called commandGenerator and reads it using the syntax I have created. This will create little movable blocks, reminsent of scratch, which you can move around and plug into eachother. It comes pre-made with minecraft commands, but you can you it for any of your concationation needs. It allows for easy duplication, deletion, and dragging of commands. The syntax of the input file is as follows.

<Label>
  <"A Label">//This is what words appear next to the widget block, with no effect on output.
<Header>
  <"A Header">//This does not graphicaly appear, but instead is the first thing that is concatanted in the output
//At this point the only output you would get would be "A Header".
  
<Label>
  <"A Label">//This is what words appear next to the widget block, with no effect on output.
<Header>
  <"A Header">//This does not graphicaly appear, but instead is the first thing that is concatanted in the output
<VariableTemplate>//This is the first input box, where you can type a string, or drag another widget block into its space. You can chain     <"My Variable>//This displays when nothing is put in. Has no effect on output.   //as many of these as you like, one after another.
  <" First! "> //This is what is put before the "My First Variable", will not appear if My Variable is left blank.
  <" Last!"> //This is what is put after the "My Firt Variable", will not appear if my variable is left blank.
  
So, If you typed in "This is so cool" to the My Variable, the output would be
"A Header First! This is so cool Last!"
You can chain as many of these together as you like. The second thing you can add is a Drop Down box.
<DropDown>
  <"{">
  <"}">
  <"1">
  <"2">
  <"Option 3">
<EndDropDown>
If you selected 1 on the dropdown the output would be
"{1}"
If you selected Option 3 the ouptut would be
"{Option 3}"
Ect
Notice that the Beginning and End concationtion are the first two options, and there is no option to display without affect concationation. You can chain together as many options as you like, including a "" option. The "" option is very important becuase it would not include the begging and end concation, I.E.
<Label>
  <"A Label">
<Header>
  <"A Header">
<DropDown>
  <" {/">
  <"/}">
  <"">
  <"1">
  <"2">
<EndDropDown>
<DropDown>
    <" {">
    <"}">
    <"A">
    <"B">
<EndDropDown>

If you selected the first option, "", then the output would be
"A Header {A}"
But if you selected the second option, "1", then the output would be
"A Header {/1/} {A}"
Notice that you must state <EndDropDown> when you are finished adding items to your drop down, but that is not needed for variable template.
  
Next, you can change the default height and width of the variables. You do this by putting <Size> before the label. I.E.
<Command>
  <Size>
    <Width:40>
    <Height:40>
  <Label>
    <"Hi">
  <Header>
    <"Hello">
<EndCommand>
You also need to start each command with the <Command> tag and end each command with the <EndCommand> tag.
Spacing is important, five spaces is the number of spaces that <Size> is ahead of <Command> that <Width:40> is ahead of <Size> ect.
Finally, you can change the color of the widget block at the end with this syntax:

  <Color>
    <Red:25>
    <Green:35>
    <Blue:120>
<EndCommand>
So, a working command could be.

<Command>
  <Size>
    <Width:40>
    <Height:40>
  <Label>
    <"Hey">
  <Header>
    <"HELLO!">
  <VariableTemplate>
    <"First">
    <" ">
    <"">
  <DropDown>
    <"{">
    <"}">
    <"@">
    <"a">
    <"A">
  <EndDropDown>
  <VariableTemplate>
    <"THIRD!">
    <"{ ">
    <" }">
  <Color>
    <Red:50>
    <Green:0>
    <Blue:15>
<EndCommand>

/*
 * Βασίλης Γεμιστός / Vasilis Gemistos
 * 
 * mail: cst11010@uop.gr
 * 
 * AM : 2025201100010
 * 
 * ----------------------------------------------
 * 
 * Παναγιώτης Κουτσιώρας / Panagiotis Koutsioras
 * 
 * mail: cst11037@uop.gr
 * 
 * AM : 2025201100037
 * 
 */
package battleship;


public enum Command {

    HELP("help", "/-------------------------------\\\n"
               + "|.     Available commands      .|\n"
               + "\\-------------------------------/\n"
               + " |. HELP - Prints help        .|\n"
               + " |. SAVE - Saves current game .|\n"
               + " |. LOAD - Loads a game       .|\n"
               + " |. exit - Exits game         .|\n"
               + "/-------------------------------\\\n"),
    SAVE("save", "Save game"),
    LOAD("load", "Load game"),
    EXIT("exit", "Exit game");
    
    //Πεδία
    private String commandString;
    private String helpText;

    //Constructor
    private Command(String cms, String help) {
        commandString = cms;
        helpText = help;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getHelpText() {
        return helpText;
    }
}
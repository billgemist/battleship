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

public class MoveIsCommandException extends InvalidLocationException {
    private Command cmd ;

    public MoveIsCommandException(){
        super("This is a game command and not a move");
    }
    
    public MoveIsCommandException(String message){
        super(message);
    }
    
    public void MoveIsCommandException(Command cmd){
        this.cmd = cmd;
    }
    
    public Command getCommand(){
        return cmd;
    }
}

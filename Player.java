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

public class Player {

    private String name;
    private int score;
    private int field;
    public String[][] gameBoard;//Ταμπλό του παίχτη
    
    
    public Player (String name){
        this.name = name;
    }
    
    public String getName(){
       return name;
    }
    
    //Κατασκευή πεδίου του παίχτη (Γραμμές x Στήλες)
    public void initField(int r, int c) {

        gameBoard = new String[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                gameBoard[i][j] = ".";
            }
        }

    }
    
    //Τοποθετεί τα πλοία του παίκτη στο πεδίο του αντίπαλου παίχτη
    public void placeShips(Field otherField)
    {
        
        
    }
    
    //Ελέγχει αν ο παίχτης είναι νικητής (Όλα τα πλοία του αντιπάλου έχουν βυθιστεί)
    public void hasWon()
    {
        
    }
    
    //Επιλογή θέσης για την κίνηση του παίχτη
    public void selectMove()
    {
        
    }
    
    // prints the game board of each player
     public void printGameBoard(int r, int c)
    {
       System.out.println("\n" + name + " Board:");     
       System.out.print("     ");
       for(int i=0; i<9; i++){
            System.out.print(i+1 + "    ");
       }
       for(int i=0; i<c-9; i++){
            System.out.print(i+10 + "   ");
       }
       System.out.print("\n   ");
       for(int i=0; i<c; i++){
            System.out.print("-----");
       }
       System.out.print("\n");
       for (int i =0; i < r; i++){
            System.out.print(" " + (char)(65+i) + " | "); 
            for(int j = 0; j < c; j++){
                System.out.print(gameBoard[i][j] + "    ");  
            }
            System.out.println("\n");
       }   
       
    }
}

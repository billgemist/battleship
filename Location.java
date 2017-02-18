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

public class Location {
    private int row;
    private int col;
    private boolean ship; //true if the location has a ship 
    private boolean marked; // true if the location is marked
    
    //Καλείται όταν ο παίχτης επιλέξει την θέση αυτή
    public void mark()
    {
        marked = true;
        if(ship == true){
            isHit(); // calling the method to hit the ship
        }
    }
    
    //Ελέγχει αν η θέση έχει επιλεγεί από τον παίκτη σε κίνηση του
    public boolean isMarked()
    {
        if (marked == true) {
            System.out.println("location is marked");
            return true;
        }
        else {
            System.out.println("location is not marked");
            return false;
        }
    }
    
    //Ελέγχει αν η θέση είναι κενή
    public boolean isEmpty()
    {
        if (ship == true) {
            System.out.println("there is a ship");
            return true;
        }
        else {
            System.out.println("no ship");
            return false;
        }
        
    }
    
    //Ελέχει αν το πλοίο που τυχόν υ[άρχει στη θέση αυτή έχει χτυπηθεί εκεί
    public boolean isHit()
    {
        if (ship == true && marked == true) {
            System.out.println("the ship has been hitten");
            return true;
        }
        else {
            System.out.println("the ship has not been hitten");
            return false;
        }
        
    }
}

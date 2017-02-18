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

public class AircraftCarrier extends Ship {
    
    public AircraftCarrier(String letter, int length, int points, Field field, String dir, int[] start){
        super(letter, length, points, field, dir, start);
    }
    
    public String getSinkMessage(){
        return "Aircraft Carrier sank!";
    }
}


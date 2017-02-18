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

import java.util.InputMismatchException;

public enum ShipDirection {
    HORIZONTAL, VERTICAL;
    
    public static ShipDirection fromString(String dirString){
        if ("h".equals(dirString)){
            return HORIZONTAL;
        }
        if ("v".equals(dirString)){
            return VERTICAL;
        }
        else{
            try{
                throw new InputMismatchException(); // causes an exception
            }
            catch(Exception e){
                System.exit(0);
                return null;
            }
        }
    }
}


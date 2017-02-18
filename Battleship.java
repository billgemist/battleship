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

public class Battleship {

    public static void main(String[] args) {
        System.out.println("| BATTLESHIPS |\n");
        Game Battleship = new Game();
        
        Battleship.init();
        Battleship.play();
    }
}

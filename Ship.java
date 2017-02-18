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

public abstract class Ship {

    public String letter;  //Αρχικό γράμμα πλοίου
    public int length;  //Μήκος πλοίου
    public int points;  //Πόντοι πλοίου
    public Field field;  //Πεδίο πλοίου
   // private int start;  //Πλώρη πλοίου 
    public int[] start = new int[2];
    public String dir;  //Κατεύθυνση πλοίου
    public int hitCount;

    public Ship(String letter, int length, int points, Field field, String dir, int[] start) {
        this.letter = letter;
        this.length = length;
        this.points = points;
        this.field = field;
        this.dir = dir;
        this.start = start;
    }

    //Καλείται όταν χτυπηθεί μια θέση του πλοίου
    public void hit() {
        hitCount++;
    }

    //Ελέγχει αν το πλοίο έχει χτυπηθεί (Τουλάχιστον μια θέση)
    public boolean isHit(String dirString) {
        return (hitCount > 0);
    }

    //Ελέγχει αν το πλοίο έχει βυθιστεί(Όλες οι θέσεις χτυπημένες)
    //Επιστρέφει true εφόσον το πλοίο έχει βυθιστεί, δηλαδή όταν τα
    //χτυπήματα γίνουν ίσα με το μήκος του πλοίου
    public boolean isSinking() {
        return (hitCount == length);
    }

    //Μήνυμα ότι χτυπήθηκε πλοίο
    public String getHitMessage() {
        return "Ship hit! Well done.";
    }

    //Απειλή πλοίου. Γίνονται οι κατάλληλες ενέργειες
    public void threaten() {
    }
}

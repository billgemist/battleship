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

public class Field {

    int rows;
    int cols;
    public String[][] gameBoard; //Ταμπλό του παίχτη
    String name;

    public Field(int rows, int cols, String name, String[][] gameBoard) {
        this.rows = rows;
        this.cols = cols;
        this.gameBoard = gameBoard;
        this.name = name;
    }

    public String getLocation(int r, int c) {
//        return ("" + (char)(65+(r-1)) + c );  
        return (gameBoard[r][c]);
    }

    public void getLocation(String locString) {
    }

    public boolean placeShipRandomly(Ship s, int maxTries, boolean checkMarked) {
        boolean decisionRandom = false;
        if ("HORIZONTAL".equals(s.dir)) {
            int i = s.start[0];
            for (int j = s.start[1]; j > (s.start[1] - s.length); j--) {
                if ((j >= 0) || (j <= cols)) {
                    if ((".".equals(gameBoard[i][j]))) {
                        gameBoard[i][j] = s.letter;
                        System.out.println(s.letter);
                        decisionRandom = true;
                    } else {
                        System.out.println("No available position");
                        return false;
                    }
                } else {
                    System.out.println("Out of array bounds");
                    return false;
                }
            }
        }

        if ("VERTICAL".equals(s.dir)) {

            int j = s.start[1];
            for (int i = s.start[0]; i < (s.start[0] + s.length); i++) {
                if ((i >= 0) || (i <= rows)) {
                    if (".".equals(gameBoard[i][j])) {
                        gameBoard[i][j] = s.letter;
                        decisionRandom = true;
                    } else {
                        System.out.println("No available position");
                        return false;
                    }
                } else {
                    System.out.println("Out of array bounds");
                    return false;
                }
            }

        }
        return decisionRandom;


    }

    public boolean placeShip(Ship s, boolean checkMarked) {
        boolean decision = false;
        if ("HORIZONTAL".equals(s.dir)) {
            int i = s.start[0];
            for (int j = s.start[1]; j > (s.start[1] - s.length); j--) {
                if ((j >= 0) || (j <= cols)) {
                    if ((".".equals(gameBoard[i][j]))) {
                        gameBoard[i][j] = s.letter;
                        System.out.println(s.letter);
                        decision = true;
                    } else {
                        System.out.println("No available position");
                        return false;
                    }
                } else {
                    System.out.println("Out of array bounds");
                    return false;
                }
            }
        }

        if ("VERTICAL".equals(s.dir)) {
          
            int j = s.start[1];
            for (int i = s.start[0]; i < (s.start[0] + s.length); i++) {
                if ((i >= 0) || (i <= rows)) {
                    if (".".equals(gameBoard[i][j])) {
                        gameBoard[i][j] = s.letter;
                        decision = true;
                    } else {
                        System.out.println("No available position");
                        return false;
                    }
                } else {
                    System.out.println("Out of array bounds");
                    return false;
                }
            }

        }
        return decision;

    }

    public void removeShip(Ship s) {
    }

    public void processValidMove(Location moveLoc) {
    }


    public void toStringWithShips() {
    }
}

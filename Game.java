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

import java.util.Random;
import java.util.Scanner;

public class Game {

    private int choice1;//Είδος παίχτη
    private int choice2;//Είδος παίχτη
    private int choice3;//Είδος παιχνιδιού
    private int putShipsP1;//Τοποθέτηση πλοίων
    private int putShipsP2;//Τοποθέτηση πλοίων
    private int row = 0;//Γραμμες του ταμπλό
    private int col = 0;//Στήλες του ταμπλό
    private String directionP1; // ship direction for player's 1 ships
    private String directionP2; // ship direction for player's 2 ships
    private String bowP1; // start of the ship, player 1
    private String bowP2; // start of the ship, player 2
    public int[] start = new int[2]; // array to show the start(bow) of the ship
    private String letterCommand; // keeps the letter of the command tha the user gives 
    private String numberCommand; // keeps the letter of the command tha the user gives 
    private boolean positionAvailabillity; // true for a clean position and false for a marked
    Random rnd = new Random();
    Scanner input = new Scanner(System.in);
    
    private Player player1;
    private Player player2;
    
    private int totalMovements;
    private Player currentTurn;


    //Αρχικοποίηση
    public void init() {
        //Διάβασμα αριθμού στηλών και γραμμών από το χρήστη
        do {
            System.out.print("Rows of Field:");
            row = input.nextInt();
        } while (row < 10 || row > 15);

        do {
            System.out.print("Columns of Field:");
            col = input.nextInt();
        } while (col < 10 || col > 15);

//        //Δημιουργία ταμπλό
//        createBoard(row,col);

        //Είδος αντιπάλων: Αντίπαλος υπολογιστής ή άνθρωπος
        do {
            System.out.println("\nKind of First Player:");
            System.out.println(" 1. Human");
            System.out.println(" 2. Computer");
            System.out.print("> ");
            choice1 = input.nextInt();
        } while (choice1 < 1 || choice1 > 2);

        do {
            System.out.println("\nKind of Second Player:");
            System.out.println(" 1. Human");
            System.out.println(" 2. Computer");
            System.out.print("> ");
            choice2 = input.nextInt();
        } while (choice2 < 1 || choice2 > 2);

        //Human VS Human
        if (choice1 == 1 && choice2 == 1) {
            System.out.println("\nHuman VS Human\n");
            System.out.print("Name for Human 1:");
            player1 = new PlayerHuman(input.next());//Human
            System.out.print("Name for Human 2:");
            player2 = new PlayerHuman(input.next());//Human
        }

        //Human VS Computer
        if (choice1 == 1 && choice2 == 2 || choice1 == 2 && choice2 == 1) {
            System.out.println("\nHuman VS Computer\n");
            System.out.print("Name for Human:");
            player1 = new PlayerHuman(input.next());//Human
            System.out.print("Name for Computer:");
            player2 = new PlayerComputer(input.next());//Computer
        }

        //Computer VS Computer
        if (choice1 == 2 && choice2 == 2) {
            System.out.println("\nComputer VS Computer\n");
            System.out.print("Name for Computer 1:");
            player1 = new PlayerComputer(input.next());//Computer
            System.out.print("Name for Computer 2:");
            player2 = new PlayerComputer(input.next());//Computer
        }

        //Επιλογή τέλους παιχνιδιού
        System.out.println("\nChoose way to end the game:");
        System.out.println(" 1. At specific moves");
        System.out.println(" 2. When all the ships sink");
        System.out.print("> ");
        choice3 = input.nextInt();


        // calling the init method to create the boards
        player1.initField(row, col);
        player2.initField(row, col);

        // creating two obj of field class 
        Field fieldPlayer1 = new Field(row, col, player1.getName(), player2.gameBoard); // player 1 field
        Field fieldPlayer2 = new Field(row, col, player2.getName(), player1.gameBoard); // player 2 field

        //prints the two game boards
        player1.printGameBoard(row, col);
        player2.printGameBoard(row, col);

        if (choice1 == 1) {
            do {
                System.out.println("Player1 : Choose how to put the ships on the battlefield");
                System.out.println("1. Manually, put your ships at your own");
                System.out.println("2. Automatically, putting your ships randomly");
                System.out.print("> ");
                putShipsP1 = input.nextInt();
            } while (putShipsP1 < 1 || putShipsP1 > 2);
        } else {
            System.out.println("Computer player: placing the ships randomly");
            putShipsP1 = 2;
        }

        if (choice2 == 1) {
            do {
                System.out.println("Player 2 : Choose how to put the ships on the battlefield");
                System.out.println("1. Manually, put your ships at your own");
                System.out.println("2. Automatically, putting your ships randomly");
                System.out.print("> ");
                putShipsP2 = input.nextInt();
            } while (putShipsP2 < 1 || putShipsP2 > 2);
        } else {
            System.out.println("Computer player: placing the ships randomly");
            putShipsP2 = 2;
        }


        // player 1 putting the ships at his own
        if (putShipsP1 == 1) {

            // 1st AircraftCarrier
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st AircraftCarrier");
                System.out.println("give the start of the ship");
                System.out.print(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                AircraftCarrier p1AircraftCarrier1 = new AircraftCarrier("A", 5, 5, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1AircraftCarrier1, true); // method checking for availabillity of the position and putting the ship to the board
                
            } while (positionAvailabillity == false);

            // 2nd AircraftCarrier
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd AircraftCarrier");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                AircraftCarrier p1AircraftCarrier2 = new AircraftCarrier("A", 5, 5, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1AircraftCarrier2, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 1st Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                Destroyer p1Destroyer3 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1Destroyer3, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 2nd Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                Destroyer p1Destroyer2 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1Destroyer2, true);// method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            // 3rd Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("3rd Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                Destroyer p1Destroyer3 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1Destroyer3, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            // 1st Submarine
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st Submarine");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                Submarine p1Submarine1 = new Submarine("S", 1, 3, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1Submarine1, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 2nd Submarine
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd Submarine");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP1 = input.next();
                letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP1 = input.next();
                directionP1 = directionP1.toUpperCase();
                Submarine p1Sumbarine2 = new Submarine("S", 1, 3, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShip(p1Sumbarine2, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

        }

        //Player1 placing ships random
        if (putShipsP1 == 2) {
            //1st AircraftCarrier 
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                AircraftCarrier p1AircraftCarrier1 = new AircraftCarrier("A", 5, 5, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1AircraftCarrier1, 0, true); // method checking for availabillity of the position and putting the ship to the board

            } while (positionAvailabillity == false);


            //2nd AircraftCarrier 
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                AircraftCarrier p1AircraftCarrier2 = new AircraftCarrier("A", 5, 5, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1AircraftCarrier2, 0, true); // method checking for availabillity of the position and putting the ship to the board

            } while (positionAvailabillity == false);

            //1st Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                Destroyer p1Destroyer1 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1Destroyer1, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //2nd Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                Destroyer p1Destroyer2 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1Destroyer2, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //3rd Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                Destroyer p1Destroyer3 = new Destroyer("D", 3, 2, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1Destroyer3, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //1st Submarine
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                Submarine p1Submarine1 = new Submarine("S", 1, 3, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1Submarine1, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //1nd Submarine
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP1 = "HORIZONTAL";
                } else {
                    directionP1 = "VERTICAL";
                }

                Submarine p1Submarine2 = new Submarine("S", 1, 3, fieldPlayer1, directionP1, start);
                positionAvailabillity = fieldPlayer1.placeShipRandomly(p1Submarine2, 0, true);// method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

        }

        ////////////////////////////////////////////////////////////////////

        // Player 2 putting the ships at his own
        if (putShipsP2 == 1) {

            // 1st AircraftCarrier
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st AircraftCarrier");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                AircraftCarrier p2AircraftCarrier1 = new AircraftCarrier("A", 5, 5, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2AircraftCarrier1, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            // 2nd AircraftCarrier
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd AircraftCarrier");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                AircraftCarrier p2AircraftCarrier2 = new AircraftCarrier("A", 5, 5, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2AircraftCarrier2, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 1st Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                Destroyer p2Destroyer1 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2Destroyer1, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 2nd Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[1] = stringToInt(letterCommand);
                start[2] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                Destroyer p2Destroyer2 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2Destroyer2, true);// method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            // 3rd Destroyer
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("3rd Destroyer");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                Destroyer p2Destroyer3 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2Destroyer3, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            // 1st Submarine
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("1st Submarine");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                Submarine p2Sumbarine1 = new Submarine("S", 1, 3, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2Sumbarine1, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);


            // 2nd Submarine
            do {
                System.out.println("You have to put the following ships: two aircraftcarriers, three destroyers, two submarines");
                System.out.println("2nd Submarine");
                System.out.println("give the start of the ship");
                System.out.println(">");
                bowP2 = input.next();
                letterCommand = bowP2.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
                numberCommand = bowP2.substring(1); // user gives "A14" this line keeps the number only as string
                start[0] = stringToInt(letterCommand);
                start[1] = stringNumberToInt(numberCommand);

                System.out.println("Give the ship direction: Vertical or Horizontal");
                System.out.println(">");
                directionP2 = input.next();
                directionP2 = directionP2.toUpperCase();
                Submarine p2Sumbarine2 = new Submarine("S", 1, 3, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShip(p2Sumbarine2, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

        }

        //Player2 placing ships random
        if (putShipsP2 == 2) {
            //1st AircraftCarrier 
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;
                System.out.println("dsf");

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                AircraftCarrier p2AircraftCarrier1 = new AircraftCarrier("A", 5, 5, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2AircraftCarrier1, 0, true); // method checking for availabillity of the position and putting the ship to the board

            } while (positionAvailabillity == false);


            //2nd AircraftCarrier 
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                AircraftCarrier p2AircraftCarrier2 = new AircraftCarrier("A", 5, 5, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2AircraftCarrier2, 0, true); // method checking for availabillity of the position and putting the ship to the board

            } while (positionAvailabillity == false);

            //1st Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                Destroyer p2Destroyer1 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2Destroyer1, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //2nd Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                Destroyer p2Destroyer2 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2Destroyer2, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //3rd Destroyer
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                Destroyer p2Destroyer3 = new Destroyer("D", 3, 2, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2Destroyer3, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //1st Submarine
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                Submarine p2Submarine1 = new Submarine("S", 1, 3, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2Submarine1, 0, true); // method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

            //1nd Submarine
            do {
                int k = rnd.nextInt(row - 2); // 
                int l = rnd.nextInt(col - 2); //
                start[0] = k;
                start[1] = l;

                int m = rnd.nextInt(1);
                if (m == 1) {
                    directionP2 = "HORIZONTAL";
                } else {
                    directionP2 = "VERTICAL";
                }

                Submarine p2Submarine2 = new Submarine("S", 1, 3, fieldPlayer2, directionP2, start);
                positionAvailabillity = fieldPlayer2.placeShipRandomly(p2Submarine2, 0, true);// method checking for availabillity of the position and putting the ship to the board
            } while (positionAvailabillity == false);

        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.println(fieldPlayer2.gameBoard[i][j]);
            }
        }

        player1.gameBoard = fieldPlayer2.gameBoard;
        player2.gameBoard = fieldPlayer1.gameBoard;
        //prints the two game boards
        player1.printGameBoard(row, col);
        player2.printGameBoard(row, col);
    }

    //Τοποθέτηση πλοίων
    public void placeShips() {
    }

    //Κυρίως παιχνίδι
    public void play() {
        
        boolean gameOver = false;
        int numShots = 0;
        
        Random rnd = new Random();
       
        //Τυχαία τιμή για την επιλογή πρώτου παίχτη.
        int k = rnd.nextInt(2);
        
        System.out.println(k);
        if( k == 0 ){
            System.out.println("First Player: " + player1.getName());
            currentTurn = player1;
        }
        else{
            System.out.println("First Player: " + player2.getName());
            currentTurn = player2;
        }
        int t=0;//Βοηθητική μεταβλητη για καταμέτρηση των κινήσεων
        
        while(!gameOver){
            player1.printGameBoard(row, col);
            player2.printGameBoard(row, col);
            
            System.out.println(currentTurn.getName() + " give a shot:");
            String shot = input.next();
            //Μετατροπές
            letterCommand = bowP1.substring(0, 1).toUpperCase(); // user gives "A14", and this line keeps the A and if it's 'a' turn it on 'A'
            numberCommand = bowP1.substring(1); // user gives "A14" this line keeps the number only as string
            start[0] = stringToInt(letterCommand);
            start[1] = stringNumberToInt(numberCommand);
            
            //Εδώ πρέπει να κληθεί η κλάση Location για κατάλληλες ενέργειες.
            t++;
            if(t % 2 == 0){
               totalMovements--;
            }
            
            if (totalMovements == 0){
                gameOver = true;
            }
            
        }
    }

    //Τέλος παιχνιδιού
    public void showResult() {
    }

    public int stringToInt(String s) {
        if (s.equals("A")) {
            return 0;
        }
        if (s.equals("B")) {
            return 1;
        }
        if (s.equals("C")) {
            return 2;
        }
        if (s.equals("D")) {
            return 3;
        }
        if (s.equals("E")) {
            return 4;
        }
        if (s.equals("F")) {
            return 5;
        }
        if (s.equals("G")) {
            return 6;
        }
        if (s.equals("H")) {
            return 7;
        }
        if (s.equals("I")) {
            return 8;
        }
        if (s.equals("J")) {
            return 9;
        }
        if (s.equals("K")) {
            return 10;
        }
        if (s.equals("L")) {
            return 11;
        }
        if (s.equals("M")) {
            return 12;
        }
        if (s.equals("N")) {
            return 13;
        }
        if (s.equals("O")) {
            return 14;
        }
        return 0;
    }

    public int stringNumberToInt(String s) {
        if (s.equals("1")) {
            return 0;
        }
        if (s.equals("2")) {
            return 1;
        }
        if (s.equals("3")) {
            return 2;
        }
        if (s.equals("4")) {
            return 3;
        }
        if (s.equals("5")) {
            return 4;
        }
        if (s.equals("6")) {
            return 5;
        }
        if (s.equals("7")) {
            return 6;
        }
        if (s.equals("8")) {
            return 7;
        }
        if (s.equals("9")) {
            return 8;
        }
        if (s.equals("10")) {
            return 9;
        }
        if (s.equals("11")) {
            return 10;
        }
        if (s.equals("12")) {
            return 11;
        }
        if (s.equals("13")) {
            return 12;
        }
        if (s.equals("14")) {
            return 13;
        }
        if (s.equals("15")) {
            return 14;
        }
        return 0;
    }
}

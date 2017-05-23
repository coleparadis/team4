import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        int[][] playerBoard = new int[10][10]; // 0 = EMPTY, 1 = MISSED_SHOT, 2 = SHIP
        int[][] enemyBoard = new int[10][10];
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have these ships:");
        System.out.println("Ship       Size");
        System.out.println("Carrier    5");
        System.out.println("Battleship 4");
        System.out.println("Cruiser    3");
        System.out.println("Submarine  2");
        System.out.println("Cruiser    1");
        String[] ships = {"Carrier","Battleship","Cruiser","Submarine","Cruiser"}; // Ships available to place
        int[] sizes = {5, 4, 3, 2, 1}; // Size of each ship
        for (int i=0; i<5; i++) {
            int x = 0; // X Coordinate of ship
            int y = 0; // Y Coordinate of ship
            String direction = ""; // Direction of ship
            boolean validPlacement = false;
            while (!validPlacement) {
                System.out.printf("Where would you like to place your %s? (Please enter starting position [A1-J10]): \n", ships[i]); // The starting coordinate of the ship
                boolean gotLocation = false;
                while (!gotLocation) {
                    String startingLocation = scanner.next();
                    x = startingLocation.substring(0, 1).toLowerCase().charAt(0) - 'a' + 1; // Grabs x coordinate from the input | (-'a'+1) turns the letter into 1-10
                    y = Integer.parseInt(startingLocation.substring(1)); // Grabs y coordinate from the input
                    if ((x <= 10 && x >= 0) && (y <= 10 && y >= 0)) {
                        gotLocation = true;
                    } else {
                        System.out.println("Invalid position. Please enter a coordinate from A1-J10: ");
                    }
                }
                System.out.printf("Please enter the direction of the %s (vertical[v] or horizontal[h]): \n", ships[i]);
                boolean gotDirection = false;
                while (!gotDirection) {
                    direction = scanner.next();
                    if (direction.equals("v") || direction.equals("h")) {
                        gotDirection = true;
                    } else {
                        System.out.println("Invalid direction. Please enter either v or h: ");
                    }
                }
                // Checks if the placement can fit on the board
                validPlacement = true;
                if (direction.equals("v")) {
                    if (!(x>=0 && x<=9)) {
                        validPlacement = false;
                    }
                    if (!(y+sizes[i]>=0 && y+sizes[i]<=9)) {
                        validPlacement = false;
                    }
                } else if (direction.equals("h")) {
                    if (!(x+sizes[i]>=0 && x+sizes[i]<=9)) {
                        validPlacement = false;
                    }
                    if (!(y>=0 && y<=9)) {
                        validPlacement = false;
                    }
                }
                // Checks if other ships are in the way
                for (int i2=0; i2<sizes[i]; i2++) {
                    if (direction.equals("v")) {
                        if (!(playerBoard[x][y+i2]==0)) {
                            validPlacement = false;
                            System.out.printf("%s cannot be placed there. \n", ships[i]);
                            break;
                        }
                    } else if (direction.equals("h")) {
                        if (!(playerBoard[x+i2][y]==0)) {
                            validPlacement = false;
                            System.out.printf("%s cannot be placed there. \n", ships[i]);
                            break;
                        }
                    }
                }
            }
            // Place ship onto board (once it has been verified as a valid placement)
            for (int i3=0; i3<sizes[i]; i3++) {
                if (direction.equals("v")) {
                    playerBoard[x][y+i3]=2;
                } else if (direction.equals("h")) {
                    playerBoard[x+i3][y]=2;
                }
            }
        }
    }

}

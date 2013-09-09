import java.util.Scanner;

public class Main {

    /**
     * Initialize a scanner object.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Initialize the dices.
     */
    protected static Dices dices = new Dices();

    public static void main(String[] args) {
        // Create an array of players.
        Player[] players = createPlayers(getAmountOfPlayers());

        int round = 1;

        while (round <= 15) {
            for (int pid = 0; pid < players.length; pid++) {
                // Define the active player.
                Player activePlayer = players[pid];

                // Show the player header.
                activePlayer.printHeader();

                // Throw the dices and show them.
                dices.throwAll();
                System.out.println(dices.toString());

                // Let the user re-roll the dices.
                reRoll(activePlayer);

                // Let the user choose what rule to add the points to.
                while (!chooseRule(activePlayer));

            }

            round++;
        }

        System.out.println("");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + " got "
                    + players[i].getScore() + " points.");
        }
        Main.scanner.close();

    }

    /**
     * Ask how many players the user want.
     */
    public static int getAmountOfPlayers() {
        int players;
        while (true) {
            try {
                System.out.println("How many players?");
                players = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                continue;
            }
        }
        return players;
    }

    /**
     * Create players.
     * 
     * @return An array of players.
     */
    public static Player[] createPlayers(int quantity) {
        Player[] players = new Player[quantity];

        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter name of player " + (i + 1) + ": ");
            // @TODO: Validate that we have a name??
            String playerName = scanner.nextLine().trim();
            players[i] = new Player(playerName);
        }

        return players;
    }

    /**
     * Handle re-rolls.
     * 
     * @param activePlayer
     *            The active player object.
     */
    public static void reRoll(Player activePlayer) {
        int reRolls = 2;
        while (reRolls > 0) {
            System.out.print("Enter the numbers of the dices you "
                    + " want to re-roll:");
            String reRollDicesStr = scanner.nextLine().trim();

            // Brake if the user just press enter.
            if (reRollDicesStr.length() < 1) {
                break;
            }

            // Slip the string with the dice numbers to re-roll.
            String[] reRollDices = reRollDicesStr.split(" ");

            for (int i = 0; i < reRollDices.length; i++) {
                int dieNumber = Integer.parseInt(reRollDices[i]) - 1;

                try {
                    dices.throwOne(dieNumber);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The die with index " + dieNumber
                            + " does not exists.");
                }
            }

            System.out.println(dices.toString());

            reRolls--;
        }
    }

    /**
     * Let the user choose what rule to add points to.
     * 
     * @param activePlayer
     *            A player object with the active player.
     * @return True on success, else false.
     */
    public static boolean chooseRule(Player activePlayer) {
        try {
            System.out.println(Rules.choices(activePlayer));
            System.out.println("Choose what rule to use:");

            String choice = scanner.nextLine();

            int intChoice;

            // Check the input, if it fails to parse it as an int, return false.
            try {
                intChoice = Integer.parseInt(choice) - 1;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a valid rule.");
                return false;
            }

            switch (intChoice) {
                case 0:
                    activePlayer.setPoint(intChoice, Rules.ones());
                    break;
                case 1:
                    activePlayer.setPoint(intChoice, Rules.twos());
                    break;
                case 2:
                    activePlayer.setPoint(intChoice, Rules.threes());
                    break;
                case 3:
                    activePlayer.setPoint(intChoice, Rules.fours());
                    break;
                case 4:
                    activePlayer.setPoint(intChoice, Rules.fives());
                    break;
                case 5:
                    activePlayer.setPoint(intChoice, Rules.sixes());
                    break;
                case 6:
                    activePlayer.setPoint(intChoice, Rules.onePair());
                    break;
                case 7:
                    activePlayer.setPoint(intChoice, Rules.twoPair());
                    break;
                case 8:
                    activePlayer.setPoint(intChoice, Rules.threeOfKind());
                    break;
                case 9:
                    activePlayer.setPoint(intChoice, Rules.fourOfKind());
                    break;
                case 10:
                    activePlayer.setPoint(intChoice, Rules.smallStraight());
                    break;
                case 11:
                    activePlayer.setPoint(intChoice, Rules.largeStraight());
                    break;
                case 12:
                    activePlayer.setPoint(intChoice, Rules.fullHouse());
                    break;
                case 13:
                    activePlayer.setPoint(intChoice, Rules.chance());
                    break;
                case 14:
                    activePlayer.setPoint(intChoice, Rules.yahtzee());
                    break;
                default:
                    System.out.println("That is not a valid rule.");
                    return false;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}

import java.util.Arrays;

public class Rules {

    /**
     * Calculate the sum of all dices with a value of [value].
     * 
     * @param value
     *            the value to check against.
     * 
     * @return A sum of points.
     */
    public static int sumBasic(int value) {
        // Get the dices.
        int[] dices = Main.dices.getDices();

        int sum = 0;
        for (int i = 0; i < dices.length; i++) {
            if (dices[i] == value) {
                sum += dices[i];
            }
        }
        return sum;
    }

    public static int ones() {
        return sumBasic(1);
    }

    public static int twos() {
        return sumBasic(2);
    }

    public static int threes() {
        return sumBasic(3);
    }

    public static int fours() {
        return sumBasic(4);
    }

    public static int fives() {
        return sumBasic(5);
    }

    public static int sixes() {
        return sumBasic(6);
    }

    public static int onePair() {
        // Get the dices.
        int[] dices = Main.dices.getDices();
        Arrays.sort(dices);

        for (int i = dices.length - 1; i > 0; i--) {
            if (dices[i] == dices[i - 1]) {
                return dices[i] + dices[i - 1];

            }
        }
        return 0;
    }

    public static int twoPair() {
        // Get the dices.
        int[] dices = Main.dices.getDices();
        Arrays.sort(dices);
        int sum = 0;

        for (int i = dices.length - 1; i > 0; i--) {

            if (fourOfKind() == 0) {
                if (dices[i] == dices[i - 1]) {
                    sum = dices[i] + dices[i - 1];
                    if (i > 2) {
                        for (int j = i - 2; j > 0; j--) {
                            if (dices[j] == dices[j - 1]) {
                                return sum + dices[j] + dices[j - 1];
                            }
                        }
                    }

                }
            }
        }
        return 0;
    }

    public static int threeOfKind() {
        // Get the dices.
        int[] dices = Main.dices.getDices();
        Arrays.sort(dices);

        for (int i = dices.length - 1; i > 1; i--) {
            if (dices[i] == dices[i - 1] && dices[i] == dices[i - 2]) {
                return dices[i] + dices[i - 1] + dices[i - 2];

            }
        }
        return 0;
    }

    public static int fourOfKind() {
        // Get the dices.
        int[] dices = Main.dices.getDices();
        Arrays.sort(dices);

        for (int i = dices.length - 1; i > 2; i--) {
            if (dices[i] == dices[i - 1] && dices[i] == dices[i - 2]
                    && dices[i] == dices[i - 3]) {
                return dices[i] + dices[i - 1] + dices[i - 2] + dices[i - 3];

            }
        }
        return 0;
    }

    public static int smallStraight() {

        // Get the dices.
        int[] dices = Main.dices.getDices();
        Arrays.sort(dices);
        int[] test = { 1, 2, 3, 4, 5 };
        boolean match = true;
        for (int i = 0; i < test.length; i++) {
            if (test[i] != dices[i]) {
                match = false;
            }
        }

        if (match) {
            return 15;
        }
        return 0;
    }

    public static int largeStraight() {
        // Get the dices.
        int[] dices = Main.dices.getDices();

        Arrays.sort(dices);
        int[] test = { 2, 3, 4, 5, 6 };
        boolean match = true;
        for (int i = 0; i < test.length; i++) {
            if (test[i] != dices[i]) {
                match = false;
            }
        }

        if (match) {
            return 20;
        }
        return 0;
    }

    public static int fullHouse() {
        if (onePair() > 0 && threeOfKind() > 0) {
            return onePair() + threeOfKind();
        }

        return 0;
    }

    public static int chance() {
        // Get the dices.
        int[] dices = Main.dices.getDices();

        int sum = 0;
        for (int i = 0; i < dices.length; i++) {
            sum += dices[i];
        }

        return sum;
    }

    public static int yahtzee() {
        // Get the dices.
        int[] dices = Main.dices.getDices();

        Arrays.sort(dices);
        if (dices[0] == dices[4]) {
            return 50;
        }

        return 0;

    }

    /**
     * Construct the choice table.
     * 
     * @param player
     *            A player object.
     * @return A string with the choice table.
     */
    public static String choices(Player player) {
        String grid = "";

        String[] choices = { "Aces", "Twos", "Threes", "Fours", "Fives",
                "Sixes", "One pair", "Two pair", "Three of kind",
                "Four of kind", "Small straight", "Large straight",
                "Full house", "Chance", "Yahtzee" };

        int[] points = { ones(), twos(), threes(), fours(), fives(), sixes(),
                onePair(), twoPair(), threeOfKind(), fourOfKind(),
                smallStraight(), largeStraight(), fullHouse(), chance(),
                yahtzee() };

        for (int i = 0; i < choices.length; i++) {
            if (player.getScorecard()[i] == null) {
                grid += (i + 1) + "\t add " + points[i] + " points to "
                        + choices[i] + "\n";
            }
        }

        return grid;
    }
}

import java.util.Random;

public class Dices {

    /**
     * An array of dices.
     */
    private int[] dices = new int[5];

    /**
     * A Random object.
     */
    private static Random random = new Random();

    /**
     * @return An array with all the dices.
     */
    public int[] getDices() {
        return this.dices;
    }

    /**
     * Throw all the dices.
     */
    public void throwAll() {
        for (int i = 0; i < this.dices.length; i++) {
            dices[i] = (random.nextInt(6) + 1);
        }
    }

    /**
     * Throw one die.
     * 
     * @param index
     *            The die index.
     */
    public void throwOne(int index) {
        dices[index] = (random.nextInt(6) + 1);
    }

    /**
     * @return Returns a string representation of the object.
     */
    public String toString() {
        String output = "\t\t";

        for (int i = 0; i < dices.length; i++) {
            output += " _ \t";
        }

        output += "\nDice value:\t";

        for (int i = 0; i < dices.length; i++) {
            output += "|" + this.dices[i] + "|\t";
        }

        output += "\n\t\t";

        for (int i = 0; i < dices.length; i++) {
            output += " ¯ \t";
        }

        output += "\nDice number:\t";

        for (int i = 0; i < dices.length; i++) {
            output += " " + (i + 1) + "\t";
        }

        return output + "\n";
    }

}

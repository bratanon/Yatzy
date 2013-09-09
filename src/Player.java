public class Player {

    /**
     * The palyer name.
     */
    private String name;

    private String genitiveName;

    private Integer[] scorecard = new Integer[15];

    public Player(String name) {
        this.setName(name);
    }

    /**
     * Get player name.
     */
    private void setName(String name) {
        // Set regular name.
        this.name = name;

        boolean addGenitiv = !name.endsWith("s") && !name.endsWith("z")
                && !name.endsWith("x");

        // Set genetive name.
        this.genitiveName = (addGenitiv) ? name + "s" : name;
    }

    /**
     * Get player name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get player name in genitive.
     */
    public String getGenitiveName() {
        return this.genitiveName;
    }

    /**
     * Print the player header.
     */
    public void printHeader() {
        System.out.println("\n");
        System.out.println("=================================================");
        System.out.println(this.getGenitiveName() + " turn");
        System.out.println("=================================================");
    }

    /**
     * Get the scorecard.
     * 
     * @return An array filled with points.
     */
    public Integer[] getScorecard() {
        return this.scorecard;
    }

    public void setPoint(int i, int points) throws Exception {
        if (this.scorecard[i] == null) {
            this.scorecard[i] = points;
        }
        else {
            throw new Exception("That rules is already taken.");
        }
    }

    public int getSum() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += scorecard[i];
        }

        return sum;
    }

    public int getBonus() {
        if (getSum() >= 63) {
            return 50;
        }
        else {
            return 0;
        }
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < scorecard.length; i++) {
            score += scorecard[i];
        }

        score += getBonus();

        return score;
    }

}

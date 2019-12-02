import java.io.Serializable;

/**
 * Class to hold Player data, like name, options chosen, and score.
 */
public class Player implements Comparable, Serializable {

    /** Player's name for viewing on GUI. */
    private String name;

    /** To tell if it is player's turn. */
    private boolean turn;

    /** If aces scoring option has been chosen. */
    private boolean acesChosen;

    /** If twos scoring option has been chosen. */
    private boolean twosChosen;

    /** If threes scoring option has been chosen. */
    private boolean threesChosen;

    /** If fours scoring option has been chosen. */
    private boolean foursChosen;

    /** If fives scoring option has been chosen. */
    private boolean fivesChosen;

    /** If sixes scoring option has been chosen. */
    private boolean sixesChosen;

    /** If small straight scoring option has been chosen. */
    private boolean smallStraightChosen;

    /** If large straight scoring option has been chosen. */
    private boolean largeStraightChosen;

    /** If full house scoring option has been chosen. */
    private boolean fullHouseChosen;

    /** If three of a kind scoring option has been chosen. */
    private boolean threeOfAKindChosen;

    /** If four of a kind scoring option has been chosen. */
    private boolean fourOfAKindChosen;

    /** If chance scoring option has been chosen. */
    private boolean chanceChosen;

    /** If yahtzee scoring option has been chosen. */
    private boolean yahtzeeChosen;

    /** If player is an AI. */
    private boolean isAI;

    /** Keep track of player's score. */
    private int totalScore = 0;

    /** Keep track of number of Yahtzees rolled for bonus scoring. */
    private int yahtzeeRolls;

    /**
     * Default constructor that sets name to parameter.
     *
     * @param newName name of player
     */
    public Player(final String newName) {
        this.name = newName;
        turn = false;
        acesChosen = false;
        twosChosen = false;
        threesChosen = false;
        foursChosen = false;
        fivesChosen = false;
        sixesChosen = false;
        fullHouseChosen = false;
        smallStraightChosen = false;
        largeStraightChosen = false;
        yahtzeeChosen = false;
        chanceChosen = false;
        threesChosen = false;
        fourOfAKindChosen = false;
        totalScore = 0;
        yahtzeeRolls = 0;
    }

    /**
     * Getter for number of yahtzee rolls.
     *
     * @return yahtzee rolls
     */
    public int getYahtzeeRolls() {
        return yahtzeeRolls;
    }

    /**
     * Setter for yahtzee rolls.
     *
     * @param newYahtzeeRolls yahtzee rolls to set
     */
    public void setYahtzeeRolls(int newYahtzeeRolls) {
        this.yahtzeeRolls = newYahtzeeRolls;
    }

    /**
     * Getter for name.
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param newName name to be set
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Getter for turn.
     *
     * @return true if player's turn
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * Setter for turn.
     *
     * @param newTurn boolean to set turn
     */
    public void setTurn(boolean newTurn) {
        this.turn = newTurn;
    }

    /**
     * Getter for aces chosen.
     *
     * @return true if aces has been chosen
     */
    public boolean isAcesChosen() {
        return acesChosen;
    }

    /**
     * Setter for aces chosen.
     *
     * @param newAcesChosen boolean to set aces chosen
     */
    public void setAcesChosen(boolean newAcesChosen) {
        this.acesChosen = newAcesChosen;
    }

    /**
     * Getter for twos chosen.
     *
     * @return true if twos chosen
     */
    public boolean isTwosChosen() {
        return twosChosen;
    }

    /**
     * Setter for twos chosen.
     *
     * @param newTwosChosen boolean to set twos chosen
     */
    public void setTwosChosen(boolean newTwosChosen) {
        this.twosChosen = newTwosChosen;
    }

    /**
     * Getter for threes chosen.
     *
     * @return true if threes chosen
     */
    public boolean isThreesChosen() {
        return threesChosen;
    }

    /**
     * Setter for threes chosen.
     *
     * @param newThreesChosen boolean to set threes chosen
     */
    public void setThreesChosen(boolean newThreesChosen) {
        this.threesChosen = newThreesChosen;
    }

    /**
     * Getter for fours chosen.
     *
     * @return true if fours chosen
     */
    public boolean isFoursChosen() {
        return foursChosen;
    }

    /**
     * Setter for fours chosen.
     *
     * @param newFoursChosen boolean to set fours chosen
     */
    public void setFoursChosen(boolean newFoursChosen) {
        this.foursChosen = newFoursChosen;
    }

    /**
     * Getter for fives chosen.
     *
     * @return true if fives chosen
     */
    public boolean isFivesChosen() {
        return fivesChosen;
    }

    /**
     * Setter for fives chosen.
     *
     * @param newFivesChosen boolean to set fives chosen
     */
    public void setFivesChosen(boolean newFivesChosen) {
        this.fivesChosen = newFivesChosen;
    }

    /**
     * Getter for sixes chosen.
     *
     * @return true if sixes chosen
     */
    public boolean isSixesChosen() {
        return sixesChosen;
    }

    /**
     * Setter for sixes chosen.
     *
     * @param newSixesChosen boolean to set sixes chosen
     */
    public void setSixesChosen(boolean newSixesChosen) {
        this.sixesChosen = newSixesChosen;
    }

    /**
     * Setter for small straight chosen.
     *
     * @return true if small straight chosen
     */
    public boolean isSmallStraightChosen() {
        return smallStraightChosen;
    }

    /**
     * Setter for small straight chosen.
     *
     * @param newSmallStraightChosen boolean to set small straight chosen
     */
    public void setSmallStraightChosen(boolean newSmallStraightChosen) {
        this.smallStraightChosen = newSmallStraightChosen;
    }

    /**
     * Getter for large straight chosen.
     *
     * @return true if larges straight chosen
     */
    public boolean isLargeStraightChosen() {
        return largeStraightChosen;
    }

    /**
     * Setter for large straight chosen.
     *
     * @param newLargeStraightChosen boolean to set large straight chosen
     */
    public void setLargeStraightChosen(boolean newLargeStraightChosen) {
        this.largeStraightChosen = newLargeStraightChosen;
    }

    /**
     * Getter for full house chosen.
     *
     * @return true if full house chosen
     */
    public boolean isFullHouseChosen() {
        return fullHouseChosen;
    }

    /**
     * Setter for full house chosen.
     *
     * @param newFullHouseChosen boolean to set full house chosen
     */
    public void setFullHouseChosen(boolean newFullHouseChosen) {
        this.fullHouseChosen = newFullHouseChosen;
    }

    /**
     * Getter for three of a kind chosen.
     *
     * @return true if three of a kind chosen
     */
    public boolean isThreeOfAKindChosen() {
        return threeOfAKindChosen;
    }

    /**
     * Setter for three of a kind chosen.
     *
     * @param newThreeOfAKindChosen boolean to set three of a kind chosen
     */
    public void setThreeOfAKindChosen(boolean newThreeOfAKindChosen) {
        this.threeOfAKindChosen = newThreeOfAKindChosen;
    }

    /**
     * Getter for four of a kind chosen.
     *
     * @return true if four of a kind chosen
     */
    public boolean isFourOfAKindChosen() {
        return fourOfAKindChosen;
    }

    /**
     * Setter for four of a kind chosen.
     *
     * @param newFourOfAKindChosen boolean to set four of a kind chosen
     */
    public void setFourOfAKindChosen(boolean newFourOfAKindChosen) {
        this.fourOfAKindChosen = newFourOfAKindChosen;
    }

    /**
     * Getter for chance chosen.
     *
     * @return true if chance chosen
     */
    public boolean isChanceChosen() {
        return chanceChosen;
    }

    /**
     * Setter for chance chosen.
     *
     * @param newChanceChosen boolean to set chance chosen
     */
    public void setChanceChosen(boolean newChanceChosen) {
        this.chanceChosen = newChanceChosen;
    }

    /**
     * Getter for yahtzee chosen.
     *
     * @return true if yahtzee chosen
     */
    public boolean isYahtzeeChosen() {
        return yahtzeeChosen;
    }

    /**
     * Setter for yahtzee chosen.
     *
     * @param newYahtzeeChosen boolean to set yahtzee chosen
     */
    public void setYahtzeeChosen(boolean newYahtzeeChosen) {
        this.yahtzeeChosen = newYahtzeeChosen;
    }

    /**
     * Getter for total score.
     *
     * @return player's total score
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Setter for total score.
     *
     * @param newTotalScore int to set total score
     */
    public void setTotalScore(int newTotalScore) {
        this.totalScore = newTotalScore;
    }

    /**
     * Function to compare player objects.
     *
     * @param o player to compare to
     * @return player's total score
     */
    @Override
    public int compareTo(Object o) {
        return getTotalScore();
    }

    /**
     * Getter for if player is an ai.
     *
     * @return true if player is an ai
     */
    public boolean hasAI() {
        return isAI;
    }

    /**
     * Setter for if player is an ai.
     *
     * @param newMode boolean to set if player is an ai
     */
    public void setAI(boolean newMode) {
        isAI = newMode;
    }
}


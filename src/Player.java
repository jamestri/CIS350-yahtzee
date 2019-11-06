import java.io.Serializable;

/**
 * Class to hold Player data, like name, options chosen, and score
 */
public class Player implements Comparable, Serializable {
  private String name;
  private boolean turn,
      acesChosen,
      twosChosen,
      threesChosen,
      foursChosen,
      fivesChosen,
      sixesChosen,
      smallStraightChosen,
      largeStraightChosen,
      fullHouseChosen,
      threeOfAKindChosen,
      fourOfAKindChosen,
      chanceChosen,
      yahtzeeChosen;
  private int totalScore, yahtzeeRolls;

  /**
   * Default constructor that sets name to parameter
   * @param name name of player
   */
  public Player(String name) {
    this.name = name;
    turn =
        acesChosen =
            twosChosen =
                threesChosen =
                    foursChosen =
                        fivesChosen =
                            sixesChosen =
                                smallStraightChosen =
                                    largeStraightChosen =
                                        fullHouseChosen =
                                            threeOfAKindChosen =
                                                fourOfAKindChosen =
                                                    chanceChosen = yahtzeeChosen = false;

    totalScore = yahtzeeRolls = 0;
  }

  /**
   * Getter for number of yahtzee rolls
   * @return yahtzee rolls
   */
  public int getYahtzeeRolls() {
    return yahtzeeRolls;
  }

  /**
   * Setter for yahtzee rolls
   * @param yahtzeeRolls yahtzee rolls to set
   */
  public void setYahtzeeRolls(int yahtzeeRolls) {
    this.yahtzeeRolls = yahtzeeRolls;
  }

  /**
   * Getter for name
   * @return player name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for name
   * @param name name to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for turn
   * @return true if player's turn
   */
  public boolean isTurn() {
    return turn;
  }

  /**
   * Setter for turn
   * @param turn boolean to set turn
   */
  public void setTurn(boolean turn) {
    this.turn = turn;
  }

  /**
   * Getter for aces chosen
   * @return true if aces has been chosen
   */
  public boolean isAcesChosen() {
    return acesChosen;
  }

  /**
   * Setter for aces chosen
   * @param acesChosen boolean to set aces chosen
   */
  public void setAcesChosen(boolean acesChosen) {
    this.acesChosen = acesChosen;
  }

  /**
   * Getter for twos chosen
   * @return true if twos chosen
   */
  public boolean isTwosChosen() {
    return twosChosen;
  }

  /**
   * Setter for twos chosen
   * @param twosChosen boolean to set twos chosen
   */
  public void setTwosChosen(boolean twosChosen) {
    this.twosChosen = twosChosen;
  }

  /**
   * Getter for threes chosen
   * @return true if threes chosen
   */
  public boolean isThreesChosen() {
    return threesChosen;
  }

  /**
   * Setter for threes chosen
   * @param threesChosen boolean to set threes chosen
   */
  public void setThreesChosen(boolean threesChosen) {
    this.threesChosen = threesChosen;
  }

  /**
   * Getter for fours chosen
   * @return true if fours chosen
   */
  public boolean isFoursChosen() {
    return foursChosen;
  }

  /**
   * Setter for fours chosen
   * @param foursChosen boolean to set fours chosen
   */
  public void setFoursChosen(boolean foursChosen) {
    this.foursChosen = foursChosen;
  }

  /**
   * Getter for fives chosen
   * @return true if fives chosen
   */
  public boolean isFivesChosen() {
    return fivesChosen;
  }

  /**
   * Setter for fives chosen
   * @param fivesChosen boolean to set fives chosen
   */
  public void setFivesChosen(boolean fivesChosen) {
    this.fivesChosen = fivesChosen;
  }

  /**
   * Getter for sixes chosen
   * @return true if sixes chosen
   */
  public boolean isSixesChosen() {
    return sixesChosen;
  }

  /**
   * Setter for sixes chosen
   * @param sixesChosen boolean to set sixes chosen
   */
  public void setSixesChosen(boolean sixesChosen) {
    this.sixesChosen = sixesChosen;
  }

  /**
   * Setter for small straight chosen
   * @return true if small straight chosen
   */
  public boolean isSmallStraightChosen() {
    return smallStraightChosen;
  }

  /**
   * Setter for small straight chosen
   * @param smallStraightChosen boolean to set small straight chosen
   */
  public void setSmallStraightChosen(boolean smallStraightChosen) {
    this.smallStraightChosen = smallStraightChosen;
  }

  /**
   * Getter for large straight chosen
   * @return true if larges straight chosen
   */
  public boolean isLargeStraightChosen() {
    return largeStraightChosen;
  }

  /**
   * Setter for large straight chosen
   * @param largeStraightChosen boolean to set large straight chosen
   */
  public void setLargeStraightChosen(boolean largeStraightChosen) {
    this.largeStraightChosen = largeStraightChosen;
  }

  /**
   * Getter for full house chosen
   * @return true if full house chosen
   */
  public boolean isFullHouseChosen() {
    return fullHouseChosen;
  }

  /**
   * Setter for full house chosen
   * @param fullHouseChosen boolean to set full house chosen
   */
  public void setFullHouseChosen(boolean fullHouseChosen) {
    this.fullHouseChosen = fullHouseChosen;
  }

  /**
   * Getter for three of a kind chosen
   * @return true if three of a kind chosen
   */
  public boolean isThreeOfAKindChosen() {
    return threeOfAKindChosen;
  }

  /**
   * Setter for three of a kind chosen
   * @param threeOfAKindChosen boolean to set three of a kind chosen
   */
  public void setThreeOfAKindChosen(boolean threeOfAKindChosen) {
    this.threeOfAKindChosen = threeOfAKindChosen;
  }

  /**
   * Getter for four of a kind chosen
   * @return true if four of a kind chosen
   */
  public boolean isFourOfAKindChosen() {
    return fourOfAKindChosen;
  }

  /**
   * Setter for four of a kind chosen
   * @param fourOfAKindChosen boolean to set four of a kind chosen
   */
  public void setFourOfAKindChosen(boolean fourOfAKindChosen) {
    this.fourOfAKindChosen = fourOfAKindChosen;
  }

  /**
   * Getter for chance chosen
   * @return true if chance chosen
   */
  public boolean isChanceChosen() {
    return chanceChosen;
  }

  /**
   * Setter for chance chosen
   * @param chanceChosen boolean to set chance chosen
   */
  public void setChanceChosen(boolean chanceChosen) {
    this.chanceChosen = chanceChosen;
  }

  /**
   * Getter for yahtzee chosen
   * @return true if yahtzee chosen
   */
  public boolean isYahtzeeChosen() {
    return yahtzeeChosen;
  }

  /**
   * Setter for yahtzee chosen
   * @param yahtzeeChosen boolean to set yahtzee chosen
   */
  public void setYahtzeeChosen(boolean yahtzeeChosen) {
    this.yahtzeeChosen = yahtzeeChosen;
  }

  /**
   * Getter for total score
   * @return player's total score
   */
  public int getTotalScore() {
    return totalScore;
  }

  /**
   * Setter for total score
   * @param totalScore int to set total score
   */
  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  /**
   * Function to compare player objects
   * @param o player to compare to
   * @return player's total score
   */
  @Override
  public int compareTo(Object o) {
    return getTotalScore();
  }
}


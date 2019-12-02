import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The game logic of Yahtzee, determining score and rolling.
 */
public class GameLogic implements Serializable {
    /** First die for playing the game. */
    public Die die1;
    /** Second die for playing the game. */
    public Die die2;
    /** Third die for playing the game. */
    public Die die3;
    /** Fourth die for playing the game. */
    public Die die4;
    /** Fifth die for playing the game. */
    public Die die5;
    /** First player to play the game. */
    public Player player1;
    /** Second player to play the game. */
    public Player player2;
    /** Third player to play the game. */
    public Player player3;
    /** Fourth player to play the game. */
    public Player player4;
    /** Fifth player to play the game. */
    public Player player5;
    /** Number of rounds to keep track of game. */
    public int numRounds;
    /** Value of first die rolled. */
    public int die1Val;
    /** Value of second die rolled. */
    public int die2Val;
    /** Value of third die rolled. */
    public int die3Val;
    /** Value of fourth die rolled. */
    public int die4Val;
    /** Value of fifth die rolled. */
    public int die5Val;
    /** Number of rolls of player during round. */
    private int numRolls;
    /** Number of players for game creation. */
    private int numberOfPlayers;
    /** To tell if player must pass turn to next player. */
    private boolean mustPass;
    /** Score Option chosen by player to end turn. */
    public ScoreOption optionChosen;
    /**  Score of getting a full house. */
    public static final int FULL_HOUSE_SCORE = 25;
    /** Score of getting a small straight. */
    public static final int SMALL_STRAIGHT_SCORE = 30;
    /** Score of getting a large straight. */
    public static final int LARGE_STRAIGHT_SCORE = 40;
    /** Score of getting a yahtzee. */
    public static final int YAHTZEE_SCORE = 50;
    /** Score of getting a bonus yahtzee. */
    public static final int BONUS_SCORE = 100;
    /** List of all the values of dice rolled. */
    public static ArrayList<Integer> dieVals;
    /** Game status to keep track of game status. */
    public GameStatus gameStatus;
    /**
     * Default Constructor.
     */
    public GameLogic() {
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
        die4 = new Die();
        die5 = new Die();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        player3 = new Player("Player 3");
        player4 = new Player("Player 4");
        player5 = new Player("Player 5");
        numRolls = 1;
        numRounds = 0;
        player1.setTurn(true);
        optionChosen = ScoreOption.NONE;
        dieVals = new ArrayList<>();
        gameStatus = GameStatus.IN_PROGRESS;
        mustPass = false;
    }

    /**
     * Rolls the dice should the number of max rolls not be hit.
     */
    public void roll() {
        if (numRolls <= 3) {
            if (!die1.isHold()) {
                die1Val = die1.roll();
            }
            if (!die2.isHold()) {
                die2Val = die2.roll();
            }
            if (!die3.isHold()) {
                die3Val = die3.roll();
            }
            if (!die4.isHold()) {
                die4Val = die4.roll();
            }
            if (!die5.isHold()) {
                die5Val = die5.roll();
            }
        }
    }

    /**
     * Increments the number of rolls or resets if at 3 rolls.
     */
    public void incrementNumRolls() {
        if (numRolls == 1 || numRolls == 2) {
            numRolls++;
        } else if (numRolls == 3) {
            numRolls = 1;
        }
    }

    /**
     * Getter for must pass.
     *
     * @return true if must pass turn
     */
    public boolean isMustPass() {
        return mustPass;
    }

    /**
     * Setter for must pass.
     *
     * @param newMustPass boolean to set if player must pass
     */
    public void setMustPass(boolean newMustPass) {
        this.mustPass = newMustPass;
    }

    /**
     * Getter for the number of rolls.
     *
     * @return int for number of rolls
     */
    public int getNumRolls() {
        return numRolls;
    }

    /**
     * Adds the players score to the score option that they chose.
     *
     * @param player player who chose the score category
     */
    public void addScore(Player player) {
        // does bonus score here
        if (player.getYahtzeeRolls() > 0 && player.isYahtzeeChosen()) {
            if (die1Val == die2Val && die1Val == die3Val
                    && die1Val == die4Val && die1Val == die5Val) {
                player.setTotalScore(player.getTotalScore() + BONUS_SCORE);
                player.setYahtzeeRolls(player.getYahtzeeRolls() + 1);
            }
        }
        player.setTotalScore(player.getTotalScore() + chosenOptionScore(optionChosen, player));
        changeTurn();
        dieVals.clear();
    }

    /**
     * Changes turn to next player and increments round counter.
     */
    public void changeTurn() {
        if (player1.isTurn()) {
            player1.setTurn(false);
            player2.setTurn(true);
        } else if (player2.isTurn()) {
            player2.setTurn(false);
            if (numberOfPlayers > 2) {
                player3.setTurn(true);
            } else {
                player1.setTurn(true);
                numRounds++;
            }
        } else if (player3.isTurn()) {
            player3.setTurn(false);
            if (numberOfPlayers > 3) {
                player4.setTurn(true);
            } else {
                player1.setTurn(true);
                numRounds++;
            }
        } else if (player4.isTurn()) {
            player4.setTurn(false);
            if (numberOfPlayers > 4) {
                player5.setTurn(true);
            } else {
                player1.setTurn(true);
                numRounds++;
            }
        } else if (player5.isTurn()) {
            player5.setTurn(false);
            player1.setTurn(true);
            numRounds++;
        }
        if (numRounds == 13) {
            isWinner();
        }
    }

    /**
     * Gets the current player numbers turn.
     */
    public Player getTurn() {
        if (player1.isTurn()) {
            return player1;
        } else if (player2.isTurn()) {
            return player2;
        } else if (player3.isTurn()) {
            return player3;
        } else if (player4.isTurn()) {
            return player4;
        } else {
            return player5;
        }
    }

    /**
     * Returns the number of rounds currently in progress.
     *
     * @return num rounds in progress
     */
    public int getNumRounds() {
        return numRounds;
    }

    /**
     * Adds the player who will be added into the game.
     *
     * @param name of the player
     */
    public void addPlayer(String name) {
        Player newPlayer = new Player(name);
    }

    /**
     * Returns the winner out of the five players and sets game status to over.
     *
     * @return winning player
     */
    public Player isWinner() {
        ArrayList<Player> whoWins = new ArrayList<>();
        whoWins.add(player1);
        whoWins.add(player2);
        whoWins.add(player3);
        whoWins.add(player4);
        whoWins.add(player5);
        Collections.sort(whoWins);
        gameStatus = GameStatus.GAME_OVER;
        return whoWins.get(4);
    }

    /**
     * Returns the score of what option the player chose.
     *
     * @param option score option they chose
     * @param player player who is choosing score
     * @return correct score of option chosen
     */
    public int chosenOptionScore(ScoreOption option, Player player) {
        dieVals.add(die1Val);
        dieVals.add(die2Val);
        dieVals.add(die3Val);
        dieVals.add(die4Val);
        dieVals.add(die5Val);

        switch (option) {
            case ACES:
                if (!player.isAcesChosen()) {
                    player.setAcesChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 1) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case TWOS:
                if (!player.isTwosChosen()) {
                    player.setTwosChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 2) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case THREES:
                if (!player.isThreesChosen()) {
                    player.setThreesChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 3) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case FOURS:
                if (!player.isFoursChosen()) {
                    player.setFoursChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 4) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case FIVES:
                if (!player.isFivesChosen()) {
                    player.setFivesChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 5) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case SIXES:
                if (!player.isSixesChosen()) {
                    player.setSixesChosen(true);
                    int sum = 0;
                    for (int val : dieVals) {
                        if (val == 6) {
                            sum += val;
                        }
                    }
                    return sum;
                }
                break;
            case SMALL_STRAIGHT: // incomplete, need to check if small straight happened.
                if (!player.isSmallStraightChosen()) {
                    player.setSmallStraightChosen(true);
                    // add dievals to see if match 10, 14, or 18, and for one occurrence of each val
                    int sum = 0;
                    boolean one = false;
                    boolean two = false;
                    boolean three = false;
                    boolean four = false;
                    boolean five = false;
                    boolean six = false;
                    for (int val : dieVals) {
                        sum += val;
                        if (val == 1 && !one) {
                            one = true;
                        }
                        if (val == 2 && !two) {
                            two = true;
                        }
                        if (val == 3 && !three) {
                            three = true;
                        }
                        if (val == 4 && !four) {
                            four = true;
                        }
                        if (val == 5 && !five) {
                            five = true;
                        }
                        if (val == 6 && !six) {
                            six = true;
                        }
                    }
                    // conditions for small straight
                    boolean conditionOne = one && two && three && four;
                    boolean conditionTwo = two && three && four && five;
                    boolean conditionThree = three && four && five && six;
                    if ((sum == 10 || sum == 14 || sum == 18)
                            && (conditionOne || conditionTwo || conditionThree)) {
                        return SMALL_STRAIGHT_SCORE;
                    }
                }
                break;
            case LARGE_STRAIGHT:
                if (!player.isLargeStraightChosen()) {
                    player.setLargeStraightChosen(true);
                    // check for if large straight happened.
                    int sum = 0;
                    // add contents of array list to get 15 or 20, check to see if one occurrence of each num
                    boolean one = false;
                    boolean two = false;
                    boolean three = false;
                    boolean four = false;
                    boolean five = false;
                    boolean six = false;
                    for (int val : dieVals) {
                        sum += val;
                        if (val == 1 && !one) {
                            one = true;
                        }
                        if (val == 2 && !two) {
                            two = true;
                        }
                        if (val == 3 && !three) {
                            three = true;
                        }
                        if (val == 4 && !four) {
                            four = true;
                        }
                        if (val == 5 && !five) {
                            five = true;
                        }
                        if (val == 6 && !six) {
                            six = true;
                        }
                    }
                    // conditions for large straight
                    boolean conditionOne = one && two && three && four && five;
                    boolean conditionTwo = two && three && four && five && six;
                    if ((sum == 15 || sum == 20) && (conditionOne || conditionTwo)) {
                        return LARGE_STRAIGHT_SCORE;
                    }
                }
                break;
            case CHANCE:
                if (!player.isChanceChosen()) {
                    player.setChanceChosen(true);
                    return die1Val + die2Val + die3Val + die4Val + die5Val;
                }
                break;
            case YAHTZEE: // only for first time scoring yahtzee
                if (!player.isYahtzeeChosen()) {
                    player.setYahtzeeChosen(true);
                    if (die1Val == die2Val
                            && die1Val == die3Val
                            && die1Val == die4Val
                            && die1Val == die5Val) {
                        player.setYahtzeeRolls(1);
                        return YAHTZEE_SCORE;
                    } else {
                        return 0;
                    }
                }
                break;
            case FULL_HOUSE:
                if (!player.isFullHouseChosen()) {
                    player.setFullHouseChosen(true);
                    // getting counts of each value
                    int oneCount = 0;
                    int twoCount = 0;
                    int threeCount = 0;
                    int fourCount = 0;
                    int fiveCount = 0;
                    int sixCount = 0;
                    for (int val : dieVals) {
                        switch (val) {
                            case 1:
                                oneCount++;
                                break;
                            case 2:
                                twoCount++;
                                break;
                            case 3:
                                threeCount++;
                                break;
                            case 4:
                                fourCount++;
                                break;
                            case 5:
                                fiveCount++;
                                break;
                            case 6:
                                sixCount++;
                                break;
                            default:
                                break;
                        }
                    }
                    // seeing how many of each there are
                    boolean oneTwo = false;
                    boolean oneThree = false;
                    boolean twoTwo = false;
                    boolean twoThree = false;
                    boolean threeTwo = false;
                    boolean threeThree = false;
                    boolean fourTwo = false;
                    boolean fourThree = false;
                    boolean fiveTwo = false;
                    boolean fiveThree = false;
                    boolean sixTwo = false;
                    boolean sixThree = false;
                    if (oneCount == 2) {
                        oneTwo = true;
                    }
                    if (oneCount == 3) {
                        oneThree = true;
                    }
                    if (twoCount == 2) {
                        twoTwo = true;
                    }
                    if (twoCount == 3) {
                        twoThree = true;
                    }
                    if (threeCount == 2) {
                        threeTwo = true;
                    }
                    if (threeCount == 3) {
                        threeThree = true;
                    }
                    if (fourCount == 2) {
                        fourTwo = true;
                    }
                    if (fourCount == 3) {
                        fourThree = true;
                    }
                    if (fiveCount == 2) {
                        fiveTwo = true;
                    }
                    if (fiveCount == 3) {
                        fiveThree = true;
                    }
                    if (sixCount == 2) {
                        sixTwo = true;
                    }
                    if (sixCount == 3) {
                        sixThree = true;
                    }
                    // condition for a full house
                    boolean condition =
                            ((oneTwo && (twoThree || threeThree || fourThree || fiveThree || sixThree))
                                    || (twoTwo && (oneThree || threeThree || fourThree || fiveThree || sixThree))
                                    || (threeTwo && (oneThree || twoThree || fourThree || fiveThree || sixThree))
                                    || (fourTwo && (oneThree || twoThree || threeThree || fiveThree || sixThree))
                                    || (fiveTwo && (oneThree || twoThree || threeThree || fourThree || sixThree))
                                    || (sixTwo && (oneThree || twoThree || threeThree || fourThree || fiveThree)));
                    if (condition) {
                        return FULL_HOUSE_SCORE;
                    }
                }
                break;
            case THREE_OF_A_KIND:
                if (!player.isThreeOfAKindChosen()) {
                    player.setThreeOfAKindChosen(true);
                    // counting occurrences of each value
                    int oneCount = 0;
                    int twoCount = 0;
                    int threeCount = 0;
                    int fourCount = 0;
                    int fiveCount = 0;
                    int sixCount = 0;
                    for (int val : dieVals) {
                        switch (val) {
                            case 1:
                                oneCount++;
                                break;
                            case 2:
                                twoCount++;
                                break;
                            case 3:
                                threeCount++;
                                break;
                            case 4:
                                fourCount++;
                                break;
                            case 5:
                                fiveCount++;
                                break;
                            case 6:
                                sixCount++;
                                break;
                            default:
                                break;
                        }
                    }
                    // checking for true case
                    if (oneCount >= 3
                            || twoCount >= 3
                            || threeCount >= 3
                            || fourCount >= 3
                            || fiveCount >= 3
                            || sixCount >= 3) {
                        return die1Val + die2Val + die3Val + die4Val + die5Val;
                    }
                }
                break;
            case FOUR_OF_A_KIND:
                if (!player.isFourOfAKindChosen()) {
                    player.setFourOfAKindChosen(true);
                    // counting occurrences of each value
                    int oneCount = 0;
                    int twoCount = 0;
                    int threeCount = 0;
                    int fourCount = 0;
                    int fiveCount = 0;
                    int sixCount = 0;
                    for (int val : dieVals) {
                        switch (val) {
                            case 1:
                                oneCount++;
                                break;
                            case 2:
                                twoCount++;
                                break;
                            case 3:
                                threeCount++;
                                break;
                            case 4:
                                fourCount++;
                                break;
                            case 5:
                                fiveCount++;
                                break;
                            case 6:
                                sixCount++;
                                break;
                            default:
                                break;
                        }
                    }
                    // checking for true case
                    if (oneCount >= 4
                            || twoCount >= 4
                            || threeCount >= 4
                            || fourCount >= 4
                            || fiveCount >= 4
                            || sixCount >= 4) {
                        return die1Val + die2Val + die3Val + die4Val + die5Val;
                    }
                }
                break;
        }
        return 0;
    }

    /**
     * Setter for the number of players.
     *
     * @param numPlayers in for number of players to be set
     */
    public void setNumPlayers(int numPlayers) {
        numberOfPlayers = numPlayers;
    }
}

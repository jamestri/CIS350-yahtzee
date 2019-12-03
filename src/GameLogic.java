import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/** The game logic of Yahtzee, determining score and rolling. */
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
  /** Number of rolls of player during round. */
  private int numRolls;
  /** Number of players for game creation. */
  private int numberOfPlayers;
  /** To tell if player must pass turn to next player. */
  private boolean mustPass;
  /** Score Option chosen by player to end turn. */
  public ScoreOption optionChosen;
  /** Score of getting a full house. */
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

  private int firstValue;
  private int secondValue;
  private boolean oneChosen;
  private boolean sixChosen;

  /** Default Constructor. */
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
    numberOfPlayers = 2;
  }

  /** Rolls the dice should the number of max rolls not be hit. */
  public void roll() {
    if (numRolls <= 3) {
      if (!die1.isHold()) {
        die1.setRoll(die1.roll());
      }
      if (!die2.isHold()) {
        die2.setRoll(die2.roll());
      }
      if (!die3.isHold()) {
        die3.setRoll(die3.roll());
      }
      if (!die4.isHold()) {
        die4.setRoll(die4.roll());
      }
      if (!die5.isHold()) {
        die5.setRoll(die5.roll());
      }
    }
  }

  /** Increments the number of rolls or resets if at 3 rolls. */
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
      if (die1.getRoll() == die2.getRoll()
          && die1.getRoll() == die3.getRoll()
          && die1.getRoll() == die4.getRoll()
          && die1.getRoll() == die5.getRoll()) {
        player.setTotalScore(player.getTotalScore() + BONUS_SCORE);
        player.setYahtzeeRolls(player.getYahtzeeRolls() + 1);
      }
    }
    player.setTotalScore(player.getTotalScore() + chosenOptionScore(optionChosen, player));
    changeTurn();
    dieVals.clear();
  }

  /** Changes turn to next player and increments round counter. */
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
    secondValue = -1;
    oneChosen = false;
    sixChosen = false;
  }

  /** Gets the current player numbers turn. */
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
    dieVals.add(die1.getRoll());
    dieVals.add(die2.getRoll());
    dieVals.add(die3.getRoll());
    dieVals.add(die4.getRoll());
    dieVals.add(die5.getRoll());

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
          boolean one = false;
          boolean two = false;
          boolean three = false;
          boolean four = false;
          boolean five = false;
          boolean six = false;
          for (int val : dieVals) {
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
          if ((conditionOne || conditionTwo || conditionThree)) {
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
          return die1.getRoll() + die2.getRoll() + die3.getRoll() + die4.getRoll() + die5.getRoll();
        }
        break;
      case YAHTZEE: // only for first time scoring yahtzee
        if (!player.isYahtzeeChosen()) {
          player.setYahtzeeChosen(true);
          if (die1.getRoll() == die2.getRoll()
              && die1.getRoll() == die3.getRoll()
              && die1.getRoll() == die4.getRoll()
              && die1.getRoll() == die5.getRoll()) {
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
            return die1.getRoll()
                + die2.getRoll()
                + die3.getRoll()
                + die4.getRoll()
                + die5.getRoll();
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
            return die1.getRoll()
                + die2.getRoll()
                + die3.getRoll()
                + die4.getRoll()
                + die5.getRoll();
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

  /**
   * Getter for the number of players.
   *
   * @returns number of players in the game
   */
  public int getNumPlayers() {
    return numberOfPlayers;
  }

  public boolean checkIfCategoryUsed() {
    boolean hasBeenUsed = false;
    if (optionChosen == ScoreOption.ACES) {
      if (getTurn().isAcesChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.CHANCE) {
      if (getTurn().isChanceChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.FIVES) {
      if (getTurn().isFivesChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.FOUR_OF_A_KIND) {
      if (getTurn().isFourOfAKindChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.FOURS) {
      if (getTurn().isFoursChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.FULL_HOUSE) {
      if (getTurn().isFullHouseChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.LARGE_STRAIGHT) {
      if (getTurn().isLargeStraightChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.SIXES) {
      if (getTurn().isSixesChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.SMALL_STRAIGHT) {
      if (getTurn().isSmallStraightChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.THREE_OF_A_KIND) {
      if (getTurn().isThreeOfAKindChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.THREES) {
      if (getTurn().isThreesChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.TWOS) {
      if (getTurn().isTwosChosen()) hasBeenUsed = true;
    }
    if (optionChosen == ScoreOption.YAHTZEE) {
      if (getTurn().isYahtzeeChosen()) hasBeenUsed = true;
    }
    return hasBeenUsed;
  }

  public ScoreOption getAIScoringOption(Player p) {
    ScoreOption AIScoringOption = ScoreOption.NONE;
    int range = 13;
    int min = 1;
    int chooseRandomScoringOption = (int) (Math.random() * 13) + 1;
    if (chooseRandomScoringOption == 1) {
      AIScoringOption = ScoreOption.CHANCE;
    }
    if (chooseRandomScoringOption == 2) {
      AIScoringOption = ScoreOption.YAHTZEE;
    }
    if (chooseRandomScoringOption == 3) {
      AIScoringOption = ScoreOption.ACES;
    }
    if (chooseRandomScoringOption == 4) {
      AIScoringOption = ScoreOption.TWOS;
    }
    if (chooseRandomScoringOption == 5) {
      AIScoringOption = ScoreOption.THREES;
    }
    if (chooseRandomScoringOption == 6) {
      AIScoringOption = ScoreOption.FOURS;
    }
    if (chooseRandomScoringOption == 7) {
      AIScoringOption = ScoreOption.FIVES;
    }
    if (chooseRandomScoringOption == 8) {
      AIScoringOption = ScoreOption.SIXES;
    }
    if (chooseRandomScoringOption == 9) {
      AIScoringOption = ScoreOption.THREE_OF_A_KIND;
    }
    if (chooseRandomScoringOption == 10) {
      AIScoringOption = ScoreOption.FOUR_OF_A_KIND;
    }
    if (chooseRandomScoringOption == 11) {
      AIScoringOption = ScoreOption.FULL_HOUSE;
    }
    if (chooseRandomScoringOption == 12) {
      AIScoringOption = ScoreOption.SMALL_STRAIGHT;
    }
    if (chooseRandomScoringOption == 13) {
      AIScoringOption = ScoreOption.LARGE_STRAIGHT;
    }
    optionChosen = AIScoringOption;
    if (checkIfCategoryUsed()) {
      optionChosen = getAIScoringOption(getTurn());
    }
    AIScoringOption = optionChosen;
    return AIScoringOption;
  }

  public void aiChooseDice() {
    ArrayList<Die> Dice = new ArrayList<>();
    Dice.add(die1);
    Dice.add(die2);
    Dice.add(die3);
    Dice.add(die4);
    Dice.add(die5);
    int oneCount = 0;
    int twoCount = 0;
    int threeCount = 0;
    int fourCount = 0;
    int fiveCount = 0;
    int sixCount = 0;
    for (Die d : Dice) {
      if (d.getRoll() == 1) {
        oneCount++;
      }
      if (d.getRoll() == 2) {
        twoCount++;
      }
      if (d.getRoll() == 3) {
        threeCount++;
      }
      if (d.getRoll() == 4) {
        fourCount++;
      }
      if (d.getRoll() == 5) {
        fiveCount++;
      }
      if (d.getRoll() == 6) {
        sixCount++;
      }
      d.setHold(false);
    }
    int maxValue1 = Math.max(oneCount, twoCount);
    int maxValue2 = Math.max(threeCount, fourCount);
    int maxValue3 = Math.max(fiveCount, sixCount);
    int maxValue4 = Math.max(maxValue1, maxValue2);
    int finalMaxValue = Math.max(maxValue4, maxValue3);

    if (optionChosen == ScoreOption.ACES) {
      for (Die d : Dice) {
        if (d.getRoll() == 1) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.TWOS) {
      for (Die d : Dice) {
        if (d.getRoll() == 2) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.THREES) {
      for (Die d : Dice) {
        if (d.getRoll() == 3) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.FOURS) {
      for (Die d : Dice) {
        if (d.getRoll() == 4) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.FIVES) {
      for (Die d : Dice) {
        if (d.getRoll() == 5) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.SIXES) {
      for (Die d : Dice) {
        if (d.getRoll() == 6) {
          d.setHold(true);
        } else {
          d.setHold(false);
        }
      }
    }
    if (optionChosen == ScoreOption.THREE_OF_A_KIND
        || optionChosen == ScoreOption.FOUR_OF_A_KIND
        || optionChosen == ScoreOption.YAHTZEE) {
      if (finalMaxValue == oneCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 1) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
      if (finalMaxValue == twoCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 2) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
      if (finalMaxValue == threeCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 3) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
      if (finalMaxValue == fourCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 4) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
      if (finalMaxValue == fiveCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 5) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
      if (finalMaxValue == sixCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 6) {
            d.setHold(true);
          } else {
            d.setHold(false);
          }
        }
      }
    }
    if (optionChosen == ScoreOption.LARGE_STRAIGHT || optionChosen == ScoreOption.SMALL_STRAIGHT) {
      boolean oneTaken = false;
      boolean twoTaken = false;
      boolean threeTaken = false;
      boolean fourTaken = false;
      boolean fiveTaken = false;
      boolean sixTaken = false;
      for (Die d : Dice) {
        if (d.getRoll() == 1) {
          if (oneTaken || sixChosen) d.setHold(false);
          else {
            d.setHold(true);
            oneTaken = true;
            oneChosen = true;
          }
        }
        if (d.getRoll() == 2) {
          if (twoTaken) d.setHold(false);
          else {
            d.setHold(true);
            twoTaken = true;
          }
        }
        if (d.getRoll() == 3) {
          if (threeTaken) d.setHold(false);
          else {
            d.setHold(true);
            threeTaken = true;
          }
        }
        if (d.getRoll() == 4) {
          if (fourTaken) d.setHold(false);
          else {
            d.setHold(true);
            fourTaken = true;
          }
        }
        if (d.getRoll() == 5) {
          if (fiveTaken) d.setHold(false);
          else {
            d.setHold(true);
            fiveTaken = true;
          }
        }
        if (d.getRoll() == 6) {
          if (sixTaken || oneChosen) d.setHold(false);
          else {
            d.setHold(true);
            sixTaken = true;
            sixChosen = true;
          }
        }
      }
    }
    if (optionChosen == ScoreOption.CHANCE) {
      for (Die d : Dice) {
        if (d.getRoll() == 6 || d.getRoll() == 5 || d.getRoll() == 4) {
          d.setHold(true);
        }
      }
    }
    if (optionChosen == ScoreOption.FULL_HOUSE) {
      if (finalMaxValue == oneCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 1) {
            d.setHold(true);
            firstValue = 1;
          }
        }
      } else if (oneCount >= 1) {
        if (secondValue == -1 || secondValue == 1 || firstValue == 1)
          for (Die d : Dice) {
            if (d.getRoll() == 1) {
              if (oneCount <= 3)
                d.setHold(true);
              secondValue = 1;
            }
          }
      }
      if (finalMaxValue == twoCount) {
        for (Die d : Dice) {
          if (d.getRoll() == 2) {
            if (twoCount <= 3)
              d.setHold(true);
            if (firstValue == 1) secondValue = 2;
            else firstValue = 2;
          }
        }
      } else if (twoCount >= 1) {
        if (secondValue == 2 || secondValue == -1 || firstValue == 2) {
          for (Die d : Dice) {
            if (d.getRoll() == 2) {
              d.setHold(true);
              secondValue = 2;
            }
          }
        }
      }
      if (finalMaxValue == threeCount) {
        if (secondValue < 0) {
          for (Die d : Dice) {
            if (d.getRoll() == 3) {
              if (threeCount <= 3)
                d.setHold(true);
              if (firstValue > 0 && firstValue < 3) secondValue = 3;
              else firstValue = 3;
            }
          }
        }
      } else if (threeCount >= 1) {
        if (secondValue == 3 || secondValue == -1 || firstValue == 3) {
          for (Die d : Dice) {
            if (d.getRoll() == 3) {
              d.setHold(true);
              secondValue = 3;
            }
          }
        }
      }
      if (finalMaxValue == fourCount) {
        if (secondValue < 0) {
          for (Die d : Dice) {
            if (d.getRoll() == 4) {
              if (fourCount <= 3)
                d.setHold(true);
              if (firstValue > 0 && firstValue < 4) secondValue = 4;
              else firstValue = 4;
            }
          }
        }
      } else if (fourCount >= 1) {
        if (secondValue < 0) {
          if (secondValue == 4 || secondValue == -1 || firstValue == 4) {
            for (Die d : Dice) {
              if (d.getRoll() == 4) {
                d.setHold(true);
                secondValue = 4;
              }
            }
          }
        }
      }
      if (finalMaxValue == fiveCount) {
        if (secondValue < 0) {
          for (Die d : Dice) {
            if (d.getRoll() == 5) {
              if (fiveCount <= 3)
              d.setHold(true);
              if (firstValue > 0 && firstValue < 5) secondValue = 5;
              else firstValue = 5;
            }
          }
        }
      } else if (fiveCount >= 1) {
        if (secondValue == 5 || secondValue == -1 || firstValue == 5) {
          for (Die d : Dice) {
            if (d.getRoll() == 5) {
              d.setHold(true);
              secondValue = 5;
            }
          }
        }
      }
      if (finalMaxValue == sixCount) {
        if (secondValue < 0) {
          for (Die d : Dice) {
            if (d.getRoll() == 6) {
              if (sixCount <= 3)
                d.setHold(true);
              if (firstValue > 0 && firstValue < 6) secondValue = 6;
              else firstValue = 6;
            }
          }
        }
      } else if (sixCount >= 1) {
        if (secondValue == 6 || secondValue == -1 || firstValue == 6) {
          for (Die d : Dice) {
            if (d.getRoll() == 6) {
              d.setHold(true);
              secondValue = 6;
            }
          }
        }
      }
    }
  }
}

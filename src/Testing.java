import java.awt.font.GlyphMetrics;
import org.junit.Test;
import static org.junit.Assert.*;

public class Testing {


  @Test
  public void testRoll() {
    GameLogic game = new GameLogic();
    game.roll();
    assert (game.die1.getRoll() >= 1 && game.die1.getRoll() <= 6);
    assert (game.die2.getRoll() >= 1 && game.die2.getRoll() <= 6);
    assert (game.die3.getRoll() >= 1 && game.die3.getRoll() <= 6);
    assert (game.die4.getRoll() >= 1 && game.die4.getRoll() <= 6);
    assert (game.die5.getRoll() >= 1 && game.die5.getRoll() <= 6);
    game.die1.setRoll(5);
    game.die1.setHold(true);
    game.roll();
    assert (game.die1.getRoll() == 5);
  }

  @Test
  public void testWinner() {
    GameLogic game = new GameLogic();
    game.player1.setTotalScore(3000);
    game.player2.setTotalScore(4500);
    game.player3.setTotalScore(3500);
    game.player4.setTotalScore(5000);
    game.player5.setTotalScore(2500);
    assert(game.player4 == game.isWinner());
  }

  @Test
  public void testNumRounds() {
    GameLogic game = new GameLogic();
    game.numRounds = 5;
    assert(5 == game.getNumRounds());
  }

  @Test
  public void testGetTurns() {
    GameLogic game = new GameLogic();
    assert(game.player1 == game.getTurn());
    game.player1.setTurn(false);
    game.player2.setTurn(true);
    assert(game.player2 == game.getTurn());
    game.player2.setTurn(false);
    game.player3.setTurn(true);
    assert(game.player3 == game.getTurn());
    game.player3.setTurn(false);
    game.player4.setTurn(true);
    assert(game.player4 == game.getTurn());
    game.player4.setTurn(false);
    game.player5.setTurn(true);
    assert(game.player5 == game.getTurn());
    game.player5.setTurn(false);
    game.player1.setTurn(true);
    assert(game.player1 == game.getTurn());
  }

  @Test
  public void testNumPlayersAIAndRolls() {
    GameLogic game = new GameLogic();
    game.setNumPlayers(5);
    assert(game.getNumPlayers() == 5);
    game.player1.setAI(true);
    assert(game.player1.hasAI() == true);
    game.player1.setName("Billy");
    assert(game.player1.getName().equals("Billy"));
    game.player1.setYahtzeeRolls(2);
    assert(game.player1.getYahtzeeRolls() == 2);
    game.incrementNumRolls();
    game.incrementNumRolls();
    game.incrementNumRolls();
    assert(game.getNumRolls() == 1);
  }

  @Test
  public void testCategoriesChosen() {
    GameLogic game = new GameLogic();
    game.player1.setYahtzeeChosen(true);
    assert (game.player1.isYahtzeeChosen());
    game.player1.setAcesChosen(true);
    assert (game.player1.isAcesChosen());
    game.player1.setSixesChosen(true);
    assert (game.player1.isSixesChosen());
    game.player1.setLargeStraightChosen(true);
    assert (game.player1.isLargeStraightChosen());
    game.player1.setSmallStraightChosen(true);
    assert (game.player1.isSmallStraightChosen());
    game.player1.setTwosChosen(true);
    assert (game.player1.isTwosChosen());
    game.player1.setFullHouseChosen(true);
    assert (game.player1.isFullHouseChosen());
    game.player1.setThreesChosen(true);
    assert (game.player1.isThreesChosen());
    game.player1.setThreeOfAKindChosen(true);
    assert (game.player1.isThreeOfAKindChosen());
    game.player1.setFoursChosen(true);
    assert (game.player1.isFoursChosen());
    game.player1.setFourOfAKindChosen(true);
    assert (game.player1.isFourOfAKindChosen());
    game.player1.setFivesChosen(true);
    assert (game.player1.isFivesChosen());
    game.player1.setChanceChosen(true);
    assert (game.player1.isChanceChosen());
    game.optionChosen = ScoreOption.ACES;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.YAHTZEE;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.TWOS;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.THREES;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.THREE_OF_A_KIND;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.SMALL_STRAIGHT;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.SIXES;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.LARGE_STRAIGHT;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.FULL_HOUSE;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.FOURS;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.FOUR_OF_A_KIND;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.FIVES;
    assert (game.checkIfCategoryUsed());
    game.optionChosen = ScoreOption.CHANCE;
    assert (game.checkIfCategoryUsed());
  }

  @Test
  public void testIfCanPass() {
    GameLogic game = new GameLogic();
    game.setMustPass(true);
    assert (game.isMustPass());
  }

  @Test
  public void testChangingTurns() {
    GameLogic game = new GameLogic();
    game.setNumPlayers(5);
    for (int i = 0; i < 5; i++) {
      game.changeTurn();
    }
    assert (game.getTurn() == game.player1);
    game.setNumPlayers(4);
    for (int i = 0; i < 4; i++) {
      game.changeTurn();
    }
    assert (game.getTurn() == game.player1);
    game.setNumPlayers(3);
    for (int i = 0; i < 3; i++) {
      game.changeTurn();
    }
    assert (game.getTurn() == game.player1);
    game.setNumPlayers(2);
    for (int i = 0; i < 2; i++) {
      game.changeTurn();
    }
    assert (game.getTurn() == game.player1);
  }

  @Test
  public void testFullHouse1() {
    GameLogic game = new GameLogic();
    game.setNumPlayers(3);
    game.die1.setRoll(1);
    game.die2.setRoll(1);
    game.die3.setRoll(1);
    game.die4.setRoll(2);
    game.die5.setRoll(2);
    game.optionChosen = ScoreOption.FULL_HOUSE;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 25);
    game.player1.setTotalScore(0);
    game.die1.setRoll(3);
    game.die2.setRoll(3);
    game.die3.setRoll(3);
    game.die4.setRoll(4);
    game.die5.setRoll(4);
    game.optionChosen = ScoreOption.FULL_HOUSE;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 25);
    game.player2.setTotalScore(0);
    game.die1.setRoll(5);
    game.die2.setRoll(5);
    game.die3.setRoll(5);
    game.die4.setRoll(6);
    game.die5.setRoll(6);
    game.optionChosen = ScoreOption.FULL_HOUSE;
    game.addScore(game.getTurn());
    assert (game.player3.getTotalScore() == 25);
    game.player3.setTotalScore(0);
    GameLogic game2 = new GameLogic();
    game2.setNumPlayers(3);
    game2.die1.setRoll(1);
    game2.die2.setRoll(1);
    game2.die3.setRoll(2);
    game2.die4.setRoll(2);
    game2.die5.setRoll(2);
    game2.optionChosen = ScoreOption.FULL_HOUSE;
    game2.addScore(game2.getTurn());
    assert (game2.player1.getTotalScore() == 25);
    game2.player1.setTotalScore(0);
    game2.die1.setRoll(3);
    game2.die2.setRoll(3);
    game2.die3.setRoll(4);
    game2.die4.setRoll(4);
    game2.die5.setRoll(4);
    game2.optionChosen = ScoreOption.FULL_HOUSE;
    game2.addScore(game2.getTurn());
    assert (game2.player2.getTotalScore() == 25);
    game2.player2.setTotalScore(0);
    game2.die1.setRoll(5);
    game2.die2.setRoll(5);
    game2.die3.setRoll(6);
    game2.die4.setRoll(6);
    game2.die5.setRoll(6);
    game2.optionChosen = ScoreOption.FULL_HOUSE;
    game2.addScore(game2.getTurn());
    assert (game2.player3.getTotalScore() == 25);
    game2.player3.setTotalScore(0);
  }

  @Test
  public void testStraights(){
    GameLogic game = new GameLogic();
    game.die1.setRoll(1);
    game.die2.setRoll(2);
    game.die3.setRoll(3);
    game.die4.setRoll(4);
    game.die5.setRoll(5);
    game.optionChosen = ScoreOption.LARGE_STRAIGHT;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 40);
    game.die1.setRoll(6);
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 40);
    GameLogic game2 = new GameLogic();
    game2.setNumPlayers(3);
    game2.die1.setRoll(1);
    game2.die2.setRoll(2);
    game2.die3.setRoll(3);
    game2.die4.setRoll(4);
    game2.die5.setRoll(1);
    game2.optionChosen = ScoreOption.SMALL_STRAIGHT;
    game2.addScore(game2.getTurn());
    assert (game2.player1.getTotalScore() == 30);
    game2.die1.setRoll(2);
    game2.die5.setRoll(5);
    game2.addScore(game2.getTurn());
    assert (game2.player2.getTotalScore() == 30);
    game2.die2.setRoll(6);
    game2.addScore(game2.getTurn());
    assert (game2.player3.getTotalScore() == 30);
  }

  @Test
  public void testDigitCategoriesAndChance(){
    GameLogic game = new GameLogic();
    game.die1.setRoll(1);
    game.die2.setRoll(3);
    game.die3.setRoll(3);
    game.die4.setRoll(5);
    game.die5.setRoll(2);
    game.optionChosen = ScoreOption.CHANCE;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 14);
    game.optionChosen = ScoreOption.ACES;
    game.player1.setTotalScore(0);
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 1);
    game.optionChosen = ScoreOption.TWOS;
    game.player2.setTotalScore(0);
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 2);
    game.player1.setTotalScore(0);
    game.optionChosen = ScoreOption.THREES;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 6);
    game.optionChosen = ScoreOption.FOURS;
    game.player2.setTotalScore(0);
    game.die1.setRoll(4);
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 4);
    game.player1.setTotalScore(0);
    game.optionChosen = ScoreOption.FIVES;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 5);
    game.player2.setTotalScore(0);
    game.optionChosen = ScoreOption.SIXES;
    game.die1.setRoll(6);
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 6);
  }

  @Test
  public void testThreeOfAKind(){
    GameLogic game = new GameLogic();
    game.setNumPlayers(3);
    game.die1.setRoll(1);
    game.die2.setRoll(1);
    game.die3.setRoll(1);
    game.die4.setRoll(4);
    game.die5.setRoll(5);
    game.optionChosen = ScoreOption.THREE_OF_A_KIND;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 12);
    game.die1.setRoll(2);
    game.die2.setRoll(2);
    game.die3.setRoll(2);
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 15);
    game.die1.setRoll(3);
    game.die2.setRoll(3);
    game.die3.setRoll(3);
    game.addScore(game.getTurn());
    assert (game.player3.getTotalScore() == 18);

    GameLogic nGame = new GameLogic();
    nGame.setNumPlayers(3);
    nGame.die1.setRoll(4);
    nGame.die2.setRoll(4);
    nGame.die3.setRoll(4);
    nGame.die4.setRoll(1);
    nGame.die5.setRoll(3);
    nGame.optionChosen = ScoreOption.THREE_OF_A_KIND;
    nGame.addScore(nGame.getTurn());
    assert (nGame.player1.getTotalScore() == 16);
    nGame.die1.setRoll(5);
    nGame.die2.setRoll(5);
    nGame.die3.setRoll(5);
    nGame.addScore(nGame.getTurn());
    assert (nGame.player2.getTotalScore() == 19);
    nGame.die1.setRoll(6);
    nGame.die2.setRoll(6);
    nGame.die3.setRoll(6);
    nGame.addScore(nGame.getTurn());
    assert (nGame.player3.getTotalScore() == 22);
  }

  @Test
  public void testFourOfAKind(){
    GameLogic game = new GameLogic();
    game.setNumPlayers(3);
    game.die1.setRoll(1);
    game.die2.setRoll(1);
    game.die3.setRoll(1);
    game.die4.setRoll(1);
    game.die5.setRoll(5);
    game.optionChosen = ScoreOption.FOUR_OF_A_KIND;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 9);
    game.die1.setRoll(2);
    game.die2.setRoll(2);
    game.die3.setRoll(2);
    game.die4.setRoll(2);
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 13);
    game.die1.setRoll(3);
    game.die2.setRoll(3);
    game.die3.setRoll(3);
    game.die4.setRoll(3);
    game.addScore(game.getTurn());
    assert (game.player3.getTotalScore() == 17);

    GameLogic nGame = new GameLogic();
    nGame.setNumPlayers(3);
    nGame.die1.setRoll(4);
    nGame.die2.setRoll(4);
    nGame.die3.setRoll(4);
    nGame.die4.setRoll(4);
    nGame.die5.setRoll(3);
    nGame.optionChosen = ScoreOption.FOUR_OF_A_KIND;
    nGame.addScore(nGame.getTurn());
    assert (nGame.player1.getTotalScore() == 19);
    nGame.die1.setRoll(5);
    nGame.die2.setRoll(5);
    nGame.die3.setRoll(5);
    nGame.die4.setRoll(5);
    nGame.addScore(nGame.getTurn());
    assert (nGame.player2.getTotalScore() == 23);
    nGame.die1.setRoll(6);
    nGame.die2.setRoll(6);
    nGame.die3.setRoll(6);
    nGame.die4.setRoll(6);
    nGame.addScore(nGame.getTurn());
    assert (nGame.player3.getTotalScore() == 27);
  }

  @Test
  public void testYahtzee() {
    GameLogic game = new GameLogic();
    game.setNumPlayers(2);
    game.die1.setRoll(1);
    game.die2.setRoll(1);
    game.die3.setRoll(1);
    game.die4.setRoll(1);
    game.die5.setRoll(1);
    game.optionChosen = ScoreOption.YAHTZEE;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 50);
    game.player1.setTotalScore(0);
    game.changeTurn();
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 100);
  }

  @Test
  public void testPoorlyChosenCategories() {
    GameLogic game = new GameLogic();
    game.setNumPlayers(2);
    game.die1.setRoll(1);
    game.die2.setRoll(3);
    game.die3.setRoll(1);
    game.die4.setRoll(4);
    game.die5.setRoll(5);
    game.optionChosen = ScoreOption.FULL_HOUSE;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 0);
    game.optionChosen = ScoreOption.FOUR_OF_A_KIND;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 0);
    game.optionChosen = ScoreOption.THREE_OF_A_KIND;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 0);
    game.optionChosen = ScoreOption.YAHTZEE;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 0);
    game.optionChosen = ScoreOption.LARGE_STRAIGHT;
    game.addScore(game.getTurn());
    assert (game.player1.getTotalScore() == 0);
    game.optionChosen = ScoreOption.SMALL_STRAIGHT;
    game.addScore(game.getTurn());
    assert (game.player2.getTotalScore() == 0);
  }
}

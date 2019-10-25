import org.junit.Test;

public class Testing {

  @Test
  public void testRoll() {
    GameLogic game = new GameLogic();
    game.roll();
    assert (game.die1Val >= 1 && game.die1Val <= 6);
    assert (game.die2Val >= 1 && game.die2Val <= 6);
    assert (game.die3Val >= 1 && game.die3Val <= 6);
    assert (game.die4Val >= 1 && game.die4Val <= 6);
    assert (game.die5Val >= 1 && game.die5Val <= 6);
  }

  @Test
  public void testWinner() {
    GameLogic game = new GameLogic();
    game.player1.setTotalScore(3000);
    game.player2.setTotalScore(4500);
    game.player3.setTotalScore(3500);
    game.player4.setTotalScore(5000);
    game.player5.setTotalScore(2500);
    assert (game.player4 == game.isWinner());
  }

  @Test
  public void testNumRounds() {
    GameLogic game = new GameLogic();
    game.numRounds = 5;
    assert (5 == game.getNumRounds());
  }

  @Test
  public void testGetTurns() {
    GameLogic game = new GameLogic();
    assert (1 == game.getTurn());
    game.player1.setTurn(false);
    game.player2.setTurn(true);
    assert (2 == game.getTurn());
    game.player2.setTurn(false);
    game.player3.setTurn(true);
    assert (3 == game.getTurn());
    game.player3.setTurn(false);
    game.player4.setTurn(true);
    assert (4 == game.getTurn());
    game.player4.setTurn(false);
    game.player5.setTurn(true);
    assert (5 == game.getTurn());
    game.player5.setTurn(false);
    assert (-1 == game.getTurn());
  }
}


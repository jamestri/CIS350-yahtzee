public class GameLogic {
    public Die die1, die2, die3, die4, die5;
    public Player player1, player2, player3, player4, player5;
    public int numRolls;
    public static final int FULL_HOUSE = 25, SMALL_STRAIGHT = 30,
            LARGE_STRAIGHT = 40, YAHTZEE = 50, BONUS = 100;

    public GameLogic(){
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
        numRolls = 0;
        player1.setTurn(true);
    }

    private void roll(){
        if (numRolls < 3) {
            if (!die1.isHold()) {
                die1.roll();
            }
            if (!die2.isHold()) {
                die2.roll();
            }
            if (!die3.isHold()) {
                die3.roll();
            }
            if (!die4.isHold()) {
                die4.roll();
            }
            if (!die5.isHold()) {
                die5.roll();
            }
        }
        numRolls++;
    }

    private void addScore(Player player){
        player.setTotalScore(player.getTotalScore() + 500000000);//not done
    }
}

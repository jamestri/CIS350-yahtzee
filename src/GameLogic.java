public class GameLogic {
    public Die die1, die2, die3, die4, die5;
    public Player player1, player2, player3, player4, player5;
    public int numRolls, die1Val, die2Val, die3Val, die4Val, die5Val;
    public ScoreOption optionChosen;
    public static final int FULL_HOUSE_SCORE = 25, SMALL_STRAIGHT_SCORE = 30,
            LARGE_STRAIGHT_SCORE = 40, YAHTZEE_SCORE = 50, BONUS_SCORE = 100;

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
        optionChosen = ScoreOption.NONE;
    }

    private void roll(){
        if (numRolls < 3) {
                die1Val = die1.roll();
                die2Val = die2.roll();
                die3Val = die3.roll();
                die4Val = die4.roll();
                die5Val = die5.roll();
        }
        numRolls++;
    }

    private void addScore(Player player){
        //does bonus score here
        if (player.getYahtzeeRolls() > 0 && player.isYahtzeeChosen()){
            if (die1Val == die2Val && die1Val == die3Val && die1Val == die4Val && die1Val == die5Val){
                player.setTotalScore(player.getTotalScore() + BONUS_SCORE);
                player.setYahtzeeRolls(player.getYahtzeeRolls() + 1);
            }
        }
        player.setTotalScore(player.getTotalScore() + chosenOptionScore(optionChosen, player));
    }

    private int chosenOptionScore(ScoreOption option, Player player){
        switch (option){
            case ACES:
                if (!player.isAcesChosen()) {
                    player.setAcesChosen(true);
                    return 17;
                }
                break;
            case TWOS:
                if (!player.isTwosChosen()) {
                    player.setTwosChosen(true);
                    return 17;
                }
                break;
            case THREES:
                if (!player.isThreesChosen()) {
                    player.setThreesChosen(true);
                    return 17;
                }
                break;
            case FOURS:
                if (!player.isFoursChosen()) {
                    player.setFoursChosen(true);
                    return 17;
                }
                break;
            case FIVES:
                if (!player.isFivesChosen()) {
                    player.setFivesChosen(true);
                    return 17;
                }
                break;
            case SIXES:
                if (!player.isSixesChosen()) {
                    player.setSixesChosen(true);
                    return 17;
                }
                break;
            case SMALL_STRAIGHT: //incomplete, need to check if small straight happened
                if (!player.isSmallStraightChosen()) {
                    player.setSmallStraightChosen(true);
                    return SMALL_STRAIGHT_SCORE;
                }
                break;
            case LARGE_STRAIGHT: //incomplete need to check if large straight happened
                if (!player.isLargeStraightChosen()) {
                    player.setLargeStraightChosen(true);
                    return LARGE_STRAIGHT_SCORE;
                }
                break;
            case CHANCE:
                if (!player.isChanceChosen()) {
                    player.setChanceChosen(true);
                    return die1Val + die2Val + die3Val + die4Val + die5Val;
                }
                break;
            case YAHTZEE: //only for first time scoring yahtzee
                if (!player.isYahtzeeChosen()) {
                    player.setYahtzeeChosen(true);
                    if (die1Val == die2Val && die1Val == die3Val && die1Val == die4Val && die1Val == die5Val) {
                        player.setYahtzeeRolls(1);
                        return YAHTZEE_SCORE;
                    } else {
                        return 0;
                    }
                }
                break;
            case FULL_HOUSE: //incomplete need to check for full house
                if (!player.isFullHouseChosen()) {
                    player.setFullHouseChosen(true);
                    return FULL_HOUSE_SCORE;
                }
                break;
            case THREE_OF_A_KIND:
                if (!player.isThreeOfAKindChosen()) {
                    player.setThreeOfAKindChosen(true);
                    return die1Val + die2Val + die3Val + die4Val + die5Val;
                }
                break;
            case FOUR_OF_A_KIND:
                if (!player.isFourOfAKindChosen()) {
                    player.setFourOfAKindChosen(true);
                    return die1Val + die2Val + die3Val + die4Val + die5Val;
                }
                break;
        }

        return 0;
    }
}

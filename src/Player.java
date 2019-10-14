public class Player implements Comparable<Player>{
    private String name;
    private boolean turn, acesChosen, twosChosen, threesChosen,
            foursChosen, fivesChosen, sixesChosen, smallStraightChosen,
            largeStraightChosen, fullHouseChosen, threeOfAKindChosen,
            fourOfAKindChosen, chanceChosen, yahtzeeChosen;
    private int totalScore, yahtzeeRolls;


    public Player(String name) {
        this.name = name;
        turn = acesChosen = twosChosen = threesChosen = foursChosen = fivesChosen =
                sixesChosen = smallStraightChosen = largeStraightChosen = fullHouseChosen =
                        threeOfAKindChosen = fourOfAKindChosen = chanceChosen = yahtzeeChosen = false;

        totalScore = yahtzeeRolls = 0;
    }

    public int getYahtzeeRolls() {
        return yahtzeeRolls;
    }

    public void setYahtzeeRolls(int yahtzeeRolls) {
        this.yahtzeeRolls = yahtzeeRolls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isAcesChosen() {
        return acesChosen;
    }

    public void setAcesChosen(boolean acesChosen) {
        this.acesChosen = acesChosen;
    }

    public boolean isTwosChosen() {
        return twosChosen;
    }

    public void setTwosChosen(boolean twosChosen) {
        this.twosChosen = twosChosen;
    }

    public boolean isThreesChosen() {
        return threesChosen;
    }

    public void setThreesChosen(boolean threesChosen) {
        this.threesChosen = threesChosen;
    }

    public boolean isFoursChosen() {
        return foursChosen;
    }

    public void setFoursChosen(boolean foursChosen) {
        this.foursChosen = foursChosen;
    }

    public boolean isFivesChosen() {
        return fivesChosen;
    }

    public void setFivesChosen(boolean fivesChosen) {
        this.fivesChosen = fivesChosen;
    }

    public boolean isSixesChosen() {
        return sixesChosen;
    }

    public void setSixesChosen(boolean sixesChosen) {
        this.sixesChosen = sixesChosen;
    }

    public boolean isSmallStraightChosen() {
        return smallStraightChosen;
    }

    public void setSmallStraightChosen(boolean smallStraightChosen) {
        this.smallStraightChosen = smallStraightChosen;
    }

    public boolean isLargeStraightChosen() {
        return largeStraightChosen;
    }

    public void setLargeStraightChosen(boolean largeStraightChosen) {
        this.largeStraightChosen = largeStraightChosen;
    }

    public boolean isFullHouseChosen() {
        return fullHouseChosen;
    }

    public void setFullHouseChosen(boolean fullHouseChosen) {
        this.fullHouseChosen = fullHouseChosen;
    }

    public boolean isThreeOfAKindChosen() {
        return threeOfAKindChosen;
    }

    public void setThreeOfAKindChosen(boolean threeOfAKindChosen) {
        this.threeOfAKindChosen = threeOfAKindChosen;
    }

    public boolean isFourOfAKindChosen() {
        return fourOfAKindChosen;
    }

    public void setFourOfAKindChosen(boolean fourOfAKindChosen) {
        this.fourOfAKindChosen = fourOfAKindChosen;
    }

    public boolean isChanceChosen() {
        return chanceChosen;
    }

    public void setChanceChosen(boolean chanceChosen) {
        this.chanceChosen = chanceChosen;
    }

    public boolean isYahtzeeChosen() {
        return yahtzeeChosen;
    }

    public void setYahtzeeChosen(boolean yahtzeeChosen) {
        this.yahtzeeChosen = yahtzeeChosen;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }


    public int compareTo(Player o) {
        if (getTotalScore() < o.getTotalScore()) {
            return -1;
        } else {
            return 1;
        }
    }
}



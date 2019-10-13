import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AI extends Player{
    public Die die1, die2, die3, die4, die5;
    public int numRounds, numRolls, die1Val,die2Val,die3Val,die4Val,die5Val;
    public static ArrayList<Integer> dieVals;
    public boolean turn;
    public static Random rnd;
    public static ArrayList<Integer> dieHold;
    public int rndValue;


    public AI(){
        super("Mark");
        die1 = new Die();
        die2 =new Die();
        die3 = new Die();
        die4 = new Die();
        die5 = new Die();
        dieVals = new ArrayList<>();
        numRolls=0;
        numRounds = 0;
        rnd = new Random();
        dieHold = new ArrayList<>();
        rndValue = rnd.nextInt(2);

    }

    private void roll(){
        if (numRolls < 3) {
            die1Val = die1.roll();
            die2Val = die2.roll();
            die3Val = die3.roll();
            die4Val = die4.roll();
            die5Val = die5.roll();

            //adding values of die to arraylist on roll.
            dieVals.add(die1Val);
            dieVals.add(die2Val);
            dieVals.add(die3Val);
            dieVals.add(die4Val);
            dieVals.add(die5Val);

        }
        numRolls++;
    }

    static int val1 = dieVals.get(0);
    static int val2 = dieVals.get(1);
    static int val3 = dieVals.get(2);
    static int val4 = dieVals.get(3);
    static int val5 = dieVals.get(4);

    private static boolean twoMatch = (val1 == val2 || val1 == val3 || val1 == val4 || val1 == val5 || val2 == val3 || val2 == val4 || val2 == val5 || val3 == val4 || val3 == val5 || val4 == val5);

    private static boolean threeMatch = (val1 == val2 && val1 == val3) || (val1 == val4 && val1 == val5)||
     (val2 == val4 && val2 == val5) || (val2 == val1 && val2 == val3) || (val3 == val1 && val3 == val2) || (val3 ==val4 && val3 == val5) ||
        (val4 == val1 && val4 ==val2) || (val4 == val3 && val4 == val5) || (val5 == val1 && val5 == val2) || (val5 == val3 && val5 == val4);



    static boolean fourMatch = (val1 == val2 && val1 == val3 && val1 == val4 || val1 == val5);


    public void chancesOfYahtzee() {

        //after each roll program evaluates chances of getting some configuration.
        if (numRolls == 1) {
                if (twoMatch) {
                    //hold if random value is a zero || roll if it is a 1.
                    if (rndValue == 0) {
                        hold();
                        roll();
                    } else {
                        roll();
                    }

                }
        }

        if (numRolls == 2){
            if (threeMatch) {
                if(rndValue == 0){
                    hold();
                    roll();
                }else{
                    roll();
                    //or score in three of a kind
                }
            }

            if (fourMatch){
                if (rndValue == 1){
                    hold();
                    roll();
                }else{
                    roll();
                    //or score in four of a kind
                }
            }

        }



    }


    private void hold() {
        for (int i = 0; i < dieVals.size(); i++) {
            //if two die match.
            if (twoMatch) {
                if(val1 == val2){
                    die1.setHold(true);
                    die2.setHold(true);
                    addToDieHold(val1,val2);
                }
                else if(val1 == val3){
                    die1.setHold(true);
                    die3.setHold(true);
                    addToDieHold(val1,val2);
                }

                else if(val1 == val4){
                    die1.setHold(true);
                    die4.setHold(true);
                    addToDieHold(val1,val4);
                }

                else if(val1 == val5){
                    die1.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val1,val5);
                }
                else if(val2 == val3){
                    die2.setHold(true);
                    die3.setHold(true);
                    addToDieHold(val1,val5);
                }
                else if(val2 == val4){
                    die2.setHold(true);
                    die4.setHold(true);
                    addToDieHold(val1,val5);
                }
                else if(val2 == val5){
                    die2.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val1,val5);
                }
                else if(val3 == val4){
                    die3.setHold(true);
                    die4.setHold(true);
                    addToDieHold(val1,val5);
                }

                else if(val3 == val5){
                    die3.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val1,val5);
                }
                else if(val4 == val5){
                    die4.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val1,val5);
                }

            }


            if(threeMatch){
                if (val1==val2 && val1 == val3){
                    die1.setHold(true);
                    die2.setHold(true);
                    die3.setHold(true);
                    addToDieHold(val1,val2,val3);
                }else if(val1 == val4 && val1 == val5){
                    die1.setHold(true);
                    die4.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val1,val4,val5);
                }else if (val2 == val4 && val2 == val5){
                    die2.setHold(true);
                    die4.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val2,val4,val5);
                }else if (val2 == val1 && val2 == val3){
                    die2.setHold(true);
                    die1.setHold(true);
                    die3.setHold(true);
                    addToDieHold(val1,val2,val3);
                }else if(val3 == val1 && val3 == val2){
                    die3.setHold(true);
                    die2.setHold(true);
                    die1.setHold(true);
                    addToDieHold(val1,val2,val3);
                } else if(val3 == val4 && val3 == val5){
                    die3.setHold(true);
                    die4.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val3,val4,val5);
                }else if(val4 == val1 && val4 ==val2){
                    die4.setHold(true);
                    die1.setHold(true);
                    die2.setHold(true);
                    addToDieHold(val1,val2,val4);
                }else if(val4 == val3 && val4 == val5){
                    die4.setHold(true);
                    die3.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val4,val3,val5);
                }else if(val5 == val1 && val5 == val2){
                    die5.setHold(true);
                    die1.setHold(true);
                    die2.setHold(true);
                    addToDieHold(val5,val1,val2);
                }else if(val5 == val3 && val5 == val4){
                    die3.setHold(true);
                    die4.setHold(true);
                    die5.setHold(true);
                    addToDieHold(val3,val4,val5);
                }

            }
        }
    }







    private void addToDieHold(int a, int b){
        List<Integer> vals = Arrays.asList(a,b);
        dieHold.addAll(vals);
    }

    private void addToDieHold(int a, int b, int c){
        List<Integer> vals = Arrays.asList(a,b,c);
        dieHold.addAll(vals);
    }

    private void holdValue(int val){
        dieHold.add(val);
    }

    @Override
    public boolean isTurn() {
        return turn;
    }
}
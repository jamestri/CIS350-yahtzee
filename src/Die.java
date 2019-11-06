import java.io.Serializable;
import java.util.Random;

/**
 * Class to act as a die and have values
 */
public class Die implements Serializable {
  private int roll;
  private Random rand;
  private boolean hold;

  /**
   * Default constructor
   */
  public Die() {
    roll = 0;
    rand = new Random();
    hold = false;
  }

  /**
   * Rolls the 6 sided die
   * @return value of roll
   */
  public int roll() {
    if (!hold) {
      roll = rand.nextInt(6) + 1;
    }
    return roll;
  }

  /**
   * Getter for the roll
   * @return roll
   */
  public int getRoll() {
    return roll;
  }

  /**
   * Setter for roll
   * @param roll int of roll to set
   */
  public void setRoll(int roll) {
    this.roll = roll;
  }

  /**
   * Getter for hold
   * @return true if die is held
   */
  public boolean isHold() {
    return hold;
  }

  /**
   * Setter for hold
   * @param hold boolean to set hold
   */
  public void setHold(boolean hold) {
    this.hold = hold;
  }
}


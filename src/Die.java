import java.io.Serializable;
import java.util.Random;

/**
 * Die class that represents a six sided die. Each roll returns a random value between 1 and 6.
 *
 * @version 2
 */
public class Die implements Serializable {

  /** holds the value of the die after rolled. */
  private int roll;

  /** random object to generate a random value for roll. */
  private Random rand;

  /** flag to set whether the die is being held or not. */
  private boolean hold;

  /** number of sides on die. */
  private static final int DIE_SIDES = 6;
  /** Default constructor. Initializes roll value to 0, random object and set hold flag to false. */
  public Die() {
    roll = 0;
    rand = new Random();
    hold = false;
  }

  /**
   * Rolls the 6 sided die. Generates random value between 1 to 6.
   *
   * @return value of roll
   */
  public int roll() {
    if (!hold) {
      roll = rand.nextInt(DIE_SIDES) + 1;
    }

    return roll;
  }

  /**
   * Getter for the roll value.
   *
   * @return roll value
   */
  public int getRoll() {
    return roll;
  }

  /**
   * Setter for roll value.
   *
   * @param rollChange int value to set the roll to
   */
  public void setRoll(int rollChange) {
    this.roll = rollChange;
  }

  /**
   * Getter for hold flag.
   *
   * @return true if die is held
   */
  public boolean isHold() {
    return hold;
  }

  /**
   * Setter for hold flag.
   *
   * @param holdChange boolean to set hold
   */
  public void setHold(boolean holdChange) {
    this.hold = holdChange;
  }
}

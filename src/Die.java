import java.io.Serializable;
import java.util.Random;

public class Die implements Serializable {
  private int roll;
  private Random rand;
  private boolean hold;

  public Die() {
    roll = 0;
    rand = new Random();
    hold = false;
  }

  public int roll() {
    if (!hold) {
      roll = rand.nextInt(6) + 1;
    }
    return roll;
  }

  public int getRoll() {
    return roll;
  }

  public void setRoll(int roll) {
    this.roll = roll;
  }

  public boolean isHold() {
    return hold;
  }

  public void setHold(boolean hold) {
    this.hold = hold;
  }
}


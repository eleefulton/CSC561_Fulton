package lifeform;

import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

/**
 * Keep track of information pertaining to Aliens including their max hit points
 * and recovery behavior
 * 
 * @author Ethan Fulton
 *
 */
public class Alien extends LifeForm {

  private int maxLifePoints;
  private RecoveryBehavior myRecoveryBehavior;

  public Alien(String name, int points) {
    this(name, points, new RecoveryNone());
  }

  public Alien(String name, int points, RecoveryBehavior rb) {
    super(name, points);
    maxLifePoints = points;
    myRecoveryBehavior = rb;
    attackStrength = 10;
  }

  /**
   * calculate the aliens recovery based on it's RecoveryBehavior
   */
  public void recover() {
    if (myRecoveryBehavior != null) {
      currentLifePoints = myRecoveryBehavior.calculateRecovery(getCurrentLifePoints(), maxLifePoints);
    }
  }

}

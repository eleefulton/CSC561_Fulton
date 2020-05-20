package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryNone {

  /**
   * check that the max life points of an alien stays at max after recovery
   */
  @Test
  public void testNoRecovery() {
    RecoveryNone recoveryNone = new RecoveryNone();
    assertEquals(10, recoveryNone.calculateRecovery(10, 10));
  }

  /**
   * check that the life points don't change when recovery happens after damage
   */
  @Test
  public void testNoRecoveryAfterDamage() {
    RecoveryNone recoveryNone = new RecoveryNone();
    int currentLifePoints = 30;
    int maxLifePoints = 30;
    assertEquals(30, recoveryNone.calculateRecovery(currentLifePoints, maxLifePoints));
    currentLifePoints -= 10;
    assertEquals(20, recoveryNone.calculateRecovery(currentLifePoints, maxLifePoints));
  }

}

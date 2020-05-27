package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryLinear {
  
  /**
   * begin tests for Strategy Pattern
   */

  /**
   * check if already at max life points no recovery occurs
   */
  @Test
  public void testNoRecoveryWhenNotHurt() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = rl.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * check that if below max life points recovery occurs but does not put alien
   * back at full health
   */
  @Test
  public void testRecoveryNotToFull() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePoints = 30;
    int currentLifePoints = 20;
    assertEquals(23, rl.calculateRecovery(currentLifePoints, maxLifePoints));
  }

  /**
   * check that recovery does not make alien go over max life points
   */
  @Test
  public void testRecoveryPastMax() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePoints = 30;
    int currentLifePoints = 29;
    assertEquals(30, rl.calculateRecovery(currentLifePoints, maxLifePoints));
  }
  
  /**
   * check that if an alien is at 0 life points it no longer recovers
   */
  @Test
  public void testRecoveryDead() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePoints = 30;
    int currentLifePoints = 0;
    assertEquals(0, rl.calculateRecovery(currentLifePoints, maxLifePoints));
  }
  
  /**
   * check that if an alien can recover to exactly its max life points
   */
  @Test
  public void testRecoveryPercise() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePoints = 30;
    int currentLifePoints = 27;
    assertEquals(30, rl.calculateRecovery(currentLifePoints, maxLifePoints));
  }

}

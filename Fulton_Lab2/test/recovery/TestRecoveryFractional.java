package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryFractional {
  
  /**
   * begin tests for Strategy Pattern
   */

  /**
   * check that a fractional recovery behavior can be created and recovers the
   * proper amount
   */
  @Test
  public void testFractionalRecovery() {
    RecoveryFractional fr = new RecoveryFractional(0.1);
    int currentLifePoints = 100;
    int maxLifePoints = 100;
    assertEquals(100, fr.calculateRecovery(currentLifePoints, maxLifePoints));
    currentLifePoints -= 10;
    assertEquals(99, fr.calculateRecovery(currentLifePoints, maxLifePoints));
    currentLifePoints = 89;
    assertEquals(97, fr.calculateRecovery(currentLifePoints, maxLifePoints));
  }

  /**
   * check a fractional recovery doesn't go over max life points
   */
  @Test
  public void testMaxRecovery() {
    RecoveryFractional fr = new RecoveryFractional(0.1);
    int currentLifePoints = 98;
    int maxLifePoints = 100;
    assertEquals(100, fr.calculateRecovery(currentLifePoints, maxLifePoints));
  }

  /**
   * check fractional recovery doesn't recover if dead
   */
  @Test
  public void testDead() {
    RecoveryFractional fr = new RecoveryFractional(0.1);
    int currentLifePoints = 0;
    int maxLifePoints = 100;
    assertEquals(0, fr.calculateRecovery(currentLifePoints, maxLifePoints));
  }
}

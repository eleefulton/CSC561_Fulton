package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

import recovery.RecoveryLinear;
import recovery.RecoveryNone;

public class TestAlien {
  
  
  /**
   * begin tests for Strategy Pattern
   */

  /**
   * check that an Alien can be initialized with only a name and max life points
   */
  @Test
  public void testSimpleInitalization() {
    Alien alien = new Alien("alie", 40);
    assertEquals(40, alien.getCurrentLifePoints());
    assertEquals("alie", alien.getName());
  }
  
  /**
   * check that an Alien can be initialized with a RecoveryLinear recovery
   */
  @Test
  public void testRecoveryLinear() {
    RecoveryLinear rl = new RecoveryLinear(3);
    Alien alien = new Alien("alie", 40, rl);
    assertEquals(40, alien.getCurrentLifePoints());
    assertEquals("alie", alien.getName());
    alien.takeHit(10);
    assertEquals(30, alien.getCurrentLifePoints());
    alien.recover();
    assertEquals(33, alien.getCurrentLifePoints());
  }

}

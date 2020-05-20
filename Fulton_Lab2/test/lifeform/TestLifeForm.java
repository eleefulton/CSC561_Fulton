package lifeform;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm {
  /**
   * When a LifeForm is created, it should know its name and how many life points
   * it has.
   */
  @Test
  public void testInitialization() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }
  
  /**
   * Check a LifeForm can take damage but not go below 0 life points
   */
  @Test
  public void testTakeDamage() {
    LifeForm entity = new MockLifeForm("Bob", 40);
    entity.takeHit(30);
    assertEquals(10, entity.getCurrentLifePoints());
    entity.takeHit(10);
    assertEquals(0, entity.getCurrentLifePoints());
    entity.takeHit(1);
    assertEquals(0, entity.getCurrentLifePoints());
  }
  
}

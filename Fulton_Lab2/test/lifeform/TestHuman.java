package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHuman {
  
  
  /**
   * begin tests for Strategy Pattern
   */

  /**
   * Check that a human can be initialized with an armor value no less than 0
   */
  @Test
  public void testInitalization() {
   Human human1 = new Human("Bob", 40, 10);
   assertEquals(10, human1.getArmorPoints());
   Human human2 = new Human("Dob", 40, -1);
   assertEquals(0, human2.getArmorPoints());
  }
  
  /**
   * Check that a human's armor can be updated
   */
  @Test
  public void testSetArmorPoints() {
    Human human = new Human("Bob", 40, 10);
    assertEquals(10, human.getArmorPoints());
    human.setArmorPoints(9);
    assertEquals(9, human.getArmorPoints());
  }

}

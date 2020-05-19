import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the functionality provided by the Environment class
 *
 */
public class TestEnvironment {

  /**
   * Tests a 1x1 environment can be created and populated with a single cell
   */
  @Test
  public void testInitializationSingleCell() {
    Environment e = new Environment(1,1);
    assertEquals(1, e.getNumberOfRows());
    assertEquals(1, e.getNumberOfColumns());
    assertEquals(1, e.getNumberOfCells());
    assertNull(e.getLifeForm(0,0));
  }
  
  /**
   * Tests adding a LifeForm to the Environment at the specified location
   */
  @Test
  public void testAddLifeForm() {
    Environment e = new Environment(2,3);
    LifeForm l = new LifeForm("L", 10);
    e.addLifeForm(l, 0, 1);
    assertEquals(l, e.getLifeForm(0,1));
  }
  
  /**
   * Tests that a LifeForm cannot be added to a cell out of bounds of the
   * Environment
   */
  @Test
  public void testAddLifeFormOffGrid() {
    Environment e = new Environment(2,3);
    LifeForm l = new LifeForm("L", 10);
    e.addLifeForm(l, -1, 1);
    assertEquals(null, e.getLifeForm(-1,1));
    e.addLifeForm(l, 0, 3);
    assertEquals(null, e.getLifeForm(0, 3));
  }

  /**
   * Tests that a LifeForm can be removed from a specified location
   */
  @Test
  public void testRemoveLifeForm() {
    Environment e = new Environment(2,3);
    LifeForm l = new LifeForm("L", 10);
    e.addLifeForm(l, 0, 1);
    assertEquals(l, e.getLifeForm(0,1));
    e.removeLifeForm(0,1);
    assertNull(e.getLifeForm(0, 1));
  }
}

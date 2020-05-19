import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The test cases for the Cell class
 *
 */
public class TestCell {
  /**
   * At initialization, the Cell should be empty and not contain a LifeForm.
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }
}

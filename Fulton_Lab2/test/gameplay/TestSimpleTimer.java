package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSimpleTimer {

  @Test
  public void testInitialization() {
    SimpleTimer timer = new SimpleTimer();
    assertEquals(0, timer.getRound());
    assertEquals(0, timer.getObservers().size());
    assertTrue(timer instanceof gameplay.Timer);
  }
  
  @Test
  public void testAddRemoveObserverWithTimeChange() {
    SimpleTimer timer = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    timer.addTimeObserver(observer);
    timer.timeChanged();
    assertEquals(1, observer.myTime);
    timer.removeTimeObserver(observer);
    timer.timeChanged();
    assertEquals(2, timer.getRound());
    assertEquals(1,  observer.myTime);
  }
  

}

class MockSimpleTimerObserver implements TimeObserver {
  public int myTime = 0;

  public void updateTime(int time) {
    myTime = time;
  }
}

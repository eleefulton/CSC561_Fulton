package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSimpleTimer
{

	/** * This tests that SimpleTimer will update time once * every second. */
	@Test
	public void testSimpleTimerAsThread() throws InterruptedException
	{
		SimpleTimer st = new SimpleTimer(1000);
		st.start();
		Thread.sleep(250); // So we are 1/4th a second different
		for (int x = 0; x < 5; x++)
		{
			assertEquals(x, st.getRound()); // assumes round starts at 0
			Thread.sleep(1000); // wait for the next time change
		}
	}

	@Test
	public void testInitialization()
	{
		SimpleTimer timer = new SimpleTimer();
		assertEquals(0, timer.getRound());
		assertEquals(0, timer.getObservers().size());
		assertTrue(timer instanceof gameplay.Timer);
	}

	@Test
	public void testAddRemoveObserverWithTimeChange()
	{
		SimpleTimer timer = new SimpleTimer();
		MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
		timer.addTimeObserver(observer);
		timer.timeChanged();
		assertEquals(1, observer.myTime);
		timer.removeTimeObserver(observer);
		timer.timeChanged();
		assertEquals(2, timer.getRound());
		assertEquals(1, observer.myTime);
	}

}

class MockSimpleTimerObserver implements TimeObserver
{
	public int myTime = 0;

	public void updateTime(int time)
	{
		myTime = time;
	}
}

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import environment.TestCell;
import environment.TestEnvironment;
import gameplay.TestSimpleTimer;
import lifeform.TestAlien;
import lifeform.TestHuman;
import lifeform.TestLifeForm;
import recovery.TestRecoveryFractional;
import recovery.TestRecoveryLinear;
import recovery.TestRecoveryNone;

/**
 * Runs all of the tests in this project
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
{ TestCell.class, TestLifeForm.class, TestEnvironment.class, TestHuman.class, TestAlien.class, TestRecoveryNone.class,
		TestRecoveryLinear.class, TestRecoveryFractional.class, TestSimpleTimer.class })
public class AllGameTests
{

}

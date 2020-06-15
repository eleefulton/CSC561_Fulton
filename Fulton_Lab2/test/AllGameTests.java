import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import environment.TestCell;
import environment.TestEnvironment;
import gameplay.TestMoveCommand;
import gameplay.TestSimpleTimer;
import lifeform.TestAlien;
import lifeform.TestHuman;
import lifeform.TestLifeForm;
import recovery.TestRecoveryFractional;
import recovery.TestRecoveryLinear;
import recovery.TestRecoveryNone;
import weapon.TestAttachment;
import weapon.TestBooster;
import weapon.TestChainGun;
import weapon.TestGenericWeapon;
import weapon.TestPistol;
import weapon.TestPlasmaCannon;
import weapon.TestScope;
import weapon.TestStabilizer;

/**
 * Runs all of the tests in this project
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
{ TestCell.class, TestLifeForm.class, TestEnvironment.class, TestHuman.class, TestAlien.class, TestRecoveryNone.class,
		TestRecoveryLinear.class, TestRecoveryFractional.class, TestSimpleTimer.class, TestAttachment.class,
		TestGenericWeapon.class, TestPlasmaCannon.class, TestStabilizer.class, TestPistol.class, TestScope.class,
		TestChainGun.class, TestBooster.class, TestMoveCommand.class })
public class AllGameTests
{

}

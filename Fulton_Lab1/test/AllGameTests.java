import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the tests in this project
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestCell.class, TestLifeForm.class, TestEnvironment.class})
public class AllGameTests {

}

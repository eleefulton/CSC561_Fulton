package recovery;

/**
 * A class for linear recovery, at each recovery a predetermined amount is added
 * to the current health
 * 
 * @author Ethan Fulton
 *
 */
public class RecoveryLinear implements RecoveryBehavior {

  private int recoveryStep;

  public RecoveryLinear(int r) {
    recoveryStep = r;
  }

  /**
   * if currentLife is below maxLife but not 0, add recoveryStep up to maxLife
   */
  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife != 0) {
      return currentLife + recoveryStep > maxLife ? maxLife : currentLife + recoveryStep;
    } else
      return 0;
  }

}

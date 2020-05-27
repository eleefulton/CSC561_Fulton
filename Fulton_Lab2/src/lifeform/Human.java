package lifeform;

/**
 * Keep track of information pertaining to Humans, including their armor points
 * 
 * @author Ethan Fulton
 *
 */
public class Human extends LifeForm {

  private int currentArmorPoints;

  public Human(String name, int lifepoints, int armor) {
    super(name, lifepoints);
    this.setArmorPoints(armor);
    attackStrength = 5;
  }

  /**
   * @return the current armor points of this human
   */
  public int getArmorPoints() {
    return currentArmorPoints;
  }

  /**
   * change currentArmorPoints to newPoints
   * 
   * @param newPoints
   *          the new armor point value
   */
  public void setArmorPoints(int newPoints) {
    currentArmorPoints = newPoints < 0 ? 0 : newPoints;
  }

  /**
   * Override the default takeHit so the Humans armor absorbs damage less than or
   * equal to the armor points
   * 
   * @param damage
   *          The amount of damage to take
   */
  @Override
  public void takeHit(int damage) {
    int damageToTake = damage > currentArmorPoints ? damage - currentArmorPoints : 0;
    currentLifePoints -= damageToTake;
  }

}

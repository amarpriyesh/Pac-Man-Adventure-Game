package otyugh;

import static dungeon.dungeon.DungeonImpl.RAND;

/**
 * The below class represents structure of an Otyugh with the components and functions used by the
 * gameplay.
 */
public class OtyughImpl implements Otyugh {


  private int life;

  private final OtyughType type;


  /**
   * Represents default constructor with default life 2.
   */
  public OtyughImpl() {
    life = 2;
    this.type = OtyughType.OTYUGH;
  }

  /**
   * The method enables Otyugh to kill the user.
   *
   * @return boolean whether the player got killed or not;
   */
  @Override
  public boolean kill() {
    int kill = RAND.nextInt(1, 6);
    if (this.life == 1) {
      return kill <= 3;
    } else {
      return this.life == 2;
    }
  }

  @Override
  public boolean isAlive() {
    return this.life > 0;
  }

  @Override
  public String takeDamage() {
    this.life--;
    if (this.life > 0) {
      return "\nYou hear a great howl in the distance";
    } else {
      return "\nYou killed an Otyugh";
    }
  }

  @Override
  public int otyughLife() {
    return life * 50;
  }

  @Override
  public int getPosition() {
    return 0;
  }

  @Override
  public void setPosition(int position) {
  //not required here.
  }

  @Override
  public OtyughType getType() {
    return this.type;
  }

}

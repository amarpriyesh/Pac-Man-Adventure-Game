package otyugh;

import static dungeon.dungeon.DungeonImpl.RAND;

/**
 * The below class represents Monster of Otyugh type, it has different property than Otyugh  and
 * has its own probablity of killing player.
 */
public class Monster extends OtyughImpl {

  private  OtyughType type;
  int life;
  private int positionID;

  /**
   * Represents a constructor to create a monster with life 1.
   */
  public Monster() {

    this.life = 1;
    this.type = OtyughType.MONSTER;
  }

  @Override
  public boolean kill() {
    int kill = RAND.nextInt(1, 10);
    life = 1;
    return kill <= 3;
  }

  @Override
  public int getPosition() {
    return positionID;
  }

  @Override
  public void setPosition(int position) {
    this.positionID = position;
  }


  @Override
  public OtyughType getType() {
    return this.type;
  }
}

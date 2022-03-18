package otyugh;

/**
 * The below class represents Pit of Otyugh type, it has different property than Otyugh  and
 * has its own probablity of killing player.
 */
public class Pit extends OtyughImpl {

  private  OtyughType type;
  private int positionID;

  /**
   * Represents a constructor to create a pit of type pit.
   */
  public Pit() {
    this.type = OtyughType.PIT;
  }

  @Override
  public boolean kill() {
    return true;
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

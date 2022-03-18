package otyugh;

/**
 * The below class represents Thief of Otyugh type, it has different property than Otyugh  and
 * can rob player of its gems.
 */
public class Thief extends OtyughImpl {

  private  OtyughType type;
  private int positionID;


  /**
   * The below constructor helps to create a thief and sets the type to thief.
   */
  public Thief() {
    this.type = OtyughType.THIEF;
  }

  @Override
  public boolean kill() {
    return false;
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

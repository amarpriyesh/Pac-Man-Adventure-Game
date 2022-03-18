package dungeon.position;

/**
 * The class represents the directions of movement allowed in the game.
 */

public enum Move {
  E("0"), W("1"), N("2"), S("3");
  private String val;

  Move(String val) {
    this.val = val;
  }

  public String getVal() {
    return this.val;
  }

  public void setVal(String value) {
    this.val = value;
  }
}

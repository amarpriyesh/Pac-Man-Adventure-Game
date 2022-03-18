package player;

import dungeon.position.Artifact;
import dungeon.position.Position;

import java.util.ArrayList;
import java.util.List;




/**
 * The class represents the real world player and hosts methods to support its movement.
 */
public class PlayerImpl implements Player {


  private final String name;
  private final List<Artifact> artifact;
  private Position position;
  private boolean alive;


  /**
   * represents player constructor to create player using the following arams.
   *
   * @param name     represents nanme.
   * @param position represents position of the player.
   */
  public PlayerImpl(String name, Position position) {
    this.name = name;
    this.position = position;
    this.artifact = new ArrayList<>();
    this.artifact.add(Artifact.ARROW);
    this.artifact.add(Artifact.ARROW);
    this.artifact.add(Artifact.ARROW);
    alive = true;

  }

  /**
   * Moves the player from one location to another.
   *
   * @param pos location object.
   */
  @Override
  public void move(Position pos) {
    this.position = pos;
  }


  /*public Position getPosition() {
    return this.position;
    //this.treasure.add(pos.)
  }
*/
  @Override
  public int getPositionId() {
    return this.position.getPosition();
  }

  @Override
  public String toString() {

    if (artifact.size() > 0) {


      return String.format("\nPlayer %s, has %s\n%s", getName(),
              getTreasure(), position.toString());
    } else {
      return String.format("\n%s", position.toString());
    }
  }

  @Override
  public Boolean hasArrow() {
    return artifact.contains(Artifact.ARROW);
  }

  @Override
  public void shootArrow() {
    artifact.remove(Artifact.ARROW);
  }

  private String getTreasure() {
    int countDiamond = 0;
    int countSapphire = 0;
    int countRuby = 0;
    int countArrow = 0;
    for (Artifact t : this.artifact
    ) {
      switch (t) {
        case RUBY:
          countRuby++;
          break;
        case SAPPHIRE:
          countSapphire++;
          break;
        case DIAMOND:
          countDiamond++;
          break;
        case ARROW:
          countArrow++;
          break;
        //default does not do anything here.
        default:
          break;
      }

    }
    StringBuilder sb = new StringBuilder();
    if (countDiamond > 0) {
      sb.append(countDiamond + " Diamond, ");
    }
    if (countSapphire > 0) {
      sb.append(countSapphire + " Sapphire, ");
    }
    if (countRuby > 0) {
      sb.append(countRuby + " Ruby, ");
    }
    if (countArrow > 0) {
      sb.append(countArrow + " Arrow");
    }
    return sb.toString();
  }

  /**
   * The method sets treasures in  the treasure list of the player.
   *
   * @param t returns Treasure.
   */
  @Override
  public void setTreasure(Artifact t) {
    this.artifact.add(t);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String playerDetails() {
    if (artifact.size() > 0) {


      return String.format("\nPlayer %s, has %s\n%s", getName(),
              getTreasure(), position.toString());
    } else {
      return String.format("\n%s", position.toString());
    }
  }

  @Override
  public boolean playerStatus() {
    return alive;
  }

  @Override
  public void playerSetStatus(boolean bool) {
    alive = bool;
  }

  @Override
  public void playerRemoveTreasure() {
    this.artifact.clear();
  }

}

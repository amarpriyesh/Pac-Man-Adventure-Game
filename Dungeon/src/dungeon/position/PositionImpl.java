package dungeon.position;

import otyugh.Monster;
import otyugh.Otyugh;
import otyugh.OtyughType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * The class represents location where a player could move in and houses various methods.
 */
public class PositionImpl implements Position {
  private final int position;
  private final int pathCount;
  private final List<Artifact> artifact;
  private final Map<Move, Integer> validMove;
  private Type type;
  private Otyugh otyugh;
  private boolean hasPlayer;
  private boolean hasPlayerVisited;
  private Monster monster;


  /**
   * Represents constructor to create location objects.
   *
   * @param position  position id of the location.
   * @param validMove takes in map of valid moves ayt a particular location.
   */
  public PositionImpl(int position, Map<Move, Integer> validMove) {
    //todo put validation
    this.validMove = validMove;
    artifact = new ArrayList<>();
    this.position = position;
    pathCount = validMove.size();
    if (pathCount == 1) {
      this.type = Type.CAVE;
    } else if (pathCount == 2) {
      this.type = Type.TUNNEL;
    } else if (pathCount > 2) {
      this.type = Type.CAVE;
    }
    hasPlayer = false;
    hasPlayerVisited = false;
    monster = null;
  }

  /**
   * Represents constructor to create deep copy location objects.
   *
   * @param other represents other location.
   */
  public PositionImpl(PositionImpl other) {
    this.position = other.position;
    this.type = other.type;
    this.pathCount = other.pathCount;
    this.artifact = other.artifact;
    this.validMove = other.validMove;
    this.hasPlayer = false;
    this.hasPlayerVisited = false;
  }

  public int getPosition() {
    return this.position;
  }

  @Override
  public boolean validMove(Move move) {
    return this.validMove.containsKey(move);
  }

  @Override
  public String validMoveStr() {
    Move[] mov = {Move.E, Move.W, Move.N, Move.S};
    String validMove = "";
    for (int i = 0; i < mov.length; i++) {
      if (this.validMove.containsKey(mov[i])) {
        validMove = validMove + " " + mov[i];
      }
    }
    return validMove;
  }

  @Override
  public int getValidMove(Move move) {
    if (validMove.get(move)!=null) {
      return validMove.get(move);
    }
    else {
      return 100000;
    }
  }

  @Override
  public Map<Move, Integer> getValidMove() {
    return validMove;
  }


  @Override
  public String toString() {
    if (artifact.size() > 0) {
      return String.format("You are in a %s, Player Position:%d\n"
                      +
                      "You find %s here\n" + "Doors lead to %s\n",
              this.type.toString(), this.position,
              getTreasure(this.artifact), validMoveStr());
    }
    return String.format("You are in a %s, Player Position:%d\n"
                    +
                    "Doors lead to %s\n", this.type.toString(), this.position,
            validMoveStr());
  }


  @Override
  public void removeTreasure(Artifact t) {
    this.artifact.remove(t);
  }

  @Override
  public boolean getTreasure(Artifact t) {
    return artifact.contains(t);
  }

  private String getTreasure(List<Artifact> tres) {
    int countDiamond = 0;
    int countSapphire = 0;
    int countRuby = 0;
    int countArrow = 0;
    for (Artifact t : tres
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

  @Override
  public boolean getTreasure() {
    return artifact.size() > 0;
  }

  @Override
  public void setTreasure(Artifact artifact) {
    this.artifact.add(artifact);
  }

  @Override
  public Type getType() {
    return this.type;
  }


  @Override
  public boolean hasTreasure() {
    return artifact.size() > 0;
  }

  @Override
  public boolean hasDiamond() {
    return artifact.contains(Artifact.DIAMOND);
  }

  @Override
  public boolean hasRuby() {
    return artifact.contains(Artifact.RUBY);
  }

  @Override
  public boolean hasSapphire() {
    return artifact.contains(Artifact.SAPPHIRE);
  }

  @Override
  public boolean hasArrow() {
    return artifact.contains(Artifact.ARROW);
  }

  @Override
  public boolean getOtyugh() {
    return this.otyugh != null;
  }

  @Override
  public void setOtyugh(Otyugh otyugh) {
    this.otyugh = otyugh;
  }

  @Override
  public boolean isAliveOtyugh() {
    return this.otyugh != null && otyugh.getType().equals(OtyughType.OTYUGH)
            && this.otyugh.isAlive();
  }


  @Override
  public boolean otyughKillTest() {
    return otyugh.kill();
  }

  @Override
  public int otyughLife() {
    return otyugh.otyughLife();
  }

  @Override
  public boolean hasPlayer() {
    return this.hasPlayer;
  }

  @Override
  public void setHasPlayer(boolean val) {
    hasPlayer = val;
  }

  @Override
  public boolean hasPlayerVisited() {
    return hasPlayerVisited;
  }

  public void setPlayerVisited(boolean val) {
    hasPlayerVisited = val;
  }

  @Override
  public void addMonster(Otyugh monster) {
    this.monster = (Monster) monster;
  }

  @Override
  public void removeMonster() {
    this.monster = null;
  }

  @Override
  public boolean hasMonster() {
    return this.monster != null;
  }


  @Override
  public OtyughType getOtyughType() {
    if (getOtyugh()) {
      return this.otyugh.getType();
    }
    return null;
  }
}


package dungeon.position;

import otyugh.Otyugh;
import otyugh.OtyughType;

import java.util.Map;




/**
 * It represents interface to the position class and has definition of its method.
 */
public interface Position {
  /**
   * Returns the position ID of a location in integer.
   *
   * @return position id in integer.
   */
  int getPosition();

  /**
   * Returns the valid moves in string format.
   *
   * @return valid moves.
   */
  String validMoveStr();

  /**
   * Provides info if moving to a particular position would be valid or not.
   *
   * @param move direction of the movement.
   * @return boolean val.
   */
  boolean validMove(Move move);

  /**
   * Returns the int value of valid moves related to  a location.
   *
   * @param move direction of move.
   * @return valid move in integer.
   */
  int getValidMove(Move move);

  /**
   * The method returns a map of Move and position for gameplay and testing.
   *
   * @return map of move and location.
   */
  Map<Move, Integer> getValidMove();

  /**
   * The method returns the type of location as cave or tunel.
   *
   * @return type of loaction.
   */
  Type getType();

  /**
   * The method returns the list of treasure housed at the location.
   *
   * @return list of treasures.
   */
  boolean getTreasure(Artifact t);

  /**
   * The below method returns if the position has a particular tresure of not.
   *
   * @return true false.
   */
  boolean getTreasure();

  /**
   * Sets treasure to a location.
   *
   * @param artifact treasure.
   */
  void setTreasure(Artifact artifact);

  /**
   * The method removes all the treasures housed at a location.
   */
  void removeTreasure(Artifact t);

  /**
   * Checks if location has treasures or not.
   *
   * @return boolean.
   */
  boolean hasTreasure();

  /**
   * The below method checks if a particular location has alive otyugh.
   *
   * @return true false.
   */
  boolean isAliveOtyugh();


  /**
   * The below method checks if there a otyugh dead or alive at a given position.
   *
   * @return true false.
   */
  boolean getOtyugh();

  /**
   * The method sets otyugh at a particular position.
   *
   * @param otyugh represents otyugh.
   */
  void setOtyugh(Otyugh otyugh);


  /**
   * The below method tests chance of otyugh getting killed.
   *
   * @return true/false for testing.
   */
  boolean otyughKillTest();

  /**
   * The beow method returns life of otyugh.
   *
   * @return integer life.
   */
  int otyughLife();

  /**
   * The below method sets the position for player variable.
   * @param val  takes in the boolean value.
   */
  void setHasPlayer(boolean val);

  /**
   *  The below method returns if the position has player.
   * @return @param val reurns has player boolean.
   */
  boolean hasPlayer();

  /**
   * The below method returns  the location as visited by  the player.
   * @return boolean value if player has visited.
   */
  boolean hasPlayerVisited();

  /**
   * The below method sets the value for position if player has visited it.
   * @param val true and false for player visit.
   */
  void setPlayerVisited(boolean val);

  /**
   * Returns true if position has diamonds.
   * @return boolean diamond val.
   */
  boolean hasDiamond();

  /**
   * Returns true if position has ruby.
   * @return boolean ruby val.
   */
  boolean hasRuby();

  /**
   * Returns true if position has sapphire.
   * @return boolean sapphire val.
   */
  boolean hasSapphire();

  /**
   * Returns true if position has arrows.
   * @return boolean arrow val.
   */
  boolean hasArrow();

  /**
   * The below method adds new monster to the location.
   * @param monster represents monster.
   */
  void addMonster(Otyugh monster);

  /**
   * The below method return the type of Otyugh whether as Otyugh enum type.
   * @return Otyugh  as enum.
   */
  OtyughType getOtyughType();

  /**
   * The below method removes the monster from the specific position.
   */
  void removeMonster();

  /**
   * The methods checks and returns if the current position has monsters.
   * @return true if th position has monster.
   */
  boolean hasMonster();

}

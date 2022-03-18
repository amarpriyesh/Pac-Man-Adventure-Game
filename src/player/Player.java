package player;

import dungeon.position.Artifact;
import dungeon.position.Position;


/**
 * It represents the interface of the player class and has definition to its methods.
 */
public interface Player {

  /**
   * Mover the player to a specific location.
   *
   * @param pos location.
   */
  void move(Position pos);


  /**
   * Returns id if the player, which is bacically the position.
   *
   * @return player id.
   */
  int getPositionId();

  /**
   * The  method gives the name of the player.
   *
   * @return name of the player.
   */
  String getName();

  /**
   * Sets treasure values to the player.
   *
   * @param t returns list of type Treasure.
   */
  void setTreasure(Artifact t);

  /**
   * The method checks if the player has arrows.
   *
   * @return true false.
   */
  Boolean hasArrow();

  /**
   * The below method helps player to shoot arrow.
   */
  void shootArrow();

  /**
   * The below method gets the player details in string format.
   * @return details as string.
   */
  String playerDetails();

  /**
   * The below method return the status of the player if the player is alive.
   * @return boolean if player is alive.
   */
  boolean playerStatus();

  /**
   * Sets the life of player if player is alive.
   * @param status represents life as boolean.
   */
  void playerSetStatus(boolean status);

  /**
   * The below method removes the treasure from the player when the player meets a thief.
   */
  void playerRemoveTreasure();


}

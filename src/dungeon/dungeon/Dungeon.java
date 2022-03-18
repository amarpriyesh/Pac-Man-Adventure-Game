package dungeon.dungeon;

import dungeon.position.Artifact;
import dungeon.position.Move;
import dungeon.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;




/**
 * It represents an interface to the class DungeonImpl,which defines the common methods required to
 * execute the game and move the player.
 */
public interface Dungeon extends ReadOnlyDungeon {


  /**
   * The method returns the player description in string format.
   *
   * @return player description.
   */
  String toString();

  /**
   * The method moves the player in required direction.
   *
   * @param move Direction to move the player.
   */
  String movePlayer(Move move);

  /**
   * Returns the printed version of the dungeon, gems and player along with the description.
   *
   * @return Returns printed dungeon in strings.
   */
  String printDungeonNew();

  /**
   * The method gives the state of the game.
   *
   * @return Returns the state of the game, END, PLAYING.
   */
  GameState getGameState();


  /**
   * This method prints the treasure on the map to help the user to find the gems.
   *
   * @param show boolean value to show or hide the treasures.
   */
  void showTreasureOnMap(boolean show);

  /**
   * Sends the deep copy of the map for testing purpose so  that players and positions
   * could be tested.
   *
   * @return map of location.
   */
  Map<Integer, Position> getPositionMap();


  /**
   * The method returns the start point of the player.
   *
   * @return start point of the player.
   */
  int getStartPoint();

  /**
   * The method returns the end point where the player has to go.
   *
   * @return end point in int.
   */
  int getEndPoint();

  /**
   * The method helps player to pickup treasures and arrows.
   */
  String pickUp(Artifact t);

  /**
   * The below method takes distance and directiona as parameters and help player shoot the otyugh.
   *
   * @param m        move.
   * @param distance distance.
   */
  String shoot(Move m, int distance);

  /**
   * The method sets up dungeon implementing different algos.
   */
  void gameSetup();

  /**
   * The below method shows Otyugh on the map.
   *
   * @param b true or false.
   */
  void showOtyughOnMap(boolean b);

  /**
   * The below method shows the dungeon.
   *
   * @param b true or false.
   */
  void showDungeon(boolean b);

  /**
   * The below method helps user to to find exact distances using depth search algo.
   *
   * @param start provide a reference point.
   * @return returns list of list of positions by distance.
   */
  List<List<Integer>> dfs(int start);

  /**
   * The below method gives Oyyughs position.
   *
   * @return Otyugh position.
   */
  Set<Integer> getOtyughPosition();

  /**
   * The below method returns player position.
   *
   * @return player pos.
   */
  int retPlayerPos();

  /**
   * The method helps to provide if the movement is a valid move.
   * @param m position.
   * @return returns the message.
   */
  String isValidPosition(int m);

  /**
   * The belo method provides new  life to the player and sets game to playing.
   */
  void reLive();

  /**
   * The below method shows the real time game results.
   * @return Returns the state of the game in String format.
   */
  String gameResult();

  /**
   * Start game in cheat mode showing all the dungeon constituents.
   */
  void setCheatMode(boolean bool);

  /**
   * The  below method sets moving monster in the game.
   */
  void setMonsters();

  /**
   * The below method stops and starts the moving of monster.
   * @param bool value to turn the monster.
   */
  void toggleRunMonster(boolean bool);

  /**
   * The below method sets Thives and Pits in the game so that the game
   * could be played interactively.
   */
  void distributePitsThieves();

  public int dfs(int start, int end);
}

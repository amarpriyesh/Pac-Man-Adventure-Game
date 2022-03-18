package dungeon.dungeon;


import dungeon.position.Artifact;
import dungeon.position.Move;
import dungeon.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * The below class represents mock model for dungeon used to test model and controller in isolation.
 */
public class MockModel implements Dungeon {

  private List<Boolean> log;

  /**
   * The below constructor represents the mock model used to mock the model functions to
   * test controller.
   * @param bool list of boolean values.
   */
  public MockModel(List<Boolean> bool) {
    log = bool;
  }
  /**
   * The method moves the player in required direction.
   *
   * @param move Direction to move the player.
   */

  @Override
  public String movePlayer(Move move) {
    log.add(true);
    return null;
  }

  /**
   * The method returns dungeon map to the view for the drawing of board and interactive gameplay.
   *
   * @return Dungeon map.
   */
  @Override
  public Map<Integer, String[]> dungeonMap() {
    log.add(true);
    return null;
  }

  /**
   * Returns the printed version of the dungeon, gems and player along with the description.
   *
   * @return Returns printed dungeon in strings.
   */
  @Override
  public String printDungeonNew() {
    log.add(true);
    return null;
  }

  /**
   * The method gives the state of the game.
   *
   * @return Returns the state of the game, END, PLAYING.
   */
  @Override
  public GameState getGameState() {
    log.add(true);
    return null;
  }

  /**
   * This method prints the treasure on the map to help the user to find the gems.
   *
   * @param show boolean value to show or hide the treasures.
   */
  @Override
  public void showTreasureOnMap(boolean show) {
    //not required.
  }

  /**
   * Sends the deep copy of the map for testing purpose so  that players and positions
   * could be tested.
   *
   * @return map of location.
   */
  @Override
  public Map<Integer, Position> getPositionMap() {
    return null;
  }

  /**
   * The method returns the start point of the player.
   *
   * @return start point of the player.
   */
  @Override
  public int getStartPoint() {
    return 0;
  }

  /**
   * The method returns the end point where the player has to go.
   *
   * @return end point in int.
   */
  @Override
  public int getEndPoint() {
    return 0;
  }

  /**
   * The method helps player to pickup treasures and arrows.
   *
   * @param t represents artifact.
   */
  @Override
  public String pickUp(Artifact t) {
    return null;
  }

  /**
   * The below method takes distance and directiona as parameters and help player shoot the otyugh.
   *
   * @param m        move.
   * @param distance distance.
   */
  @Override
  public String shoot(Move m, int distance) {
    return null;
  }

  /**
   * The method sets up dungeon implementing different algos.
   */
  @Override
  public void gameSetup() {
    //not required.
  }

  /**
   * The below method shows Otyugh on the map.
   *
   * @param b true or false.
   */
  @Override
  public void showOtyughOnMap(boolean b) {
    //not required.
  }

  /**
   * The below method shows the dungeon.
   *
   * @param b true or false.
   */
  @Override
  public void showDungeon(boolean b) {
    //not required.
  }

  /**
   * The below method helps user to to find exact distances using depth search algo.
   *
   * @param start provide a reference point.
   * @return returns list of list of positions by distance.
   */
  @Override
  public List<List<Integer>> dfs(int start) {
    return null;
  }

  /**
   * The below method gives Oyyughs position.
   *
   * @return Otyugh position.
   */
  @Override
  public Set<Integer> getOtyughPosition() {
    return null;
  }

  /**
   * The below method returns player position.
   *
   * @return player pos.
   */
  @Override
  public int retPlayerPos() {
    return 0;
  }

  /**
   * The below method returns the dimension of the dungeon.
   *
   * @return dimension of board as array.
   */
  @Override
  public int[] dimensions() {
    return new int[0];
  }

  /**
   * The  below method returns the player details as string.
   *
   * @return player details.
   */
  @Override
  public String getPlayerDetails() {
    return null;
  }

  /**
   * The method helps to provide if the movement is a valid move.
   *
   * @param m position.
   * @return returns the message.
   */
  @Override
  public String isValidPosition(int m) {
    return null;
  }

  /**
   * The belo method provides new  life to the player and sets game to playing.
   */
  @Override
  public void reLive() {
    //not required.
  }

  /**
   * The below method shows the real time game results.
   *
   * @return Returns the state of the game in String format.
   */
  @Override
  public String gameResult() {
    log.add(true);
    return null;
  }

  /**
   * Start game in cheat mode showing all the dungeon constituents.
   *
   * @param bool represents boolean value.
   */
  @Override
  public void setCheatMode(boolean bool) {
    log.add(true);
  }

  /**
   * The  below method sets moving monster in the game.
   */
  @Override
  public void setMonsters() {
    log.add(true);
  }

  /**
   * The below method stops and starts the moving of monster.
   *
   * @param bool value to turn the monster.
   */
  @Override
  public void toggleRunMonster(boolean bool) {
    log.add(true);
  }

  /**
   * The below method sets Thives and Pits in the game so that the game
   * could be played interactively.
   */
  @Override
  public void distributePitsThieves() {
    log.add(true);
  }

  @Override
  public int dfs(int start, int end) {
    return 0;
  }
}

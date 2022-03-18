package dungeon.dungeon;

import java.util.Map;

/**
 * It represents an interface to the class DungeonImpl,which defines the common methods required to
 * read the game parameters used by the view.
 */
public interface ReadOnlyDungeon {

  /**
   * The method returns dungeon map to the view for the drawing of board and interactive gameplay.
   * @return Dungeon map.
   */
  Map<Integer, String[]> dungeonMap();

  /**
   * The method returns dungeon in textual format.
   * @return textual representation of dungeon map.
   */
  String printDungeonNew();

  /**
   * Th  below method returns the player position as integer.
   * @return position of player.
   */
  int retPlayerPos();

  /**
   * The below method returns the dimension of the dungeon.
   * @return dimension of board as array.
   */
  int[] dimensions();

  GameState getGameState();

  String gameResult();

  /**
   * The  below method returns the player details as string.
   * @return player details.
   */
  String getPlayerDetails();

}
